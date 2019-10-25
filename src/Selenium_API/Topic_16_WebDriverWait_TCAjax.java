package Selenium_API;

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

public class Topic_16_WebDriverWait_TCAjax {

	WebDriver driver;
	WebDriverWait explicitWait;
	By selectedDate=By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']");
	By today=By.xpath("//a[text()='13']");
	By todaySelected=By.xpath("//a[text()='13']/parent::td[contains(@class,'rcSelected')]");
	By ajaxloadingIcon=By.xpath("//div[@class='raDiv']");
	@BeforeClass
	public void beforeClass() {
		driver=new FirefoxDriver();
		explicitWait=new WebDriverWait(driver,15);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	@Test
	public void TC_05_ExplicitWait() {
		driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		Assert.assertEquals(driver.findElement(selectedDate).getText(),"No Selected Dates to display.");
		driver.findElement(today).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(todaySelected));
		//Assert.assertTrue(driver.findElement(todaySelected).isDisplayed());
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(ajaxloadingIcon));
		Assert.assertEquals(driver.findElement(selectedDate).getText(),"Sunday, October 13, 2019");
		
	}
	@Test
	public void TC_05_ExplicitWait_State_Element() {
		driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		//01
		WebElement selectedDateText=driver.findElement(selectedDate);
		Assert.assertEquals(selectedDateText.getText(),"No Selected Dates to display.");
		
		//02: thay doi trang thai(DOM nay bi load lai)
		driver.findElement(today).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(todaySelected));
		//Assert.assertTrue(driver.findElement(todaySelected).isDisplayed());
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(ajaxloadingIcon));
		
		//03: minh van dung lai element da khai bao tai buoc 01-> fail
		Assert.assertEquals(selectedDateText.getText(),"Sunday, October 13, 2019");
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
