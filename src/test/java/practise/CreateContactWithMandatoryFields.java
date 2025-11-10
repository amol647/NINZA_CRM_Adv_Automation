package practise;


import java.util.HashMap;
import java.util.Map;

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
import objectRepositories.ContactsPage;
import objectRepositories.CreateCampaignPage;
import objectRepositories.SelectCampaignPage;
import objectRepositories.campaignsPage;
import objectRepositories.createContactPage;
import objectRepositories.HomePage;
import objectRepositories.LoginPage;


public class CreateContactWithMandatoryFields {

	public static void main(String[] args) throws Exception {

		// Create campaign
		// Read common data from properties file
		PropertyFileUtility pLib = new PropertyFileUtility();
		String BROWSER = pLib.ReadDataFromPropertyFile("Browser");
		String URL = pLib.ReadDataFromPropertyFile("URL");
		String USERNAME = pLib.ReadDataFromPropertyFile("Username");
		String PASSWORD = pLib.ReadDataFromPropertyFile("Password");

		// Read test script data from excel
		ExcelFileUtility eLib = new ExcelFileUtility();
		String CAMPAIGN_NAME = eLib.readDataFromExcelFile("Contacts", 1, 2);
		String TARGET_SIZE = eLib.readDataFromExcelFile("Contacts", 1, 3);
		String ORGANIZATION = eLib.readDataFromExcelFile("Contacts", 1, 4);
		String TITLE = eLib.readDataFromExcelFile("Contacts", 1, 5);
		String CONTACT_NAME = eLib.readDataFromExcelFile("Contacts", 1, 6);

		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();

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
		wLib.implicitWait(driver);

		// Login
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToApp(URL, USERNAME, PASSWORD);

		// Create Campaign
		campaignsPage campaignsPage = new campaignsPage(driver);
		campaignsPage.getAddCreateCampaignBtn().click();
		CreateCampaignPage createCampaignPage=new CreateCampaignPage(driver);
		createCampaignPage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		createCampaignPage.getTargetSizeTF().clear();
		createCampaignPage.getTargetSizeTF().sendKeys(TARGET_SIZE);
		createCampaignPage.getCreateCampaignBtn().click();
		HomePage homePage = new HomePage(driver);
		homePage.getCloseToastMsg().click();
		
		// Click on contacts
		homePage.getContactsLink().click();

		// click on +create contact
         ContactsPage contactsPage=new ContactsPage(driver);
         contactsPage.getAddCreateContactBtn().click();
         
		// enter the manadatory fields
         createContactPage createContactPage=new createContactPage(driver);
         createContactPage.getOrganizationNameTF().sendKeys(ORGANIZATION);
         createContactPage.getTitleTF().sendKeys(TITLE);
         createContactPage.getContactNameTF().sendKeys(CONTACT_NAME);
         createContactPage.getMobileTF().sendKeys("9" + jLib.generateNineDigitNumber());
         createContactPage.getPlusBtn().click();
//		driver.findElement(http://By.name("organizationName")).sendKeys(ORGANIZATION);
//		driver.findElement(http://By.name("title")).sendKeys(TITLE);
//		driver.findElement(http://By.name("contactName")).sendKeys(CONTACT_NAME);
//		driver.findElement(http://By.name("mobile")).sendKeys("9" + jLib.generateNineDigitNumber());
//
//		// click on plus button
//		driver.findElement(By.xpath("//*[name()='svg'and @data-icon='plus']")).click();

		// get parentId
		String parentId = driver.getWindowHandle();

		// Switch the driver control to child
//		String parentId = driver.getWindowHandle();
//		Set<String> allIds = driver.getWindowHandles();
//		allIds.remove(parentId);
//		for (String id : allIds) {
//			driver.switchTo().window(id);
//			if (driver.getTitle().contains("Select Campaign"))
//				break;
//		}
//		wLib.switchDriverControlOnTitle(driver, "Select Campaign");
		wLib.switchDriverControlOnCurrentURL(driver, "selectCampaign.html");

		// select campaignName from drop down
		SelectCampaignPage selectCampaignPage=new SelectCampaignPage(driver);
		WebElement campaignDD =selectCampaignPage.getCampaignDD();
//		WebElement campaignDD = driver.findElement(http://By.id("search-criteria"));
//		Select obj = new Select(campaignDD);
//		obj.selectByValue("campaignName");
		//wLib.select(campaignDD, "campaignName");

		// Enter Camapign name in search text field
		selectCampaignPage.getSearchTF().sendKeys(CAMPAIGN_NAME);
//		driver.findElement(http://By.id("search-input")).sendKeys(CAMPAIGN_NAME);

		// click on select button
//		WebElement selectBtn = driver.findElement(By.className("select-btn"));
		WebElement selectBtn =selectCampaignPage.getSelectBtn();
		wLib.waitUntilElementToBeVisible(driver, selectBtn);
		http://selectBtn.click();

		// switch the driver control to parent
		driver.switchTo().window(parentId);

		// click on create contact
		createContactPage.getCreateContactBtn().click();
//		driver.findElement(By.xpath("//button[text()='Create Contact']")).click();

		// Verification
		WebElement toastMsg = homePage.getToastMsg();
		wLib.waitUntilElementToBeVisible(driver, toastMsg);
		if (toastMsg.getText().contains("Successfully Added"))
			System.out.println("Contact Created");
		else
			System.out.println("Contact Not Created");
		homePage.getCloseToastMsg().click();

		// Logout
		homePage.logout();

		// Close the browser



driver.quit();
	}

}
