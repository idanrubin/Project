package PageObject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.xml.sax.SAXException;
import com.relevantcodes.extentreports.LogStatus;
import Utilities.MainUtilities;
import Utilities.MySql;
import Utilities.SharedUtilities;

public class SingInPage extends MainUtilities {
	
	public WebDriver driver;
	
	public SingInPage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(how = How.ID , using = "email")
	public WebElement emailAddress;
	
	@FindBy(how = How.ID , using = "passwd")
	public WebElement password;

	@FindBy(how = How.ID , using = "SubmitLogin")
	public WebElement singInButton;
	
	@FindBy(how = How.CLASS_NAME , using = "page-heading")
	public WebElement pageHeading;
	
	
	public void singIn(String userName) throws ParserConfigurationException, SAXException, IOException {
		try {
			String arr[] = MySql.getEmailPassword(userName);
			SharedUtilities.elementSendKey(emailAddress, arr[0]);
			SharedUtilities.elementSendKey(password, arr[1]);
			SharedUtilities.scrollToElement(singInButton);
			singInButton.click();
			test.log(LogStatus.PASS, "sing in success");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, e.getMessage() + test.addScreenCapture(takeScreenShot()));
			fail();
		}
	}
	
	public void assertUserSignedIn() throws ParserConfigurationException, SAXException, IOException {
		try {
			assertEquals(loadFromXml("userName"), SharedUtilities.getElementText(myAccountPage.accountName));	
			test.log(LogStatus.PASS, "user equal");
		} catch (AssertionError ae) {
			test.log(LogStatus.FAIL, ae.getMessage() + test.addScreenCapture(takeScreenShot()));
			fail("user not equal");
		}
	}
	
}
