package Exercise;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_WebElement {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void TC_01_Check_Element_Display() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		WebElement emailDisplay = IsDisplay(driver, "#mail");
		WebElement ageDisplay = IsDisplay(driver, "#under_18");
		WebElement EducationDisplay = IsDisplay(driver, "#edu");
		
		if(emailDisplay.isDisplayed()) {
			emailDisplay.sendKeys("trang@gmail.com");
			System.out.println("Email is display");
		}else {
			System.out.println("Email is not display");
		}

		if(EducationDisplay.isDisplayed()) {
			emailDisplay.sendKeys("iuifefewfwf");
			System.out.println("Education is display");
		}else {
			System.out.println("Education is not display");
		}
		
		if(ageDisplay.isDisplayed()) {
			ageDisplay.click();
			System.out.println("Age is display");
		}else {
			System.out.println("Age is not display");
		}
		
		
	}
	
	private  WebElement IsDisplay(WebDriver driver,  String location) {
		try {
			WebElement element =  driver.findElement(By.cssSelector(location));
			
			return element;
//			return element.isDisplayed();
		} catch (NoSuchElementException e) {
			return null;
		}
	
	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
