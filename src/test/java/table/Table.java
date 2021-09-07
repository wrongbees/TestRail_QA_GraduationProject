package table;

import core.BrowsersService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import wrappers.UIElement;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private BrowsersService browsersService;
    private UIElement uiElement;
    private List<TableRow> tableRowList = new ArrayList<>();

    public Table(BrowsersService browsersService, WebElement webElement) {
        this.browsersService = browsersService;
        uiElement = new UIElement(browsersService, webElement);


        for( WebElement element: uiElement.findElements(By.xpath("//tr[@class != 'header']")))
        tableRowList.add(new TableRow(browsersService, element));

    }
}
