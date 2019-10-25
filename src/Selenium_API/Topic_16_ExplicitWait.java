package Selenium_API;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_ExplicitWait {

	WebDriver driver;
	By startButton=By.xpath("//button[text()='Start']");
	By loadingIcon=By.xpath("//div[@id='loading']");
	By helloWorld=By.xpath("//h4[text()='Hello World!']");
	WebDriverWait explicitWait; 
	@BeforeClass
	public void beforeClass() {
		driver=new FirefoxDriver();
	}
	
		//@Test
		public void TC_02_Emplicit_Invisibility_03_seconds() {
			explicitWait=new WebDriverWait(driver, 3);
			driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
			driver.findElement(startButton).click();
			
			explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
			Assert.assertTrue(driver.findElement(helloWorld).isDisplayed());
		
		}
		//@Test
		public void TC_02_Emplicit_Invisibility_05_seconds() {
			explicitWait=new WebDriverWait(driver, 5);
			driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
			driver.findElement(startButton).click();
			
			explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
			Assert.assertTrue(driver.findElement(helloWorld).isDisplayed());
		
		}
		@Test
		public void TC_03_Emplicit_Visibility_03_seconds() {
			explicitWait=new WebDriverWait(driver, 3);
			driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
			driver.findElement(startButton).click();
			
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(loadingIcon));
			Assert.assertTrue(driver.findElement(helloWorld).isDisplayed());
		
		}
		@Test
		public void TC_03_Emplicit_Visibility_05_seconds() {
			explicitWait=new WebDriverWait(driver, 5);
			driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
			driver.findElement(startButton).click();
			
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(loadingIcon));
			Assert.assertTrue(driver.findElement(helloWorld).isDisplayed());
		
		}
		@AfterClass
		public void afterClass() {
			driver.quit();
		}
}
