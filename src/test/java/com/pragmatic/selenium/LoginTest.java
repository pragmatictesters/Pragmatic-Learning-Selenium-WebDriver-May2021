package com.pragmatic.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class LoginTest {

    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        //Implicit wait is NOT RECOMMENDED IN REAL PROJECT
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.get("http://hrm.pragmatictestlabs.com");
    }


    @AfterMethod
    public void afterMethod() {
        driver.close();
    }



    @Test
    public void testLoginWithValidUserCredentials() {
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");
        driver.findElement(By.id("btnLogin")).click();
        String strError = driver.findElement(By.id("welcome")).getText();
        Assert.assertEquals(strError, "Username cannot be empty");

    }


    @Test
    public void testLoginWithBlankUsernameAndBlankPassword() {

        driver.findElement(By.id("txtUsername")).sendKeys("");
        driver.findElement(By.id("txtPassword")).sendKeys("");
        driver.findElement(By.id("btnLogin")).click();
        String strError = driver.findElement(By.id("spanMessage")).getText();
        Assert.assertEquals(strError, "Username cannot be empty");

    }


    @Test(dataProviderClass = TestData.class, dataProvider = "login-data")
    public void testLoginWithInvalidTestData(String username, String password, String expected_error) {
        driver.findElement(By.id("txtUsername")).sendKeys(username);
        driver.findElement(By.id("txtPassword")).sendKeys(password);
        driver.findElement(By.id("btnLogin")).click();
        String strError = driver.findElement(By.id("spanMessage")).getText();
        Assert.assertEquals(strError, expected_error);
    }


    @Test
    public void testLoginWithBlankUsernameOnly() {
        driver.findElement(By.id("txtUsername")).sendKeys("");
        driver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");
        driver.findElement(By.id("btnLogin")).click();
        String strError = driver.findElement(By.id("spanMessage")).getText();
        Assert.assertEquals(strError, "Username cannot be empty");
    }

    @Test
    public void testLoginWithBlankPassword() {
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("");
        driver.findElement(By.id("btnLogin")).click();
        String strError = driver.findElement(By.id("spanMessage")).getText();
        Assert.assertEquals(strError, "Password cannot be empty");
    }


    @Test
    public void testLoginWithInvalidPassword() {
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("Test");
        driver.findElement(By.id("btnLogin")).click();
        String strError = driver.findElement(By.id("spanMessage")).getText();
        Assert.assertEquals(strError, "Invalid credentials");
    }

}
