package com.Utils;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtility {

    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    // Generate random 6-character alpha-numeric string
    public static String getRandomAlphaNumeric() {
        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            int index = RANDOM.nextInt(ALPHANUMERIC.length());
            sb.append(ALPHANUMERIC.charAt(index));
        }
        return sb.toString();
    }

    // Capture screenshot with unique filename
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        if (driver == null) {
            System.out.println("⚠️ WebDriver is null, skipping screenshot.");
            return null;
        }

        try {
            // Validate browser is open
            driver.getTitle(); // throws if browser is closed

            // Create screenshot folder if missing
            String folderPath = System.getProperty("user.dir") + File.separator + "Screen_Shoot";
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Build full path
            String fileName = screenshotName + "_" + getRandomAlphaNumeric() + ".png";
            String fullPath = folderPath + File.separator + fileName;

            // Capture and save
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(fullPath));

            System.out.println("📸 Screenshot saved at: " + fullPath);
            return fullPath;

        } catch (Exception e) {
            System.err.println("❌ Screenshot capture failed: " + e.getMessage());
            return null;
        }
    }
}
