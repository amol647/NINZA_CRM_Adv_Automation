package genericUtilities;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {

	
	public void implicitWait(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}
	
	
	public void waitUntilElementToBeVisible(WebDriver driver, WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void select(WebElement element, int index)
	{
		Select select = new Select(element);
		select.selectByIndex(index);	
		
	}
	
	public void select(WebElement element, String value)
	{
		Select select = new Select(element);
		select.selectByValue(value);	
		
	}
	
	public void select(String visibleText, WebElement element)
	{
		Select select = new Select(element);
		select.selectByVisibleText(visibleText);	
		
	}
	
	public void deselect(WebElement element, int index)
	{
		Select select = new Select(element);
		select.deselectByIndex(index);	
		
	}
	
	public void deselect(WebElement element, String value)
	{
		Select select = new Select(element);
		select.deselectByValue(value);	
		
	}
	
	public void deselect(String visibleText, WebElement element)
	{
		Select select = new Select(element);
		select.deselectByVisibleText(visibleText);	
		
	}
	
	public void mouseHoverOnWebElement(WebDriver driver, WebElement element)
	{
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}
	
	
	public void clickOnWebElement(WebDriver driver, WebElement element)
	{
		Actions action = new Actions(driver);
		action.moveToElement(element).click().perform();
	}
	
	public void switchDriverControlOnTitle(WebDriver driver, String title)
	{
		String parentId = driver.getWindowHandle();
		Set<String> allIds = driver.getWindowHandles();
		allIds.remove(parentId);
		for (String id : allIds) {
			driver.switchTo().window(id);
			if (driver.getTitle().contains(title))
				break;
		}
	}
	
	
	public void switchDriverControlOnCurrentURL(WebDriver driver, String URL)
	{
		String parentId = driver.getWindowHandle();
		Set<String> allIds = driver.getWindowHandles();
		allIds.remove(parentId);
		for (String id : allIds) {
			driver.switchTo().window(id);
			if (driver.getCurrentUrl().contains(URL))
				break;
		}
	}
	
	
	public void switchToFrame(WebDriver driver, int index)
	{
		driver.switchTo().frame(index);
	}
	
	
	public void switchToFrame(WebDriver driver, String nameOrId)
	{
		driver.switchTo().frame(nameOrId);
	}
	
	
	public void switchToFrame(WebDriver driver, WebElement frameElement)
	{
		driver.switchTo().frame(frameElement);
	}
	
	
	public void switchToAlertAndAccept(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	

	public void switchToAlertAndDismiss(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	

	public String switchToAlertAndGetText(WebDriver driver)
	{
		return driver.switchTo().alert().getText();
	}
	
	public void switchToAlertAndSendKeys(WebDriver driver, String text)
	{
		driver.switchTo().alert().sendKeys(text);
	}
	
	public void doubleClick(WebDriver driver, WebElement element)
	{
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}
	
	
	public void rightClick(WebDriver driver, WebElement element)
	{
		Actions action = new Actions(driver);
		action.contextClick(element).perform();
	}
	
	
	public void dragAndDrop(WebDriver driver, WebElement sourceElement, WebElement targetElement)
	{
		Actions action = new Actions(driver);
		action.dragAndDrop(sourceElement, targetElement).perform();
	}
	
	//clickandhold
	
	//release
	
	//draganddrop
	
	//QCO-GROGRD-M113
	//Grooming Code
	//10AM to 12PM
	
}
