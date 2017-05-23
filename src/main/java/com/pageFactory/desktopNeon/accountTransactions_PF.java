package com.pageFactory.desktopNeon;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.Screen;

import com.generic.Pojo;
import com.generic.Utilities;
import com.generic.WrapperFunctions;

public class accountTransactions_PF {
	
	private Utilities objUtilities;
	private WrapperFunctions objWrapperFunctions;
	WebDriver webdriver;
	Properties objConfig=new Properties();
	String custNumber="",user="",message="";
	String NeonUser,NeonPass;
	Screen objScreen;
	static String refId_FE="";
	
	public accountTransactions_PF(Pojo pojo){
		objUtilities = pojo.getObjUtilities();
		objWrapperFunctions = pojo.getObjWrapperFunctions();
		pojo.getObjConfig();
		custNumber=pojo.getUser();
		
	}
	
	private By transactionTab=By.xpath("//li//a//span//span[@class='ig-icon-text'][contains(.,'Transactions')]");	
	private By badBeatJackpotATab=By.xpath("//li//a[contains(.,'Bad Beat Jackpot A')]");
	private By badBeatJackpotARevTab=By.xpath("//li//a[contains(.,'Bad Beat Jackpot A Reversal')]");
	
	private By badBeatJackpotBTab=By.xpath("//li//a[contains(.,'Bad Beat Jackpot B')]");
	private By badBeatJackpotBRevTab=By.xpath("//li//a[contains(.,'Bad Beat Jackpot B Reversal')]");
	
	private By badBeatJackpotCTab=By.xpath("//li//a[contains(.,'Bad Beat Jackpot C')]");
	private By badBeatJackpotCRevTab=By.xpath("//li//a[contains(.,'Bad Beat Jackpot C Reversal')]");
	
