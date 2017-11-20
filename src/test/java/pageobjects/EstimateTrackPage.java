package pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.DriverInstance;
import js.injections.JavascriptInjectors;
import utils.Utils;;

public class EstimateTrackPage {
	private WebDriver driver;
	private static final String link="https://fair-taxi-fare.com/estimate-fare/estimate-track";
	
	public 	EstimateTrackPage(WebDriver driver) {
		this.driver=driver;
		driver.get(link);
		Utils.sleep(1000);
		
	}
	
	public void reload() {
		driver.get(link);
	}
	
	public 	EstimateTrackPage(WebDriver driver,double lat,double lon) {
		this.driver=DriverInstance.getInstance();
		InitialPage ip=new InitialPage(driver);
		JavascriptInjectors.setCurrentLocation(driver, lat, lon);
		ip.getEstimateFare().click();
		//driver.get("https://fair-taxi-fare.com/estimate-fare/estimate-track");
		Utils.sleep(1000);
		
	}
	
	@FindBy(how = How.ID, using = "pickUp")
	private WebElement pickUp;
	
	public WebElement getPickUp() {
		return driver.findElement(By.id("pickUp"));
	}
	
	@FindBy(how = How.ID, using = "dropOff")
	private WebElement dropOff;
	
	public WebElement getDropOff() {
		return driver.findElement(By.id("dropOff"));
	}
	
	@FindBy(how = How.ID,using="estimate-submit")
	private WebElement estimateSubmit;
	
	public WebElement getEstimateSubmit() {
		return driver.findElement(By.id("estimate-submit"));
	}
	
	@FindBy(how = How.ID,using="map")
	private WebElement map;
	public WebElement getMap() {
		return driver.findElement(By.id("map"));
	}
	
	public WebElement getCalloutInput() {
		return driver.findElement(By.cssSelector("label.custom-control.custom-checkbox > input"));
	}
	
	public WebElement getCallout() {
		return driver.findElement(By.cssSelector("span.custom-control-description"));
	}
	
	public WebElement getWarning() {
		return driver.findElement(By.cssSelector("div > app-fair-alert > div.alert.alert-danger.alert-dismissable"));
	}
	
	//https://www.youtube.com/watch?v=cA1q6_Tff7Y
	public WebElement getBack() {
		return driver.findElement(By.id("back-a"));
	}
	
	public Boolean isCalloutChecked() {
		return JavascriptInjectors.isCheckboxChecked(driver, "label.custom-control.custom-checkbox > input");
	}
		
    public void setPickUpLocation(String location) {
    	WebElement pickUp=getPickUp();
    	pickUp.clear();
    	pickUp.click();
    	pickUp.sendKeys(location);
    	Utils.sleep(2000);
    	//Utils.pressAnyKeyToContinue();
    	try {
    		WebElement loc=(driver.findElements(By.xpath("//div[@class=\"pac-container pac-logo\"][1]/div"))).get(0);
    		loc.click();
    	} catch (Throwable e) {
    		e.printStackTrace();
    	}
    	
    	Utils.sleep(300); 	
    }
    
    public void setDropOffLocation(String location) {
    	WebElement dropOff=getDropOff();
    	dropOff.clear();
    	dropOff.click();
    	dropOff.sendKeys(location);
    	Utils.sleep(2000);
    	WebElement loc=(driver.findElements(By.xpath("//div[@class=\"pac-container pac-logo\"][2]/div"))).get(0);
    	loc.click();
    	Utils.sleep(300); 	
    }
    
    public double estimateTrack(String start,String end,boolean callout) {
    	setPickUpLocation( start);
    	setDropOffLocation( end);
    	
    	WebElement clt=getCallout();
    	//clt.click();
    	WebElement chk=getCalloutInput();
    	Utils.sleep(1000);
    	//System.out.println(isCalloutChecked()+"~~~");
    	if(chk.isSelected() && !callout ) {
    		clt.click();
    	}
    	if(!chk.isSelected() && callout) {
    		clt.click();
    	}
    	
    	getEstimateSubmit().click();
    	Utils.sleep(1500);
    	try {
	    	return Double.parseDouble(
	    			driver.findElement(By.xpath("//h3[@class=\"display-4\"]")).
	    			getText().split(" ")[0]
	    	); 
    	}catch(NoSuchElementException nse ) {
    		return -1;
    	}
    }
    
    public void mapClick(int xPercent,int yPercent) {
    	if(xPercent>100 || xPercent<0 || yPercent >100 || yPercent<0) {
    		throw new IllegalArgumentException("percent values must be between 0 and 100");
    	}
    	
    	WebElement map=getMap();
    	int yPos=map.getLocation().y;
    	int xPos=map.getLocation().x;
    	
    	int width=map.getSize().width;
    	int heigh=map.getSize().height;
    	
    	int yCor=yPos+((yPercent*heigh)/100);
    	int xCor=xPos+((xPercent*width)/100);
    		
    }
    
   
    
    
    public void mapScrollClick(int down,int left) {
    	JavascriptInjectors.scrollDownById(driver, "map", down, left);
    	getMap().click();
    }
    
    
    

    
    //$x('//button[@class="btn btn-outline-light"]')[0].click();
    	
	
}
