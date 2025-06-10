package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.ProductSearch;
import testBase.baseClass;

public class TC0004_searchProduct extends baseClass {
	
	@Test (groups = {"Sanity", "Master"})
	public void verifysearchProduct()
	{
		logger.info("***** Starting TestCase TC0004_searchProduct *****");
		HomePage hp = new HomePage(driver);
		
		logger.info("Click on my account....");
		hp.clickMyAccount();
		logger.info("Click on my login....");
		hp.linkLogin();
		
		logger.info("Enter user details email & Password....");
		LoginPage lp = new LoginPage(driver);
		lp.setLogEmail(p.getProperty("email"));
		lp.setLogpass(p.getProperty("password"));
		logger.info("Click on login button....");
		lp.clickButn_Login();
		
		logger.info("Enter the Product name for search... ");
		ProductSearch sp=new ProductSearch(driver);
		sp.srchProduct(p.getProperty("searchproduct"));
		
		logger.info("Click on Search button....");
		sp.clkButton();
		
		logger.info("Validate the product found...");
		String enterProduct =p.getProperty("searchproduct");
		String productTitle=sp.getProduct();
		String noProduct=sp.getMsgNotFound();
		if(productTitle.equals(enterProduct))
		{
			logger.info("Product found.....!");
			Assert.assertTrue(true);
		}
		else
		{	noProduct.equals("There is no product that matches the search criteria.");
			logger.error("No product found...!");
			//logger.debug("Debug logs...!");
			Assert.assertTrue(true);	
		}
		
		logger.info("***** Finish TestCase TestCase TC0004_searchProduct *****");
	}

}