	private By amountTextbox=By.xpath("//div//input[@id='textBoxAmount']");
	private By commentTextBox=By.xpath("//textarea[@id='Comment']");
	private By saveButton=By.xpath("//button[@class='btn-primary btn-secured'][contains(.,'Save')]");	
	private By accountText=By.xpath("//div//span[@class='readonlytext']");
	private By okBtn=By.xpath("//button[@class='btn btn-default'][contains(.,'OK')]");
	private By yesBtn=By.xpath("//button[@class='btn btn-default'][contains(.,'Yes')]");
	private By newBtn=By.xpath("//button[@class='btn-primary btn-secured'][contains(.,'New')]");
	
	
	public void  addTranscationA()
	{
		objUtilities.logReporter("Click on transaction Tab", objWrapperFunctions.click(transactionTab), false);
		objUtilities.logReporter("Click on bad Beat Jackpot A tab", objWrapperFunctions.click(badBeatJackpotATab), false);	
		objWrapperFunctions.waitForElementPresence(accountText);
		String accounttxt=objWrapperFunctions.getText(accountText);
		System.out.println("Account : " +accounttxt);
		objUtilities.logReporter("Enter Amount Number", objWrapperFunctions.setText(amountTextbox,"10"), false);
		objUtilities.logReporter("Enter Comment Number", objWrapperFunctions.setText(commentTextBox,"test"), false);
		objUtilities.logReporter("Click on Save", objWrapperFunctions.click(saveButton), false);	
		objUtilities.logReporter("Click on Save", objWrapperFunctions.click(yesBtn), false);
		objUtilities.logReporter("Click on Save", objWrapperFunctions.click(okBtn), false);
		objUtilities.logReporter("Click on Save", objWrapperFunctions.verifyIsElementPresent(newBtn),false);
		objUtilities.logReporter("Click on Save", objWrapperFunctions.click(newBtn), false);
	}
	public void  transationAReversal()
	{
	//	objUtilities.logReporter("Click on transaction Tab", objWrapperFunctions.click(transactionTab), false);
		objUtilities.logReporter("Click on bad Beat Jackpot A tab", objWrapperFunctions.click(badBeatJackpotARevTab), false);	
		objWrapperFunctions.waitForElementPresence(accountText);
		String accounttxt=objWrapperFunctions.getText(accountText);
		System.out.println("Account : " +accounttxt);
		objUtilities.logReporter("Enter Amount Number", objWrapperFunctions.setText(amountTextbox,"10"), false);
		objUtilities.logReporter("Enter Comment Number", objWrapperFunctions.setText(commentTextBox,"test"), false);
		objUtilities.logReporter("Click on Save", objWrapperFunctions.click(saveButton), false);	
		objUtilities.logReporter("Click on Save", objWrapperFunctions.click(yesBtn), false);
		objUtilities.logReporter("Click on Save", objWrapperFunctions.click(okBtn), false);
		objUtilities.logReporter("Click on Save", objWrapperFunctions.verifyIsElementPresent(newBtn),false);
		objUtilities.logReporter("Click on Save", objWrapperFunctions.click(newBtn), false);
	}
	public void  addTranscationB()
	{
		//objUtilities.logReporter("Click on transaction Tab", objWrapperFunctions.click(transactionTab), false);
		objUtilities.logReporter("Click on bad Beat Jackpot A tab", objWrapperFunctions.click(badBeatJackpotBTab), false);	
		objWrapperFunctions.waitForElementPresence(accountText);
		String accounttxt=objWrapperFunctions.getText(accountText);
		System.out.println("Account : " +accounttxt);
		objUtilities.logReporter("Enter Amount Number", objWrapperFunctions.setText(amountTextbox,"10"), false);
		objUtilities.logReporter("Enter Comment Number", objWrapperFunctions.setText(commentTextBox,"test"), false);
		objUtilities.logReporter("Click on Save", objWrapperFunctions.click(saveButton), false);	
		objUtilities.logReporter("Click on Save", objWrapperFunctions.click(yesBtn), false);
		objUtilities.logReporter("Click on Save", objWrapperFunctions.click(okBtn), false);
		objUtilities.logReporter("Click on Save", objWrapperFunctions.verifyIsElementPresent(newBtn),false);
		objUtilities.logReporter("Click on Save", objWrapperFunctions.click(newBtn), false);
	}
	public void  transationBReversal()
	{
	//	objUtilities.logReporter("Click on transaction Tab", objWrapperFunctions.click(transactionTab), false);
		objUtilities.logReporter("Click on bad Beat Jackpot A tab", objWrapperFunctions.click(badBeatJackpotBRevTab), false);	
		objWrapperFunctions.waitForElementPresence(accountText);
		String accounttxt=objWrapperFunctions.getText(accountText);
		System.out.println("Account : " +accounttxt);
		objUtilities.logReporter("Enter Amount Number", objWrapperFunctions.setText(amountTextbox,"10"), false);
		objUtilities.logReporter("Enter Comment Number", objWrapperFunctions.setText(commentTextBox,"test"), false);
		objUtilities.logReporter("Click on Save", objWrapperFunctions.click(saveButton), false);	
		objUtilities.logReporter("Click on Save", objWrapperFunctions.click(yesBtn), false);
		objUtilities.logReporter("Click on Save", objWrapperFunctions.click(okBtn), false);
		objUtilities.logReporter("Click on Save", objWrapperFunctions.verifyIsElementPresent(newBtn),false);
		objUtilities.logReporter("Click on Save", objWrapperFunctions.click(newBtn), false);
	}
	public void  addTranscationC()
	{
		//objUtilities.logReporter("Click on transaction Tab", objWrapperFunctions.click(transactionTab), false);
		objUtilities.logReporter("Click on bad Beat Jackpot A tab", objWrapperFunctions.click(badBeatJackpotCTab), false);	
		objWrapperFunctions.waitForElementPresence(accountText);
		String accounttxt=objWrapperFunctions.getText(accountText);
		System.out.println("Account : " +accounttxt);
		objUtilities.logReporter("Enter Amount Number", objWrapperFunctions.setText(amountTextbox,"10"), false);
		objUtilities.logReporter("Enter Comment Number", objWrapperFunctions.setText(commentTextBox,"test"), false);
		objUtilities.logReporter("Click on Save", objWrapperFunctions.click(saveButton), false);	
		objUtilities.logReporter("Click on Save", objWrapperFunctions.click(yesBtn), false);
		objUtilities.logReporter("Click on Save", objWrapperFunctions.click(okBtn), false);
		objUtilities.logReporter("Click on Save", objWrapperFunctions.verifyIsElementPresent(newBtn),false);
		//objUtilities.logReporter("Click on Save", objWrapperFunctions.click(newBtn), false);
	}
	public void  transationReversalC()
	{
	//	objUtilities.logReporter("Click on transaction Tab", objWrapperFunctions.click(transactionTab), false);
		objUtilities.logReporter("Click on bad Beat Jackpot A tab", objWrapperFunctions.click(badBeatJackpotCRevTab), false);	
		objWrapperFunctions.waitForElementPresence(accountText);
		String accounttxt=objWrapperFunctions.getText(accountText);
		System.out.println("Account : " +accounttxt);
		objUtilities.logReporter("Enter Amount Number", objWrapperFunctions.setText(amountTextbox,"10"), false);
		objUtilities.logReporter("Enter Comment Number", objWrapperFunctions.setText(commentTextBox,"test"), false);
		objUtilities.logReporter("Click on Save", objWrapperFunctions.click(saveButton), false);	
		objUtilities.logReporter("Click on Save", objWrapperFunctions.click(yesBtn), false);
		objUtilities.logReporter("Click on Save", objWrapperFunctions.click(okBtn), false);
		objUtilities.logReporter("Click on Save", objWrapperFunctions.verifyIsElementPresent(newBtn),false);
		//objUtilities.logReporter("Click on Save", objWrapperFunctions.click(newBtn), false);
	}
	
	
	

	
	
	
	
	
}
