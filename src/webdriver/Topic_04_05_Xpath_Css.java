package webdriver;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class Topic_04_05_Xpath_Css {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/");
	}

	@Test
	public void TC_01_LoginWithEmptyEmailAndPassword() {
	
		driver.findElement(By.xpath(".//*[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath(".//*[@id='send2']")).click();
		
		Assert.assertEquals("This is a required field.", driver.findElement(By.xpath(".//*[@id='advice-required-entry-email']")).getText());
		Assert.assertEquals("This is a required field.", driver.findElement(By.xpath(".//*[@id='advice-required-entry-pass']")).getText());

	}
	
	@Test
	public void TC_02_LoginWithInvalidEmail() {
		driver.findElement(By.xpath(".//*[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath(".//*[@id='send2']")).click();
		driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("12345@gmail.774343");
		driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys("123456");
		driver.findElement(By.xpath(".//*[@id='send2']")).click();
		Assert.assertEquals("Please enter a valid email address. For example johndoe@domain.com.", driver.findElement(By.xpath(".//*[@id='advice-validate-email-email']")).getText());
		
	}
	
	@Test
	public void TC_03_LoginWIthPasswordLessThan6() {
		driver.findElement(By.xpath(".//*[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath(".//*[@id='send2']")).click();
		driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("12345@gmail.com");
		driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys("12345");
		driver.findElement(By.xpath(".//*[@id='send2']")).click();
		Assert.assertEquals("Please enter 6 or more characters without leading or trailing spaces.", driver.findElement(By.xpath(".//*[@id='advice-validate-password-pass']")).getText());
		
	}
	
	@Test
	public void TC_04_LoginWithIncorrectEmailPassword() {
		driver.findElement(By.xpath(".//*[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath(".//*[@id='send2']")).click();
		driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("automaton@gmail.com");
		driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys("123123123");
		driver.findElement(By.xpath(".//*[@id='send2']")).click();
		Assert.assertEquals("Invalid login or password.", driver.findElement(By.cssSelector(".error-msg>ul>li>span")).getText());
		
	}
	
	public String generateStringForEmail() {
		 int leftLimit = 97; // letter 'a'
		    int rightLimit = 122; // letter 'z'
		    int targetStringLength = 10;
		    Random random = new Random();
		    StringBuilder buffer = new StringBuilder(targetStringLength);
		    for (int i = 0; i < targetStringLength; i++) {
		        int randomLimitedInt = leftLimit + (int) 
		          (random.nextFloat() * (rightLimit - leftLimit + 1));
		        buffer.append((char) randomLimitedInt);
		    }
		    String generatedString = buffer.toString();

		    return generatedString;
	}
	
	@Test
	public void TC_05_CreateNewAccount() {
		driver.findElement(By.xpath(".//*[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath(".//*[@id='login-form']//a[@class='button']")).click();
		
	    String generatedString =generateStringForEmail();
	    String email = generatedString+ "@gmail.com" ;
	    String lastName = "Le";
	    String firstName ="Trang";
	    
	    driver.findElement(By.xpath(".//*[@id='firstname']")).sendKeys(firstName);
	    driver.findElement(By.xpath(".//*[@id='lastname']")).sendKeys(lastName);
	    driver.findElement(By.xpath(".//*[@id='email_address']")).sendKeys(email);
	    driver.findElement(By.xpath(".//*[@id='password']")).sendKeys("Trang@123");
	    driver.findElement(By.xpath(".//*[@id='confirmation']")).sendKeys("Trang@123");
	    driver.findElement(By.xpath(".//*[@id='is_subscribed']")).click();
	    driver.findElement(By.xpath(".//*[@type=\"submit\" and @title=\"Register\" ]")).click();
	   Assert.assertEquals("Thank you for registering with Main Website Store.", driver.findElement(By.cssSelector(".success-msg>ul>li>span")).getText());
//	   String text =  driver.findElement(By.xpath(".//*[@class='col-1']//p")).getText();
//	   System.out.println("Name : " + text);
//	   List<String> result = Arrays.asList(text.split("<br/>"));
//	   for(int i=0;i<result.size() ; i++) {
//		   
//		   System.out.println(result.get(i));
//	   }
//	   Assert.assertEquals(firstName + " " + lastName, result.get(0));
//	   Assert.assertEquals(email, result.get(1));
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
