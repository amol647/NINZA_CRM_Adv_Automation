package genericUtilities;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import objectRepositories.HomePage;
import objectRepositories.LoginPage;

public class BaseClass {
	
	public WebDriver driver = null;
	
	public PropertyFileUtility pLib = new PropertyFileUtility();
	public ExcelFileUtility eLib = new ExcelFileUtility();
	public JavaUtility jLib = new JavaUtility();
	public WebDriverUtility wLib = new WebDriverUtility();
	
	public static WebDriver sdriver = null;
	
	@BeforeSuite (groups = {"smoke", "regression", "systemTest"})
	public void beforeSuite() {
		System.out.println("Establish the database connection");
	}

	@BeforeTest (groups = {"smoke", "regression", "systemTest"})
	public void beforeTest() {
		System.out.println("Pre-conditions for parallel executions");
	}

/*	//For Parallel Execution

	@Parameters("BROWSER")
	@BeforeClass (groups = {"smoke", "regression", "systemTest"})
	public void beforeClass(String browser) throws Exception {
		System.out.println("Launch the browser");
		
		String BROWSER = browser;
		
		*/
	
	
	@BeforeClass (groups = {"smoke", "regression", "systemTest"})
	public void beforeClass() throws Exception {
		System.out.println("Launch the browser");
		ChromeOptions settings = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);
		settings.setExperimentalOption("prefs", prefs);
		
		String BROWSER = pLib.ReadDataFromPropertyFile("Browser");
		
//		To Read the date from Command Line using Maven Commands
//		String BROWSER = System.getProperty("browser");		
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

		sdriver = driver;
		
		driver.manage().window().maximize();

		WebDriverUtility wLib = new WebDriverUtility();

		// Call implicitWait method from WebDriverUtility to include implicit wait
		wLib.implicitWait(driver);
	}
 
	@BeforeMethod (groups = {"smoke", "regression", "systemTest"})
	public void beforeMethod() throws Exception {
		System.out.println("Login");
		
		String URL = pLib.ReadDataFromPropertyFile("URL");
		String USERNAME = pLib.ReadDataFromPropertyFile("Username");
		String PASSWORD = pLib.ReadDataFromPropertyFile("Password");
		
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(URL, USERNAME, PASSWORD);
		
	}

	@AfterMethod (groups = {"smoke", "regression", "systemTest"})
	public void afterMethod() {
		System.out.println("Logout");
		HomePage homePage=new HomePage(driver);
		homePage.logout();
	}

	@AfterClass (groups = {"smoke", "regression", "systemTest"})
	public void afterClass() {
		System.out.println("Close the browser");
		driver.quit();
	}

	@AfterTest (groups = {"smoke", "regression", "systemTest"})
	public void afterTest() {
		System.out.println("Post-Conditions for parallel executions");
	}

	@AfterSuite (groups = {"smoke", "regression", "systemTest"})
	public void afterSuite() {
		System.out.println("Close the database connection");
	}

}
