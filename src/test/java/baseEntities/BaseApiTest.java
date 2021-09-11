package baseEntities;


import core.BrowsersService;
import core.ReadProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;

import static io.restassured.RestAssured.given;

public abstract class BaseApiTest {

    public BrowsersService browsersService;
    public static Logger logger = LoggerFactory.getLogger(BaseApiTest.class);


    @BeforeMethod
    public void startBrowser() throws MalformedURLException {
        browsersService = new BrowsersService();
    }

    @BeforeTest
    public void setup(){
        RestAssured.baseURI = ReadProperties.getInstance().getURL();

        RestAssured.requestSpecification = given()
                .header(HTTP.CONTENT_TYPE, ContentType.JSON)

                .auth().preemptive().basic(
                        ReadProperties.getInstance().getUsername(),
                        ReadProperties.getInstance().getPassword());
    }

    @AfterMethod
    public void closeBrowser() {
        browsersService.getDriver().quit();
        browsersService = null;
    }
}

