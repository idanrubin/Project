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
import Utilities.SharedUtilities;

public class HomePage extends MainUtilities {
	
	public WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(how = How.CLASS_NAME , using = "login")
	public WebElement loginButton;

	@FindBy(how = How.ID , using = "search_query_top")
	public WebElement searchField;

	@FindBy(how = How.NAME , using = "submit_search")
	public WebElement searchButton;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\'block_top_menu\']/ul/li[2]/a")
	public WebElement dresses;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\'block_top_menu\']/ul/li[2]/ul/li[3]/a")
	public WebElement summer;
	
	@FindBy(how = How.CLASS_NAME, using = "blockbestsellers")
	public WebElement bestSellers;

	@FindBy(how = How.XPATH, using = "//*[@id=\'blockbestsellers\']/li[2]/div")
	public WebElement tShirt;

	@FindBy(how = How.XPATH, using = "//*[@id=\'blockbestsellers\']/li[2]/div/div[2]/div[2]/a[1]")
	public WebElement addToCartButton; 
	
	@FindBy(how = How.XPATH , using = "//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a/span")
	public WebElement proceedToCheckout;

	@FindBy(how = How.XPATH , using = "//*[@id=\'layer_cart\']/div[1]/div[2]/div[4]/span/span")
	public WebElement continueShoppingButton;
	
	@FindBy(how = How.XPATH , using = "//*[@id=\'header\']/div[3]/div/div/div[3]/div/a/span[1]")
	public WebElement cartProductQuntity;	
	
	
	
	
	public void searchBar(String valueToSearch) throws ParserConfigurationException, SAXException, IOException {
		try {
			SharedUtilities.elementSendKey(homePage.searchField, valueToSearch);
			SharedUtilities.elementClick(homePage.searchButton);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, e.getMessage() + test.addScreenCapture(takeScreenShot()));
			fail();
		}
	}
	
	
	public void addProductToCart(WebElement category , WebElement productName) throws InterruptedException, IOException, ParserConfigurationException, SAXException {
		try {
			SharedUtilities.scrollToElement(category);
			SharedUtilities.elementClick(category);
			SharedUtilities.mouseHover(productName, homePage.addToCartButton);
			SharedUtilities.elementClick(homePage.continueShoppingButton);
			test.log(LogStatus.PASS, "add product to cart");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, e.getMessage() + test.addScreenCapture(takeScreenShot()));
			fail();
		}
	}	
	
	
	public void assertNumberProductInCartIs(String num) throws IOException, ParserConfigurationException, SAXException {
		try {
			assertEquals(num, cartProductQuntity.getText());
			test.log(LogStatus.PASS, "assertion number producrt in cart");			
		} catch (AssertionError ae) {
			test.log(LogStatus.FAIL, ae.getMessage() + test.addScreenCapture(takeScreenShot()));
			fail();
		}
	}
	
}
