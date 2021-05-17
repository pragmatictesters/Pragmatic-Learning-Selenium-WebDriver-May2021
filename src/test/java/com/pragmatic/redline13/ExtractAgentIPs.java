package com.pragmatic.redline13;

import com.pragmatic.selenium.TestData;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class ExtractAgentIPs {

    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void beforeMethod() {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        driver = new ChromeDriver(options);
        driver.get("https://www.redline13.com/Service");
    }


    @AfterMethod
    public void afterMethod() {
        driver.close();
    }

    @Test
    public void extractTheIPs() {
        driver.findElement(By.name("loginEmail")).sendKeys("janesh@pragmatictesters.com");
        driver.findElement(By.name("loginPassword")).sendKeys("password");
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        driver.get("https://www.redline13.com/AWS/ListServers");

        //Rows
        List<WebElement> eleRows = driver.findElements(By.xpath("//table[@id='servers']/tbody/tr"));
        for(WebElement eleRow : eleRows){
           // System.out.println("" + eleRow.getText());
        }

        List<WebElement> eleIPs = driver.findElements(By.xpath("//table[@id='servers']/tbody/tr/td[3]"));
        System.out.println("Number of servers " + eleIPs.size());
        for (WebElement eleIP : eleIPs){
            String strIP = eleIP.getText().split("\n")[0].trim();
            if (strIP.length()>5){
                System.out.println(eleIP.getText().split("\n")[0] +"/32,");
            }
        }
        System.out.println("Number of servers " + eleIPs.size());

    }


}
