package Selenium_API;

import org.testng.annotations.Test;



import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_01_Check {
  WebDriver driver;
  
  @BeforeClass
  public void beforeClass() {
	  System.out.print("Pre-condition 01: Init Firefox browser");
	  driver=new FirefoxDriver();
	  System.out.print("Pre-condition 02: Wait for page load success");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  System.out.print("Pre-condition 03: Open Itiec");
	  driver.get("https://itviec.com/");
  }
  @Test
  public void TC_01() {
	  System.out.print("TC_01-Step1");
	  String homePageUrl=driver.getCurrentUrl();
	  
	  System.out.print("TC_02-Step 02");
	  Assert.assertEquals(homePageUrl,"https://itviec.com/");
  }
  @Test
  public void TC_02() {
	  System.out.print("TC_01-Step1");
	  String homePageTitle=driver.getTitle();
	  
	  System.out.print("TC_02-Step 02");
	  Assert.assertEquals(homePageTitle,"ITviec | Top IT Jobs for You");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("Post condition:Close");
	  driver.quit();
  }

}
