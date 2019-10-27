package Selenium_API;

import java.util.Date;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_WebDriverWait_VI_Missing {

	WebDriver driver;
	WebDriverWait explicitWait;
	@BeforeClass
	public void beforeClass() {
		driver=new FirefoxDriver();
		
	}
	@Test
	public void TC_01() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		explicitWait=new WebDriverWait(driver,10);
		driver.get("http://demo.guru99.com/");
		
		
		//Implicit wait
		System.out.println("---Step 01 Start TC 01 Element found:"+new Date()+"---------");
		try {
			WebElement emailTextbox=driver.findElement(By.xpath("//input[@name='emailid']"));
			Assert.assertTrue(emailTextbox.isDisplayed());
		}catch (Exception ex) {
			System.out.println("Switch to catch exception");
		}
		System.out.print("-------Step 01 End TC 01 Element found"+new Date()+"----------");
		
		
		//Explicit wait
		System.out.println("---Step 02 Start TC 01 Element found:"+new Date()+"---------");
		try {
			//get timeout cua Implicit 
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@name='emailid']"))));
			//run:- 01 driver.findElement(By.xpath("//input[@name='emailid']"=>imlpicit
			      //02 ExpectedConditions.visibilityOf
			
			
			//get timeout cua Explicit
			explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@name='btnLogin']")));
			
			
		}
		catch(Exception ex) {
			System.out.println("Switch to catch exception");
		}
		System.out.print("-------Step 01 End TC 02 Element found"+new Date()+"----------");
	}
	@Test
	public void TC_02() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		explicitWait=new WebDriverWait(driver, 10);
		
		driver.get("http://demo.guru99.com/");
		
		System.out.println("-----Step 01 Start TC 02 Elemnt not found timeout equal"+new Date()+"--------");
		try {
			WebElement emailTextbox=explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("Wrong locator"))));
			Assert.assertTrue(emailTextbox.isDisplayed());
		}catch(Exception ex) {
			System.out.println(ex.getMessage()); 
		}
		System.out.print("-------Step 01 End TC 02 Element not found timeout equal"+new Date()+"----------");
		
		System.out.println("-----Step 02 Start TC 02 Elemnt not found timeout equal"+new Date()+"--------");
		try {
			WebElement emailTextbox=explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wrong locator")));
			Assert.assertTrue(emailTextbox.isDisplayed());
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		System.out.println("-----Step 02 Start TC 02 Elemnt not found timeout equal"+new Date()+"--------");
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
