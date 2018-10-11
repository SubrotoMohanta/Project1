package Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.itextpdf.text.DocumentException;

import Utilities.exceptionReport;

public class testFlightPageLoad {

	@Test
	public void clearTripFlightPage() {

		exceptionReport ExceptionReport = new exceptionReport();

		try {

			clearTripFlightsPage ClearTripFlightsPage = new clearTripFlightsPage();

			driverFunctions.testCaseName = ClearTripFlightsPage.getClass().getName();

			ClearTripFlightsPage.load();

			ClearTripFlightsPage.searchFlights();

		} catch (Exception e) {

			ExceptionReport.getLogAndReport(e);
			driverFunctions.driverQuit();
		} 
	}

	@AfterTest
	public void screenShotToPDF() throws MalformedURLException, DocumentException, IOException {
		driverFunctions.screenshotToPdf();
		driverFunctions.driverQuit();

	}

}
