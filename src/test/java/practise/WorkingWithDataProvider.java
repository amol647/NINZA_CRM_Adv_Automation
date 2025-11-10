package practise;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import objectRepositories.HomePage;
import objectRepositories.LoginPage;



public class WorkingWithDataProvider {

	@Test(dataProvider = "loginDetails")
	public void login(String username,String password) {
		WebDriver driver=new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		LoginPage loginPage=new LoginPage(driver);
		loginPage.loginToApp("http://49.249.28.218:8098/", username, password);
	
		HomePage homePage=new HomePage(driver);
		homePage.logout();
		driver.quit();
	}
	
	@DataProvider
	public  Object[][] loginDetails() {
		
		Object [][] objArr=new Object[5][2];	
		objArr[0][0]="rmgyantra";
		objArr[0][1]="rmgy@9999";
		objArr[1][0]="rmgyantra";
		objArr[1][1]="rmgy@9999";
		objArr[2][0]="rmgyantra";
		objArr[2][1]="rmgy@9999";
		objArr[3][0]="rmgyantra";
		objArr[3][1]="rmgy@9999";
		objArr[4][0]="rmgyantra";
		objArr[4][1]="rmgy@9999";
		
		return objArr;
	
	}
}