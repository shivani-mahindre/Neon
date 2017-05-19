package com.scripts.desktopGNG;

import org.testng.annotations.Test;

import com.generic.BaseTest;
import com.pageFactory.desktopGNG.Reception_Entry_PF;
import com.pageFactory.desktopGNG.login_PF;


public class Reception_Entry extends BaseTest{
	
	
	private Reception_Entry_PF objReception_Entry_PF;
	private login_PF objlogin_PF;
	
	@Test(priority = 0)
	public void loggin()
	{
		initializeWebEnvironment("Neon");
		objReception_Entry_PF= new Reception_Entry_PF(this);
		objlogin_PF = new login_PF(this);
		objlogin_PF.login();
		objReception_Entry_PF.customerEntry();
		objReception_Entry_PF.suspendedCustomer();
		objlogin_PF.logout();	
		
		//tearDown();
	}
}
