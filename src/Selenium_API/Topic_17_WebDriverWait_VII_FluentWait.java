package Selenium_API;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_17_WebDriverWait_VII_FluentWait {

	WebDriver driver;
	WebDriverWait emplicitWait;
	FluentWait<WebElement> fluentWait;
	@BeforeClass
	public void beforeClass() {
		driver=new FirefoxDriver();
		emplicitWait=new WebDriverWait(driver, 10);
	}
	@Test
	public void TC_01() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		WebElement countdown=driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
		emplicitWait.until(ExpectedConditions.visibilityOf(countdown));
		
		//khoi tao fluent wait
		fluentWait=new FluentWait<WebElement>(countdown);
		fluentWait.withTimeout(15, TimeUnit.SECONDS)
		//Tan so nua s check 1 lan
		.pollingEvery(500, TimeUnit.MILLISECONDS)
		//Neu gap exception la find khong tim thay element se bo qua
		.ignoring(NoSuchElementException.class)
		//Kiem tra dieu kien
		.until(new Function<WebElement,Boolean>(){
			public Boolean apply(WebElement element) {
				//Kiem tra dk countdown=00
				boolean flag=element.getText().endsWith("02");
				System.out.println("Time"+element.getText());
				return flag;
			}
		});
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
