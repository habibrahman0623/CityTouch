package cardsModule;

import org.testng.annotations.Test;
import loginModule.Login;

import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class NewTest {
	
	WebDriver driver;
  @Test
  public void othercardPayment() throws InterruptedException {
	  
	  OtherCreditCardPayment otherCreditCardPayment = new OtherCreditCardPayment(driver);
	  otherCreditCardPayment.payment("2251106207001", "BDT", "test", 10, "test", "email");
  }
  
  @BeforeTest
  public void beforeTest() {
	  
	  System.setProperty("webdriver.chrome.driver", "H:\\Software\\chromedriver_win32\\chromedriver.exe");
	  	driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);			
		Login login = new Login(driver);
		login.login("rejuan", "123456Tt");
		
  }

  @AfterTest
  public void afterTest() throws InterruptedException {
	  Thread.sleep(3000);
	  driver.quit();
  }

}
