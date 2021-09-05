package tests;

import baseEntities.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;

public class TestTest extends BaseTest {

    @Test
    public void LoginTest() {

        new LoginPage(browsersService, true)
                .successfulLogin();
    }


}
