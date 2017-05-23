package com.scripts.desktopNeon;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.generic.BaseTest;
import com.pageFactory.desktopNeon.logSuspendedCustomer_PF;


public class logSuspendedCustomer extends BaseTest
{
	private logSuspendedCustomer_PF objlogSuspendedCustomer_PF;
	//@Title("Register new customer in Neon and Perform deposit ")
		@Test(priority = 0)
		public void loggingSuspendedCustomer()
		{
			initializeWebEnvironment("Neon");
			objlogSuspendedCustomer_PF= new logSuspendedCustomer_PF(this);
			objlogSuspendedCustomer_PF.suspendedCustomer();
			//tearDown();
		}
		// Closing the browser
			@AfterClass
			private void tearDown()
			{
				//tearDownWebEnvironment();

			}
	
}
