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
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",projectPath+ "\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		expliciWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
//		driver.get("");
	}

//	@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectItemInCustomDropdow("//span[@id=\"number-button\"]", "//ul[@id=\"number-menu\"]//div", "17");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class=\"ui-selectmenu-text\" and text()=17]")).isDisplayed());
		
	}

//	@Test
	public void TC_02_ReactJs() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemInCustomDropdow("//div[@role=\"listbox\"]", "//div[@role=\"option\"]//span", "Justen Kitsune");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@role=\"alert\" and text()=\"Justen Kitsune\"]")).isDisplayed());
	}

//	@Test
	public void TC_03_VueJs() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemInCustomDropdow("//li[@class=\"dropdown-toggle\"]", "//ul[@class=\"dropdown-menu\"]//a", "Second Option");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"dropdown-toggle\" and contains(text(),'Second Option')]")).isDisplayed());
		
	}

	//@Test
	public void TC_04_Angular(){
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		selectItemInCustomDropdow("//ejs-dropdownlist[@id=\"games\"]//span[contains(@class, \"e-search-icon\")]", "//ul[@id=\"games_options\"]//li", "Tennis");
		Assert.assertEquals(driver.findElement(By.xpath("//ejs-dropdownlist[@id=\"games\"]//input")).getAttribute("aria-label"), "Tennis");
	
		//sleepSecond(5);
	}
	
	//@Test
	public void TC_05_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		selectItemInEdibale("//input[@class=\"search\"]", "//span[@class=\"text\"]", "Belize");
	//	sleepSecond(5);
	Assert.assertEquals("Belize", driver.findElement(By.xpath("//div[@role=\"alert\"]")).getText());
	System.out.println("Trang"+ driver.findElement(By.xpath("//div[@role=\"alert\"]")).getText());
	//	Assert.assertTrue(driver.findElement(By.xpath("//div[@role=\"alert\"]//i")).getText().contains("Belize"));
	}
	
	@Test
	public void TC_06_MultipleSelect() {
		driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
		String[] expected = {"February", "April"};
		selectMultiple("(//button[@class=\"ms-choice\"])[1]","(//button[@class=\"ms-choice\"])[1]/following-sibling::div//span" ,expected);
	
		Assert.assertTrue(areItemSelected(expected));
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
	
	public void selectItemInEdibale(String parentElement, String childXpath, String expectedText) {
		
		// Click the cha
		driver.findElement(By.xpath(parentElement)).click();
		sleepSecond(1);
		driver.findElement(By.xpath(parentElement)).sendKeys(expectedText);
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

	public void selectMultiple(String parentXpath, String childXpath, String[] expectedText) {
		
		// click vao dropdowlist 
		driver.findElement(By.xpath(parentXpath)).click();
		
		// CHo cho tat ca cac phan tu load ra thanh cong
		List<WebElement> listItems = expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		
		for(WebElement chilElement : listItems) {
			
			for(String itemText : expectedText) {
				if(chilElement.getText().equals(itemText)) {
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", chilElement);
					sleepSecond(1);
					
					chilElement.click();
					
					sleepSecond(1);
					List<WebElement> selectedItem = driver.findElements(By.xpath("//li[@class=\"selected\"]//input"));
					if(selectedItem.size() == expectedText.length)
					{
						break;
					}
				}
			}
		}
	}
	
	public boolean areItemSelected(String[] expectedText) {
		List<WebElement>  selectedItem = driver.findElements(By.xpath("//li[@class=\"selected\"]//input"));
		int countItems = selectedItem.size();
		String expectedInput = driver.findElement(By.xpath("//button[@class=\"ms-choice\"]//span[1]")).getText();
		if(countItems<= 3 && countItems>0) {
			boolean status = true;
			
			for(WebElement item : selectedItem) {
				if (!expectedInput.contains(item.getText())) {
					status = false;
					return status;
				}
			}
			return status;
		}else {
			if(countItems>=12) {
				return driver.findElement(By.xpath("//button[@class=\"ms-choice\"]/span[text()='All selected']")).isDisplayed();
			}else {
				if(countItems>3 && countItems<12) {
					return driver.findElement(By.xpath("//button[@class=\"ms-choice\"]/span[text()=' " + countItems + "of 12 selected']")).isDisplayed();
				}else {
					return false;
				}
			}
		}
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepSecond(long time) {
		
		try {
			Thread.sleep(time * 100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
