package campaignTest;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
//org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import objectRepositories.CreateCampaignPage;
import objectRepositories.HomePage;
import objectRepositories.LoginPage;
import objectRepositories.campaignsPage;
import objectRepositories.campaignsPage;

@Listeners(genericUtilities.ListenerImplementation.class)

class CreateCampaignTest extends BaseClass{

	@Test (groups="smoke")
	public void createCampaignWithMandatoryFieldsTest() throws IOException {

		String CAMPAIGN_NAME = eLib.readDataFromExcelFile("Campaign", 1, 2);
		String TARGET_SIZE = eLib.readDataFromExcelFile("Campaign", 1, 3);

		// Create Campaign with mandatory fields
		campaignsPage campaignsPage = new campaignsPage(driver);
		campaignsPage.getAddCreateCampaignBtn().click();
		CreateCampaignPage createCampaignPage = new CreateCampaignPage(driver);
		createCampaignPage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		createCampaignPage.getTargetSizeTF().clear();
		createCampaignPage.getTargetSizeTF().sendKeys(TARGET_SIZE);
		createCampaignPage.getCreateCampaignBtn().click();

		// Verify the toast msg
		HomePage homepage = new HomePage(driver);
		WebElement toastMsg = homepage.getToastMsg();
		wLib.waitUntilElementToBeVisible(driver, toastMsg);
		String msg = toastMsg.getText();
		
		//Fail the Test Case to Check Listeners Taking ScreenShot on Test Failure
		homepage.getCloseToastMsg().click();
		Assert.assertTrue(msg.contains("asc"), "Toast Msg Verifiction Succeded");

	}

	@Test (groups="systemTest")
	public void createCampaignWithStatusTest() throws Exception {

		// Read test script data from excel file
		String CAMPAIGN_NAME = eLib.readDataFromExcelFile("Campaign", 6, 2);
		String STATUS = eLib.readDataFromExcelFile("Campaign", 6, 3);
		String TARGET_SIZE = eLib.readDataFromExcelFile("Campaign", 6, 4);

		// Create Campaign with mandatory fields
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		driver.findElement(By.name("campaignName")).sendKeys(CAMPAIGN_NAME);
		driver.findElement(By.name("campaignStatus")).sendKeys(STATUS);
		WebElement targetSize = driver.findElement(By.name("targetSize"));
		targetSize.clear();
		targetSize.sendKeys(TARGET_SIZE);
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();

		// Verify the toast msg
		HomePage homepage = new HomePage(driver);
		WebElement toastMsg = homepage.getToastMsg();
		wLib.waitUntilElementToBeVisible(driver, toastMsg);
		String msg = toastMsg.getText();
//		if (msg.contains("Successfully Added"))
//			System.out.println("Campaign Created");
//		else
//			System.out.println("Campaign Not Created");
		
		Assert.assertTrue(msg.contains("Successfully Added"), "Toast Msg Verifiction Succeded");
		
		homepage.getCloseToastMsg().click();

	}

	@Test (groups="systemTest")
	public void createCampaignWithExpectedCloseDateTest() throws Exception {

		String CAMPAIGN_NAME = eLib.readDataFromExcelFile("Campaign", 11, 2);
		String TARGET_SIZE = eLib.readDataFromExcelFile("Campaign", 11, 3);

		// Create Campaign with mandatory fields
		campaignsPage campaignsPage = new campaignsPage(driver);
		campaignsPage.getAddCreateCampaignBtn().click();
		CreateCampaignPage createCampaignPage = new CreateCampaignPage(driver);
		createCampaignPage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		createCampaignPage.getExpectedCloseDateTF().sendKeys(jLib.getRequiredDate(70));
		createCampaignPage.getTargetSizeTF().clear();
		createCampaignPage.getTargetSizeTF().sendKeys(TARGET_SIZE);
		createCampaignPage.getCreateCampaignBtn().click();

		// Verify the toast msg
		HomePage homepage = new HomePage(driver);
		WebElement toastMsg = homepage.getToastMsg();
		wLib.waitUntilElementToBeVisible(driver, toastMsg);
		String msg = toastMsg.getText();
//		if (msg.contains("Successfully Added"))
//			System.out.println("Campaign Created");
//		else
//			System.out.println("Campaign Not Created");
		Assert.assertTrue(msg.contains("Successfully Added"), "Toast Msg Verifiction Succeded");
		homepage.getCloseToastMsg().click();
	}
}