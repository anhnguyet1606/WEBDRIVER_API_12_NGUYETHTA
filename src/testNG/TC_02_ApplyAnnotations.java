package testNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TC_02_ApplyAnnotations {
  
  WebDriver driver; 
  By emailTextBox=By.xpath("//input[@id='email']");

  @BeforeClass
  public void beforeClass() {
	  driver=new FirefoxDriver(); 	
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }
  @BeforeMethod
  public void beforeMethod() {
	  //Run cho moi TC, chay lai cho 3 TC thi la 3 lan
	  driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
  }
  @Test
  public void TC_01() {
	  System.out.println("TC 01_LoginWithEmailInvalid");
	  driver.findElement(emailTextBox).sendKeys("@email@email.com");
  }
  @Test
  public void TC_02() {
	  System.out.println("TC 01_LoginWithEmptyEmailAndPass");
	  driver.findElement(emailTextBox).sendKeys("@empty@email.com");
  }
  @Test
  public void TC_03() {
	  System.out.println("TC 01_LoginWithPasswordIncorrect");
	  driver.findElement(emailTextBox).sendKeys("@Incorrect@email.com");
  }

  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}