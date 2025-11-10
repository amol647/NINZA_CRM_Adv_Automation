package contactTest;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import objectRepositories.ContactsPage;
import objectRepositories.CreateCampaignPage;
import objectRepositories.HomePage;
import objectRepositories.SelectCampaignPage;
import objectRepositories.campaignsPage;
import objectRepositories.createContactPage;

public class CreateContactTest extends BaseClass{

	@Test (groups="regression")
	public void createContactWithMandatoryFieldsTest() throws Exception {
		// Create Campaign
		String CAMPAIGN_NAME = eLib.readDataFromExcelFile("Contacts", 1, 2);
		String TARGET_SIZE = eLib.readDataFromExcelFile("Contacts", 1, 3);
		String ORGANIZATION = eLib.readDataFromExcelFile("Contacts", 1, 4);
		String TITLE = eLib.readDataFromExcelFile("Contacts", 1, 5);
		String CONTACT_NAME = eLib.readDataFromExcelFile("Contacts", 1, 6);
		
		campaignsPage campaignsPage = new campaignsPage(driver);
		campaignsPage.getAddCreateCampaignBtn().click();
		CreateCampaignPage createCampaignPage = new CreateCampaignPage(driver);
		createCampaignPage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		createCampaignPage.getTargetSizeTF().clear();
		createCampaignPage.getTargetSizeTF().sendKeys(TARGET_SIZE);
		createCampaignPage.getCreateCampaignBtn().click();
		HomePage homePage = new HomePage(driver);
		homePage.getCloseToastMsg().click();

		// Click on contacts
		homePage.getContactsLink().click();

		// click on +create contact
		ContactsPage contactsPage = new ContactsPage(driver);
		contactsPage.getAddCreateContactBtn().click();

		// enter the manadatory fields
		createContactPage createContactPage = new createContactPage(driver);
		createContactPage.getOrganizationNameTF().sendKeys(ORGANIZATION);
		createContactPage.getTitleTF().sendKeys(TITLE);
		createContactPage.getContactNameTF().sendKeys(CONTACT_NAME);
		createContactPage.getMobileTF().sendKeys("9" + jLib.generateNineDigitNumber());
		createContactPage.getPlusBtn().click();

		// get parentId
		String parentId = driver.getWindowHandle();
		wLib.switchDriverControlOnCurrentURL(driver, "selectCampaign.html");

		// select campaignName from drop down
		SelectCampaignPage selectCampaignPage = new SelectCampaignPage(driver);
		WebElement campaignDD = selectCampaignPage.getCampaignDD();
		// Enter Camapign name in search text field
		selectCampaignPage.getSearchTF().sendKeys(CAMPAIGN_NAME);

		// click on select button
		WebElement selectBtn = selectCampaignPage.getSelectBtn();
		wLib.waitUntilElementToBeVisible(driver, selectBtn);
		selectBtn.click();

		// switch the driver control to parent
		driver.switchTo().window(parentId);

		// click on create contact
		createContactPage.getCreateContactBtn().click();

		// Verification
		WebElement toastMsg = homePage.getToastMsg();
		wLib.waitUntilElementToBeVisible(driver, toastMsg);
	
		String msg = toastMsg.getText();
		Assert.assertTrue(msg.contains("Successfully Added"), "Toast Msg Verifiction Succeded");
		
		homePage.getCloseToastMsg().click();
	}

}
