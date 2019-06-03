package fundTransferModule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class TransferToOutsideCityAccount extends FundTransfer {

	public TransferToOutsideCityAccount(WebDriver driver) {
		super(driver);
		
	}
	
	public void toOutSideCityAccount(String sender, String receiver,Integer amount, String remarks, String transferThrough, String otpType, String transferType, String paymentFrequency, Integer numberOfPayment) throws InterruptedException{
		
		driver.findElement(By.xpath(".//*[@id='menu']/li[3]/a")).click();
		Select toAccount = new Select(driver.findElement(By.id("alias")));
		toAccount.selectByVisibleText(receiver);
		if(transferThrough.toLowerCase().contains("eftn")){
			if(!transferType.toLowerCase().contains("now")){
				driver.findElement(By.id("standardType")).click();
				Thread.sleep(1000);
				Select frequency = new Select(driver.findElement(By.id("paymentFrequency")));
				frequency.selectByVisibleText(paymentFrequency);
				driver.findElement(By.id("numberOfPayments")).sendKeys(numberOfPayment.toString());
			}
		}
		else {
			driver.findElement(By.id("payThroughRTGS")).click();
		}
		transferFund(sender, amount, remarks, otpType);
	}
	
	public void addbeneficiary(String nickName, String accountHolderName, String accountNumber, String routingNumber, String mobileNumber, String emailId, String lastFourDigitOfCard) throws InterruptedException{
		
		driver.findElement(By.xpath(".//*[@id='menu']/li[3]/a")).click();
		WebElement addbeneficiaryButton = driver.findElement(By.xpath(".//*[@id='content']/div[1]/div/div/div[2]/div[1]/a[1]/img"));
		addbeneficiaryButton.click();
		WebElement addButton = driver.findElement(By.xpath(".//*[@type='submit']"));
		addButton.click();
		String aliasError = driver.findElement(By.id("alias-error")).getText();
		if(aliasError.contains("Nickname is required.")){
			driver.findElement(By.id("alias")).sendKeys(nickName);
			addButton.click();
		}
		String accountHolderNameError = driver.findElement(By.id("AccountHolderName-error")).getText();
		if(accountHolderNameError.contains("Account holder's name is required.")){
			driver.findElement(By.id("AccountHolderName")).sendKeys(accountHolderName);
			addButton.click();
		}
		String accountNumberError = driver.findElement(By.id("accountNumber-error")).getText();
		if(accountNumberError.contains("Account Number is required.")){
			driver.findElement(By.id("accountNumber")).sendKeys(accountNumber);
			addButton.click();
		}
		String routingNumberError = driver.findElement(By.id("bankRoutingNumber-error")).getText();
		if(routingNumberError.contains("Bank Routing Number is required.")){
			driver.findElement(By.id("routingNumber")).sendKeys(routingNumber);
		}
		
		driver.findElement(By.id("mobileNumber")).sendKeys(mobileNumber);
		driver.findElement(By.id("emailId")).sendKeys(emailId);
		Thread.sleep(3000);
		addButton.click();
		Thread.sleep(3000);
		WebElement confirm = driver.findElement(By.xpath(".//*[@id='addOtherBankBeneficiaryOB']/div[11]/div/a[1]"));
		//WebElement reset = driver.findElement(By.xpath(".//*[@id='addOtherBankBeneficiaryOB']/div[11]/div/a[2]"));
		//WebElement back = driver.findElement(By.xpath(".//*[@id='addOtherBankBeneficiaryOB']/div[11]/div/a[3]"));
		confirm.click();
		driver.switchTo().activeElement();
        Thread.sleep(3000);
        WebElement okButton = driver.findElement(By.xpath(".//*[@type='button']"));
        okButton.click();
        String selectCardError = driver.findElement(By.id("userCardNumber-error")).getText();
        if(selectCardError.contains("Please select a card.")){
        	driver.findElement(By.xpath(".//*[@id='radioChange']")).click();
        	okButton.click();
        }
        String cardNumberError = driver.findElement(By.id("cvvNumber-error")).getText();
        if(cardNumberError.contains("Last 4 digits of selected card is required.")){
        	driver.findElement(By.xpath(".//*[@id='cvvNumber']")).sendKeys(lastFourDigitOfCard);
        	okButton.click();
        }
   	    WebElement backToFundTransfer = driver.findElement(By.xpath(".//*[@class='btn whiteBtn']"));
   	    backToFundTransfer.click();
		
	}
	
	public void deleteBeneficiary(String nickName) throws InterruptedException{
		WebElement deleteBeneficiaryButton = driver.findElement(By.xpath(".//*[@id='content']/div/div[1]/div/div[2]/div[1]/a[2]/img"));
		deleteBeneficiaryButton.click();
		Select name = new Select(driver.findElement(By.id("alias")));
		name.selectByVisibleText(nickName);
		Thread.sleep(3000);
		WebElement delete = driver.findElement(By.xpath(".//*[@type='submit']"));
		delete.click();
		Thread.sleep(3000);
		WebElement backToFundTransfer = driver.findElement(By.xpath(".//*[@id='deleteBeneficiaryOB']/div[3]/div/a"));
		backToFundTransfer.click();
	}


}
