package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Browser {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");


	@Test
	public void TC_01_Firefox() {
		driver = new FirefoxDriver();
		driver.get("https://demo.nopcommerce.com");
		driver.quit();
		
	}

	@Test
	public void TC_02_Chrome_For_Window() {
		
		// Window
		System.setProperty("webdriver.chrome.driver", projectPath + "\\BrowserDrivers\\chromedriver.exe");
		
		driver = new ChromeDriver();
		driver.get("https://demo.nopcommerce.com");
		driver.quit();
		
		
	}

//	@Test
	public void TC_03_Chrome_For_MAC() {
		//MAC
				//Step 1: 
				// Step 2 Set permission for chrome
			System.setProperty("webdriver.chrome.driver", projectPath + "//BrowserDrivers//chromedriver");
				
				driver = new ChromeDriver();
				driver.get("https://demo.nopcommerce.com");
				driver.quit();
	}
	@Test
	public void TC_04_Edge_For_Window() {
		
		// Window
		System.setProperty("webdriver.edge.driver", projectPath + "\\BrowserDrivers\\msedgedriver.exe");
		
		driver = new EdgeDriver();
		driver.get("https://demo.nopcommerce.com");
		driver.quit();
		
	}
	//@Test
	public void TC_05_Edge_For_MAC() {
		// Window
		System.setProperty("webdriver.edge.driver", projectPath + "//BrowserDrivers//msedgedriver");
		
		driver = new EdgeDriver();
		driver.get("https://demo.nopcommerce.com");
		driver.quit();
	}


}
