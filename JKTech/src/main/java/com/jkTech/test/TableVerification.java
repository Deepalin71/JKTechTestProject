package com.jkTech.test;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;

public class TableVerification {

	public static void main(String[] args) {
		
		// Declare variable
		int nameIndex=0,CPUIndex=0;
		String CPULoadInTable=null;
		String CPULoadInYellowLabel=null;
		
		// Open browser
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();


		// Navigate to URL
		String URL = "http://uitestingplayground.com/dynamictable";
		driver.navigate().to(URL);

		
		// Find number of columns and rows
		List<WebElement> tableHeaders= driver.findElements(By.xpath("//div[@role='rowgroup'][1]//span"));
		List<WebElement> tableRows= driver.findElements(By.xpath("//div[@role='rowgroup'][2]//div"));

		//Find column with particular headers (Name and CPU)
		for(int i=0;i<tableHeaders.size();i++) {
			if(tableHeaders.get(i).getText().equalsIgnoreCase("Name")) {
				nameIndex=i+1;
			}
			if(tableHeaders.get(i).getText().equalsIgnoreCase("CPU")) {
				CPUIndex=i+1;
			}
			
		}
		
		System.out.println("Name is at "+nameIndex+" index and CPU load is at "+CPUIndex+" index");
		
		//Find CPU load value for a particular browser Chrome
		for(int j=1;j<=tableRows.size();j++) {
			
			if(driver.findElement(By.xpath("//div[@role='rowgroup'][2]//div["+j+"]/span["+nameIndex+"]")).getText().equalsIgnoreCase("Chrome")) {
				CPULoadInTable=driver.findElement(By.xpath("//div[@role='rowgroup'][2]//div["+j+"]/span["+CPUIndex+"]")).getText();
				break;
			}
			
		}
		
		//Find CPU load in Yellow label
		CPULoadInYellowLabel=driver.findElement(By.cssSelector(".bg-warning")).getText();
		CPULoadInYellowLabel=CPULoadInYellowLabel.substring(12);
		
		System.out.println("CPU load time in table:: "+CPULoadInTable);
		System.out.println("CPU load time in Yellow label:: "+CPULoadInYellowLabel);

		//Compare CPU Load
		Assert.assertEquals(CPULoadInYellowLabel, CPULoadInTable, "CPU load doesn't match");
		System.out.println("CPU load for Chrome are equal");
		
		driver.close();
	}

}
