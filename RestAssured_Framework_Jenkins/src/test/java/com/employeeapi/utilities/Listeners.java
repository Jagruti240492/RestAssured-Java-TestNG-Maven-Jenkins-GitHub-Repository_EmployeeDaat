package com.employeeapi.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter 
{
	public ExtentSparkReporter reporter;
	public ExtentReports extentReports;
	public ExtentTest test;
	
    public void onStart(ITestContext testContext)
    {
      reporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/extent-report.html");          
      reporter.config().setDocumentTitle("RestAPI Automation report");
      reporter.config().setReportName("RestAPI Testing Report");
      reporter.config().setTheme(Theme.DARK);
      	
	  extentReports = new ExtentReports(); 
	  extentReports.attachReporter(reporter);
	  extentReports.setSystemInfo("Host name", "localhost");
	  extentReports.setSystemInfo("Environment", "QA");
	  extentReports.setSystemInfo("user", "jagrutishevalkar");    
   }
    
    public void onTestSuccess(ITestResult result)
    {
    	test = extentReports.createTest(result.getName());
    	test.log(Status.PASS, "TestCase Passed ===> "+result.getName());
    }
    
    public void onTestFailure(ITestResult result)
    {
    	test = extentReports.createTest(result.getName());
    	test.log(Status.FAIL, "TestCase Failed ===> "+result.getName());
    	test.log(Status.FAIL, "TestCase Failed ===> "+result.getThrowable());
    }
    
    public void onTestSkipped(ITestResult result)
    {
    	test = extentReports.createTest(result.getName());
    	test.log(Status.SKIP, "TestCase Skipped ===> "+result.getName());
    }
    
    public void onFinish(ITestContext testContext)
    {
    	extentReports.flush();
    }
}

