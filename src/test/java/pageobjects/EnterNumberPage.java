package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import driver.DriverInstance;
import utils.Keyboard;
import utils.Utils;

public class EnterNumberPage {
	
	private WebDriver driver;
	private static final String link="https://fair-taxi-fare.com/check-taxi/text-recognition";
	
	public 	EnterNumberPage(WebDriver driver) {
		this.driver=driver;
		driver.get(link);
		Utils.sleep(1000);
	}
	
	public 	EnterNumberPage(WebDriver driver,boolean fromInit) {
		if(!fromInit) {
			this.driver=driver;
			driver.get(link);
			Utils.sleep(1000);
			//return;
		} else {
			(new InitialPage(driver)).getEnterNumber().click();
			Utils.sleep(1000);
			//return;
		}	
	}
	
	public void reload() {
		driver.get(link);
	}
	
	//https://www.youtube.com/watch?v=cA1q6_Tff7Y
	public WebElement getBack() {
		return driver.findElement(By.id("back-a"));
	}

	
	public WebElement getInputNumber() {
		return driver.findElement(By.cssSelector("[class=\"form-control\"]"));
	}
	
	public WebElement getCheckNumber() {
		return driver.findElement(By.xpath("//button[contains(text(),'Check Number')]"));
	}
	
	public WebElement getEnterNumberAgain() {
		return driver.findElement(By.xpath("//button[contains(text(),'Enter Number Again')]"));
	}
	
	public WebElement getUploadPhoto() {
		return driver.findElement(By.xpath("//button[contains(text(),'Upload Photo')]"));
	}
	
	public String checkNumber(String plateNumber) {
		getInputNumber().clear();
		getInputNumber().sendKeys(plateNumber);
		getCheckNumber().click();
		Utils.sleep(3000);
		try {
			return driver.findElement(By.cssSelector("h5")).getText();
		}catch(NoSuchElementException nse) {
			return driver.findElement(By.tagName("body")).getText();
		}
	}
	
	public String checkPhoto(String pathToPhoto) {
		getInputNumber().clear();
		getInputNumber().sendKeys("#");
		getCheckNumber().click();
		Utils.sleep(3500);
		
		WebElement upl;
		try{
			upl=driver.findElement(By.xpath("//button[contains(text(),'Upload Photo')]"));
			upl.click();
		}catch(ElementNotVisibleException enve) {
			upl=driver.findElement(By.xpath("//button[contains(text(),'Take Photo')]"));
			upl.click();
		}
		
		Utils.sleep(500);
		Keyboard kb=new Keyboard();
		kb.type(Utils.getFullPath(pathToPhoto));
		Utils.sleep(500);
		kb.type("\n");
		Utils.sleep(3500);
		return driver.findElement(By.tagName("body")).getText();
	}

}
