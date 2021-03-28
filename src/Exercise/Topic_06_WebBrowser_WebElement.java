package Exercise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_WebBrowser_WebElement {
	
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_VerifyURL() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[text()=\"My Account\"]")).click();
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url, "http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//span[text()=\"Create an Account\"]")).click();
		String urlCreate = driver.getCurrentUrl();
		Assert.assertEquals(urlCreate, "http://live.demoguru99.com/index.php/customer/account/create/");
		
	}

	@Test
	public void TC_02_Verify_Title() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[text()=\"My Account\"]")).click();
	
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		driver.findElement(By.xpath("//span[text()=\"Create an Account\"]")).click();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}

	@Test
	public void TC_03_Navigate_Function() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[text()=\"My Account\"]")).click();
		driver.findElement(By.xpath("//span[text()=\"Create an Account\"]")).click();
		
		driver.navigate().back();
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		driver.navigate().forward();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		
	}
	
	@Test
	public void TC_04_Get_Page_Source_Code() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[text()=\"My Account\"]")).click();
	
		Assert.assertTrue(driver.getPageSource().contains("LOGIN OR CREATE AN ACCOUNT"));
		
		driver.findElement(By.xpath("//span[text()=\"Create an Account\"]")).click();
		
		Assert.assertTrue(driver.getPageSource().contains("CREATE AN ACCOUNT"));
		
	}
	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}


}
