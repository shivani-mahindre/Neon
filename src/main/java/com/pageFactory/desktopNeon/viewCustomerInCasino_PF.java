package com.pageFactory.desktopNeon;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.Screen;

import com.generic.Pojo;
import com.generic.Utilities;
import com.generic.WrapperFunctions;

public class viewCustomerInCasino_PF {

	private Utilities objUtilities;
	private WrapperFunctions objWrapperFunctions;
	WebDriver webdriver;
	Properties objConfig=new Properties();
	String custNumber="",user="",message="";
	String NeonUser,NeonPass;
	Screen objScreen;
	static String refId_FE="";
	
	public viewCustomerInCasino_PF(Pojo pojo)
	{
		objUtilities = pojo.getObjUtilities();
		objWrapperFunctions = pojo.getObjWrapperFunctions();
		pojo.getObjConfig();
		custNumber=pojo.getUser();
	}
	
	
	private By viewsTab=By.xpath("//li//a//span//span[@class='ig-icon-text'][contains(.,'Views')]");
	private By custInCasinoTab=By.xpath("//li//a[@id='Views_CustomersInTheCasino_Sidebar'] ");
	private By custLists=By.xpath("//ul[@class='gallery']//li[1]//ul//li[1]");
	private By recordsCount=By.xpath("//div//div[@class='recordcount']//span[@class='ng-binding']");
	private By image=By.xpath("//ul[@class='gallery']//li//h4//span[@class='glyphicon glyphicon-user ng-scope']");
	private By noIdentificationCta=By.xpath("//div//span[contains(.,'No Identification')]");
	private By peopleInCasinoTab=By.xpath("//li//a[@id='Views_CustomersInTheCasinoList_Sidebar']");
	private By custNumber1=By.xpath("//form//div//fieldset//div[1]//span");
	private By toggleViewButton=By.xpath("//div//span[@class='toggleView GalleryView']");
	private By custDetails=By.xpath("//fieldset//div");
	
	public void viewCustomers()
	{
		objUtilities.logReporter("Click on views tab", objWrapperFunctions.click(viewsTab), false);
		objUtilities.logReporter("Click on People In Casino tab", objWrapperFunctions.click(custInCasinoTab), false);	
		String count=objWrapperFunctions.getText(recordsCount);
		System.out.println(count+ " customers in the Casino ");
		if(objWrapperFunctions.verifyIsElementPresent(image)==true)
		{
			objUtilities.logReporter("Click on shoulder image", objWrapperFunctions.click(image), false);
			objUtilities.logReporter("verify No Identification button is display ", objWrapperFunctions.verifyIsElementPresent(noIdentificationCta), false);
			
		}
		else
		{
			System.out.println(" ID required customers not avaliable  ");
		}
//		/objUtilities.logReporter("verify shoulder image is display", objWrapperFunctions.verifyIsElementPresent(image), false);
	
		objUtilities.logReporter("Click on List of People In Casino tab", objWrapperFunctions.click(peopleInCasinoTab), false);	
		objUtilities.logReporter("Click on List of People In Casino tab", objWrapperFunctions.click(toggleViewButton), false);		
		String count1=objWrapperFunctions.getText(recordsCount);
		System.out.println(count1 +" customers in the Casino ");
		objUtilities.logReporter("Click on list", objWrapperFunctions.click(custLists), false);
		String custnum=objWrapperFunctions.getText(custLists);
		System.out.println("****Customer Details are displyed ****");
		objWrapperFunctions.displayAllElement(custDetails);
		String custnum1=objWrapperFunctions.getText(custNumber1);
		objUtilities.logReporter("",custnum.contentEquals(custnum1), false);
	}
	
}
