package pages;

import baseEntities.BasePage;
import core.BrowsersService;
import core.ReadProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import wrappers.Button;
import wrappers.UIElement;

public class DashboardPage extends BasePage {

    private final static String ENDPOINT = "/index.php?/dashboard";

    private final static By DASHBOARD_PAGE_TITLE = By.className("content-header-title");
    private final static By ADD_PROJECT_BUTTON = By.id("sidebar-projects-add");

    private final static String OPEN_SOME_PROJECT_BUTTON = "//*[@class='grid']//a[contains(text(), '%s')]";


    public DashboardPage(BrowsersService browsersService, boolean openPageByUrl) {
        super(browsersService, openPageByUrl);
    }

    @Override
    protected void openPage() {
        browsersService.getDriver().get(ReadProperties.getInstance().getURL() + ENDPOINT);
    }

    @Override
    public boolean isPageOpened() {
        try {
            return getDashboardPageInstallationName().isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    private WebElement getDashboardPageInstallationName() {
        return browsersService.getWaiters().waitForVisibility(DASHBOARD_PAGE_TITLE);
    }

    private Button getAddProjectButton() {
        return new Button(browsersService, ADD_PROJECT_BUTTON);
    }

    private UIElement getSomeProjectHref(String nameProject) {
        return new UIElement(browsersService,By.xpath(String.format(OPEN_SOME_PROJECT_BUTTON,nameProject)));
    }

    public AddProjectPage clickAddProjectButton() {
        getAddProjectButton()
                .click();
        return new AddProjectPage(browsersService, false);
    }

    public SomeProjectPage clickSomeProjectPage(String nameProject) {
        getSomeProjectHref(nameProject)
                .click();
        return new SomeProjectPage(browsersService, false);
    }
}
