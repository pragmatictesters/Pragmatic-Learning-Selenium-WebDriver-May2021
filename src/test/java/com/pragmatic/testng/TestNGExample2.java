package com.pragmatic.testng;

import org.testng.annotations.Test;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class TestNGExample2 {


    @Test (enabled = true)
    public void testMethod1() {
        System.out.println("TestNGExample2.testMethod1");
    }

    @Test (enabled = false)
    public void testMethod2() {
        System.out.println("TestNGExample2.testMethdd2");
    }

    @Test
    public void testMethod3() {
        System.out.println("TestNGExample2.testMethdd3");
    }


}
