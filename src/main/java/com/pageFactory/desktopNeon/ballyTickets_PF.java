package com.pageFactory.desktopNeon;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.Screen;

import com.generic.Pojo;
import com.generic.Utilities;
import com.generic.WrapperFunctions;

public class ballyTickets_PF {

	
	private Utilities objUtilities;
	private WrapperFunctions objWrapperFunctions;
	WebDriver webdriver;
	Properties objConfig=new Properties();
	String custNumber="",machineName="",message="";
	//String NeonUser,NeonPass;
	Screen objScreen;
	static String refId_FE="";
	
	public ballyTickets_PF(Pojo pojo){
		objUtilities = pojo.getObjUtilities();
		objWrapperFunctions = pojo.getObjWrapperFunctions();
		pojo.getObjConfig();	
		custNumber=pojo.getUser();
		machineName=pojo.getMachine();
	//	System.out.println(machineName);
		
	}
	
	private By transactionTab=By.xpath("//li//a//span//span[@class='ig-icon-text'][contains(.,'Transactions')]");	
	private By ballyTicketsCountedTab=By.xpath("//li//a[contains(.,'Bally Tickets Counted')]");
	private By headingText=By.xpath("//div[@class='buttonbar']//div//h2");
	
	private By closeIconTab=By.xpath("//div[@class='buttonbar']//div//button");
	private By ballyTicketsIssuedTab=By.xpath("//li//a[contains(.,'Bally Tickets Issued')]");	
	private By ballyTicketsIssuedReversalTab=By.xpath("//li//a[contains(.,'Bally Tickets Issued Reversal')]");	
	private By ballyTicketsReplayedTab=By.xpath("//li//a[contains(.,'Bally Tickets Replayed')]");	
	private By ballyTicketsReplayedReversalTab=By.xpath("//li//a[contains(.,'Bally Tickets Replayed Reversal')]");	
	
	private By ballyTicketsSoldTab=By.xpath("//li//a[contains(.,'Bally Tickets Sold')]");	
	private By ballyTicketsWriteoffTab=By.xpath("//li//a[contains(.,'Bally Tickets Writeoff')]");	
	
	private By ballyTicketsWriteoffReversalTab=By.xpath("//li//a[contains(.,'Bally Tickets Writeoff Reversal')]");
	
	private By numberInput=By.xpath("//input[@id='textBoxCustomerNumber']");
	//div[@class='col1']
	private By custDetails=By.xpath("//div//fieldset//div[3]");
	private By machineInput=By.xpath("//input[@id='textBoxMachineNumber']");
	private By gameThemeText=By.xpath("//div//fieldset//div[2]");	
	private By amountTextbox=By.xpath("//div//input[@id='textBoxAmount']");
	private By commentTextBox=By.xpath("//textarea[@id='Comment']");
	private By saveButton=By.xpath("//button[@class='btn-primary btn-secured'][contains(.,'Save')]");	
	private By accountText=By.xpath("//div//span[@class='readonlytext']");
	private By okBtn=By.xpath("//button[@class='btn btn-default'][contains(.,'OK')]");
	private By yesBtn=By.xpath("//button[@class='btn btn-default'][contains(.,'Yes')]");
	private By newBtn=By.xpath("//button[@class='btn-primary btn-secured'][contains(.,'New')]");
	
	
	
	
	public void navigateToBallyTicketsCountedTab()
	{
		objUtilities.logReporter("Click on transaction Tab", objWrapperFunctions.click(transactionTab), false);
		objUtilities.logReporter("Click on bad Beat Jackpot A tab", objWrapperFunctions.click(ballyTicketsCountedTab), false);
		String text=objWrapperFunctions.getText(headingText);
		System.out.println(text);	
	}
	
