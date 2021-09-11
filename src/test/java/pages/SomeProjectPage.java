package pages;

import baseEntities.BasePage;
import core.BrowsersService;
import core.ReadProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import pages.baseHeaderPage.HeaderDashboard;
import wrappers.Button;

public class SomeProjectPage extends HeaderDashboard {

    private final static String ENDPOINT = "/index.php?/projects/overview/%d";

    private final static By SOME_PROJECT_TITLE = By.cssSelector(".page_title");

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
}
