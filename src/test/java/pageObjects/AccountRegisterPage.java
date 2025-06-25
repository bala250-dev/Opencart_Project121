package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegisterPage extends BasePage {
	
	public AccountRegisterPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastname;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkdpolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	public void setFirstname(String fname)
	{
		txtFirstname.sendKeys(fname);
	}
	
	public void setLastname(String lname)
	{
		txtLastname.sendKeys(lname);
	}
	
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	public void setTelephoneNumber(String number)
	{
		txtTelephone.sendKeys(number);
	}
	
	public void SetPassword(String psw)
	{
		txtPassword.sendKeys(psw);
	}
	
	public void SetConfirmPassword(String psw)
	{
		txtConfirmPassword.sendKeys(psw);
	}
	
	public void SelectCheckBox()
	{
		chkdpolicy.click();
	}
	
	public void ClickContinue()
	{
		//solution 1
		btnContinue.click();
		
		//solution 2
		//btnContinue.submit();
		
		//sol3
		//Actions act=new Actions(driver);
		//act.moveToElement(btnContinue).click().perform();
	}
	
	public String getConfirmation()
	{
		try 
		{
			return(msgConfirmation.getText());
		} 
		catch (Exception e) 
		{
			return (e.getMessage());
		}
	}
	
	
	
	
	
	
	

}
