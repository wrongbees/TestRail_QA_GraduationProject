package tests;

import baseEntities.BaseTest;
import models.ModelsFactory;
import models.Project;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddTestCasePage;
import pages.AdministrationProjectsPage;
import pages.LoginPage;


public class TestTest extends BaseTest {
    Project project;

    @Test
    public void addProjectTest() {

        this.project = ModelsFactory.getProject();

        AdministrationProjectsPage adminPage = new LoginPage(browsersService, true)
                .successfulLogin()
                .clickAddProjectButton()
                .addProject(project);

        Assert.assertTrue(adminPage.projectIsFound(project));// проверка на присутствии в таблице

    }

    /***
     *message from Vladimir: все замечания устранены
     *
     *  Произошли изменения в SomeTestCasePage ( нужно удалять закомиченное т.к при создании нового case
     * указанных элементов нет)
     *
     *  Появился AttachFileWindow
     *
     *  Посмотри изменения в AddTestCasePage
     *
     *  Появился класс RobotExecutor
     *
     */
    @Test
    public void uploadingFileTest() {
        this.project = Project.builder()               //
                .name("Ms. Sheila Blick_Project.")     //  эти строчки подлежат удалению
                .build();                              //

        AddTestCasePage addTestCasePage = new LoginPage(browsersService, true)
                .successfulLogin()
                .clickProjectLink(project)
                .clickDashboardTestCaseButton()
                .clickAddTestCaseButton()
                .addTestCase(ModelsFactory.getCases())
                .clickEntityAttachmentFieldButton()
                .downloadFile("config.properties")
                .clickAttachButton();

        Assert.assertEquals(addTestCasePage.getFirstFileName(),"config.properties");
    }

    /*
    Тест...
    Добавился проект...
    Удалился проект...
    Проверили (по возникновению ошибки), что проекта нет...
     */

    /***
     * Будем менять на аннотацию?
     * (expectedExceptions = NullPointerException.class, expectedExceptionsMessageRegExp = "Project not found")
     */
    @Test
    public void deleteProjectTest() {
        Project project = ModelsFactory.getProject();

        System.out.println(project.getName());

        new LoginPage(browsersService, true)
                .successfulLogin()
                .clickAddProjectButton()
                .addProject(project);

        AdministrationProjectsPage adminPage = new AdministrationProjectsPage(browsersService, true)
                .deleteProject(project);

        Assert.assertThrows(java.lang.NullPointerException.class, () -> adminPage.deleteProject(project));

    }
}
