package code;

import java.util.concurrent.TimeUnit;
import java.nio.file.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/* Prerequisite checks and tests
 * This class will complete initial checks to ensure that all requirements are satisfied so that the program doesn't run into any problems.
 * These checks can/may include:
 *  - Checking for chromedriver.exe, the program that allows our code to interface with GoogleChrome.
 *  - Checking our Internet connection
 *  - Checking that GoogleChrome is an installed application in the user's computer.
 *  - Checking that chromedriver.exe is functioning as expected.
 */

public class PrerequisiteChecks {
	Path driverDirectory;
	String driverDirectory_absolute;
	
	boolean debugEnabled = false;
	//Chrome_TestEnvironment testObject = new Chrome_TestEnvironment();
	
	public PrerequisiteChecks(boolean debugEnabled) {
		this.debugEnabled = debugEnabled;
	}
	
	public boolean run() {
		boolean pass = false;
		
		//Fetching the operation system
		check_operatingSystem();
		
		//Initiates the txt file to start recording console output
		//testObject.outputTestResults();
		
		//Dynamically fetching the location of "chromedriver.exe".
		driverDirectory = Paths.get("resources","chromedriver.exe");
		driverDirectory_absolute = driverDirectory.toFile().getAbsolutePath();
		
		//Checking if chromedriver.exe is available.
		if (Files.exists(driverDirectory)) {
			if (Files.isRegularFile(driverDirectory)) {
				System.out.println("ChromeDriver.exe exists in program resources: @ "+driverDirectory_absolute);
				System.setProperty("webdriver.chrome.driver", driverDirectory_absolute); 
				pass = true;
			}
		}
		//If not found in local "resources" directory, prompt the user to manually provide it's location.
		else { 
			System.out.println("ChromeDriver.exe does not exist in program resources");
			System.out.println("Please input the location of \"ChromeDriver.exe\" :");
			System.out.println("**NOT YET IMPLEMENTED! CLOSING PROGRAM**");
			return false;
		}
		
		System.out.println("\n\nCommencing ChromeDriver test:\n");
		boolean driverValid = check_ChromeDriver();
		System.out.println("\nTest complete.");
		
		if(driverValid) {
			System.out.println("Selenium \"ChromeDriver.exe\" test was successful.\n");
		}
		return true;
	}
	
	private void check_operatingSystem() {
	    String OS = System.getProperty("os.name").toLowerCase();
	    boolean IS_WINDOWS = (OS.indexOf("win") >= 0);
	    boolean IS_MAC = (OS.indexOf("mac") >= 0);
	    boolean IS_UNIX = (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);
	    boolean IS_SOLARIS = (OS.indexOf("sunos") >= 0);
		
		System.out.print("Fetching Operating System: ");
		if (IS_WINDOWS) {
	        System.out.println("Windows recognised. (Note: This is a good thing! Windows is this programs native OS.)");
	    } else if (IS_MAC) {
	        System.out.println("Mac recognised. (Note: This program was developed for Windows, issues may be present when using other Operating Systems.)");
	    } else if (IS_UNIX) {
	        System.out.println("Unix / Linux recognised. (Note: This program was developed for Windows, issues may be present when using other Operating Systems.)");
	    } else if (IS_SOLARIS) {
	        System.out.println("Solaris recognised. (Note: This program was developed for Windows, issues may be present when using other Operating Systems.)");
	    } else {
	        System.out.println("Failed to recognise OS. (Note: This program was developed for Windows, issues may be present when using other Operating Systems.)");
	    }
	}
	
	//Will open an initial test environment to check that the chrome driver is working correctly.
	//By running the Chrome Driver, we can also determine whether the user has Google Chrome installed.
	//Will also determine how long it took to initialize the driver and navigate to the Google home-page.
	private boolean check_ChromeDriver() {
		System.setProperty("webdriver.chrome.driver", driverDirectory_absolute); 
	    
		long startTime = System.currentTimeMillis();
		WebDriver driver=new ChromeDriver();  
	    
	    String website = "https://www.google.com/";
	    driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	    
	    try {
	    	boolean siteLoaded = false;
	    	long endTime = 0;
	        long timeToLoad = 0;
	    	
	        //Navigating to the Google home-page.
	    	driver.navigate().to(website);  
	    	JavascriptExecutor j = (JavascriptExecutor)driver;
	    	
	    	//The following loop will determine when the page is fully loaded and how long it took in milliseconds.
	        if (j.executeScript("return document.readyState").toString().equals("complete")){
	        	siteLoaded = true;
	       	  	endTime = System.currentTimeMillis();
	       	  	timeToLoad = (endTime - startTime);
	       	  	System.out.println("WebDriver was instantiated and navigated to \""+website+"\" in "+timeToLoad+" milliseconds.");
	        }
	        else {
	            while(siteLoaded==false) {
	                //check page state
	                if (j.executeScript("return document.readyState").toString().equals("complete")){
	             		siteLoaded = true;
	             		endTime = System.currentTimeMillis();
	             		timeToLoad = (endTime - startTime);
	             		System.out.println("WebDriver was instantiated and navigated to \""+website+"\" in "+timeToLoad+" milliseconds.");
	                	break;
	                }
	             }
	        }
	        
	        
	    } catch (TimeoutException e) {
	        System.out.println("Page: " + website + " did not load within 10 seconds!");
	        driver.quit();
	        return false;
	    }
	    driver.quit();
		return true;
	}
}