package Selenium_API;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Topic_08_Dropdown_List {
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor jsExecutor;
	Select select;

	@BeforeClass
	public void beforeClass() {
		driver =new FirefoxDriver();
		waitExplicit=new WebDriverWait(driver, 30);
		jsExecutor=(JavascriptExecutor)driver; 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	public void TC_01_Default_Dropdown() throws InterruptedException {
		driver.get("https://egov.danang.gov.vn/reg");
		WebElement sexDropDown=driver.findElement(By.xpath("//select[@id='gioiTinh']"));
		
		select=new Select(sexDropDown);
		//Chon item trong dropdown bang index
		select.selectByIndex(2);
		Thread.sleep(3000);
		
		//verify item da dc chon thanh cong
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Nam");
		
		select.selectByValue("2");
		Thread.sleep(2000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Nữ");
		
		select.selectByVisibleText("Không xác định");
		Thread.sleep(3000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Không xác định");
		
		WebElement cityDropDown=driver.findElement(By.xpath("//select[@id='thuongtru_tinhthanh']"));
		select=new Select(cityDropDown);
		select.selectByVisibleText("thành phố Hồ Chí Minh");
		Thread.sleep(2000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "thành phố Hồ Chí Minh");
		
		
		//Trong trường hợp mà dropdown hỗ trợ multiple select
		//select.deselectAll();
		
		//Kiểm tra 1 dropdown co bao nhieu item
		Assert.assertEquals(select.getOptions().size(), 65);
		
		System.out.println("Kiem tra dropdowm co multiple select hay khong="+select.isMultiple());
		Assert.assertEquals(select.isMultiple(), false);
		Assert.assertFalse(select.isMultiple());
		
		
	}
	
	
	public void TC_02_HTML_Dropdown_List() throws InterruptedException {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		WebElement secDropDown=driver.findElement(By.xpath("//select[@id='job1']"));
		
		select=new Select(secDropDown);
		//kiem tra khong ho tro multiple select
		System.out.println("Kiem tra dropdown co ho tro multiple hay khong:"+select.isMultiple());
		Assert.assertFalse(select.isMultiple());
		
		select.selectByVisibleText("Automation Tester");
		Thread.sleep(3000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Automation Tester");
		
		select.selectByValue("manual");
		Thread.sleep(3000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Tester");
		
		select.selectByIndex(3);
		Thread.sleep(3000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(),"Mobile Tester");
		
		Assert.assertEquals(select.getOptions().size(), 5);
		
		
		
	}
	
	public void TC_03_Dropdown_JQuery()throws Exception {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectItemInDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li/div", "8");
		Assert.assertEquals(getTextElement("//span[@id='number-button']/span[@class='ui-selectmenu-text']"), "8");
		Assert.assertTrue(isElementDisplayed("//span[@id='number-button']/span[@class='ui-selectmenu-text'and text()='8']"));
		
		selectItemInDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li/div", "2");
		Assert.assertEquals(getTextElement("//span[@id='number-button']/span[@class='ui-selectmenu-text']"), "2");
		Assert.assertTrue(isElementDisplayed("//span[@id='number-button']/span[@class='ui-selectmenu-text'and text()='2']"));
		
		selectItemInDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li/div", "15");
		Assert.assertEquals(getTextElement("//span[@id='number-button']/span[@class='ui-selectmenu-text']"), "15");
		Assert.assertTrue(isElementDisplayed("//span[@id='number-button']/span[@class='ui-selectmenu-text'and text()='15']"));
		
		selectItemInDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li/div", "19");
		Assert.assertEquals(getTextElement("//span[@id='number-button']/span[@class='ui-selectmenu-text']"), "19");
		Assert.assertTrue(isElementDisplayed("//span[@id='number-button']/span[@class='ui-selectmenu-text'and text()='19']"));
	}
	
	
    public void TC_04_Dropdown_Angular() throws Exception {
		driver.get("https://material.angular.io/components/select/examples");
		
		selectItemInDropdown("//label/child::mat-label[text()='State']", "//div[contains(@class,'mat-primary')]/mat-option", "Ohio");
		
	}
	
   
	public void TC_05_Dropdown_ReactJs() throws Exception{
		driver.get("https://react.semantic-ui.com/modules/dropdown/");
		
		selectItemInDropdown("//div[@id='types-selection']/div/div[2]/div/div", "//div[@class='visible menu transition']/div", "Jenny Hess");
	}
	@Test
    public void TC_06_Dropdown_Editable() throws Exception {
    	driver.get("http://indrimuska.github.io/jquery-editable-select/");
    	selectItemInEditableDropdown("//div[@id='slide-place']/input", "//div[@id='slide-place']/ul/li", "Ford");
    }
	
	public void selectItemInDropdown(String parentLocator,String allItemsLocator, String expectedElement) throws InterruptedException {
		
		//Click vao dropdown de hien thi cac item
		WebElement parentDropdown=driver.findElement(By.xpath(parentLocator));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);",parentDropdown);
		//parentDropdown.click();
		jsExecutor.executeScript("arguments[0].click();", parentDropdown);
		Thread.sleep(3000);
		
		//wait cho cac element load successfully
	    waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsLocator)));
	    
		//define ra 1 list element de store lai all items duoc load ra		
		List<WebElement> allItems=driver.findElements(By.xpath(allItemsLocator));
		
		for(WebElement item:allItems) {
			if(item.getText().equals(expectedElement)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				item.click();
				break;
			}
		}
		
		
	}
	
    public void selectItemInEditableDropdown(String parentLocator,String allItemsLocator, String expectedElement) throws InterruptedException {
		
		//Click vao dropdown de hien thi cac item
		WebElement parentDropdown=driver.findElement(By.xpath(parentLocator));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);",parentDropdown);
		//parentDropdown.click();
		jsExecutor.executeScript("arguments[0].click();", parentDropdown);
		Thread.sleep(3000);
		parentDropdown.sendKeys(expectedElement);
		Thread.sleep(3000);
		
		//wait cho cac element load successfully
	    waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsLocator)));
	    
		//define ra 1 list element de store lai all items duoc load ra		
		List<WebElement> allItems=driver.findElements(By.xpath(allItemsLocator));
		
		for(WebElement item:allItems) {
			if(item.getText().equals(expectedElement)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				item.click();
				break;
			}
		}
		
		
	}
	
	public String getTextElement(String locator) {
		return driver.findElement(By.xpath(locator)).getText();
	}
	public boolean isElementDisplayed(String locator) {
		if(driver.findElement(By.xpath(locator)).isDisplayed()) {
			System.out.print("Element with locator"+locator+"is displayed");
			return true;
		}
		else {
			System.out.print("Element with locator"+locator+"is not displayed");
			return false;
		}
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	

}
