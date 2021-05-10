package com.pragmatic.testng;

import org.testng.annotations.Test;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class TestNGExample3 {


    @Test
    public void testMethod1() {
        System.out.println("TestNGExample2.testMethod1");
    }

    @Test (priority = 1)
    public void testMethod2() {
        System.out.println("TestNGExample2.testMethdd2");
    }

    @Test (priority = 0)
    public void testMethod3() {
        System.out.println("TestNGExample2.testMethdd3");
    }


}
