package com.pageFactory.desktopGNG;

import java.io.IOException;
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

public class neonReg_PF
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
	
	public neonReg_PF(Pojo pojo){
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
	private By customerTab=By.xpath("//li[@id='CustomerManagement_Menu']//a[contains(.,'Customers')]");
	private By registrationTab=By.xpath("//a[@id='CustomerManagement_Registration_Menu']");
	private By customersSubTab=By.xpath("//a[@id='CustomerManagement_CustomerMaintenance_Menu']");
	private By customerName=By.xpath("//input[@name='Customer.Name']");
	private By gender=By.xpath("//input[@name='Customer.Gender']");
	private By titleDropdown=By.id("dropDownTitle");
	private By foreName=By.xpath("//input[@name='Customer.Forename']");
	private By dob=By.xpath("//div[@class='bootstrap-calendar calendar-container']//div//input");
	private By streetInput=By.xpath("//textarea[@name='PrimaryAddress.Street']");
	private By cityInput=By.xpath("//input[@name='PrimaryAddress.City']");
	private By cardNumber=By.xpath("//div//input[@id='textBoxCardNumber']");
	private By saveBtn=By.xpath("//div//button[@class='btn-primary btn-secured'][contains(.,'Save')]");	
	private By noMatchBtn=By.xpath("//div//a[@class='ersatzButton primary btn-secured'][contains(.,'No Match')]");
	private By customerNo=By.xpath("//div//input[@name='Customer.Number']");
	private By serchInput=By.xpath("//div//input[@name='Number']");	
	private By findBtn=By.xpath("//div//button[@class='btn-primary buttonFind btn-secured']");
	private By cashDeskTab=By.xpath("//li[@id='Cashdesk_Menu']//a[contains(.,'Cash Desk')]");
	private By depositTab=By.xpath("//li[@id='Cashdesk_Deposits_Menu']//a[contains(.,'Deposits')]");
	private By depositOnTab=By.xpath("//ul//li//a[@id='TasksSidebar_DepositOn_98002'][contains(.,'Deposit On')]");
	private By customerNoDeposit=By.xpath("//div//input[@name='CustomerNumber']");
	private By amountInput=By.xpath("//div//input[@name='Amount']");
	private By cashCheckbox=By.xpath("//div//input[@name='CashExchange']");
	private By yesBtn=By.xpath("//button[@class='btn btn-default'][contains(.,'Yes')]");
	private By mesg=By.xpath("//button[@class='btn btn-default'][contains(.,'Yes')]");
	private By balance=By.xpath("//div//span[@id='labelCurrentBalance']");
	private By depositOffTab=By.xpath("//ul//li//a[@id='TasksSidebar_DepositOff_98001'][contains(.,'Deposit Off')]");	
	private By reportTab=By.xpath("//li[@id='Reports_Menu']//a[contains(.,'Reports')]");
	private By attendenceTab=By.xpath("//li[@id='Reports_AttendanceReports_Menu']//a[contains(.,'Attendance')]");
	private By attendenceSummaryTab=By.xpath("//a[@id='AttendanceReports_AttendanceSummaryReport_Menu']");	
	private By export=By.xpath("//button[@id='buttonExport']");
	private By exportToCSV=By.xpath("//ul[@class='dropdown-menu']//li//a[@id='exportToCSV']");
	
	
//Registration on Neon System
	public void registration()
	{
		try
		{	
		objUtilities.logReporter("Enter username", objWrapperFunctions.setText(userName, NeonUser), false);
		objUtilities.logReporter("Enter password", objWrapperFunctions.setText(password,NeonPass), false);
		objUtilities.logReporter("Click on signBtn", objWrapperFunctions.click(signBtn), false);
		Thread.sleep(10000);
		//objWrapperFunctions.waitForElementPresence(okBtn);
		objWrapperFunctions.acceptMultipleAlert(okBtn);
	//	objWrapperFunctions.pressEnterBtn(okBtn);
		//objWrapperFunctions.pressEnterBtn(okBtn);
		//objWrapperFunctions.pressEnterBtn(okBtn);
		Thread.sleep(2000);
		System.out.println("****************** Registration ******************");
		objUtilities.logReporter("Click on customer tab", objWrapperFunctions.click(customerTab), false);
		objUtilities.logReporter("Click on registartion tab", objWrapperFunctions.click(registrationTab), false);	
		custName=objWrapperFunctions.getName();
	//	System.out.println(custName);
		objUtilities.logReporter("Enter username", objWrapperFunctions.setText(customerName,custName), false);
		objWrapperFunctions.pressTabBtn(customerName);
		objUtilities.logReporter("Click on customer tab", objWrapperFunctions.click(titleDropdown), false);
		objUtilities.logReporter("Click on customer tab", objWrapperFunctions.selectDropDownByRandomIndex(titleDropdown, 8,1), false);	
		//objUtilities.logReporter("Click on customer tab", objWrapperFunctions.selectDropDownByIndex(titleDropdown,1), false);	
		objUtilities.logReporter("Enter forename", objWrapperFunctions.setText(foreName, "SQS"), false);
		date=objWrapperFunctions.getRandomDate();
		conDate=objUtilities.getFormatedDate(date,"ddmmyyyy","dd/mm/yyyy");
		System.out.println("Date of Birth = " +conDate);
		objUtilities.logReporter("Enter DateOfBirth", objWrapperFunctions.setText(dob,date), false);
		objUtilities.logReporter("select gender", objWrapperFunctions.click(gender), false);	
		objUtilities.logReporter("Enter street", objWrapperFunctions.setText(streetInput, "Stanley Park Drive"), false);
		objUtilities.logReporter("Enter City", objWrapperFunctions.setText( cityInput, "WEMBLEY"), false);	
		objUtilities.logReporter("Click on card number", objWrapperFunctions.click(cardNumber), false);
		cardnum=objWrapperFunctions.getRandomNumber();
		System.out.println("Card Number=" +cardnum);
		objUtilities.logReporter("Enter street", objWrapperFunctions.setText(cardNumber,cardnum), false);
		objUtilities.logReporter("Click on Save btn", objWrapperFunctions.click(saveBtn), false);	
		if(objWrapperFunctions.checkElementDisplyed(noMatchBtn))
		{
			objUtilities.logReporter(" click on No Match Btn", objWrapperFunctions.click(noMatchBtn), false);
			Thread.sleep(10000);
			if(objWrapperFunctions.checkElementDisplyed(noMatchBtn))
			{
			objUtilities.logReporter(" click on No Match Btn", objWrapperFunctions.doubleClick(noMatchBtn), false);
			}
		}
		
		if(objWrapperFunctions.checkElementDisplyed(yesBtn))
		{
			objUtilities.logReporter(" click on yesBtn", objWrapperFunctions.click(yesBtn), false);
			objUtilities.logReporter(" click on ok Btn", objWrapperFunctions.click(okBtn), false);
		}	
		
		 System.out.println("Customer registered  successfully ");
		 refId_FE=objWrapperFunctions.getAttributeValue(customerNo,"value");
		 System.out.println("Customer Number of Registered user =" +refId_FE);
			objExcel.createCustomerExcel(refId_FE, cardnum,conDate);
		 
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
	
	//Perform Deposit On 
	public void depositOn()
	{	
		try{
		Thread.sleep(4000);
		System.out.println("***************Performing Deposit On Functionality ***************");
		System.out.println("Customer Number=" +refId_FE);
		objUtilities.logReporter("Click on Deposit On tab", objWrapperFunctions.click(depositOnTab), false);	
		objUtilities.logReporter("Enter Customer Number", objWrapperFunctions.setText(customerNoDeposit,refId_FE), false);
		objWrapperFunctions.pressTabBtn(customerNoDeposit);
		Thread.sleep(3000);
		message=objWrapperFunctions.getText(balance);
		System.out.println("Account balance before deposit = "+message);		
		objUtilities.logReporter("Click on amount textbox", objWrapperFunctions.click(amountInput), false);
		objUtilities.logReporter("Click on amount textbox", objWrapperFunctions.clear(amountInput), false);
		objUtilities.logReporter("Enter amount", objWrapperFunctions.setText(amountInput,"10"), false);
		System.out.println("Deposit 10 amount as cash value ");
		objUtilities.logReporter("Click on Save btn", objWrapperFunctions.click(cashCheckbox), false);
		objUtilities.logReporter("Click on Save btn", objWrapperFunctions.click(saveBtn), false);	
			if(objWrapperFunctions.checkElementDisplyed(yesBtn))
			{
				objUtilities.logReporter(" click on yesBtn", objWrapperFunctions.click(yesBtn), false);
				if(objWrapperFunctions.checkElementDisplyed(yesBtn))
				{
				objUtilities.logReporter(" click on yes Btn", objWrapperFunctions.click(yesBtn), false);
				}
			}		
			objWrapperFunctions.pressEnterBtn(okBtn);
			message=objWrapperFunctions.getText(balance);
			System.out.println("Account balance after deposit = "+message);
			System.out.println("Transaction Completed successfully");
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Perform deposit off
	public void depositOff()
	{	
		try{
		Thread.sleep(4000);
		System.out.println("***************Performing Deposit Off Functionality ***************");
		System.out.println("Customer Number=" +refId_FE);
		objUtilities.logReporter("Click on Deposit Off tab", objWrapperFunctions.click(depositOffTab), false);	
		objUtilities.logReporter("Enter Customer Number", objWrapperFunctions.setText(customerNoDeposit,refId_FE), false);		
		objWrapperFunctions.pressTabBtn(customerNoDeposit);
		Thread.sleep(3000);
		message=objWrapperFunctions.getText(balance);
		System.out.println("Account balance before deposit = "+message);		
		objUtilities.logReporter("Click on amount textbox", objWrapperFunctions.click(amountInput), false);
		objUtilities.logReporter("Click on amount textbox", objWrapperFunctions.clear(amountInput), false);
		objUtilities.logReporter("Enter amount", objWrapperFunctions.setText(amountInput,"5"), false);
		System.out.println("Deposit off 5 amount as cash value ");
		objUtilities.logReporter("Click on Save btn", objWrapperFunctions.click(cashCheckbox), false);
		objUtilities.logReporter("Click on Save btn", objWrapperFunctions.click(saveBtn), false);	
			if(objWrapperFunctions.checkElementDisplyed(yesBtn))
			{
				objUtilities.logReporter(" click on yesBtn", objWrapperFunctions.click(yesBtn), false);
				if(objWrapperFunctions.checkElementDisplyed(yesBtn))
				{
				objUtilities.logReporter(" click on yes Btn", objWrapperFunctions.click(yesBtn), false);
				}
			}		
			objWrapperFunctions.pressEnterBtn(okBtn);
			message=objWrapperFunctions.getText(balance);
			System.out.println("Account balance after deposit = "+message);
			System.out.println("Transaction Completed successfully");
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	public void serchUser()
	{
		try
		{
		System.out.println("Customer Number=" +refId_FE);
		objUtilities.logReporter("Click on customer tab", objWrapperFunctions.click(customerTab), false);	
		Thread.sleep(8000);
		objUtilities.logReporter("Click on customer tab", objWrapperFunctions.click(customersSubTab), false);
		Thread.sleep(8000);
		objUtilities.logReporter("Enter street", objWrapperFunctions.setText(serchInput,refId_FE), false);
		objUtilities.logReporter("Click on customer tab", objWrapperFunctions.click(findBtn), false);
		Thread.sleep(8000);
		
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	public void report()
	{
		try
		{
				
		objUtilities.logReporter("Click on customer tab", objWrapperFunctions.click(reportTab), false);	
		Thread.sleep(8000);
		objUtilities.logReporter("Click on customer tab", objWrapperFunctions.click(attendenceTab), false);
		Thread.sleep(3000);
		objUtilities.logReporter("Click on customer tab", objWrapperFunctions.click(attendenceSummaryTab), false);
		
		Thread.sleep(3000);
		objUtilities.logReporter("Click on customer tab", objWrapperFunctions.click(export), false);
		Thread.sleep(3000);
		objUtilities.logReporter("Click on customer tab", objWrapperFunctions.click(exportToCSV), false);
		Thread.sleep(15000);
		Runtime.getRuntime().exec("C:/SQS_Automation_Trinity_Framework_Neon/AutoIt3/saveCsv.exe");	
		
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	//	objWrapperFunctions.switchToChildWindow();
		//objWrapperFunctions.switchToChildWindow();
		//objWrapperFunctions.switchToChildWindow();
		//objWrapperFunctions.pressTabBtn();
		 catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
}
