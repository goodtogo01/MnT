package com.test;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Setup.BaseSetup;
import com.Utiil.Utilities;
import com.page.DepartmentSections;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DepartmentSectionsTest extends BaseSetup {
	WebDriverManager driver;
	public static Utilities util;
	public static DepartmentSections departmentSections;

	@BeforeMethod
	public static void tearUp() {
		initDriver();
		util = new Utilities();
		departmentSections = new DepartmentSections(getDriver());
	}

	@Test
	public void titleTest() {
		String expectedTitle = "Employee Records Table";

		System.out.println("Title found as: " + getDriver().getTitle());

		Assert.assertEquals(getDriver().getTitle(), expectedTitle, "Title did not matched!!!");
	}
	
	
	@Test
	public static void getDeptCount() {
		// can be use any dept. name
		int depcount = departmentSections.getEngineeringDepartmentCount("Engineering"); 
		System.out.println("Total number of Engineering department employees: " +depcount);
		int numberOfDept = departmentSections.getTotalNumberOfDepartment();
		System.out.println("Number of dept: "+numberOfDept);
	}

	
	@AfterMethod
	public void tearDown() {
		quitDriver();
	}
}
