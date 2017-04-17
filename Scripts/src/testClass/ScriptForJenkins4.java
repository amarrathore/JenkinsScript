package testClass;

import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

@Listeners(testClass.ScreenshotITest.class)
public class ScriptForJenkins4 extends WebDriverFactory {
	public Actions action;
	public WebDriverWait explicitWait;
	public JavascriptExecutor executor = (JavascriptExecutor)driver;
	
	@BeforeClass
	public void setPreCondion() {
		try {
			System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.get("https://www.volaris.com/?culture=en-US&Flag=us");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test(priority = 1)
	public void home() throws Exception {
		try {
			explicitWait = new WebDriverWait (driver, 50);
			WebElement sourceField = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn_origen")));
			action.moveToElement(sourceField).click().build().perform();
			//WebElement sourceField = driver.findElement(By.id("btn_origen"));
			
			WebElement source = driver.findElement(By.linkText("Cancun"));
			
			/*executor.executeScript("scroll(250, 0)");
			source.click();*/
			//executor.executeScript("arguments[0].click();", source);
			/*explicitWait = new WebDriverWait (driver, 20);
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Cancun"))).click();*/
			driver.findElement(By.xpath("//*[@id='input_destination']")).click();
			
			WebElement destination = driver.findElement(By.linkText("Mexico City"));
			//executor.executeScript("arguments[0].click();", destination);
			//action.moveToElement(destination).click().build().perform();
			driver.findElement(By.xpath("//*[@id='selDestinFlight']/div[1]/div/button")).click();
			WebElement oneWay = driver.findElement(By.cssSelector("[id='ulTrips'] li:nth-child(2)"));
			action = new Actions(driver);
			action.moveToElement(oneWay).click().build().perform();
			driver.findElement(By.id("btnNext")).click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test(priority = 2)
	public void selectDate() {
		try {
			Thread.sleep(3000);
			driver.findElement(By.id("btnPassenger")).click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test(priority = 3)
	public void selectPAX() {
		try {
			Thread.sleep(3000);
			driver.findElement(By.id("btnSearchFlight")).click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test(priority = 4)
	public void searchFlight() throws InterruptedException {
		try {
			Thread.sleep(5000);
			String searchText = "Choose your departing flight for Cancun to Mexico City";
			String expectedText = driver.findElement(By.id("tripDisplay_0")).getText();
			Assert.assertEquals(searchText, expectedText);
			driver.findElement(By.xpath("//*[@id='sortedAvailability0']/div[1]/div[1]/div[1]/div[2]")).click();
			driver.findElement(By.id("submit_search_button")).click();
			Thread.sleep(3000);
			driver.findElement(By.className("js-bundle-modal-option")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority = 5)
	public void PAXInfo() {
		try {
			Thread.sleep(5000);
			String infoText = "WE NEED SOME INFO ON WHO'S GOING.";
			String expectedText = driver.findElement(By.xpath(".//*[@id='passengerForm']/h1")).getText();
			Assert.assertEquals(infoText, expectedText);
			driver.findElement(By.id("volarisPassengers_0__Name_First")).sendKeys("test");
			driver.findElement(By.id("volarisPassengers_0__Name_Last")).sendKeys("test");
			WebElement month = driver.findElement(By.id("volarisPassengers_0__month"));
			WebElement day = driver.findElement(By.id("volarisPassengers_0__day"));
			WebElement year = driver.findElement(By.id("volarisPassengers_0__year"));
			WebElement nationality = driver.findElement(By.id("volarisPassengers_0__Info_Nationality"));
			Select select1 = new Select(month);
			select1.selectByVisibleText("Jan");
			Select select2 = new Select(day);
			select2.selectByIndex(5);
			Select select3 = new Select(year);
			select3.selectByIndex(30);
			Select select4 = new Select(nationality);
			select4.selectByVisibleText("Mexico");
			driver.findElement(By.id("volarisPassengers_0__Info_Gender_Male")).click();
			driver.findElement(By.id("volarisContact_Name_First")).sendKeys("test");
			driver.findElement(By.id("volarisContact_Name_Last")).sendKeys("test");
			driver.findElement(By.id("volarisContact_EmailAddress")).sendKeys("test@test.com");
			driver.findElement(By.id("volarisContact_VerifyEmailAddress")).sendKeys("test@test.com");
			driver.findElement(By.id("volarisContact_HomePhone")).sendKeys("12456");
			driver.findElement(By.xpath("//*[@id='passengerForm']/div[2]/div/div/div/div/label[1]/span")).click();
			driver.findElement(By.cssSelector("#submit_passenger_button")).click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test(priority = 6)
	public void customers() throws InterruptedException {
		try {
			Thread.sleep(3000);
			String customersText = "CUSTOMERS";
			String expectedText = driver.findElement(By.cssSelector(".content-h.visible-bpl")).getText();
			Assert.assertEquals(customersText, expectedText);
			driver.findElement(By.xpath("//*[@id='submit_bundle_button']")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority = 7)
	public void selectSeat() {
		try {
			Thread.sleep(5000);
			driver.findElement(By.cssSelector(".mfp-close")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//div[3]/div/button")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority = 8)
	public void tripExtras() throws InterruptedException {
		try {
			Thread.sleep(5000);
			String tripExtrasText = "YOU DECIDE WHAT TO ADD.";
			String expectedText = driver.findElement(By.cssSelector(".content-h")).getText();
			Assert.assertEquals(tripExtrasText, expectedText);
			driver.findElement(By.cssSelector("button#submit_extras_button")).click();
			driver.findElement(By.xpath("//a[contains(@class,'tripExtrasNoThanks')]")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority = 9)
	public void carAndPayment() throws InterruptedException {
		try {
			Thread.sleep(5000);
			driver.findElement(By.xpath(".//div/div[3]/button[@id='submit_cars_button']")).click();
			Thread.sleep(3000);
			String paymentText = "Select your payment method";
			String expectedText = driver.findElement(By.cssSelector(".paymentOptionContainer>h1")).getText();
			Assert.assertEquals(paymentText, expectedText);
			System.out.println("Welcome to Payment Page.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void closeTest() {
		driver.quit();
	}
}
