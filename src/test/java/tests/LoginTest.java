package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.ComposePage;
import pages.HomePage;
import pages.LoginPage;
import utility.TestUtility;

public class LoginTest 
{
	public static void main(String[] args) throws Exception
	{
		//open excel file for test data reading
		File f=new File("E:\\Projects\\pomframeworkgmail.xlsx");
		FileInputStream fi=new FileInputStream(f);
		Workbook wb=WorkbookFactory.create(fi);
		//goto sheet1
		Sheet sh=wb.getSheet("Sheet1");
		int nour=sh.getPhysicalNumberOfRows();
		int nouc=sh.getRow(0).getPhysicalNumberOfCells();
		//create result column
		SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
		Date dt=new Date();
		sh.autoSizeColumn(nouc);
		CellStyle cs=wb.createCellStyle();
		cs.setWrapText(true);
		Cell rc=sh.getRow(0).createCell(nouc);
		rc.setCellStyle(cs);
		rc.setCellValue("Test results on "+sf.format(dt));
		//DDT in POM
		for(int i=1;i<=sh.getLastRowNum();i++)
		{
			Row r=sh.getRow(i);
			String bn=r.getCell(0).getStringCellValue();
			String u;
			try
			{
				u=r.getCell(1).getStringCellValue();
			}
			catch(Exception ex1)
			{
				u="";
			}
			String uc=r.getCell(2).getStringCellValue();
			String p;
			try
			{
				p=r.getCell(3).getStringCellValue();
			}
			catch(Exception ex2)
			{
				p="";
			}
			String pc=r.getCell(4).getStringCellValue();			
            //Create object to "TestUtility" Class
			TestUtility tu=new TestUtility();		
			//open browser
			WebDriver driver=tu.openBrowser(bn);
			//create object to page classes
			HomePage hp=new HomePage(driver);
			LoginPage lp=new LoginPage(driver);
			ComposePage cp=new ComposePage(driver);	
			//Launch site
			tu.launchsite(driver,"https://www.gmail.com");
			WebDriverWait w=new WebDriverWait(driver,20);
			w.until(ExpectedConditions.visibilityOf(hp.uid));
			//Enter userid and click next
			hp.uidfill(u);
			w.until(ExpectedConditions.elementToBeClickable(hp.uidnext));
			hp.uidnextclick();
			Thread.sleep(5000);
			try
			{
				if(uc.equalsIgnoreCase("blank") && hp.blankuiderr.isDisplayed())
				{
					Cell c=r.createCell(nouc);
					c.setCellStyle(cs);
					c.setCellValue("Login test passed for blank userid");
				}
				else if(uc.equalsIgnoreCase("invalid") && hp.invaliduiderr.isDisplayed())
				{
					Cell c=r.createCell(nouc);
					c.setCellStyle(cs);
					c.setCellValue("Login test passed for invalid userid");
				}
				else if(uc.equalsIgnoreCase("valid") && lp.pwd.isDisplayed())
				{
					//Enter Password and perform password validations
					lp.pwdfill(p);
					w.until(ExpectedConditions.elementToBeClickable(lp.pwdnext));
					lp.pwdnextclick();
					Thread.sleep(5000);
					if(pc.equalsIgnoreCase("blank") && lp.blankpwderr.isDisplayed())
					{
						Cell c=r.createCell(nouc);
						c.setCellStyle(cs);
						c.setCellValue("Login test passed for blank password");
					}
					else if(pc.equalsIgnoreCase("invalid") && lp.invalidpwderr.isDisplayed())
					{
						Cell c=r.createCell(nouc);
						c.setCellStyle(cs);
						c.setCellValue("Login test passed for invalid password");
					}
					else if(pc.equalsIgnoreCase("valid") && cp.comp.isDisplayed())
					{
						Cell c=r.createCell(nouc);
						c.setCellStyle(cs);
						c.setCellValue("Login test passed for valid userid and password");
						//do logout
						cp.profilepicclick();
						w.until(ExpectedConditions.elementToBeClickable(cp.signout));
						cp.signoutclick();
					}
					else
					{
						String f1=tu.pageScreenshot(driver);
						Cell c=r.createCell(nouc);
						c.setCellStyle(cs);
						c.setCellValue("pwd test failed,see "+f1);
				    }
				}
				else
				{
					String f2=tu.pageScreenshot(driver);
					Cell c=r.createCell(nouc);
					c.setCellStyle(cs);
					c.setCellValue("userid test failed,see "+f2);
				}
				//close site
				tu.closesite(driver);     
			}
			catch(Exception ex) 
			{
				String f3=tu.pageScreenshot(driver);
				System.out.println(i+"th time,problem occured,see"+f3+" "+ex.getMessage());
			}			
		}
		//Save and close existing Excel file with test results
	    FileOutputStream fo=new FileOutputStream(f);
		wb.write(fo);
	    fi.close();
	    fo.close();
	    wb.close();
	}
}
