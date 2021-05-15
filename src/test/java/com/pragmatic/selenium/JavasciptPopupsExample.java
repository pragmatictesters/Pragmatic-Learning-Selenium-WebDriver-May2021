package com.pragmatic.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
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

import java.util.concurrent.TimeUnit;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class JavasciptPopupsExample {

    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.get("http://demosite.pragmatictestlabs.com/Alerts.html");
    }


    @AfterMethod
    public void afterMethod() {
        driver.close();
    }

    @Test
    public void testAlertMessage() {
        //Click the button in webpage
        driver.findElement(By.xpath("//button[text()='Simple Alert']")).click();
        //Switch to alert
        Alert alert = driver.switchTo().alert();
        String strMessage = alert.getText();
        Assert.assertEquals(strMessage, "This Is Simple Alert");
        alert.accept();
    }

    @Test
    public void testAlertAccept() {

        //Click the button in webpage
        driver.findElement(By.xpath("//button[text()='Simple Alert']")).click();
        //Switch to alert
        Alert alert = driver.switchTo().alert();
        alert.accept();
        //Verify the message
        String strMessage = driver.findElement(By.id("confirm-demo")).getText();
        Assert.assertEquals(strMessage, "Alert is gone.");
    }


    @Test
    public void testConfirmationMessage() {
        driver.findElement(By.xpath("//button[contains(text(),'Confirm Alert')]")).click();
        Alert confirmation = driver.switchTo().alert();
        Assert.assertEquals(confirmation.getText(), "Press a button!");
        confirmation.accept();

    }

    @Test
    public void testConfirmationOK() {
        driver.findElement(By.xpath("//button[contains(text(),'Confirm Alert')]")).click();
        Alert confirmation = driver.switchTo().alert();
        confirmation.accept();
        //Verify the message
        String strMessage = driver.findElement(By.id("confirm-demo")).getText();
        Assert.assertEquals(strMessage, "Confirmed.");
    }

    @Test
    public void testConfirmationCancel() {
        driver.findElement(By.xpath("//button[contains(text(),'Confirm Alert')]")).click();
        Alert confirmation = driver.switchTo().alert();
        confirmation.dismiss();
        //Verify the message
        String strMessage = driver.findElement(By.id("confirm-demo")).getText();
        Assert.assertEquals(strMessage, "Rejected!");
    }

    @Test
    public void testPromptTypeTextOK() {
        driver.findElement(By.xpath("//button[text()='Prompt Alert']")).click();
        Alert prompt = driver.switchTo().alert();
        prompt.sendKeys("Selenium");
        prompt.accept();
        //Verify the message
        String strMessage = driver.findElement(By.id("confirm-demo")).getText();
        Assert.assertEquals(strMessage, "Selenium");
    }

    @Test
    public void testPromptTypeTextCancel() {
        driver.findElement(By.xpath("//button[text()='Prompt Alert']")).click();
        Alert prompt = driver.switchTo().alert();
        prompt.dismiss();


    }

    @Test
    public void testTimingAlert() throws InterruptedException {
        driver.findElement(By.xpath("//button[text()='Timing Alert']")).click();
        //IMPORTANT Wait till alert is present before switching to the alert
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(
                ExpectedConditions.alertIsPresent()
        );
        Alert alert = driver.switchTo().alert();
        alert.accept();
        //Verify the message
        String strMessage = driver.findElement(By.id("confirm-demo")).getText();
        Assert.assertEquals(strMessage, "0");
    }


}
