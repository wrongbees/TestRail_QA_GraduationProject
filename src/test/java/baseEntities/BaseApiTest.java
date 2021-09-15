package baseEntities;

import core.ReadProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.protocol.HTTP;
import org.testng.annotations.BeforeTest;
import static io.restassured.RestAssured.given;

public abstract class BaseApiTest extends BaseTest{

    @BeforeTest
    public void setup(){
        RestAssured.baseURI = ReadProperties.getInstance().getURL();

        RestAssured.requestSpecification = given()
                .header(HTTP.CONTENT_TYPE, ContentType.JSON)

                .auth().preemptive().basic(
                        ReadProperties.getInstance().getUsername(),
                        ReadProperties.getInstance().getPassword());
    }
}

