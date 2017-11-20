package tests;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import driver.DriverInstance;
import js.injections.JavascriptInjectors;
import pageobjects.EnterNumberPage;
import utils.Utils;

public class EnterNumberPhotoTest {
	
	private static WebDriver driver;
	private static EnterNumberPage enp;
	
	@BeforeClass
	public static void before() {

			//System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
			
			driver= DriverInstance.getInstance();
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
	public void testOkPhoto() {
		String res=enp.checkPhoto("./pics/5749.jpg");
		Utils.sleep(2200);
		assertTrue(res.toLowerCase().contains("company"));
		assertTrue(res.toLowerCase().contains("registration"));
		assertTrue(res.toLowerCase().contains("axi is fair"));	
	}
	
	@Test
	public void testNotOkPhoto() {
		String res=enp.checkPhoto("./pics/184millions.jpg");
		Utils.sleep(2000);
		assertTrue(res.toLowerCase().contains("could not recognize the vehicle's registration number"));
	}
	


}
