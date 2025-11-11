package com.Setup;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseSetup {
	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	String pTitle = "Employee Records Table";


	public static WebDriver initDriver() {

		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--headless");
		WebDriver driver = new ChromeDriver(options);

		tlDriver.set(driver);
		getDriver().get("file:///Users/khosruzzaman/JAVA-WORKS/mnt/datatable/employee.html");
		driver.manage().window().maximize();
		return getDriver();
	}

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	

	public static void quitDriver() {
		if (tlDriver.get() != null) {
			tlDriver.get().quit();
			tlDriver.remove();
		}
	}

}
