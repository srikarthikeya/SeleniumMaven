/**
 * @author KarthikeyaSri
 */
package com.automation.ecommerce.basePage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.automation.ecommerce.listeners.Listener;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class BasePage
{
	public static WebDriver driver;
	public static final String path = "./configure.properties";
	public String log4jPath = "./log4j.properties";
	
	public ExtentReports extent;
	public ExtentTest logger;
	public ITestResult result;
	public ExtentHtmlReporter htmlReporter;
	
	/**
	 * @param String Key value from properties file
	 */
	public String getData(String key) throws Exception
	{
		File f = new File(path);
		FileInputStream fis = new FileInputStream(f);
		Properties p = new Properties();
		p.load(fis);
		return p.getProperty(key);
	}

	/**
	 * @param WebElement
	 * @param int
	 */
	public void selectOption(WebElement element, int option)
	{
		Select s = new Select(element);
		s.selectByIndex(option);
	}

	/**
	 * @param time
	 * @param WebElement
	 */
	public void elementVisible(int time, WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public int randomNumber()
	{
		Random r = new Random();
		int randomInt = r.nextInt(999);
		return randomInt;
	}

	public void browserLaunch(String browser, String url)
	{
		if (browser.equalsIgnoreCase("firefox"))
		{
			//System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//drivers//geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("chrome"))
		{
			//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir" + "//drivers//chromedriver.exe"));
			System.setProperty("webdriver.chrome.driver", "G:\\Karthik\\Selenium\\Library\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("ie"))
		{
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir" + "//drivers//IEDriverServer.exe"));
			driver = new InternetExplorerDriver();
		}
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void startReport(Method result)
	{
		
		Date d=new Date();
        String newD=d.toString().replace(":", "_").replace(" ", "_")+".html";
        System.out.println(System.getProperty("user.dir")+newD);

        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/src/main/java/com/automation/ecommerce/htmlReports/"+newD);
		extent = new ExtentReports ();
		extent.attachReporter(htmlReporter);
        htmlReporter.config().setChartVisibilityOnOpen(true);
		extent.setSystemInfo("Host Name", "LocalHost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Author", "KarthikeyaSri");
		
		//extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));

		
		htmlReporter.config().setDocumentTitle("Automation Practice Page Test");
		htmlReporter.config().setReportName("Extent Report of AutomationPractice Page");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
		
		logger=extent.createTest(result.getName());
		logger.log(Status.INFO, result.getName()+"Test has started !");
	}
	
	@AfterMethod
	public void endReport(ITestResult result) throws Exception
	{
		getResult(result);
	}
	
	public void checkTitle(WebDriver driver, String expectedTitle)
	{
        if(driver.getTitle().equals(expectedTitle))
        {
            logger.log(Status.PASS, "Check page title - Page title is " + expectedTitle);
        }
        else
        {
            logger.log(Status.FAIL, "Check page title - Incorrect login page title " + driver.getTitle());
        }
    }
	
/*	public void checkErrorMessage(WebDriver driver, String expectedMessage) {
		 
        String errorMessage = driver.findElement(By.className("error")).getText();
 
        if(errorMessage.equals(expectedMessage)) {
            logger.log(Status.PASS, "Check error message - Error message is " + expectedMessage);
        } else {
            logger.log(Status.FAIL, "Check error message - View details below:"+createScreenshot(driver));
        }
    }*/
	
	/**
	 * @param result2
	 * @throws IOException 
	 */
	public void getResult(ITestResult result2) throws IOException
	{
		if(result2.getStatus()==ITestResult.SUCCESS)
		{
			//logger.log(Status.PASS, result2.getName()+" "+"Test is Success !");
			//MarkupHelper is used to display the output in different colors
			logger.log(Status.PASS, MarkupHelper.createLabel(result2.getName() + " - Test Case Passed", ExtentColor.GREEN));
			logger.log(Status.PASS, MarkupHelper.createLabel(result2.getThrowable() + " - Test Case Passed", ExtentColor.GREEN));
			
			//To capture screenshot path and store the path of the screenshot in the string "screenshotPath"
            //We do pass the path captured by this method in to the extent reports using "logger.addScreenCaptureFromPath" method. 			
            
            String screenShotPath = GetScreenShotPass.capture(driver, "screenShotName");
			logger.log(Status.PASS, "Snapshot below: "+ logger.addScreenCaptureFromPath(screenShotPath)); //screenShotPath
		}
		else if(result2.getStatus()==ITestResult.FAILURE)
		{
			//logger.log(Status.FAIL, result2.getName()+" "+"Test is a Failure & reason is :  "+result2.getThrowable());
			//MarkupHelper is used to display the output in different colors
			logger.log(Status.FAIL, MarkupHelper.createLabel(result2.getName() + " - Test Case Failed", ExtentColor.RED));
			logger.log(Status.FAIL, MarkupHelper.createLabel(result2.getThrowable() + " - Test Case Failed", ExtentColor.RED));

			String screenShotPath = GetScreenShotFail.capture(driver, "screenShotName");
			logger.log(Status.FAIL, "Snapshot below: "+ logger.addScreenCaptureFromPath(screenShotPath)); //screenShotPath
		
		}
		else if(result2.getStatus()==ITestResult.SKIP)
		{
			//logger.log(Status.SKIP, result2.getName()+" "+"Test is Skipped & reason is :  "+result2.getThrowable());
			//MarkupHelper is used to display the output in different colors
			logger.log(Status.SKIP, MarkupHelper.createLabel(result2.getName() + " - Test Case Skipped", ExtentColor.BLUE));
			logger.log(Status.SKIP, MarkupHelper.createLabel(result2.getThrowable() + " - Test Case Skipped", ExtentColor.BLUE));
		}
	}
	public static class GetScreenShotFail {
	     
	    public static String capture(WebDriver driver,String screenShotName) throws IOException
	    {
	        TakesScreenshot ts = (TakesScreenshot)driver;
	        File source = ts.getScreenshotAs(OutputType.FILE);
	        String dest = System.getProperty("user.dir")+"/src/main/java/com/automation/ecommerce/failedScreens/"+screenShotName+".png";
	        File destination = new File(dest);
	        FileUtils.copyFile(source, destination);        
	                     
	        return dest;
	    }
	}
	
	public static class GetScreenShotPass {
	     
	    public static String capture(WebDriver driver,String screenShotName) throws IOException
	    {
	        TakesScreenshot ts = (TakesScreenshot)driver;
	        File source = ts.getScreenshotAs(OutputType.FILE);
	        String dest = System.getProperty("user.dir")+"/src/main/java/com/automation/ecommerce/passedScreens/"+screenShotName+".png";
	        File destination = new File(dest);
	        FileUtils.copyFile(source, destination);        
	                     
	        return dest;
	    }
	}
	
	@AfterClass
	public void endTest()
	{
		closeBrowser();
	}

	public void closeBrowser()
	{
		//driver.quit();
		//extent.endTest(logger);
		extent.flush();
	}
}
