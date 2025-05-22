package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "loginData")
	public String [][] getData() throws IOException
	{
		//Get the file from test Data
		String path = ".\\testData\\LoginData.xlsx";
		ExcelUtility xlxuti = new ExcelUtility(path);
		
		int totalrows = xlxuti.getRowCount("sheet1");
		int totalcols = xlxuti.getCellCount("sheet1", 1);
		
		//creating two dimensional array to stored data
		String logindata[][] =new String [totalrows][totalrows];
		
		//Reading data from two dimensional array
		for(int i =1; i<=totalrows; i++)
		{
			for (int j=0; j<totalcols; j++) 
			{
				logindata[i-1][j] = xlxuti.getCellData("sheet1", i, j);
			}
		}
		
		return logindata;
	}
}
