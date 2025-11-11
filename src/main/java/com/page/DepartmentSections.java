package com.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DepartmentSections {

    private WebDriver driver;

    // Constructor
    public DepartmentSections(WebDriver driver) {
        this.driver = driver;
    }

    // Table row locator
    private By tableRows = By.xpath("//*[@id='employeeTable']/tbody/tr");

    // Column locators (1-based index)
    private By departmentColumn = By.xpath("//*[@id='employeeTable']/tbody/tr/td[3]");
    private By positionColumn = By.xpath("//*[@id='employeeTable']/tbody/tr/td[6]");
    private By nameColumn = By.xpath("//*[@id='employeeTable']/tbody/tr/td[1]");

 // ✅ Count Total number of Departments exist
    public int getTotalNumberOfDepartment() {
    	  List<WebElement> depts = driver.findElements(departmentColumn);
          int count = 0;
          for (WebElement dept : depts) {
             count++;
          }
         return count;
    }
    
    // ✅ Count how many employees are in the particular Department
    public int getEngineeringDepartmentCount(String name) {
        List<WebElement> depts = driver.findElements(departmentColumn);
        int count = 0;
        for (WebElement dept : depts) {
            if (dept.getText().equalsIgnoreCase(name)) {
                count++;
            }
        }
        return count;
    }

}