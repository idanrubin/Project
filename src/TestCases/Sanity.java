package TestCases;

import java.io.IOException;
import java.sql.SQLException;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;
import org.sikuli.script.FindFailed;
import org.xml.sax.SAXException;
import PageObject.ManagePageObject;
import Utilities.MainUtilities;
import Utilities.SharedUtilities;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class Sanity extends MainUtilities {

	@Rule
	public TestName testName = new TestName();

	@BeforeClass
	public static void openSession() throws ParserConfigurationException, SAXException, IOException {
		initLog4j();
		initBrowser(loadFromXml("BrowserType"));
		initExtentReports();
		ManagePageObject.initObj();
	}

	@Before
	public void beforeTest() throws ParserConfigurationException, SAXException, IOException {
		initTestReport(testName.getMethodName().split("_")[0] , testName.getMethodName().split("_")[1]);
		driver.get(loadFromXml("URL"));
	}

	@Test
	public void test01_SingIn() throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException, SQLException {
		SharedUtilities.elementClick(homePage.loginButton);
		singInPage.singIn(loadFromXml("userName"));
		singInPage.assertUserSignedIn();
	}

	@Test
	public void test02_AddProductToCart() throws IOException, ParserConfigurationException, SAXException, InterruptedException {
		homePage.addProductToCart(homePage.bestSellers , homePage.tShirt);
		homePage.assertNumberProductInCartIs("1");
	}

	@Test
	public void test03_assertElementPicture() throws ParserConfigurationException, SAXException, IOException, FindFailed {
		homePage.searchBar(loadFromXml("searchInput"));
		SharedUtilities.assertElementImageWith(loadFromXml("PictureName"));
	}

	@After
	public void afterTest() {
		endTestReport();
	}

	@AfterClass
	public static void closeSession() {
		flushingReport();
		driverQuit();
	}
}