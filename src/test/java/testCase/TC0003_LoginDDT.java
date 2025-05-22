package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.baseClass;
import utilities.DataProviders;

public class TC0003_LoginDDT extends baseClass
{
	// Geting data from dataprovider when dataprovider stored in different class
	@Test(dataProvider = "loginData", dataProviderClass = DataProviders.class)
	public void verify_loginDDT(String email, String pwd, String exp)
	{
		logger.info("***** Starting TC0002_accountLogin *****");
		
		HomePage hp = new HomePage(driver);
		
		logger.info("Clicked on My Account...!");
		hp.clickMyAccount();
		
		logger.info("Clicked on Login Link...!");
		hp.linkLogin();
		
		LoginPage lp = new LoginPage(driver);
		lp.setLogEmail(email);
		lp.setLogpass(pwd);
		
		
		logger.info("Clicked on Login...!");
		lp.clickButn_Login();
		
		//My Account page
		MyAccountPage myacc = new MyAccountPage(driver);
		boolean tragetPage = myacc.isMyAccountPageExist();
		Assert.assertTrue(tragetPage);	
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(tragetPage == true)
			{
				myacc.clickLogout();
				Assert.assertTrue(true);
				
			}
			else
			{
				Assert.assertTrue(false);
			}
	
		}
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(tragetPage == true)
			{
				myacc.clickLogout();
				Assert.assertTrue(false);
				
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		
	}
	
	
	

}
