package objectRepositories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy (id="username")
	private WebElement userameTF;
	
	
	@FindBy (id="inputPassword")
	private WebElement passwordTF;
	
	
	@FindBy (xpath="//button[text()='Sign In']")
	private WebElement signInBtn;


	public WebElement getUserameTF() {
		return userameTF;
	}


	public WebElement getPasswordTF() {
		return passwordTF;
	}


	public WebElement getSignInBtn() {
		return signInBtn;
	}	
	
	
	
	public void loginToApp(String url, String username, String password)
	{
		driver.get(url);
		userameTF.sendKeys(username);
		passwordTF.sendKeys(password);
		signInBtn.click();
	}
	
}
