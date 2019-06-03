package fundTransferModule;

import org.testng.annotations.Test;

import loginModule.Login;

import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class TestMyAccount {
  
	WebDriver driver;
	TransferToMyCityAccount transferToMyCityAccount;
	
  @Test
  public void sameAccount() throws InterruptedException {
	  
	  transferToMyCityAccount.sameAccount("6761106207004", "6761106207004", 10,"test", "email");
  }
  
  /*@Test
  public void fundTransferWithoutOTP(){
	  
	  transferToMyCityAccount.withoutOTP("6761106207004", "2251106207001", 10,"test", "email");
  }*/
  
  @Test
  public void fundTransferToMyAccount(){
	  
	  transferToMyCityAccount.toMyCityAccounts("6761106207004", "2251106207001", 10,"test", "email");
	  transferToMyCityAccount.toMyCityAccounts("6761106207004", "2251106207001", 10,"test", "sms");
  }
  
  
  @BeforeTest
  public void beforeTest() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "H:\\Software\\chromedriver_win32\\chromedriver.exe");
	  	driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);			
		Login login = new Login(driver);
		login.login("rejuan", "123456Tt");
		transferToMyCityAccount = new TransferToMyCityAccount(driver);
  }

  @AfterTest(alwaysRun = true)
  public void afterTest() throws InterruptedException {
	  Thread.sleep(2000);
	  driver.quit();
  }

}
