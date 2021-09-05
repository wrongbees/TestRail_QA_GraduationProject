package baseEntities;

import core.BrowsersService;

import org.testng.annotations.*;



public abstract class BaseTest {
    public BrowsersService browsersService;


    @BeforeMethod
    public void startBrowser() {
        browsersService = new BrowsersService();

    }

    @AfterMethod
    public void closeBrowser() {
        browsersService.getDriver().quit();
        browsersService = null;
    }
}