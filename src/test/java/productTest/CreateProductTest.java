package productTest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import objectRepositories.HomePage;

public class CreateProductTest extends BaseClass{

	@Test (groups="smoke")
	public void createProductWithMandatoryFieldsTest() throws Exception
	{
		String PRODUCT_NAME = eLib.readDataFromExcelFile("Products", 1, 2);
		String CATEGORY_DD = eLib.readDataFromExcelFile("Products", 1, 3);
		String QUANTITY = eLib.readDataFromExcelFile("Products", 1, 4);
		String PRICE = eLib.readDataFromExcelFile("Products", 1, 5);
		String VENDOR_ID_DD = eLib.readDataFromExcelFile("Products", 1, 6);
		
		// Create Product Book
		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.xpath("//span[text()='Add Product']")).click();
		driver.findElement(By.name("productName")).sendKeys(PRODUCT_NAME + jLib.generateNineDigitNumber());// enter product
																										// name
		WebElement categoryDD = driver.findElement(By.name("productCategory"));
		Select category = new Select(categoryDD);
		category.selectByValue(CATEGORY_DD);// enter value-Electronics
		wLib.select(categoryDD, CATEGORY_DD);
		WebElement quantity = driver.findElement(By.name("quantity"));
		quantity.clear();
		quantity.sendKeys(QUANTITY);// enter quantity
		WebElement price = driver.findElement(By.name("price"));
		price.clear();
		price.sendKeys(PRICE);// enter price
		WebElement vendorIdDD = driver.findElement(By.name("vendorId"));
		Select vendorId = new Select(vendorIdDD);
		vendorId.selectByValue(VENDOR_ID_DD);// enter value-VID_448
		wLib.select(vendorIdDD, VENDOR_ID_DD);
		driver.findElement(By.xpath("//button[text()='Add']")).click();

		// Verify the toast msg
		HomePage homepage=new HomePage(driver);
		WebElement toastMsg = homepage.getToastMsg();
		wLib.waitUntilElementToBeVisible(driver, toastMsg);
		String msg = toastMsg.getText();
		
		Assert.assertTrue(msg.contains("Successfully Added"), "Toast Msg Verifiction Succeded");
		
		homepage.getCloseToastMsg().click();
	}
	
}
