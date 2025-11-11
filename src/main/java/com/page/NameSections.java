package com.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NameSections {
	private WebDriver driver;

	// Constructor
	public NameSections(WebDriver driver) {
		this.driver = driver;
	}

	// Table row locator
	private By tableRows = By.xpath("//*[@id='employeeTable']/tbody/tr");

	// Column locators (1-based index)
	private By nameColumn = By.xpath("//*[@id='employeeTable']/tbody/tr/td[1]");

	// ✅ Validate if a specific employee exists with given details
	public void findEmployee(String name) {
		List<WebElement> rows = driver.findElements(tableRows);
		boolean found = false;
		for (WebElement row : rows) {
			String headers = row.findElement(By.xpath("//*[@id='employeeTable']/thead/tr")).getText();
			String empName = row.findElement(By.xpath("./td[2]")).getText();
			if (empName.equalsIgnoreCase(name)) {
				System.out.println("✅ Employee found: " + empName);
				System.out.println(headers);
				System.out.println(row.getText());
				found = true;
			break;
			}
		}
		if (!found){
			System.out.println("❌ No employee found with name: " + name);
		}
	}
	
}
