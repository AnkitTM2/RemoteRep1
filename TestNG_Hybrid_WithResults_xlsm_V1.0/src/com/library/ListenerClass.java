package com.library;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;
import com.relevantcodes.extentreports.ExtentReports;

public class ListenerClass extends GlobalVariable implements IAnnotationTransformer,ITestListener
{
	@SuppressWarnings("rawtypes")
	@Override
	public void transform(ITestAnnotation iAno, Class testClass, Constructor testConstructor, Method method) 
	{
		String TestCaseName = method.getName();
		boolean TestCaseExecutionStatus = ExcelLibrary.getExecuteStatus(TestCaseName);
		if(TestCaseExecutionStatus == true)
		{
			int priority = ExcelLibrary.getPriority(TestCaseName);
			String Description = ExcelLibrary.getDescription(TestCaseName);
			
			iAno.setEnabled(true);
			iAno.setPriority(priority);
			iAno.setDescription(Description);
		}
		else
		{
			iAno.setEnabled(false);
		}
	}
	
	@Override
	public void onStart(ITestContext context) 
	{
		System.out.println("Execution Start");
		CurrentResultsFolder = WebLibrary.createresultsfolder();
		ScreenshotsFolderPath = CurrentResultsFolder + "\\Screenshots";
		ResulthtmlPath = CurrentResultsFolder + "\\Summary.html";
		//Create report
		report = new ExtentReports(ResulthtmlPath, true);
	}

	@Override
	public void onTestStart(ITestResult result) 
	{
		System.out.println("");
		System.out.println("");
		System.out.println("Start: " + result.getMethod().getMethodName());
		TestReport = report.startTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		System.out.println("End: " + result.getMethod().getMethodName());
		report.endTest(TestReport);
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		System.out.println("End: " + result.getMethod().getMethodName());
		report.endTest(TestReport);
	}

	@Override
	public void onTestSkipped(ITestResult result) 
	{
		System.out.println("End: " + result.getMethod().getMethodName());
		report.endTest(TestReport);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{
		System.out.println("End: " + result.getMethod().getMethodName());
		report.endTest(TestReport);
	}



	@Override
	public void onFinish(ITestContext context) 
	{
		System.out.println("");
		System.out.println("");
		System.out.println("Execution Finish");
		System.out.println("");
		System.out.println("");
		report.close();	
		ZipFileCode.ZipFolder();
	}
}
