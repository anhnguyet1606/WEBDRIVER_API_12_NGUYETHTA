package Selenium_API;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_04_Exercises {
	WebDriver driver;
	String emailAddress;
	By myAccountLink=By.xpath("//div[@class='footer']//a[text()='My Account']");
	By emailTextBox=By.xpath("//input[@id='email']");
	By passTextBox=By.xpath("//input[@id='pass']");
	By loginButton=By.xpath("//button[@id='send2']");

	@BeforeClass
	  public void beforeClass() {
		driver=new FirefoxDriver();
		emailAddress="automstion_"+ randomNumber()+".gmail.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
	@BeforeMethod
	public void LoopForEveryTest() {
        driver.get("http://live.guru99.com/index.php");
    	
        driver.findElement(myAccountLink).click();
	}
	
    @Test
    public void TC_01_LoginWithEmailPassEmpty() {
    	
    	driver.findElement(emailTextBox).sendKeys("");
    	driver.findElement(passTextBox).sendKeys("");
    	driver.findElement(loginButton).click();
    	
    	String emailErroMsg=driver.findElement(By.id("advice-required-entry-email")).getText();
    	Assert.assertEquals(emailErroMsg,"This is a required field." );
    	
    	String passwordErroMsg= driver.findElement(By.id("advice-required-entry-pass")).getText();
    	Assert.assertEquals(passwordErroMsg,"This is a required field.");
    	
    }
    @Test
    public void TC_02_LoginWithEmailEmpty() {
     
    	driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123212323@12324.1443");
    	driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
    	driver.findElement(By.xpath("//button[@id='send2']")).click();
    	
    	String emailInvalidMsg=driver.findElement(By.id("advice-validate-email-email")).getText();
    	Assert.assertEquals(emailInvalidMsg, "Please enter a valid email address. For example johndoe@domain.com.");
    	
    	String passwordErroMsg= driver.findElement(By.id("advice-required-entry-pass")).getText();
    	Assert.assertEquals(passwordErroMsg,"This is a required field.");
    	
    }
    @Test
    public void TC_03_LoginWithPassLessThan6character(){
    	driver.findElement(emailTextBox).sendKeys("automation@gmail.com");
    	driver.findElement(passTextBox).sendKeys("1234");
    	driver.findElement(loginButton).click();
    	
    	
    	String passwordErroMsg= driver.findElement(By.id("advice-validate-password-pass")).getText();
    	Assert.assertEquals(passwordErroMsg,"Please enter 6 or more characters without leading or trailing spaces.");
    	
    }
    @Test
    public void TC_04_LoginWithPassIncorrect() {
    	driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
    	driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("1234567");
    	driver.findElement(By.xpath("//button[@id='send2']")).click();
    	
    	String errorMsg= driver.findElement(By.xpath("//li[@class='error-msg']")).getText();
    	Assert.assertEquals(errorMsg, "Invalid login or password.");
    	
    }
    @Test
    public void TC_05_CreateAnAccount() {
    	driver.findElement(By.xpath("//a[@class='button']")).click();
    	
    	driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("huynh");
    	driver.findElement(By.xpath("//input[@id='middlename']")).sendKeys("Anh");
    	driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Nguyet");
    	driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(emailAddress);
    	driver.findElement(By.xpath("//input[@id='password']")).sendKeys("1234567");
    	driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("1234567");
    	driver.findElement(By.xpath("//form[@id='form-validate']/div[2]/button")).click();
    }
    


   @AfterClass
   public void afterClass() {
	   driver.quit();
   }
   public int randomNumber() {
	   Random random=new Random();
	   return random.nextInt(999999);
   }
 
}