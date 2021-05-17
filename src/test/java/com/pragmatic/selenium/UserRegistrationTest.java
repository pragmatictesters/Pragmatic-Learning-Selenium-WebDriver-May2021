package com.pragmatic.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class UserRegistrationTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.get("https://www.ncare.net.au/");

    }

    @AfterMethod(enabled = false)
    public void afterMethod() {
        driver.close();
    }

    @Test
    public void testUserRegistrationWithoutInstitute() {
        driver.findElement(By.linkText("Register")).click();
        //Select the title from the dropdown
        WebElement lstTitle = driver.findElement(By.id("prefix"));
        Select selTitle = new Select(lstTitle);
        selTitle.selectByVisibleText("Mr");
        selTitle.selectByIndex(2);
        selTitle.selectByValue("Professor");



        //Type first name

        //Type last name

        //Type email

        //Type the contact number
        driver.findElement(By.id("contact_number")).sendKeys("0412345678");


        //Moving to an element and calling actions of the elements
        WebElement eleRegister = driver.findElement(By.xpath("//button[@title='Register']"));
        Actions actions = new Actions(driver);
        Action action = actions.moveToElement(eleRegister)
                .pause(Duration.ofMillis(500))
                .click()
                .build();

        action.perform();



    }

    @Test
    public void testUserLogin() {
        driver.findElement(By.partialLinkText("Log")).click();

    }


}
