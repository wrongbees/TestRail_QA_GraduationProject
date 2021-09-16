package baseEntities;

import core.BrowsersService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.net.MalformedURLException;

@Listeners(Listener.class)
public abstract class BaseUITest extends BaseTest {
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