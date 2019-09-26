package Selenium_API;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_09_Checkbox_Radio_Alert {

	WebDriver driver;
	JavascriptExecutor jsExecutor;
	Alert alert;
	@BeforeClass
	public void beforeClass() {
		driver=new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public void TC_01_Button() {
		driver.get("http://live.guru99.com/");
		
		clickElementByJavascript(driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")));
		clickElementByJavascript("//div[@class='footer']//a[text()='My Account']");
		clickElementByJavascript(By.xpath("//div[@class='footer']//a[text()='My Account']"));
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
		
		clickElementByJavascript("//span[text()='Create an Account']");
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");
		
	}
	
	public void TC_02_Checkbox() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		
		clickElementByJavascript("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
		Assert.assertTrue(isElementSelected(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")));
		
		clickElementByJavascript("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
		Assert.assertFalse(isElementSelected(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")));
		
		
	}
	
	public void TC_04_Alert() {
		
		//case 01 Accept alert
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		alert=driver.switchTo().alert();
		//verify test trong alert
		Assert.assertEquals(alert.getText(),"I am a JS Alert");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You clicked an alert successfully");
		
		//case 02-Cancel
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		alert=driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You clicked: Cancel" );
		
		//case 03-prompt(Enter text to alert)
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		alert=driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.sendKeys("automation test");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You entered: automation test" );
		
		
	}
	@Test
	public void TC_07_AuthenticationAlert() {
		String username="admin";
		String password="admin";
		String url="the-internet.herokuapp.com/basic_auth";
		driver.get("http://"+username+":"+password+"@"+url);
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		
	}
	public void clickElementByJavascript(By by) {
		jsExecutor=(JavascriptExecutor)driver;
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
	}
	public void clickElementByJavascript(WebElement element) {
		jsExecutor=(JavascriptExecutor)driver;
		jsExecutor.executeScript("arguments[0].click();", element);
	}
	public void clickElementByJavascript(String locator) {
		jsExecutor=(JavascriptExecutor)driver;
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath(locator)));
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
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
