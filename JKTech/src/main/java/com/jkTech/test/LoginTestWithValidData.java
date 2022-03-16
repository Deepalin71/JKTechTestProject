package com.jkTech.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTestWithValidData {
	public static void main(String[] args) {

		// Declare variable
		ExcelDataConfig excel = new ExcelDataConfig("TestData/LoginTestFile.xls");
		String name = null;
		String pswd = null;
		String loginStatus = null;
		SoftAssert softAssert = new SoftAssert();

		// Open browser
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		// Navigate to URL
		String URL = "http://uitestingplayground.com/sampleapp";
		driver.navigate().to(URL);

		// Find all webElements
		WebElement nameBox = driver.findElement(By.xpath("//input[@name='UserName']"));
		WebElement pswdBox = driver.findElement(By.xpath("//input[@name='Password']"));
		WebElement loginButton = driver.findElement(By.xpath("//button[@id='login']"));

		System.out.println("Total rows in sheet:: "+excel.lastRow(0));
		for (int i = 1;i<excel.lastRow(0); i++) {
			System.out.println("\nRow number:: "+i);
			//Fetch test data
			name = excel.getData(0, i, 0);
			pswd = excel.getData(0, i, 1);

			//Put test data into textBox and click login
			nameBox.sendKeys(name);
			pswdBox.sendKeys(pswd);
			loginButton.click();
			
			System.out.println("Test name:: "+name+"\nTest password:: "+pswd);
			
			loginStatus = driver.findElement(By.cssSelector("#loginstatus")).getText();
			
			//Verify message with valid data
			if (name != "" && pswd.equals("pwd")) {
				softAssert.assertEquals(loginStatus, "Welcome, " + name.trim() + "!", "Unable to Login with valid credentials");
				System.out.println("User successfully login with valid credentials!!!");
				loginButton.click();
			} 
			
			//verify message with invalid data
			else {
				softAssert.assertEquals(loginStatus, "Invalid username/password",
						"User is able to login with incorrect credentials ");
				System.out.println("User is unable to login with incorrect credentials!!!");
				loginButton.click();

			}
			

		}
		driver.close();
	}

}
