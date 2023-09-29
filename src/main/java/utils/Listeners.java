
package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import core.BaseClass;

public class Listeners extends BaseClass implements ITestListener{

	
	private static final String OUTPUT_FOLDER = "./ExtentReport/";
	private static final String FILE_NAME = "API_Test_Execution_Report.html";
	
	private static ExtentReports extent = init();
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	
	private static ExtentReports init() {
		Path path = Paths.get(OUTPUT_FOLDER);
		
		if(!Files.exists(path)) {
		try {
			Files.createDirectories(path);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		}
		
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter (OUTPUT_FOLDER +FILE_NAME );
		
		sparkReporter.config().setDocumentTitle("Test Result");
		sparkReporter.config().setReportName("API Automation Test Result");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		return extent;
	}
	
	@Override
	public void onTestStart(ITestResult result) {
	
	String methodName = result.getMethod().getMethodName();
	
	System.out.println(methodName +"started!");
	logger.info(result.getMethod().getMethodName());
	
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		logger.info("test passed successfully");
		logger.info("method name: " + result.getMethod().getMethodName());
		
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
		
				result.getMethod().getDescription());
		
		extentTest.log(Status.PASS, "test passed");

	
	}

	@Override
	public void onTestFailure(ITestResult result) {
		logger.info("test failed");
		logger.info("method name : " + result.getMethod().getMethodName());
		

		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
		
		result.getMethod().getDescription());
		extentTest.log(Status.FAIL, "test failed");
	//	extentTest.assignAuthor("Sayed Sadat");
	//	extentTest.assignCategory("smoke test");
	//	extentTest.assignCategory("regression");
		//test.get().info("test failed");
	//	test.get().getModel().setEndTime(getTime(result.getEndMillis()));
		//DriverUtility.screenShot();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + " skipped!");
		logger.info("method name" + result.getMethod().getMethodName());
		
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
				result.getMethod().getDescription());
				extentTest.log(Status.SKIP, "test skipped");
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	logger.info("method name " + result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		logger.info("method name " + result.getMethod().getMethodName());
		
	}

	@Override
	public void onStart(ITestContext context) {
		logger.info("test execution started" + context);
		
	}

	@Override
	public void onFinish(ITestContext context) {
		logger.info(" end of execution" + context);
		
		extent.flush();
		
		test.remove();
	}
	
	private Date getTime(long milli) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milli);
		return calendar.getTime();
	}


	}



