package generic;



import org.testng.ITestResult;
import org.testng.annotations.AfterClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.RollingFileAppender;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.pulse.Page.HomePage;
import com.pulse.Page.LoginPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;




public abstract class BaseTest implements IAutoConst{
	
	//public static RemoteWebDriver driver;
	
	
	public   RemoteWebDriver driver=null;
	
	public static int REPEAT_MINUS = 100;
	
Logger APP_LOGS = Logger.getLogger("BaseTest");
     
	
	public static String scrshotFolderLoc;

	 final static String workingDir = System.getProperty("user.dir");

	  final static String filePath = "\\data\\preCondInput.xlsx";

	//public static String path ="C:\\Users\\Admin\\workspace\\SynchronizedExtentReport5\\data\\preCondInput.xlsx";

	  public static String path =workingDir+filePath;
	
	static int testRunId=000000;
	
	  public static String logfiletimestamp;
	    
	//	static BasePage b=new BasePage(driver);
		
		 
		 public static String downloadPath = null;
		
		public static  ExtentReports extent;
		public static  ExtentTest extentTest; 
		
		static Excel eLib = new Excel();
		
		String url = eLib.getCellValue(path,"PreCon", 1, 0);
		
		
	//	String browserType = eLib.getCellValue(path,"PreCon", 1, 1);
		
		
	//	 int time = Integer.parseInt((eLib.getCellValue(path,"PreCon", 1, 2)).replace(".0", ""));
		
		/*
		@BeforeTest
		public void setExtent()
		
		
		{
			extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/"+"AutomationLog_TestRunId ExtentReport.html", false);
			extent.addSystemInfo("Host Name", "Naveen Mac");
			extent.addSystemInfo("User Name", "Naveen Automation Labs");
			extent.addSystemInfo("Environment", "QA");
			
			
			
		}*/
		
		@BeforeTest
		
		@Parameters({"browser"})
		
		public void setUp(String browser) throws Exception 
		
		{
			System.out.println("*******************");
			
			System.out.println("Opening chrome driver");
			 
			
			driver = getDriver(browser);
			
			
			driver.manage().window().maximize();

		}
		

		
		


		
		public static RemoteWebDriver getDriver(String browser) throws Exception {
			
		//	extentTest =extent.startTest(method.getName());
			
		
/*			 String fileName=null;
			   
	    	 Date d = new Date();
	    	 
	    	 
	 	    SimpleDateFormat format = new SimpleDateFormat(  "dd.MMMMM.yyyy.hh.mm");
	 	   

	 	   logfiletimestamp = "AutomationLog_TestRunId_"+format.format(d);
	 	   
	 	   
	 	   
		    System.setProperty("autologname",logfiletimestamp);

		      PropertyConfigurator.configure("Log4j.properties");
		    
        	String browserType = eLib.getCellValue(path,"PreCon", 1, 1);
	
			
			BasePage b=new BasePage(driver);
			
			
		     downloadPath=b.preInitialize();
		*/
				System.out.println("Opening chrome driver2");
					 
			
		 		return new RemoteWebDriver(new URL("http://192.168.2.8:4444/wd/hub"), getBrowserCapabilities(browser));
			
		 		
		 		
		 		//return new RemoteWebDriver(new URL("http://10.111.108.157:4444/wd/hub"), getBrowserCapabilities(browser));
						 
				
			
		}
		
		public static DesiredCapabilities getBrowserCapabilities(String browser) throws MalformedURLException
		
