package pages;

import baseEntities.BasePage;
import core.BrowsersService;
import core.ReadProperties;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import wrappers.Button;
import wrappers.InputField;

@Log4j2
public class LoginPage extends BasePage {

    private final static String ENDPOINT = "/index.php?/auth/login";

    private final static By LOGIN_PAGE_TITLE = By.className("loginpage-installationname");
    private final static By EMAIL_FIELD = By.id("name");
    private final static By PASSWORD_FIELD = By.id("password");
    private final static By BUTTON_FIELD = By.id("button_primary");
    private final static By INCORRECT_LOGIN_MESSAGE = By.className("error-text");

    public LoginPage(BrowsersService browsersService, boolean openPageByUrl) {
        super(browsersService, openPageByUrl);
    }

    @Override
    protected void openPage() {
        browsersService.getDriver().get(ReadProperties.getInstance().getURL() + ENDPOINT);
    }

    @Override
    public boolean isPageOpened() {
        try {
            return getLoginPageInstallationName().isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    private WebElement getLoginPageInstallationName() {
        return browsersService.getWaiters().waitForVisibility(LOGIN_PAGE_TITLE);
    }

    private InputField getNameField() {
        return new InputField(browsersService, EMAIL_FIELD);
    }

    private InputField getPasswordField() {
        return new InputField(browsersService, PASSWORD_FIELD);
    }

    private Button getLoginButton() {
        return new Button(browsersService, BUTTON_FIELD);
    }

    private WebElement getIncorrectLoginMessage() {
        return browsersService.getWaiters().waitForVisibility(INCORRECT_LOGIN_MESSAGE);
    }

    private LoginPage inputEmail(String email) {
        getNameField()
                .sendKeys(email);
        return this;
    }

    private LoginPage inputPasswordField(String password) {
        getPasswordField()
                .sendKeys(password);
        return this;
    }

    private void clickButton() {
        getLoginButton()
                .click();
    }

    public String getErrorLoginMessageTest() {
        return getIncorrectLoginMessage().getText();
    }

    @Step("Login with correct attributes and click Log in button")
    public DashboardPage successfulLogin() {
        log.info("Step: Login with correct attributes and click Log in button");
        inputEmail(ReadProperties.getInstance().getUsername())
                .inputPasswordField(ReadProperties.getInstance().getPassword())
                .clickButton();
        return new DashboardPage(browsersService, true);
    }

    @Step("Login with incorrect password Log in button")
    public LoginPage unsuccessfulLogin() {
        log.info("Step: Login with incorrect password Log in button");
        inputEmail(ReadProperties.getInstance().getUsername())
                .inputPasswordField(ReadProperties.getInstance().getUsername())
                .clickButton();
        return this;
    }

    @Step("Enter a value {email} in the email input Log in button")
    public LoginPage loginWithParameters(String email, String password) {
        log.info("Step: Enter a value {email} in the email input Log in button");
        inputEmail(email)
                .inputPasswordField(password)
                .clickButton();
        return this;
    }
}


