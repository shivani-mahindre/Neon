package com.scripts.desktopGNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.generic.BaseTest;
import com.pageFactory.desktopGNG.DepositOff_PF;
import com.pageFactory.desktopGNG.login_PF;


public class depositOff extends BaseTest
{
	private login_PF objlogin_PF;
	private DepositOff_PF objDepositOff_PF;
	
	@Test(priority = 0)
	public void deposit()
	{
		initializeWebEnvironment("Neon");
		objDepositOff_PF=new DepositOff_PF(this);
		objlogin_PF = new login_PF(this);
		objlogin_PF.login();
		objlogin_PF.logout();	
	//	tearDown();
	
	}
/*	// Closing the browser
		@AfterClass
		private void tearDown() 
		{
			tearDownWebEnvironment();
		
		}*/
}
