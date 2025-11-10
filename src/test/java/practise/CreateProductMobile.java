package practise;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import objectRepositories.HomePage;
import objectRepositories.LoginPage;

public class CreateProductMobile {

	public static void main(String[] args) throws Exception {

		// Read data from properties file
				PropertyFileUtility pLib = new PropertyFileUtility();
				String BROWSER = pLib.ReadDataFromPropertyFile("Browser");
				String URL = pLib.ReadDataFromPropertyFile("URL");
				String USERNAME = pLib.ReadDataFromPropertyFile("Username");
				String PASSWORD = pLib.ReadDataFromPropertyFile("Password");

				// Read test script data from excel file
				ExcelFileUtility eLib = new ExcelFileUtility();
				String PRODUCT_NAME = eLib.readDataFromExcelFile("Products", 1, 2);
				String CATEGORY_DD = eLib.readDataFromExcelFile("Products", 1, 3);
				String QUANTITY = eLib.readDataFromExcelFile("Products", 1, 4);
				String PRICE = eLib.readDataFromExcelFile("Products", 1, 5);
				String VENDOR_ID_DD = eLib.readDataFromExcelFile("Products", 1, 6);

				JavaUtility jLib = new JavaUtility();
				WebDriverUtility wLib = new WebDriverUtility();

				// Launch the browser
				ChromeOptions settings = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<>();
				prefs.put("profile.password_manager_leak_detection", false);
				settings.setExperimentalOption("prefs", prefs);

				WebDriver driver = null;
				if (BROWSER.equalsIgnoreCase("Edge"))
					driver = new EdgeDriver();
				else if (BROWSER.equalsIgnoreCase("Chrome"))
					driver = new ChromeDriver(settings);
				else if (BROWSER.equalsIgnoreCase("Firefox"))
					driver = new FirefoxDriver();
				else if (BROWSER.equalsIgnoreCase("Safari"))
					driver = new SafariDriver();

				driver.manage().window().maximize();
				wLib.implicitWait(driver);

				// Login
				LoginPage loginPage=new LoginPage(driver);
				loginPage.loginToApp(URL,USERNAME,PASSWORD);

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
				if (msg.contains("Successfully Added"))
					System.out.println("Product Created");
				else
					System.out.println("Product Not Created");
				homepage.getCloseToastMsg().click();

				// Logout
				homepage.logout();
				
				// Close browser
				driver.quit();
		
	}

}
