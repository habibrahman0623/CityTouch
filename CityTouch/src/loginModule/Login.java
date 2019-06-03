package loginModule;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Login {
	
	WebDriver driver;
	String MainWindow;
	public Login(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		this.driver.get("http://172.16.229.241:8080/login.html");
		
		
	}
	
	public void login(String UserName, String Password){
		
		
		MainWindow = driver.getWindowHandle();
		
		WebElement userName =  driver.findElement(By.id("userName"));
		userName.clear();
		userName.sendKeys(UserName);
		WebElement password = driver.findElement(By.id("password"));
		password.clear();
		password.sendKeys(Password);
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.className("loginButton")).click();
		
		try {
				Alert alert = driver.switchTo().alert();
				alert.accept();
				driver.switchTo().window(MainWindow);
		} catch (Exception e) {
				// TODO: handle exception
		}
							
		
	}


}
