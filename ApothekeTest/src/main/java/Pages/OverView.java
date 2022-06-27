package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OverView {
	
	WebDriver driver;
	ElementWait waitTime ;
	
	
	//Constructor that will be automatically called as soon as the object of the class is created
	public OverView(WebDriver driver) {
		this.driver=driver;
		waitTime = new ElementWait(driver);
	}
	
	//Locator for CustomerName field
		By customerName = By.xpath("//div[@class='o-AccountContent-left-column']//h3/font[2]/font");
		
	
		//Method to get the CustomerName 
		public String getCustmerName() {
			try {
				return driver.findElement(customerName).getText();
			} catch(Exception e) {
				System.out.println(e.getStackTrace());
				return "null";
			}	
		}
}