	public void navigateToBallyTicketsReplayedTab()
	{
		objUtilities.logReporter("Click on transaction Tab", objWrapperFunctions.click(transactionTab), false);
		objUtilities.logReporter("Click on bally Tickets Replayed Tab", objWrapperFunctions.click(ballyTicketsReplayedTab), false);
		String text=objWrapperFunctions.getText(headingText);
		System.out.println(text);	
	}
	public void navigateToballyTicketsReplayedReversalTab()
	{
		objUtilities.logReporter("Click on transaction Tab", objWrapperFunctions.click(transactionTab), false);
		objUtilities.logReporter("Click on bally Tickets Replayed Reversal Tab", objWrapperFunctions.click(ballyTicketsReplayedReversalTab), false);
		String text=objWrapperFunctions.getText(headingText);
		System.out.println(text);	
	}
	
	public void addAmountDetailsAndSave()
	{
		objWrapperFunctions.waitForElementPresence(accountText);
		String accounttxt=objWrapperFunctions.getText(accountText);
		System.out.println("Account : " +accounttxt);
		objWrapperFunctions.clear(amountTextbox);
		objUtilities.logReporter("Enter Amount Number", objWrapperFunctions.setText(amountTextbox,"10"), false);		
		objUtilities.logReporter("Enter Comment Number", objWrapperFunctions.setText(commentTextBox,"test"), false);
		objUtilities.logReporter("Click on Save", objWrapperFunctions.click(saveButton), false);	
		objUtilities.logReporter("Click on Yes button", objWrapperFunctions.click(yesBtn), false);
		objUtilities.logReporter("Click on Ok", objWrapperFunctions.click(okBtn), false);
		objUtilities.logReporter("Verify New button present", objWrapperFunctions.verifyIsElementPresent(newBtn),false);
		objUtilities.logReporter("Click on New", objWrapperFunctions.click(newBtn), false);
		objUtilities.logReporter("Click on 'x' ", objWrapperFunctions.click(closeIconTab), false);		
	}
	
	public void navigateToBallyTicketsIssuedTab()
	{
			objUtilities.logReporter("Click on transaction Tab", objWrapperFunctions.click(transactionTab), false);
			objUtilities.logReporter("Click on bally Tickets Issued Tab", objWrapperFunctions.click(ballyTicketsIssuedTab), false);
			String text=objWrapperFunctions.getText(headingText);
			System.out.println(text);	
			objUtilities.logReporter("Enter Machine Number", objWrapperFunctions.setText(machineInput,machineName), false);
			objWrapperFunctions.pressTabBtn(machineInput);
			String gameTheme=objWrapperFunctions.getText(gameThemeText);
			System.out.println(gameTheme);
	}
	public void navigateToballyTicketsIssuedReversalTab()
	{
			objUtilities.logReporter("Click on transaction Tab", objWrapperFunctions.click(transactionTab), false);
			objUtilities.logReporter("Click on bally Tickets Replayed Tab", objWrapperFunctions.click(ballyTicketsIssuedReversalTab), false);
			String text=objWrapperFunctions.getText(headingText);
			System.out.println(text);	
			objUtilities.logReporter("Enter Machine Number", objWrapperFunctions.setText(machineInput,machineName), false);
			objWrapperFunctions.pressTabBtn(machineInput);
			String gameTheme=objWrapperFunctions.getText(gameThemeText);
			System.out.println(gameTheme);
	}
	
