package week5.day2.assignments;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseServiceNow {

	public ChromeDriver driver;
	
	@Parameters({"url","username","password"})	
	@BeforeMethod
	public void preConditions(String url, String username, String password ) {
		
		WebDriverManager.chromedriver().setup();
		
		driver = new ChromeDriver();
		
		// Open instance
		driver.get(url);
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.switchTo().frame(0);
		
		// Login
		driver.findElement(By.id("user_name")).sendKeys(username);
		driver.findElement(By.id("user_password")).sendKeys(password);
		driver.findElement(By.id("sysverb_login")).click();
		
		// Filter
		driver.findElement(By.id("filter")).sendKeys("incident");
		
		// Click on Incident -> All
		driver.findElement(By.xpath("//div[@class='sn-widget-list-title' and text()='Resolved']/following::div[3]")).click();
	
	}
	
	@AfterMethod
	public void postConditions() {
		driver.close();
		
	}
}
