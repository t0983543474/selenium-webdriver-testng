package Exercise;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class Topic_14_UploadFile {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait waitDriver;
	
	String img1 = projectPath + "\\UploadFile\\Image-01.PNG";
	String img2 = projectPath + "\\UploadFile\\Image-02.PNG";
	String img3 = projectPath + "\\UploadFile\\Image-03.PNG" ;
	
	String nameImg1 = "Image-01.PNG";
	String nameImg2 = "Image-02.PNG";
	String nameImg3 = "Image-03.PNG";
	
	String autoITChromePath =  projectPath + "\\AutoIT\\ChromeSingleFile.exe";
	String autoITChromeMultiplePath = projectPath  + "\\AutoIT\\ChromeMultipleFile.exe";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",projectPath+ "\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		waitDriver = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_UploadBySendKey() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		driver.findElement(By.xpath("//input[@type=\"file\"]")).sendKeys(img1 + "\n" + img2 + "\n" + img3);
		
		
		sleepSecond(1);
		
		//p[text()="Image-01.PNG"]
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()=\"Image-01.PNG\"]")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()=\"Image-02.PNG\"]")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()=\"Image-03.PNG\"]")).isDisplayed());
		
	
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table .start"));
		
		
		for(WebElement item : startButtons) {
			item.click();
			
			sleepSecond(1);
		}
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + nameImg1 + "']")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + nameImg2 + "']")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + nameImg3 + "']")).isDisplayed());
		
		
	}

	@Test
	public void TC_02_AutoIT_Single_Chrome() throws IOException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		driver.findElement(By.cssSelector(".btn-success")).click();
		
		
		Runtime.getRuntime().exec(new String[] {autoITChromePath, img1});
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()=\"Image-01.PNG\"]")).isDisplayed());
		

		List<WebElement> startButtons = driver.findElements(By.cssSelector("table .start"));
	
		for(WebElement item : startButtons) {
			item.click();
			
			sleepSecond(1);
		}
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + nameImg1 + "']")).isDisplayed());
		
	}

	@Test
	public void TC_03_Chrome_AutoIT_Multiple() throws IOException {
	  driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		driver.findElement(By.cssSelector(".btn-success")).click();
		
		
		Runtime.getRuntime().exec(new String[] {autoITChromeMultiplePath, img1, img2});
		
Assert.assertTrue(driver.findElement(By.xpath("//p[text()=\"Image-01.PNG\"]")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()=\"Image-02.PNG\"]")).isDisplayed());
		
//		Assert.assertTrue(driver.findElement(By.xpath("//p[text()=\"Image-03.PNG\"]")).isDisplayed());
		
	
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table .start"));
		
		
		for(WebElement item : startButtons) {
			item.click();
			
			sleepSecond(1);
		}
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + nameImg1 + "']")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + nameImg2 + "']")).isDisplayed());
		
//		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + nameImg3 + "']")).isDisplayed());
		
		
	}

	@Test
	public void TC_04_UploadFile() {
		driver.get("https://gofile.io/?t=uploadFiles");
		
		driver.findElement(By.id("uploadFile-Input")).sendKeys(img1 + "\n" + img2 + "\n" + img3);
		
		waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.id("rowUploadSuccess-showFiles")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//h5[text()=\"Your files have been successfully uploaded\"]")).isDisplayed());
		
		driver.findElement(By.id("rowUploadSuccess-showFiles")).click();
		
		sleepSecond(5);
		List<WebElement> fileNames = driver.findElements(By.xpath("//span[@class='contentName']"));
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + nameImg1 + "']")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + nameImg2 + "']")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + nameImg3 + "']")).isDisplayed());
		
//		driver.findElement(By.xpath("//span[text()='Upload files']")).click();
		
		driver.findElement(By.xpath("//span[text()=\"Image-01.PNG\"]/ancestor::div[contains(@class, 'text-sm-left')]/following-sibling::div[contains(@class, 'col-sm-1')]//button")).click();
		
	   org.testng.Assert.assertTrue(	driver.findElement(By.xpath("//span[text()=\"Image-01.PNG\"]/ancestor::div[contains(@class, 'text-sm-left')]/following-sibling::div[contains(@class, 'col-sm-1')]//div[contains(@class, 'dropdown-menu')]//a[text()='Download']")).isDisplayed()
		);
	
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
