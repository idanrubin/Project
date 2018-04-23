package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import Utilities.MainUtilities;

public class MyAccountPage extends MainUtilities {
	
	public WebDriver driver;

	public MyAccountPage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(how = How.CLASS_NAME , using = "home")
	public WebElement homeButton;
	
	@FindBy(how = How.CLASS_NAME , using = "account")
	public WebElement accountName;
	
	
}

