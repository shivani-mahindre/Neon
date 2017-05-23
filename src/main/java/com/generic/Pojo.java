package com.generic;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.util.Hashtable;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.adobe.genie.genieCom.SWFApp;

/**
 * @ScriptName    : Pojo
 * @Description   : This class will used to set common properties 
 * 					like webdriver and properties files.
 * @Author	    : Automation Tester (SQS)
 */
public class Pojo 
{
	private WebDriver webDriver;
	private AppiumDriver<?> appiumDriver;
	private AndroidDriver<?> androidDriver;
	private IOSDriver<?> iOSDriver;
	private WebDriverWait webDriverWait;
	private WebDriverWait appiumDriverWait;
	private Properties objConfig;
	private Hashtable<String , String> dataPoolHashTable;
	private Utilities objUtilities;
	private WrapperFunctions objWrapperFunctions;
	private CustomReporter objReporter;
	private String runningScript = "";
	private String strTestDataFilePath = "";
	private String testCaseID = "";
	private String runID = "",url="";
	private SWFApp app;
	private String user,Machine,PMAPassword,user1Value,user2Value,pass,testName,NeonUser,NeonPassword; 
	
	// Getter Setter for WebDriver object instance
	public void setDriver(WebDriver webDriver)
	{
		this.webDriver = webDriver;
	}

	public WebDriver getDriver()
	{
		return webDriver;
	}

	// Getter Setter for Appium Driver object instance
	public void setAppiumDriver(AppiumDriver<?> appiumDriver)
	{
		this.appiumDriver = appiumDriver;
	}

	public AppiumDriver<?> getAppiumDriver()
	{
		return appiumDriver;
	}

	// Getter Setter for Android Driver object instance
	public void setAndroidDriver(AndroidDriver<?> androidDriver)
	{
		this.androidDriver = androidDriver;
	}

	public AndroidDriver<?> getAndroidDriver()
	{
		return androidDriver;
	}

	// Getter Setter for IOS Driver object instance
	public void appium(IOSDriver<?> iOSDriver)
	{
		this.iOSDriver = iOSDriver;
	}

	public IOSDriver<?> getIOSDriver()
	{
		return iOSDriver;
	}

	// Getter Setter for Test Data File Path
	public String getStrTestDataFilePath()
	{
		return strTestDataFilePath;
	}

	public void setStrTestDataFilePath(String strTestDataFilePath)
	{
		this.strTestDataFilePath = strTestDataFilePath;
	}

	// Getter Setter for Wait object instance for web driver
	public void setWebDriverWait(WebDriverWait webDriverWait)
	{
		this.webDriverWait = webDriverWait;
	}

	public WebDriverWait getWebDriverWait()
	{
		return webDriverWait;
	}

	// Getter Setter for Wait object instance for appium driver
	public void setAppiumDriverWait(WebDriverWait appiumDriverWait)
	{
		this.appiumDriverWait = appiumDriverWait;
	}

	public WebDriverWait getAppiumDriverWait()
	{
		return appiumDriverWait;
	}
 
	// Getter Setter for Config object instance
	public void setObjConfig(Properties objConfig)
	{
		this.objConfig = objConfig;
	}

	public Properties getObjConfig()
	{
		return objConfig;
	}

	//  Getter Setter for DataPoolHashtable object instance
	public void setDataPoolHashTable(Hashtable<String, String> dataPoolHashTable)
	{
		this.dataPoolHashTable = dataPoolHashTable;
	}

	public Hashtable<String, String> getDataPoolHashTable()
	{
		return dataPoolHashTable;
	}

	// Getter Setter for Running Script Name
	public void setRunningScriptName(String scriptName)
	{
		this.runningScript = scriptName;
	}

	public String getRunningScriptName()
	{
		return runningScript;
	}

	// Getter Setter for Custom Reporter object instance
	public void setCustomeReporter(CustomReporter reporter)
	{
		this.objReporter = reporter;
	}

	public CustomReporter getCustomeReporter()
	{
		return objReporter;
	}

	// Getter Setter for Utilities object instance
	public Utilities getObjUtilities() 
	{
		return objUtilities;
	}

	public void setObjUtilities(Utilities objUtilities) 
	{
		this.objUtilities = objUtilities;
	}

	// Getter Setter for Wrapper function object instance
	public WrapperFunctions getObjWrapperFunctions() 
	{
		return objWrapperFunctions;
	}

	public void setObjWrapperFunctions(WrapperFunctions objWrapperFunctions)
	{
		this.objWrapperFunctions = objWrapperFunctions;
	}

	// Getter Setter for Test Case ID 
	public String getTestCaseID() 
	{
		return testCaseID;
	}

	public void setTestCaseID(String testCaseID) 
	{
		this.testCaseID = testCaseID;
	}

	// Getter Setter for RunID
	public String getRunID() 
	{
		return runID;
	}

	public void setRunID(String runID) 
	{
		this.runID = runID;
	}
	public void setWait(WebDriverWait wait)
	{
	}
	public void setApp1(SWFApp app) 
	{
		this.app = app;
	}
	
	public SWFApp getApp1() 
	{
		return app;
	}

	public void setUser(String user)
	{
		this.user = user;
	}
	
	public String getUser() {
		return user;
	}
	public void setPass(String pass)
	{
		this.pass = pass;
	}
	
	public String getPass() 
	{
		return pass;
	}
	
	public void setPAMPassword(String PMAPassword)
	{
	    this.PMAPassword = PMAPassword;
	}
	public String getPAMPassword()
	{
	    return PMAPassword;
	}
	public void setUser1Values(String user1Value)
	{
	    this.user1Value = user1Value;
	}
	public String getUser1Values()
	{
	return user1Value;
	}
	
	
	public void setUser2Values(String user2Value)
	{
	    this.user2Value = user2Value;
	}
	
	public String getUser2Values()
	{
	return user2Value;
	}

	public void setWebUrl(String url)
	{
	    this.url = url;
	}
	
	public String getWebUrl()
	{
	return url;
	}

	public void setTestTestName(String testName)
	{
		this.testName=testName;
	}
	
	public String getTestTestName()
	{
		return testName;
		
	}
	

	public void setNeonUser(String NeonUser)
	{
		this.NeonUser = NeonUser;
	}
	
	public String getNeonUser() {
		return NeonUser;
	}
	public void setNeonPassword(String NeonPassword)
	{
	    this.NeonPassword = NeonPassword;
	}
	public String geNeonPassword()
	{
	    return NeonPassword;
	}
	public void setMachine(String Machine)
	{
		this.Machine = Machine;
	}
	
	public String getMachine() {
		return Machine;
	}

}
