package com.pragmatic.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class DragAndDropExample {


    private static final String BASE_URL = "http://demosite.pragmatictestlabs.com/Droppable.html";
    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @AfterClass
    public void afterClass() {
        System.out.println("DragAndDropExample.afterClass");
    }


    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    }


    @AfterMethod (enabled = false)
    public void afterMethod() {
        //driver.close();
    }

    @Test
    public void testDragAndDrop() {
        WebElement eleSource = driver.findElement(By.id("draggableview")); //Source element
        WebElement eleTarget = driver.findElement(By.id("droppableview"));//Target element

        Actions actions = new Actions(driver);
        Action action = actions.dragAndDrop(eleSource, eleTarget).build();
        action.perform();

    }

    @Test
    public void testDragToLeftCornerOfTheTarget(){
        WebElement eleSource = driver.findElement(By.id("draggableview")); //Source element
        WebElement eleTarget = driver.findElement(By.id("droppableview"));//Target element

        Actions actions = new Actions(driver);

        int targetX = eleTarget.getLocation().x + eleTarget.getRect().width - eleSource.getLocation().x;
        int targetY = eleTarget.getRect().height;
        actions.dragAndDropBy(eleSource, targetX, targetY)
                .build()
                .perform();

    }
}
