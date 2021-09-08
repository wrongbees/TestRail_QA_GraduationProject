package pages;

import baseEntities.BasePage;
import core.BrowsersService;
import core.ReadProperties;
import models.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import table.Table;

public class AdministrationProjectsPage extends BasePage {
    private static Table table;
    private static final String endpoint = "/index.php?/admin/projects/overview";

    private final static By PROJECTS_PAGE_TITLE = By.xpath("//*[@class = 'content-header-title page_title']");
    private final static By TABLE = By.xpath("//table[@class = 'grid']");


    public AdministrationProjectsPage(BrowsersService browsersService, boolean openPageByUrl) {
        super(browsersService, openPageByUrl);

        table = new Table(browsersService, browsersService.getDriver().findElement(TABLE));
    }

    @Override
    protected void openPage() {
        browsersService.getDriver().get(ReadProperties.getInstance().getURL() + endpoint);
    }

    @Override
    public boolean isPageOpened() {
        try {
            return getProjectPageTitle().isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    private WebElement getProjectPageTitle() {
        return browsersService.getDriver().findElement(PROJECTS_PAGE_TITLE);
    }

    public AdministrationProjectsPage deleteProject(Project project) {

        table.getDeleteCell(project.getName()).click();
       return new ConfirmationDeleteWindow(browsersService)
                .checkBoxDelete()
                .clickButtonOk();

    }

}
