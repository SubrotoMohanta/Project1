package Listeners;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Test.driverFunctions;

public class Listener implements ITestListener {

	protected static ExtentReports reports;
	protected static ExtentTest test;

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

		reports = new ExtentReports("Reports\\ExtentReports\\MyExtentReport.html", true);
		reports.loadConfig(new File("src\\test\\resources\\extent-config.xml"));
		test = reports.startTest(result.getMethod().getMethodName());

		test.log(LogStatus.INFO, result.getMethod().getMethodName() + "test is started");

	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

		test.log(LogStatus.PASS, result.getMethod().getMethodName() + "test is passed");

	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub

		test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed");

		driverFunctions
				.screenShot("src\\test\\resources\\Screenshots\\" + result.getMethod().getMethodName() + ".png");

		String file = test.addScreenCapture(System.getProperty("user.dir") + "\\src\\test\\resources\\Screenshots\\"
				+ result.getMethod().getMethodName() + ".png");

		System.out.println("The file path is " + file);

		test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed", file);
		test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed",
				result.getThrowable().getMessage());

	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(LogStatus.SKIP, result.getMethod().getMethodName() + "test is skipped");

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

		reports = new ExtentReports(new SimpleDateFormat("yyyy-MM-dd hh-mm-ss-ms").format(new Date()) + "reports.html");

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

		driverFunctions.driverQuit();
		reports.endTest(test);
		reports.flush();

	}

}
