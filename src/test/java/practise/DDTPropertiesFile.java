package practise;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DDTPropertiesFile {

	public static void main(String[] args) throws IOException {

		//Create a java representation object of Physical File
		FileInputStream fis = new FileInputStream("C:\\Users\\Nihal\\Desktop\\Ammie\\Temp\\Advance Selenium\\CommonData.properties.txt");
		
		//Create object of Properties File
		Properties prop = new Properties();
		
		//Load the data into prop
		prop.load(fis);
		
		//Returns String and store it in a String Variable
		String BROWSER = prop.getProperty("Browser");
		String URL = prop.getProperty("URL");
		String USERNAME = prop.getProperty("Username");
		String PASSWORD = prop.getProperty("Password");
		
		
		System.out.println(BROWSER);
		System.out.println(URL);
		System.out.println(USERNAME);
		System.out.println(PASSWORD);
	}

}
