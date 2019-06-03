package fundTransferModule;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;



public class FundTransfer {
	
	WebDriver driver;
	
	public FundTransfer(WebDriver driver) {
		
		this.driver = driver;
		this.driver.get("http://172.16.229.241:8080/fundtransfer/mycityaccounts/transfer");
		
	}
	public void transferFund(String sender,Integer amount, String remarks, String otpType){
		
		int otpIndex = otpType.toLowerCase() == "sms"?0:1;
		WebElement nextButton = driver.findElement(By.xpath(".//*[@type='submit']"));
		nextButton.click();
		String fromAcMessage = "";
		try {
			fromAcMessage = driver.findElement(By.id("fromAccountCurrency-error")).getText();
		} catch (Exception e) {
			// 
			e.printStackTrace();
		}
		if(fromAcMessage.contains("Account Currency is required.")){
			Select fromAccount = new Select(driver.findElement(By.id("fromAccountNumber")));
			fromAccount.selectByValue(sender);
			nextButton.click();
		}
		
		String amountMessage = driver.findElement(By.id("transferAmount-error")).getText();
		if(amountMessage.contains("required.") || amountMessage.contains("Requried.")){
			driver.findElement(By.id("transferAmount")).sendKeys(amount.toString());
			nextButton.click();
		}
		String remarksMessage = driver.findElement(By.id("remarks-error")).getText();
		if(remarksMessage.contains("Remarks is required.")){
			driver.findElement(By.id("remarks")).sendKeys("test");
			
		}
		
		driver.findElements(By.id("otpType")).get(otpIndex).click();
		nextButton.click();
		WebElement requestForTransferButton = driver.findElement(By.xpath(".//*[@type='submit']"));
		requestForTransferButton.click();
		String userMessage = driver.findElement(By.id("userMessage")).getText();
		if(!userMessage.contains("A One-Time Password (OTP) has been sent to your registered mobile/email. Please enter below.")){
			System.out.println("Fund transfer is not possible. Because of "+ userMessage);
			this.driver.get("http://172.16.229.241:8080");
			this.driver.get("http://172.16.229.241:8080/fundtransfer/mycityaccounts/transfer");
			
		}
		else {
			String passwordMessage = driver.findElement(By.id("transactionPassword-error")).getText();
			if(passwordMessage.contains("must not be empty.")){
				driver.findElement(By.id("transactionPassword")).sendKeys(otpPerse());
			}
			
			requestForTransferButton.click();
			driver.findElement(By.xpath(".//*[@class='btn whiteBtn aWhiteBtnIe7']")).click();
		}
		
	}
	
	public String otpPerse(){
		String otp = "1234";
		
		return otp;
	}
	
	public void transferFundWithoutOTP(String sender,Integer amount, String remarks, String otpType){
		
		int otpIndex = otpType.toLowerCase() == "sms"?0:1;
		Select fromAccount = new Select(driver.findElement(By.id("fromAccountNumber")));
		fromAccount.selectByValue(sender);
		driver.findElement(By.id("transferAmount")).sendKeys(amount.toString());
		driver.findElement(By.id("remarks")).sendKeys("test");
		driver.findElements(By.id("otpType")).get(otpIndex).click();
		driver.findElement(By.xpath(".//*[@type='submit']")).click();
		
		driver.findElement(By.id("transactionPassword")).sendKeys("");
		driver.findElement(By.xpath(".//*[@type='submit']")).click();
		String message = driver.findElement(By.id("transactionPassword-error")).getText();
		System.out.println(message);
		if(message.contains("must not be empty."))
			driver.findElement(By.xpath(".//*[@id='toMyCityBankAccountCheck']/div[7]/div/a[1]")).click();
	}
	
		

}
