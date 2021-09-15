package tests;

import baseEntities.BaseTest;
import io.qameta.allure.Description;
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

    @Description("Positive test for adding a project using random values")
    @Test
    public void positiveAddProjectTest() {
        this.project = ModelsFactory.getProject();
        AdministrationProjectsPage adminPage = new LoginPage(browsersService, true)
                .successfulLogin()
                .clickAddProjectButton()
                .addProject(project);

        Assert.assertTrue(adminPage.projectIsFound(project));
    }

    @Description("Positive test for editing a test case by uploading a second file")
    @Test(dependsOnMethods = "positiveAddProjectTest")
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
