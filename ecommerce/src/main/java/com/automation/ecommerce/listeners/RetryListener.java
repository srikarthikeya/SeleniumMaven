/**
 * @author KarthikeyaSri
 */
package com.automation.ecommerce.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

public class RetryListener implements IAnnotationTransformer
{

	/* (non-Javadoc)
	 * @see org.testng.IAnnotationTransformer#transform(org.testng.annotations.ITestAnnotation, java.lang.Class, java.lang.reflect.Constructor, java.lang.reflect.Method)
	 */
	public void transform(ITestAnnotation arg0, Class arg1, Constructor arg2, Method arg3)
	{
		IRetryAnalyzer r = arg0.getRetryAnalyzer();
		
		if(r==null) //if a number is given it'll execute only PASS/FAIL/SKIP multiple time and this number represents Test status
		{
			arg0.setRetryAnalyzer(Retry.class); //here Retry is a user defined class & code to retry the test multiple times is written there
		}
	}

}
