package com.demo.test;

import org.testng.annotations.Test;

import com.demo.webdriver.BrowserType;
import com.demo.webdriver.LocalDriverManager;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.testng.Assert;

public class DemoTest { 
	public String baseUrl = "http://demo.guru99.com/test/newtours/";
	public WebDriver driver;
	
	//Make a folder C:\SeleniumDemo and download the git repository

	@Test
	public void verifyHomepageTitle() throws InterruptedException {

		System.out.println("launching browser");
		//Browser can be selected as Chrome or firefox. Swap between lines 24 and 25 for the same
		BrowserType browserType = BrowserType.CHROME;
		//BrowserType browserType = BrowserType.FIREFOX;
		//Launch the browser and baseurl
		driver = LocalDriverManager.getDriver(browserType);
		driver.get(baseUrl);

		//Wait for 3sec for the page to load
		Thread.sleep(3000);
		String expectedTitle = "Welcome: Mercury Tours";
		String actualTitle = driver.getTitle();
		
		//Validate the pagetitle with expected value
		Assert.assertEquals(actualTitle, expectedTitle);
		System.out.println("Title is validated.");
		
		//Wait for a few seconds to load the username and password fields
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Validate the existence of username and password fields
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='userName']")).isDisplayed() && driver.findElement(By.xpath("//input[@name='password']")).isDisplayed(), "Username and password fields are present.");
		Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'REGISTER')]")).isDisplayed(), "Register link is present.");

		//Close the driver to exit the test
		driver.close();
	}
}