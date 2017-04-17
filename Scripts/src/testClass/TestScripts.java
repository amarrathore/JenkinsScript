package testClass;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.internal.Dynamic;

import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

@Listeners(testClass.ScreenshotITest.class)
public class TestScripts extends WebDriverFactory {
	public Actions action;
	
	@Test
	public void test() throws Exception {
		try {
			System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.get("https://www.volaris.com/?culture=en-US&Flag=us");
			Thread.sleep(5000);
			driver.findElement(By.id("btn_origen")).click();
			Thread.sleep(2000);
			driver.findElement(By.linkText("Cancun")).click();
			driver.findElement(By.xpath("//*[@id='input_destination']")).click();
			driver.findElement(By.linkText("Mexico City")).click();
			driver.findElement(By.xpath("//*[@id='selDestinFlight']/div[1]/div/button")).click();
			WebElement oneWay = driver.findElement(By.cssSelector("[id='ulTrips'] li:nth-child(2)"));
			action = new Actions(driver);
			action.moveToElement(oneWay).click().build().perform();
			driver.findElement(By.id("btnNext")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("btnPassenger")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("btnSearchFlight")).click();	
			String parentWindow = driver.getWindowHandle();	    
			Set<String> set =  driver.getWindowHandles();
			Iterator<String> iterator = set.iterator();
			  while(iterator.hasNext()) {
				  String childWindow = iterator.next();
				  if(!parentWindow.equals(childWindow)) {
			    	  driver.switchTo().window(childWindow).getTitle();
			    	  }
			  }
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id='sortedAvailability0']/div[1]/div[1]/div[1]/div[2]")).click();
			driver.findElement(By.id("submit_search_button")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id='modcont-combodeals']/div[2]/a")).click();
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
			//driver.findElement(By.id("ckbPrimaryCustomerInformation")).click();
			driver.findElement(By.id("volarisContact_Name_First")).sendKeys("test");
			driver.findElement(By.id("volarisContact_Name_Last")).sendKeys("test");
			driver.findElement(By.id("volarisContact_EmailAddress")).sendKeys("test@test.com");
			driver.findElement(By.id("volarisContact_VerifyEmailAddress")).sendKeys("test@test.com");
			driver.findElement(By.id("volarisContact_HomePhone")).sendKeys("12456");
			driver.findElement(By.className("checkbox-label")).click();
			driver.findElement(By.id("submit_passenger_button")).click();
			//driver.quit();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	
	}
}
