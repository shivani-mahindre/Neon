package com.pageFactory.desktopNeon;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.Screen;

import com.generic.Pojo;
import com.generic.Utilities;
import com.generic.WrapperFunctions;

public class Reception_Entry_PF {

	private Utilities objUtilities;
	private WrapperFunctions objWrapperFunctions;
	WebDriver webdriver;
	Properties objConfig=new Properties();
	String custNumber="",user="",message="";
	String NeonUser,NeonPass;
	Screen objScreen;
	static String refId_FE="";
	
	public Reception_Entry_PF(Pojo pojo){
		objUtilities = pojo.getObjUtilities();
		objWrapperFunctions = pojo.getObjWrapperFunctions();
		pojo.getObjConfig();
		custNumber=pojo.getUser();
		
	}
	
	private By customerTab=By.xpath("//li[@id='CustomerManagement_Menu']//a[contains(.,'Customers')]");
	private By receptionTab=By.xpath("//a[@id='CustomerManagement_Reception_Menu']");
	private By visitorCount=By.xpath("//div[@id='divCustomersCount']");
	private By findInputBox=By.xpath("//input[@name='SearchCriteria']");
	private By okButton=By.xpath("//footer//button[contains(.,'OK')]");
	private By text=By.xpath("//div[@class='modal-body ig-modal-scroll']");
	private By infoMsg=By.xpath("//div[@id='receptionInfo']");
	//private By okBtn=By.xpath("//button[@class='btn btn-default'][contains(.,'OK')]");	
	private By okBtn=By.xpath("//button[contains(.,'OK')]");	
	private By yesBtn=By.xpath("//button[contains(.,'Yes')]");
	private By noBtn=By.xpath("//button[contains(.,'No')]");
	private By customerNumber=By.xpath("//input[@name='Number']");
	private By custName=By.xpath("//input[@name='Name']");
	private By dob=By.xpath("//input[@name='DateOfBirth']");
	private By cancelButton=By.xpath("//div//footer//button[contains(.,'Cancel')]");
	private By referenceInput=By.xpath("//div//input[@id='Reference']");
	private By detailsInput=By.xpath("//div//input[@id='IssueDetail']");
	private By commit=By.xpath("//div//footer//button[contains(.,'Commit')]");
	
