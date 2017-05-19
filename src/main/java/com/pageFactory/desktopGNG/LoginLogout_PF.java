package com.pageFactory.desktopGNG;

import org.openqa.selenium.By;

import com.generic.Utilities;
import com.generic.WrapperFunctions;
import com.generic.Pojo;

public class LoginLogout_PF
{
	private Utilities objUtilities;
	private WrapperFunctions objWrapperFunctions;
	String NeonUser,NeonPass;
		//constructor
		public LoginLogout_PF(Pojo pojo)
		{
			objUtilities = pojo.getObjUtilities();
			objWrapperFunctions = pojo.getObjWrapperFunctions();
			pojo.getObjConfig();
			NeonUser=pojo.getNeonUser();
			NeonPass=pojo.geNeonPassword();	
		}
		private By inputUserName=By.id("Username");
		private By password=By.id("Password");
		private By signBtn=By.xpath("//button[@class='btn btn-lg btn-primary btn-block ig-signin-button']");
		  
		  
		  /**
		   * @author : Automation Tester(SQS) 
		   * @Description : set user name */
		  public void login()
		  {
			  objUtilities.logReporter("Set 'UserName'.", NeonUser, objWrapperFunctions.setText(inputUserName, NeonUser), false);
			  objUtilities.logReporter("Set 'Password'.",NeonPass, objWrapperFunctions.setText(password, NeonPass), false);
			  
			  objUtilities.logReporter("Click 'Sign In'.", objWrapperFunctions.click(signBtn),false);
			  
		  }

		  /**
		   * @author : Automation Tester(SQS) 
		   * @Description : Set password */
/*		  public void setPassword(String userPassword)
		  {
			  objUtilities.logReporter("Set 'Password'.",NeonPass, objWrapperFunctions.setText(password, NeonPass), false);
		  }

		  /**
		   * @author : Automation Tester(SQS) 
		   * @Description : Click on Sign in button */
		  /*public void clickCloseCookie()
		  {
		//	  objUtilities.logReporter("Click 'Close cookie Button'.", objWrapperFunctions.click(btnCloseCookie), false);
		    
		  }
		  	  
		  /**
		   * @author : Automation Tester(SQS) 
		   * @Description : Click on Sign in button */
	/*	  public void clickSignIn()
		  {
			  objUtilities.logReporter("Click 'Sign In'.", objWrapperFunctions.click(signBtn),false);
			  
		  }
		  
		  /**
		   * @author : Automation Tester(SQS) 
		   * @Description : Click on Sign in button */
/*		  public void clickLogOut()
		  {
	//		  objUtilities.logReporter("Click 'LogOut'.", objWrapperFunctions.click(linkLogOut), false);
		  }
		  
		  public void loginError()
		  {
			 if(objWrapperFunctions.verifyIsElementPresent(loginError))
			  {
				  objUtilities.logReporter("Login failure error", objWrapperFunctions.verifyIsElementPresent(loginError), false);
			  }
		  }
		*/  
		  
}
