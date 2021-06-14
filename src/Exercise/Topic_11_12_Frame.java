package Exercise;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_12_Frame {
	
	WebDriver driver;
	JavascriptExecutor jsExcutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsExcutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	//	driver.get("");
	}

	@Test
	public void TC_05_IFrame() {
		driver.get("https://kyna.vn/");
		
		WebElement iframe = driver.findElement(By.xpath("//iframe[contains(@src,\"www.facebook.com\" )]"));
		
		jsExcutor.executeScript("arguments[0].scrollIntoView(true);", iframe);
		Assert.assertTrue(iframe.isDisplayed());
		
		driver.switchTo().frame(iframe);
		
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'169K')]")).isDisplayed());
	
        driver.switchTo().defaultContent();
        
        driver.findElement(By.id("cs_chat_iframe")).click();
        
        driver.switchTo().frame("cs_chat_iframe");
        driver.findElement(By.xpath("//input[contains(@class, \"input_name\")]")).sendKeys("Phuong");
        driver.findElement(By.xpath("//input[contains(@class, \"input_phone\")]")).sendKeys("0987654532");
        
        Select service = new Select(driver.findElement(By.id("serviceSelect")));
        service.selectByVisibleText("HỖ TRỢ KỸ THUẬT");
        
        driver.findElement(By.xpath("//textarea[@name=\"message\"]")).sendKeys("Testing");
        
        driver.findElement(By.xpath("//input[contains(@class,\"submit\")]")).click();
        
//        sleepSecond(2);
      Assert.assertTrue(  driver.findElement(By.xpath("//label[contains(@class,\"logged_in_name\") and text()=\"Phuong\"]")).isDisplayed());
    
      
      Assert.assertTrue(  driver.findElement(By.xpath("//label[contains(@class,\"logged_in_phone\") and text()=\"0987654532\"]")).isDisplayed());
      
  
      Assert.assertTrue(  driver.findElement(By.xpath("  //textarea[@name=\"message\" and text()=\"Testing\"]")).isDisplayed());
      
      driver.switchTo().defaultContent();
      
      WebElement seacrBar = driver.findElement(By.id("live-search-bar"));
      
      jsExcutor.executeScript("arguments[0].scrollIntoView(true);", seacrBar);
      String searchKey = "Excel";
      seacrBar.sendKeys(searchKey);
      seacrBar.sendKeys(Keys.ENTER);
      
      List<WebElement> list = driver.findElements(By.xpath("//ul[@class=\"k-box-card-list\"]//h4"));
      
      for(WebElement  e : list) {
    	  Assert.assertTrue(e.getText().toLowerCase().contains(searchKey.toLowerCase()));
      }
	}

	@Test
	public void TC_06_Frame() {
		driver.get("https://v1.hdfcbank.com/assets/popuppages/netbanking.htm");
		driver.findElement(By.xpath("(//div[contains(@class, \"text-center\")]//a[text()=\"Continue to NetBanking\"])[1]")).click();
		
		driver.switchTo().frame("login_page");
		
		driver.findElement(By.name("fldLoginUserId")).sendKeys("098765433");
		
		driver.findElement(By.xpath("//input[@name=\"fldLoginUserId\"]//ancestor::table[@class=\"lForm\"]//img")).click();
		
		Assert.assertTrue(driver.findElement(By.name("fldPassword")).isDisplayed());
		
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame("footer");
		
		driver.findElement(By.xpath("//form[@name=\"frmFooter\"]//a[text()=\"Terms and Conditions\"]")).click();
		
	}

	@Test
	public void TC_07_() {
	
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
