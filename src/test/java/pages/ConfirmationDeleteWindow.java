package pages;

import baseEntities.BasePage;
import core.BrowsersService;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import wrappers.Button;
import wrappers.CheckBox;

public class ConfirmationDeleteWindow extends BasePage {

    private final static By TITLE = By.id("ui-dialog-title-deleteDialog");
    private final static By DELETE_CHECKBOX = By.xpath("//strong[text() ='Yes, delete this project (cannot be undone)']//ancestor::label//*[@name = 'deleteCheckbox']");
    private final static By BUTTON_OK = By.xpath("//*[@id='deleteDialog']/child::div[@class = 'button-group dialog-buttons-highlighted']/child::a");


    public ConfirmationDeleteWindow(BrowsersService browsersService) {
        super(browsersService, false);
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

    public ConfirmationDeleteWindow checkBoxDelete(){
        new CheckBox(browsersService,DELETE_CHECKBOX).changeState(true);
        return this;
    }

    public ConfirmationDeleteWindow unCheckBoxDelete(){
        new CheckBox(browsersService,DELETE_CHECKBOX).changeState(false);
        return this;
    }
    public AdministrationProjectsPage clickButtonOk(){
        new Button(browsersService,BUTTON_OK).click();
        return new AdministrationProjectsPage(browsersService,false);
    }
}
