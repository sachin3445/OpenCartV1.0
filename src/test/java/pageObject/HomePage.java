package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage
{

	public HomePage(WebDriver driver) 
	{
		super(driver);
		
	}
	
@FindBy(xpath="//span[normalize-space()='My Account']")
WebElement linkMyAccount;
@FindBy(xpath="//a[normalize-space()='Register']")
WebElement linkRegister;
@FindBy(xpath="//a[normalize-space()='Login']")
WebElement linkLogin;

 public void clickMyAccount()
 {
	 linkMyAccount.click();
 }

 public void linkRegistred()
 {
	 linkRegister.click();
 }
 
 public void linkLogin()
 {
	 linkLogin.click();
 }
 
}
