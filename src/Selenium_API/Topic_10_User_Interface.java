package Selenium_API;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_10_User_Interface {
    WebDriver driver;
    Actions action;
    JavascriptExecutor jsExecutor;
    
    //Get root folder
    String workingDirectory=System.getProperty("user.dir");
    String jsFilePath= workingDirectory +"\\DragAndDrop\\drag_and_drop_helper.js";
    String jQueryFilePath=workingDirectory + "\\DragAndDrop\\jquery_load_helper.js";
	@BeforeClass
	public void beforeClass() {
		FirefoxProfile profile=new FirefoxProfile();
		profile.setPreference("dom.webnotifications.enable",false);
		driver =new FirefoxDriver(profile);
		
		action=new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public void TC_01_Hover() {
		driver.get("https://www.myntra.com/");
		WebElement discoverLink=driver.findElement(By.xpath("//nav[@class='desktop-navbar']//a[text()='Discover']"));
		action.moveToElement(discoverLink).perform();
		
		driver.findElement(By.xpath("//a[text()='American Eagle']")).click();
		//action.click(driver.findElement(By.xpath("\"//a[text()='American Eagle']\""))).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb']")).isDisplayed());
		
		}
	
	public void TC_02_ClickAndHold_01() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List <WebElement> allelements=driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		System.out.print("so luong phan tu la:"+allelements.size());
		
		action.clickAndHold(allelements.get(0)).moveToElement(allelements.get(7)).release().perform();
		
		List<WebElement>cellSelected=driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(cellSelected.size(),8 );
	}
	
	public void TC_02_ClickAndHold_02() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List <WebElement> allelements=driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		System.out.print("so luong phan tu la:"+allelements.size());
		
		//Nhan phim xuong
		action.keyDown(Keys.CONTROL).perform();
		action.click(allelements.get(0)).click(allelements.get(5)).click(allelements.get(3)).perform();
		action.keyUp(Keys.CONTROL).perform();
		
		List<WebElement>cellSelected=driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(cellSelected.size(),3 );
	}
	
	public void TC_03_DoubleClick() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		WebElement doubleClick=driver.findElement(By.xpath("//button[text()='Double click me']"));
		action.doubleClick(doubleClick).perform();
		Assert.assertTrue(driver.findElement(By.xpath(".//p[@id='demo']")).isDisplayed());
	}
	
	public void TC_04_RightClick() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		WebElement rightClickButton=driver.findElement(By.xpath("//span[text()='right click me']"));
		action.contextClick(rightClickButton).perform();
		
		WebElement quitBeforeHover=driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') and not (contains(@class,'context-menu-visible'))and not (contains(@class,'context-menu-hover'))]"));
		action.moveToElement(quitBeforeHover).perform();
		
		WebElement quitAfterHover=driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') and (contains(@class,'context-menu-visible'))and (contains(@class,'context-menu-hover'))]"));
		Assert.assertTrue(quitAfterHover.isDisplayed());
	}
	
	public void TC_05_DragDrop(){

		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
		
		WebElement sourceCircle=driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement targetCircle=driver.findElement(By.xpath("//div[@id='droptarget']"));
		
		action.dragAndDrop(sourceCircle, targetCircle);
		
		WebElement targetSuccess=driver.findElement(By.xpath(".//div[@id='droptarget' and text()='You did great!']"));	
		Assert.assertTrue(targetSuccess.isDisplayed());
		
		
	}
	@Test
	public void TC_06_Dragdrop_HTML5()throws InterruptedException, IOException{
		driver.get("http://the-internet.herokuapp.com/drag_and_drop");
		
		String sourceXpath = "//div[@id='column-a']";
		String targetXpath = "//div[@id='column-b']";
		
		
		String java_script= readFile(jsFilePath);
		
	
		// A to B
		java_script= java_script+"$(\""+sourceXpath+"\").simulateDragDrop({ dropTarget:\""+targetXpath+"\"});";
		jsExecutor.executeScript(java_script);
		Thread.sleep(3000);
	}
	public String readFile(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}
	public void drag_the_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
