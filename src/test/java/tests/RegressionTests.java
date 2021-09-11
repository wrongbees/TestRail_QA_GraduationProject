package tests;

import baseEntities.BaseTest;
import models.ModelsFactory;
import models.Project;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AdministrationProjectsPage;
import pages.LoginPage;

public class RegressionTests extends BaseTest {
    Project project;

    @Test
    public void negativeLoginTest(){
       LoginPage loginPage = new LoginPage(browsersService,true)
                .unsuccessfulLogin();

       Assert.assertEquals(loginPage.getErrorLoginMessageTest(), "Email/Login or Password is incorrect. Please try again.");
    }

    @Test
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
     */


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
