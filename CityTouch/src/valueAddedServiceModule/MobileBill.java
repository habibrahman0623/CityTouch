package valueAddedServiceModule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class MobileBill extends Bill {

	public MobileBill(WebDriver driver) {
		super(driver);
		WebElement mobileBill = driver.findElement(By.xpath(".//*[@id='menu']/li[2]/a"));
		mobileBill.click();
	}
	
	public void payMobileBill(String nickName, String fromAccount, String amount, String otpType) throws InterruptedException{
		
		billPay(nickName, "", fromAccount, amount, otpType);
		
	}
	
	public void addBeneficiary(String operatorType, String connectionType, String mobileNumber, String nickName, String lastFourDigitOfCard){
		try {
			WebElement addbbeneficiaryButton = driver.findElement(By.xpath(".//*[@id='content']/div[1]/div/div/div[2]/div/div[1]/a[1]/img"));
			addbbeneficiaryButton.click();
			Select operator = new Select(driver.findElement(By.id("operator")));
			operator.selectByVisibleText(operatorType);
			Thread.sleep(5000);
			Select type = new Select(driver.findElement(By.xpath(".//*[@id='dynamicContent']/div[1]/div/div/div/select")));
			type.selectByVisibleText(connectionType);
			WebElement mobile = driver.findElement(By.id("billFieldsPhoneNumber"));
			mobile.sendKeys(mobileNumber);
			WebElement name = driver.findElement(By.xpath(".//*[@name='alias']"));
			name.sendKeys(nickName);
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
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception occured");
		}
	}
	
	public void deleteBeneficiary(String nickName){
		
		
		try {
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
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
