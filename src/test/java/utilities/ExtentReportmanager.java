package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.baseClass;

public class ExtentReportmanager implements ITestListener
{
	public ExtentSparkReporter sparkReporter; // used for UI
	public ExtentReports extent; //used for common information
	public ExtentTest test; // Creating test entries and update the status like pass failed in report
	String repName;
	

	
	public void onStart(ITestContext testContext) {
		
		
		//Create a timestamp for report name
		String timeStamp = new SimpleDateFormat("yyyy.mm.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report- " + timeStamp + ".html";
		
		
		// Create a folder and give the path to stored the report
		sparkReporter = new ExtentSparkReporter(".\\Reports\\" + repName); 
		
		sparkReporter.config().setDocumentTitle("OpenCart Automation Report"); // Give the document title
		sparkReporter.config().setReportName("OpenCart Funcational Test"); //Give the report name
		sparkReporter.config().setTheme(Theme.DARK); // Set the theme
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Application Name", "OpenCart");
		extent.setSystemInfo("Computer Name", "localhost");
		extent.setSystemInfo("Enviroment", "QA");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List <String> includeGroups= testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includeGroups.isEmpty())
		{
			extent.setSystemInfo("Groups", includeGroups.toString());
		}
		
	  }
	
	public void onTestSuccess(ITestResult result) 
	{
		test =extent.createTest(result.getTestClass().getName()); // create new entry in test report
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() + "Test Case is Pass succssfully");
	}

	public void onTestFailure(ITestResult result) 
	{
		test =extent.createTest(result.getTestClass().getName()); // create new entry in test report
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName() + "Test Case got Failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
		String imgpath = new baseClass().captureScreenShot(result.getName());
		test.addScreenCaptureFromPath(imgpath);
		}
		catch (IOException e1)
		{
			e1.printStackTrace(); 
		}
		
	}

	public void onTestSkipped(ITestResult result) 
	{
		test =extent.createTest(result.getClass().getName()); // create new entry in test report
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + "Test Case is Skiped");
		test.log(Status.INFO, result.getThrowable().getMessage());
  	}

	public void onFinish(ITestContext context) 
	{
		extent.flush();
		
		String pathofExtentReport = System.getProperty("user.dir") + "\\Reports\\" + repName;
		File extentReport = new File(pathofExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}
		catch (IOException e) 
		{
			e.getStackTrace(); 	 	
		}
	}


}
