package fundTransferModule;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class TransferToMyCityAccount extends FundTransfer {

	public TransferToMyCityAccount(WebDriver driver) {
		super(driver);
		
	}
	
	public void toMyCityAccounts(String sender, String receiver,Integer amount, String remarks, String otpType){
		Select toAccount = new Select(driver.findElement(By.id("toAccountNumber")));
		toAccount.selectByValue(receiver);
		transferFund(sender, amount, remarks, otpType);
	}
	
	public void sameAccount(String sender, String receiver,Integer amount, String remarks, String otpType) throws InterruptedException{
		
		String MainWindow = driver.getWindowHandle();
		Select fromAccount = new Select(driver.findElement(By.id("fromAccountNumber")));
		fromAccount.selectByValue(sender);
		Select toAccount = new Select(driver.findElement(By.id("toAccountNumber")));
		toAccount.selectByValue(receiver);
		Thread.sleep(3000);
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			driver.switchTo().window(MainWindow);
		} catch (Exception e) {
			
		}
	}
	
	public void withoutOTP(String sender, String receiver,Integer amount, String remarks, String otpType){
		
		Select toAccount = new Select(driver.findElement(By.id("toAccountNumber")));
		toAccount.selectByValue(receiver);
		transferFundWithoutOTP(sender, amount, remarks, otpType);
	}
	
	public void fundTransferWithoutGivingInfo(String sender, String receiver,Integer amount, String remarks, String otpType){
		
		WebElement nextButton = driver.findElement(By.xpath(".//*[@type='submit']"));
		nextButton.click();
		String toAcMessage = driver.findElement(By.id("toAccountCurrency-error")).getText();
		if(toAcMessage.contains("To Account Currency is required.")){
			Select toAccount = new Select(driver.findElement(By.id("toAccountNumber")));
			toAccount.selectByValue(receiver);
		}
		
		transferFund(sender, amount, remarks, otpType);

	}

}
