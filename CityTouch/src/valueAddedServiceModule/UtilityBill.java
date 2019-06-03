package valueAddedServiceModule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class UtilityBill {
	
	WebDriver driver;
	public UtilityBill(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		driver.get("http://172.16.229.241:8080/billpay/registration/3");
		WebElement utilityBill = driver.findElement(By.xpath(".//*[@id='menu']/li[1]/a"));
		utilityBill.click();
	}
	
	public void deleteBeneficiary(String nickName) throws InterruptedException{
		WebElement deleteBeneficiaryButton = driver.findElement(By.xpath(".//*[@id='content']/div[1]/div/div/div[2]/div/div[1]/a[2]/img"));
		deleteBeneficiaryButton.click();
		Select name = new Select(driver.findElement(By.id("company")));
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
	
	public void addBeneficiary(String companyName, String meterNumber, String mobileNumber, String nickName, String lastFourDigitOfCard) throws InterruptedException{
		
		WebElement addbeneficiaryButton = driver.findElement(By.xpath(".//*[@id='content']/div[1]/div/div/div[2]/div/div[1]/a[1]/img"));
		addbeneficiaryButton.click();
		Select cName = new  Select(driver.findElement(By.id("company")));
		cName.selectByVisibleText(companyName);
		WebElement meterNo = driver.findElement(By.id("billFieldsBillAccountNumber"));
		meterNo.sendKeys(meterNumber);
		WebElement mobile = driver.findElement(By.id("billFieldsMobileNumber"));
		mobile.sendKeys(mobileNumber);
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
