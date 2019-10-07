package Selenium_API;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Javascript_Executor {
  
	WebDriver driver;
	
	String customerName,gender,dateOfBirth,address,city,state,pin,phone,email,password,customerID;
	String editAddress,editCity,editState,editPin,editPhone,editEmail;
	
	
	By customerNameTextbox=By.xpath("//input[@name='name']");
	By genderTextbox=By.xpath("//input[@name='gender']");
	By datrOfBirthTextbox=By.xpath("//input[@name='dob']");
	By addressTextArea=By.xpath("//textarea[@name='addr']");
	By cityTextbox=By.xpath("//input[@name='city']");
	By stateTextbox=By.xpath("//input[@name='state']");
	By pinTextbox=By.xpath("//input[@name='pinno']");
	By phoneTextbox=By.xpath("//input[@name='telephoneno']");
	By emailTextbox=By.xpath("//input[@name='emailid']");
	By passwordTextbox=By.xpath("//input[@name='password']");
	By submitButton=By.xpath("//input[@name='sub']");
	
	@BeforeClass
	public void beforeClass() {
	
		//driver=new FirefoxDriver();
		
		String projectFolder=System.getProperty("user.dir");
		System.out.print("Root folder"+projectFolder);
		System.setProperty("webdriver.chrome.driver", projectFolder+"\\Library\\chromedriver.exe");
		driver=new ChromeDriver();
		
		customerName="Sarrwr Kitcatttt";
		gender="male";
		dateOfBirth="2000-01-01";
		address="1031 Daystar Road";
		city="Austin";
		state="Texas";
		pin="787033";
		phone="5128293664";
		email="qwewt@gmail.com";
		password="123456";
		
		
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	//@Test
	public void TC_01() {
		driver.get("http://live.guru99.com/");
		
		String liveGuruDomain=(String) executeForBrowser("return document.domain");
		Assert.assertEquals(liveGuruDomain,"live.guru99.com");
		
		String liveGuruURL=(String) executeForBrowser("return document.URL");
		Assert.assertEquals(liveGuruURL, "http://live.guru99.com/");
		
		clickToElementByJS(driver.findElement(By.xpath("//a[text()='Mobile']")));
		clickToElementByJS(driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']/button")));
		Assert.assertTrue(isInnerTextMatched("Sony Xperia was added to your shopping cart."));
		
		clickToElementByJS(driver.findElement(By.xpath("//a[text()='Customer Service']")));
		Assert.assertEquals(executeForBrowser("return document.title"), "Customer Service");
		
		//scrollToBottomPage();
		scrollToElement(driver.findElement(By.xpath("//input[@id='newsletter']")));
		Assert.assertTrue(isInnerTextContained("Praesent ipsum libero, auctor ac, tempus nec, tempor nec, justo."));
		
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		String GuruDomain=(String) executeForBrowser("return document.domain");
		Assert.assertEquals(GuruDomain,"demo.guru99.com");
	}
	//@Test
	public void TC_02() throws InterruptedException {
		driver.get("http://demo.guru99.com/v4/");
		sendkeyToElement(By.xpath("//input[@name='uid']"), "mngr209708");
		sendkeyToElement(By.xpath("//input[@name='password']"),"arasYsY");
		clickToElement(By.xpath("//input[@name='btnLogin']"));
		
		clickToElement(By.xpath("//a[text()='New Customer']"));
		
		sendkeyToElement(customerNameTextbox, customerName);
		removeAttributeInDOM(driver.findElement(By.xpath("//input[@id='dob']")), "type");
		Thread.sleep(2000);
		
		sendkeyToElement(datrOfBirthTextbox, dateOfBirth);
		
		sendkeyToElement(addressTextArea, address);
		sendkeyToElement(cityTextbox, city);
		sendkeyToElement(stateTextbox, state);
		sendkeyToElement(pinTextbox, pin);
		sendkeyToElement(phoneTextbox, phone);
		sendkeyToElement(emailTextbox, email);
		sendkeyToElement(passwordTextbox, password);
		
		
		
		
		clickToElement(submitButton);
		
		
		//Output data(verify =input)
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")), customerName);
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Gender']/following-sibling::td")), gender);
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")), dateOfBirth);
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Address']/following-sibling::td")), address);
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='City']/following-sibling::td")), city);
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='State']/following-sibling::td")),state);
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Pin']/following-sibling::td")), pin);
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")), phone);
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Email']/following-sibling::td")),email);
	
	    //customerID=getTextElement(By.xpath("//td[text()='Customer ID']/following-sibling::td"));
	    
	}
	
	@Test
	public void TC_03() {
		driver.get("http://live.guru99.com/");
		highlightElement(driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")));
		clickToElementByJS(driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")));
		clickToElementByJS(driver.findElement(By.xpath("//span[text()='Create an Account']")));
		
		sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='firstname']")), "automation");
		sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='middlename']")), "test");
		sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='lastname']")), "webdriver");
		sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='email_address']")), "testing"+random()+"@gmail.com");
		sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='password']")), "123456");
		sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='confirmation']")), "123456");
		
		clickToElementByJS(driver.findElement(By.xpath("//button[@title='Register']")));
		Assert.assertTrue(isInnerTextMatched("Thank you for registering with Main Website Store."));
		
		clickToElementByJS(driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")));
		clickToElementByJS(driver.findElement(By.xpath("//a[text()='Log Out']")));
		Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed());
		//Assert.assertTrue(isInnerTextContained("THIS IS DEMO SITE FOR"));
	}
	
	public int random() {
		Random rand=new Random();
		return rand.nextInt(999999);
	}
	public void sendkeyToElement(By by,String value) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(value);
	}
	public void clickToElement(By by) {
		driver.findElement(by).click();
	}
	public String getTextElement(By by) {
		return driver.findElement(by).getText();
	}
	public String getAttributeValue(By by, String attributeName) {
		return driver.findElement(by).getAttribute(attributeName);
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	// Browser
		public Object executeForBrowser(String javaSript) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript(javaSript);
		}
		
		public boolean isInnerTextMatched(String textExpected) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String textActual = (String) js.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
			System.out.println("Text actual = " + textActual);
			return textActual.equals(textExpected);
		}
		public boolean isInnerTextContained(String textExpected) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return (boolean) js.executeScript("return document.documentElement.innerText.contains('" + textExpected + "')");
			
		}
		
		
		
		public void navigateToUrlByJS(String url) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.location = '" + url + "'");
		}
		
		// Element
		public void highlightElement(WebElement element) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String originalStyle = element.getAttribute("style");
			js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

		}
		
		public void clickToElementByJS(WebElement element) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
		}
		
		public void scrollToBottomPage() {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		}

		public void scrollToElement(WebElement element) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", element);
		}

		public void sendkeyToElementByJS(WebElement element, String value) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
		}

		public void removeAttributeInDOM(WebElement element, String attributeRemove) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
		}

}
