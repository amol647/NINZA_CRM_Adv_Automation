package genericUtilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class IRetryImplementation implements IRetryAnalyzer{

	int count = 1;
	int limitCount = 5;
	@Override
	public boolean retry(ITestResult result) {
		
		if(count <= limitCount)
		{
			count++;
			return true;	//When returned true, it executes the TC
		}
		
		return false;
	}
	
	

}
