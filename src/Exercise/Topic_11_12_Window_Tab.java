package Exercise;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class Topic_11_12_Window_Tab {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",projectPath+ "\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor= (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
//		driver.get("");
	}

	@Test
	public void TC_07_Window_Tab() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//a[text()=\"GOOGLE\"]")).click();
		String IdWindowDefault = driver.getWindowHandle();
		switchTabByTitle("Google");
		Assert.assertTrue(driver.getTitle().equals("Google"));
		
		switchTabByTitle("SELENIUM WEBDRIVER FORM DEMO");
		
		
		driver.findElement(By.xpath("//a[text()=\"FACEBOOK\"]")).click();
		switchTabByTitle("Facebook - Đăng nhập hoặc đăng ký");
		
		Assert.assertTrue(driver.getTitle().equals("Facebook - Đăng nhập hoặc đăng ký"));
		
		switchTabByTitle("SELENIUM WEBDRIVER FORM DEMO");
		
		driver.findElement(By.xpath("//a[text()=\"TIKI\"]")).click();
		switchTabByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		
		Assert.assertTrue(driver.getTitle().equals("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh"));
		
	//	switchTabByTitle("SELENIUM WEBDRIVER FORM DEMO");
		
		Assert.assertTrue(CloseAllWindowWithouParent(IdWindowDefault));
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
		
		
		
		
	}

	@Test
	public void TC_08_Window_Tab() {
		driver.get("https://kyna.vn/");
		
		String parentWindow = driver.getWindowHandle();
		
		jsExecutor.executeScript( "window.scrollBy(0,document.body.scrollHeight)");
		
		driver.findElement(By.xpath("//div[@id=\"k-footer\"]//img[@alt=\"facebook\"]")).click();
		
		switchTabByTitle("Kyna.vn - Trang chủ | Facebook");
		
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Trang chủ | Facebook");
		
		switchTabByTitle("Kyna.vn - Học online cùng chuyên gia");
		
		driver.findElement(By.xpath("//div[@id=\"k-footer\"]//img[@alt=\"youtube\"]")).click();
		
		switchTabByTitle("Kyna.vn - YouTube");
		
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - YouTube");
		switchTabByTitle("Kyna.vn - Học online cùng chuyên gia");
		
		driver.findElement(By.xpath("//div[@id=\"k-footer\"]//img[@alt=\"zalo\"]")).click();
		
		
		switchTabByTitle("Zalo Official Account");
		
		Assert.assertEquals(driver.getTitle(), "Zalo Official Account");
		
		org.testng.Assert.assertTrue(CloseAllWindowWithouParent(parentWindow)); 
		
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Học online cùng chuyên gia");
		
	}

	@Test
	public void TC_09_Window_Tab() {
		driver.get("http://live.demoguru99.com/index.php/");
		
		String parentWindow = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()=\"Mobile\"]")).click();
		
		driver.findElement(By.xpath("//a[text()=\"Sony Xperia\"]/ancestor::li//a[text()=\"Add to Compare\"]")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"success-msg\"]//span[text()=\"The product Sony Xperia has been added to comparison list.\"]")).isDisplayed());
        
		
		driver.findElement(By.xpath("//a[text()=\"Samsung Galaxy\"]/ancestor::li//a[text()=\"Add to Compare\"]")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"success-msg\"]//span[text()=\"The product Samsung Galaxy has been added to comparison list.\"]")).isDisplayed());
        
		driver.findElement(By.xpath("//span[text()=\"Compare\"]")).click();
		
		switchTabByTitle("Products Comparison List - Magento Commerce");
		
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		
		CloseAllWindowWithouParent(parentWindow);
		
		driver.findElement(By.xpath("//a[text()=\"Clear All\"]")).click();
		
		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		alert.accept();
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()=\"The comparison list was cleared.\"]")).isDisplayed());
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void swichTabById(String id) {
		Set<String> allWindows = driver.getWindowHandles();
		
		for(String windowId : allWindows) {
			if(!windowId.equals(id)) {
				driver.switchTo().window(windowId);
				break;
			}
		}
	}
	
	public void switchTabByTitle(String title) {
		Set<String> allWindows = driver.getWindowHandles();
		
		for(String windowId : allWindows) {
			driver.switchTo().window(windowId);
			if(driver.getTitle().equals(title)) {
				break;
			}
		}
	}
	
	public  boolean CloseAllWindowWithouParent(String parentId) {
		Set<String> allWindows = driver.getWindowHandles();
		
		for(String winId : allWindows) {
			if(!winId.equals(parentId)) {
				driver.switchTo().window(winId);
				driver.close();
			}
		}
		driver.switchTo().window(parentId);
		
		if(driver.getWindowHandles().size()==1)
			return true;
		else
			return false;
	}
	
	public void sleepSecond(long time) {
		
		try {
			Thread.sleep(time *1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