		{
			

            DesiredCapabilities dr=null;

            
            RemoteWebDriver driver=null;
            
			switch (browser) 
			
			
			
			{
			case "firefox":
				System.out.println("Opening firefox driver");

			//	System.setProperty("webdriver.chrome.driver", "C:\\Users\\ssrivastava4\\workspace\\PulseProject7\\driver\\chromedriver.exe");
			
				
				 DesiredCapabilities capability = new DesiredCapabilities().firefox();
		            capability.setBrowserName("firefox");
		            capability.setPlatform(Platform.WINDOWS);
		            
		         //   DesiredCapabilities capabillities = new DesiredCapabilities("firefox", "3.6.", Platform.WINDOWS);
		            capability.setCapability("job-name", "Fancy Firefox profile");
		            FirefoxProfile profile = new FirefoxProfile();
		            profile.setPreference("network.http.phishy-userpass-length", 255);
		            capability.setCapability(FirefoxDriver.PROFILE, profile);
		            
		            
		            
		            
				return DesiredCapabilities.firefox();
				
				
				
			case "chrome":
				System.out.println("Opening chrome driver3");
			
				
			/*	ChromeOptions options = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_settings.popups", 0);
				options.setExperimentalOption("prefs", prefs);
				//driver = new ChromeDriver();
			
				return DesiredCapabilities.chrome();
			*/
				
			
				
				
				
			//	// baseUrl = "https://www.google.co.in/";
				  //  hubURL = "http://HubIpAddress:4444/wd/hub";

				    DesiredCapabilities capability1 = DesiredCapabilities.chrome();
				    capability1.setBrowserName("chrome");
				    capability1.setPlatform(Platform.ANY);


				//    driver = new RemoteWebDriver (new URL("http://10.111.108.157:4444/wd/hub"), capability1);
				
				
				    ChromeOptions options = new ChromeOptions();
		            options.addArguments("--start-maximized");

		
		            
		            
				    return DesiredCapabilities.chrome();
					
				
				
				
				
			case "IE":
				System.out.println("Opening IE driver");
				return DesiredCapabilities.internetExplorer();
			default:
				System.out.println("browser : " + browser + " is invalid, Launching Firefox as browser of choice..");
				return DesiredCapabilities.firefox();
				
				
			
			
			}
			
	
		
		}
		
		
	
		
		
		
	
/*		@BeforeMethod-----shwetabh working code earlier
	
	public void preCondition(Method method) throws Exception
	
	{
		
			extentTest =extent.startTest(method.getName());
			
		 String fileName=null;
		   
    	 Date d = new Date();
    	 
    	 
 	    SimpleDateFormat format = new SimpleDateFormat(  "dd.MMMMM.yyyy.hh.mm");
 	   

 	   logfiletimestamp = "AutomationLog_TestRunId-"+b.getlastRunid()+"_"+format.format(d);
 	   
 	   
 	   
 	  
	    System.setProperty("autologname",logfiletimestamp);

	      PropertyConfigurator.configure("Log4j.properties");
	    
	    	 
	    	 
		//scrshotFolderLoc=BasePage.createFolder();
		    
			Excel eLib = new Excel();
			
		//	String url = eLib.getCellValue(path,"PreCon", 1, 0);
			
		
			
			String browserType = eLib.getCellValue(path,"PreCon", 1, 1);
			
			
			int time = Integer.parseInt((eLib.getCellValue(path,"PreCon", 1, 2)).replace(".0", ""));
			
			
			
		     downloadPath=b.preInitialize();
			if(browserType.equalsIgnoreCase("GC"))
			{
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\ssrivastava4\\workspace\\PulseProject6\\driver\\chromedriver.exe");
			
				
				  
	

			     HashMap<String, Object> chromePrefs = new HashMap<String, Object>();  
			     chromePrefs.put("profile.default_content_settings.popups", 0);  
			     
			 	
			     chromePrefs.put("download.default_directory", downloadPath);  
			     
			     
			     

			     chromePrefs.put("safebrowsing.enabled", true);  
			     


			     ChromeOptions options = new ChromeOptions();
			     options.addArguments("disable-infobars");
			     Map<String, Object> prefs = new HashMap<String, Object>();
			     prefs.put("profile.default_content_setting_values.automatic_downloads", 1);
			     options.setExperimentalOption("prefs", prefs);

			     driver = new ChromeDriver(options);
					
	
    
			     
			     
					APP_LOGS.info("Chrome Browser opened");

				
				//**************************************			
							
							
							
							
							
				
				
				
				APP_LOGS.info("Chrome Browser opened");
			}
			
			
			
			
			else if(browserType.equalsIgnoreCase("FF"))
			{
				
				
			   //   File pathBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
                  
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\ssrivastava4\\workspace\\PulseProject6\\driver\\geckodriver.exe");
				//System.setProperty("webdriver.firefox.marionette","C:\\Users\\ssrivastava4\\workspace\\PulseProject6\\driver\\geckodriver.exe");
				
				
                   downloadPath=b.preInitialize();
		
		
         //        File pathBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
                   
                   
                  // FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);
                   
                   
                                      
                  
				 
				FirefoxProfile profile = new FirefoxProfile();
			
				profile.setPreference("browser.download.folderList", 2);
				profile.setPreference("browser.download.manager.showWhenStarting", false);
				profile.setPreference("browser.download.dir", downloadPath);
				
				
				
				profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
						"application/vnd.ms-powerpoint,application/vnd.openxmlformats-officedocument.presentationml.presentation");
								
				
				profile.setPreference("browser.helperApps.alwaysAsk.force", false);
				profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
				profile.setPreference("browser.download.manager.focusWhenStarting", false);
				profile.setPreference("browser.download.manager.useWindow", false);
				profile.setPreference("browser.download.manager.showAlertOnComplete", false);
				profile.setPreference("browser.download.manager.closeWhenDone", false);
			
				
				DesiredCapabilities capabilities = new DesiredCapabilities();
			    capabilities.setBrowserName("firefox");
			    capabilities.setPlatform(org.openqa.selenium.Platform.ANY);
			    capabilities.setCapability(FirefoxDriver.PROFILE, profile);
			    driver = new FirefoxDriver(capabilities);
			    
			    
			    
		   		 
				 FirefoxOptions options = new FirefoxOptions();
				 options.setProfile(profile);
				 
				// driver = new FirefoxDriver(options);
	              //driver = new FirefoxDriver(profile);
				
				APP_LOGS.info("FireFox Browser opened");
			}
			
			
			
			
			
			else if(browserType.equalsIgnoreCase("IE"))
			{
				System.setProperty("webdriver.ie.driver", "C:\\Users\\ssrivastava4\\workspace\\PulseProject\\driver\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				
				APP_LOGS.info("IE Browser opened");
			}
			else
			{
				System.out.println("Browser input is invalid. Please go and select the browser properly");
				
				APP_LOGS.info("Browser input is invalid. Please go and select the browser properly");
			}
			
						
		
			driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);

			APP_LOGS.info("Implicit wait given");
		      
			 

         	String url = eLib.getCellValue(path,"PreCon", 1, 0);
			 
			driver.manage().window().maximize();
			driver.get(url);
			
			APP_LOGS.info("Url opened");
			
        b.preInitialize();
        
		
		b.insertsql(method.getName());
	
		b.getallmethodlist();

		b.executesqlandverifytext();
		
		
		b.getlastRunid();
		
		
		
	
	    System.out.println("Executing Test Case id  : "+b.getlastRunid());
	       
	       APP_LOGS.info("Executing Test Case id is : "+	b.getlastRunid());
	       
	       
	       
	       
			    
	}
	
		*/
		
		
		
