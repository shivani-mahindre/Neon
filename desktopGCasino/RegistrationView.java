package com.views.desktopGCasino;

import java.util.Properties;

import com.generic.Pojo;
import com.generic.Utilities;
import com.pageFactory.desktopGcasino.Registration;
import com.pageFactory.desktopMecca.Register_pageFactory;

public class RegistrationView {
	private Pojo objPojo;
	private String testDataHolder = "";
	private Utilities objUtilities;
	private Properties objConfig;
	//page object declaration
	private Registration objRegistration;
	
	public RegistrationView(Pojo pojo)
	{
		this.objPojo = pojo;
		objUtilities = pojo.getObjUtilities();
		pojo.getObjWrapperFunctions();
		objConfig = pojo.getObjConfig();
	}
	
	
	public void register() 
	{
		objRegistration = new Registration(objPojo);
		
		objRegistration.registration();
		/*testDataHolder = objConfig.getProperty("web.username");
		objHomePage.setUserName(testDataHolder);
		testDataHolder = objConfig.getProperty("web.password");
		objHomePage.setPassword(testDataHolder);
		objHomePage.clickSignIn();
		objHomePage=null;*/

	}
}
