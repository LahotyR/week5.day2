package week5.day2.assignments;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import week5.day2.ExcelData;

public class BaseClassDay5 {

	public ChromeDriver driver;
	public String filename;
	
	@Parameters ({"url","username","password"})
	@BeforeMethod
	public void preCondition(String url, String uname, String pass) {
		// Setup driver
		WebDriverManager.chromedriver().setup();
										
		// Launch browser
		driver = new ChromeDriver();
				
		//Implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
										
		// Load URL
		driver.get(url);
										
		// Maximize browser
		driver.manage().window().maximize();
		
		// 2. Enter UserName
		driver.findElement(By.xpath("(//input[@id='username'])")).sendKeys(uname);
		
		// 3. Enter password
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(pass);
				
		// 4. Click on Login Button using Class Locator
		driver.findElement(By.xpath("//input[@class = 'decorativeSubmit']")).click();
		
		// 5. Click on CRM/SFA Link
		driver.findElement(By.xpath("//div[@id = 'label']")).click();
	}
	
	@AfterMethod
	public void postCondition() {
		// Close browser
		driver.close();
	}
	
	@DataProvider(name = "excelData")
	public String[][] data() throws IOException {
		
		return ExcelData.excelData(filename);
		
	}
}
