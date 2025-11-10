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
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import objectRepositories.CreateCampaignPage;
import objectRepositories.campaignsPage;
import objectRepositories.HomePage;
import objectRepositories.LoginPage;

public class CreateCampaignWithExpectedCloseDateTest {

	public static void main(String[] args) throws Throwable {

		// Read data from properties file
//		FileInputStream fis1 = new FileInputStream("C:\\Users\\Nihal\\Desktop\\Ammie\\Temp\\Advance Selenium\\CommonData.properties.txt");
//		Properties prop = new Properties();
//		prop.load(fis1);
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

		// Generate date after 30 days
//		Date date = new Date();
//		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
//		sim.format(date);
//		Calendar cal = sim.getCalendar();
//		cal.add(Calendar.DAY_OF_MONTH, 34);
//		String requiredDate = sim.format(cal.getTime());

		JavaUtility jLib = new JavaUtility();
		String DateAfterNoOfDays = jLib.getRequiredDate(60);

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

		// Call implicitWait method from WebDriverUtility to include implicit wait
		wLib.implicitWait(driver);

//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

//		// Login
//		driver.get(URL);
//		driver.findElement(By.id("username")).sendKeys(USERNAME);
//		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
//		driver.findElement(By.xpath("//button[text()='Sign In']")).click();

		// Using POM class loginPage to call method to login to App
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(URL, USERNAME, PASSWORD);

//		// Read Test Data From Excel File
//
//		FileInputStream fis = new FileInputStream("./src/main/resources/TestScriptData_NINZA_CRM.xls");
//
//		Workbook wb = WorkbookFactory.create(fis);
//
//		String CAMPAIGN_NAME = wb.getSheet("Campaign").getRow(11).getCell(2).getStringCellValue();
//
//		String TARGET_SIZE = wb.getSheet("Campaign").getRow(11).getCell(3).getStringCellValue();

		// Read Test Script Data from Excel File
		// Call method from ExcelFileUtility class

		ExcelFileUtility EFU = new ExcelFileUtility();
		String CAMPAIGN_NAME = EFU.readDataFromExcelFile("Campaign", 11, 2);
		String TARGET_SIZE = EFU.readDataFromExcelFile("Campaign", 11, 3);

		// Create Campaign
//		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		
		// Create object of POM (objectRepository) class and call the method to get
		// createCampaign btn and click
		campaignsPage campaignsPage = new campaignsPage(driver);
		campaignsPage.getAddCreateCampaignBtn().click();
		
//		driver.findElement(By.name("campaignName")).sendKeys(CAMPAIGN_NAME);
//		WebElement targetSize = driver.findElement(By.name("targetSize"));
//		targetSize.clear();
//		targetSize.sendKeys(TARGET_SIZE);
//
////		driver.findElement(By.name("expectedCloseDate")).sendKeys(requiredDate);
//		driver.findElement(By.name("expectedCloseDate")).sendKeys(DateAfterNoOfDays);
//
//		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();

		//Create Campaign using POM class methods
		CreateCampaignPage createCampaignPage = new CreateCampaignPage(driver);
		createCampaignPage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		createCampaignPage.getTargetSizeTF().clear();
		createCampaignPage.getTargetSizeTF().sendKeys(TARGET_SIZE);
		createCampaignPage.getExpectedCloseDateTF().sendKeys(DateAfterNoOfDays);
		createCampaignPage.getCreateCampaignBtn().click();
		
		
		// Verification
		HomePage homePage = new HomePage(driver);

		WebElement toastMsg = homePage.getToastMsg();

		// Call method from WebDriverUtility to wait for ElementToBeVisible
		// pass the arguments as driver reference and webelement
		wLib.waitUntilElementToBeVisible(driver, toastMsg);

//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//		wait.until(ExpectedConditions.visibilityOf(toastMsg));
		if (toastMsg.getText().contains("Successfully Added"))
			System.out.println("Campaign Created");
		else
			System.out.println("Campaign Not Created");
//		driver.findElement(By.xpath("//button[@aria-label='close']")).click();
		homePage.getCloseToastMsg().click();

//		// Logout
//		WebElement UserIcon = driver.findElement(By.xpath("//div[@class='user-icon']"));
//
////		Actions action = new Actions(driver);
////		action.moveToElement(UserIcon).perform();
//
//		// Call mouseHoverOnWebElement method from WebDriverUtility class
//		wLib.mouseHoverOnWebElement(driver, UserIcon);
//
//		WebElement Logout = driver.findElement(By.xpath("//div[text()='Logout ']"));
////		action.moveToElement(Logout).click().perform();
//
//		// Call clickOnWebElement method from WebDriverUtility class
//		wLib.clickOnWebElement(driver, Logout);

		homePage.logout();

		// Close the browser
		driver.quit();

	}

}
