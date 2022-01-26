package week5.day2.assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateLead extends BaseClassDay5 {
	
	@BeforeClass
	public void setUp() {
		filename = "CreateLead";
	}
	
	@Test (dataProvider = "excelData", priority = 0)
	
	public void createLead(String cname, String fname, String lname,
			String fnamelocal, String lnamelocal, String title, String dob, String profession, 
			String dept, String revenue, String empcount, String sic, String ticker, String desc, String note, 
			String phCountryCode, String phAreaCode, String phone, String phExt, String phName, 
			String email, String url, String toName, String attName, String add1, String add2, 
			String city, String zip, String zipExt) {
			
		///Click on Leads tab
		driver.findElement(By.linkText("Leads")).click();
		
		//Click on Create Lead tab
		driver.findElement(By.linkText("Create Lead")).click();
		 
		//Enter all fields under Create Lead section
       // 1. Donot work on Parent Account Field
       // 2.Enter the Birthdate using SendKeys
		WebElement companyName = driver.findElement(By.id("createLeadForm_companyName"));
		companyName.sendKeys(cname);
		
		// First name
		WebElement firstName = driver.findElement(By.id("createLeadForm_firstName"));
		firstName.sendKeys(fname);
		
		// Last name
		WebElement lastName = driver.findElement(By.id("createLeadForm_lastName"));
		lastName.sendKeys(lname);
				
		// Select source from drop-down
		WebElement source = driver.findElement(By.id("createLeadForm_dataSourceId"));
		Select option1 = new Select(source);
		option1.selectByVisibleText("Existing Customer");
		
		// Marketing Campaign
		WebElement campaign = driver.findElement(By.id("createLeadForm_marketingCampaignId"));
		Select option2 = new Select(campaign);
		option2.selectByIndex(4);
		
		// First Name Local
		driver.findElement(By.id("createLeadForm_firstNameLocal")).sendKeys(fnamelocal);
		
		// Last Name Local
		driver.findElement(By.id("createLeadForm_lastNameLocal")).sendKeys(lnamelocal);
		
		// Salutation
		driver.findElement(By.id("createLeadForm_personalTitle")).sendKeys(title);
		
		// Birth date
		WebElement birthDate = driver.findElement(By.id("createLeadForm_birthDate"));
		birthDate.sendKeys(dob);
		
		// Title
		driver.findElement(By.id("createLeadForm_generalProfTitle")).sendKeys(profession);
		
		// Department
		driver.findElement(By.id("createLeadForm_departmentName")).sendKeys(dept);
		
		// Annual Revenue
		driver.findElement(By.xpath("//input[@id = 'createLeadForm_annualRevenue']")).sendKeys(revenue);
		
		//Preferred Currency
		WebElement currency = driver.findElement(By.xpath("//select[@id = 'createLeadForm_currencyUomId']"));
		Select option3 = new Select(currency);
		int size = option3.getOptions().size();
		option3.selectByIndex(size-10);
		
		// Industry
		WebElement industry = driver.findElement(By.xpath("//select[@name = 'industryEnumId']"));
		Select option4 = new Select(industry);
		int size2 = option4.getOptions().size();
		option4.selectByIndex(size2-12);
		
		// No. of employees
		driver.findElement(By.xpath("//input[@id='createLeadForm_numberEmployees']")).sendKeys(empcount);
		
		// Ownership
		WebElement ownership = driver.findElement(By.xpath("//select[@id = 'createLeadForm_ownershipEnumId']"));
		Select option5 = new Select(ownership);
		option5.selectByVisibleText("LLC/LLP");
		
		//SIC Code
		driver.findElement(By.xpath("//input[@id = 'createLeadForm_sicCode']")).sendKeys(sic);
		
		//Ticker Symbol
		driver.findElement(By.xpath("//input[@id = 'createLeadForm_tickerSymbol']")).sendKeys(ticker);
		
		//Description
		driver.findElement(By.xpath("//textarea[@id = 'createLeadForm_description']")).sendKeys(desc);
		
		//Important Note
		driver.findElement(By.xpath("//textarea[@id = 'createLeadForm_importantNote']")).sendKeys(note);
		
		// Enter all fields under Contact Information	
		// Country Code
		driver.findElement(By.xpath("//input[@id = 'createLeadForm_primaryPhoneCountryCode']")).sendKeys(phCountryCode);
		
		// Area Code
		driver.findElement(By.xpath("//input[@id = 'createLeadForm_primaryPhoneAreaCode']")).sendKeys(phAreaCode);
		
		// Phone Number
		driver.findElement(By.xpath("//input[contains(@id,'createLeadForm_primaryPhoneNumber')]")).sendKeys(phone);
		
		// Extension
		driver.findElement(By.xpath("//input[@id = 'createLeadForm_primaryPhoneExtension']")).sendKeys(phExt);
		
		// Person to ask for
		driver.findElement(By.xpath("//input[@id = 'createLeadForm_primaryPhoneAskForName']")).sendKeys(phName);
		
		// E-mail Address
		driver.findElement(By.xpath("//input[@id = 'createLeadForm_primaryEmail']")).sendKeys(email);
		
		// Web URL
		driver.findElement(By.xpath("//input[@id = 'createLeadForm_primaryWebUrl']")).sendKeys(url);
		
		// Enter all fields under Primary Address
		// To Name
		driver.findElement(By.xpath("//input[@id = 'createLeadForm_generalToName']")).sendKeys(toName);
		
		// Attention Name
		driver.findElement(By.xpath("//input[@id = 'createLeadForm_generalAttnName']")).sendKeys(attName);
		
		// Address Line 1
		driver.findElement(By.xpath("//input[@id = 'createLeadForm_generalAddress1']")).sendKeys(add1);
		
		// Address Line 2
		driver.findElement(By.xpath("//input[@id = 'createLeadForm_generalAddress2']")).sendKeys(add2);
		
		// City
		driver.findElement(By.xpath("//input[@id = 'createLeadForm_generalCity']")).sendKeys(city);
		
		// State/Province
		WebElement state = driver.findElement(By.xpath("//select[@id = 'createLeadForm_generalStateProvinceGeoId']"));
		Select option6 = new Select(state);
		option6.selectByValue("GU");
		
		// Zip/Postal Code
		driver.findElement(By.xpath("//input[@id = 'createLeadForm_generalPostalCode']")).sendKeys(zip);
		
		// Country
		
		// Zip/Postal Code extension
		driver.findElement(By.xpath("//input[@id = 'createLeadForm_generalPostalCodeExt']")).sendKeys(zipExt);
		
		// Get the Firstname and print it	  
		String fName = firstName.getAttribute("value");
		System.out.println("First Name is: " + fName);

		// Click on Create Lead button
		driver.findElement(By.name("submitButton")).click();
		
		 
		// Get and Verify the Title of the resulting Page(View Lead)
		String pageTitle = driver.getTitle();
		System.out.println("Page title is: " + pageTitle);

	}

}
