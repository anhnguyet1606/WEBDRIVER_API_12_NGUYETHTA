package Selenium_API;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Topic_07_TestBox_TextArea {

	WebDriver driver;
	String customerName,gender,dateOfBirth,address,city,state,pin,phone,email,password,customerID;
	String editAddress,editCity,editState,editPin,editPhone,editEmail;
	
	
	By customerNameTextbox=By.xpath("//input[@name='name']");
	By genderTextbox=By.xpath("//input[@name='gender']");
	By datrOfBirthTextbox=By.xpath("//input[@name='dob']");
	By addressTextArea=By.xpath("//textarea[@name='addr']");
	By cityTextbox=By.xpath("//input[@name='city']");
	By stateTextbox=By.xpath("//input[@name='state']");
	By pinTextbox=By.xpath("//input[@name='pinno']");
	By phoneTextbox=By.xpath("//input[@name='telephoneno']");
	By emailTextbox=By.xpath("//input[@name='emailid']");
	By passwordTextbox=By.xpath("//input[@name='password']");
	By submitButton=By.xpath("//input[@name='sub']");
	
	@BeforeClass
	public void beforeClass() {
		driver=new FirefoxDriver();
		
		customerName="Sarrwr Kitcatttt";
		gender="male";
		dateOfBirth="2000-01-01";
		address="1031 Daystar Road";
		city="Austin";
		state="Texas";
		pin="787033";
		phone="5128293664";
		email="skiqercatttt0@gmail.com";
		password="123456";
		
		//data cho edit
		editAddress="1031 edit Daystar Road";
		editCity=" edit Austin";
		editState="edit Texas";
		editPin="787033000";
		editPhone="5128293664000";
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://demo.guru99.com/v4/");
	} 
	@Test
	public void TC_01_CreateNewCustomer() {
		sendkeyToElement(By.xpath("//input[@name='uid']"), "mngr209708");
		sendkeyToElement(By.xpath("//input[@name='password']"),"arasYsY");
		clickToElement(By.xpath("//input[@name='btnLogin']"));
		
		clickToElement(By.xpath("//a[text()='New Customer']"));
		
		sendkeyToElement(customerNameTextbox, customerName);
		sendkeyToElement(datrOfBirthTextbox, dateOfBirth);
		sendkeyToElement(addressTextArea, address);
		sendkeyToElement(cityTextbox, city);
		sendkeyToElement(stateTextbox, state);
		sendkeyToElement(pinTextbox, pin);
		sendkeyToElement(phoneTextbox, phone);
		sendkeyToElement(emailTextbox, email);
		sendkeyToElement(passwordTextbox, password);
		
		
		
		
		clickToElement(submitButton);
		
		
		//Output data(verify =input)
		/*Assert.assertEquals(getTextElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")), customerName);
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Gender']/following-sibling::td")), gender);
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")), dateOfBirth);
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Address']/following-sibling::td")), address);
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='City']/following-sibling::td")), city);
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='State']/following-sibling::td")),state);
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Pin']/following-sibling::td")), pin);
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Mobile.No']/following-sibling::td")), phone);
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Email']/following-sibling::td")),email);*/
	
	    customerID=getTextElement(By.xpath("//td[text()='Customer ID']/following-sibling::td"));
	    
	    
	}
	@Test
	public void  TC_02_EditCustomer() {
		clickToElement(By.xpath("//a[text()='Edit Customer']"));
		
		sendkeyToElement(By.xpath("//input[@name='cusid']"),customerID);
		
		clickToElement(By.xpath("//input[@name='AccSubmit']"));
		
		//Verify data at edit customer=Input(New customer)
		Assert.assertEquals(getAttributeValue(customerNameTextbox, "value"),customerName);
		Assert.assertEquals(getAttributeValue(genderTextbox, "value"),gender);
		Assert.assertEquals(getAttributeValue(datrOfBirthTextbox, "value"),dateOfBirth);
		//Address (Option-> get text )
		Assert.assertEquals(getTextElement(addressTextArea),address);
		Assert.assertEquals(getAttributeValue(cityTextbox, "value"),city);
		Assert.assertEquals(getAttributeValue(stateTextbox, "value"),state);
		Assert.assertEquals(getAttributeValue(pinTextbox, "value"),pin);
		Assert.assertEquals(getAttributeValue(phoneTextbox, "value"),phone);
		Assert.assertEquals(getAttributeValue(emailTextbox, "value"),email);
		
		sendkeyToElement(addressTextArea, editAddress);
		sendkeyToElement(cityTextbox, editCity);
		sendkeyToElement(stateTextbox, editState);
		sendkeyToElement(pinTextbox, editPin);
		sendkeyToElement(phoneTextbox, editPhone);
		sendkeyToElement(emailTextbox, editEmail);
		
		clickToElement(submitButton);
	
		
		
	}
	public void sendkeyToElement(By by,String value) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(value);
	}
	public void clickToElement(By by) {
		driver.findElement(by).click();
	}
	public String getTextElement(By by) {
		return driver.findElement(by).getText();
	}
	public String getAttributeValue(By by, String attributeName) {
		return driver.findElement(by).getAttribute(attributeName);
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
