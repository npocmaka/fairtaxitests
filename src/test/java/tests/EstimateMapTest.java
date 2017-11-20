package tests;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;

import static org.junit.Assert.assertNotEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import driver.DriverInstance;
import js.injections.JavascriptInjectors;
import pageobjects.EstimateTrackPage;
import pageobjects.LiveTrackPage;
import utils.Utils;

public class EstimateMapTest {
	
	private static WebDriver driver;
	private static EstimateTrackPage et;
	
	
	@BeforeClass
	public static void before() {

			//System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
			
			driver= DriverInstance.getInstance();
			JavascriptInjectors.setCurrentLocation(driver, 42.687706, 23.323003);
			et=new EstimateTrackPage(driver,43.137960,24.712521);
			Utils.sleep(1000);

	}
	
	@Test
	public  void testInitialLocation() {
		String il=getInitialLocation();
		assertTrue(il.contains("Akademik Anastas Ishirkov"));
	}
	
	@Test
	public  void testClickOnTheMap() {
		String il=getInitialLocation();
		assertTrue(il.contains("Akademik Anastas Ishirkov"));
		
		et.mapScrollClick(1800, 1800);
		Utils.sleep(500);
		et.getMap().click();
		
		et.getDropOff().click();
		et.getDropOff().sendKeys(Keys.ESCAPE);
		Utils.sleep(500);
		String dropLocation;
		dropLocation=JavascriptInjectors.textByCSSPathLast(driver, "span.pac-item-query > span.pac-matched");
//		try {
//			dropLocation=JavascriptInjectors.textByCSSPath(driver, "span.pac-item-query > span.pac-matched", 2);
//		}catch(Throwable e) {
//			dropLocation=JavascriptInjectors.textByCSSPath(driver, "span.pac-item-query > span.pac-matched", 1);
//		}
		
		//System.out.println(dropLocation);
		assertNotEquals(dropLocation, il);
	}
	
	private String getInitialLocation() {
		et.getPickUp().click();
		et.getPickUp().sendKeys(Keys.ESCAPE);
		Utils.sleep(500);
		String res=JavascriptInjectors.textByXpath(driver, "//span[@class=\"pac-item-query\"]/span[@class=\"pac-matched\"][1]");
		return res;
	}
	
}
