package com.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.Setup.Reports;
import com.Utils.Utilities;
import com.page.NameSections;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NameSectionsTest extends Reports {

    WebDriverManager driver;
    public Utilities util;
    public NameSections nameSections;

    @BeforeMethod
    public void tearUp() {
        initDriver();
        util = new Utilities();
        nameSections = new NameSections(getDriver());
    }

    @Test
    public void titleTest() {
        getTest().info("Validating page title...");
        String expectedTitle = "Employee Records Table";
        String actualTitle = getDriver().getTitle();

        System.out.println("Title found as: " + actualTitle);
        Assert.assertEquals(actualTitle, expectedTitle, "Title did not match!");
        getTest().pass("✅ Page title validated successfully");
    }

    @Test
    public void getEmployeeDetails() {
        getTest().info("Fetching Employee Details by name");
        nameSections.findEmployee("Karen Davis");
        getTest().pass("✅ Employee details fetched successfully");
    }

    @AfterMethod
    public void tearDown() {
        quitDriver();
    }
}