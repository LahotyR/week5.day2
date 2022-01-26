package week5.day2.assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DeleteLead extends BaseClassDay5 {
	
	@BeforeClass
	public void setUp() {
		filename = "DeleteLead";	
	}
	
	@Test(priority = 4, dataProvider = "excelData")
	public void deleteLead(String code, String phAreaCode, String phone) throws InterruptedException {
		
		// 6. Click Leads link
		driver.findElement(By.linkText("Leads")).click();
		
		// 7. Click Find leads
		driver.findElement(By.xpath("//a[contains(text(),'Find Leads')]")).click();
		
		// 8. Click on Phone
		driver.findElement(By.xpath("//span[contains(text(),'Phone')]")).click();
		
		// 9. Enter phone number
		WebElement countryCode = driver.findElement(By.name("phoneCountryCode"));
		countryCode.clear();
		countryCode.sendKeys(code);
		
		driver.findElement(By.xpath("//input[contains(@name,'phoneAreaCode')]")).sendKeys(phAreaCode);
		
		driver.findElement(By.xpath("//input[contains(@name,'phoneNumber')]")).sendKeys(phone);
		
		// 10. Click find leads button	
		WebElement findLeadsButton = driver.findElement(By.xpath("//button[contains(text(),'Find Leads')]"));
		findLeadsButton.click();
		Thread.sleep(2000);
		
		// 11. Capture lead ID of First Resulting lead
		WebElement leadID = driver.findElement(By.xpath("//div[@class = 'x-grid3-cell-inner x-grid3-col-partyId']/a[1]"));
		String text = leadID.getText();
		System.out.println("Lead ID is: " + text);
		
		Thread.sleep(2000);
		
		// 12. Click First Resulting lead
		leadID.click();
		
		// 13. Click Delete
		driver.findElement(By.linkText("Delete")).click();
		Thread.sleep(2000);
		
		// 14. Click Find leads
		driver.findElement(By.xpath("//a[contains(text(),'Find Leads')]")).click();
		
		//driver.findElement(By.linkText("Find Leads")).click();
		
		// 15. Enter captured lead ID
		driver.findElement(By.xpath("//input[@name = 'id']")).sendKeys(text);
		Thread.sleep(2000);
		
		// 16. Click find leads button
		driver.findElement(By.xpath("//button[contains(text(),'Find Leads')]")).click();
		Thread.sleep(2000);
		
		// 17. Verify message "No records to display" in the Lead List. This message confirms the successful deletion
		WebElement message = driver.findElement(By.xpath("//div[contains(text(),'No records to display')]"));
		String text2 = message.getText();
		String verify = "No records to display";
		
		if (verify.equalsIgnoreCase(text2)) {
			System.out.println("The Lead is deleted successfully");
			
		} else {
			System.out.println("Lead is not deleted");
		}
		

	}

}
