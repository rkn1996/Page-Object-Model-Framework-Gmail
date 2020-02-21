package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage 
{
    public WebDriver driver;
    
    @FindBy(how=How.NAME,using="password")
    public WebElement pwd;
    
    @FindBy(how=How.XPATH,using="//*[text()='Next']")
    public WebElement pwdnext;
    
    @FindBy(how=How.XPATH,using="//*[text()='Enter a password']")
    public WebElement blankpwderr;
    
    @FindBy(how=How.XPATH,using="//*[contains(text(),'Wrong password')]")
    public WebElement invalidpwderr;
    
    public LoginPage(WebDriver driver)
    {
    	this.driver=driver;
    	PageFactory.initElements(driver, this);
    }
    
    public void pwdfill(String x)
    {
    	pwd.sendKeys(x);
    }
    
    public void pwdnextclick()
    {
    	pwdnext.click();
    }    
    
}
