package com.pageFactory.CashierManagerTC;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.swing.text.DefaultEditorKit.CutAction;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.sikuli.script.Screen;

import com.generic.ExcelIterator;
import com.generic.Pojo;
import com.generic.Utilities;
import com.generic.WrapperFunctions;

public class BalancePage
{
	private Utilities objUtilities;
	private WrapperFunctions objWrapperFunctions;
	WebDriver webdriver;
	Properties objConfig=new Properties();
	String message="";
	String NeonUser,NeonPass;
	Screen objScreen;
	ExcelIterator objExcel=new ExcelIterator();
	static String refId_FE="",custName="",date="",cardnum="", conDate="";
	//static int cardnum;
	
	public BalancePage(Pojo pojo){
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

	//private By exportToCSV=By.xpath("//ul[@class='dropdown-menu']//li//a[@id='exportToCSV']");
	private By viewTab=By.cssSelector("div.ig-navbar-menu li#View_Menu");
	private By balanceMenu=By.cssSelector("div.ig-navbar-menu li#View_Menu div.container-accordion-inner ul li a#View_Balance_Menu");
	private By viewBalanceText=By.cssSelector("div.buttonbar div h2");
	private By getDetailsOnPage = By.cssSelector("div#main-content div.content.ig-old-layout.ig-host-key.ng-scope div#formBalances div#tblBalancing table tbody tr td");
	

	public void function_depositON(){
		

	}
	

	
	public void viewBalanceCheckDetails()
	{
		try
		{	
		objUtilities.logReporter("Enter username", objWrapperFunctions.setText(userName, NeonUser), false);
		objUtilities.logReporter("Enter password", objWrapperFunctions.setText(password,NeonPass), false);
		objUtilities.logReporter("Click on signBtn", objWrapperFunctions.click(signBtn), false);
		Thread.sleep(15000);
		objWrapperFunctions.waitForElementPresence(okBtn);
		objWrapperFunctions.acceptMultipleAlert(okBtn);
		
		//objWrapperFunctions.pressEnterBtn(okBtn);
		//objWrapperFunctions.pressEnterBtn(okBtn);
		Thread.sleep(2000);
		System.out.println("****************** Balance Details Verification ******************");
		objUtilities.logReporter("Click on view tab", objWrapperFunctions.click(viewTab), false);
		objUtilities.logReporter("Click on Balance Menu", objWrapperFunctions.click(balanceMenu), false);
		objUtilities.logReporter("Verify View Balance text", objWrapperFunctions.checkElementDisplyed(viewBalanceText), false);
		objUtilities.logReporter("Verify View Details text", objWrapperFunctions.checkElementDisplyed(getDetailsOnPage), false);
		System.out.println("Balance text: "+objWrapperFunctions.getText(viewBalanceText));
		//checkPresenceOfText(getDetailsOnPage);
		System.out.println(objWrapperFunctions.getText(getDetailsOnPage));
		
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//objExcel.createCustomerExcel(refId_FE, cardnum, date);
	}
	
	
	
	void checkPresenceOfText(By locator){
		List<WebElement> list= objWrapperFunctions.getElements(locator);
		Iterator<WebElement> iterator = list.iterator();
		String arrTemp[]=new String[list.size()];
		int i=0;
		while(iterator.hasNext()){
			arrTemp[i]=objWrapperFunctions.getTextOfElement(list.get(i));
					i++;
					System.out.println(arrTemp[i]);
		}
		
		
		
	}
	
	//Perform Deposit On 
	
	
	
	
	
	
	
	
	
	
	


	
	
	
	
	
	
}
