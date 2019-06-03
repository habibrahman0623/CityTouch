package fundTransferModule;

import org.testng.annotations.Test;
import loginModule.Login;
import org.testng.annotations.BeforeTest;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class NewTest {
	
	WebDriver driver ;
	
 /*@Test
  public void fundTransferToOutsideCityAccount() throws InterruptedException{
	  TransferToOutsideCityAccount transferToOutsideCityAccount = new TransferToOutsideCityAccount(driver);
	  transferToOutsideCityAccount.toOutSideCityAccount("6761106207004","test user",10,"test", "EFTN", "email", "now", "Yearly", 2);
  }
  
  @Test
  public void visaInstantPayment(){
	  VisaInstantPayment visaInstantPayment = new VisaInstantPayment(driver);
	  visaInstantPayment.visaInstantPayment("6761106207004", "asdasd", 10, "test", "email");
  }*/
  
  
 /* @Test
  public void addBeneficiaryAndDeleteInVisaInstantPayment() throws InterruptedException {
	  VisaInstantPayment visaInstantPayment = new VisaInstantPayment(driver);
	  try {
		visaInstantPayment.deleteBeneficiary("asdasd");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println("This beneficiary is not exist");
		e.printStackTrace();
	}
	  visaInstantPayment.addBeneficiary("asdasd", "123456789098765", "test", "01234567899", "b2@gmail.com", "0005");
	  	  
  }*/
  
  @Test
  public void addBeneficiaryAndDeleteOutsideCityAccount() throws InterruptedException{
	  TransferToOutsideCityAccount transferToOutsideCityAccount = new TransferToOutsideCityAccount(driver);
	  try {
		transferToOutsideCityAccount.deleteBeneficiary("CNN Just");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println("This beneficiary is not exist");
		e.printStackTrace();
	}
	  transferToOutsideCityAccount.addbeneficiary("CNN Just", "chub", "45678888", "090130319", "01234567899", "b2@gmail.com", "0005");
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
	  Thread.sleep(2000);
	  driver.quit();
  }

}
