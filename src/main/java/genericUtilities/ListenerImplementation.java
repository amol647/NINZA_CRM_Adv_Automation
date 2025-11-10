package genericUtilities;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplementation implements ITestListener, ISuiteListener {

	
	public ExtentReports report;
	public ExtentTest test;
	
	@Override
	public void onStart(ISuite suite) {
		
		System.out.println("Executes befor @BeforeSuite annotation");
		System.out.println("Report Configuration");
		System.out.println("Report needs to be Configured before the Execution");
		
		JavaUtility jLib = new JavaUtility();
		
		//Add ExtentSparkReporter dependency from AventStack
		
		//Configure the Report
		//The folder will be created Automatically
		ExtentSparkReporter spark = new ExtentSparkReporter("./ExtentReports/report"+jLib.getCurrentDateAndTime()+".html");
		
		spark.config().setDocumentTitle("CRM Reports");
		spark.config().setReportName("NINZA_CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		
		//Until the Object of ExtentReports is created the report will not be generated
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows");
		report.setSystemInfo("Browser", "Chrome");
		report.setSystemInfo("Environment", "Test2");
		
	}

	@Override
	public void onFinish(ISuite suite) {
		
		System.out.println("Report BackUp");
		
		report.flush();
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testCaseName = result.getMethod().getMethodName();
		System.out.println(testCaseName+"TestCase Execution Started");
		
		//get control of the test case
		test = report.createTest(testCaseName);
		
		test.log(Status.INFO, testCaseName+"Execution Started");
		
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testCaseName = result.getMethod().getMethodName();

		test.log(Status.PASS, testCaseName+"TestCase Execution PASSED");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		String testCaseName = result.getMethod().getMethodName();
		
		test.log(Status.FAIL, testCaseName+"TestCase Execution Failed");
		
		JavaUtility jLib = new JavaUtility();
		TakesScreenshot ts = (TakesScreenshot) BaseClass.sdriver;
		String src = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(src);
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testCaseName = result.getMethod().getMethodName();

		test.log(Status.SKIP, testCaseName+"TestCase Execution SKIPPED");
	}

	
}
