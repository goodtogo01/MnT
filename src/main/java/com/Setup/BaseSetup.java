package com.Setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.Utils.ExtentReportManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import com.aventstack.extentreports.ExtentReports;

public class BaseSetup {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    protected static ExtentReports extent;

    // ------------------ DRIVER SETUP ------------------
    public static WebDriver initDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless"); // remove to see browser
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

    // ------------------ EXTENT REPORT SETUP ------------------
    @BeforeSuite(alwaysRun = true)
    public void setupExtentReport() {
        if (extent == null) {
            extent = ExtentReportManager.getInstance();
        }
    }

    @BeforeClass(alwaysRun = true)
    public void initExtentForClass() {
        if (extent == null) {
            extent = ExtentReportManager.getInstance();
        }
    }
}