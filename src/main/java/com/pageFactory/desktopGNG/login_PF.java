package com.pageFactory.desktopGNG;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.Screen;

import com.generic.ExcelIterator;
import com.generic.Pojo;
import com.generic.Utilities;
import com.generic.WrapperFunctions;

public class login_PF
{
	private Utilities objUtilities;
	private WrapperFunctions objWrapperFunctions;

	WebDriver webdriver;
	Properties objConfig=new Properties();
	String user="",message="";
	String NeonUser,NeonPass;
	Screen objScreen;
	static String refId_FE="";
	
	public login_PF(Pojo pojo)
	{
		objUtilities = pojo.getObjUtilities();
		objWrapperFunctions = pojo.getObjWrapperFunctions();
		pojo.getObjConfig();
		NeonUser=pojo.getNeonUser();
		NeonPass=pojo.geNeonPassword();	
		
	}
	
	private By userName=By.id("Username");
	private By password=By.id("Password");
	private By signBtn=By.xpath("//button[@class='btn btn-lg btn-primary btn-block ig-signin-button']");
	private By okBtn=By.xpath("//button[@class='btn btn-default'][contains(.,'OK')]");
	private By usernmLink=By.xpath("//span[@class='readonlytext ig-header-text'][contains(.,'rankqa')]");
	private By logout=By.xpath("//li[@id='linkUserLogOut']//a[contains(.,'Log Out')]");
	
	public void login()
	{
		try 
		{
		objUtilities.logReporter("Enter username", objWrapperFunctions.setText(userName, NeonUser), false);
		objUtilities.logReporter("Enter password", objWrapperFunctions.setText(password,NeonPass), false);
		objUtilities.logReporter("Click on signBtn", objWrapperFunctions.click(signBtn), false);
		Thread.sleep(15000);
		objWrapperFunctions.pressEnterBtn(okBtn);
		objWrapperFunctions.pressEnterBtn(okBtn);
		objWrapperFunctions.pressEnterBtn(okBtn);
		objWrapperFunctions.pressEnterBtn(okBtn);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void logout()
	{
		objUtilities.logReporter("Click on username link", objWrapperFunctions.click(usernmLink), false);
		objWrapperFunctions.verifyIsElementPresent(logout);
		objUtilities.logReporter("Click on username link", objWrapperFunctions.click(logout), false);
	
	}
}
