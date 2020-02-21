package utility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestUtility 
{
	public WebDriver openBrowser(String bn)
	{
		WebDriver driver;
		if(bn.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(bn.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if(bn.equalsIgnoreCase("opera"))
		{
			OperaOptions oo=new OperaOptions();
			oo.setBinary("C:\\Users\\USER\\AppData\\Local\\Programs\\Opera\\65.0.3467.78\\opera.exe");
			WebDriverManager.operadriver().setup();
			driver=new OperaDriver(oo);
		}
		else if(bn.equalsIgnoreCase("edge"))
		{
			EdgeOptions eo=new EdgeOptions();
			eo.setBinary("C:\\Program Files (x86)\\microsoft\\Edge Beta\\Application\\msedge.exe");
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver(eo);
		}
		else
		{
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();
		}
		driver.manage().window().maximize();
		return(driver);		
	}
	
    public void launchsite(WebDriver driver,String url)
    {
    	driver.get(url);
    }
 
    public void closesite(WebDriver driver)
    {
    	driver.close();
    }
    
    public String pageScreenshot(WebDriver driver) throws Exception
    {
    	SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
    	Date dt=new Date();
    	String ssname=sf.format(dt)+".png";
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest=new File(ssname);
		FileHandler.copy(src,dest);
		return(dest.getAbsolutePath());
    			
    }       
}
