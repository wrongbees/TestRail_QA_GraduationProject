package pages;

import baseEntities.BasePage;
import core.BrowsersService;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import wrappers.Button;

public class TestCasesPage extends BasePage {

    private final static By TEST_CASES_TITLE = By.cssSelector(".page_title");
    private final static By ADD_TEST_CASE_BUTTON = By.id("sidebar-cases-add");

    public TestCasesPage(BrowsersService browsersService, boolean openPageByUrl) {
        super(browsersService, openPageByUrl);
    }

    @Override
    protected void openPage() {

    }

    @Override
    public boolean isPageOpened() {
        try {
            return getTestCasesInstallationName().isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    private WebElement getTestCasesInstallationName(){
        return browsersService.getWaiters().waitForVisibility(TEST_CASES_TITLE);
    }

    private Button getAddTestCaseButton(){
        return new Button(browsersService,ADD_TEST_CASE_BUTTON);
    }

    public void clickAddTestCaseButton(){
        getAddTestCaseButton()
                .click();
    }
}
