package tests;

import baseEntities.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class MessageTest extends BaseTest {

    @Test
    public void positivePopUpMessageTest() {
        new LoginPage(browsersService, true)
                .successfulLogin();
        WebElement element = browsersService.getWaiters().waitForVisibility(By.className("icon-display-small"));
        Actions actions = new Actions(browsersService.getDriver());
        actions
                .moveToElement(element)
                .build()
                .perform();
        WebElement popUpMessage = browsersService.getWaiters().waitForVisibility(By.className("tooltip-header"));

        Assert.assertEquals(popUpMessage.getText(), "Compact View");
    }
}
