package com.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * ExtentReportManager - Handles creation and configuration of ExtentReports for
 * the ZSolution automation framework.
 *
 * This class ensures a single report instance is used throughout execution,
 * applies a timestamped report file name, and loads custom CSS/JS if available.
 */
public class ExtentReportManager {

	private static ExtentReports extent;

	/**
	 * Creates and returns a singleton ExtentReports instance.
	 * 
	 * @return ExtentReports instance
	 */
	public static ExtentReports getInstance() {
		if (extent == null) {
			extent = createInstance();
		}
		return extent;
	}

	/**
	 * Builds and configures a new ExtentReports instance with Spark reporter.
	 * 
	 * @return Configured ExtentReports instance
	 */
	private static ExtentReports createInstance() {
	    ExtentReports extent = new ExtentReports();

	    // Set default report folder if system property is not set
	    String reportFolder = System.getProperty("extent.report.folder");
	    if (reportFolder == null || reportFolder.isEmpty()) {
	        reportFolder = System.getProperty("user.dir") + "/reports"; // default folder inside project
	    }

	    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    String reportPath = reportFolder + "/ExtentReport_" + timestamp + ".html";

	    ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
	    spark.config().setTheme(Theme.STANDARD);
	    spark.config().setDocumentTitle("M&T Automation Report");
	    spark.config().setReportName("M&T Test Execution Report");

	    extent.attachReporter(spark);

	    extent.setSystemInfo("Project Name", "M&T Automation Project");
	    extent.setSystemInfo("Framework Type", "Selenium WebDriver + TestNG");
	    extent.setSystemInfo("Author", "Khosruz Zaman");
	    extent.setSystemInfo("OS", System.getProperty("os.name"));
	    extent.setSystemInfo("Java Version", System.getProperty("java.version"));

	    return extent;
	}

	/**
	 * Flushes and closes the report instance.
	 */
	public static void flushReport() {
		if (extent != null) {
			extent.flush();
		}
	}
}