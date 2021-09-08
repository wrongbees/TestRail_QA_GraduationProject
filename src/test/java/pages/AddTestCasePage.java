package pages;

import baseEntities.BasePage;
import core.BrowsersService;
import core.ReadProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import wrappers.Button;
import wrappers.InputField;

public class AddTestCasePage extends BasePage {

    private final static String ENDPOINT = "index.php?/cases/add/2";//???

    private final static By TEST_CASE_TITLE_LABEL = By.xpath("//label[@for = 'title']");
    private final static By TEST_CASE_TITLE_INPUT = By.id("title");
    private final static By ADD_TEST_CASE_BUTTON = By.id("accept");
    private final static By TEST_CASE_ERROR_LABEL = By.xpath(" //*[contains(text(),'Field')]");

    public AddTestCasePage(BrowsersService browsersService, boolean openPageByUrl) {
        super(browsersService, openPageByUrl);
    }

    @Override
    protected void openPage() {
        browsersService.getDriver().get(ReadProperties.getInstance().getURL() + ENDPOINT);
    }

    @Override
    public boolean isPageOpened() {
        try {
            return getTestCaseInstallationName().isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    private WebElement getTestCaseInstallationName(){
        return browsersService.getWaiters().waitForVisibility(TEST_CASE_TITLE_LABEL);
    }

    private InputField getTestCaseTitleInput(){
        return new InputField(browsersService,TEST_CASE_TITLE_INPUT);
    }

    private Button getAddTestCaseButton(){
        return new Button(browsersService,ADD_TEST_CASE_BUTTON);
    }

   public WebElement getTestCaseErrorLabel(){
        return browsersService.getWaiters().waitForVisibility(TEST_CASE_ERROR_LABEL);}

    private void inputTestCaseTitle(String testCaseTitle) {
        getTestCaseTitleInput()
                .sendKeys(testCaseTitle);
    }

    private void clickAddTestCaseButton() {
        getAddTestCaseButton()
                .click();
    }

    public SomeTestCasePage successfullyAddTestCase(String testCaseTitle){
        inputTestCaseTitle(testCaseTitle);
        clickAddTestCaseButton();
        return new SomeTestCasePage(browsersService,false);
    }

    public AddTestCasePage unsuccessfullyAddTestCase(String testCaseTitle){
        inputTestCaseTitle(testCaseTitle);
        clickAddTestCaseButton();
        return this;
    }
}
