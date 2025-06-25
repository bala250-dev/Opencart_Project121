package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*
 Data is valid - login success - test pass - logout
 Data is valid - login failed - test fail
 
 Data is Invalid - login success - test fail - logout
 Data is Invalid - login failed - test pass
 */

public class TC003_LoginDDT extends BaseClass
{
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class,groups="datadriven")
	public void verify_loginDDT(String email, String pwd, String exp)
	{
		
		logger.info("***** Started TC003_LoginDDT *****");
		  
		try
		{
		  //HomePage
		  HomePage hp = new HomePage(driver);
		  hp.clickMyAccount();
		  hp.clickOnLogin();
				
		  //LoginPage
		  LoginPage lp = new LoginPage(driver);
		  lp.setEmail(email);
		  lp.setPassword(pwd);
		  lp.clickLogin();
				
		  //MyAccountPage
		  MyAccountPage mp =new MyAccountPage(driver);
		  boolean targetpage=mp.isMyAccountPageExists();
		  
		  if(exp.equalsIgnoreCase("Valid"))
		  {
			  if(targetpage==true)
			  {
				  mp.clickLogout();
				  Assert.assertTrue(true);
			  }
			  else
			  {
				  Assert.assertTrue(false);
			  }
		  }
		  
		  if(exp.equalsIgnoreCase("Invalid"))
		  {
			  if(targetpage==true)
			  {
				  mp.clickLogout();
				  Assert.assertTrue(false);
			  }
			  else
			  {
				  Assert.assertTrue(true);
			  }
		  }
		  
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		  	
		  logger.info("***** Finished TC003_LoginDDT *****");
	}
	

}
