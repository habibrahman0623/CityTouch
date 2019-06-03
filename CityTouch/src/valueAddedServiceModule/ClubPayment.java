package valueAddedServiceModule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ClubPayment extends Bill{

	public ClubPayment(WebDriver driver) {
		super(driver);
		WebElement clubPayment = driver.findElement(By.xpath(".//*[@id='menu']/li[3]/a"));
		clubPayment.click();
	}
	
	public void clubPay(String nickName, String fromAccount, String amount, String otpType){
		try {
			billPay(nickName, "", fromAccount, amount, otpType);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addBeneficiary(String clubName, String memberId, String nickName, String lastFourDigitOfCard) throws InterruptedException{
		
		//try {
			WebElement addbbeneficiaryButton = driver.findElement(By.xpath(".//*[@id='content']/div[1]/div/div/div[2]/div/div[1]/a[1]/img"));
			addbbeneficiaryButton.click();
			Select club = new Select(driver.findElement(By.id("club")));
			club.selectByVisibleText(clubName);
			Thread.sleep(5000);
			WebElement id = driver.findElement(By.id("billFieldsMemberId"));
			id.sendKeys(memberId);
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
		/*} catch (Exception e) {
			// TODO: handle exception
		}*/
	}
	
	public void deleteBeneficiary(String nickName){
		
		
		try {
			WebElement deleteBeneficiaryButton = driver.findElement(By.xpath(".//*[@id='content']/div[1]/div/div/div[2]/div/div[1]/a[2]/img"));
			deleteBeneficiaryButton.click();
			Select name = new Select(driver.findElement(By.id("club")));
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
