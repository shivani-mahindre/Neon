package com.scripts.desktopNeon;

import org.testng.annotations.Test;

import com.generic.BaseTest;
import com.pageFactory.desktopNeon.Reception_Entry_PF;
import com.pageFactory.desktopNeon.login_PF;
import com.pageFactory.desktopNeon.viewCustomerInCasino_PF;

public class viewCustomerCasino extends BaseTest{

	
	private viewCustomerInCasino_PF objviewCustomerInCasino_PF;
	private login_PF objlogin_PF;
	
	
	@Test(priority = 0)
	public void verifyCustomersInCasino()
	{
		initializeWebEnvironment("Neon");
		objviewCustomerInCasino_PF= new viewCustomerInCasino_PF(this);
		objlogin_PF = new login_PF(this);
		objlogin_PF.login();
		objviewCustomerInCasino_PF.viewCustomers();
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
