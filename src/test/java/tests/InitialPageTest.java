package tests;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;

import static org.junit.Assert.assertNull;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import driver.DriverInstance;
import js.injections.JavascriptInjectors;
import pageobjects.EstimateTrackPage;
import pageobjects.InitialPage;
import utils.Utils;

public class InitialPageTest {
	private static WebDriver driver;
	private static InitialPage ip;
	
	@BeforeClass
	public static void before() {

			//System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
			
			driver= DriverInstance.getInstance();
			JavascriptInjectors.setCurrentLocation(driver, 42.687706, 23.323003);
			ip=new InitialPage(driver);
			Utils.sleep(1000);

	}
	
	@Before
	public void reload() {
		ip.reload();
	}
	
	@Test
	public void uploadPicNoTaxiTest() {
		assertTrue("1.", ip.uploadPhoto("./pics/5951.png").contains("5951"));
		//System.out.println();	
	}
	
	@Test
	public void uploadPicNoNumber() {
		assertNull("1.", ip.uploadPhoto("./pics/84milions.jpg"));
		//System.out.println();	
	}
	
	@Test
	public void uploadPicGoodTaxi() {
		assertTrue("1.", ip.uploadPhoto("./pics/3132.jpg").contains("3132"));
		String wholeText=driver.findElement(By.tagName("body")).getText();
		assertTrue(wholeText.toLowerCase().contains("taxi is fair!"));
		//System.out.println(wholeText);	
	}
	

}
