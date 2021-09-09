package pages;

import baseEntities.BasePage;
import core.BrowsersService;
import core.ReadProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import wrappers.Button;

public class SomeProjectPage extends BasePage {

    private final static String ENDPOINT = "/index.php?/projects/overview/%s";

    private final static By SOME_PROJECT_TITLE = By.cssSelector(".page_title");
    private final static By DASHBOARD_TEST_CASE_TITLE = By.id("navigation-suites");

    public SomeProjectPage(BrowsersService browsersService, boolean openPageByUrl) {
        super(browsersService, openPageByUrl);
    }

    @Override
    protected void openPage() {
        browsersService.getDriver().get(ReadProperties.getInstance().getURL() + ENDPOINT);
    }

    @Override
    public boolean isPageOpened() {
        try {
            return getSomeProjectInstallationName().isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    private WebElement getSomeProjectInstallationName() {
        return browsersService.getWaiters().waitForVisibility(SOME_PROJECT_TITLE);
    }

    private Button getDashboardTestCaseButton(){
        return new Button(browsersService,DASHBOARD_TEST_CASE_TITLE);
    }

    public TestCasesPage clickDashboardTestCaseButton(){
        getDashboardTestCaseButton()
                .click();
        return new TestCasesPage(browsersService,false);
    }

}
