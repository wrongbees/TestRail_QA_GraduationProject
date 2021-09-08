package pages;

import baseEntities.BasePage;
import core.BrowsersService;
import core.ReadProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class SomeTestCasePage extends BasePage {

    private final static String ENDPOINT = "index.php?/cases/view/3";//???

    private final static By TEST_CASES_TITLE = By.className("content-header-id");
    private final static By TEST_CASES_TITLE_NAME = By.cssSelector(".page_title");

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
}
