package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ComposePage 
{
public WebDriver driver;
      
    @FindBy(how=How.XPATH,using="//*[text()='Compose']")
    public WebElement comp;
    
    @FindBy(how=How.XPATH,using="//*[contains(@aria-label,'Google Account')]/span")
    public WebElement profilepic;
    
    @FindBy(how=How.XPATH,using="//*[text()='Sign out']")
    public WebElement signout;
    
    public ComposePage(WebDriver driver)
    {
    	this.driver=driver;
    	PageFactory.initElements(driver, this);
    }
    
    public void profilepicclick()
    {
    	profilepic.click();
    }
    
    public void signoutclick()
    {
    	signout.click();
    }       
}
