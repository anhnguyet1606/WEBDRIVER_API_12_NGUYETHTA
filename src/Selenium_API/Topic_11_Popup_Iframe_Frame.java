package Selenium_API;



import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;

public class Topic_11_Popup_Iframe_Frame {
    WebDriver driver;
    
	@BeforeClass
	public void beforeClass() {
		driver=new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public void TC_01_Popup_IFrame()throws Exception {
		driver.get("http://www.hdfcbank.com/");
		if(driver.findElement(By.xpath("//img[contains(@class,'popupbanner')]")).isDisplayed()) {
			driver.findElement(By.cssSelector(".popupCloseButton")).click();
			boolean closeIconStatus=driver.findElement(By.cssSelector(".popupCloseButton")).isDisplayed();
			Assert.assertFalse(closeIconStatus);
		}
		//Verify right banner co 6 image
		List <WebElement> rightBannerImgs= driver.findElements(By.xpath("//div[@id='rightbanner']//img"));
		Assert.assertEquals(rightBannerImgs.size(),7);
		
		//Verify fliper banner co 8 image
		List<WebElement> fliperBannerImgs=driver.findElements(By.xpath("//img[@class='front icon']"));
		Assert.assertEquals(fliperBannerImgs.size(),8);
		
		//verify fliper banner display
		for(WebElement item: fliperBannerImgs) {
			System.out.println("check fliper banner"+ item.isDisplayed());
			Assert.assertTrue(item.isDisplayed());
		}
		
		
	}
	
	public void TC_02_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking");
		driver.switchTo().frame(0);
		//driver.switchTo().frame("login_page");
		//driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='login_page']")));
		
		driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("selenium_online");
		driver.findElement(By.xpath("//table[@class='lForm']//img[@alt='continue']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='fldPassword']")).isDisplayed());
		
		//Back to parent page(top window)
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='footer']")));
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Privacy Policy']")).isDisplayed());
	}
	@Test
	public void TC_03_IFrame() {
		driver.get("https://kyna.vn/");
		
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='fanpage']//iframe")));
		String kynaFaceLine=driver.findElement(By.xpath("//div[@class='_1drq']")).getText();
		Assert.assertEquals(kynaFaceLine,"170K likes");
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
