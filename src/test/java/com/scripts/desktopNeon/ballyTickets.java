package com.scripts.desktopNeon;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.generic.BaseTest;
import com.pageFactory.desktopNeon.ballyTickets_PF;
import com.pageFactory.desktopNeon.login_PF;

public class ballyTickets extends BaseTest {

	private ballyTickets_PF objballyTickets_PF;
	private login_PF objlogin_PF;
	
	@Test(priority = 0)
	public void VerifyballyTickets()
	{
		initializeWebEnvironment("Neon");
		objballyTickets_PF= new ballyTickets_PF(this);
		objlogin_PF = new login_PF(this);
		objlogin_PF.login();
	/*	objballyTickets_PF.navigateToBallyTicketsCountedTab();
		objballyTickets_PF.addAmountDetailsAndSave();
		
		objballyTickets_PF.navigateToBallyTicketsIssuedTab();
		objballyTickets_PF.addAmountDetailsAndSave();
		
		objballyTickets_PF.navigateToballyTicketsIssuedReversalTab();
		objballyTickets_PF.addAmountDetailsAndSave();
		
		objballyTickets_PF.navigateToBallyTicketsReplayedTab();
		objballyTickets_PF.addAmountDetailsAndSave();
		
		objballyTickets_PF.navigateToballyTicketsReplayedReversalTab();
		objballyTickets_PF.addAmountDetailsAndSave();
		*/
	
		objballyTickets_PF.navigateToBallyTicketsSoldTab();
		objballyTickets_PF.addAmountDetailsAndSave();
		objlogin_PF.logout();	
		
		tearDown();
	}
	
	// Closing the browser
	@AfterClass
	private void tearDown() 
	{
		tearDownWebEnvironment();
	
	}


}
