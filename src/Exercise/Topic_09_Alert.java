package Exercise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class Topic_09_Alert {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 10);
		jsExecutor = (JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//driver.get("");
	}

	//@Test
	public void TC_05_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		WebElement element = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
		
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
		
		element.click();
		
		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id=\"result\"]")).getText(), "You clicked an alert successfully");

	}

	//@Test
	public void TC_06_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		WebElement element = driver.findElement(By.xpath("//button[text()=\"Click for JS Confirm\"]"));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
		
		element.click();
		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id=\"result\"]")).getText(), "You clicked: Ok");
		
		element.click();
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id=\"result\"]")).getText(), "You clicked: Cancel");
		
		
	}

	//@Test
	public void TC_07_Promt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		WebElement element = driver.findElement(By.xpath("//button[text()=\"Click for JS Prompt\"]"));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
		
		element.click();
		String k= "Trang";
		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		alert.sendKeys(k);
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id=\"result\"]")).getText(), "You entered: " + k);
		
		
	}
	
	@Test
	public void TC_09_Authentication_Alert() {
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials')]")).isDisplayed());
		
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
