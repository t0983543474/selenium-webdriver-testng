package Exercise;


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

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_User_Interactions {

	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExcutor;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String jsDragDropPath = projectPath + "\\DragAndDrop\\drag_and_drop_helper.js";
	String jsJqueryDragDropPath = projectPath + "/DragAndDrop/jquery_load_helper.js";
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",projectPath+ "\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExcutor =  (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 10);
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	//	driver.get("");
	}

	@Test
	public void TC_01_HoverElement() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		action.moveToElement(driver.findElement(By.id("age"))).perform();
		
		sleepSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector(".ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
		
		
	}

	@Test
	public void TC_02_HoverToElement() {
		driver.get("http://www.myntra.com/");
		action.moveToElement(driver.findElement(By.xpath("//a[text()='Kids' and @class =\"desktop-main\"]"))).perform();
		sleepSecond(1);
		action.click(driver.findElement(By.xpath("//a[text()='Home & Bath' and @class =\"desktop-categoryName\"] "))).perform();
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class =\"breadcrumbs-crumb\" and text()='Kids Home Bath']")).isDisplayed());
		
	}

	@Test
	public void TC_03_Hover_Element() {
		driver.get("https://hn.telio.vn/");
		
		Assert.assertFalse(driver.findElement(By.xpath("//div[@class=\"content-top panel\"]//span[text()='Bánh kẹo']//parent::a//following-sibling::ul")).isDisplayed());
		
		action.moveToElement(driver.findElement(By.xpath("//div[@class=\"content-top panel\"]//span[text()='Bánh kẹo']"))).perform();
		sleepSecond(1);
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class=\"content-top panel\"]//span[text()='Bánh kẹo']//parent::a//following-sibling::ul")).isDisplayed());
		
		
		
	}
	
	@Test
	public void TC_04_ClickAndHoldElement() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List<WebElement> stt = driver.findElements(By.xpath("//li[contains(@class, \"ui-selectee\")]"));
		
		action.clickAndHold(stt.get(0))
		.moveToElement(stt.get(3))
		.release().perform();
		sleepSecond(3);
		Assert.assertEquals(driver.findElements(By.cssSelector(".ui-selected")).size(), 4);
		
	}
	
	@Test
	public void TC_05_CLickAndSelectElemnt() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List<WebElement> stt = driver.findElements(By.xpath("//li[contains(@class, \"ui-selectee\")]"));
		
		action.keyDown(Keys.CONTROL).perform();
		action.click(stt.get(0))
		.click(stt.get(2))
		.click(stt.get(6))
		.click(stt.get(7))
		.perform();
		
		action.keyUp(Keys.CONTROL).perform();
		
		Assert.assertEquals(driver.findElements(By.cssSelector(".ui-selected")).size(), 4);
		
	}
	
	@Test
	public void TC_06_DoubleClick() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		jsExcutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()=\"Double click me\"]")));
		
		action.doubleClick(driver.findElement(By.xpath("//button[text()=\"Double click me\"]"))).perform();
		
		Assert.assertEquals(driver.findElement(By.id("demo")).getText(), "Hello Automation Guys!");
		
	}
	
	@Test
	public void TC_07_RightClick() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		action.contextClick(driver.findElement(By.xpath("//span[text()=\"right click me\"]"))).perform();
		
		Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-icon-quit:not(.context-menu-hover):not(.context-menu-visible)")).isDisplayed());
		
		action.moveToElement(driver.findElement(By.xpath("//li[contains(@class, \"context-menu-icon-quit\")]"))).perform();
		
		Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-icon-quit.context-menu-hover.context-menu-visible")).isDisplayed());
		action.click(driver.findElement(By.xpath("//li[contains(@class, \"context-menu-icon-quit\")]"))).perform();
		
		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
		sleepSecond(2);
		Assert.assertFalse(driver.findElement(By.cssSelector(".context-menu-list")).isDisplayed());
		
	}
	
	@Test
	public void TC_08_Drag_Drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		
		Assert.assertEquals(driver.findElement(By.id("droptarget")).getText(), "Drag the small circle here.");
		
		action.dragAndDrop(driver.findElement(By.id("draggable")), driver.findElement(By.id("droptarget"))).perform();
		Assert.assertEquals(driver.findElement(By.id("droptarget")).getText(), "You did great!");
		
	//	System.out.println((driver.findElement(By.id("droptarget")).getAttribute("background-color")));
		
		Assert.assertEquals(convertGrbaToHex(driver.findElement(By.id("droptarget")).getCssValue("background-color")), "#03a9f4");
	}
	
	@Test
	public void TC_09_Drag_Drop_HTML5() throws IOException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		
		String column_A = "#column-a";
		String column_B = "#column-b";
		
		String jsValue = readFile(jsDragDropPath);
		
		jsValue = jsValue + "$(\"" + column_A + "\").simulateDragDrop({ dropTarget: \"" + column_B + "\"});";
	
		jsExcutor.executeScript(jsValue);
		sleepSecond(3);
		jsExcutor.executeScript(jsValue);
		sleepSecond(3);
		jsExcutor.executeScript(jsValue);
		sleepSecond(3);
	}
	
	@Test
	public void TC_09_Drag_Drop_HTML5_Robot() throws IOException, AWTException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		
		drag_the_and_drop_html5_by_xpath("//div[@id=\"column-a\"]", "//div[@id=\"column-b\"]");
	
		sleepSecond(3);
		
		drag_the_and_drop_html5_by_xpath("//div[@id=\"column-a\"]", "//div[@id=\"column-b\"]");
		
		sleepSecond(3);
		
		drag_the_and_drop_html5_by_xpath("//div[@id=\"column-a\"]", "//div[@id=\"column-b\"]");
		
		sleepSecond(3);
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
	
	public String convertGrbaToHex(String  rage) {
		return Color.fromString(rage).asHex();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepSecond(long time) {
		
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

}
