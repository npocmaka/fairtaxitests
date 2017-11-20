package tests;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import driver.DriverInstance;
import js.injections.JavascriptInjectors;
import pageobjects.EnterNumberPage;
import pageobjects.EstimateTrackPage;
import utils.Utils;

public class EnterNumberTest {
	private static WebDriver driver;
	private static EnterNumberPage enp;
	
	@BeforeClass
	public static void before() {

			//System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
			
			driver=driver= DriverInstance.getInstance();
			JavascriptInjectors.setCurrentLocation(driver, 42.687706, 23.323003);
			enp=new EnterNumberPage(driver);
			Utils.sleep(1000);
	}
	
	@Before
	public void reload() {
		enp.reload();
		Utils.sleep(2000);
	}
	
	@Test
	public  void testNonTaxiNumber() {
		String res=enp.checkNumber("ca5951xk");
		//System.out.println(res);
		assertTrue(res.toLowerCase().contains("here is no information about this taxi’s fares and which company it belongs to"));
		
	}
	
	@Test
	public  void testOkTaxiNumber() {
		String res=enp.checkNumber("cb5749ab");
		//System.out.println(res);
		assertTrue(res.toLowerCase().contains("company"));
		assertTrue(res.toLowerCase().contains("registration"));
		assertTrue(res.toLowerCase().contains("axi is fair"));	
	}

}
