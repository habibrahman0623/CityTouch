package valueAddedServiceModule;

import org.testng.annotations.Test;

import loginModule.Login;

import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class BillPayTest {
	
	WebDriver driver;
	
	 @Test
	  public void mobileBillPay() throws InterruptedException {
		  MobileBill mobileBill = new MobileBill(driver);
		  mobileBill.payMobileBill("1test","2251106207001", "10", "sms");
	  }
	  
	  @Test
	  public void clubBillPay(){
		  ClubPayment clubPayment = new ClubPayment(driver);
		  clubPayment.clubPay("Hossain test", "2251106207001", "10", "email");
	  }
	  
	  @Test
	  public void insuranceBillPay(){
		  InsurancePayment insurancePayment = new InsurancePayment(driver);
		  insurancePayment.insurancePay("Khan", "Premium", "2251106207001", "10", "email");
	  }
	  
	  @Test
	  public void internetBillPay(){
		  InternetBill internetBill = new InternetBill(driver);
		  internetBill.internetBillPay("bl061", "99 TK", "1", "2251106207001", "01234567899", "email");
	  }
	  
	  @Test
	  public void deleteAndAddBeneficiaryOfMobileBill(){
		  
		  MobileBill mobileBill = new MobileBill(driver);
		  try {
			mobileBill.deleteBeneficiary("Auto Test");
		  } catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("This beneficiary is not exist");
			e.printStackTrace();
		  }
		  mobileBill.addBeneficiary("Bangla Link", "Prepaid", "12345678", "Auto Test", "0005");
	  }
	  
	  @Test
	  public void deleteAndAddBeneficiaryOfClubPayment() throws InterruptedException{
		  ClubPayment clubPayment = new ClubPayment(driver);
		  clubPayment.deleteBeneficiary("test");
		  clubPayment.addBeneficiary("Dhaka Club", "A-71", "test", "0005");
	  }
	  
	  @Test
	  public void deleteAndAddBeneficiaryOfInsurancePayment() throws InterruptedException{
		  InsurancePayment insurancePayment = new InsurancePayment(driver);
		  insurancePayment.deleteBeneficiary("Khan");
		  insurancePayment.addBeneficiary("MetLife", "324234", "Ame", "Khan", "0005");
		  
	  }
	  
	  @Test
	  public void deleteAndAddBeneficiaryOfInternetBill() throws InterruptedException{
		  InternetBill internetBill = new InternetBill(driver);
		  internetBill.deleteBeneficiary("bl061");
		  internetBill.addBeneficiary("BanglaLion", "ctgict.tc061", "Prepaid", "bl061", "0005");
	  }
	  
	  @Test
	  public void deleteAndAddBeneficiaryOfUtilityBill() throws InterruptedException{
		  
		  UtilityBill utilityBill = new UtilityBill(driver);
		  utilityBill.deleteBeneficiary("asdadsad");
		  utilityBill.addBeneficiary("DESCO", "1253", "1253632523", "asdadsad", "0005");
	  }
	  
	  @Test
	  public void deleteAndAddBeneficiaryOfTutionFee() throws InterruptedException{
		  TuitionFee tuitionFee = new TuitionFee(driver);
		  tuitionFee.deleteBeneficiary("A10");
		  tuitionFee.addBeneficiary("NSU", "1311254758", "A10", "0005");
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
	  public void afterTest() {
		  
		  try {
			Thread.sleep(5000);
			driver.quit();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }

}
