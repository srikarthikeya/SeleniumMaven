/**
 * @author KarthikeyaSri
 */
package com.automation.ecommerce.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

public class Retry implements IRetryAnalyzer
{
	private int retryCount = 0;
	private int maxretryCount = 2;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.IRetryAnalyzer#retry(org.testng.ITestResult)
	 */
	public boolean retry(ITestResult arg0)
	{
		if (retryCount < maxretryCount)
		{
			log("Retrying Test " + " "+arg0.getName() +" "+ " " + " with status " + " " + getResultStatusName(arg0.getStatus())
					+" "+ "for " + " "+(retryCount + 1) + "time(s)");

			retryCount++;
			return true;
		}
		return false;
	}

	/**
	 * @param string
	 */
	private void log(String string)
	{
		Reporter.log(string);
	}

	/**
	 * @param status
	 * @return resultName
	 */
	private String getResultStatusName(int status)
	{
		String resultName = null;
		
		if (status == 1)
			resultName = " SUCCESS ";
		if (status == 2)
			resultName = " FAILED ";
		if (status == 3)
			resultName = " SKIPPED ";

		return resultName;
	}

}