	public void customerEntry()
	{
		try
		{	
		Thread.sleep(2000);	
		objUtilities.logReporter("Click on Customer tab", objWrapperFunctions.click(customerTab), false);
		objUtilities.logReporter("Click on Reception tab", objWrapperFunctions.click(receptionTab), false);	
		String visitor=objWrapperFunctions.getText(visitorCount);
		System.out.println("No of visitors in the Casino =" +visitor);
		objUtilities.logReporter("Enter CustomerNumber", objWrapperFunctions.setText(findInputBox,custNumber), false);
		objWrapperFunctions.pressTabBtn(findInputBox);
		objUtilities.logReporter("Click on 'Cancel' button", objWrapperFunctions.click(cancelButton), false);
		objUtilities.logReporter("Click on 'No' button", objWrapperFunctions.click(noBtn), false);
		String vistCnt=objWrapperFunctions.getText(visitorCount);
		if(vistCnt.equals(visitor))
		{ System.out.println("After rejecting log visit ,count of visitors is same as before ");}	
		objUtilities.logReporter("Enter CustomerNumber", objWrapperFunctions.setText(findInputBox,custNumber), false);
		objWrapperFunctions.pressTabBtn(findInputBox);
		objUtilities.logReporter("Enter Reference ", objWrapperFunctions.setText(referenceInput,"test"), false);
		objUtilities.logReporter("Enter details", objWrapperFunctions.setText(detailsInput,"test"), false);
		objUtilities.logReporter("Click on 'Cancel' button", objWrapperFunctions.click(commit), false);
		objUtilities.logReporter("Click on 'Yes' button", objWrapperFunctions.click(yesBtn), false);
		//System.out.println("****************Customer Details**********************");
		message=objWrapperFunctions.getAttributeValue(customerNumber, "value");
		System.out.println("Number :" +message);
		message=objWrapperFunctions.getAttributeValue(custName,"value");
		System.out.println("Customer Name :" +message);
		message=objWrapperFunctions.getAttributeValue(dob,"value");
		System.out.println("Date of Birth :" +message);
		user=objWrapperFunctions.getText(infoMsg);
		System.out.println("Receptionist received Message  :"+user);	
		//System.out.println(" Verify No of visitors in the Casino remains as before =" +visitor);
		}catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	
	
	public void customerEntryUsingName()
	{
		try
		{	
		Thread.sleep(2000);	
		objUtilities.logReporter("Click on Customer tab", objWrapperFunctions.click(customerTab), false);
		objUtilities.logReporter("Click on Reception tab", objWrapperFunctions.click(receptionTab), false);	
		String visitor=objWrapperFunctions.getText(visitorCount);
		System.out.println("No of visitors in the Casino =" +visitor);
		objUtilities.logReporter("Enter CustomerNumber", objWrapperFunctions.setText(findInputBox,"09/03/1972"), false);
		objWrapperFunctions.pressTabBtn(findInputBox);
		objUtilities.logReporter("Click on 'Cancel' button", objWrapperFunctions.click(cancelButton), false);
		objUtilities.logReporter("Click on 'No' button", objWrapperFunctions.click(noBtn), false);
		String vistCnt=objWrapperFunctions.getText(visitorCount);
		System.out.println("Visitor count "+vistCnt);	
		objUtilities.logReporter("Enter CustomerNumber", objWrapperFunctions.setText(findInputBox,custNumber), false);
		objWrapperFunctions.pressTabBtn(findInputBox);
		objUtilities.logReporter("Click on 'Cancel' button", objWrapperFunctions.click(cancelButton), false);
		objUtilities.logReporter("Click on 'Yes' button", objWrapperFunctions.click(yesBtn), false);
		//System.out.println("****************Customer Details**********************");
		message=objWrapperFunctions.getAttributeValue(customerNumber, "value");
		System.out.println("Number :" +message);
		message=objWrapperFunctions.getAttributeValue(custName,"value");
		System.out.println("Customer Name :" +message);
		message=objWrapperFunctions.getAttributeValue(dob,"value");
		System.out.println("Date of Birth :" +message);
		user=objWrapperFunctions.getText(infoMsg);
		System.out.println("Receptionist received Message  :"+user);	
		//System.out.println(" Verify No of visitors in the Casino remains as before =" +visitor);
		}catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	public void suspendedCustomer()
	{
	try
	{	
	Thread.sleep(2000);	
	objUtilities.logReporter("Click on Customer tab", objWrapperFunctions.click(customerTab), false);
	objUtilities.logReporter("Click on Reception tab", objWrapperFunctions.click(receptionTab), false);	
//	objWrapperFunctions.wait();
	String visitor=objWrapperFunctions.getText(visitorCount);
	System.out.println("No of visitors in the Casino =" +visitor);
	objUtilities.logReporter("Enter CustomerNumber", objWrapperFunctions.setText(findInputBox, "1955149730"), false);
	objWrapperFunctions.pressTabBtn(findInputBox);
	objUtilities.logReporter("Click on ok tab", objWrapperFunctions.click(okButton), false);
	message=objWrapperFunctions.getText(text);
	System.out.println("Warning message is displayed as :" +message);
	objWrapperFunctions.pressEnterBtn(okBtn);
	user=objWrapperFunctions.getText(infoMsg);
	System.out.println("Receptionist received Message  :"+user);	
	
	}catch (InterruptedException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	
	
	public void lapsedCustomer()
	{
	try
	{	
	Thread.sleep(2000);	
	objUtilities.logReporter("Click on Customer tab", objWrapperFunctions.click(customerTab), false);
	objUtilities.logReporter("Click on Reception tab", objWrapperFunctions.click(receptionTab), false);	
//	objWrapperFunctions.wait();
	String visitor=objWrapperFunctions.getText(visitorCount);
	System.out.println("No of visitors in the Casino =" +visitor);
	objUtilities.logReporter("Enter CustomerNumber", objWrapperFunctions.setText(findInputBox, "1955149730"), false);
	objWrapperFunctions.pressTabBtn(findInputBox);
	objUtilities.logReporter("Click on ok tab", objWrapperFunctions.click(okButton), false);
	message=objWrapperFunctions.getText(text);
	System.out.println("Warning message is displayed as :" +message);
	objWrapperFunctions.pressEnterBtn(okBtn);
	user=objWrapperFunctions.getText(infoMsg);
	System.out.println("Receptionist received Message  :"+user);	
	
	}catch (InterruptedException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	}
