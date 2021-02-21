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
	String lastName ;
	String firstName;
	String email;
	String pass;

	@BeforeClass
	public void beforeClass() {
		String generatedString =generateStringForEmail();
		 email = generatedString+ "@gmail.com" ;
		 lastName = "Le";
		 firstName = "Trang";
		 pass = "Trang@123";
		
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
		
		driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("12345@gmail.774343");
		driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys("123456");
		driver.findElement(By.xpath(".//*[@id='send2']")).click();
		Assert.assertEquals("Please enter a valid email address. For example johndoe@domain.com.", driver.findElement(By.xpath(".//*[@id='advice-validate-email-email']")).getText());
		
	}
	
	@Test
	public void TC_03_LoginWIthPasswordLessThan6() {
		driver.findElement(By.xpath(".//*[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("12345@gmail.com");
		driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys("12345");
		driver.findElement(By.xpath(".//*[@id='send2']")).click();
		Assert.assertEquals("Please enter 6 or more characters without leading or trailing spaces.", driver.findElement(By.xpath(".//*[@id='advice-validate-password-pass']")).getText());
		
	}
	
	@Test
	public void TC_04_LoginWithIncorrectEmailPassword() {
		driver.findElement(By.xpath(".//*[@class='footer']//a[@title='My Account']")).click();
	
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
		driver.findElement(By.xpath("//*[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//*[@id='login-form']//a[@class='button']")).click();
		
	 
	    
	    driver.findElement(By.xpath(".//*[@id='firstname']")).sendKeys(firstName);
	    driver.findElement(By.xpath(".//*[@id='lastname']")).sendKeys(lastName);
	    driver.findElement(By.xpath(".//*[@id='email_address']")).sendKeys(email);
	    driver.findElement(By.xpath(".//*[@id='password']")).sendKeys(pass);
	    driver.findElement(By.xpath(".//*[@id='confirmation']")).sendKeys(pass);
	    driver.findElement(By.xpath(".//*[@id='is_subscribed']")).click();
	    driver.findElement(By.xpath(".//*[@type=\"submit\" and @title=\"Register\" ]")).click();
	   Assert.assertEquals("Thank you for registering with Main Website Store.", driver.findElement(By.cssSelector(".success-msg>ul>li>span")).getText());
	   String text =  driver.findElement(By.xpath(".//*[@class='col-1']//p")).getText();

	 text = stringToHTMLString(text);
	   List<String> result = Arrays.asList(text.split("<br/>"));

	   Assert.assertEquals(firstName + " " + lastName, result.get(0));
	   Assert.assertEquals(email, result.get(1));
	   driver.findElement(By.xpath(".//*[@id='header']//span[text()='Account']")).click();
	   driver.findElement(By.xpath(".//*[@title='Log Out']")).click();
	}
	public  String stringToHTMLString(String string) {
	    StringBuffer sb = new StringBuffer(string.length());
	    // true if last char was blank
	    boolean lastWasBlankChar = false;
	    int len = string.length();
	    char c;

	    for (int i = 0; i < len; i++) {
	        c = string.charAt(i);
	        if (c == ' ') {
	            // blank gets extra work,
	            // this solves the problem you get if you replace all
	            // blanks with &nbsp;, if you do that you loss 
	            // word breaking
	            if (lastWasBlankChar) {
	                lastWasBlankChar = false;
	                sb.append("&nbsp;");
	            } else {
	                lastWasBlankChar = true;
	                sb.append(' ');
	            }
	        } else {
	            lastWasBlankChar = false;
	            //
	            // HTML Special Chars
	            if (c == '"')
	                sb.append("&quot;");
	            else if (c == '&')
	                sb.append("&amp;");
	            else if (c == '<')
	                sb.append("&lt;");
	            else if (c == '>')
	                sb.append("&gt;");
	            else if (c == '\n')
	                // Handle Newline
	                sb.append("<br/>");
	            else {
	                int ci = 0xffff & c;
	                if (ci < 160)
	                    // nothing special only 7 Bit
	                    sb.append(c);
	                else {
	                    // Not 7 Bit use the unicode system
	                    sb.append("&#");
	                    sb.append(new Integer(ci).toString());
	                    sb.append(';');
	                }
	            }
	        }
	    }
	    return sb.toString();
	}
	
	@Test 
	public void TC_06_LoginWithValidEmailAndPassword() {
		driver.findElement(By.xpath(".//*[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath(".//*[@id='email']")).sendKeys(email);
		driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys(pass);
		driver.findElement(By.xpath(".//*[@id='send2']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
		
		Assert.assertEquals("Hello, "+ firstName + " " + lastName + "!", driver.findElement(By.xpath("//*[@class='hello']/strong")).getText());
		 String text =  driver.findElement(By.xpath(".//*[@class='col-1']//p")).getText();

		 text = stringToHTMLString(text);
		   List<String> result = Arrays.asList(text.split("<br/>"));

		   Assert.assertEquals(firstName + " " + lastName, result.get(0));
		   Assert.assertEquals(email, result.get(1));
	}
	
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
