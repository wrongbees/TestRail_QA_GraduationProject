package pages;

import baseEntities.BasePage;
import core.BrowsersService;
import core.ReadProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import wrappers.Button;

public class SomeTestCasePage extends BasePage {

    private final static String ENDPOINT = "index.php?/cases/view/%d";//???

    private final static By TEST_CASES_TITLE = By.className("content-header-id");
    private final static By TEST_CASES_NAME = By.className("content-header-title page_title");
    private final static By TEST_CASES_TITLE_NAME = By.cssSelector(".page_title");
    private final static By EDIT_TEST_CASES_BUTTON = By.cssSelector(".button-text");

    public SomeTestCasePage(BrowsersService browsersService, boolean openPageByUrl) {
        super(browsersService, openPageByUrl);
    }

    @Override
    protected void openPage() {
        browsersService.getDriver().get(ReadProperties.getInstance().getURL() + ENDPOINT);
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

    public WebElement getTestCaseName(){
        return browsersService.getWaiters().waitForVisibility(TEST_CASES_TITLE_NAME);
    }



    private WebElement getEditTestCaseButton(){ return browsersService.getDriver().findElement(EDIT_TEST_CASES_BUTTON);}

    public AddEditTestCasePage clickEditTestCaseButton(){
       // getEditTestCaseButton().click();
        ((JavascriptExecutor) browsersService.getDriver()).executeScript("arguments[0].click();", getEditTestCaseButton());
        return  new AddEditTestCasePage(browsersService,false);
    }
    public String getTitleText(){
        return getTestCaseName().getText();
    }

}
