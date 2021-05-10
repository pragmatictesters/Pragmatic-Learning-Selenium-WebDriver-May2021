package com.pragmatic.testng;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class AnnotationExplained  extends  TestBase{

    @Test
    public void testLoginWithValidCredentials(){
        System.out.println("AnnotationExplained.testLoginWithValidCredentials");

    }


    @Test
    public void testLoginWithInvalidCredentials(){
        System.out.println("AnnotationExplained.testLoginWithInvalidCredentials");

    }

    @Test
    public void testLoginWithBlankUsernameAndBlankPassword(){
        System.out.println("AnnotationExplained.testLoginWithBlankUsernameAndBlankPassword");

    }

}
