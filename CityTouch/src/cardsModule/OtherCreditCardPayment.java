package cardsModule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class OtherCreditCardPayment {
	
	WebDriver driver;
	public OtherCreditCardPayment(WebDriver driver) {
		
		this.driver = driver;
		this.driver.get("http://172.16.229.241:8080/account/cards/other/payment");
	}
	
	public void payment(String sender,String currency, String receiver, Integer amount, String remarks, String otpType) throws InterruptedException{
		
		int otpIndex = otpType.toLowerCase() == "sms"?0:1;
		WebElement nextButton = driver.findElement(By.xpath(".//*[@type='submit']"));
		nextButton.click();
		String toAcMessage = driver.findElement(By.id("toCardNumber-error")).getText();
		if(toAcMessage.contains("Credit Card is required.")){
			Select toAccount = new Select(driver.findElement(By.id("alias")));
			toAccount.selectByVisibleText(receiver);;
		}
		
		WebElement usdCurrency = driver.findElement(By.id("currencyUSD"));
		if(!currency.toLowerCase().contains("bdt")){
			Thread.sleep(2000);
			usdCurrency.click();
		}
		
		String fromAcMessage = "";
		try {
			fromAcMessage = driver.findElement(By.id("fromAccountNumber-error")).getText();
		} catch (Exception e) {
			// 
			e.printStackTrace();
		}
		if(fromAcMessage.contains("From Account is required.")){
			Select fromAccount = new Select(driver.findElement(By.id("fromAccountNumber")));
			fromAccount.selectByValue(sender);
			Thread.sleep(2000);
			nextButton.click();
		}
		
		String amountMessage = driver.findElement(By.id("transferAmount-error")).getText();
		if(amountMessage.contains("required.") || amountMessage.contains("Requried.")){
			driver.findElement(By.id("transferAmount")).sendKeys(amount.toString());
			
		}
		
		driver.findElement(By.id("remarks")).sendKeys("test");	
		driver.findElements(By.id("otpType")).get(otpIndex).click();
		nextButton.click();
		String userMessage = driver.findElement(By.id("userMessage")).getText();
		if(userMessage.contains("A One-Time Password (OTP) has been sent to your registered mobile/email. Please enter below.")){
			WebElement requestForTransferButton = driver.findElement(By.xpath(".//*[@type='submit']"));
			requestForTransferButton.click();		
			String passwordMessage = driver.findElement(By.id("transactionPassword-error")).getText();
			if(passwordMessage.contains("must not be empty.")){
				driver.findElement(By.id("transactionPassword")).sendKeys("1234");
			}
				
			requestForTransferButton.click();
			driver.findElement(By.xpath(".//*[@id='cardPaymentSuccess']/div[11]/div/a")).click();
		}
		
		
		
		
	}
	
	public void addbeneficiary(){
		
		WebElement addbeneficiaryButton = driver.findElement(By.xpath(".//*[@id='content']/div/div[1]/div[1]/div[2]/div/a[1]/img"));
		addbeneficiaryButton.click();
		
	}
	
	

}
