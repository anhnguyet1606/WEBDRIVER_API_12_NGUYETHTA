package Selenium_API;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_WebDriver_Wait_Implicit {

	WebDriver driver;
	By startButton=By.xpath("//button[text()='Start']");
	By loadingIcon=By.xpath("//div[@id='loading']");
	By helloWorld=By.xpath("//h4[text()='Hello World!']");
	WebDriverWait explicitWait; 
	@BeforeClass
	public void beforeClass() {
		driver=new FirefoxDriver();
		
	}
	@Test
	public void TC_01_ImplicipWait_02_seconds() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(startButton).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
	}
	@Test
	public void TC_01_ImplicipWait_05_seconds() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(startButton).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
	}
	@Test
	public void TC_01_ImplicipWait_10_seconds() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(startButton).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
