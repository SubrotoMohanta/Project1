package Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import Utilities.exceptionReport;

public class driverFunctions {

	public static WebDriver driver;

	public static String testCaseName;

	public static int screenShotCounter = 0;

	public static Logger logger = Logger.getLogger(testFlightPageLoad.class);

	static exceptionReport ExceptionReport = new exceptionReport();

	public driverFunctions() {

		try {
			File file = new File("src//test//resources//PropertyFiles//Driver.properties");

			FileInputStream fis = new FileInputStream(file);

			Properties browser = new Properties();

			browser.load(fis);

			if (browser.getProperty("browser").equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "src//test//resources//Drivers//chromedriver.exe");
				this.driver = new ChromeDriver();

				logger.debug("Chrome initiliased as driver");

			}
		} catch (Exception e) {
			ExceptionReport.getLogAndReport(e);
		}

	}

	public static void driverQuit() {

		logger.debug("Closing the driver");

		driver.quit();
	}

	public void sendText(WebElement element, String values) {

		logger.debug("Sending value " + values);
		element.sendKeys(values);

	}

	public void explicitWait(WebElement element, int time) {

		WebDriverWait wait = new WebDriverWait(driver, time);

		logger.debug("Waiting for time" + time);

		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void explicitWaitClickable(WebElement element, int time) {

		WebDriverWait wait = new WebDriverWait(driver, time);

		logger.debug("Waiting for time" + time);

		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void screenShot(String path) {

		try {
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			FileUtils.copyFile(src, new File(path));
		} catch (Exception e) {
			ExceptionReport.getLogAndReport(e);
		}

	}

	public static void testScreenShot() {

		try {
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			FileUtils.copyFile(src, new File("src\\test\\resources\\testName\\TestCaseScreenshots" + "\\" + testCaseName
					+ "\\" + testCaseName + screenShotCounter++ + ".png"));

			System.out.println("Screenshot taken");
		} catch (Exception e) {
			ExceptionReport.getLogAndReport(e);
		}

	}

	public static void screenshotToPdf() {

		try {
			ArrayList<String> IMAGES = new ArrayList<String>();

			for (int i = 0; i < screenShotCounter; i++) {

				IMAGES.add("src\\test\\resources\\testName\\TestCaseScreenshots" + "\\" + testCaseName + "\\"
						+ testCaseName + i + ".png");

			}

			Image img = Image.getInstance(IMAGES.get(0));

			String output = "src\\test\\resources\\testName\\TestCaseScreenshots" + "\\" + testCaseName + "\\"
					+ testCaseName + ".pdf";

			Document document = new Document(img);
			PdfWriter.getInstance(document, new FileOutputStream(output));

			document.open();

			String image;

			for (int i = 0; i < IMAGES.size(); i++) {
				image = IMAGES.get(i);
				img = Image.getInstance(image);
				document.setPageSize(img);
				document.newPage();
				img.setAbsolutePosition(0, 0);
				document.add(img);
			}
			document.close();
		} catch (Exception e) {
			ExceptionReport.getLogAndReport(e);
		}

	}
}
