package Utilities;

import static org.junit.Assert.fail;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.sikuli.script.FindFailed;
import org.sikuli.script.SikuliException;
import org.xml.sax.SAXException;
import com.relevantcodes.extentreports.LogStatus;

public class SharedUtilities extends MainUtilities{

	public static void assertElementImageWith(String sikuliPicName) throws FindFailed, ParserConfigurationException, SAXException, IOException {
		try {
			screen.find(loadFromXml("SikuliPathe") + sikuliPicName + ".PNG");
			test.log(LogStatus.PASS, "Sikuli Found Elemenet");
		}catch (SikuliException e) {
			test.log(LogStatus.FAIL, e.getMessage() + "Sikuli fail to assert" + test.addScreenCapture(takeScreenShot()));
			fail("Sikuli fail to assert");
		}
	}
	
	public static void selectDropDown(WebElement elem , String value) throws IOException, ParserConfigurationException, SAXException {
		try {
			Select mySelection = new Select(elem);
			mySelection.selectByValue(value);
			test.log(LogStatus.PASS, "Element " + value + " select");
		}catch (Exception e) {
			test.log(LogStatus.FAIL, e.getMessage() + test.addScreenCapture(takeScreenShot()));
			fail("selectDropDown Fail");
		}
	}
	
	public static void mouseHover(WebElement elem1 , WebElement elem2) throws InterruptedException, IOException, ParserConfigurationException, SAXException {
		try {
			actions.moveToElement(elem1).build().perform();
			test.log(LogStatus.PASS, "mouse go to first element");
			wait.until(ExpectedConditions.elementToBeClickable(elem2));
			actions.moveToElement(elem2).click().build().perform();
			test.log(LogStatus.PASS, "mouse click seconde element");
			Thread.sleep(1000);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, e.getMessage() + test.addScreenCapture(takeScreenShot()));
			fail("mouseHover fail");
		}
	}
	
	public static void scrollToElement(WebElement elem) throws InterruptedException, IOException, ParserConfigurationException, SAXException {
			try {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);" , elem);
				Thread.sleep(1000);
				test.log(LogStatus.PASS, "scrolling to element");
			} catch (Exception e) {
				test.log(LogStatus.FAIL, e.getMessage() + test.addScreenCapture(takeScreenShot()));
				fail("scrolling to element fail");
			}
	}
	
	public static void elementClick(WebElement elem) throws IOException, ParserConfigurationException, SAXException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(elem));
			elem.click();
			test.log(LogStatus.PASS, elem.getText() + " element click");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, e.getMessage() + test.addScreenCapture(takeScreenShot()));
			fail(elem +  " not click");
		}
	} 
	
	
	public static String getElementText(WebElement elem) {
		return elem.getText();
	}
	
	public static void elementSendKey(WebElement elem , String text) throws IOException, ParserConfigurationException, SAXException {
		try {
			elem.sendKeys(text);
			test.log(LogStatus.PASS, "send keys to element");
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL, e.getMessage() + test.addScreenCapture(takeScreenShot()));
			fail(elem + "fail to send keys");
		}
	}

}
