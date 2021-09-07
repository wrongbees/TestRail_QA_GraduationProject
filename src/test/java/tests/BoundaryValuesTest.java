package tests;

import baseEntities.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;

public class BoundaryValuesTest extends BaseTest {

    @Test
    public void boundaryValuesTest(){
        new LoginPage(browsersService, true)
                .successfulLogin()
                .clickSomeProjectPage("привет12")
                .clickDashboardTestCaseButton()
                .clickAddTestCaseButton();

    }
}
