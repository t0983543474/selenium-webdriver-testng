package Exercise;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class Topic_07_08_DropdownList_Custom {
	
	WebDriver driver;
	WebDriverWait expliciWait;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		expliciWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("");
	}

	@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectItemInCustomDropdow("//span[@id=\"number-button\"]", "//ul[@id=\"number-menu\"]//div", "17");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class=\"ui-selectmenu-text\" and text()=17]")).isDisplayed());
		
	}

	@Test
	public void TC_02_ReactJs() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemInCustomDropdow("//div[@role=\"listbox\"]", "//div[@role=\"option\"]//span", "Justen Kitsune");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@role=\"alert\" and text()=\"Justen Kitsune\"]")).isDisplayed());
	}

	@Test
	public void TC_03_VueJs() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemInCustomDropdow("//li[@class=\"dropdown-toggle\"]", "//ul[@class=\"dropdown-menu\"]//a", "Second Option");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"dropdown-toggle\" and contains(text(),'Second Option')]")).isDisplayed());
		
	}
	
	public void selectItemInCustomDropdow(String parentElement, String childXpath, String expectedText) {
		
		// Click the cha
		driver.findElement(By.xpath(parentElement)).click();
		sleepSecond(1);
		// CHo cho tat ca cac item duoc load ra het
		List<WebElement> allItems = expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		
		for(WebElement item : allItems) {
			if(item.getText().equals(expectedText)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepSecond(1);
				item.click();
				break;
			}
		}
		
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


}
