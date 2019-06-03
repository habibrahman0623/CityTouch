package valueAddedServiceModule;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Bill {
	
	WebDriver driver;
	
	public Bill(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		driver.get("http://172.16.229.241:8080/billpay/registration/3");
	}
	
	public void billPay(String nickName, String insurancePaidFor, String fromAccount, String amount, String otpType) throws InterruptedException{
		
		int otpIndex = otpType.toLowerCase() == "sms"?0:1;
		Select name = new Select(driver.findElement(By.id("alias")));
		name.selectByVisibleText(nickName);
		if(insurancePaidFor.length() != 0){
			Select insuranceType = new Select(driver.findElement(By.id("InsurancePaidFor")));
			insuranceType.selectByVisibleText(insurancePaidFor);
		}
		Thread.sleep(10000);
		Select accountNumber = new Select(driver.findElement(By.id("accountNumber")));		
		accountNumber.selectByValue(fromAccount);
		WebElement billAmount = driver.findElement(By.id("amount"));
		billAmount.sendKeys(amount);
		List<WebElement> otp = driver.findElements(By.id("otpType"));
		otp.get(otpIndex).click();
		WebElement nextButton = driver.findElement(By.xpath(".//*[@type='submit']"));
		nextButton.click();
		Thread.sleep(3000);
		WebElement password = driver.findElement(By.id("transactionPassword"));
		password.sendKeys(otpPerse());
		WebElement payNowButton = driver.findElement(By.xpath(".//*[@type='submit']"));
		payNowButton.click();
		Thread.sleep(3000);
		WebElement backButton = driver.findElement(By.xpath(".//*[@class='btn billpayBtn']"));
		backButton.click();
		
	}
	
	public String otpPerse(){
		String otp = "1234";
		
		return otp;
	}

}
