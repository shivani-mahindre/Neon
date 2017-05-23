package com.pageFactory.desktopNeon;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.Screen;

import com.generic.Pojo;
import com.generic.Utilities;
import com.generic.WrapperFunctions;

public class DepositOnOff_PF
{

	private Utilities objUtilities;
	private WrapperFunctions objWrapperFunctions;
	WebDriver webdriver;
	Properties objConfig=new Properties();
	String custNumber="",message="";
	
	public DepositOnOff_PF(Pojo pojo)
	{
		objUtilities = pojo.getObjUtilities();
		objWrapperFunctions = pojo.getObjWrapperFunctions();
		custNumber=pojo.getUser();
		System.out.println("customerNumber->>"+custNumber);
	}

	private By okBtn=By.xpath("//button[@class='btn btn-default'][contains(.,'OK')]");	
	private By saveBtn=By.xpath("//div//button[@class='btn-primary btn-secured'][contains(.,'Save')]");	
	private By depositOnTab=By.xpath("//ul//li//a[@id='TasksSidebar_DepositOn_98001'][contains(.,'Deposit On')]");
	private By customerNoDeposit=By.xpath("//div//input[@name='CustomerNumber']");
	private By amountInput=By.xpath("//div//input[@name='Amount']");
	private By cashCheckbox=By.xpath("//div//input[@name='CashExchange']");
	private By yesBtn=By.xpath("//button[@class='btn btn-default'][contains(.,'Yes')]");
	private By balance=By.xpath("//div//span[@id='labelCurrentBalance']");
	private By depositOffTab=By.xpath("//ul//li//a[@id='TasksSidebar_DepositOff_98006'][contains(.,'SAW Withdrawal')]");	
	private By commentTextArea=By.xpath("//div//textarea[@name='Comment']");
	
	public void depositOn()
	{	
		try{
		Thread.sleep(5000);
		System.out.println("***************Performing Deposit On Functionality ***************");
		System.out.println("Customer Number=" +custNumber);
		objUtilities.logReporter("Click on Deposit On tab", objWrapperFunctions.click(depositOnTab), false);	
		objUtilities.logReporter("Enter Customer Number", objWrapperFunctions.setText(customerNoDeposit,custNumber), false);
		objWrapperFunctions.pressTabBtn(customerNoDeposit);
		Thread.sleep(3000);
		message=objWrapperFunctions.getText(balance);
		System.out.println("Account balance before deposit = "+message);		
		objUtilities.logReporter("Click on amount textbox", objWrapperFunctions.click(amountInput), false);
		objUtilities.logReporter("Click on amount textbox", objWrapperFunctions.clear(amountInput), false);
		objUtilities.logReporter("Enter amount", objWrapperFunctions.setText(amountInput,"10"), false);
		System.out.println("Deposit 10 amount as cash value ");
		objUtilities.logReporter("Enter comment", objWrapperFunctions.setText(commentTextArea,"test"), false);
		objUtilities.logReporter("Click on cashCheckbox", objWrapperFunctions.click(cashCheckbox), false);	
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
		System.out.println("Customer Number=" +custNumber);
		objUtilities.logReporter("Click on Deposit Off tab", objWrapperFunctions.click(depositOffTab), false);	
		objUtilities.logReporter("Enter Customer Number", objWrapperFunctions.setText(customerNoDeposit,custNumber), false);		
		objWrapperFunctions.pressTabBtn(customerNoDeposit);
		Thread.sleep(3000);
		message=objWrapperFunctions.getText(balance);
		System.out.println("Account balance before deposit = "+message);		
		objUtilities.logReporter("Click on amount textbox", objWrapperFunctions.click(amountInput), false);
		objUtilities.logReporter("Click on amount textbox", objWrapperFunctions.clear(amountInput), false);
		objUtilities.logReporter("Enter amount", objWrapperFunctions.setText(amountInput,"5"), false);
		System.out.println("Deposit off 5 amount as cash value ");
		objUtilities.logReporter("Enter comment", objWrapperFunctions.setText(commentTextArea,"test"), false);
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
	
	
	
}
