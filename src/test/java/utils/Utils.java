package utils;

import java.io.File;

public class Utils {
	
	public static void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
	public static String getFullPath(String path) {
		File file=new File(path);
		return file.getAbsolutePath();
	}
	
    public static void pressAnyKeyToContinue()
	 { 
	        System.out.println("Press Enter key to continue...");
	        try
	        {
	            System.in.read();
	        }  
	        catch(Exception e)
	        {}  
	 }
}
