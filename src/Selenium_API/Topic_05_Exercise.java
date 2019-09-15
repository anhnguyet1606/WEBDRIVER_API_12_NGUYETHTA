package Selenium_API;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Exercise {
	WebDriver driver;
	By emailTextBox = By.xpath("//input[@id='mail']");
	By ageUnder18=By.xpath("//input[@id='under_18']");
	By educationTextArea= By.xpath("//textarea[@id='edu']");
	By passwordTextBox=By.xpath("//input[@id='password']");
	By jobRole01=By.xpath("//select[@id='job1']");
	By developmentCheckBox=By.xpath("//input[@id='development']");
	By ageOver18Radio=By.xpath("//input[@id='over_18']");

	@BeforeClass
	public void beforeClass() {
		driver=new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	@Test
	public void TC_01_IsDisplayed () {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		
		if(isElementDisplayed(emailTextBox)) {
			sendkeyToElement(emailTextBox,"Automation Testing");
		}
		Assert.assertTrue(isElementDisplayed(emailTextBox));
		if(isElementDisplayed(ageUnder18)) {
			clickToElement(ageUnder18);
		}
		Assert.assertTrue(isElementDisplayed(ageUnder18));
		if(isElementDisplayed(educationTextArea)) {
			sendkeyToElement(educationTextArea, "Automation testing");
			
		}
		Assert.assertTrue(isElementDisplayed(educationTextArea));
		
		
	}
	@Test
	public void TC_02_IsEnabled(By by) {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		
		Assert.assertTrue(isElementEnabled(emailTextBox));
		Assert.assertTrue(isElementEnabled(ageUnder18));
		
		Assert.assertFalse(isElementEnabled(passwordTextBox));
		Assert.assertFalse(isElementEnabled(jobRole01));
		
	}
	@Test 
	public void TC_03_IsSelected(By by) {
		clickToElement(ageOver18Radio);
		clickToElement(developmentCheckBox);
		
		Assert.assertTrue(isElementSelected(ageOver18Radio));
		Assert.assertTrue(isElementSelected(developmentCheckBox));
		
		clickToElement(developmentCheckBox);
		Assert.assertFalse(isElementSelected(developmentCheckBox));
		
	}
	public boolean isElementDisplayed(By by) {
		if(driver.findElement(by).isDisplayed()) {
			System.out.println("Element with locator["+by+"] is displayed");
			return true;
			
		}
		else {
			System.out.println("Element with locator["+by+"is not displayed");
			return false;
		}
	}
	public boolean isElementEnabled(By by) {
		if(driver.findElement(by).isEnabled()) {
			System.out.println("Element with locator["+by+"] is enabled");
			return true;
			
		}
		else {
			System.out.println("Element with locator["+by+"is not enabled");
			return false;
		}
	}
	public boolean isElementSelected(By by) {
		if(driver.findElement(by).isSelected()) {
			System.out.println("Element with locator["+by+"] is selected");
			return true;
			
		}
		else {
			System.out.println("Element with locator["+by+"is not selected");
			return false;
		}
	}
	public void sendkeyToElement(By by, String value) {
		driver.findElement(by).sendKeys(value);
	}
	public void clickToElement(By by) {
		driver.findElement(by).click();
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
