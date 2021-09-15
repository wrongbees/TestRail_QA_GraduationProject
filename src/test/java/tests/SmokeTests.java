package tests;

import baseEntities.BaseUITest;
import io.qameta.allure.Description;
import models.ModelsFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddEditTestCasePage;
import pages.AdministrationProjectsPage;
import pages.LoginPage;
import pages.conformationPages.AttachFileWindow;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

public class SmokeTests extends BaseUITest {
    //Project project;
    // Cases cases;

//    @Description("Positive test for adding a project using random values")
//    @Test
//    public void positiveAddProjectTest() {
//        this.project = ModelsFactory.getProject();
//        AdministrationProjectsPage adminPage = new LoginPage(browsersService, true)
//                .successfulLogin()
//                .clickAddProjectButton()
//                .addProject(project);
//
//        Assert.assertTrue(adminPage.projectIsFound(project));
//    }

    @Description("Positive test for editing a test case by uploading a second file")
    @Test//(dependsOnMethods = "positiveAddProjectTest")
    public void positiveEditTestCaseTest() throws AWTException, InterruptedException {

        cases = ModelsFactory.getCases();

        AttachFileWindow window = new LoginPage(browsersService, true)
                .successfulLogin()
                .clickProjectLink(this.project)
                .clickDashboardTestCaseButton()
                .clickAddTestCaseButton()
                .addTestCase(cases)
                .clickEntityAttachmentFieldButton()
                .clickAddNewButton();


        Robot robot = new Robot();
        robot.delay(3000);
        StringSelection stringSelection = new StringSelection(new File("TestCase.xlsx").getAbsolutePath());
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        window = window
                .downloadFile()
                .clickAttachButton()
                .clickAddTestCaseButton()//остановилась здесь
                .clickEditTestCaseButton()
                .clickEntityAttachmentFieldButton()
                .clickAddNewButton();

        robot = new Robot();
        robot.delay(3000);
        stringSelection = new StringSelection(new File("pooh.jpg").getAbsolutePath());
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        AddEditTestCasePage addEditTestCasePage = window
                .downloadFile()
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
