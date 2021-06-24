package Exercise;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_JavaScriptExecutor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",projectPath+ "\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 10);
		jsExecutor = (JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
//		driver.get("");
	}

	//@Test
	public void TC_01_JavascriptExcuor() {
		navigateToUrlByJS("http://live.demoguru99.com/");
		
		String domail =(String) executeForBrowser("return document.domain;");
		
		Assert.assertEquals(domail, "live.demoguru99.com");
		
		String URL = (String) executeForBrowser("return document.URL;");
		
		Assert.assertEquals(URL, "http://live.demoguru99.com/");
		
		clickToElementByJS("//a[text()=\"Mobile\"]");
		
		clickToElementByJS("//a[text()=\"Samsung Galaxy\"]/parent::h2/following-sibling::div[@class=\"actions\"]//button");
		
		
		Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));
		
		clickToElementByJS("//a[text()=\"Customer Service\"]");
		
		String titleCus =(String) executeForBrowser("return document.title;");
		
		Assert.assertEquals(titleCus, "Customer Service");
		
		scrollToElement("//input[@type=\"email\"]");
		
		sendkeyToElementByJS("//input[@type=\"email\"]", getRandomEmail());
		
		clickToElementByJS("//button[@title=\"Subscribe\"]");
		//sleepInSecond(3);
		
		Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));
		
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		
		String domailGuru =(String) executeForBrowser("return document.domain;");
		
		Assert.assertEquals(domailGuru, "demo.guru99.com");
		
		
		
	}

	private String getRandomEmail() {
		Random rand = new Random();
		return  rand.nextInt() + "@gmail.com";
	}
	//@Test
	public void TC_02_Validation_Message() {
		navigateToUrlByJS("https://automationfc.github.io/html5/index.html");
		clickToElementByJS("//input[@type=\"submit\"]");
		
		Assert.assertEquals(getElementValidationMessage("//input[@type=\"name\"]"), "Please fill out this field.");

		sendkeyToElementByJS("//input[@type=\"name\"]", "Trang Le");
		
		clickToElementByJS("//input[@type=\"submit\"]");
		
		Assert.assertEquals(getElementValidationMessage("//input[@type=\"password\"]"), "Please fill out this field.");

		sendkeyToElementByJS("//input[@type=\"password\"]", "Trang@gmail.com");
		
		clickToElementByJS("//input[@type=\"submit\"]");
		
		Assert.assertEquals(getElementValidationMessage("//input[@type=\"email\"]"), "Please fill out this field.");

		sendkeyToElementByJS("//input[@type=\"email\"]", "123!@123");
		Assert.assertEquals(getElementValidationMessage("//input[@type=\"email\"]"), "Please match the requested format.");
		
		
		sendkeyToElementByJS("//input[@type=\"email\"]", "trang@gmail.com");
		
		
		
		clickToElementByJS("//input[@type=\"submit\"]");
		
		Assert.assertEquals(getElementValidationMessage("//select"), "Please select an item in the list.");

	}

	//@Test
	public void TC_03_HTML5_Validation_Message() {
		navigateToUrlByJS("https://login.ubuntu.com/");
		
		if(isDisplayElement(By.cssSelector(".p-modal__dialog"))) {
			clickToElementByJS("//button[text()=\"Accept all and visit site\"]");
		}
		
		clickToElementByJS("//form[@id=\"login-form\"]//button[@name=\"continue\"]");
		
		Assert.assertEquals(getElementValidationMessage("//form[@id=\"login-form\"]//input[@name=\"email\"]"), "Please fill out this field.");
		
		sendkeyToElementByJS("//form[@id=\"login-form\"]//input[@name=\"email\"]", "a");
		
		
	}
	
	//@Test
	public void TC_04_Remove_Attribute() {
		
		
		navigateToUrlByJS("http://demo.guru99.com/");
		
		String email = getRandomEmail();
		
		System.out.println("email:" + email);
		
		sendkeyToElementByJS("//input[@name=\"emailid\"]", email);
		
		clickToElementByJS("//input[@name=\"btnLogin\"]");
		
		sleepInSecond(3);
		
		String username = driver.findElement(By.xpath("//td[text()=\"User ID :\"]/following-sibling::td")).getText().trim();
		
		String password = driver.findElement(By.xpath("//td[text()=\"Password :\"]/following-sibling::td")).getText().trim();
		navigateToUrlByJS("http://demo.guru99.com/v4");
		
		sendkeyToElementByJS("//input[@name=\"uid\"]", username);
		
		sendkeyToElementByJS("//input[@name=\"password\"]", password);
		
		clickToElementByJS("//input[@name=\"btnLogin\"]");
		
		sleepInSecond(1);
		
		clickToElementByJS("//a[text()=\"New Customer\"]");
		
		
		removeAttributeInDOM("//input[@id=\"dob\"]", "type");
		
		sleepInSecond(2);
		
		sendkeyToElementByJS("//input[@name=\"name\"]", "Trang");
		
//		clickToElementByJS("//input[@value=\"f\"]");
		
		sendkeyToElementByJS("//input[@name=\"dob\"]", "04/18/2020");
		
//		sendkeyToElementByJS("//textarea[@name=\"addr\"]", "Xuan Mai");
		
		driver.findElement(By.xpath("//textarea[@name=\"addr\"]")).sendKeys("Nhown Hanh");
		
		sendkeyToElementByJS("//input[@name=\"city\"]", "Binh DInh");
		
		sendkeyToElementByJS("//input[@name=\"state\"]", "state");
		
		sendkeyToElementByJS("//input[@name=\"pinno\"]", "123456");
		
		sendkeyToElementByJS("//input[@name=\"telephoneno\"]", "098765322");
		
		sendkeyToElementByJS("//input[@name=\"emailid\"]", email);
		
		sendkeyToElementByJS("//input[@name=\"password\"]", "123456");
		
		sleepInSecond(3);
		
		clickToElementByJS("//input[@name=\"sub\"]");
		
		sleepInSecond(10);
		
		Assert.assertTrue(getInnerText().contains("Customer Registered Successfully!!!"));
		
	}

	
	@Test
	public void TC_05_CreateAnAccount() {
		navigateToUrlByJS("http://live.demoguru99.com/");
		
		clickToElementByJS("//div[@id=\"header-account\"]//a[text()=\"My Account\"]");
		
		clickToElementByJS("//a[@title=\"Create an Account\"]");
		
		sendkeyToElementByJS("//input[@id=\"firstname\"]", "Trang");
		
		sendkeyToElementByJS("//input[@id=\"lastname\"]", "Le");
		
		sendkeyToElementByJS("//input[@id=\"email_address\"]", getRandomEmail());
		
		sendkeyToElementByJS("//input[@id=\"password\"]", "123!@#TT");
		
		sendkeyToElementByJS("//input[@id=\"confirmation\"]", "123!@#TT");
		
		clickToElementByJS("//button[@title=\"Register\"]");
		
		Assert.assertTrue(getInnerText().contains("Thank you for registering with Main Website Store."));
		
		
		clickToElementByJS("//a[@title=\"Log Out\"]");
		
		boolean urlCorretc = explicitWait.until(ExpectedConditions.urlMatches("http://live.demoguru99.com/index.php/"));
		System.out.println("urlCorretc" + urlCorretc);
		
		Assert.assertTrue(urlCorretc);
		
		
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public boolean isDisplayElement(By path) {
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			List<WebElement> list = driver.findElements(path);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			if(list.size()<=0) {
				return false;
			}else {
				if(list.size()>0 && !list.get(0).isDisplayed()){
					return false;
				}
				else
					return true;
			}
	
	}
	
	public void sleepInSecond(long time) {
		
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElement(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
}
