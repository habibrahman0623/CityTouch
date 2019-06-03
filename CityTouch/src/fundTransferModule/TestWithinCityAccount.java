package fundTransferModule;

import org.testng.annotations.Test;

import loginModule.Login;

import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class TestWithinCityAccount {
  
	WebDriver driver;
	TransferToWithinCityAccount toWithinCityAccount;
	
	@Test
	public void fundTransferWithinCityAccount(){
		 // TransferToWithinCityAccount transferToWithinCityAccount = new TransferToWithinCityAccount(driver);
		  toWithinCityAccount.toWithinCityAccounts("6761106207004","testBDT",10,"test", "email");
		  toWithinCityAccount.toWithinCityAccounts("6761106207004","testBDT",10,"test", "sms");
    }
	
  @Test
  public void addBeneficiaryByOwnAccount() throws InterruptedException {
		toWithinCityAccount.addBeneficiaryByOwnAccount("Auto test", "6761106207004", "01234567899", "b2@gmail.com", "0005");
		
  }
	
  @Test
  public void addBeneficiaryAndDeleteWithinCityAccount() throws InterruptedException{	
		
		
		try {
			toWithinCityAccount.deleteBeneficiary("test you");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			toWithinCityAccount.addBeneficiary("test you", "2101774441001", "01234567899", "b2@gmail.com", "0005");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
  }
  @BeforeTest
  public void beforeTest() {
	  System.setProperty("webdriver.chrome.driver", "H:\\Software\\chromedriver_win32\\chromedriver.exe");
	  	driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Login login = new Login(driver);
		login.login("rejuan", "123456Tt");
		toWithinCityAccount = new TransferToWithinCityAccount(driver);
  }

  @AfterTest
  public void afterTest() throws InterruptedException {
	  
	  Thread.sleep(2000);
	  driver.quit();
  }

}
