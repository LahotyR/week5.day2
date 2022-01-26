package week5.day2.assignments;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class IncidentLifecycle extends BaseServiceNow {
 
	public String attribute;
	
	@Test (priority = 1)
	public void createIncident() throws IOException {
		
		// Click on New
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//button[@id='sysverb_new']")).click();
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("gsft_main");
		WebElement number = driver.findElement(By.id("incident.number"));
		 attribute = number.getAttribute("value");
		System.out.println("Value of incident: " + attribute);
		
		driver.findElement(By.id("lookup.incident.caller_id")).click();
		Set<String> set = driver.getWindowHandles();
		
		List<String> list = new ArrayList<String>(set);
		String child = list.get(1);
		
		driver.switchTo().window(child);
		//int row = driver.findElements(By.xpath("//table[@id='sys_user_table']//tr")).size();
		driver.findElement(By.xpath("//table[@id='sys_user_table']//tr[3]/td[3]")).click();
		
		driver.switchTo().window(list.get(0));
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//div/input[@id='incident.short_description']"))
		.sendKeys("Need to get the session out of hibernation");
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//span/following-sibling::input[@placeholder='Search']")).sendKeys(attribute, Keys.ENTER);
		
		// Validate
		String incident = driver.findElement(By.xpath("//table[@id='incident_table']//tbody/tr/td[3]")).getText();
		
		if(incident.equalsIgnoreCase(attribute)) {
			System.out.println("Incident created successfully");

			
		} else {
			System.out.println("Issue with incident creation");
		}
		
} 
	
	@Test (priority = 2)
	public void UpdateIncident() throws InterruptedException {
		
		// Search for the existing incident and click on the incident
		driver.switchTo().frame(0);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span/following-sibling::input[@placeholder='Search']"))
		.sendKeys(attribute, Keys.ENTER);
		driver.findElement(By.xpath("//table[@id='incident_table']//tbody/tr/td[3]")).click();
		
		// Update the incidents with Urgency as High and State as In Progress
		driver.switchTo().defaultContent();
		driver.switchTo().frame(0);
		
		WebElement dropdown1 = driver.findElement(By.id("incident.state"));
		Select state = new Select(dropdown1);
		String text = state.getOptions().get(1).getText();
		state.selectByIndex(1);
		WebElement dropdown2 = driver.findElement(By.id("incident.urgency"));
		Select urgency = new Select(dropdown2);
		urgency.selectByVisibleText("1 - High");
		
		driver.findElement(By.xpath("(//textarea[@placeholder='Work notes'])[1]"))
		.sendKeys("Updated the incident");
		
		driver.findElement(By.id("sysverb_update")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//span/following-sibling::input[@placeholder='Search']"))
		.sendKeys(attribute, Keys.ENTER);
		
		// Verify the priority and state
		driver.switchTo().defaultContent();
		driver.switchTo().frame(0);
		String priority = driver.findElement(By.xpath("//table[@id='incident_table']//tbody/tr/td[7]")).getText();
		String stateIncident = driver.findElement(By.xpath("//table[@id='incident_table']//tbody/tr/td[8]")).getText();
		
		if(stateIncident.equalsIgnoreCase(text)) {
			System.out.println("Incident updated successfully with state as " + stateIncident + " and "
		+ " priority as " + priority);

			
		} else {
			System.out.println("Issue with incident updation");
		}
		
	} 
	
	@Test (priority = 3)
	public void assignIncident() throws InterruptedException {
		
		// Search for the existing incident and click on the incident
		driver.switchTo().frame(0);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span/following-sibling::input[@placeholder='Search']"))
		.sendKeys(attribute, Keys.ENTER);
		driver.findElement(By.xpath("//table[@id='incident_table']//tbody/tr/td[3]")).click();
		
		// Assign the incident to Software group
		driver.findElement(By.id("lookup.incident.assignment_group")).click();
		Set<String> set = driver.getWindowHandles();
		
		List<String> list = new ArrayList<String>(set);
		String child = list.get(1);
		
		driver.switchTo().window(child);
		driver.findElement(By.xpath("//label[text()='Search']/following-sibling::input[@placeholder='Search']"))
		.sendKeys("Software", Keys.ENTER);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//table[@id='sys_user_group_table']//tbody/tr[1]/td[3]/a")).click();
		driver.switchTo().window(list.get(0));
		driver.switchTo().frame(0);
		Thread.sleep(2000);
		String text = driver.findElement(By.id("sys_display.incident.assignment_group")).getAttribute("value");
		
		// Update the incident with Work Notes
		driver.findElement(By.xpath("(//textarea[@placeholder='Work notes'])[2]"))
		.sendKeys("Assigned the incident to software group");
		driver.findElement(By.id("sysverb_update")).click();
		driver.switchTo().defaultContent();
		
		// Verify the Assignment group and Assigned for the incident
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//span/following-sibling::input[@placeholder='Search']"))
		.sendKeys(attribute, Keys.ENTER);
		String group = driver.findElement(By.xpath("//table[@id='incident_table']//tbody/tr/td[10]")).getText();
		String assigned = driver.findElement(By.xpath("//table[@id='incident_table']//tbody/tr/td[11]")).getText();
	
		if(group.equalsIgnoreCase(text) && assigned.contains("empty")) {
			System.out.println("Incident is assigned to " + group + " group and not assigned to user yet");
			
		} else {
			System.out.println("Assignment not successful!!");
		}
		
	}
	
	@Test (priority = 4)
	public void deleteIncident() throws InterruptedException {
		
		// Search for the existing incident and navigate into the incident
		driver.switchTo().frame(0);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span/following-sibling::input[@placeholder='Search']"))
		.sendKeys(attribute, Keys.ENTER);
		driver.findElement(By.xpath("//table[@id='incident_table']//tbody/tr/td[3]")).click();
		
		// Delete the incident
		driver.findElement(By.id("sysverb_delete")).click();
		driver.findElement(By.xpath("//button[@id='ok_button']")).click();
		Thread.sleep(2000);
		
		// Verify the deleted incident
		driver.switchTo().defaultContent();
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//span/following-sibling::input[@placeholder='Search']"))
		.sendKeys(attribute, Keys.ENTER);
		String deleteMessage = driver.findElement(By.xpath("//td[contains(text(),'No records to display')]")).getText();
		
		if(deleteMessage.contains("No")) {
			System.out.println("Incident " + attribute + " deleted successfully");
		} else {
			System.out.println("Issue with incident deletion");
		}
	}

}
