package pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.DriverInstance;
import js.injections.JavascriptInjectors;
import utils.Coordinates;
import utils.TrackingFare;
import utils.Utils;

public class LiveTrackPage {
	private WebDriver driver;
	private static String  link="https://fair-taxi-fare.com/estimate-fare/live-track";
	
	public 	LiveTrackPage(WebDriver driver) {
		this.driver=driver;
		driver.get(link);
		Utils.sleep(5000);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("livetrack-button")));
		
	}
	// live tracking with preset start location
	public 	LiveTrackPage(WebDriver driver , double lat,double lon) {
		this.driver=driver;
		//EstimateTrackPage etp=new EstimateTrackPage(driver, lat, lon);
		driver.get(link);
		JavascriptInjectors.setCurrentLocation(driver, lat, lon);
		//etp.
		Utils.sleep(5000);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("livetrack-button")));
		
	}
	
	public void reload() {
		driver.get(link);
	}
	
	@FindBy(how = How.ID, using = "livetrack-button")
	private WebElement trackButton;
	
	public WebElement getTrackButton() {
		return driver.findElement(By.id("livetrack-button"));
	}
	
	@FindBy(how = How.CSS, using ="div.px-2.px-lg-5.text-center > h3")
	private WebElement trackData;
	
	
	public String getTrackData() {
		return driver.findElement(By.cssSelector("div.px-2.px-lg-5.text-center > h3")).getText();
	}
	
	//https://www.youtube.com/watch?v=cA1q6_Tff7Y
	public WebElement getBack() {
		return driver.findElement(By.id("back-a"));
	}
	
	public WebElement getCalloutInput() {
		return driver.findElement(By.cssSelector("label.custom-control.custom-checkbox > input"));
	}
	
	public WebElement getCallout() {
		return driver.findElement(By.cssSelector("span.custom-control-description"));
	}
	
	
	public void stopTracking() {
		if (getTrackButton().getAttribute("value").toLowerCase().contains("stop")) {
			getTrackButton().click();
		}
	}
	
	public void startTracking() {
		
		if (getTrackButton().getAttribute("value").toLowerCase().contains("start")) {
			getTrackButton().click();
		}
	}
	
	public double getDistance() {
		return Double.parseDouble(getTrackData().split("/")[0].split("km")[0]);
	}
	
	public double getMoney() {
		return Double.parseDouble(getTrackData().split("/")[1].split("BGN")[0]);
	}
	
	
	private void waitForElement(WebElement we,int repeats,int timeout) {
		for (int i=0;i<repeats;i++) {
			if(!we.isDisplayed()) {
				Utils.sleep(timeout);
			}
		}
	}
	
	
	private void waitForElement(By selector,int repeats,int timeout,String message) {
		for (int i=0;i<repeats;i++) {
			if(driver.findElements(selector).size()<1) {
				Utils.sleep(timeout);
				return;
			}
		}
		throw new IllegalStateException(message);
		
	}
	
	public TrackingFare liveTrack(Coordinates[] crds,int stepTimeout,boolean callout) {
		
		if ( crds==null||crds.length<2 ) {
			return new TrackingFare(0, 0);
		}
		
    	WebElement chk=getCalloutInput();
    	WebElement clt=getCallout();
    	if(chk.isSelected() && !callout ) {
    		clt.click();
    	}
    	if(!chk.isSelected() && callout) {
    		clt.click();
    	}
    	Utils.sleep(stepTimeout);
		
		
		JavascriptInjectors.addGeolocationMock(driver);
		JavascriptInjectors.useGM(driver);
		JavascriptInjectors.setGMLocation(driver, 
				crds[0].getLat(), 
				crds[0].getLon());
		JavascriptInjectors.setCurrentLocation(driver, crds[0].getLat(), crds[0].getLon());
		Utils.sleep(stepTimeout);
		startTracking();
		
		for(int i=1;i<crds.length;i++) {
			JavascriptInjectors.setGMLocation(driver, 
					crds[i].getLat(), 
					crds[i].getLon());
			Utils.sleep(stepTimeout);
			JavascriptInjectors.sendGMLocation(driver);
		}
		
		stopTracking();
		JavascriptInjectors.restoreGM(driver);
		return new TrackingFare(getDistance(),getMoney());
		
	}

}
