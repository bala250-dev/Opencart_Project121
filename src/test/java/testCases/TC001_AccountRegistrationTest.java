package testCases;


import org.testng.Assert;


import org.testng.annotations.Test;

import pageObjects.*;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		
		logger.info("***** Starting TC001_AccountRegistrationTest *****");
		
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		
		logger.info("Clicked on MyAccount Link ");
		
		hp.clickRegister();
		logger.info("Clicked OnRegister Link ");
		
		AccountRegisterPage regpage= new AccountRegisterPage(driver);
		
		logger.info("Providing customermdetails....");
		regpage.setFirstname(randomeString().toUpperCase());
		regpage.setLastname(randomeString().toUpperCase());
		regpage.setEmail(randomeString() + "@gmail.com");
		regpage.setTelephoneNumber(randomeNumber());
		
		String password=randomeAlphaNumberic();
		
		regpage.SetPassword(password);
		regpage.SetConfirmPassword(password);
		
		regpage.SelectCheckBox();
		regpage.ClickContinue();
		
		logger.info("Validating expected message...");
		String confirmation=regpage.getConfirmation();
		
		if(confirmation.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			
			logger.error("Test failed..");
			logger.debug("Debug logs..");
			Assert.assertTrue(false);
			
		}

		//Assert.assertEquals(confirmation, "Your Account Has Been Created!!!");
		} 
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("***** Finished TC001_AccountRegistrationTest *****");
		
	}

	

	
}
