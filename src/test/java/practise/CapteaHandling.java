package practise;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.sourceforge.tess4j.Tesseract;

public class CapteaHandling {

	public static void main(String[] args) throws Throwable {

		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--disable-notifications");

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(opt);

		driver.get("https://www.irctc.co.in/nget/train-search");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()=\" LOGIN \"]")).click();

		Thread.sleep(1000);
		WebElement captcha = driver.findElement(By.xpath("//img[@class=\"captcha-img\"]"));

		TakesScreenshot ts = (TakesScreenshot) captcha;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./CaptchaScreenShot.png");
		FileUtils.copyFile(src, dest);

		Tesseract tss = new Tesseract();
		tss.setDatapath("C:\\Users\\Shobha\\Downloads\\Tess4J-3.4.8-src (1)\\Tess4J\\tessdata");
		String st = tss.doOCR(dest);
		String img = st.replaceAll(" ", "");
		System.out.println(img);

		driver.findElement(By.xpath("//input[@id=\"captcha\"]")).sendKeys(img);
	}

}
