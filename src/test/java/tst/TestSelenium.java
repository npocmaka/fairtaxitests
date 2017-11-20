package tst;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebElement;

import js.injections.JavascriptInjectors;
import js.resources.MockGeolocation;
import pageobjects.EstimateTrackPage;
import pageobjects.InitialPage;
import utils.Utils;

public class TestSelenium {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		//https://fair-taxi-fare.com/check-taxi
		//((JavascriptExecutor)driver).executeScript("window.navigator.geolocation.getCurrentPosition = function(success){var position = {'coords' : {'latitude': '43.138008', 'longitude': '24.712009'}}; success(position);}");
		//driver.get("https://fair-taxi-fare.com");
		//((JavascriptExecutor)driver).executeScript(MockGeolocation.mockGeoloc);
		//WebElement startPoint=Find
		//((JavascriptExecutor)driver).executeScript("window.navigator.geolocation.watchPosition = function(success){var position = {'coords' : {'latitude': '43.138116', 'longitude': '24.711431'},timestamp: (new Date()).getTime()}; success(position);}");
		
		//((JavascriptExecutor)driver).executeScript("window.navigator.geolocation.getCurrentPosition = function(success){var position = {'coords' : {'latitude': '43.138008', 'longitude': '24.712009'}}; success(position);}");
		//System.out.println(((JavascriptExecutor)driver).executeScript("try{return geolocate.use!=undefined;}catch(err){return false}"));
		//System.out.println(((JavascriptExecutor)driver).executeScript("return geolocate.use();"));
		//InitialPage ip=new InitialPage(driver);
		//System.out.println(ip.uploadPhoto("./pics/5951.png"));
		EstimateTrackPage etp = new EstimateTrackPage(driver);
		JavascriptInjectors.setFindByXpath(driver);
		Utils.sleep(2000);
		//etp.mapClick(50, 10);
		etp.mapScrollClick(1800, 1800);
		etp.getPickUp().click();
		etp.getPickUp().sendKeys(Keys.ESCAPE);
		Utils.sleep(500);
		etp.getDropOff().click();
		etp.getDropOff().sendKeys(Keys.ESCAPE);
		Utils.sleep(500);
		etp.getMap().click();
		Utils.sleep(500);
		//System.out.println(">>"+etp.getPickUp());
		
		String res=JavascriptInjectors.textByXpath(driver, "//span[@class=\"pac-item-query\"]/span[@class=\"pac-matched\"][1]");
		System.out.println(res);
		
		String res2=JavascriptInjectors.textByCSSPath(driver, "span.pac-item-query > span.pac-matched", 2);
		System.out.println(res2);
		
		//System.out.println(((JavascriptExecutor)driver).executeScript("return document.getElementById('pickUp').textContent"));
		//List<WebElement> els=driver.findElements(By.cssSelector("span.pac-item-query > span.pac-matched"));
		//System.out.println(els.size());
		//System.out.println(els.get(0).getAttribute(arg0));
		//System.out.println(els.get(1).getText());
		//System.out.println(els.get(2).getText());
		
		

	}

}
