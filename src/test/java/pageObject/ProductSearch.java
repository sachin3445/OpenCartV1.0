package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductSearch extends BasePage
{
	public ProductSearch(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	//Constructor
	
	@FindBy(xpath="//input[@name='search']")
	WebElement txt_SearchProduct;
	
	@FindBy(xpath="//span[@class='input-group-btn']")
	WebElement btn_Search;
	
	@FindBy(xpath="//div[@class='caption']//h4//a[1]")
	WebElement getProductTitle;
	
	@FindBy(xpath="//p[normalize-space()='There is no product that matches the search criteria.']")
	WebElement getMsgNotFound;
	
	public void srchProduct(String productName) 
	{
		txt_SearchProduct.sendKeys(productName);;
	}
	
	public void clkButton()
	{
		btn_Search.click();
	}
	
	public String getProduct() {
		try 
			{
				return (getProductTitle.getText());
			} catch (Exception e)
			{
				return (e.getMessage());
			}
			
		}
	
	public String getMsgNotFound() {
		try 
			{
				return (getMsgNotFound.getText());
			} catch (Exception e)
			{
				return (e.getMessage());
			}
			
		}
	
}
