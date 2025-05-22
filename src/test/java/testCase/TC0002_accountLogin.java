package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.baseClass;


public class TC0002_accountLogin extends baseClass
{
	@Test (groups = {"Regression", "Master"})
	public void verifyLogin ()
	{
	
		logger.info("***** Starting TC0002_accountLogin *****");
		try {
		HomePage hp = new HomePage(driver);
		
		logger.info("Clicked on My Account...!");
		hp.clickMyAccount();
		
		logger.info("Clicked on Login Link...!");
		hp.linkLogin();
		
		LoginPage lp = new LoginPage(driver);
		lp.setLogEmail(p.getProperty("email"));
		lp.setLogpass(p.getProperty("password"));
		
		
		logger.info("Clicked on Login...!");
		lp.clickButn_Login();
		
		//My Account page
		MyAccountPage myacc = new MyAccountPage(driver);
		boolean tragetPage = myacc.isMyAccountPageExist();
		Assert.assertTrue(tragetPage);	
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("***** Finish TestCase TC0002_accountLogin *****");
	}
}
