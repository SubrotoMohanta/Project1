package Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;

import Utilities.exceptionReport;

public class clearTripFlightsPage extends driverFunctions {

	@FindBy(id = "FromTag")
	WebElement fromFlight;

	@FindBy(id = "ToTag")
	WebElement ToFlight;

	@FindBy(id = "OneWay")
	WebElement OneWay;

	@FindBy(id = "DepartDate")
	WebElement DepartDate;

	@FindBy(id = "Adults")
	WebElement Adults;

	@FindBy(id = "Childrens")
	WebElement Childrens;

	@FindBy(id = "Infants")
	WebElement Infants;

	@FindBy(id = "SearchBtn")
	WebElement SearchBtn;

	exceptionReport ExceptionReport = new exceptionReport();

	clearTripFlightsPage() throws IOException {

		PageFactory.initElements(driver, this);

	}

	public void load() {

		try {
			File file = new File("src//test//resources//PropertyFiles//URL.properties");

			FileInputStream fis = new FileInputStream(file);

			Properties url = new Properties();

			url.load(fis);

			driver.get(url.getProperty("flightPage"));

			driver.getTitle().equalsIgnoreCase("Site for Booking Flights, Hotels, Packages, Trains & Local activities");

			driver.manage().window().maximize();

			testScreenShot();

			Thread.sleep(3000);
		} catch (Exception e) {
			ExceptionReport.getLogAndReport(e);
		}

	}

	public void searchFlights() throws InterruptedException {

		try {
			File file = new File("src//test//resources//PropertyFiles//SearchFlights.properties");

			FileInputStream fis = new FileInputStream(file);

			Properties searchFlightsProperty = new Properties();

			searchFlightsProperty.load(fis);

			// if
			// (searchFlightsProperty.getProperty("Trip").equalsIgnoreCase("OneWay"))
			// {

			if (OneWay.isSelected()) {

				sendText(fromFlight, searchFlightsProperty.getProperty("From"));

				sendText(ToFlight, searchFlightsProperty.getProperty("To"));

				Thread.sleep(5000);

				sendText(DepartDate, searchFlightsProperty.getProperty("Departon"));

				sendText(Adults, searchFlightsProperty.getProperty("Adults"));

				sendText(Childrens, searchFlightsProperty.getProperty("Children"));

				sendText(Infants, searchFlightsProperty.getProperty("Infants"));

				testScreenShot();
				
				explicitWaitClickable(SearchBtn, 5);

				SearchBtn.click();

				testScreenShot();

			}

			// }
		} catch (Exception e) {
			ExceptionReport.getLogAndReport(e);
		} 

	}

}
