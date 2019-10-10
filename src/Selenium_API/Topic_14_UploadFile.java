package Selenium_API;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_UploadFile {
 
	WebDriver driver;
	String projectPath=System.getProperty("user.dir");
	
	String hoabaymauName="hoabaymau.jpg";
	String hoatraitimName="hoatraitim.jpg";
	String huongduongName="huongduong.jpg";
	
	String hoabaymauPath=projectPath+"\\UploadFile\\"+hoabaymauName;
	String hoatraitimPath=projectPath+"\\UploadFile\\"+hoatraitimName;
	String huongduongPath=projectPath+"\\UploadFile\\"+huongduongName;
	@BeforeClass
	public void beforeClass() {
		//driver=new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", ".\\Library\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	//@Test
	public void TC_01() throws InterruptedException {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		WebElement inputUploadFile=driver.findElement(By.xpath("//input[@type='file']"));
		/*upload 1 file
		inputUploadFile.sendKeys(hoabaymauPath);*/
		//upload multi files
		inputUploadFile.sendKeys(hoabaymauPath+"\n"+hoatraitimPath+"\n"+huongduongPath);
		
		//Verify 3 file dc load len thanh cong
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'"+hoabaymauName+"')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'"+hoatraitimName+"')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'"+huongduongName+"')]")).isDisplayed());
		Thread.sleep(2000);
		List<WebElement>startUpload=driver.findElements(By.xpath("//table//button[contains(@class,\"start\")]"));
		for(WebElement start:startUpload) {
			start.click();
			Thread.sleep(2000);
		}
		//verify 3 file dc upload thanh cong
		Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'"+hoabaymauName+"')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'"+hoatraitimName+"')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'"+huongduongName+"')]")).isDisplayed());
		Thread.sleep(3000);
	}
	@Test
	public void TC_02_Robot() throws InterruptedException, AWTException {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		WebElement uploadButton=driver.findElement(By.cssSelector(".fileinput-button"));
		uploadButton.click();
		
		StringSelection select=new StringSelection("hoabaymauPath");
		
		//Copy to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
		
		Robot robot=new Robot();
		Thread.sleep(2000);
		
		//nhan phim enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		//Nhan xuong Ctrl-V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		//nha Ctrl-V
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(1000);
		
		//Nhan Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
