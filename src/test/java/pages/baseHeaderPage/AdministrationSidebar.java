package pages.baseHeaderPage;

import baseEntities.BasePage;
import core.BrowsersService;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import pages.AdministrationProjectsPage;
import wrappers.Button;

public class AdministrationSidebar extends BasePage {

    private final static By SIDEBAR_LOCATOR_PRESENT = By.id("sidebar");
    private final static By PROJECTS_BUTTON = By.id("navigation-sub-projects");

    public AdministrationSidebar(BrowsersService browsersService, boolean openPageByUrl) {
        super(browsersService, openPageByUrl);
    }

    @Override
    protected void openPage() {
    }

    @Override
    public boolean isPageOpened() {
        try {
            return getSidebarLocatorPresent().isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    private WebElement getSidebarLocatorPresent() {
        return browsersService.getWaiters().waitForVisibility(SIDEBAR_LOCATOR_PRESENT);
    }

    private Button getProjectsButton() {
        return new Button(browsersService, PROJECTS_BUTTON);
    }

    public AdministrationProjectsPage clickProjectsButton(){
        getProjectsButton().click();
        return new AdministrationProjectsPage(browsersService,false);
    }
}
