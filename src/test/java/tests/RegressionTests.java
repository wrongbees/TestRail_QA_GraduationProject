package tests;

import baseEntities.BaseUITest;
import core.ReadProperties;
import io.qameta.allure.Description;
import models.ModelsFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddEditTestCasePage;
import pages.DashboardPage;
import pages.LoginPage;
import pages.SomeTestCasePage;
import pages.conformationPages.ConfirmationDeleteWindow;
import utils.DataProvider;

import java.awt.*;

public class RegressionTests extends BaseUITest {

    @Description("Negative test for login with incorrect password")
    @Test
    public void negativeLoginTest() {
        LoginPage loginPage = new LoginPage(browsersService, true)
                .unsuccessfulLogin();

        Assert.assertEquals(loginPage.getErrorLoginMessageTest(), "Email/Login or Password is incorrect. Please try again.");
    }

    @Description("Negative SQL injection test in email input")
    @Test(dependsOnMethods = "negativeLoginTest")
    public void negativeSafetyTest() {
        LoginPage loginPage = new LoginPage(browsersService, true)
                .loginWithParameters("SELECT * FROM users WHERE id=1'", ReadProperties.getInstance().getPassword());

        Assert.assertEquals(loginPage.getErrorLoginMessageTest(), "Email/Login or Password is incorrect. Please try again.");
    }

    @Description("Positive test for displaying a pop-up message")
    @Test(dependsOnMethods = "negativeSafetyTest")
    public void positivePopUpMessageTest() {
        DashboardPage dashboardPage = new LoginPage(browsersService, true)
                .successfulLogin()
                .actionForPopUp();

        Assert.assertEquals(dashboardPage.getPopUpMessageTitleText(), "Compact View");
    }

    @Description("Positive test for uploading file in Test Case")
    @Test(dependsOnMethods = "positivePopUpMessageTest")
    public void positiveUploadingFileTest() throws AWTException, InterruptedException {
        project = ModelsFactory.getProject();
        AddEditTestCasePage addTestCasePage = new LoginPage(browsersService, true)
                .successfulLogin()
                .clickAddProjectButton()
                .addProject(project)
                .clickReturnDashboardPageButton()
                .clickProjectLink(project)
                .clickDashboardTestCaseButton()
                .clickAddTestCaseButton()
                .addTestCase(ModelsFactory.getCases())
                .clickEntityAttachmentFieldButton()
                .downloadFile("config.properties")
                .clickAttachButton();

        Assert.assertEquals(addTestCasePage.getFirstFileName(), "config.properties");
    }

    @Description("Negative Boundary value test for trying to enter string without any symbols in Name Test Case input")
    @Test(dependsOnMethods = "negativeSafetyTest")
    public void negativeNullBoundaryValueTest() {
        project = ModelsFactory.getProject();
        AddEditTestCasePage addTestCasePage = new LoginPage(browsersService, true)
                .successfulLogin()
                .clickAddProjectButton()
                .addProject(project)
                .clickReturnDashboardPageButton()
                .clickProjectLink(project)
                .clickDashboardTestCaseButton()
                .clickAddTestCaseButton()
                .unsuccessfullyAddTestCase("   ");

        Assert.assertEquals(addTestCasePage.getTestCaseErrorLabel().getText(),
                "Field Title is a required field.");
    }

    @Description("Positive Boundary value test for enter valid number of symbols in Name Test Case input")
    @Test(dependsOnMethods = "negativeNullBoundaryValueTest",
            dataProvider = "BoundaryInputFiledValue",
            dataProviderClass = DataProvider.class, alwaysRun = true)
    public void positiveBoundaryValuesTest(int numberOfValuesInputFiled) {
        String newGeneratedString = ModelsFactory.stringGenerator(numberOfValuesInputFiled);
        SomeTestCasePage someTestCasePage = new LoginPage(browsersService, true)
                .successfulLogin()
                .clickProjectLink(project)
                .clickDashboardTestCaseButton()
                .clickAddTestCaseButton()
                .successfullyAddTestCase(newGeneratedString);
        Assert.assertEquals(someTestCasePage.getTestCaseName().getText(), newGeneratedString);
    }

    @Description("Negative Boundary value test for trying to enter values that are higher than allowed in Name Test Case input")
    @Test(dependsOnMethods = "negativeNullBoundaryValueTest",
            dataProvider = "NegativeBoundaryInputFiledValue",
            dataProviderClass = DataProvider.class)
    public void negativeBoundaryValuesTest(int numberOfValuesInputFiled) {
        int maxValueSymbolsInString = 250;
        String newGeneratedString = ModelsFactory.stringGenerator(numberOfValuesInputFiled);
        SomeTestCasePage someTestCasePage = new LoginPage(browsersService, true)
                .successfulLogin()
                .clickProjectLink(project)
                .clickDashboardTestCaseButton()
                .clickAddTestCaseButton()
                .successfullyAddTestCase(newGeneratedString);
        String actualResult = someTestCasePage.getTestCaseName().getText();
        String expectedResult = newGeneratedString.substring(0, maxValueSymbolsInString);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Description("Test for opening dialog window before deleting a project")
    @Test(dependsOnMethods = "negativeBoundaryValuesTest")
    public void positiveDialogBoxDisplayTest() {
        ConfirmationDeleteWindow deleteWindow = new LoginPage(browsersService, true)
                .successfulLogin()
                .clickAdministrationButton()
                .clickProjectsButton()
                .openConformationDeleteWindow(project);

        Assert.assertEquals(deleteWindow.getWindowTitleText(), "Confirmation");
    }
}
