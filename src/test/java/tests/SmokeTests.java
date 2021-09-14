package tests;

import baseEntities.BaseTest;
import models.Cases;
import models.ModelsFactory;
import models.Project;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddEditTestCasePage;
import pages.AdministrationProjectsPage;
import pages.LoginPage;

import java.awt.*;

public class SmokeTests extends BaseTest {
    Project project;
    Cases cases;

    @Test (description = "Positive test for adding a project using random values")
    public void positiveAddProjectTest() {
        this.project = ModelsFactory.getProject();
        AdministrationProjectsPage adminPage = new LoginPage(browsersService, true)
                .successfulLogin()
                .clickAddProjectButton()
                .addProject(project);

        Assert.assertTrue(adminPage.projectIsFound(project));
    }

    @Test(description = "Positive test for editing a test case by uploading a second file",
            dependsOnMethods = "positiveAddProjectTest")
    public void positiveEditTestCaseTest() throws AWTException, InterruptedException {
        cases = ModelsFactory.getCases();

        AddEditTestCasePage addEditTestCasePage = new LoginPage(browsersService, true)
                .successfulLogin()
                .clickProjectLink(project)
                .clickDashboardTestCaseButton()
                .clickAddTestCaseButton()
                .addTestCase(cases)
                .clickEntityAttachmentFieldButton()
                .downloadFile("StartTest.xml")
                .clickAttachButton()
                .clickAddTestCaseButton()//остановилась здесь
                .clickEditTestCaseButton()
                .clickEntityAttachmentFieldButton()
                .downloadFile("pooh.jpg")
                .clickAttachButton();

        Assert.assertTrue(addEditTestCasePage.getAddTestCaseButton().isEnabled());
    }

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
