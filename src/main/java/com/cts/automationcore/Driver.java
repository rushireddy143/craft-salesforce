package com.cts.automationcore;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cts.objectrepository.ObjectRepository;


public class Driver extends TestCase {
	
	public static WebDriver driver;
	
	protected Datasheet data;
	protected Configuration conf;
	protected ObjectRepository or;
	protected static WebDriverWait wait;
	
	public void setup(String strLocation,String strSheetName,String strBrowsers) throws Exception{
		String strBrowser = strBrowsers;
		DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
		String AplicationEnvironment = System.getProperty("environment");
		
		data = Datasheet.getInstance();
		data.getDataSheet();
		data.setActiveSheet(strSheetName);
		try{
			conf = Configuration.getConfiguration();
			conf.getpropValues();
		}catch (IOException e) {
			System.out.println("Issues in properties file for Configuration");
		}
		
		try{
			if(strBrowser.equalsIgnoreCase("fireFox")){
				cap=DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				cap.setPlatform(Platform.ANY);
				
			}else if(strBrowser.equalsIgnoreCase("iexplore")){
				System.out.println("Setting up IE browser");
				cap=DesiredCapabilities.internetExplorer();
				cap.setBrowserName("iexplorer");
				cap.setPlatform(Platform.WINDOWS);
				cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			}else if(strBrowser.equalsIgnoreCase("Chrome")){
				cap=DesiredCapabilities.chrome();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("test-type");
				options.addArguments("disable-extensions");
				options.addArguments("--start-maximized");
				cap.setCapability(ChromeOptions.CAPABILITY,options);
				
				  System.setProperty("webdriver.chrome.driver","D:\\Selenium\\chromedriver.exe");
					
			      //Opening Browser
				  driver=new ChromeDriver(options);
			}
			
			//driver=new RemoteWebDriver(new URL("http:/localhost:4444/wd/hub"),cap);
			//driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
			//driver.manage().timeouts().implicitlyWait(90,TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(120,TimeUnit.SECONDS);
			wait = new WebDriverWait(driver,60);
			driver.get("https://login.yahoo.com/");
			
			
			
			
			/*switch (AplicationEnvironment){
			case "Dev":
				driver.get(Configuration.DevUrl);
				reportEvent.Status("Application opened with",Configuration.DevUrl,"pass");
				break;
			case "Test":
				driver.get(Configuration.TestUrl);
				reportEvent.Status("Application opened with",Configuration.TestUrl,"pass");
				break;
			case "Stage":
				driver.get(Configuration.StageUrl);
				reportEvent.Status("Application opened with",Configuration.StageUrl,"pass");
				break;
			default:
				reportEvent.Status("Aplication Issue","Environment Selection Problem usingSystem Variable","FAIL");
				break;
			}
			Thread.sleep(6000);*/
			driver.manage().window().maximize();
		}catch(Exception e){
			System.out.println("Issue in Browser Startup Review Driver or Start Grid and Nodes");
			System.out.println(e.toString());
		}
		
//		try{
//			or = ObjectRepository.getConfiguration();
//			or.getPropValues();
//		}catch (IOException e) {
//			System.out.println("Issues in Properties File for object Repository");
//		}
	
		}
}


