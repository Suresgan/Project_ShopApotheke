package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;



public class SeleniumUtil {
	
	
	WebDriver driver;
	ElementWait waitTime ;
	
	
	//Constructor that will be automatically called as soon as the object of the class is created
	public SeleniumUtil(WebDriver driver) {
		this.driver=driver;
		waitTime = new ElementWait(driver);
	}
	
	
	
	//Method to compare the String 
	public boolean getTextAndCompare(WebElement element, String text) {
		boolean status = element.getText().contentEquals(text);
		return status;
	}	

	public void acceptConsent() {
		WebElement root1 = driver.findElement(By.id("usercentrics-root"));
		//Get shadow root element
		WebElement shadowRoot1 = getShadowRootElement(root1);
		
		List<WebElement> elementName = shadowRoot1.findElements(By.tagName("button"));
		for(WebElement Em:elementName) {
			System.out.println(Em.getAttribute("class"));
			if(Em.getAttribute("class").contentEquals("sc-gsDKAQ dZIwB")) {
				Em.click();
			}
		}
	}
		
		
	public WebElement getShadowRootElement(WebElement element) {
			
			JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
			 SearchContext shadowRoot = (SearchContext)javascriptExecutor.executeScript("return arguments[0].shadowRoot", element);
			 WebElement shadowContent = shadowRoot.findElement(By.cssSelector("div#focus-lock-id")); 
			 return shadowContent;
			    }	
	
	public boolean getCurrentPageTitleAndCompare(String value) {
		String title= driver.getTitle();
		if(title.equalsIgnoreCase(value)) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	
	
	public void reportLog(ExtentTest logger, String steps, boolean value) throws IOException {
		
		if(value== true) {
			logger.log(Status.PASS, steps);
		}else {
			logger.log(Status.FAIL, steps);
		}
			
		  
		  
		
		
		
	    //logger.log(Status.INFO, "Login to amazon");
	    //logger.log(Status.PASS, "Title verified");
	   
	    //extent.flush();
		
         // You can call createTest method multiple times depends on your requirement
         // In our case we are calling twice which will add 2 testcases in our report
	   // ExtentTest logger2=extent.createTest("Logoff Test");
	    
	  //  logger2.log(Status.FAIL, "Title verified");
	    
	  //  logger2.fail("Failed because of some issues", MediaEntityBuilder.createScreenCaptureFromPath("/Users/mukeshotwani/Desktop/logo.jpg").build());
     
	 //.pass("Failed because of some issues", MediaEntityBuilder.createScreenCaptureFromPath("/Users/mukeshotwani/Desktop/logo.jpg").build());

         // This will add another test in report
	   // extent.flush();
	}
	
	
}
