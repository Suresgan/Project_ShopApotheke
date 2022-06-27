package testCases;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Pages.LoginPage;
import Pages.OverView;
import Pages.SeleniumUtil;

public class ApothekeInvalidLogin {
	

	static WebDriver driver;
	LoginPage login;
	OverView overViewPage;
	SeleniumUtil seleniumUtils ;
	ExtentHtmlReporter reporter;
	ExtentReports extent; 
	ExtentTest  logger;
	SoftAssert softAssert;
	
	@BeforeClass
	public void loadClass() {
		 reporter=new ExtentHtmlReporter("./Reports/TestCases2.html");
		 extent = new ExtentReports();
		 extent.attachReporter(reporter);
		 logger = extent.createTest("InvalidLogin");
	}
	
	@BeforeMethod
	public void launchBrowser() {
		
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir")+"\\src\\main\\resources\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking" ,"enable-automation"));
		driver = new ChromeDriver(options);
		overViewPage = new OverView(driver);
		login = new LoginPage(driver, logger);
		seleniumUtils = new SeleniumUtil(driver);
		driver.manage().window().maximize();
	}

	
	
	@Test
	public void invalidLogin() throws InterruptedException, IOException {
		softAssert = new SoftAssert();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		driver.get("https://www.shop-apotheke.com/nx/login/");
		
		Reporter.log("Browser Opened and url lanuched");
		seleniumUtils.reportLog(logger, "Browser Opened and url lanuched", seleniumUtils.getCurrentPageTitleAndCompare("Anmelden"));
		
		Thread.sleep(5000);
		
		login.acceptConsent();
		Reporter.log("Accepted consent popup");
		
		
		login.enterEmail("1234@gmail.com");
		Reporter.log("Entered email address in email field");
		
		login.enterPassword("QazWsx@22");
		Reporter.log("Entered password in password field");
		
		login.clickFrcButton();
		Reporter.log("Clicked on Anti robot button");
		
		login.clickSubmitButton();
		Reporter.log("Clicked on submit button");
		softAssert.assertTrue(login.getErrorMessage().contentEquals("E-Mail-Adresse und/oder Passwort sind falsch. Bitte überprüfen Sie Ihre Eingaben."), "Verify the error message");
		extent.flush();
		softAssert.assertAll();
		
		
	}
	

	@AfterMethod
	public void closeBrowser() {
		 driver.quit();
	}
	

}
