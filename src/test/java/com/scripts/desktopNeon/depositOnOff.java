package com.scripts.desktopNeon;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.generic.BaseTest;
import com.pageFactory.desktopNeon.DepositOnOff_PF;
import com.pageFactory.desktopNeon.login_PF;


public class depositOnOff extends BaseTest
{
	private login_PF objlogin_PF;
	private DepositOnOff_PF objDepositOff_PF;
	
	@Test(priority = 0)
	public void deposit()
	{
		initializeWebEnvironment("Neon");
		objDepositOff_PF=new DepositOnOff_PF(this);
		objlogin_PF = new login_PF(this);
		objlogin_PF.login();
		objDepositOff_PF.depositOn();
		objDepositOff_PF.depositOff();
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
