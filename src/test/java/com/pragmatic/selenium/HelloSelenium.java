package com.pragmatic.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class HelloSelenium{

    WebDriver driver;



    /**
     *  Test with valid user credentials
     *
     */
    @Test
    public void testLoginWithValidCredentials(){


        //Launch the web browser
        driver = new ChromeDriver();

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

    @BeforeClass
    private void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void testLoginWithInvalidPassword(){


        //Launch the web browser
        driver = new ChromeDriver();

        //Navigate to login page (URL)
        driver.get("http://hrm.pragmatictestlabs.com/");

        //Type username
        //Locate the element and then send the text to type
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");

        //Type password
        driver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");

        //Click login button
        driver.findElement(By.id("btnlogin")).click();

        //Verify error message
        String errorMessage = driver.findElement(By.id("spanMessage")).getText();
        Assert.assertEquals(errorMessage, "Invalid credentials");

        //Logout

        //Close the browser
        driver.close();

    }


    @Test
    public void testLoginWithBlankUsernameAndBlankPassword(){


        //Launch the web browser
        driver = new ChromeDriver();

        //Navigate to login page (URL)
        driver.get("http://hrm.pragmatictestlabs.com/");

        //Type username
        //Locate the element and then send the text to type
        driver.findElement(By.id("txtUsername")).clear();

        //Type password
        driver.findElement(By.id("txtPassword")).clear();

        //Click login button
        driver.findElement(By.id("btnlogin")).click();

        //Verify error message
        String errorMessage = driver.findElement(By.id("spanMessage")).getText();
        Assert.assertEquals(errorMessage, "Username cannot be empty");

        //Logout

        //Close the browser
        driver.close();
    }




}
