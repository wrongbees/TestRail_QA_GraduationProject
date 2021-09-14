package pages.conformationPages;

import baseEntities.BasePage;
import core.BrowsersService;
import executors.RobotExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import pages.AddEditTestCasePage;
import wrappers.Button;

import java.awt.*;

public class AttachFileWindow extends BasePage {

    private final static By WINDOW_TITLE = By.id("ui-dialog-title-attachmentNewDialogFile");

    private final static By ADD_NEW_BUTTON = By.id("libraryAddAttachment");
    private final static By ATTACH_BUTTON = By.id("attachmentNewSubmit");
    private final static By CANCEL_BUTTON = By.className("button button-right button-negative button-cancel dialog-action-close");
    private final static By DELETE_BUTTON = By.id("libraryDeleteAttachment");

    public AttachFileWindow(BrowsersService browsersService) {
        super(browsersService, false);
    }

    @Override
    protected void openPage() {
    }

    @Override
    public boolean isPageOpened() {
        try {
            return getAddNewButton().isEnabled();

        } catch (NoSuchElementException e) {
            return false;
        }

    }

    private WebElement getWindowTitle() {
        return browsersService.getDriver().findElement(WINDOW_TITLE);
    }

    private Button getAddNewButton() {
        return new Button(browsersService, ADD_NEW_BUTTON);
    }

    private Button getAttachButton() {
        return new Button(browsersService, ATTACH_BUTTON);
    }

    private Button getCancelButton() {
        return new Button(browsersService, CANCEL_BUTTON);
    }

    private Button getDeleteButton() {
        return new Button(browsersService, DELETE_BUTTON);
    }

    private AttachFileWindow clickAddNewButton() {
        getAddNewButton().click();
        return this;
    }

    public void clickCancelButton() {
        getAttachButton().click();
    }

    public void clickDeleteButton() {
        getDeleteButton().click();
    }

    public AddEditTestCasePage clickAttachButton() {
        getAttachButton().click();
        return new AddEditTestCasePage(browsersService, false);
    }

    /***
     *  метод запускает робота для загрузки файла, и ждет 10 сек. пока не появится кнопка
     *                            DELETE
     */

    public AttachFileWindow downloadFile(String fileName) throws AWTException, InterruptedException {
        Thread.sleep(5000);
        clickAddNewButton();
        System.out.println("******************Вызываем робота*****************");
        RobotExecutor.downloadFile(fileName);
        int timeOut = 0;
        boolean isEnable = false;

        while (timeOut < 20 & !isEnable) {
            try {

                isEnable = getDeleteButton().isDisplayed();

            } catch (NoSuchElementException e) {e.printStackTrace();}
            browsersService.sleep(1000);
            timeOut++;
        }
        return this;
    }
}