	public void navigateToBallyTicketsSoldTab()
	{
			objUtilities.logReporter("Click on transaction Tab", objWrapperFunctions.click(transactionTab), false);
			objUtilities.logReporter("Click on Bally Tickets Sold Tab", objWrapperFunctions.click(ballyTicketsSoldTab), false);
			String text=objWrapperFunctions.getText(headingText);
			System.out.println(text);	
			
			objUtilities.logReporter("Enter Customer Number", objWrapperFunctions.setText(numberInput,custNumber), false);
			objWrapperFunctions.pressTabBtn(numberInput);
			System.out.println("******Customer Deatils*******");
			System.out.println("Customer Number "+custNumber);
			objWrapperFunctions.displayAllElement(custDetails);
			
	}
	/*public void  ballyTicketsCounted()
	{
		objUtilities.logReporter("Click on transaction Tab", objWrapperFunctions.click(transactionTab), false);
		objUtilities.logReporter("Click on bad Beat Jackpot A tab", objWrapperFunctions.click(ballyTicketsCountedTab), false);
		String text=objWrapperFunctions.getText(headingText);
		System.out.println(text);	
		objWrapperFunctions.waitForElementPresence(accountText);
		String accounttxt=objWrapperFunctions.getText(accountText);
		System.out.println("Account : " +accounttxt);
		objUtilities.logReporter("Enter Amount Number", objWrapperFunctions.setText(amountTextbox,"10"), false);
		
		objUtilities.logReporter("Enter Comment Number", objWrapperFunctions.setText(commentTextBox,"test"), false);
		objUtilities.logReporter("Click on Save", objWrapperFunctions.click(saveButton), false);	
		objUtilities.logReporter("Click on Yes button", objWrapperFunctions.click(yesBtn), false);
		objUtilities.logReporter("Click on Ok", objWrapperFunctions.click(okBtn), false);
		objUtilities.logReporter("Verify New button present", objWrapperFunctions.verifyIsElementPresent(newBtn),false);
		objUtilities.logReporter("Click on New", objWrapperFunctions.click(newBtn), false);
		objUtilities.logReporter("Click on 'x' ", objWrapperFunctions.click(closeIconTab), false);
	}	
	
	
	
	
	public void  ballyTicketsIssued()
	{
		//objUtilities.logReporter("Click on transaction Tab", objWrapperFunctions.click(transactionTab), false);
		objUtilities.logReporter("Click on bally Tickets Replayed Tab", objWrapperFunctions.click(ballyTicketsIssuedTab), false);
		String text=objWrapperFunctions.getText(headingText);
		System.out.println(text);	
		objUtilities.logReporter("Enter Machine Number", objWrapperFunctions.setText(machineInput,machineName), false);
		//objWrapperFunctions.waitForElementPresence(accountText);
		//String accounttxt=objWrapperFunctions.getText(accountText);
		//System.out.println("Account : " +accounttxt);
		
		objUtilities.logReporter("Enter Amount Number", objWrapperFunctions.setText(amountTextbox,"10"), false);
		String gameTheme=objWrapperFunctions.getText(gameThemeText);
		System.out.println(gameTheme);
		objUtilities.logReporter("Enter Comment Number", objWrapperFunctions.setText(commentTextBox,"test"), false);
		objUtilities.logReporter("Click on Save", objWrapperFunctions.click(saveButton), false);	
		objUtilities.logReporter("Click on Yes button", objWrapperFunctions.click(yesBtn), false);
		objUtilities.logReporter("Click on Ok", objWrapperFunctions.click(okBtn), false);
		objUtilities.logReporter("Verify New button present", objWrapperFunctions.verifyIsElementPresent(newBtn),false);
		objUtilities.logReporter("Click on New", objWrapperFunctions.click(newBtn), false);
		objUtilities.logReporter("Click on 'x' ", objWrapperFunctions.click(closeIconTab), false);
	}
	
	
	public void  ballyTicketsIssuedReversal()
	{
		//objUtilities.logReporter("Click on transaction Tab", objWrapperFunctions.click(transactionTab), false);
		objUtilities.logReporter("Click on bally Tickets Replayed Tab", objWrapperFunctions.click(ballyTicketsIssuedReversalTab), false);
		String text=objWrapperFunctions.getText(headingText);
		System.out.println(text);	
		objUtilities.logReporter("Enter Machine Number", objWrapperFunctions.setText(machineInput,machineName), false);
		objWrapperFunctions.pressTabBtn();
		//objWrapperFunctions.waitForElementPresence(accountText);
		//String accounttxt=objWrapperFunctions.getText(accountText);
		//System.out.println("Account : " +accounttxt);
		
		objUtilities.logReporter("Enter Amount Number", objWrapperFunctions.setText(amountTextbox,"10"), false);
		String gameTheme=objWrapperFunctions.getText(gameThemeText);
		System.out.println(gameTheme);
		objUtilities.logReporter("Enter Comment Number", objWrapperFunctions.setText(commentTextBox,"test"), false);
		objUtilities.logReporter("Click on Save", objWrapperFunctions.click(saveButton), false);	
		objUtilities.logReporter("Click on Yes button", objWrapperFunctions.click(yesBtn), false);
		objUtilities.logReporter("Click on Ok", objWrapperFunctions.click(okBtn), false);
		objUtilities.logReporter("Verify New button present", objWrapperFunctions.verifyIsElementPresent(newBtn),false);
		objUtilities.logReporter("Click on New", objWrapperFunctions.click(newBtn), false);
		objUtilities.logReporter("Click on 'x' ", objWrapperFunctions.click(closeIconTab), false);
	}
	
	
	public void  ballyTicketsReplayed()
	{
		//objUtilities.logReporter("Click on transaction Tab", objWrapperFunctions.click(transactionTab), false);
		objUtilities.logReporter("Click on bally Tickets Replayed Tab", objWrapperFunctions.click(ballyTicketsReplayedTab), false);
		String text=objWrapperFunctions.getText(headingText);
		System.out.println(text);	
		objWrapperFunctions.waitForElementPresence(accountText);
		String accounttxt=objWrapperFunctions.getText(accountText);
		System.out.println("Account : " +accounttxt);
		objUtilities.logReporter("Enter Amount Number", objWrapperFunctions.setText(amountTextbox,"10"), false);
		objUtilities.logReporter("Enter Comment Number", objWrapperFunctions.setText(commentTextBox,"test"), false);
		objUtilities.logReporter("Click on Save", objWrapperFunctions.click(saveButton), false);	
		objUtilities.logReporter("Click on Yes button", objWrapperFunctions.click(yesBtn), false);
		objUtilities.logReporter("Click on Ok", objWrapperFunctions.click(okBtn), false);
		objUtilities.logReporter("Verify New button present", objWrapperFunctions.verifyIsElementPresent(newBtn),false);
		objUtilities.logReporter("Click on New", objWrapperFunctions.click(newBtn), false);
		objUtilities.logReporter("Click on 'x' ", objWrapperFunctions.click(closeIconTab), false);
	}
	public void  ballyTicketsReplayedReversal()
	{
		//objUtilities.logReporter("Click on transaction Tab", objWrapperFunctions.click(transactionTab), false);
		objUtilities.logReporter("Click on bally Tickets Replayed Reversal Tab", objWrapperFunctions.click(ballyTicketsReplayedReversalTab), false);
		String text=objWrapperFunctions.getText(headingText);
		System.out.println(text);	
		objWrapperFunctions.waitForElementPresence(accountText);
		String accounttxt=objWrapperFunctions.getText(accountText);
		System.out.println("Account : " +accounttxt);
		objUtilities.logReporter("Enter Amount Number", objWrapperFunctions.setText(amountTextbox,"10"), false);
		objUtilities.logReporter("Enter Comment Number", objWrapperFunctions.setText(commentTextBox,"test"), false);
		objUtilities.logReporter("Click on Save", objWrapperFunctions.click(saveButton), false);	
		objUtilities.logReporter("Click on Yes button", objWrapperFunctions.click(yesBtn), false);
		objUtilities.logReporter("Click on Ok", objWrapperFunctions.click(okBtn), false);
		objUtilities.logReporter("Verify New button present", objWrapperFunctions.verifyIsElementPresent(newBtn),false);
		objUtilities.logReporter("Click on New", objWrapperFunctions.click(newBtn), false);
		objUtilities.logReporter("Click on 'x' ", objWrapperFunctions.click(closeIconTab), false);
	}*/
	
	
}
