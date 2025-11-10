package practise;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class NewTestNGTest {

	@Test
	public void createCampaignWithMandatoryFieldsTest()
	{
		
		Reporter.log("Executed only in Report");
		Reporter.log("Executed in both Console and Report", true);
		
	}
}
