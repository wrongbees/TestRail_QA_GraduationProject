package pages;

import baseEntities.BasePage;
import core.BrowsersService;
import core.ReadProperties;
import io.qameta.allure.Step;
import models.Cases;
import org.openqa.selenium.*;
import pages.conformationPages.AttachFileWindow;
import wrappers.Button;
import wrappers.InputField;

public class AddEditTestCasePage extends BasePage {

    private final static String ENDPOINT = "index.php?/cases/add/%d";

    private final static By TEST_CASE_TITLE_LABEL = By.xpath("//label[@for = 'title']");
    private final static By TEST_CASE_TITLE_INPUT = By.id("title");
    private final static By ADD_TEST_CASE_BUTTON = By.id("accept");
    private final static By CANCEL_BUTTON = By.xpath("//*[@class = 'button-group']//a");
    private final static By TEST_CASE_ERROR_LABEL = By.xpath("//*[contains(text(),'Field')]");
    private final static By REFERENCE_FIELD = By.id("refs");
    private final static By ENTITY_ATTACHMENT_LIST_EMPTY_ICON = By.id("entityAttachmentListEmptyIcon");
    private final static By ENTITY_ATTACHMENT_LIST_ADD = By.id("entityAttachmentListAdd");
    private final static By FIRST_FILE_IN_ATTACHMENT_LIST = By.xpath("//*[@id = 'entityAttachmentList']//div[@contenteditable='false']");

    public AddEditTestCasePage(BrowsersService browsersService, boolean openPageByUrl) {
        super(browsersService, openPageByUrl);
    }

    @Override
    protected void openPage() {
        browsersService.getDriver().get(ReadProperties.getInstance().getURL() + ENDPOINT);
    }

    @Override
    public boolean isPageOpened() {
        try {
            return getCancelButton().isEnabled();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    private WebElement getTestCaseInstallationName() {
        return browsersService.getWaiters().waitForVisibility(TEST_CASE_TITLE_LABEL);
    }

    private WebElement getFirstFileInAttachmentList() {
        return browsersService.getWaiters().presenceOfElementLocated(FIRST_FILE_IN_ATTACHMENT_LIST);
    }

    private InputField getTestCaseTitleInput() {
        return new InputField(browsersService, TEST_CASE_TITLE_INPUT);
    }

    private InputField getReferenceInputField() {
        return new InputField(browsersService, REFERENCE_FIELD);
    }

    private WebElement getEntityAttachmentEmptyField() {
        return browsersService.getDriver().findElement(ENTITY_ATTACHMENT_LIST_EMPTY_ICON);
    }

    private WebElement getEntityAttachmentAddField() {
        return browsersService.getDriver().findElement(ENTITY_ATTACHMENT_LIST_ADD);
    }

    public Button getCancelButton() {
        return new Button(browsersService, CANCEL_BUTTON);
    }

    public Button getAddTestCaseButton() {
        return new Button(browsersService, ADD_TEST_CASE_BUTTON);
    }

    public WebElement getTestCaseErrorLabel() {
        return browsersService.getWaiters().waitForVisibility(TEST_CASE_ERROR_LABEL);
    }

    private AddEditTestCasePage inputTestCaseTitle(String testCaseTitle) {
        getTestCaseTitleInput()
                .sendKeys(testCaseTitle);
        return this;
    }

    private AddEditTestCasePage inputReferenceField(String caseTitle) {
        getReferenceInputField()
                .sendKeys(caseTitle);
        return this;
    }

    @Step("Click Add Test Case button and go to the Some Test Case Page")
    public SomeTestCasePage clickAddTestCaseButton() {
        getAddTestCaseButton()
                .click();
        return new SomeTestCasePage(browsersService, false);
    }

    public void clickAddTestCaseButtonWithoutReturn() {
        getAddTestCaseButton()
                .click();
    }

    @Step("Input value in Test Case name, click Add Test Case button and go to Test Case Page")
    public SomeTestCasePage successfullyAddTestCase(String testCaseTitle) {
        inputTestCaseTitle(testCaseTitle);
        clickAddTestCaseButton();
        return new SomeTestCasePage(browsersService, false);
    }

    @Step("Fill test case attributes with random values")
    public AddEditTestCasePage addTestCase(Cases someCase) {
        if (someCase.getTitle() != null) {
            inputTestCaseTitle(someCase.getTitle());
        }
        if (someCase.getRefs() != null) {
            inputReferenceField(someCase.getRefs());
        }
        return this;
    }

    @Step("Enter a value in the Name Test Case input and click Add Test Case button")
    public AddEditTestCasePage unsuccessfullyAddTestCase(String testCaseTitle) {
        inputTestCaseTitle(testCaseTitle);
        clickAddTestCaseButtonWithoutReturn();
        return this;
    }

    @Step("Click on the Entity Attachment List Button for the uploading file in Test Case and open the File Uploading Window")
    public AttachFileWindow clickEntityAttachmentFieldButton() {
        try {
            ((JavascriptExecutor) browsersService.getDriver()).executeScript("arguments[0].click();", getEntityAttachmentEmptyField());
        } catch (ElementNotInteractableException ex) {
            ((JavascriptExecutor) browsersService.getDriver()).executeScript("arguments[0].click();", getEntityAttachmentAddField());
        }
        return new AttachFileWindow(browsersService);
    }

    public String getFirstFileName() {
        return getFirstFileInAttachmentList().getAttribute("title")
                .replace("(Click and hold to enter delete mode)", "").trim();
    }
}
