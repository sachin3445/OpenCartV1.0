package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
		}
	

	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement pge_Myaccount;
	
	@FindBy(xpath="//div[@class='list-group']//a[text()='Logout']")
	WebElement link_logout;
	
	public boolean isMyAccountPageExist() {
		try 
			{
				return (pge_Myaccount.isDisplayed());
			} catch (Exception e)
			{
				return false;
			}
}
	
	public void	clickLogout()
	{
		link_logout.click();
	}
}