package pages.testcasePage;

import baseEntities.BasePage;
import core.BrowsersService;
import core.ReadProperties;
import models.Cases;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import pages.SomeTestCasePage;
import pages.conformationPages.AttachFileWindow;
import wrappers.Button;
import wrappers.InputField;

public class EditTestCasePage extends BasePage {
    private final static String ENDPOINT = "index.php?/cases/edit/%d";//???

    private final static By TEST_CASE_TITLE_LABEL = By.xpath("//label[@for = 'title']");
    private final static By TEST_CASE_TITLE_INPUT = By.id("title");
    private final static By SAVE_TEST_CASE_BUTTON = By.id("accept");
    private final static By TEST_CASE_ERROR_LABEL = By.xpath("//*[contains(text(),'Field')]");
    private final static By REFERENCE_FIELD = By.id("refs");
    private final static By ENTITY_ATTACHMENT_LIST = By.id("entityAttachmentListAdd");
    private final static By FIRST_FILE_IN_ATTACHMENT_LIST = By.xpath("//*[@id = 'entityAttachmentList']//div[@contenteditable='false']");

    public EditTestCasePage(BrowsersService browsersService, boolean openPageByUrl) {
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

    public Button getSaveTestCaseButton() {
        return new Button(browsersService, SAVE_TEST_CASE_BUTTON);
    }

    public WebElement getTestCaseErrorLabel() {
        return browsersService.getWaiters().waitForVisibility(TEST_CASE_ERROR_LABEL);
    }

    private EditTestCasePage inputTestCaseTitle(String testCaseTitle) {//
        getTestCaseTitleInput()                                       //ужен ли нам этот метод???
                .sendKeys(testCaseTitle);                             //
        return this;
    }

    private EditTestCasePage inputReferenceField(String caseTitle) {//
        getReferenceInputField()                                   //Нужен ли нам этот метод????
                .sendKeys(caseTitle);                              //
        return this;
    }

    /***
     *
     * этот метод возможно не мой
     */
    public SomeTestCasePage clickAddTestCaseButton() {
        getSaveTestCaseButton()
                .click();
        return new SomeTestCasePage(browsersService, false);
    }

    public void clickAddTestCaseButtonWithoutReturn() {//
        getSaveTestCaseButton()                     // Это нам нужно?
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
    public EditTestCasePage addTestCase(Cases someCase) {
        if (someCase.getTitle() != null) {
            inputTestCaseTitle(someCase.getTitle());
        }
        if (someCase.getRefs() != null) {
            inputReferenceField(someCase.getRefs());
        }
        return this;
    }

    public EditTestCasePage unsuccessfullyAddTestCase(String testCaseTitle) {
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
                .replace("(Click and hold to enter delete mode)", "").trim();
    }
}
