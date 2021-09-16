package tests;

import baseEntities.BaseUITest;
import io.qameta.allure.Description;
import models.ModelsFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddEditTestCasePage;
import pages.AdministrationProjectsPage;
import pages.LoginPage;

import java.awt.*;

public class SmokeTests extends BaseUITest {

    @Description("Positive test for editing a test case by uploading a second file")
    @Test
    public void positiveEditTestCaseTest() throws AWTException, InterruptedException {

        cases = ModelsFactory.getCases();

        AddEditTestCasePage addEditTestCasePage = new LoginPage(browsersService, true)
                .successfulLogin()
                .clickProjectLink(project)
                .clickDashboardTestCaseButton()
                .clickAddTestCaseButton()
                .addTestCase(cases)
                .clickEntityAttachmentFieldButton()
                .downloadFile("pooh.jpg")
                .clickAttachButton()
                .clickAddTestCaseButton()
                .clickEditTestCaseButton()
                .clickEntityAttachmentFieldButton()
                .downloadFile("cat.jpg")
                .clickAttachButton();

        Assert.assertTrue(addEditTestCasePage.getAddTestCaseButton().isEnabled());
    }

    @Description("Positive test for deleting a project")
    @Test(dependsOnMethods = "positiveEditTestCaseTest", alwaysRun = true)
    public void positiveDeleteProjectTest() {
        AdministrationProjectsPage adminPage = new LoginPage(browsersService, true)
                .successfulLogin()
                .clickAdministrationButton()
                .clickProjectsButton()
                .deleteProject(project);

        Assert.assertThrows(java.lang.NullPointerException.class, () -> adminPage.deleteProject(project));
    }
}
