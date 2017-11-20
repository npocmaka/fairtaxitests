package pageobjects;


	
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;

import driver.DriverInstance;

	public class BasePageObject {
		protected WebDriver driver;
		protected String link;
		
		
		public BasePageObject(WebDriver driver) {
			this.driver = driver;
			//driver.get();
		}
		
		public BasePageObject(WebDriver driver,String link) {
			this(driver);
			this.link=link;
			driver.get(link);
		}
		
		public void reload() {
			driver.get(link);
		}
		
		
		
	}


