package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage
{

	public AccountRegistrationPage(WebDriver driver) 
	{
		super(driver);
	}
	

@FindBy(xpath="//input[@placeholder='First Name']")
WebElement txt_FirstName;
	
@FindBy(xpath = "//input[@placeholder='Last Name']")
WebElement txt_LastName;

@FindBy(xpath = "//input[@placeholder='E-Mail']")
WebElement txt_Email;

@FindBy(xpath = "//input[@placeholder='Telephone']")
WebElement txt_Telephone;

@FindBy(xpath = "//input[@placeholder='Password']")
WebElement txt_Password;

@FindBy(xpath = "//input[@placeholder='Password Confirm']")
WebElement txt_PasswordConfirm;

@FindBy(xpath = "//input[@name='agree']")
WebElement chk_Policy;

@FindBy(xpath = "//input[@type='submit']")
WebElement btn_Continue;

@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
WebElement confirm_Msg;

public void setFirstName(String FirstName)
{
	txt_FirstName.sendKeys(FirstName);
}

public void setLastName(String LastName)
{
	txt_LastName.sendKeys(LastName);
}

public void setEmail(String email)
{
	txt_Email.sendKeys(email);
}

public void setTelephone(String telphone)
{
	txt_Telephone.sendKeys(telphone);
}

public void setPassworde(String pwd)
{
	txt_Password.sendKeys(pwd);
}

public void setConfrimPassworde(String pwd)
{
	txt_PasswordConfirm.sendKeys(pwd);
}

public void clickPolicy()
{
	chk_Policy.click();
}

public void clickContinue()
{
	btn_Continue.click();
}

public String getConfrimationmsg() {
try 
	{
		return (confirm_Msg.getText());
	} catch (Exception e)
	{
		return (e.getMessage());
	}
	
}

}
