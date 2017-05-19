package com.scripts.desktopGNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.Title;

import com.generic.BaseTest;
import com.pageFactory.desktopGNG.neonReg_PF;


public class neonReg extends BaseTest
{
	private neonReg_PF objneonReg_PF;

	//@Title("Register new customer in Neon and Performing  deposit on and off functionality ")
	@Test(priority = 0)
	public void RegisterAndDeposit()
	{
		initializeWebEnvironment("Neon");
		objneonReg_PF = new neonReg_PF(this);
		objneonReg_PF.registration();
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
