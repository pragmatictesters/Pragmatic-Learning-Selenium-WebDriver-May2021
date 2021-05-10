package com.pragmatic.testng;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeSuite;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class TestBase {

    @BeforeSuite
    public void beforeSuite(){
        WebDriverManager.chromedriver().setup();
        System.out.println("TestBase.beforeSuite");
    }

}
