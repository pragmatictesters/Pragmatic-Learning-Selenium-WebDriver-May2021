package com.pragmatic.testng;

import com.pragmatic.Calculator;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class TestCalculator {



    @Test
    public void testAddNumbers(){
        Calculator calculator = new Calculator();
        int answer = calculator.addNumbers(2, 5);
        Assert.assertEquals(answer, 7, "Test is failed");
        Assert.assertEquals(calculator.addNumbers(200, 7), 207);
    }

}
