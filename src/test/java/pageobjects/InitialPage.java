package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import driver.DriverInstance;
import utils.Keyboard;
import utils.Utils;

public class InitialPage {
	private WebDriver driver;
	private static String link="https://fair-taxi-fare.com/";
	
	public 	InitialPage(WebDriver driver) {
		this.driver=driver;
		driver.get(link);
		Utils.sleep(2000);
	}
	
	public WebElement getEstimateFare() {
		return driver.findElement(By.xpath("//a/span[contains(text(),'Estimate Fare')]"));
	}
	
	public WebElement getUploadLink() {
		return driver.findElement(By.id("upload_link"));
	}
	
	public WebElement getEnterNumber() {
		return driver.findElement(By.xpath("//img[@alt=\"Enter Registration Number\"]"));
	}
	
	public String uploadPhoto(String path) {
		WebElement ul=getUploadLink();
		Keyboard kb= new Keyboard();
		String fp=Utils.getFullPath(path);

		ul.click();
		utils.Utils.sleep(500);
		
		kb.type(fp);
		utils.Utils.sleep(500);
		kb.type("\n");

		Utils.sleep(4000);

		//return "--";
		try {
			return driver.findElement(By.xpath("//dd[@class=\"col-6\"]")).getText();
		} catch (NoSuchElementException nse) {
			return null;
		}
		
	}
	
	public void reload() {
		driver.get(link);
	}

}
