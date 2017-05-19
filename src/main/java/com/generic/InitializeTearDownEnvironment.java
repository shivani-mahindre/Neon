package com.generic;


import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class InitializeTearDownEnvironment 
{
	private WebDriver webDriver;

	/**
	 * @Method		: 	setUpDesktopEnvironment
	 * @Description	:	This method initialize web driver for web application 
	 * 					by opening the browser and URL specified in config.properties file
	 * @Author	    :   Automation Tester (SQS)
	 */
	public WebDriver initializeWebEnvironment(Properties objConfig)
	{
		try 
		{
			String browser = objConfig.getProperty("web.browser").trim().toLowerCase();
			switch (browser)
			{
			case "ie": // If specified browser is Internet Explorer
				System.setProperty("webdriver.ie.driver", objConfig.getProperty("webdriver.ie.driver").trim());
				DesiredCapabilities capabilities = (DesiredCapabilities) DesiredCapabilities.class.getMethod("internetExplorer").invoke(null);
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				capabilities.setJavascriptEnabled(true); 
				//capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "");
				webDriver = new InternetExplorerDriver(capabilities);
				break;

			case "firefox": // If specified browser is Firefox 
				//webDriver = new FirefoxDriver();
				// if user want to set firefox profile use \
				System.setProperty("webdriver.gecko.driver",objConfig.getProperty("webdriver.gecko.driver").trim());            
				 webDriver = new FirefoxDriver(this.FirefoxDriverProfile(objConfig));
				break;

			case "chrome": // If specified browser is Chrome 
				System.setProperty("webdriver.chrome.driver", objConfig.getProperty("webdriver.chrome.driver").trim());
				webDriver = new ChromeDriver();
				break;
				
			case "edge": // If specified browser is Edge 
                System.setProperty("webdriver.edge.driver",objConfig.getProperty("webdriver.edge.driver").trim()); 
                EdgeOptions options= new EdgeOptions(); 
                options.setPageLoadStrategy("eager"); 
                webDriver = new EdgeDriver(); 
                break;
                
			case "safari": // If specified browser is Safari
				webDriver = new SafariDriver();
				break;
      
			//default:
				//webDriver = new FirefoxDriver();
				// if user want to set firefox profile use 
				// webDriver = new FirefoxDriver(this.FirefoxDriverProfile());
			}

			// Maximize browser
			webDriver.manage().window().maximize();
			webDriver.manage().timeouts().implicitlyWait(Integer.parseInt(objConfig.getProperty("driver.implicitlyWait")), TimeUnit.SECONDS);
			webDriver.manage().timeouts().pageLoadTimeout(Integer.parseInt(objConfig.getProperty("driver.pageLoadTimeout")), TimeUnit.SECONDS);

			return webDriver;
		}
		catch (Exception exception) 
		{
			exception.printStackTrace();
			return null;
		}
	}

	/**
	 * @Method		: tearDownWebEnvironment
	 * @Description	: quit webdriver  
	 * @author	    : Automation Tester (SQS) 
	 */
	public void tearDownWebEnvironment(Properties objConfig)
	{
		try 
		{
			for (String handle : webDriver.getWindowHandles()) 
			{
				webDriver.switchTo().window(handle);
				webDriver.close();
				//webDriver.quit();
				
				webDriver.manage().deleteAllCookies();
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			if(objConfig.getProperty("web.browser").equalsIgnoreCase("IE") || objConfig.getProperty("web.browser").equalsIgnoreCase("Chrome")|| objConfig.getProperty("web.browser").equalsIgnoreCase("Firefox"))
				killBrowserAndDriver(objConfig);
		}
	}

	/**
	 * @Method		: killBrowserAndDriver
	 * @Description	: this method close the web browser    
	 * @author	    : Automation Tester (SQS)  
	 */
	protected void killBrowserAndDriver(Properties objConfig) 
	{
		String browser = objConfig.getProperty("web.browser").trim();
		String browserProcess = "";
		String driverProcess = ""; 

		if (!browser.equals("") && browser.equalsIgnoreCase("IE")) 
		{
			browserProcess = "iexplore";
			driverProcess = "IEDriverServer.exe";
		}
		else if (!browser.equals("") && browser.equalsIgnoreCase("Chrome")) 
		{
			browserProcess = browser;
			driverProcess = "chromedriver.exe";
		}
		else if(!browser.equals("") && browser.equalsIgnoreCase("Firefox")) 
		{
			browserProcess = browser;
			driverProcess = "geckodriver.exe";
		}
		try 
		{
			Process procDriver = Runtime.getRuntime().exec("taskkill /F /T /IM "+ driverProcess);
			Process procIE = Runtime.getRuntime().exec("taskkill /F /T /IM "+ browserProcess + ".exe");
			procDriver.waitFor();
			procIE.waitFor();
		}
		catch (Exception exception) 
		{
			exception.printStackTrace();
		}
	}

	/**
	 * @Method		: 	FirefoxDriverProfile()
	 * @Description	:  	Set Firefox profile
	 * @return 		: 	profile object 
	 * @author	    : Automation Tester (SQS) 
	 */
	public FirefoxProfile FirefoxDriverProfile(Properties objConfig) 
	{
		try
		{
			
			FirefoxProfile profile = new FirefoxProfile();
			/*String downloadFilesLocation = System.getProperty("user.dir") + objConfig.getProperty("downloads.path");
			new File(downloadFilesLocation).mkdirs();
			profile.setPreference("browser.download.folderList", 2);
			profile.setPreference("browser.download.dir", downloadFilesLocation);
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
			profile.setPreference("pdfjs.disabled", true);
			profile.setPreference("plugin.scan.plid.all", false);
			profile.setPreference("plugin.scan.Acrobat", "99.0");
			profile.setAcceptUntrustedCertificates(true);
			profile.setPreference("xpinstall.signatures.required",false);
		*/
			return profile;
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			return null;
		}
	}

}
