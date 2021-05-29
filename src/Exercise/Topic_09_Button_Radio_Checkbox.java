package Exercise;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class Topic_09_Button_Radio_Checkbox {

	WebDriver driver;
	JavascriptExecutor jsExecutor;
	
	By button_Login =By.xpath("//button[@class=\"fhs-btn-login\"]");
	By tab_Login = By.xpath("//ul[@id=\"popup-login-tab_list\"]//li[contains(@class, \"popup-login-tab-login\")]");

	By login_username = By.id("login_username");
	By login_password = By.id("login_password");
	
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",projectPath+ "\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		//expliciWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
//		driver.get("");
	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		
		By popupRandom = By.xpath("//img[@class=\"smt-edit-image\"]");
		
		if(isDisplayElement(popupRandom)) {
			driver.findElement(By.xpath("//a[@id=\"NC_CLOSE_ICON\"]//img")).click();
		}
		
//	By popupRandom2 = By.xpath("//iframe[@class=\"st_preview_frame_modal\"]");
//		
//		if(isDisplayElement(popupRandom2)) {
//			driver.findElement(By.xpath("//a[@id=\"NC_CLOSE_ICON\"]//img")).click();
//		}
//		
		driver.findElement(tab_Login).click();

		Assert.assertFalse(driver.findElement(button_Login).isEnabled());
		
		driver.findElement(login_username).sendKeys("trang@gmail.com");
		driver.findElement(login_password).sendKeys("123456789");
		
		Assert.assertTrue(driver.findElement(button_Login).isEnabled());
		
		driver.navigate().refresh();
		
		driver.findElement(tab_Login).click();
		if(isDisplayElement(popupRandom)) {
			driver.findElement(By.xpath("//a[@id=\"NC_CLOSE_ICON\"]//img")).click();
		}
//		if(isDisplayElement(popupRandom2)) {
//			driver.findElement(By.xpath("//a[@id=\"NC_CLOSE_ICON\"]//img")).click();
//		}
//		
		
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled');", driver.findElement(button_Login));
		
		driver.findElement(button_Login).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id=\"login_username\"]/parent::div/following-sibling::div")).getText(), "Thông tin này không thể để trống");

		
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id=\"login_password\"]/parent::div/following-sibling::div")).getText(), "Thông tin này không thể để trống");

		
		
		
	}
	
	public boolean isDisplayElement(By element) {
		try {
			return driver.findElement(element).isDisplayed();
		}catch (Exception e) {
			return false;
			// TODO: handle exception
		}
	}

	@Test
	public void TC_02_DefaultCheckbox_RadioButton() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
	//	jsExecutor.executeScript("arguments[0].scrollIntoView(true);", 	driver.findElement(By.cssSelector("#eq5")));
		//button[@id="onetrust-accept-btn-handler"]
		
		if(isDisplayElement(By.xpath("//button[@id=\"onetrust-accept-btn-handler\"]"))) {
			driver.findElement(By.xpath("//button[@id=\"onetrust-accept-btn-handler\"]")).click();
		}
		
		driver.findElement(By.cssSelector("#eq5")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("#eq5")).isSelected());
		
		driver.findElement(By.cssSelector("#eq5")).click();
		
		Assert.assertFalse(driver.findElement(By.cssSelector("#eq5")).isSelected());
		
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		
		Assert.assertFalse(driver.findElement(By.xpath("//input[@id=\"engine3\"]")).isSelected());
		driver.findElement(By.xpath("//input[@id=\"engine3\"]")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id=\"engine3\"]")).isSelected());
		
		if(!driver.findElement(By.xpath("//input[@id=\"engine3\"]")).isSelected()) {
			driver.findElement(By.xpath("//input[@id=\"engine3\"]")).click();
		}
	}

	@Test
	public void TC_03_Custom_Checkbox_RadioButton() {
		driver.get("https://material.angular.io/components/radio/examples");
		Assert.assertFalse(driver.findElement(By.xpath("//mat-radio-button[@id=\"mat-radio-4\"]")).getAttribute("class").contains("mat-radio-checked"));
		driver.findElement(By.xpath("//mat-radio-button[@id=\"mat-radio-4\"]")).click();
		sleepSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//mat-radio-button[@id=\"mat-radio-4\"]")).getAttribute("class").contains("mat-radio-checked"));
		
		driver.get ("https://material.angular.io/components/checkbox/examples");
		
		driver.findElement(By.xpath("//mat-checkbox[@id=\"mat-checkbox-1\"]")).click();
		driver.findElement(By.xpath("//mat-checkbox[@id=\"mat-checkbox-2\"]")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//mat-checkbox[@id=\"mat-checkbox-1\"]")).getAttribute("class").contains("mat-checkbox-checked"));
		Assert.assertTrue(driver.findElement(By.xpath("//mat-checkbox[@id=\"mat-checkbox-2\"]")).getAttribute("class").contains("mat-checkbox-checked"));
	
		driver.findElement(By.xpath("//mat-checkbox[@id=\"mat-checkbox-1\"]")).click();
		driver.findElement(By.xpath("//mat-checkbox[@id=\"mat-checkbox-2\"]")).click();
		
		Assert.assertFalse(driver.findElement(By.xpath("//mat-checkbox[@id=\"mat-checkbox-1\"]")).getAttribute("class").contains("mat-checkbox-checked"));
		Assert.assertFalse(driver.findElement(By.xpath("//mat-checkbox[@id=\"mat-checkbox-2\"]")).getAttribute("class").contains("mat-checkbox-checked"));
	
		
		
	}

	@Test
	public void TC_04_CustomCheckbox_Or_RadioButton() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		By elementCanTho = By.xpath("//span[text()='Cần Thơ']/ancestor::label[contains(@class, \"isChecked\")]");
	
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Assert.assertFalse(isDisplayElement(elementCanTho));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//span[text()='Cần Thơ']")).click();
		
		sleepSecond(3);
		
		Assert.assertTrue(driver.findElement(elementCanTho).isDisplayed());
		
		
		
		
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

}
