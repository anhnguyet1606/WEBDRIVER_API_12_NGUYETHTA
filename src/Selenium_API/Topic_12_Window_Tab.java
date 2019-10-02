package Selenium_API;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Window_Tab {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver=new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	}
	
	public void TC_01 () {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Lay ID cua page hien tai
		String parentWindowID =driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		
		//switchToChildWindowByID(parentWindowID);
		swithToWindowByTitle("Google");
		
		Assert.assertEquals(driver.getTitle(),"Google");
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='q']")).isDisplayed());
		
		swithToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		swithToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
		
		closeAllWindowsWithoutParent(parentWindowID);
	}
	
	public void TC_02() {
		driver.get("http://www.hdfcbank.com/");
		
		String parentWindow=driver.getWindowHandle();
		
		if(driver.findElement(By.xpath("//img[contains(@class,'popupbanner')]")).isDisplayed()) {
			driver.findElement(By.cssSelector(".popupCloseButton")).click();
			boolean closeIconStatus=driver.findElement(By.cssSelector(".popupCloseButton")).isDisplayed();
			Assert.assertFalse(closeIconStatus);
		}
		driver.findElement(By.xpath("//a[text()='Agri']")).click();
		swithToWindowByTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");
		Assert.assertEquals(driver.getTitle(),"HDFC Bank Kisan Dhan Vikas e-Kendra");
		
		driver.findElement(By.xpath("//p[text()='Account Details']")).click();
		swithToWindowByTitle("Welcome to HDFC Bank NetBanking");
		Assert.assertEquals(driver.getTitle(), "Welcome to HDFC Bank NetBanking");
		
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='footer']")));
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Privacy Policy']")).isDisplayed());
		
		driver.findElement(By.xpath("//a[text()='Privacy Policy']")).click();
		swithToWindowByTitle("Privacy");
		Assert.assertEquals(driver.getTitle(),"Privacy");
		
		swithToWindowByTitle("HDFC Bank: Personal Banking Services");
		
		driver.findElement(By.xpath("//a[text()='Facebook']")).click();
		swithToWindowByTitle("HDFC Bank - Trang chủ | Facebook");
		Assert.assertEquals(driver.getTitle(),"HDFC Bank - Trang chủ | Facebook");
		
		closeAllWindowsWithoutParent(parentWindow);
	}
	@Test
	public void TC_03() {
		driver.get("http://live.guru99.com/index.php/");
		
		String parentWindow=driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Sony Xperia has been added to comparison list.']")).isDisplayed());
		
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());
		
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		
		swithToWindowByTitle("Products Comparison List - Magento Commerce");
		
		Assert.assertTrue(driver.getTitle().equals("Products Comparison List - Magento Commerce"));
		Assert.assertTrue(closeAllWindowsWithoutParent(parentWindow));
		
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void switchToChildWindowByID(String parent) {
		Set<String>allWindows=driver.getWindowHandles();
		for(String id:allWindows) {
			if(!id.equals(parent)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}
	public void swithToWindowByTitle(String title) {
		Set<String> allWindows=driver.getWindowHandles();
		for(String runwindow:allWindows) {
			driver.switchTo().window(runwindow);
			String currentwin=driver.getTitle();
			if(currentwin.equals(title)) {
				break;
			}
		}
	}
	public boolean closeAllWindowsWithoutParent(String parentWindow) {
		Set<String>allWindows=driver.getWindowHandles();
		for(String runWindow:allWindows) {
			if(!runWindow.equals(parentWindow)) {
				driver.switchTo().window(runWindow);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
		if(driver.getWindowHandles().size()==1) {
			return true;
		}
		else return false;
	}

}
