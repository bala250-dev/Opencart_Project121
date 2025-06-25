package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_Logintest extends BaseClass{
	
	@Test(groups={"Sanity","Master"})
	public void verify_Login()
	{
		logger.info("***** Starting TC002_Logintest ******");
		
		try
		{
		//HomePage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickOnLogin();
		
		//LoginPage
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		//MyAccountPage
		MyAccountPage mp =new MyAccountPage(driver);
		boolean targetpage=mp.isMyAccountPageExists();
		
		Assert.assertEquals(targetpage, true, "Login failed");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("***** Finished TC002_Logintest ******");
		
	}
	


}
