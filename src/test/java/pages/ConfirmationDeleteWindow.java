package pages;

import baseEntities.BasePage;
import core.BrowsersService;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class ConfirmationDeleteWindow extends BasePage {

    private final static By TITLE = By.id("ui-dialog-title-deleteDialog");

    public ConfirmationDeleteWindow(BrowsersService browsersService, boolean openPageByUrl) {
        super(browsersService, openPageByUrl);
    }

    @Override
    protected void openPage() {

    }

    @Override
    public boolean isPageOpened() {
        try {
            return browsersService.getDriver().findElement(TITLE).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
