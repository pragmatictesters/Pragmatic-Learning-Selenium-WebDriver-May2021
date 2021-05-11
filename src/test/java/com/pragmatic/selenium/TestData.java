package com.pragmatic.selenium;

import org.testng.annotations.DataProvider;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class TestData {


    @DataProvider(name ="login-data")
    public static Object[][] loginData(){
        return new Object[][]{
                {"", "", "Username cannot be empty"},
                {"", "Ptl@#321", "Username cannot be empty"},
                {"Admin", "", "Password cannot be empty"},
                {"Admin", "Test", "Invalid credentials"}
        };
    }


    @DataProvider(name= "new-employee-information")
    public static Object[][] newEmployeeInformation(){
        return new Object[] []{
                {"firstname1", "lastname1"},
                {"firstname2", "lastname2"},
                {"firstname3", "lastname3"},
                {"firstname4", "lastname4"}
        };
    }

}
