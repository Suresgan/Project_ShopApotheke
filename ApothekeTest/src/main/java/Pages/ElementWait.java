package Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ElementWait {
	
	WebDriver driver;
	WebDriverWait wait;
	
	//Constructor that will be automatically called as soon as the object of the class is created
		public ElementWait(WebDriver driver) {
			this.driver=driver;
			
		}
	
		 public WebElement waitForVisibility(WebElement element) throws Error{
			 WebElement value =  new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(element));
			  return value;
	    }
		 
		 public WebElement elementToBeClickable(WebElement element) throws Error{
			 WebElement value =  new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(element));
			  return value;
	    }
		 
		 
}
