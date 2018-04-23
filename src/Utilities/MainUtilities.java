package Utilities;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Screen;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


public class MainUtilities {
	
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static Screen screen;
	public static Actions actions;
	public static WebDriverWait wait;
	public static Logger loggerMainUtilities;
	public static Logger loggerMySql;
	public static PageObject.HomePage homePage;
	public static PageObject.MyAccountPage myAccountPage;
	public static PageObject.SingInPage singInPage;

	public static void initBrowser(String BrowserType) throws ParserConfigurationException, SAXException, IOException {
		switch(BrowserType.toLowerCase()) {
		case "chrome":
			driver = initChromeDriver();
			break;
		case "ff":
			driver = initFifeFoxDriver();
			break;
		case "edge":
			driver = initEdgeDriver();
			break;
		}
		wait = new WebDriverWait(driver, 10);
		screen = new Screen();
		actions = new Actions(driver);
		driver.get(loadFromXml("URL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		loggerMainUtilities.info("initBrowser");
	}
	
	public static WebDriver initChromeDriver() throws ParserConfigurationException, SAXException, IOException {
		System.setProperty("webdriver.chrome.driver",loadFromXml("ChromeDriverPath"));
		WebDriver driverChrome = new ChromeDriver();
		loggerMainUtilities.info("initChromeDriver");
		return driverChrome;
	}
	
	public static WebDriver initFifeFoxDriver() throws ParserConfigurationException, SAXException, IOException {
		System.setProperty("webdriver.gecko.driver",loadFromXml("FireFoxDriverPath"));
		WebDriver driverFireFox = new FirefoxDriver();
		loggerMainUtilities.info("initFifeFoxDriver");
		return driverFireFox;
	}
	
	
	public static WebDriver initEdgeDriver() throws ParserConfigurationException, SAXException, IOException {
		System.setProperty("webdriver.edge.driver",loadFromXml("EdgeDriverPath"));
		WebDriver driverEdge = new EdgeDriver();
		loggerMainUtilities.info("initEdgeDriver");
		return driverEdge;
	}
	
	public static String loadFromXml (String nodeName) throws ParserConfigurationException, SAXException, IOException {
		File fXmlFile = new File("C:\\myProjectXML\\configuration.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile); 
		doc.getDocumentElement().normalize();
		loggerMainUtilities.info("get data from xml");
		return doc.getElementsByTagName(nodeName).item(0).getTextContent();
	}
	
	public static void initExtentReports() throws ParserConfigurationException, SAXException, IOException {
		extent = new ExtentReports(loadFromXml("ReportFilePath") + loadFromXml("ReportFileName") + currentTime() + ".html", true);
		loggerMainUtilities.info("initExtentReports");
	}

	public static void flushingReport() {
		extent.flush();
		extent.close();
		loggerMainUtilities.info("Report flusing and close");
	}

	public static void initTestReport(String testName , String description) {
		test = extent.startTest(testName , description);
		loggerMainUtilities.info("Start new test " + testName +"-" + description);
	}

	public static void endTestReport() {
		extent.endTest(test);
		loggerMainUtilities.info("endTestReport");
	}

	public static String takeScreenShot() throws IOException, ParserConfigurationException, SAXException {
		String path = loadFromXml("ScreenShotPath") + loadFromXml("ScreenShotName") + currentTime() + ".png";
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(path));
		loggerMainUtilities.info("takeScreenShot");
		return path;
	}
	
	public static String currentTime() {
		return new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss").format(new Timestamp(System.currentTimeMillis()));
	}
	
	public static void initLog4j() {
		loggerMainUtilities = Logger.getLogger("MainUtilities");
		loggerMySql = Logger.getLogger("MySql");
		loggerMainUtilities.info("**** Starting Log4J ****");

	}
	
	public static void driverQuit() {
		driver.quit();
		loggerMainUtilities.info("**** Driver Quit ****");
	}
}
