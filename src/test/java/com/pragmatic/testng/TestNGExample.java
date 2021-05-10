package com.pragmatic.testng;

import org.testng.annotations.*;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class TestNGExample {

    @BeforeClass
    public void beforeClass(){
        System.out.println("TestNGExample.beforeClass");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("TestNGExample.afterClass");
    }


    @BeforeMethod
    public void beforeMethod(){
        System.out.println("TestNGExample.beforeMethod");
    }


    @AfterMethod
    public void afterMethod(){
        System.out.println("TestNGExample.afterMethod");
    }

    @Test
    public void testMethod1(){
        System.out.println("TestNGExample.testMethod1");
    }

    @Test
    public void testMethod2(){
        System.out.println("TestNGExample.testMethod2");
    }

    @Test
    public void testMethod3(){
        System.out.println("TestNGExample.testMethod3");
    }



}
