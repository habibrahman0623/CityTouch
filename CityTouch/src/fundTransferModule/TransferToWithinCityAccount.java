package fundTransferModule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class TransferToWithinCityAccount extends FundTransfer {

	public TransferToWithinCityAccount(WebDriver driver) {
		super(driver);
		
		
	}
	
	public void toWithinCityAccounts(String sender, String receiver,Integer amount, String remarks, String otpType){
		
		driver.findElement(By.xpath(".//*[@id='menu']/li[2]/a")).click();
		WebElement nextButton = driver.findElement(By.xpath(".//*[@type='submit']"));
		nextButton.click();
		String fromAcMessage = driver.findElement(By.id("fromAccountCurrency-error")).getText();					
		if(fromAcMessage.contains("Account Currency is required.")){
			Select fromAccount = new Select(driver.findElement(By.id("fromAccountNumber")));
			fromAccount.selectByValue(sender);
			nextButton.click();
		}
		String toAcMessage = driver.findElement(By.id("toAccountCurrency-error")).getText();
		if(toAcMessage.contains("To Account Currency is required.")){
			Select toAccount = new Select(driver.findElement(By.id("alias")));
			toAccount.selectByVisibleText(receiver);;
		}
		transferFund(sender, amount, remarks, otpType);
		
	}
	
	public void addBeneficiary(String nickName, String accountNumber, String mobileNumber, String emailId, String lastFourDigitOfCard ) throws InterruptedException{
		
		driver.findElement(By.xpath(".//*[@id='menu']/li[2]/a")).click();
		driver.findElement(By.xpath(".//*[@id='content']/div/div[1]/div/div[2]/div[1]/a[1]/img")).click();
		WebElement addButton = driver.findElement(By.xpath(".//*[@type='submit']"));
		addButton.click();
		String nickNameError = driver.findElement(By.id("alias-error")).getText();
		if(nickNameError.contains("Nickname is required.")){
			driver.findElement(By.id("alias")).sendKeys(nickName);
			
		}
		String acNumberError = driver.findElement(By.id("accountNumber-error")).getText();
		if(acNumberError.contains("Account Number is required & must be numeric.")){
			driver.findElement(By.id("accountNumber")).sendKeys(accountNumber);
			
		}
		
		driver.findElement(By.id("mobileNumber")).sendKeys(mobileNumber);
		driver.findElement(By.id("emailId")).sendKeys(emailId);
		Thread.sleep(3000);
		addButton.click();
		Thread.sleep(3000);
		
		WebElement confirm = driver.findElement(By.xpath(".//*[@id='beneficiaryCBAdd']/div[8]/div/a[1]"));
		//WebElement reset = driver.findElement(By.xpath(".//*[@id='beneficiaryCBAdd']/div[8]/div/a[2]"));
		//WebElement back = driver.findElement(By.xpath(".//*[@id='beneficiaryCBAdd']/div[8]/div/a[3]"));
		confirm.click();
		    		
            	
        driver.switchTo().activeElement();
        Thread.sleep(5000);
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
          	    
   	    Thread.sleep(5000);
   	    WebElement backToFundTransfer = driver.findElement(By.xpath(".//*[@class='btn whiteBtn']"));
	    backToFundTransfer.click();
            			
		
	}
	
	public void deleteBeneficiary(String nickName) throws InterruptedException{
		
		driver.findElement(By.xpath(".//*[@id='menu']/li[2]/a")).click();
		WebElement deleteBeneficiaryButton = driver.findElement(By.xpath(".//*[@id='content']/div/div[1]/div/div[2]/div[1]/a[2]/img"));
		deleteBeneficiaryButton.click();
		Select name = new Select(driver.findElement(By.id("alias")));
		name.selectByVisibleText(nickName);
		Thread.sleep(5000);
		WebElement delete = driver.findElement(By.xpath(".//*[@type='submit']"));
		delete.click();
		WebElement backToFundTransfer = driver.findElement(By.xpath(".//*[@id='deleteBeneficiaryCB']/div[3]/div/a"));
		backToFundTransfer.click();
	}
	
	public void addBeneficiaryByOwnAccount(String nickName, String accountNumber, String mobileNumber, String emailId, String lastFourDigitOfCard ) throws InterruptedException{
		
		driver.findElement(By.xpath(".//*[@id='menu']/li[2]/a")).click();
		driver.findElement(By.xpath(".//*[@id='content']/div/div[1]/div/div[2]/div[1]/a[1]/img")).click();
		driver.findElement(By.id("alias")).sendKeys(nickName);
		driver.findElement(By.id("accountNumber")).sendKeys(accountNumber);
		driver.findElement(By.id("mobileNumber")).sendKeys(mobileNumber);
		driver.findElement(By.id("emailId")).sendKeys(emailId);
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@type='submit']")).click();
		WebElement confirm = driver.findElement(By.xpath(".//*[@id='beneficiaryCBAdd']/div[8]/div/a[1]"));
		confirm.click();		    		         	
        driver.switchTo().activeElement();
        Thread.sleep(10000);      
        driver.findElement(By.xpath(".//*[@id='radioChange']")).click();
        driver.findElement(By.xpath(".//*[@id='cvvNumber']")).sendKeys(lastFourDigitOfCard);
   	    driver.findElement(By.xpath(".//*[@type='button']")).click();
   	    Thread.sleep(3000);
   	    String message = driver.findElement(By.id("userMessage")).getText();
   	    if(message.contains("You can not add you own account.")){
   	    	driver.findElement(By.xpath(".//*[@id='menu']/li[2]/a")).click();
   	    }
   	    
	  }
	
	public void withoutOTP(String sender, String receiver,Integer amount, String remarks, String otpType){
		
		driver.findElement(By.xpath(".//*[@id='menu']/li[2]/a")).click();
		Select toAccount = new Select(driver.findElement(By.id("alias")));
		toAccount.selectByVisibleText(receiver);
		transferFundWithoutOTP(sender, amount, remarks, otpType);
	}


}
