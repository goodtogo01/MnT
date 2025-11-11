package com.Setup;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.*;

import com.Utils.ScreenshotUtility;
import com.Utils.ExtentReportManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

/**
 * Reports class handles ExtentReports setup, test logging, and screenshots.
 * All test classes should extend Reports to get reporting functionality.
 */
public class Reports extends BaseSetup {

    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    // ------------------ EXTENT REPORT SETUP ------------------
    @BeforeSuite(alwaysRun = true)
    public void setupExtentReport() {
        extent = ExtentReportManager.getInstance();
    }

    @AfterSuite(alwaysRun = true)
    public void flushExtentReport() {
        if (extent != null) {
            extent.flush();
        }
    }

    // ------------------ TEST LIFECYCLE HOOKS ------------------
    @BeforeMethod(alwaysRun = true)
    public void setupDriverAndTest(Method method) {
        initDriver(); // initialize WebDriver from BaseSetup
        ExtentTest extentTest = extent.createTest(method.getName());
        test.set(extentTest);
    }

    @AfterMethod(alwaysRun = true)
    public void captureResultAndTeardown(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                String screenshotPath = ScreenshotUtility.captureScreenshot(getDriver(), result.getName());
                test.get().fail(result.getThrowable(),
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                test.get().pass("✅ Test Passed Successfully.");
            } else if (result.getStatus() == ITestResult.SKIP) {
                test.get().skip("⚠️ Test Skipped: " + result.getThrowable());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            quitDriver(); // quit driver after each test
        }
    }

    // ------------------ HELPER TO ACCESS CURRENT TEST ------------------
    public static ExtentTest getTest() {
        return test.get();
    }
}