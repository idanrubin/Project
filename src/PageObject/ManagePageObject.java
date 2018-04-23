package PageObject;

import org.openqa.selenium.support.PageFactory;
import Utilities.MainUtilities;

public class ManagePageObject extends MainUtilities {
	
	public static void initObj() {
		homePage = PageFactory.initElements(driver, PageObject.HomePage.class);
		myAccountPage = PageFactory.initElements(driver, PageObject.MyAccountPage.class);
		singInPage = PageFactory.initElements(driver, PageObject.SingInPage.class);
	}
	

}
