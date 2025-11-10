package practise;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import genericUtilities.ExcelFileUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import objectRepositories.CreateCampaignPage;
import objectRepositories.campaignsPage;
import objectRepositories.HomePage;
import objectRepositories.LoginPage;

public class CreateCampaignWithStatus {
	
	public static void main(String[] args) throws Throwable {
		
		// Read data from properties file
//				FileInputStream fis1 = new FileInputStream("C:\\Users\\Nihal\\Desktop\\Ammie\\Temp\\Advance Selenium\\CommonData.properties.txt");
//				Properties prop = new Properties();
//				prop.load(fis1);
//				String BROWSER = prop.getProperty("Browser");
//				String URL = prop.getProperty("URL");
//				String USERNAME = prop.getProperty("Username");
//				String PASSWORD = prop.getProperty("Password");

		// Read data from properties file
		//Call the method from PropertyFileUtility class
		PropertyFileUtility PFU = new PropertyFileUtility();
		String BROWSER = PFU.ReadDataFromPropertyFile("Browser");
		String URL = PFU.ReadDataFromPropertyFile("URL");
		String USERNAME = PFU.ReadDataFromPropertyFile("Username");
		String PASSWORD = PFU.ReadDataFromPropertyFile("Password");
		
				// Launch the browser
				ChromeOptions settings = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<>();
				prefs.put("profile.password_manager_leak_detection", false);
				settings.setExperimentalOption("prefs", prefs);

				WebDriver driver = null;

				if (BROWSER.equalsIgnoreCase("chrome"))
					driver = new ChromeDriver(settings);
				else if (BROWSER.equalsIgnoreCase("edge"))
					driver = new EdgeDriver();
				else if (BROWSER.equalsIgnoreCase("firefox"))
					driver = new FirefoxDriver();
				else if (BROWSER.equalsIgnoreCase("safari"))
					driver = new SafariDriver();

				driver.manage().window().maximize();
				
				WebDriverUtility wLib = new WebDriverUtility();
				
				//Call implicitWait method from WebDriverUtility to include implicit wait
				wLib.implicitWait(driver);
				
//				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

//				// Login
//				driver.get(URL);
//				driver.findElement(By.id("username")).sendKeys(USERNAME);
//				driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
//				driver.findElement(By.xpath("//button[text()='Sign In']")).click();
				
				
				// Using POM class loginPage to call method to login to App
				LoginPage lp = new LoginPage(driver);
				lp.loginToApp(URL, USERNAME, PASSWORD);

				//Read data from Excel File
				
//				FileInputStream fis = new FileInputStream("C:\\Users\\Nihal\\Desktop\\Ammie\\Temp\\Advance Selenium\\TestScriptDataNinzaCRM.xls");
//				
//				Workbook wb = WorkbookFactory.create(fis);
//				
//				String CAMPAIGN_NAME = wb.getSheet("Campaign").getRow(6).getCell(2).getStringCellValue();
//				
//				String CAMPAIGN_STATUS = wb.getSheet("Campaign").getRow(6).getCell(3).getStringCellValue();
//				
//				String TARGET_SIZE = wb.getSheet("Campaign").getRow(6).getCell(4).getStringCellValue();
				
				//Click on Create Campaign Button
//				driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
				
				//Create object of POM (objectRepository) class and call the method to get createCampaign btn and click
				campaignsPage campaignsPage = new campaignsPage(driver);
				campaignsPage.getAddCreateCampaignBtn().click();
				
				//Read data from Excel File
				//Call method from ExcelFileUtility class

				ExcelFileUtility EFU = new ExcelFileUtility();
				String CAMPAIGN_NAME = EFU.readDataFromExcelFile("Campaign", 6, 2);
				String TARGET_SIZE = EFU.readDataFromExcelFile("Campaign", 6, 4);
				String CAMPAIGN_STATUS = EFU.readDataFromExcelFile("Campaign", 6, 3);
				
				// Create Campaign
				
//				driver.findElement(By.name("campaignName")).sendKeys(CAMPAIGN_NAME);
//				driver.findElement(By.name("campaignStatus")).sendKeys(CAMPAIGN_STATUS);
//				WebElement targetSize = driver.findElement(By.name("targetSize"));
//				targetSize.clear();
//				targetSize.sendKeys(TARGET_SIZE);
//				driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
				
				//Create Campaign using POM class methods
				CreateCampaignPage createCampaignPage = new CreateCampaignPage(driver);
				createCampaignPage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
				createCampaignPage.getCampaignStatus().sendKeys(CAMPAIGN_STATUS);
				createCampaignPage.getTargetSizeTF().clear();
				createCampaignPage.getTargetSizeTF().sendKeys(TARGET_SIZE);
				createCampaignPage.getCreateCampaignBtn().click();
				
				

				// Verification
				HomePage homePage = new HomePage(driver);
				
				WebElement toastMsg = homePage.getToastMsg();

				// Call method from WebDriverUtility to wait for ElementToBeVisible
				// pass the arguments as driver reference and webelement
				wLib.waitUntilElementToBeVisible(driver, toastMsg);

//				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//				wait.until(ExpectedConditions.visibilityOf(toastMsg));
				if (toastMsg.getText().contains("Successfully Added"))
					System.out.println("Campaign Created");
				else
					System.out.println("Campaign Not Created");
//				driver.findElement(By.xpath("//button[@aria-label='close']")).click();
				homePage.getCloseToastMsg().click();

				// Logout
				WebElement UserIcon = driver.findElement(By.xpath("//div[@class='user-icon']"));

//				Actions action = new Actions(driver);
//				action.moveToElement(UserIcon).perform();
				
				//Call mouseHoverOnWebElement method from WebDriverUtility class
				wLib.mouseHoverOnWebElement(driver, UserIcon);
				
				WebElement Logout = driver.findElement(By.xpath("//div[text()='Logout ']"));
//				action.moveToElement(Logout).click().perform();
				
				//Call clickOnWebElement method from WebDriverUtility class
				wLib.clickOnWebElement(driver, Logout);

				// Close the browser
				driver.quit();
	}

}
