package tests;

import baseEntities.BaseTest;
import models.ModelsFactory;
import models.Project;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import pages.conformationPages.ConfirmationDeleteWindow;
import pages.testcasePage.AddEditTestCasePage;

public class SmokeTests extends BaseTest {
    Project project;

    @Test
    public void positivePopUpMessageTest() {
        DashboardPage dashboardPage = new LoginPage(browsersService, true)
                .successfulLogin()
                .actionForPopUp();

        Assert.assertEquals(dashboardPage.getPopUpMessageTitleText(), "Compact View");
    }

    @Test
    public void positiveUploadingFileTest() {
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

    @Test(dependsOnMethods = "positiveUploadingFileTest",
            dataProvider = "BoundaryInputFiledValue",
            dataProviderClass = DataProvider.class)
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

    @Test(dependsOnMethods = "positiveUploadingFileTest")
    public void negativeNullBoundaryValueTest() {
        AddEditTestCasePage addTestCasePage = new LoginPage(browsersService, true)
                .successfulLogin()
                .clickProjectLink(project)
                .clickDashboardTestCaseButton()
                .clickAddTestCaseButton()
                .unsuccessfullyAddTestCase("");

        Assert.assertEquals(addTestCasePage.getTestCaseErrorLabel().getText(),
                "Field Title is a required field.");
    }

    @Test(dependsOnMethods = "positiveUploadingFileTest",
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

    @Test(dependsOnMethods = "positiveUploadingFileTest")
    public void positiveDialogBoxDisplayTest() {
        ConfirmationDeleteWindow deleteWindow = new LoginPage(browsersService, true)
                .successfulLogin()
                .clickAdministrationButton()
                .clickProjectsButton()
                .openConformationDeleteWindow(project);

        Assert.assertEquals(deleteWindow.getWindowTitleText(), "Confirmation");
    }
}
