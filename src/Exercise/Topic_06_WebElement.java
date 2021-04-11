package Exercise;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_WebElement {
	WebDriver driver;
	By emailTextbox = By.id("mail");
	By ageUnder18 = By.id("under_18");
	By educationTextArea = By.id("edu");
	By JobRole1 = By.id("job1");
	By JobRole2 = By.id("job2");
	By interestCheckbox = By.id("development");
	By slider1 = By.id("slider-1");
	By password = By.id("password");
	By ageRadioDisable = By.id("radio-disabled");
	By bioErea = By.id("bio");
	By job3Drop = By.id("job3");
	By interestDisabled = By.id("check-disbaled");
	By slider2 = By.id("slider-2");
	By javascript  = By.id("javascript");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
//		driver.get("https://automationfc.github.io/basic-form/index.html");
		
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
	
	@Test
	public void TC_02_Check_Enable() {

		driver.get("https://automationfc.github.io/basic-form/index.html");
		Assert.assertTrue(IsEnabled(driver, emailTextbox));
		Assert.assertTrue(IsEnabled(driver, ageUnder18));
		Assert.assertTrue(IsEnabled(driver, educationTextArea));
		Assert.assertTrue(IsEnabled(driver, JobRole1));
		Assert.assertTrue(IsEnabled(driver, JobRole2));
		Assert.assertTrue(IsEnabled(driver, interestCheckbox));
		Assert.assertTrue(IsEnabled(driver, slider1));
		Assert.assertFalse(IsEnabled(driver, password));
		Assert.assertFalse(IsEnabled(driver, ageRadioDisable));
		Assert.assertFalse(IsEnabled(driver, bioErea));
		Assert.assertFalse(IsEnabled(driver, job3Drop));
		Assert.assertFalse(IsEnabled(driver, interestDisabled));
		Assert.assertFalse(IsEnabled(driver, slider2));
		
	}
	
	@Test
	public void TC_03_Check_Selected() {
		Assert.assertFalse(IsSelected(driver, ageUnder18));
		Assert.assertFalse(IsSelected(driver, javascript));
		
		driver.findElement(ageUnder18).click();
		driver.findElement(javascript).click();
		
		Assert.assertTrue(IsSelected(driver, ageUnder18));
		Assert.assertTrue(IsSelected(driver, javascript));
		
		driver.findElement(ageUnder18).click();
		driver.findElement(javascript).click();
		
		
		Assert.assertTrue(IsSelected(driver, ageUnder18));
		Assert.assertFalse(IsSelected(driver, javascript));
		
	}
	
	@Test
	public void TC_04_Register_Mailchimp() {
		driver.get("https://login.mailchimp.com/signup/");
		
		inputTextbox(driver, By.id("email"), "trang@fuvi.vn");
		inputTextbox(driver, By.id("new_username"), "trangle");		
		
		By pass = By.id("new_password");
		
		inputTextbox(driver, pass, "Auto");
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")).isDisplayed());

		Assert.assertFalse(IsEnabled(driver, By.id("create-account")));
		
		inputTextbox(driver, pass, "Auto@");
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed' and text()='One special character']")).isDisplayed());

		
		Assert.assertFalse(IsEnabled(driver, By.id("create-account")));
		
		
	inputTextbox(driver, pass, "Auto@1");
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed' and text()='One special character']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed' and text()='One number']")).isDisplayed());

		Assert.assertFalse(IsEnabled(driver, By.id("create-account")));
		
     inputTextbox(driver, pass, "Auto111111");
		
     
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")).isDisplayed());
//		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed' and text()='One special character']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed' and text()='One number']")).isDisplayed());

		
		
		Assert.assertFalse(IsEnabled(driver, By.id("create-account")));
		
		 inputTextbox(driver, pass, "Auto@12345");
			
//			Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")).isDisplayed());
//			Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")).isDisplayed());
//			Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed' and text()='One special character']")).isDisplayed());
//			Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")).isDisplayed());
//			Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed' and text()='One number']")).isDisplayed());
//
			Assert.assertTrue(IsEnabled(driver, By.id("create-account")));
	}
	
	private void clickElement(WebDriver driver, By location) {
		driver.findElement(location).click();
	}
	
	private void inputTextbox(WebDriver driver, By location, String text) {
		driver.findElement(location).clear();
		driver.findElement(location).sendKeys(text);
		
	}
	
	private boolean IsSelected(WebDriver driver, By location) {
		try
		{
			WebElement e = driver.findElement(location);
			if(e.isSelected()) {
				System.out.println(location + " " + "is Selected");
				return true;
			}
			else {
				System.out.println(location + " " + "is de_Selected");
				return false;
			}
			
		}catch (NoSuchElementException e) {
			return false;
		}
	}
	
	private boolean IsEnabled(WebDriver driver, By location) {
		try {
			if(driver.findElement(location).isEnabled()) {
				System.out.println(location + "_" + "is enabled");
				return true;
			}else {
				System.out.println(location + "_" + "is disabled");
				return false;
			}
			
			
		}catch(NoSuchElementException e) {
			return false;
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
