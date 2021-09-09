package tests;

import baseEntities.BaseTest;
import models.ModelsFactory;
import models.Project;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AdministrationProjectsPage;
import pages.LoginPage;

import java.awt.*;

public class TestTest extends BaseTest {
    Project project;
    //Вопросы таковы: 1. Пересмотри пожалуйста мою реализацию радио батона

    /***
     * message from Vladimir: батон вроде бы нормальный...
     */
//2. Когда возвращаем в методе (допустим на добавление проекта) return new Page(bs,true или false????)

    /***
     * message from Vladimir: будем писать return new Page(bs,false)
     */
    //3. Непонятно, что делать с ENDPOINT, если там меняется айдишник проекта
    /***
     * message from Vladimir: наверное будем писать endpoint = "//bla/bla/%s"
     */
    //4. Верхнее табло,где написано dashboard, Working Out, Natalii Lebedeva и правое табло, наверное, надо
    //выносить в отдельные сущности, потому что они неизменяемые практически и есть на каждой странице

    /***
     * message from Vladimir: я на лекции не понял, хотя ты и намекала что твой напарник всё понял.
     */

    @Test
    public void addProjectTest() throws InterruptedException {

        this.project = ModelsFactory.getProject();

        AdministrationProjectsPage adminPage = new LoginPage(browsersService, true)
                .successfulLogin()
                .clickAddProjectButton()
                .addProject(project);

        Assert.assertTrue(adminPage.projectIsFound(project));// проверка на присутствии в таблице

    }


    //6. Корявый тест на загрузку файла.проблема в 71 строке. нам нужно сделать какую-то ожидалку
    // чтобы мы поняли, что файл успел загрузится. я попробовала в 71 строку впихнуть ожидалку на "ждать, когда кнопка станит кликабл
    //решила так попробовать, потому что посмотрела, что пока файл загружается на всю страницу зависает какая-то
    //блокирующая херня
    //это видно через дев тул
    //и в 71 строке падает тест из-за этой блокирующей херни
    //вот ошибка, переведи в переводчике:
    //org.openqa.selenium.ElementClickInterceptedException: element click intercepted: Element <button id="attachmentNewSubmit" type="submit" class="button button-right button-positive button-ok" style="margin-left: 7px">...</button> is not clickable at point (1079, 548). Other element would receive the click: <div class="blockUI blockOverlay" style="z-index: 10000; border: none; margin: 0px; padding: 0px; width: 100%; height: 100%; top: 0px; left: 0px; position: fixed;"></div>
    @Test
    public void uploadingFileTest() throws AWTException, InterruptedException {
        this.project = Project.builder()
                .name("Ms. Sheila Blick_Project.")
                .build();

        new LoginPage(browsersService, true)
                .successfulLogin()
                .clickProjectLink(project)
                .clickDashboardTestCaseButton()
                .clickAddTestCaseButton()
                .successfullyAddTestCase(ModelsFactory.stringGenerator(15))
                .clickEntityAttachmentFieldButton();
      //  .
       // Thread.sleep(3000);

        //browsersService.getDriver().get("https://aqa06vladimirnatali.testrail.io/index.php?/cases/edit/1/1");

//        WebElement addCaseButton3 = browsersService.getWaiters().waitForVisibility(By.id("entityAttachmentListEmptyIcon"));
//        addCaseButton3.click();
//
//
//        WebElement addCaseButton4 = browsersService.getWaiters().waitForVisibility(By.id("libraryAttachmentsAddItem"));
//        addCaseButton4.click();
//
//        String nameImage = "TestCase.xlsx";
//        ClassLoader classLoader = getClass().getClassLoader();
//        File file = new File(Objects.requireNonNull(classLoader.getResource(nameImage)).getFile());
//        String absolutePath = file.getAbsolutePath();
//
//        Robot robot = new Robot();
//
//        StringSelection stringSelection = new StringSelection(absolutePath);
//        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
//
//        robot.setAutoDelay(500);
//        robot.keyPress(KeyEvent.VK_CONTROL);
//        robot.keyPress(KeyEvent.VK_V);
//
//        robot.keyRelease(KeyEvent.VK_CONTROL);
//        robot.keyRelease(KeyEvent.VK_V);
//
//        robot.keyPress(KeyEvent.VK_ENTER);
//        robot.keyRelease(KeyEvent.VK_ENTER);
//
//        WebElement eddCaseButton3 = browsersService.getWaiters().waitForClickable(By.id("attachmentNewSubmit"));
//        eddCaseButton3.click();
//
//        WebElement nameText = browsersService.getWaiters().waitForVisibility(By.id("title"));
//        nameText.sendKeys("LebCase");
//
//        WebElement eddCaseButton4 = browsersService.getWaiters().waitForVisibility(By.id("accept"));
//        eddCaseButton4.click();
//
//        WebElement textMessage = browsersService.getWaiters().waitForVisibility(By.className("message-success"));
//
//        Assert.assertEquals(textMessage.getText(), "Successfully added the new test case. Add another");
    }

    /*
    Тест...
    Добавился проект...
    Удалился проект...
    Проверили (по возникновению ошибки), что проекта нет...
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
