package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage 
{
    public WebDriver driver;
    
    @FindBy(how=How.NAME,using="identifier")
    public WebElement uid;
    
    @FindBy(how=How.XPATH,using="//*[text()='Next']")
    public WebElement uidnext;
    
    @FindBy(how=How.XPATH,using="(//*[contains(text(),'Enter an email')])[2]")
    public WebElement blankuiderr;
    
    @FindBy(how=How.XPATH,using="((//*[contains(text(),'find your Google Account')])[2])|((//*[contains(text(),'Enter a valid email')])[2])")
    public WebElement invaliduiderr;
    
    public HomePage(WebDriver driver)
    {
    	this.driver=driver;
    	PageFactory.initElements(driver, this);
    }
    
    public void uidfill(String x)
    {
    	uid.sendKeys(x);
    }
    
    public void uidnextclick()
    {
    	uidnext.click();
    }    
}
