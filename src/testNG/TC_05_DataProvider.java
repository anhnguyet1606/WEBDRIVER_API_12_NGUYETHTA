package testNG;

import org.testng.annotations.Test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TC_05_DataProvider {
  WebDriver driver;
  By emailTextBox=By.xpath("//input[@id='email']");
  By passwordTextBox=By.xpath("//input[@id='pass']");
  By loginButton=By.xpath("//button[@id='send2']");

  @Parameters("browser")
  @BeforeClass
  public void beforeClass(String browserName) {
	  if(browserName.equals("chrome")) {
		  System.setProperty("webdriver.chrome.driver", ".\\Library\\chromedriver.exe");
		  driver=new ChromeDriver();
	  }
	  else if(browserName.equals("firefox")) {
		  driver=new FirefoxDriver();
	  }else {
		  System.out.println("Please choose a browser");
	  }
	  
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }
  /*@BeforeMethod
  public void beforeMethod() {
	  driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
  }*/
  @Test(dataProvider="user_pass")
  public void TC_01(String username,String password) {
	  driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	  driver.findElement(emailTextBox).sendKeys(username);
	  driver.findElement(passwordTextBox).sendKeys(password);
	  driver.findElement(loginButton).click();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(username ));
	  driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
	  driver.findElement(By.xpath("//a[@text='Log Out']")).click();
	  
	  driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
  }
  @DataProvider(name="user_pass")
  public Object[][]UserAndPassData(){
	  return new Object[][] {
		 {"selenium_11_01@gmail.com","111111"},
		 {"selenium_11_02@gmail.com","111111"},
		 {"selenium_11_03@gmail.com","111111"}
	  };
  }
  @AfterMethod
  public void afterMethod() {
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
