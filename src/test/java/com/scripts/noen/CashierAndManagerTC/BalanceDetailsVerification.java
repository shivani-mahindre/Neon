package com.scripts.noen.CashierAndManagerTC;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.Title;

import com.generic.BaseTest;
import com.pageFactory.CashierManagerTC.BalancePage;


public class BalanceDetailsVerification extends BaseTest
{
	private BalancePage objBalancePage;

	//@Title("Register new customer in Neon and Performing  deposit on and off functionality ")
	
	@BeforeClass
	private void setUp(){
		
		initializeWebEnvironment("Neon");
	}
	
	@Test(priority = 0)
	public void checkBalanceDetails()
	{
		
		objBalancePage = new BalancePage(this);
		objBalancePage.viewBalanceCheckDetails();
	//	objneonReg_PF.depositOn();
	//	objneonReg_PF.depositOff();
	//	objneonReg_PF.report();
	
	}
// Closing the browser
	@AfterClass
	private void tearDown() 
	{
		tearDownWebEnvironment();
	
	}
}
