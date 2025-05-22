package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.AccountRegistrationPage;
import pageObject.HomePage;
import testBase.baseClass;

public class TC0001_accountRegistration extends baseClass
{

	

	@Test (groups = {"Sanity", "Master"})
	public void Verify_Account_Registration()
	{
		try
		{
		logger.info("***** Starting TestCase TC0001_accountRegistration *****");
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on My Account...!");
		hp.linkRegistred();
		logger.info("Clicked on Registred...!");
		
		AccountRegistrationPage register = new AccountRegistrationPage (driver);
	
		logger.info("Providing customer Details...!");
		
		String fName = "Atul"+randomString().toLowerCase();
		String eMail = "atul+" + getCurrentDateTime() + "@gmail.com";
		String telPhone = randomnumber();	
		
		register.setFirstName(fName);
		register.setLastName("More");
		register.setEmail(eMail);
		register.setTelephone(telPhone);
		register.setPassworde("Sachin@123");
		register.setConfrimPassworde("Sachin@123");
		register.clickPolicy();
		register.clickContinue();
		
		logger.info("Validating message...!");
		String confmsg = register.getConfrimationmsg();
		if (confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else 
		{
			logger.error("Test Failed...!");
			logger.debug("Debug logs...!");
			Assert.assertTrue(false);	
		}
		
			//Assert.assertEquals(confmsg, "Your Account Has Been Created!");
			
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("***** Finish TestCase TC0001_accountRegistration *****");
	}
	
	

}



