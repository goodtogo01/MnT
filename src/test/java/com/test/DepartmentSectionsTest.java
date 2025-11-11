package com.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.Setup.Reports;
import com.Utils.Utilities;
import com.page.DepartmentSections;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DepartmentSectionsTest extends Reports {

    WebDriverManager driver;
    public Utilities util;
    public DepartmentSections departmentSections;

    @BeforeMethod
    public void tearUp() {
        initDriver();
        util = new Utilities();
        departmentSections = new DepartmentSections(getDriver());
    }

    @Test
    public void titleTest() {
        getTest().info("Validate Page title and Print.");
        String expectedTitle = "Employee Records Table";

        System.out.println("Title found as: " + getDriver().getTitle());
        Assert.assertEquals(getDriver().getTitle(), expectedTitle, "Title did not match!!!");
        getTest().pass("✅ Capture and validate page title successfully.");
    }

    @Test
    public void getDeptCount() {
        getTest().info("Count Number of Departments");

        int depCount = departmentSections.getEngineeringDepartmentCount("Engineering");
        System.out.println("Total number of Engineering department employees: " + depCount);

        int numberOfDept = departmentSections.getTotalNumberOfDepartment();
        System.out.println("Number of departments: " + numberOfDept);

        getTest().pass("✅ Calculate number of departments successfully.");
    }

    @AfterMethod
    public void tearDown() {
        quitDriver();
    }
}