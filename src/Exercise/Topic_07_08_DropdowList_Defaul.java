package Exercise;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_08_DropdowList_Defaul {
	
	WebDriver driver;
	Select dropdownDay, dropdownMonth, dropdownYear;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
//		driver.get("https://automationfc.github.io/basic-form/index.html");
	}

	@Test
	public void TC_01_HTML_DropdowList() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		Select selectJob1 = new Select(driver.findElement(By.id("job1")));
		Assert.assertFalse(selectJob1.isMultiple());
		
		selectJob1.selectByVisibleText("Mobile Testing");
		
		Assert.assertEquals("Mobile Testing", selectJob1.getFirstSelectedOption().getText());
		
		sleepSecond(2);
		
		selectJob1.selectByValue("manual");
		Assert.assertEquals("Manual Testing", selectJob1.getFirstSelectedOption().getText());
		
		sleepSecond(2);
		
		List<WebElement> listOptions = selectJob1.getOptions();
		
		selectJob1.selectByIndex(listOptions.size()-1);
		Assert.assertEquals("Functional UI Testing", selectJob1.getFirstSelectedOption().getText());
		
		Assert.assertEquals(listOptions.size(), 10);
		
		Select selectJob2 = new Select(driver.findElement(By.id("job2")));
		Assert.assertTrue(selectJob2.isMultiple());
		
	}

	@Test
	public void TC_02_HTML_Dropdownlist() {
		String email = getRandomEmail();
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.id("gender-female")).click();
		driver.findElement(By.id("FirstName")).sendKeys("Trang");
		driver.findElement(By.id("LastName")).sendKeys("Le");
		driver.findElement(By.id("Email")).sendKeys(email);
		
		dropdownDay = new Select (driver.findElement(By.name("DateOfBirthDay")));
		dropdownDay.selectByVisibleText("1");
		Assert.assertEquals("1", dropdownDay.getFirstSelectedOption().getText());
		List<WebElement> optionsDay = dropdownDay.getOptions();
		Assert.assertEquals(32, optionsDay.size());
		
		dropdownMonth = new Select (driver.findElement(By.name("DateOfBirthMonth")));
		
		dropdownMonth.selectByVisibleText("May");
		Assert.assertEquals("May", dropdownMonth.getFirstSelectedOption().getText());
		List<WebElement> optionsMonth = dropdownMonth.getOptions();
		Assert.assertEquals(13, optionsMonth.size());
		
		
		dropdownYear = new Select (driver.findElement(By.name("DateOfBirthYear")));
		
		dropdownYear.selectByVisibleText("1980");
		Assert.assertEquals("1980", dropdownYear.getFirstSelectedOption().getText());
		List<WebElement> optionsYear = dropdownYear.getOptions();
		Assert.assertEquals(112, optionsYear.size());
		
		driver.findElement(By.name("Password")).sendKeys("123456789");
		driver.findElement(By.name("ConfirmPassword")).sendKeys("123456789");
		
		JavascriptExecutor js = (JavascriptExecutor)driver;	
		
		 js.executeScript("arguments[0].click();", driver.findElement(By.id("register-button")));
		
//		sleepSecond(2);
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class=\"result\"]")).getText(), "Your registration completed");
	
		driver.findElement(By.xpath("//a[@class=\"ico-account\"]")).click();
		sleepSecond(2);
		dropdownDay = new Select (driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(dropdownDay.getFirstSelectedOption().getText(), "1");
		dropdownMonth = new Select (driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(dropdownMonth.getFirstSelectedOption().getText(), "May");
		dropdownYear = new Select (driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(dropdownYear.getFirstSelectedOption().getText(), "1980");
	}

	@Test
	public void TC_03_() {
	
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepSecond(long time) {
		
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String getRandomEmail() {
		Random rand = new Random();
		return "emailtesing" + rand.nextInt() + "@gmail.com";
	}



}
