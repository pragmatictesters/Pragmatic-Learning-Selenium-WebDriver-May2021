package com.pragmatic.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class AddEmployeeTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.get("http://hrm.pragmatictestlabs.com");
        login();
    }

    private void login() {
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");
        driver.findElement(By.id("btnLogin")).click();
    }

    @AfterMethod
    public void afterMethod() {
        driver.close();
    }

    @Test
    public void testLoginWithBlankUsernameAndBlankPassword() {

        //Wait till the menu element is present in the DOM
        wait = new WebDriverWait(driver, 10);
        wait.pollingEvery(Duration.ofMillis(200));
        wait.withMessage("Menu is not available ");

        wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("menu_pim_viewPimModule2"))
        );

        driver.findElement(By.id("menu_pim_viewPimModule2")).click();

        wait.until(
                ExpectedConditions.elementToBeClickable(By.id("menu_pim_addEmployee"))
        );

        driver.findElement(By.id("menu_pim_addEmployee")).click();
        driver.findElement(By.id("firstName")).sendKeys("Janesha");
        driver.findElement(By.id("lastName")).sendKeys("Kodikarab");
        driver.findElement(By.id("btnSave")).click();
        //String strError = driver.findElement(By.id("spanMessage")).getText();
        //Assert.assertEquals(strError, "Username cannot be empty");
    }


}
