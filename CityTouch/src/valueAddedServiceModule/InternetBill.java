package valueAddedServiceModule;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class InternetBill {
	
	WebDriver driver;
	
	public InternetBill(WebDriver driver) {
		
		this.driver = driver;
		driver.get("http://172.16.229.241:8080/billpay/registration/3");
		WebElement internertBillPayment = driver.findElement(By.xpath(".//*[@id='menu']/li[6]/a"));
		internertBillPayment.click();
	}
	
	public void internetBillPay(String nickName, String rechargeCardType, String quantity, String fromAccount, String mobileNumber, String otpType){
		try {
			int otpIndex = otpType.toLowerCase() == "sms"?0:1;
			Select name = new Select(driver.findElement(By.id("alias")));
			name.selectByVisibleText(nickName);
			Select cardType = new Select(driver.findElement(By.xpath(".//*[@id='dynamicContent']/div[4]/div/div[1]/div/select")));
			cardType.selectByVisibleText(rechargeCardType);
			Select amount = new Select(driver.findElement(By.xpath(".//*[@id='dynamicContent']/div[4]/div/div[2]/div/div[1]/select")));
			amount.selectByVisibleText(quantity);
			WebElement addButton = driver.findElement(By.xpath(".//*[@id='dynamicContent']/div[4]/div/div[2]/div/div[2]/button"));
			addButton.click();
			Thread.sleep(10000);
			Select accountNumber = new Select(driver.findElement(By.id("accountNumber")));		
			accountNumber.selectByValue(fromAccount);
			WebElement mobile = driver.findElement(By.id("mobileNumber"));
			mobile.sendKeys(mobileNumber);
			List<WebElement> otp = driver.findElements(By.id("otpType"));
			otp.get(otpIndex).click();
			WebElement nextButton = driver.findElement(By.xpath(".//*[@type='submit']"));
			nextButton.click();
			Thread.sleep(3000);
			WebElement password = driver.findElement(By.id("transactionPassword"));
			password.sendKeys(new Bill(driver).otpPerse());
			WebElement payNowButton = driver.findElement(By.xpath(".//*[@type='submit']"));
			payNowButton.click();
			Thread.sleep(3000);
			WebElement backButton = driver.findElement(By.xpath(".//*[@class='btn billpayBtn']"));
			backButton.click();
		} catch (Exception e) {
			
			System.out.println("Exception occured");
		}
	}
	
	public void deleteBeneficiary(String nickName) throws InterruptedException{
		
		WebElement deleteBeneficiaryButton = driver.findElement(By.xpath(".//*[@id='content']/div[1]/div/div/div[2]/div/div[1]/a[2]/img"));
		deleteBeneficiaryButton.click();
		Select name = new Select(driver.findElement(By.id("operator")));
		name.selectByVisibleText(nickName);
		Thread.sleep(3000);
		WebElement nextButton = driver.findElement(By.xpath(".//*[@type='submit']"));
		nextButton.click();
		Thread.sleep(3000);
		WebElement confirmButton = driver.findElement(By.xpath(".//*[@type='submit']"));
		confirmButton.click();
		WebElement goHomeButton = driver.findElement(By.xpath(".//*[@class='btn billpayBtn']"));
		goHomeButton.click();
	}
	
	public void addBeneficiary(String operator, String accountId, String connectionType, String nickName, String lastFourDigitOfCard) throws InterruptedException{
		
		WebElement addbbeneficiaryButton = driver.findElement(By.xpath(".//*[@id='content']/div[1]/div/div/div[2]/div/div[1]/a[1]/img"));
		addbbeneficiaryButton.click();
		Select operatorType = new Select(driver.findElement(By.id("operator")));
		operatorType.selectByVisibleText(operator);
		WebElement accountNumber = driver.findElement(By.id("billFieldsAccountId"));
		accountNumber.sendKeys(accountId);
		Select type = new Select(driver.findElement(By.xpath(".//*[@id='dynamicContent']/div[2]/div/div/div/select")));
		type.selectByVisibleText(connectionType);
		WebElement name = driver.findElement(By.xpath(".//*[@name='alias']"));
		name.sendKeys(nickName);
		Thread.sleep(2000);
		WebElement nextButton = driver.findElement(By.xpath(".//*[@type='submit']"));
		nextButton.click();
		WebElement confirmButton = driver.findElement(By.xpath(".//*[@role='button']"));
		confirmButton.click();
		Thread.sleep(5000);
		WebElement debitCard = driver.findElement(By.id("radioChange"));
		debitCard.click();
		WebElement fourDigitOfCard = driver.findElement(By.id("cvvNumber"));
		fourDigitOfCard.sendKeys(lastFourDigitOfCard);
		WebElement okButton = driver.findElement(By.xpath(".//*[@type='button']"));
		okButton.click();
		WebElement billPayButton = driver.findElement(By.xpath(".//*[@class='btn billpayBtn']"));
		billPayButton.click();
	}
}
