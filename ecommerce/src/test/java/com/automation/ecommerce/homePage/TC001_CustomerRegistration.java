package com.automation.ecommerce.homePage;

import org.testng.annotations.Test;
import com.automation.ecommerce.basePage.BasePage;
import com.automation.ecommerce.pageUI.CreateLoginAccount;

import org.testng.annotations.BeforeTest;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class TC001_CustomerRegistration extends BasePage
{
	// public WebDriver driver;

	/*
	 * We get NullPointerException if v declare Webdriver here also.
	 * This is because of Declaring the WebDriver driver; in both parent class and
	 * child class.
	 * 
	 * Example : 1.created one base class and in base class declaring the Webdrievr;
	 * 2.Created child class and declare the webdriver in child class.
	 * 3.child class extends base class. this issue comes.
	 * 
	 * So declare the WebDriver driver in either Base class or Child Class.
	 * 
	 */
	
	//@Test(description = "This a new test", timeOut = 20000, priority = -200, dependsOnMethods = "test1", groups ="tutorial")
	@Test(testName="Creating User Account")
	public void customerRegistration() throws Exception
	{
		CreateLoginAccount c = new CreateLoginAccount(driver);
		c.createAccount();
		//c.signInPageError();
		c.accountDetailsForm();
		//c.formPageError();
		//c.logout();
	}
	
	@Test(enabled=false,description="implement for SignIn Page", dependsOnMethods= {"customerRegistration"})
	public void signIn()
	{
		
	}
	
	/*@AfterMethod()
	public void tearDown(ITestResult result)
	{
		Date d = new Date();
		DateFormat dFormat = new SimpleDateFormat("dd-MM-YYY HH:mm:ss");
		
		if(ITestResult.FAILURE==result.getStatus())
		{
			CreateLoginAccount c = new CreateLoginAccount(driver);
			c.screenShot(driver, "Failed"+result.getName()+dFormat.format(d)); //RandomStringUtils.randomAlphabetic(8)); //result.getName()
		}
		else if(ITestResult.SUCCESS==result.getStatus())
		{
			CreateLoginAccount c = new CreateLoginAccount(driver);
			c.screenShot(driver, "Passed"+result.getName()); //RandomStringUtils.randomAlphabetic(8)); //result.getName()
		}
		else if(ITestResult.SKIP==result.getStatus())
		{
			CreateLoginAccount c = new CreateLoginAccount(driver);
			c.screenShot(driver, "Skipped"+result.getName()); //RandomStringUtils.randomAlphabetic(8)); //result.getName()
		} 
	}*/

	@BeforeTest
	public void setUp() throws Exception
	{
		browserLaunch(getData("browser"), getData("url"));
		Thread.sleep(3000);

	}

	@AfterTest(enabled=false)
	public void killProcess()
	{
		CreateLoginAccount c = new CreateLoginAccount(driver);
		c.logout();
		driver.quit();
	}

}
