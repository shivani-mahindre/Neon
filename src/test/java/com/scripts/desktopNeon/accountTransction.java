package com.scripts.desktopNeon;

import org.testng.annotations.Test;

import com.generic.BaseTest;
import com.pageFactory.desktopNeon.accountTransactions_PF;
import com.pageFactory.desktopNeon.login_PF;

public class accountTransction extends BaseTest {

	private accountTransactions_PF objaccountTransactions_PF;
	private login_PF objlogin_PF;
	
	@Test(priority = 0)
	public void loggin()
	{
		initializeWebEnvironment("Neon");
		objaccountTransactions_PF= new accountTransactions_PF(this);
		objlogin_PF = new login_PF(this);
		objlogin_PF.login();
		objaccountTransactions_PF.addTranscationA();
		objaccountTransactions_PF.transationAReversal();
		
		objaccountTransactions_PF.addTranscationB();
		objaccountTransactions_PF.transationBReversal();
		objaccountTransactions_PF.addTranscationC();
		objaccountTransactions_PF.transationReversalC();
		
		objlogin_PF.logout();	
		
		//tearDown();
	}
	
	/*	// Closing the browser
	@AfterClass
	private void tearDown() 
	{
		tearDownWebEnvironment();
	
	}*/
	
	
}
