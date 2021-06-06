package Exercise;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class Topic_11_12_Popup {
	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",projectPath+ "\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//driver.get("");
	}

	@Test
	public void TC_01_Fixed_Popup() {
		driver.get("https://www.zingpoll.com/");
		
	//	Assert.assertFalse(driver.findElement(By.xpath("//h4[text()=\"SIGN UP\"]/ancestor::div[@class=\"modal-content\"]")).isDisplayed());
		
		
		driver.findElement(By.cssSelector("#Loginform")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector(".modal_dialog_custom")).isDisplayed());
		
		driver.findElement(By.cssSelector(".modal_dialog_custom .close")).click();
		
		
		Assert.assertFalse(driver.findElement(By.cssSelector(".modal_dialog_custom")).isDisplayed());
	}

	@Test
	public void TC_02_Random_Popup() {
		driver.get("https://blog.testproject.io/");
		
		By xpathPopup = By.xpath("//div[@class=\"mailch-wrap\"]");
		
		if(isDisplayElement(xpathPopup)) {
			driver.findElement(By.id("close-mailch")).click();
		}
		
		WebElement inputSearch = driver.findElement(By.xpath("//aside[@id=\"secondary\"]//input[@class=\"search-field\"]"));
		String searchKey = "Selenium";
		
		inputSearch.sendKeys(searchKey);
		inputSearch.sendKeys(Keys.ENTER);
		
	////	action.keyDown(Keys.END, inputSearch).perform();
	//	action.keyUp(Keys.END, inputSearch).perform();
		
		sleepSecond(2);
		
		List<WebElement> title = driver.findElements(By.cssSelector(".post-title"));
		
		for(WebElement t : title) {
			Assert.assertTrue(t.getText().contains(searchKey));
		}
	}

	@Test
	public void TC_03_Random_Popup_NotDOM() {
		driver.get("https://shopee.vn/");
		
		By popupNotDOM = By.cssSelector(".shopee-popup__container");
		if(isDisplayElement(popupNotDOM)) {
			driver.findElement(By.cssSelector(".shopee-popup__close-btn")).click();
		}
		
		sleepSecond(1);
		
		String searchKey = "Macbook Pro";
		
		WebElement inputSearch = driver.findElement(By.cssSelector(".shopee-searchbar-input__input"));
		
		inputSearch.sendKeys(searchKey);
		inputSearch.sendKeys(Keys.ENTER);
		
		List<WebElement> list = driver.findElements(By.xpath("//div[@class=\"yQmmFK _1POlWt _36CEnF\"]"));
		
		String[] keySearchList = searchKey.split(" ");
		System.out.println("keySearchList " + keySearchList[0]);
		System.out.println("keySearchList " + keySearchList[1]);
		for (WebElement e : list ) {
			System.out.println(e.getText().toLowerCase());
			
			org.testng.Assert.assertTrue(e.getText().toLowerCase().contains(keySearchList[0].toLowerCase()) || e.getText().toLowerCase().contains(keySearchList[1].toLowerCase()));
		}
	}
	
	public boolean isDisplayElement(By path) {
	
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			List<WebElement> list = driver.findElements(path);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			if(list.size()<=0) {
				return false;
			}else {
				if(list.size()>0 && !list.get(0).isDisplayed()){
					return false;
				}
				else
					return true;
			}
	
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
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
