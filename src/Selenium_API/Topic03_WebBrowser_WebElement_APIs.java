package Selenium_API;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic03_WebBrowser_WebElement_APIs {
	//khai bao bien driver
	WebDriver driver;
	String firstName,lastName, address,city;
	int price,productSize;
	
	//Chay dau tien de mo browser/ khoi tao data /khoi tao bien...
	@BeforeTest
	public void initData() {
		//khoi tao browser len
		driver =new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	//Testcase de thuc thi
	@Test
	public void TC_01_WebBrowserAPI() {
		//Dong browser neu 1 cua so->nhieu cua so chi dong tab dang dung
		driver.close();
		//dong browser
		driver.quit();
		//open 1 URL
		driver.get("http://live.guru99.com");
		//get ra cai url cua cai page hien tai
		String homepageUrl=driver.getCurrentUrl();
		
		//3 ham de verify du lieu: true, false/ hoac no bang cai gi
		
		//verify 1 dk dung
		Assert.assertTrue(homepageUrl.equals("http://live.guru99.com"));
		
		//verify 1 dk sai
		Assert.assertFalse(homepageUrl.equals("http://livw.guru99.com/index.php"));
		
		//verify 1 dk dau vao va dau ra bang nhau
		Assert.assertEquals(homepageUrl, "http://live.guru99.com");
		
		//tra ve source code cua page hien tai
		String homePageSource=driver.getPageSource();
		Assert.assertTrue(homePageSource.contains("This is demo site for"));
		
		//tra ve title cua page hien tai
		String homePageTitle=driver.getTitle();
		Assert.assertEquals(homePageTitle, "Home page");
		
		driver.getWindowHandle();
		driver.getWindowHandles();
		
		//sau manage() chonj thuoc tinh co option thi dung dc
		//wait for element dc tim thay
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//wait for pagr dc load thanh cong
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		//test UI
		driver.manage().window().maximize();
		
		driver.navigate().to("url");
	}
	
	//TC de thuc thi
	@Test
	public void TC_02_WebElementAPI() {
		//Tim 1 element(nhieu) va locator la gi
		
		//cach 1:Neu nhu ma element nay chi dung 1 lan
		driver.findElement(By.id("search")).sendKeys("Samsung");
		
		//cach 2:Neu nhu element nay thao tac nhieu lan-> khai bao bien
		WebElement searchTextbox=driver.findElement(By.id("search"));
	
		//Xoa du lieu truoc khi sendkey
		searchTextbox.clear();
		
	    //Nhap du lieu vao textbox/textarea
		searchTextbox.sendKeys("");
		
		//click vao 1 element
		searchTextbox.click();
		
		//Tim va thao tac voi 1 element
		searchTextbox.findElement(By.id("search")).click();
		
		//Tim va thao tac voi nhieu elements
		searchTextbox.findElements(By.id("search")).get(0).click();
		
		//Test GUI: font/size/color/position...
		String loginButtonColor=searchTextbox.getCssValue("background");
		
		//assertTrue/False
		Assert.assertTrue(searchTextbox.isDisplayed());
		Assert.assertTrue(searchTextbox.isEnabled());
		Assert.assertFalse(searchTextbox.isSelected());
		
		searchTextbox.click();
		searchTextbox.submit();//submit duoc dung trong form
	}
	@AfterTest
	public void cleanData() {
		//close browser sau khi chay all TC
		driver.quit();
	}
	

	public static void main(String[] args) {
		

	}
	

}
