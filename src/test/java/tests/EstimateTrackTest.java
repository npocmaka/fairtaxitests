package tests;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.Suite.SuiteClasses;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import driver.DriverInstance;
import js.injections.JavascriptInjectors;
import pageobjects.EstimateTrackPage;
import pageobjects.InitialPage;
import pageobjects.LiveTrackPage;
import utils.Utils;

public class EstimateTrackTest {
	private static WebDriver driver;
	private static EstimateTrackPage et;
	
	@BeforeClass
	public static void before() {

			//System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
			
			driver= DriverInstance.getInstance();
			//JavascriptInjectors.setCurrentLocation(driver, 42.687706, 23.323003);
			et=new EstimateTrackPage(driver/*,43.137960,24.712521*/);
			Utils.sleep(2000);

	}
	
	@Before
	public void reload() {
		et.reload();
		Utils.sleep(2000);
	}
	
	@Test
	public void testTracking() {

		double tr=et.estimateTrack( "ul. kozlodiy", "ul bitolya",false);
		
		assertTrue(tr>0.0);
		//et.setPickUpLocation( "ul. kozlodiy");
		//Utils.sleep(1000);
		//et.setDropOffLocation("ul bitolya");
		//Utils.sleep(1000);
		//System.out.println(et.getMap(driver).getLocation());
		//System.out.println(">>"+et.getMap(driver).getRect().height);
		//System.out.println(et.getMap().getSize());
		
		
		//JavascriptInjectors.scrollDownById(driver, "map", 100,60);
		//Utils.sleep(5000);
		//JavascriptInjectors.scrollDownById(driver, "map", 300,50);
		//System.out.println(driver.findElement(By.xpath("//h3[@class=\"display-4\"]")).getText());
		
		//et.getMap(driver).click();
		//Utils.sleep(500);
		//et.getEstimateSubmit(driver).click();
		//InitialPage ip=new InitialPage(driver);
		//System.out.println(ip.getEstimateFare().getText());
		
		//assertTrue(true);
	}
	
	@Test
	 //@SuiteClasses
	public  void testTrackingWithoutRoad() {

		double tr=et.estimateTrack( "ul bitolya" , "washington dc",false);
		Utils.sleep(2000);
		assertTrue(tr==-1);
	}
	
	@Test
	public  void testEmptyStartAddress() {

		et.getPickUp().clear();
		et.getEstimateSubmit().click();
		String warning=et.getWarning().getText();
		assertTrue(warning.toLowerCase().contains("address"));
	}
	
	@Test
	public  void testEmptyEndAddress() {

		et.getDropOff().clear();
		et.setPickUpLocation("vidrare");
		
		et.getEstimateSubmit().click();
		String warning=et.getWarning().getText();
		assertTrue(warning.toLowerCase().contains("destination"));
	}
	
	@Test
	public  void testNotExistingDestination() {

		et.setPickUpLocation("vidrare");
		
		et.getDropOff().sendKeys("~~~~~~~");
		et.getEstimateSubmit().click();
		Utils.sleep(2500);
		
		String warning=et.getWarning().getText();
		
		assertTrue(warning.toLowerCase().contains("results for"));
	}
	
	@Test
	public  void testNotExistingStart() {

		//et.setPickUpLocation("vidrare");
		et.setDropOffLocation("vidrare");
		et.getPickUp().clear();
		//et.getPickUp().sendKeys("~~~~~~~");
		et.getEstimateSubmit().click();
		Utils.sleep(2500);
		
		String warning=et.getWarning().getText();
		
		assertTrue(warning.toLowerCase().contains("results for"));
	}
	
	@Test
	public  void estimateFares() {

		double notCalled=et.estimateTrack( "bul vasil levski sofia" , "vidrare",false);
		et.reload();
		Utils.sleep(5000);
		double called=et.estimateTrack("bul vasil levski sofia" , "vidrare", true);
		assertTrue(called>notCalled);
	}
	
	@Test
	public  void estimateFaresEq() {

		double notCalled1=et.estimateTrack( "bul vasil levski sofia" , "vidrare",false);
		et.reload();
		Utils.sleep(5000);
		double notCalled2=et.estimateTrack("bul vasil levski sofia" , "vidrare", false);
		assertTrue(notCalled1==notCalled2);
	}
	
	@Test
	public  void estimateFaresEq2() {

		double called1=et.estimateTrack( "bul vasil levski sofia" , "vidrare",true);
		et.reload();
		Utils.sleep(2000);
		double called2=et.estimateTrack("bul vasil levski sofia" , "vidrare", true);
		System.out.println(called1+"-"+called1);
		assertTrue(called1==called2);
	}
	
	@Test
	public void testBack() {
		et.getBack().click();
		String address=driver.getCurrentUrl();
		assertTrue(address.contains("/check-taxi"));
	}
		
}
