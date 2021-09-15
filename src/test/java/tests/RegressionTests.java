package tests;

import baseEntities.BaseUITest;
import core.ReadProperties;
import models.ModelsFactory;
import models.Project;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import pages.conformationPages.ConfirmationDeleteWindow;
import pages.AddEditTestCasePage;

import java.awt.*;

public class RegressionTests extends BaseUITest {
    Project project;

    @Test
    public void negativeLoginTest(){
        LoginPage loginPage = new LoginPage(browsersService,true)
                .unsuccessfulLogin();

        Assert.assertEquals(loginPage.getErrorLoginMessageTest(), "Email/Login or Password is incorrect. Please try again.");
    }

    @Test(dependsOnMethods = "negativeLoginTest")
    public void negativeSafetyTest(){
        LoginPage loginPage = new LoginPage(browsersService,true)
               .loginWithParameters("SELECT * FROM users WHERE id=1'", ReadProperties.getInstance().getPassword());

        Assert.assertEquals(loginPage.getErrorLoginMessageTest(), "Email/Login or Password is incorrect. Please try again.");
    }

    @Test(dependsOnMethods = "negativeSafetyTest")
    public void positivePopUpMessageTest() {
        DashboardPage dashboardPage = new LoginPage(browsersService, true)
                .successfulLogin()
                .actionForPopUp();

        Assert.assertEquals(dashboardPage.getPopUpMessageTitleText(), "Compact View");
    }

    @Test(dependsOnMethods = "positivePopUpMessageTest")
    public void positiveUploadingFileTest() throws AWTException, InterruptedException {
        this.project = ModelsFactory.getProject();
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

        @Test(dependsOnMethods = "negativeSafetyTest")
    public void negativeNullBoundaryValueTest() {
        this.project = ModelsFactory.getProject();
        AddEditTestCasePage addTestCasePage = new LoginPage(browsersService, true)
                .successfulLogin()
                .clickAddProjectButton()
                .addProject(project)
                .clickReturnDashboardPageButton()
                .clickProjectLink(project)
                .clickDashboardTestCaseButton()
                .clickAddTestCaseButton()
                .unsuccessfullyAddTestCase("");

        Assert.assertEquals(addTestCasePage.getTestCaseErrorLabel().getText(),
                "Field Title is a required field.");
    }

    @Test(dependsOnMethods = "negativeNullBoundaryValueTest",
            dataProvider = "BoundaryInputFiledValue",
            dataProviderClass = DataProvider.class,alwaysRun = true)
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
