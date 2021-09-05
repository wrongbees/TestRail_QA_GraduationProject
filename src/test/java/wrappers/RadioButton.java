package wrappers;

import core.BrowsersService;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class RadioButton {

    private UIElement uiElement;
    private List<UIElement> options = new ArrayList<>();
    private BrowsersService browsersService;

    /***
     * RadioButton ui element для улучшения работы
     * @param browsersService
     * @param by необходимо передать значение аттрибута name
     *
     */
    public RadioButton(BrowsersService browsersService, By by) {
        this.browsersService = browsersService;

        for (WebElement element: browsersService.getDriver().findElements(by)) {
            options.add(new UIElement(browsersService, element));
        }
    }

    public void selectByIndex(int index) {
        boolean isFound = false;
        for (UIElement uiElement : options) {
            if (Integer.parseInt(uiElement.getAttribute("value")) == index) {
                uiElement.click();
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            throw new NoSuchElementException("Опции с таким индексом нет");
        }
    }

    public void selectByText(String optionName) {
        boolean isFound = false;
        for (UIElement element : options) {
            String textValue;
            try {
                textValue = element.getParent().findElement(By.xpath(String.format("./*[text() = '%s']", optionName))).getText();}
            catch (NoSuchElementException ex) {continue;}
            System.out.println(textValue);
            if (textValue.equalsIgnoreCase(optionName)) {
                element.click();
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            throw new NoSuchElementException("Опции с таким текстом нет");
        }
    }
}
