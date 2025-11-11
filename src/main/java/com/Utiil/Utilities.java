package com.Utiil;

import java.util.List;

import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Utilities {
	public WebDriverManager driver;
	// Utility method to search for a position in the list
	public static void getSpecificPosition(List<String> positionList, String positionsName) {
		boolean found = false;
		for (String name : positionList) {

			if (name.equalsIgnoreCase(positionsName)) {
				System.out.println("✅ Found position: " + name);
				found = true;
				break;
			}
		}
		if (!found) {
			System.out.println("❌ Position '" + positionsName + "' not found in the table.");
		}
		// System.out.println("Total number of position is : "+positionsName.length());
	}

	public static void getTotalNumberOfPosition(List<String> positionList, String keyword) {
	    int count = 0;
	    for (String pos : positionList) {
	        if (pos.toLowerCase().contains(keyword.toLowerCase())) {
	            count++;
	        }
	    }
	    if (count > 0) {
	        System.out.println("✅ Total number of '" + keyword + "' found: " + count);
	    } else {
	        System.out.println("❌ Position '" + keyword + "' not found in the table.");
	    }
	}


}
