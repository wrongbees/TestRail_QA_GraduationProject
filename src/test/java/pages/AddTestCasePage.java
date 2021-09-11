package pages;

import baseEntities.BasePage;
import core.BrowsersService;
import core.ReadProperties;
import models.Cases;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import pages.conformationPages.AttachFileWindow;
import wrappers.Button;
import wrappers.InputField;

public class AddTestCasePage extends BasePage {

    private final static String ENDPOINT = "index.php?/cases/add/2";//???

    private final static By TEST_CASE_TITLE_LABEL = By.xpath("//label[@for = 'title']");
    private final static By TEST_CASE_TITLE_INPUT = By.id("title");
    private final static By ADD_TEST_CASE_BUTTON = By.id("accept");
    private final static By TEST_CASE_ERROR_LABEL = By.xpath("//*[contains(text(),'Field')]");
    private final static By REFERENCE_FIELD = By.id("refs");
    private final static By ENTITY_ATTACHMENT_LIST = By.id("entityAttachmentListEmptyIcon");
    private final static By FIRST_FILE_IN_ATTACHMENT_LIST = By.xpath("//*[@id = 'entityAttachmentList']//div[@contenteditable='false']");

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
            return getTestCaseTitleInput().isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    private WebElement getTestCaseInstallationName() {
        return browsersService.getWaiters().waitForVisibility(TEST_CASE_TITLE_LABEL);
    }

    private WebElement getFirstFileInAttachmentList() {
        return browsersService.getWaiters().waitForVisibility(FIRST_FILE_IN_ATTACHMENT_LIST);
    }

    private InputField getTestCaseTitleInput() {
        return new InputField(browsersService, TEST_CASE_TITLE_INPUT);
    }

    private InputField getReferenceInputField() {
        return new InputField(browsersService, REFERENCE_FIELD);
    }

    private Button getEntityAttachmentField() {
        return new Button(browsersService, ENTITY_ATTACHMENT_LIST);
    }

    private Button getAddTestCaseButton() {
        return new Button(browsersService, ADD_TEST_CASE_BUTTON);
    }

    public WebElement getTestCaseErrorLabel() {
        return browsersService.getWaiters().waitForVisibility(TEST_CASE_ERROR_LABEL);
    }

    private AddTestCasePage inputTestCaseTitle(String testCaseTitle) {
        getTestCaseTitleInput()
                .sendKeys(testCaseTitle);
        return this;
    }

    private AddTestCasePage inputReferenceField(String caseTitle) {
        getReferenceInputField()
                .sendKeys(caseTitle);
        return this;
    }

    /***
     *
     * этот метод возможно не мой
     */
    public SomeTestCasePage clickAddTestCaseButton() {
        getAddTestCaseButton()
                .click();
        return new SomeTestCasePage(browsersService, false);
    }

    public void clickAddTestCaseButtonWithoutReturn() {
        getAddTestCaseButton()
                .click();
    }

    public SomeTestCasePage successfullyAddTestCase(String testCaseTitle) {
        inputTestCaseTitle(testCaseTitle);
        clickAddTestCaseButton();
        return new SomeTestCasePage(browsersService, false);
    }

    /***
     * Добавил метод который должен принимать Cases case
     *
     *
     */
    public AddTestCasePage addTestCase(Cases someCase) {
        if (someCase.getTitle() != null) {
            inputTestCaseTitle(someCase.getTitle());
        }
        if (someCase.getRefs() != null) {
            inputReferenceField(someCase.getRefs());
        }
        return this;
    }

    public AddTestCasePage unsuccessfullyAddTestCase(String testCaseTitle) {
        inputTestCaseTitle(testCaseTitle);
        clickAddTestCaseButtonWithoutReturn();
        return this;
    }

    public AttachFileWindow clickEntityAttachmentFieldButton() {
        getEntityAttachmentField().click();
        return new AttachFileWindow(browsersService);
    }
//!!!!!!!!!!!!!!!!!!! метод возвращает имя файл обрезая фразу (Click and hold to enter delete mode)
    public String getFirstFileName() {
        return getFirstFileInAttachmentList().getAttribute("title")
                .replace("(Click and hold to enter delete mode)","").trim();
    }
}
