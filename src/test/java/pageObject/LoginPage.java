package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage
{

	public LoginPage(WebDriver driver) {
		super(driver);
		}

@FindBy(xpath="//input[@id='input-email']")
WebElement txt_LogEmail;
@FindBy(xpath="//input[@id='input-password']")
WebElement txt_Logpass;
@FindBy(xpath="//input[@value='Login']")
WebElement butn_Login;



public void setLogEmail(String logInEmail)
{
	txt_LogEmail.sendKeys(logInEmail);
}

public void setLogpass(String logInPass)
{
	txt_Logpass.sendKeys(logInPass);
}

public void clickButn_Login()
{
	butn_Login.click();
}

}



