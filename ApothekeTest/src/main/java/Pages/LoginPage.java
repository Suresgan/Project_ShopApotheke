package Pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class LoginPage {


	WebDriver driver;
	ElementWait waitTime ;
	ExtentHtmlReporter reporter;
	ExtentReports extent; 
	ExtentTest logger;
    
	//Constructor that will be automatically called as soon as the object of the class is created
	public LoginPage(WebDriver driver, ExtentTest logger ) {
		this.driver=driver;
		waitTime = new ElementWait(driver);
		 reporter=new ExtentHtmlReporter("./Reports/TestCases.html");
		 extent = new ExtentReports();
		 this.logger = logger;
	}

	//Locator for eMail field
	By eMail = By.id("loginForm-eMail");

	//Locator for password field
	By password = By.id("loginForm-password");

	//Locator for submit Button 
	By submitButton = By.id("login-submit-btn");

	//Locator for frc Button 
	By frcButton = By.xpath("//button[@class='frc-button']");

	//Locator for frc Button 
	By errorMessageContainer = By.xpath("//div[@class='l-flex__primary u-no-margin u-padding--ends m-Notification__message']");

	



	/***Methods*****/

	//Method to Type email
	public void enterEmail(String email) {
		driver.findElement(eMail).sendKeys(email);
		if(driver.findElement(eMail).getAttribute("value").equalsIgnoreCase(email)) {
			logger.log(Status.PASS, "Entered email address in email field");
		}else {
			logger.log(Status.FAIL, "Entered email address in email field");
		}
		
	}

	//Method to Type password
	public void enterPassword(String pwd) {
		driver.findElement(password).sendKeys(pwd);
			logger.log(Status.PASS, "Entered Password in email field");
	}

	
	//Method to click frc button
	public void clickFrcButton() {
		try {
			waitTime.waitForVisibility(driver.findElement(frcButton)).click();
			logger.log(Status.PASS, "Clicked on Anti robot button");
		} catch (Exception e){
			logger.log(Status.FAIL, "Clicked on Anti robot button");
		}
		
	}


	//Method to click submit button
	public void clickSubmitButton() throws IOException {
			try{
				waitTime.elementToBeClickable(driver.findElement(submitButton)).click();
				logger.log(Status.PASS, "Clicked on submit button");
			} catch(Exception e) {
				logger.log(Status.FAIL, "Clicked on submit buttonn");
				 
			}
	}

	
	//Method to get the error message
	public String getErrorMessage() throws IOException {
		try {
			return waitTime.waitForVisibility(driver.findElement(errorMessageContainer)).getText();
		} catch(Exception e) {
			System.out.println(e.getStackTrace());
			return "null";
		}
		
	}	
	

	public void acceptConsent() {
		WebElement root1 = driver.findElement(By.id("usercentrics-root"));
		//Get shadow root element
		WebElement shadowRoot1 = getShadowRootElement(root1);

		List<WebElement> elementName = shadowRoot1.findElements(By.tagName("button"));
		for(WebElement Em:elementName) {
			if(Em.getAttribute("class").contentEquals("sc-gsDKAQ dZIwB")) {
				Em.click();
				logger.log(Status.PASS, "Accepted consent popup");
			}
		}
	}


	public WebElement getShadowRootElement(WebElement element) {

		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		SearchContext shadowRoot = (SearchContext)javascriptExecutor.executeScript("return arguments[0].shadowRoot", element);
		WebElement shadowContent = shadowRoot.findElement(By.cssSelector("div#focus-lock-id")); 
		return shadowContent;
	}	



}
