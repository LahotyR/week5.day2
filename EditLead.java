package week5.day2.assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EditLead extends BaseClassDay5 {

	@BeforeClass
	public void setUp() {
		filename = "EditLead";
	}
	
	@Test (dataProvider = "excelData", priority = 1)
	
	public void editLead(String company) throws InterruptedException {
		
		// 6. Click Leads link
		driver.findElement(By.linkText("Leads")).click();
		
		// 7. Click Find leads
		driver.findElement(By.xpath("//a[contains(text(),'Find Leads')]")).click();

		// 8. Enter first name
		driver.findElement(By.xpath("//div[@class='x-form-item x-tab-item']/following::input[@name = 'firstName']")).sendKeys("Rinky");
		
		// 9. Click Find leads button
		driver.findElement(By.xpath("//button[contains(text(),'Find Leads')]")).click();
		Thread.sleep(2000);
		
		// 10 Click on first resulting lead
		driver.findElement(By.xpath("//div[@class = 'x-grid3-cell-inner x-grid3-col-partyId']/a[1]")).click();
		
		// 11 Verify title of the page
		String title = driver.getTitle();
		System.out.println("Page title is: " + title);
		
		// 12 Click Edit
		driver.findElement(By.linkText("Edit")).click();
		
		// 13 Change the company name
		WebElement companyName = driver.findElement(By.id("updateLeadForm_companyName"));
		companyName.clear();
		companyName.sendKeys(company);
		
		// 14 Click Update
		driver.findElement(By.xpath("//input[@name = 'submitButton' and @value='Update']")).click();
		
		// 15 Confirm the changed name appears
		WebElement findElement = driver.findElement(By.xpath("//span[contains(text(),' Company Name')]"));
		String text = findElement.getText();
		System.out.println(text);
		
		if(text.contains(company)) {
			System.out.println("Company name edited successfully");
		} else {
			System.out.println("Company name mismatch");
		}

	}

}
