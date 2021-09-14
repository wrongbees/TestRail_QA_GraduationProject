package baseEntities;

import core.BrowsersService;

import org.testng.annotations.*;

import java.net.MalformedURLException;

//@Listeners(Listener.class)
public abstract class BaseTest {
    public BrowsersService browsersService;

    @BeforeMethod
    public void startBrowser() throws MalformedURLException {
        browsersService = new BrowsersService();
    }

    @AfterMethod
    public void closeBrowser() {
        browsersService.getDriver().quit();
        browsersService = null;
    }
}