package table;


import core.BrowsersService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import wrappers.UIElement;

public class Cell {
    private BrowsersService browsersService;
    private UIElement uiElement;
    private WebDriver driver;

    public Cell(BrowsersService browsersService, WebElement webElement){
        this.browsersService = browsersService;
        uiElement = new UIElement(browsersService, webElement);
    }

    public UIElement getUIElement() {
        return uiElement;
    }
}