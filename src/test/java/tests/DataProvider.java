package tests;

public class DataProvider {

    @org.testng.annotations.DataProvider(name = "BoundaryInputFiledValue")
    public static Object[][] dataBoundaryInputFiledValue() {
        return new Object[][]{
                {1},
                {249},
                {250},
        };
    }

    @org.testng.annotations.DataProvider(name = "NegativeBoundaryInputFiledValue")
    public static Object[][] dataNegativeBoundaryInputFiledValue() {
        return new Object[][]{
                {251},
        };
    }
}
