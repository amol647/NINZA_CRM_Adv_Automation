package genericUtilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileUtility {
	
	public String ReadDataFromPropertyFile(String key) throws Exception
	{
		FileInputStream fis1 = new FileInputStream("./src/main/resources/CommonData.properties.txt");

		// Create object of Properties File
		Properties prop = new Properties();

		// Load the data into prop
		prop.load(fis1);
		
		String value = prop.getProperty(key);
		
		return value;
	}
	
	
}