		public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException{
			
			
			String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			// after execution, you could see a folder "FailedTestsScreenshots"
			// under src folder
			String destination = System.getProperty("user.dir") + "/screenshots/" + screenshotName + dateName
					+ ".png";
			File finalDestination = new File(destination);
			FileUtils.copyFile(source, finalDestination);
			return destination;
		}

	
		
	
/*	
	@AfterMethod

	
	
		
		public void tearDown(ITestResult result) throws IOException{
			
			
		
		
		
			if(result.getStatus()==ITestResult.FAILURE){
				extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS ->"+result.getName()); //to add name in extent report
				extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS ->"+result.getThrowable()); //to add error/exception in extent report
				
				String screenshotPath = BaseTest.getScreenshot(driver, result.getName());
				
				
				
				extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); //to add screenshot in extent report
				//extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath)); //to add screencast/video in extent report
			}
			else if(result.getStatus()==ITestResult.SKIP){
				extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
			}
			
			
			else if(result.getStatus()==ITestResult.SUCCESS){
				
				extentTest.log(LogStatus.PASS, "TEST CASE PASSED IS ->"+result.getName()); //to add name in extent report
				//extentTest.log(LogStatus.PASS, "TEST CASE PASSED IS ->"+result.getThrowable()); //to add error/exception in extent report
				
			//	String screenshotPath1 = BaseTest.getScreenshot(driver, result.getName());
				
				
			//	extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath1));
		

			}
			
			HomePage hp = new HomePage(driver);
			//hp.clickOnProfile(driver);
			hp.clickArrow();
			
			hp.Logout();
	
		
			
			
			extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report
			
		*/	
	//}//

	
	@AfterTest
	public void postCondition() throws InterruptedException
	
	
	{
		
	//	driver.get("C:\\Users\\ssrivastava4\\workspace\\PulseProject3\\test-output\\"+"AutomationLog_TestRunId-"+b.getlastRunid()+"ExtentReport.html");
		
		
	//	Thread.sleep(25000);
		
		driver.quit();
	//		extent.flush();
		//	extent.close();
		}
			

				
		
		
		
	

		
		
			
}	





