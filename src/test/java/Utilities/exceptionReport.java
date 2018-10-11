package Utilities;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class exceptionReport {

	public static ExtentReports Exceptionreports = new ExtentReports(
			"Reports\\ExtentReports\\ExceptionExtentReport.html", true);
	public static ExtentTest Exceptiontest;

	public exceptionReport() {

		Exceptionreports.loadConfig(new File("src\\test\\resources\\extent-config.xml"));

	}

	public void getLogAndReport(Exception e) {
		Exceptiontest = Exceptionreports.startTest("Exception Report");

		Exceptiontest.log(LogStatus.FAIL, e);
		
		Exceptionreports.endTest(Exceptiontest);
		Exceptionreports.flush();

	}

}
