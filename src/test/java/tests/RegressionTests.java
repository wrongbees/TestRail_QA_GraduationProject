package tests;

import baseEntities.BaseTest;
import models.Cases;
import models.ModelsFactory;
import models.Project;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AdministrationProjectsPage;
import pages.LoginPage;
import pages.SomeTestCasePage;
import pages.testcasePage.AddEditTestCasePage;

public class RegressionTests extends BaseTest {
    Project project;
    Cases cases;

    @Test
    public void negativeLoginTest(){
       LoginPage loginPage = new LoginPage(browsersService,true)
                .unsuccessfulLogin();

       Assert.assertEquals(loginPage.getErrorLoginMessageTest(), "Email/Login or Password is incorrect. Please try again.");
    }

    @Test(dependsOnMethods = "negativeLoginTest")
    public void positiveAddProjectTest() {
        this.project = ModelsFactory.getProject();
        AdministrationProjectsPage adminPage = new LoginPage(browsersService, true)
                .successfulLogin()
                .clickAddProjectButton()
                .addProject(project);

        Assert.assertTrue(adminPage.projectIsFound(project));
    }

    /***
     * Здесь можно вставить заваленный тест на редактирование тест-кейса (в этом же проекте сделать тест кейс
     *
     * AddEditTestCasePage - рассмотреть методы
     *
     * EditTestCasePage - добавлен, т.к. отличаются энд поинты (возможно нужно сделать один базовый класс)
     *
     * SomeTestCasePage - добавлена кнопка EDIT_TEST_CASES_BUTTON? с соответствующими методами...
     */
    @Test
    public void positiveEditTestCase(){
        project = Project.builder()               //
                .name("Doloris Barton_Project.")  // подлежит удалению
                .build();                         //

        cases = ModelsFactory.getCases();

        SomeTestCasePage someTestCasePage = new LoginPage(browsersService,true)
                .successfulLogin()
                .clickProjectLink(project)
                .clickDashboardTestCaseButton()
                .clickAddTestCaseButton()
                .addTestCase(cases)
                .clickEntityAttachmentFieldButton()
                .downloadFile("StartTest.xml")
                .clickAttachButton()
                .clickAddTestCaseButton()
                .clickEditTestCaseButton()
                .clickEntityAttachmentFieldButton()
                .downloadFile("pooh.jpg")
                .clickAttachButton()
                .clickAddTestCaseButton();

      Assert.assertEquals(someTestCasePage.getTitleText(),cases.getTitle());

    }

    /***
     * Будем менять на аннотацию?
     * (expectedExceptions = NullPointerException.class, expectedExceptionsMessageRegExp = "Project not found")
     */
    @Test (dependsOnMethods = "positiveAddProjectTest")
    public void positiveDeleteProjectTest() {
      AdministrationProjectsPage adminPage = new LoginPage(browsersService, true)
                .successfulLogin()
                .clickAdministrationButton()
                .clickProjectsButton()
                .deleteProject(project);

        Assert.assertThrows(java.lang.NullPointerException.class, () -> adminPage.deleteProject(project));
    }

}
