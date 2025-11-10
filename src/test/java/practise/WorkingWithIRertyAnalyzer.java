package practise;

import org.testng.Assert;
import org.testng.annotations.Test;

public class WorkingWithIRertyAnalyzer {
	
	@Test (retryAnalyzer = genericUtilities.IRetryImplementation.class)
	public void test()
	{
		Assert.assertEquals("hdfc", "hfdc");
	}

}
