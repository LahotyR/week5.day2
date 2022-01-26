package week5.day2.assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateContact extends BaseClassDay5 {
	
	@BeforeClass
	public void setUp() {
		filename="CreateContact";	
	}
	
	@Test(priority = 1, dataProvider = "excelData")
	public void createContact(String fname, String lname, String fnameLocal, String lnameLocal,
			 String dept, String desc, String email, String note) {
		
		// 5. Click on contacts Button
		driver.findElement(By.linkText("Contacts")).click();
		
		// 6. Click on Create Contact
		driver.findElement(By.linkText("Create Contact")).click();
		
		//7. Enter FirstName Field Using id Locator
		driver.findElement(By.id("firstNameField")).sendKeys(fname);
		
		// 8. Enter LastName Field Using id Locator
		driver.findElement(By.id("lastNameField")).sendKeys(lname);
		
		// 9. Enter FirstName(Local) Field Using id Locator
		driver.findElement(By.xpath("//input[contains(@id,'createContactForm_firstNameLocal')]")).sendKeys(fnameLocal);
		
		// 10. Enter LastName(Local) Field Using id Locator
		driver.findElement(By.xpath("//input[contains(@id,'createContactForm_lastNameLocal')]")).sendKeys(lnameLocal);
		
		// 11. Enter Department Field Using any Locator of Your Choice
		driver.findElement(By.xpath("//input[contains(@id,'createContactForm_departmentName')]")).sendKeys(dept);
		
		// 12. Enter Description Field Using any Locator of your choice 
		driver.findElement(By.name("description")).sendKeys(desc);
		
		// 13. Enter your email in the E-mail address Field using the locator of your choice
		driver.findElement(By.xpath("//input[contains(@id,'createContactForm_primaryEmail')]")).sendKeys(email);
		
		// 14. Select State/Province as NewYork Using Visible Text
		WebElement state = driver.findElement(By.xpath("//select[contains(@id,'createContactForm_generalStateProvinceGeoId')]"));
		Select dropdown = new Select(state);
		dropdown.selectByVisibleText("New York");
		
		// 15. Click on Create Contact
		driver.findElement(By.xpath("//input[contains(@name,'submitButton')]")).click();
		
		// 16. Click on edit button
		driver.findElement(By.xpath("//div[@class = 'frameSectionExtra']/a[contains(text(),'Edit')]")).click();
		
		// 17. Clear the Description Field using .clear
		driver.findElement(By.id("updateContactForm_description")).clear();
		
		// 18. Fill Important Note Field with Any text
		driver.findElement(By.id("updateContactForm_importantNote")).sendKeys(note);
		
		// 19. Click on update button using Xpath locator
		driver.findElement(By.xpath("//input[@name = 'submitButton' and @value = 'Update']")).click();
		
		// 20. Get the Title of Resulting Page.
		String title = driver.getTitle();
		System.out.println("Page title is: " + title);
		
	}

}
