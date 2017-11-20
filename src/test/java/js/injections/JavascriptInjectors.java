package js.injections;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import js.resources.FindByXpath;
import js.resources.MockGeolocation;

public class JavascriptInjectors {
	
	public static void setCurrentLocation(WebDriver driver , double lat, double lon) {
		((JavascriptExecutor)driver).executeScript("window.navigator.geolocation.getCurrentPosition = function(success){var position = {'coords' : {'latitude': '"+lat+"', 'longitude': '"+lon+"'}}; success(position);}");		
	}
	
	public static void setCurrentLocation(WebDriver driver , String lat, String lon) {
		((JavascriptExecutor)driver).executeScript("window.navigator.geolocation.getCurrentPosition = function(success){var position = {'coords' : {'latitude': '"+lat+"', 'longitude': '"+lon+"'}}; success(position);}");		
	}
	
	public static void addGeolocationMock(WebDriver driver) {
		((JavascriptExecutor)driver).executeScript(MockGeolocation.mockGeoloc);		
	}
	
	public static boolean isThereGM(WebDriver driver) {
		return (Boolean)((JavascriptExecutor)driver).executeScript("try{return geolocate.use!=undefined;}catch(err){return false}");
	}
	
	public static void useGM(WebDriver driver) {
		if(!isThereGM(driver)) {
			addGeolocationMock(driver);
		}
		((JavascriptExecutor)driver).executeScript("geolocate.use();");		
	}
	
	public static void setGMLocation(WebDriver driver,String lat,String lon) {
		if(!isThereGM(driver)) {
			addGeolocationMock(driver);
		}
		//((JavascriptExecutor)driver).executeScript("geolocate.use();");
		((JavascriptExecutor)driver).executeScript("geolocate.change({lat: "+lat+", lng: "+lon+"});");

	}
	
	public static void setGMLocation(WebDriver driver,double lat,double lon) {
		if(!isThereGM(driver)) {
			addGeolocationMock(driver);
		}
		((JavascriptExecutor)driver).executeScript("geolocate.change({lat: "+lat+", lng: "+lon+"});");
	}
	
	public static void sendGMLocation(WebDriver driver) {
		if(!isThereGM(driver)) {
			addGeolocationMock(driver);
		}
		((JavascriptExecutor)driver).executeScript("geolocate.send();");
	}
	
	public static void restoreGM(WebDriver driver) {
		if(!isThereGM(driver)) {
			addGeolocationMock(driver);
		}
		((JavascriptExecutor)driver).executeScript("geolocate.restore();");
	}
	
	public static void scrollDownById(WebDriver driver,String id,int down,int left) {
		((JavascriptExecutor) driver).executeScript("document.getElementById('"+id+"').scrollBy("+left+", "+down+");");
	}
	
	public static void click(WebDriver driver, String id) {
		((JavascriptExecutor) driver).executeScript("");
	}
	
	public static void setFindByXpath(WebDriver driver) {
		((JavascriptExecutor)driver).executeScript(FindByXpath.findElementByXpath);	
	}
	
	public static String textByXpath(WebDriver driver, String xpath) {
		String command="return (function(path){"+
				"  return document.evaluate(path, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;"+
				"})('"+xpath+"').innerText;";
		
		//System.out.println(command);
				
		return (String)(((JavascriptExecutor)driver).executeScript(command));	
	}
	
	public static String textByCSSPath(WebDriver driver, String path, int item) {
		String command = "return document.querySelectorAll('"+path+"')["+item+"].innerText";
		//System.out.println(command);
		return (String)(((JavascriptExecutor)driver).executeScript(command));
	}
	
	public static String textByCSSPathLast(WebDriver driver, String path) {
		String command = "var all=document.querySelectorAll('"+path+"'); return all[all.length-1].innerText";
		//System.out.println(command);
		return (String)(((JavascriptExecutor)driver).executeScript(command));
	}
	
	public static Boolean isCheckboxChecked(WebDriver driver ,String cssPath) {
		String command = "var chk=document.querySelectorAll(\""+cssPath+"\")[0]; return chk.checked";
		return (Boolean)(((JavascriptExecutor)driver).executeScript(command));
	}
	

}
