package tests;

import baseEntities.BaseTest;
import models.ModelsFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class BoundaryValuesTest extends BaseTest{

    @Test(dataProvider = "BoundaryInputFiledValue", dataProviderClass = DataProvider.class)
    public void positiveBoundaryValuesTest(int numberOfValuesInputFiled){
        ModelsFactory modelsFactory = new ModelsFactory();
        String newGeneratedString = modelsFactory.stringGenerator(numberOfValuesInputFiled);
        SomeTestCasePage someTestCasePage = new LoginPage(browsersService, true)
                .successfulLogin()
                .clickSomeProjectPage("привет12")
                .clickDashboardTestCaseButton()
                .clickAddTestCaseButton()
                .successfullyAddTestCase(newGeneratedString);
        Assert.assertEquals(someTestCasePage.getTestCaseName().getText(),newGeneratedString);
    }

    @Test
    public void negativeNullBoundaryValueTest() {
        AddTestCasePage addTestCasePage = new LoginPage(browsersService, true)
                .successfulLogin()
                .clickSomeProjectPage("привет12")
                .clickDashboardTestCaseButton()
                .clickAddTestCaseButton()
                .unsuccessfullyAddTestCase("");
        Assert.assertEquals(addTestCasePage.getTestCaseErrorLabel().getText(),
                "Field Title is a required field.");
    }

    @Test(dataProvider = "NegativeBoundaryInputFiledValue", dataProviderClass = DataProvider.class)
    public void negativeBoundaryValuesTest(int numberOfValuesInputFiled){
        int maxValueSymbolsInString = 250;
        ModelsFactory modelsFactory = new ModelsFactory();
        String newGeneratedString = modelsFactory.stringGenerator(numberOfValuesInputFiled);
        SomeTestCasePage someTestCasePage = new LoginPage(browsersService, true)
                .successfulLogin()
                .clickSomeProjectPage("привет12")
                .clickDashboardTestCaseButton()
                .clickAddTestCaseButton()
                .successfullyAddTestCase(newGeneratedString);
        String actualResult = someTestCasePage.getTestCaseName().getText();
        String expectedResult = newGeneratedString.substring(0,maxValueSymbolsInString);
        Assert.assertEquals(actualResult,expectedResult);
    }
}
