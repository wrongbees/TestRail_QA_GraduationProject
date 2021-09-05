package wrappers;

import core.BrowsersService;
import org.openqa.selenium.By;

public class InputField {
    private final UIElement uiElement;

    public InputField(BrowsersService browsersService, By by) {
        this.uiElement = new UIElement(browsersService, by);
    }

    public void sendKeys(CharSequence... charSequences) {
        uiElement.sendKeys(charSequences);
    }

    public boolean isDisplayed() {
        return uiElement.isDisplayed();
    }
}
