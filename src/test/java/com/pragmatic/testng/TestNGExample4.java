package com.pragmatic.testng;

import org.testng.annotations.Test;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class TestNGExample4 {


    @Test ()
    public void testMethod1() {
        System.out.println("TestNGExample2.testMethod1");
    }

    @Test
    public void testMethod2() {
        System.out.println("TestNGExample2.testMethdd2");
    }

    @Test (invocationCount = 3)
    public void testMethod3() {
        System.out.println("TestNGExample2.testMethdd3");
    }


}
