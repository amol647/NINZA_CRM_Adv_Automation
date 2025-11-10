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

import genericUtilities.ExcelFileUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import objectRepositories.CreateCampaignPage;
import objectRepositories.campaignsPage;
import objectRepositories.HomePage;
import objectRepositories.LoginPage;

public class CreateCampaignWithMandatoryFieldsTest {

	public static void main(String[] args) throws InterruptedException, Throwable {

		ChromeOptions settings = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);
		settings.setExperimentalOption("prefs", prefs);

//		WebDriver driver = new ChromeDriver(settings);

		// Read Common data from Properties File

//		// Create a java representation object of Physical File
//		FileInputStream fis1 = new FileInputStream("C:\\Users\\Nihal\\Desktop\\Ammie\\Temp\\Advance Selenium\\CommonData.properties.txt");
//
//		// Create object of Properties File
//		Properties prop = new Properties();
//
//		// Load the data into prop
//		prop.load(fis1);
//
//		// Returns String and store it in a String Variable
//		String BROWSER = prop.getProperty("Browser");
//		String URL = prop.getProperty("URL");
//		String USERNAME = prop.getProperty("Username");
//		String PASSWORD = prop.getProperty("Password");

		// Read data from properties file
		// Call the method from PropertyFileUtility class
		PropertyFileUtility PFU = new PropertyFileUtility();
		String BROWSER = PFU.ReadDataFromPropertyFile("Browser");
		String URL = PFU.ReadDataFromPropertyFile("URL");
		String USERNAME = PFU.ReadDataFromPropertyFile("Username");
		String PASSWORD = PFU.ReadDataFromPropertyFile("Password");

		// Launche the Respective Browser as mentioned in Properties File

		WebDriver driver = null;
//		WebDriverManager.edgedriver().setup();

		if (BROWSER.equalsIgnoreCase("Chrome"))
			driver = new ChromeDriver(settings);

		else if (BROWSER.equalsIgnoreCase("Edge"))
			driver = new EdgeDriver();

		else if (BROWSER.equalsIgnoreCase("Firefox"))
			driver = new FirefoxDriver();

		else if (BROWSER.equalsIgnoreCase("Safari"))
			driver = new SafariDriver();

		else
			System.out.println("Failed to Launch Browser");

		driver.manage().window().maximize();

		WebDriverUtility wLib = new WebDriverUtility();

		// Call implicitWait method from WebDriverUtility to include implicit wait
		wLib.implicitWait(driver);

//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.get(URL);

		//Login
//		WebElement UserName = driver.findElement(By.id("username"));
//		WebElement Password = driver.findElement(By.id("inputPassword"));
//		WebElement SignInBtn = driver.findElement(By.xpath("//button[text()='Sign In']"));
//		UserName.sendKeys(USERNAME);
//		Password.sendKeys(PASSWORD);
//		SignInBtn.click();

		// Using POM class loginPage to call method to login to App
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(URL, USERNAME, PASSWORD);


//		// Read Test Script Data from Excel File
//
//		FileInputStream fis = new FileInputStream("./src/main/resources/TestScriptData_NINZA_CRM.xls");
//
//		Workbook wb = WorkbookFactory.create(fis);
//
//		String CAMPAIGN_NAME = wb.getSheet("Campaign").getRow(1).getCell(2).getStringCellValue();
//
//		String TARGET_SIZE = wb.getSheet("Campaign").getRow(1).getCell(3).getStringCellValue();

		// Click on Create Campaign
//		WebElement CreateCampaignBtn = driver.findElement(By.xpath("//span[text()='Create Campaign']"));
//		CreateCampaignBtn.click();
		
		//Create object of POM (objectRepository) class and call the method to get createCampaign btn and click
		campaignsPage campaignsPage = new campaignsPage(driver);
		campaignsPage.getAddCreateCampaignBtn().click();

		// Read Test Script Data from Excel File
		// Call method from ExcelFileUtility class

		ExcelFileUtility EFU = new ExcelFileUtility();
		String CAMPAIGN_NAME = EFU.readDataFromExcelFile("Campaign", 1, 2);
		String TARGET_SIZE = EFU.readDataFromExcelFile("Campaign", 1, 3);

		//Create Campaign
//		// Enter Campaign Name
//		driver.findElement(By.name("campaignName")).sendKeys(CAMPAIGN_NAME);
//
//		// Enter Target Size
//		driver.findElement(By.name("targetSize")).sendKeys(TARGET_SIZE);
//
//		// Click on Create Campaign Button
//		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();

		//Create Campaign using POM class methods
		CreateCampaignPage createCampaignPage = new CreateCampaignPage(driver);
		createCampaignPage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		createCampaignPage.getTargetSizeTF().clear();
		createCampaignPage.getTargetSizeTF().sendKeys(TARGET_SIZE);
		createCampaignPage.getCreateCampaignBtn().click();
		
		
		
		// Verify Toast Message
		HomePage homePage = new HomePage(driver);
		
		WebElement ActualToastMessage = homePage.getToastMsg();

		// Call method from WebDriverUtility to wait for ElementToBeVisible
		// pass the arguments as driver reference and webelement
		wLib.waitUntilElementToBeVisible(driver, ActualToastMessage);

//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.visibilityOf(ActualToastMessage));

		String ActualMessage = ActualToastMessage.getText();

		if (ActualMessage.contains("Successfully Added")) {
			System.out.println("Campaign Created Successfully and Verified");
		}

		else {
			System.out.println("Campaign Creation Failed");
		}

//		driver.findElement(By.xpath("//button[@aria-label='close']")).click();
		homePage.getCloseToastMsg().click();
		
		
		
		// Logout
		WebElement UserIcon = driver.findElement(By.xpath("//div[@class='user-icon']"));

//		Actions action = new Actions(driver);
//		action.moveToElement(UserIcon).perform();

		// Call mouseHoverOnWebElement method from WebDriverUtility class
		wLib.mouseHoverOnWebElement(driver, UserIcon);

		WebElement Logout = driver.findElement(By.xpath("//div[text()='Logout ']"));
//		action.moveToElement(Logout).click().perform();

		// Call clickOnWebElement method from WebDriverUtility class
		wLib.clickOnWebElement(driver, Logout);

		// Close the Browser
		driver.quit();
	}

}
