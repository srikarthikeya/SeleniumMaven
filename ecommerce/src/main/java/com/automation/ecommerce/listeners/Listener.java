/**
 * @author KarthikeyaSri
 */
package com.automation.ecommerce.listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.automation.ecommerce.basePage.BasePage;

public class Listener extends BasePage implements ITestListener
{
	
	public File sourcePath;
	public String reportDirectory ;
	public File targetPath;
	
	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onFinish(org.testng.ITestContext)
	 */
	public void onFinish(ITestResult arg0)
	{
		Reporter.log("Test Finished"+arg0.getMethod().getMethodName());
		
	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onStart(org.testng.ITestContext)
	 */
	public void onStart(ITestResult arg0)
	{
		Reporter.log("Test started & running"+arg0.getMethod().getMethodName());
	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onTestFailedButWithinSuccessPercentage(org.testng.ITestResult)
	 */
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0)
	{
		Reporter.log("Test Percentage"+arg0.getMethod().getMethodName());
		
	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onTestFailure(org.testng.ITestResult)
	 */
	public void onTestFailure(ITestResult arg0)
	{
		Reporter.log("Test Failure : "+arg0.getMethod().getMethodName());
		//Calendar cal = Calendar.getInstance();
		SimpleDateFormat simpleDate = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa"); //DD_MM_YYYY_hh_mm_ss");
		String methodName = arg0.getName();
		
		if(!(arg0.isSuccess()))
		{
			File sourcePath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String reportDirectory = (new File(System.getProperty("user.dir")).getAbsolutePath()
					+"/src/main/java/com/automation/ecommerce/");
			File targetPath = new File((String)reportDirectory+"/failedScreens/"+" "+methodName+" "
					+simpleDate.format(new Date())+".png");
			
			try
			{
				FileUtils.copyFile(sourcePath, targetPath);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			Reporter.log("<a href=' "+targetPath.getAbsolutePath()+"'>"+ "<img src=' "
					+targetPath.getAbsolutePath()+" 'height='100'  width='100'/></a>");
		}
		
	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onTestSkipped(org.testng.ITestResult)
	 */
	public void onTestSkipped(ITestResult arg0)
	{
		Reporter.log("Test Skipped : "+arg0.getMethod().getMethodName());
		
	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onTestStart(org.testng.ITestResult)
	 */
	public void onTestStart(ITestResult arg0)
	{
		Reporter.log("Test started & running : "+arg0.getMethod().getMethodName());
	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onTestSuccess(org.testng.ITestResult)
	 */
	public void onTestSuccess(ITestResult arg0)
	{
		Reporter.log("Test Success : "+arg0.getMethod().getMethodName());
		//Calendar cal = Calendar.getInstance();
		//Calendar cal = new GregorianCalendar();
		SimpleDateFormat simpleDate = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa"); //DD_MM_YYYY_hh_mm_ss");
		//simpleDate.setTimeZone(cal.getTimeZone());
		String methodName = arg0.getName();
		
		if(arg0.isSuccess())
		{
			File sourcePath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String reportDirectory = (new File(System.getProperty("user.dir")).getAbsolutePath()
					+"/src/main/java/com/automation/ecommerce/");
			File targetPath = new File((String)reportDirectory+"/passedScreens/"+" "+methodName+" "
					+simpleDate.format(new Date())+".png"); //format(cal.getTime())
			
			
			try
			{
				FileUtils.copyFile(sourcePath, targetPath);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			Reporter.log("<a href=' "+targetPath.getAbsolutePath()+"'>"+ "<img src=' "+targetPath.getAbsolutePath()+" 'height='100'  width='100'/></a>");
		}
		
	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onFinish(org.testng.ITestContext)
	 */
	public void onFinish(ITestContext arg0)
	{
	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onStart(org.testng.ITestContext)
	 */
	public void onStart(ITestContext arg0)
	{
	}

}
