package pages;

import baseEntities.BasePage;
import core.BrowsersService;

public class AdministrationProjectsPage extends BasePage {

    public AdministrationProjectsPage(BrowsersService browsersService, boolean openPageByUrl) {
        super(browsersService, openPageByUrl);
    }

    @Override
    protected void openPage() {

    }

    @Override
    public boolean isPageOpened() {
        return false;
    }
}
