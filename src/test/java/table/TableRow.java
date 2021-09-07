package table;

import core.BrowsersService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import wrappers.UIElement;

import java.util.ArrayList;
import java.util.List;

public class TableRow {
    private BrowsersService browsersService;
    private UIElement uiElement;
    private List<Cell> tableRowList = new ArrayList<>();

    public TableRow(BrowsersService browsersService, WebElement webElement) {
        this.browsersService = browsersService;
        this.uiElement = new UIElement(browsersService,webElement);

        for (WebElement element : this.uiElement.findElements(By.xpath("//td")))
        tableRowList.add(new Cell(browsersService, element));
    }
}
