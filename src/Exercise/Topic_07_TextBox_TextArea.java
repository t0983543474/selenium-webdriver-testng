package Exercise;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class Topic_07_TextBox_TextArea {
	WebDriver driver;
	String email ;
	String userId ;
	String password;
	String loginPage;
	String CustomerId ;
	
	By customerName = By.name("name");
	By mailGender = By.xpath("//input[@value=\"m\"]");
	By dob = By.name("dob");
	By address = By.name("addr");
	By city = By.name("city");
	By sttae = By.name("state");
	By Pin = By.name("pinno");
	By telephone = By.name("telephoneno");
	By emailId = By.name("emailid");
	By pass = By.name("password");
	By submitNew = By.name("sub");
	
	String cusName ="Trang Le";
	String dateOfBirth = "04/18/2020";
	String AddressCus = "Binh Dinh";
	String CityCus = "AN Nhon";
	String state = "Xuan Mai";
	String PinNo = "123456";
	String phone ="098765433";
	String emailCus = getRandomEmail();
	String passCus  = "35497262";
	
	
	String AddressCusEdit = "Ho CHi Minh";
	String CityCusEdit = "Thu DUc";
	String stateEdit = "Hip binh chanh";
	String PinNoEdit = "111111";
	String phoneEdit ="098765433";
	String emailCusEdit = getRandomEmail();
	String passCusEdit  = "23456733";
	
	

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("http://demo.guru99.com/v4");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		email =  getRandomEmail();
		
	}

	@Test
	public void TC_01_Register() {
		
		loginPage = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		inputTextbox(driver, By.name("emailid"), email);
		clickButton(driver, By.name("btnLogin"));
//		Assert.assertEquals("Access details to demo site.", driver.findElement(By.xpath("//h2[text()='Access details to demo site.']")).getText());
//		sleepSecond(10);
		userId = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
//		System.out.println("userId = " + userId);
		
		
	}

	@Test
	public void TC_02_LoginPage() {
		driver.get(loginPage);
		inputTextbox(driver, By.name("uid"), userId);
		inputTextbox(driver, By.name("password"), password);
		clickButton(driver, By.name("btnLogin"));
		
		Assert.assertEquals("Welcome To Manager's Page of Guru99 Bank",driver.findElement(By.xpath("//marquee[@class=\"heading3\"]")).getText());
		
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		inputTextbox(driver, customerName, cusName);
		clickButton(driver, mailGender);
		 inputTextbox(driver, dob, dateOfBirth);
		inputTextbox(driver, address, AddressCus);
		inputTextbox(driver, city, CityCus);
		inputTextbox(driver, sttae, state);
		inputTextbox(driver, Pin, PinNo);
		inputTextbox(driver, telephone, phone);
		inputTextbox(driver, emailId, emailCus);
		inputTextbox(driver, pass, passCus);
		
		clickButton(driver, submitNew);
		
		CustomerId = driver.findElement(By.xpath("//td[text()=\"Customer ID\"]/following-sibling::td")).getText();
		
		System.out.println("CustomerId= " + CustomerId);
		Assert.assertEquals("Customer Registered Successfully!!!", driver.findElement(By.xpath("//p[@class=\"heading3\"]")).getText());
		
		Assert.assertEquals(cusName, driver.findElement(By.xpath("//td[text()=\"Customer Name\"]/following-sibling::td")).getText());
		Assert.assertEquals(AddressCus, driver.findElement(By.xpath("//td[text()=\"Address\"]/following-sibling::td")).getText());

		Assert.assertEquals(CityCus, driver.findElement(By.xpath("//td[text()=\"City\"]/following-sibling::td")).getText());
		Assert.assertEquals(state, driver.findElement(By.xpath("//td[text()=\"State\"]/following-sibling::td")).getText());

		Assert.assertEquals(PinNo, driver.findElement(By.xpath("//td[text()=\"Pin\"]/following-sibling::td")).getText());
		Assert.assertEquals(phone, driver.findElement(By.xpath("//td[text()=\"Mobile No.\"]/following-sibling::td")).getText());

//		Assert.assertEquals(email, driver.findElement(By.xpath("//td[text()=\"Email\"]/following-sibling::td")).getText());
		
		
		
		
	}

	@Test
	public void TC_03_Edit_Customer() {
		
		driver.findElement(By.xpath("//a[text()=\"Edit Customer\"]")).click();
		inputTextbox(driver, By.name("cusid"), CustomerId);
		clickButton(driver, By.name("AccSubmit"));
		
		Assert.assertEquals(cusName, driver.findElement(customerName).getText());
		Assert.assertFalse(driver.findElement(customerName).isEnabled());
		
		inputTextboxEdit(driver, address, AddressCusEdit);
		inputTextboxEdit(driver, city, CityCusEdit);
		inputTextboxEdit(driver, sttae, stateEdit);
		inputTextboxEdit(driver, Pin, PinNoEdit );
		inputTextboxEdit(driver, telephone, phoneEdit);
		inputTextboxEdit(driver, emailId, getRandomEmail());
		inputTextboxEdit(driver, pass, passCusEdit);
		
		clickButton(driver, By.name("sub"));
		
	Assert.assertEquals("Customer details updated Successfully!!!", driver.findElement(By.xpath("//p[@class=\"heading3\"]")).getText());
		
		Assert.assertEquals(cusName, driver.findElement(By.xpath("//td[text()=\"Customer Name\"]/following-sibling::td")).getText());
		Assert.assertEquals(address, driver.findElement(By.xpath("//td[text()=\"Address\"]/following-sibling::td")).getText());

		Assert.assertEquals(CityCus, driver.findElement(By.xpath("//td[text()=\"City\"]/following-sibling::td")).getText());
		Assert.assertEquals(state, driver.findElement(By.xpath("//td[text()=\"State\"]/following-sibling::td")).getText());

		Assert.assertEquals(PinNo, driver.findElement(By.xpath("//td[text()=\"Pin\"]/following-sibling::td")).getText());
		Assert.assertEquals(phone, driver.findElement(By.xpath("//td[text()=\"Mobile No.\"]/following-sibling::td")).getText());

		Assert.assertEquals(email, driver.findElement(By.xpath("//td[text()=\"Email\"]/following-sibling::td")).getText());
		
		
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	private void inputTextbox(WebDriver driver, By location, String text) {
//		driver.findElement(location).clear();
		driver.findElement(location).sendKeys(text);
		
	}
	private void inputTextboxEdit(WebDriver driver, By location, String text) {
		driver.findElement(location).clear();
		driver.findElement(location).sendKeys(text);
		
	}
	
	private void clickButton(WebDriver driver, By location) {
		driver.findElement(location).click();
	}
	private String getRandomEmail() {
		Random rand = new Random();
		return "emailtesing" + rand.nextInt() + "@gmail.com";
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
