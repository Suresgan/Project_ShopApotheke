package testCases;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Pages.ElementWait;
import Pages.LoginPage;
import Pages.OverView;
import Pages.SeleniumUtil;

public class ApothekeLogin {

	static WebDriver driver;
	LoginPage login;
	OverView overViewPage;
	SeleniumUtil seleniumUtils ;
	ExtentHtmlReporter reporter;
	ExtentReports extent; 
	ExtentTest  logger;
	
	@BeforeClass
	public void loadClass() {
		 reporter=new ExtentHtmlReporter("./Reports/TestCases.html");
		 extent = new ExtentReports();
		 extent.attachReporter(reporter);
		 logger = extent.createTest("loginSuccess");
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

	
	
	@Test (priority = 1)
	public void loginSuccess() throws InterruptedException, IOException {
		SoftAssert softAssert = new SoftAssert();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		driver.get("https://www.shop-apotheke.com/nx/login/");
		
		Reporter.log("Browser Opened and url lanuched");
		seleniumUtils.reportLog(logger, "Browser Opened and url lanuched", seleniumUtils.getCurrentPageTitleAndCompare("Anmelden"));
		
		Thread.sleep(5000);
		
		login.acceptConsent();
		Reporter.log("Accepted consent popup");
		
		
		login.enterEmail("suresgan@gmail.com");
		Reporter.log("Entered email address in email field");
		
		login.enterPassword("QazWsx@22");
		Reporter.log("Entered password in password field");
		
		login.clickFrcButton();
		Reporter.log("Clicked on Anti robot button");
		
		login.clickSubmitButton();
		Reporter.log("Clicked on submit button");
		softAssert.assertTrue(overViewPage.getCustmerName().equalsIgnoreCase("Suresh Ganesan"), "Verify the customer Name after login");
		extent.flush();
		softAssert.assertAll();
		
	}
	
	
	
	@AfterMethod
	public void closeBrowser() {
		// driver.quit();
	}

}
