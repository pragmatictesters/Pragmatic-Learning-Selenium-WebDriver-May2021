package com.pragmatic.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class HelloSelenium{


    /**
     *  Test with valid user credentials
     *
     */
    @Test
    public void testLoginWithValidCredentials(){
        //Setup browser driver
        WebDriverManager.chromedriver().setup();


        //Launch the web browser
        WebDriver driver = new ChromeDriver();

        //Navigate to login page (URL)
        driver.get("http://hrm.pragmatictestlabs.com/");

        //Type username
        //Locate the element and then send the text to type
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");

        //Type password
        driver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");

        //Click login button
        driver.findElement(By.id("btnlogin")).click();

        //Verify welcome message
        String welcomeMessage = driver.findElement(By.id("welcome")).getText();
        Assert.assertEquals(welcomeMessage, "Welcome Admin");

        //Logout


        //Close the browser
        driver.close();
    }

    @Test
    public void testLoginWithInvalidPassword(){
        System.out.println("HelloSelenium.testLoginWithInvalidPassword");
    }

}
