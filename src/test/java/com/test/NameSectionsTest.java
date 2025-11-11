package com.test;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Setup.BaseSetup;
import com.Utiil.Utilities;
import com.page.DepartmentSections;
import com.page.NameSections;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NameSectionsTest  extends BaseSetup {
	WebDriverManager driver;
	public static Utilities util;
	public static NameSections nameSections;
	
	@BeforeMethod
	public static void tearUp() {
		initDriver();
		util = new Utilities();
		nameSections = new NameSections(getDriver());
	}

	
	@Test
	public void titleTest() {
		String expectedTitle = "Employee Records Table";

		System.out.println("Title found as: " + getDriver().getTitle());

		Assert.assertEquals(getDriver().getTitle(), expectedTitle, "Title did not matched!!!");
	}
	
	@Test
	public static void getEmployeeDetails() {
		nameSections.findEmployee("David Kim");
                
	}
	
	@AfterMethod
	public void tearDown() {
		quitDriver();
	}
}
