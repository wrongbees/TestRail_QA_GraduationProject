package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestTest {

    @Test
    public void first() {
        int a = 4;
        int b = 2;
        int result = a/b;
        Assert.assertEquals(result,2);
    }


}
