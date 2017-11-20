package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverInstance {
	
	   //private static DriverInstance instance = null;
	   private static WebDriver driver;
	   
	   protected DriverInstance() {
	      // Exists only to defeat instantiation.
	   }
	   public static WebDriver  getInstance() {
	      if(driver == null) {
	         //instance = new DriverInstance();
	         System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
	         driver= new ChromeDriver();
	      }
	      return driver;
	   }


}
