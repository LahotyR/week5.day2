package week5.day2.assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DuplicateLead extends BaseClassDay5 {

	@BeforeClass
	public void setUp() {
		filename = "DuplicateLead";
	}
	
	@Test(dataProvider = "excelData" , priority = 2)
	public void duplicateLead(String email) throws InterruptedException {
				
				// 6. Click Leads link
				driver.findElement(By.linkText("Leads")).click();
				
				// 7. Click Find leads
				WebElement findLeads = driver.findElement(By.linkText("Find Leads"));
				findLeads.click();
				
				// 8. Click on Email
				driver.findElement(By.xpath("//span[contains(text(),'Email')]")).click();
				
				// 9. Enter Email
				WebElement emailTextbox = driver.findElement(By.xpath("//input[@name = 'emailAddress']"));
				emailTextbox.sendKeys(email);
				
				// 10. Click find leads button
				WebElement findLeadsButton = driver.findElement(By.xpath("//button[contains(text(),'Find Leads')]"));
				findLeadsButton.click();
				Thread.sleep(2000);
				
				// 11. Capture name of First Resulting lead
				WebElement leadID = driver.findElement(By.xpath("//div[@class = 'x-grid3-cell-inner x-grid3-col-partyId']/a"));
				String text = leadID.getText();
				System.out.println("Lead ID is: " + text);
				
				Thread.sleep(2000);
				
				// 12. Click First Resulting lead
				leadID.click();
				
				// 13. Click Duplicate Lead
				driver.findElement(By.linkText("Duplicate Lead")).click();
				
				// 14. Verify the title as 'Duplicate Lead'
				WebElement title = driver.findElement(By.id("sectionHeaderTitle_leads"));
				String titleText = title.getText();
				 if (titleText.equalsIgnoreCase("Duplicate Lead")) {
					 System.out.println("Title verified successfully: " + titleText);
				 } else {
					 System.out.println("Title name mismatch");
				 }
				
				/*
				WebElement verifyEmail = driver.findElement(By.id("createLeadForm_primaryEmail"));
				String text2 = verifyEmail.getText();
				System.out.println(text2);
				
				if (text2.equalsIgnoreCase(text)) {
					System.out.println("Email is duplicate: " + text2);
				}
				*/
				
				// 15. Click Create Lead
				driver.findElement(By.xpath("//input[@name = 'submitButton']")).click();
				Thread.sleep(4000);
				
				// 16. Confirm the duplicated lead name is same as captured name
				WebElement email1 = driver.findElement(By.xpath("//b[contains(text(),'Email Address')]/following::a[1]"));
				String emailText = email1.getText();
				System.out.println(emailText);
				
				if (emailText.equalsIgnoreCase(email)) {
					System.out.println("Email IDs are same");
				} else {
					 System.out.println("Email ID mismatch");
				}
				

	}

}
