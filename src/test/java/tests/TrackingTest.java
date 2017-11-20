package tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.collect.LinkedHashMultimap;

import driver.DriverInstance;
import js.injections.JavascriptInjectors;
import pageobjects.LiveTrackPage;
import utils.Coordinates;
import utils.TrackingFare;
import utils.Utils;

public class TrackingTest extends SeleniumTest{
	
	private static LiveTrackPage lt;
	private static WebDriver driver;
	
	
	private static double[] gradskiSad2sofiiskiZatror= {
			42.695285, 23.320878,
			42.695773, 23.321017,
			42.696144, 23.321071,
			42.696467, 23.320878,
			42.696869, 23.320771,
			42.697137, 23.321028,
			42.697405, 23.321318,
			42.697642, 23.321651,
			42.697737, 23.321190,
			42.697855, 23.320793,
			42.698052, 23.319817,
			42.698233, 23.319045,
			42.698335, 23.318326,
			42.698808, 23.315655,
			42.699084, 23.314443,
			42.699328, 23.313005,
			42.699643, 23.311181,
			42.699990, 23.309379,
			42.700187, 23.308306,
			42.700424, 23.306643,
			42.700779, 23.304647,
			42.701031, 23.303242,
			42.701748, 23.303457,
			42.702568, 23.303661,
			42.703301, 23.303854,
			42.704823, 23.304315,
			42.705674, 23.305130,
			42.705855, 23.304926,
			42.706060, 23.303467,
			42.706249, 23.301986,
			42.707558, 23.302383,
			42.708354, 23.304261
	};
	
	private static double[] pirogov2grobishta= {
			42.690049, 23.307371,
			42.690293, 23.306985,
			42.690033, 23.306534,
			42.690222, 23.306352,
			42.690687, 23.307371,
			42.691420, 23.308787,
			42.691917, 23.309753,
			42.692508, 23.310171,
			42.693328, 23.309624,
			42.694440, 23.308884,
			42.695323, 23.308777,
			42.697310, 23.309303,
			42.699896, 23.310033,
			42.702064, 23.310666,
			42.704737, 23.311396,
			42.706030, 23.311943,
			42.705762, 23.315569,
			42.705518, 23.319571,
			42.705140, 23.323680,
			42.704738, 23.328669,
			42.705235, 23.332370,
			42.705424, 23.333100,
			42.706812, 23.334784,
			42.707600, 23.333861,
			42.708065, 23.333872,
			42.709910, 23.335589,
			42.711100, 23.336769,
			42.712275, 23.341919,
			42.712614, 23.343410,
			42.714502, 23.347336,
			42.715298, 23.347905,
			42.716827, 23.352669,
			42.716827, 23.352669,
			42.719239, 23.360651,
			42.719901, 23.362046,
	};
	
	@BeforeClass
	public static void before() {

			//System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
			
			driver= DriverInstance.getInstance();
			JavascriptInjectors.setCurrentLocation(driver, 42.687706, 23.323003);
			lt=new LiveTrackPage(driver);

	}
	
	
	@Before
	public void reload() {
		lt.reload();
		Utils.sleep(2000);
	}
	@Test
	public void testTracking() {
		
		lt.startTracking();
		
		JavascriptInjectors.addGeolocationMock(driver);
		JavascriptInjectors.useGM(driver);
		Utils.sleep(10000);
		assertTrue("1.", lt.getMoney()>=0);
		assertTrue("2.", lt.getDistance()>=0);
		
		JavascriptInjectors.setGMLocation(driver, 42.687036, 23.326057);
		Utils.sleep(10000);
		assertTrue("3.", lt.getMoney()>=0);
		assertTrue("4.", lt.getDistance()>=0);

		JavascriptInjectors.setGMLocation(driver, 42.686918, 23.325574);
		Utils.sleep(10000);
		assertTrue("5.", lt.getMoney()>0);
		assertTrue("6.", lt.getDistance()>0);
		
		
		JavascriptInjectors.setGMLocation(driver, 42.686855,23.325166);
		Utils.sleep(10000);
		assertTrue("7.", lt.getMoney()>0);
		assertTrue("8.", lt.getDistance()>0);
		
		JavascriptInjectors.setGMLocation(driver, 42.686681,23.324957);
		Utils.sleep(30000);
		assertTrue("9.", lt.getMoney()>0);
		assertTrue("10.", lt.getDistance()>0);
		
		JavascriptInjectors.setGMLocation(driver, 42.686255,23.325214);
		//LocationInjectors.sendGMLocation(driver);
		Utils.sleep(30000);
		assertTrue("11.", lt.getMoney()>0);
		assertTrue("12.", lt.getDistance()>0);
		
		JavascriptInjectors.setGMLocation(driver, 42.685790,23.325423);
		Utils.sleep(30000);
		assertTrue("13.", lt.getMoney()>0);
		assertTrue("14.", lt.getDistance()>0);
		
		JavascriptInjectors.setGMLocation(driver, 42.685096,23.325863);
		Utils.sleep(30000);
		assertTrue("15.", lt.getMoney()>0);
		assertTrue("16.", lt.getDistance()>0);
		
		lt.stopTracking();
	
	}
	
	@Test
	public void testTrackingPresetInitialLocation() {
		
		
		
		JavascriptInjectors.addGeolocationMock(driver);
		JavascriptInjectors.useGM(driver);
		JavascriptInjectors.setGMLocation(driver, 42.991762, 24.011298);
		JavascriptInjectors.setCurrentLocation(driver, 42.991762, 24.011298);

		
		lt.startTracking();
		Utils.sleep(10000);
		assertTrue("1.", lt.getMoney()>=0);
		assertTrue("2.", lt.getDistance()>=0);
		
		JavascriptInjectors.setGMLocation(driver, 42.991095,24.011620);
		Utils.sleep(30000);
		assertTrue("3.", lt.getMoney()>0);
		assertTrue("4.", lt.getDistance()>0); 
		
		lt.stopTracking();
		Utils.sleep(1000);
		
		assertTrue("5.", lt.getMoney()>0);
		assertTrue("6.", lt.getDistance()>0);
	}
	
	@Test
	public void testBack() {
		lt.getBack().click();
		String address=driver.getCurrentUrl();
		assertTrue(address.contains("/check-taxi"));
	}
	
	@Test
	public void testWithMoreData() {
		Coordinates[] crds=Coordinates.double2crds(pirogov2grobishta);
		
		TrackingFare tf=lt.liveTrack(crds, 2500, true);		
		TrackingFare tf2=lt.liveTrack(crds, 2500, false);
		
		assertTrue(tf.getFare()-tf2.getFare()>0.5);
		assertTrue(Math.abs(tf.getDistance() - tf2.getDistance())<0.5);
	}
	
	
	
	@AfterClass
	public static void after() {
		JavascriptInjectors.restoreGM(driver);
	}

}
