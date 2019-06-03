package fundTransferModule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class VisaInstantPayment extends FundTransfer {

	public VisaInstantPayment(WebDriver driver) {
		super(driver);
		
	}
	
	public void visaInstantPayment(String sender, String receiver,Integer amount, String remarks, String otpType){
		
		driver.findElement(By.xpath(".//*[@id='menu']/li[4]/a")).click();
		Select toAccount = new Select(driver.findElement(By.id("alias")));
		toAccount.selectByVisibleText(receiver);
		transferFund(sender, amount, remarks, otpType);
	}
	
	public void addBeneficiary(String nickName, String recepientVisaCardNumber,String recepientName,String mobileNumber, String emailId, String lastFourDigitOfCard) throws InterruptedException{
		
		driver.findElement(By.xpath(".//*[@id='menu']/li[4]/a")).click();
		WebElement addbeneficiaryButton = driver.findElement(By.xpath(".//*[@id='content']/div/div[1]/div/div[2]/div[1]/div[1]/a[1]/img"));
		addbeneficiaryButton.click();
		WebElement addButton = driver.findElement(By.xpath(".//*[@type='submit']"));
		addButton.click();
		String aliasError = driver.findElement(By.id("alias-error")).getText();
		if(aliasError.contains("Nickname is required.")){
			driver.findElement(By.id("alias")).sendKeys(nickName);
			addButton.click();
		}
		String visaCardNumberError = driver.findElement(By.id("visaCardNumber-error")).getText();
		if(visaCardNumberError.contains("Visa Card Number is required.")){
			driver.findElement(By.id("visaCardNumber")).sendKeys(recepientVisaCardNumber);
			addButton.click();
		}
		String cardHolderNameError = driver.findElement(By.id("cardHolderName-error")).getText();
		if(cardHolderNameError.contains("Visa Card Holder Name is required.")){
			driver.findElement(By.id("cardHolderName")).sendKeys(recepientName);
			
		}
		
		driver.findElement(By.id("mobileNumber")).sendKeys(mobileNumber);
		driver.findElement(By.id("emailId")).sendKeys(emailId);
		addButton.click();
		Thread.sleep(4000);
		WebElement confirm = driver.findElement(By.xpath(".//*[@id='beneficiaryCBAdd']/div[7]/div/a[1]"));
		//WebElement reset = driver.findElement(By.xpath(".//*[@id='beneficiaryCBAdd']/div[7]/div/a[2]"));
		//WebElement back = driver.findElement(By.xpath(".//*[@id='beneficiaryCBAdd']/div[7]/div/a[3]"));
		confirm.click();
		Thread.sleep(4000);
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
		
		driver.findElement(By.xpath(".//*[@id='menu']/li[4]/a")).click();
		WebElement deleteBeneficiaryButton = driver.findElement(By.xpath(".//*[@id='content']/div/div[1]/div/div[2]/div[1]/div[1]/a[2]/img"));
		deleteBeneficiaryButton.click();
		Select name = new Select(driver.findElement(By.id("alias")));
		name.selectByVisibleText(nickName);
		Thread.sleep(5000);
		WebElement delete = driver.findElement(By.xpath(".//*[@type='submit']"));
		
		delete.click();
		WebElement backToFundTransfer = driver.findElement(By.xpath(".//*[@id='deleteBeneficiaryCB']/div[3]/div/a"));
		backToFundTransfer.click();
	}
	
	

}
