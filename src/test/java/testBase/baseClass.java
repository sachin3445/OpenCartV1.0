package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager; // these are two import for log
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class baseClass {

public static WebDriver driver;
public Logger logger;
public Properties p;
	
	@SuppressWarnings("deprecation")
	@BeforeClass (groups = {"Regression", "Master", "Sanity"})
	@Parameters({"os", "browser"})
	public void setup(String os, String br) throws IOException, MalformedURLException, InterruptedException
	{
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
				
		logger= LogManager.getLogger(this.getClass());
		
		// run Grid
		if(p.getProperty("execution_Env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilites=new DesiredCapabilities();
			
			//os
			
			if(os.equalsIgnoreCase("Window"))
			{
				capabilites.setPlatform(Platform.WIN10);
			}
			else if(os.equalsIgnoreCase("mac")) 
			{
				capabilites.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No matching Operating System found");
				return;
			}
			
			//browser
			switch(br.toLowerCase())
			{
			case "chrome":capabilites.setBrowserName("chrome"); break;
			case "edge":capabilites.setBrowserName("MicrosoftEdge"); break;
			default : System.out.println("Invalid browser"); return;
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilites);
		} 
		
		// run code on local
		if(p.getProperty("execution_Env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
			case "chrome": driver = new ChromeDriver(); break;
			case "edge": driver = new EdgeDriver(); break;
			case "firefox" : driver = new FirefoxDriver(); break;
			default : System.out.println("Invalid browser"); return;
			}
		} 
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get(p.getProperty("url"));	
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}	
	
	@AfterClass (groups = {"Regression", "Master", "Sanity"})
	public void teardown()
	{
		driver.quit();
	}
	
	
	public String randomString ()
	{
		@SuppressWarnings("deprecation")
		String randomalphabet = RandomStringUtils.randomAlphabetic(3);
		return randomalphabet;
		
	}
		
	public String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyymmddHHmmss");
        return now.format(formatter);
    }
	
	public String randomnumber()
	{
		@SuppressWarnings("deprecation")
		String randomnum = RandomStringUtils.randomNumeric(10);
		return randomnum;
	}
	
	public String captureScreenShot(String tname) throws IOException
	{
		String timeStamp = new SimpleDateFormat("yyyy.mm.dd.HH.mm.ss").format(new Date());
		TakesScreenshot takeScreenShot= (TakesScreenshot) driver;
		File sourcefile = takeScreenShot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir") + "\\ScreenShot\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File (targetFilePath);
		sourcefile.renameTo(targetFile);
		
		
		return targetFilePath;
		
	}
	
}
