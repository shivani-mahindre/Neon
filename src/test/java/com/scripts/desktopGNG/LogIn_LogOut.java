package com.scripts.desktopGNG;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.generic.BaseTest;
import com.pageFactory.desktopGNG.LoginLogout_PF;


/**
 * @ScriptName    : CreateNewCustomer
 * @Description   : Create new customer
 */

public class LogIn_LogOut extends BaseTest
{
	//private Utilities objUtilities;
	//private LoginView objLoginView ;
	private LoginLogout_PF objLoginLogout_PF;

	// Initializing Environment 
	@Test
	private void setUp()
	{
	    initializeWebEnvironment("MB");
		//objLoginView = new  LoginView(this);
		objLoginLogout_PF=new LoginLogout_PF(this);
		objLoginLogout_PF.login();
		 //Pojo objPojo=new Pojo();
	}
	
/*	@BeforeTest
	public void getTestName(final ITestContext testContext)
	{
	     Pojo objPojo=new Pojo();
	     objPojo.setTestTestName(testContext.getName());
	}*/
	
	
	// Execution flow for the test
	/*	@Title("Login to Mecca site")
	@Test
	public void loginNeonSystem() 
	{
		
		//objLoginLogout_PF.clickSignIn();
	//	objLoginLogout_PF.loginError();
		//objLoginView.login();
	//	objLoginView.logout();
		
	}
	
	// Closing the browser
	@AfterClass
	private void tearDown() 
	{
		tearDownWebEnvironment();
		objLoginView = null;
	}*/
}