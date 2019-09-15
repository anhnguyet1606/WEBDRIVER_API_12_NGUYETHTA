package Selenium_API;

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

	@BeforeClass
	public void beforeClass() {
		driver=new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	@Test
	public void TC_01_IsDisplayed() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		
		if(isElementDisplayed(emailTextBox)) {
			sendkeyToElement(emailTextBox,"Automation Testing");
		}
		if(isElementDisplayed(ageUnder18)) {
			clickToElement(ageUnder18);
		}
		if(isElementDisplayed(educationTextArea)) {
			sendkeyToElement(educationTextArea, "Automation testing");
			
		}
		
		
		
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
	public void sendkeyToElement(By by, String value) {
		driver.findElement(by).sendKeys(value);
	}
	public void clickToElement(By by) {
		driver.findElement(by).click();
	}
	@AfterClass
	public void afterClass() {
		
	}
}
