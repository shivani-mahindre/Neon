package com.generic;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @ScriptName : BaseTest
 * @Description : This class will load all test data, load all objects,
 *              initialize web driver, start reports. Contains generic
 *              functionalities like open browser, login/logout
 * @author : Automation Tester (SQS)
 */
public class BaseTest extends Pojo
{
    // Local Variables
    public String userNAME;
    private WebDriver webDriver;
    private AppiumDriver<?> appiumDriver;
    private Properties objConfig;
    private WebDriverWait webDriverWait;
    private WebDriverWait appiumDriverWait;
    private Utilities objUtilities;
    private WrapperFunctions objWrapperFunctions;
    private CustomReporter objCustomReporter;
    private String testDataFilePath = "";
    private String callingClassName = "";
    public List<Hashtable<String, String>> hashtableList = new ArrayList<Hashtable<String, String>>();
    private AndroidDriver<?> androidDriver;
    private InitializeTearDownEnvironment objInitializeTearDownEnvironment;


    /* * @Method : initializeWebEnvironment
     * @Description : This method initialize web driver for web application by
     *              opening the browser and URL specified in config.properties
     *              file
     * @author : Automation Tester (SQS)
    */
    public void initializeWebEnvironment(String brand) 
    {
    	this.loadConfigProperties();
    	String user="";
    	user=objConfig.getProperty("web.CustomerNumber");
    	this.setUser(user);
    	String machine=objConfig.getProperty("web.machine");
    	this.setMachine(machine);
    	String NeonUser = objConfig.getProperty("web.NeonUsername"); 
    	this.setNeonUser(NeonUser);
    	
    	String NeonPassword = objConfig.getProperty("web.NeonPassword");
    	this.setNeonPassword(NeonPassword);
    	
    	callingClassName = this.getClass().getName();
    	this.setRunningScriptName(callingClassName);
    	System.out.println("ScriptName : " + callingClassName);

    	objInitializeTearDownEnvironment = new InitializeTearDownEnvironment();
    	webDriver = objInitializeTearDownEnvironment.initializeWebEnvironment(objConfig);

    	this.setDriver(webDriver);
    	webDriverWait = new WebDriverWait(webDriver, Integer.parseInt(objConfig.getProperty("driver.WebDriverWait")));
    	this.setWebDriverWait(webDriverWait);

    	objCustomReporter = new CustomReporter();
    	if (objConfig.getProperty("test.custom.reporter").equalsIgnoreCase("true"))
    	    this.startCustomReports();

    	objUtilities = new Utilities(this);
    	this.setObjUtilities(objUtilities);

    	objWrapperFunctions = new WrapperFunctions(this);
    	this.setObjWrapperFunctions(objWrapperFunctions);

    	// Open url
    	webDriver.get(objConfig.getProperty("Neon.Url"));
    	// return user;
        }
   
 /*   
    
   public void initializeWebEnvironment(String brand) 
    {
	this.loadConfigProperties();
	callingClassName = this.getClass().getName();
	System.out.println("ScriptName : " + callingClassName);

	setRunningScriptName(callingClassName);
	String user ="",pass="";
	if(brand.equals("MB"))
	{	
		user=objConfig.getProperty("web.username_mb");
		this.setUser(user);
		System.out.println(user);
		pass=objConfig.getProperty("web.password_mb");
		this.setPass(pass);
		System.out.println(pass);
	}
	else
	{
		 user=objConfig.getProperty("web.username");
		 this.setUser(user);
		 pass=objConfig.getProperty("web.password");
		 this.setPass(pass);
	}
	
	//setRunningScriptName(callingClassName);
	
	objInitializeTearDownEnvironment = new InitializeTearDownEnvironment();
	webDriver = objInitializeTearDownEnvironment.initializeWebEnvironment(objConfig);

	this.setDriver(webDriver);

	webDriverWait = new WebDriverWait(webDriver, Integer.parseInt(objConfig.getProperty("driver.WebDriverWait")));
	this.setWebDriverWait(webDriverWait);

	objCustomReporter = new CustomReporter();
	if (objConfig.getProperty("test.custom.reporter").equalsIgnoreCase("true"))
	this.startCustomReports();

	objUtilities = new Utilities(this);
	this.setObjUtilities(objUtilities);

	objWrapperFunctions = new WrapperFunctions(this);
	this.setObjWrapperFunctions(objWrapperFunctions);
	
	webDriver.get(objConfig.getProperty("Neon.Url"));
	
    }
    
   */
    
    
    /**
     * @Method : tearDownEnvironment
     * @Description : quit webdriver
     * @author : Harshvardhan Yadav (SQS)
     */
    public void tearDownWebEnvironment() {
    try
    {
	if (objConfig.getProperty("test.custom.reporter").equalsIgnoreCase("true"))
	    objCustomReporter.endReport();
	objInitializeTearDownEnvironment.tearDownWebEnvironment(objConfig);
	objUtilities = null;
	objWrapperFunctions = null;
    }
    catch(Exception e)
    {
    	e.printStackTrace();
    	
    }
    }


    /**
     * @Method : loadTestData
     * @param : runID - test case run id
     * @param : dataSet - test data hash table
     * @Description : Load data from excel for the running testCase and return
     *              as Object array
     * @author : Automation Tester (SQS)
     */
    public void loadTestData(String runID, Hashtable<String, String> dataSet) {
	this.setRunID(runID);
	this.setDataPoolHashTable(dataSet);
	objUtilities = new Utilities(this);
	this.setObjUtilities(objUtilities);

	objWrapperFunctions = new WrapperFunctions(this);
	this.setObjWrapperFunctions(objWrapperFunctions);
    }

    /**
     * @Method : loadDataProvider
     * @param : testCaseID - test case id
     * @param : testDataFile - test data file
     * @Description : Load Data from Excel for the running testCase and return
     *              as Object array
     * @author : Automation Tester (SQS)
     */
    public Object[][] loadDataProvider(String testCaseID, String testDataFile) {
	Object[][] dataPool = null;

	if (!testDataFile.equals("") && !testCaseID.equals("")) {
	    testDataFilePath = System.getProperty("user.dir") + objConfig.getProperty("testdata.path") + "/"
		    + testDataFile + ".xlsx";
	    this.setTestCaseID(testCaseID);
	    this.setStrTestDataFilePath(testDataFilePath);

	    DataPool objDataPool = new DataPool();
	    dataPool = objDataPool.loadTestData(testCaseID, testDataFilePath);
	}
	return dataPool;
    }

    /**
     * @Method : loadConfigProperties
     * @Description : load config properties
     * @author : Automation Tester (SQS)
     */
    public void loadConfigProperties() {
	try {
	    objConfig = new Properties();
	    objConfig
		    .load(new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties"));
	    this.setObjConfig(objConfig);
	} catch (Exception exception) {
	    exception.printStackTrace();
	}
    }

    /**
     * startCustomeReports - this method starts the custome report
     * 
     * @author : Automation Tester (SQS)
     */
    public void startCustomReports() {
	setCustomeReporter(objCustomReporter);
	objCustomReporter.startReport(callingClassName);
    }

    public void closeBrowser() {
	try {
	    for (String handle : webDriver.getWindowHandles()) {
		webDriver.switchTo().window(handle);
		webDriver.quit();
		System.out.println("Closing browser again");
	    }
	} catch (Exception exception) {
	    exception.printStackTrace();
	    if (objConfig.getProperty("web.browser").equalsIgnoreCase("IE")
		    || objConfig.getProperty("web.browser").equalsIgnoreCase("Chrome")
		    || objConfig.getProperty("web.browser").equalsIgnoreCase("Firefox"))
		killBrowserAndDriver();
	}
    }

    protected void killBrowserAndDriver() {
	String browser = objConfig.getProperty("web.browser").trim();
	String driverProcess = "";

	if (!browser.equals("") && browser.equalsIgnoreCase("IE")) {
	    driverProcess = "IEDriverServer.exe";
	} else if (!browser.equals("") && browser.equalsIgnoreCase("Chrome")) {
	    driverProcess = "chromedriver.exe";
	} else if (!browser.equals("") && browser.equalsIgnoreCase("Firefox")) {
	    driverProcess = "firefox.exe";
	}
	try {
	    // Process procDriver =
	    // Runtime.getRuntime().exec("taskkill /F /T /IM "+ driverProcess);
	    Process procIE = Runtime.getRuntime().exec("taskkill /im" + driverProcess);
	    // procDriver.waitFor();
	    procIE.waitFor();
	} catch (Exception exception) {
	    exception.printStackTrace();
	}
    }
    
}