package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Log;
//import jdk.internal.org.jline.utils.Log;

public class PageFactoryLoginPage {
	
	private WebDriver driver;
	
	@FindBy(id="user")
	WebElement username;
	
	@FindBy(id="pass")
	WebElement password;
	
	@FindBy(xpath="//button[contains(text(),'Login')]")
	WebElement loginbuton;
	
	//private By username=By.id("user");
	//private By password=By.id("pass");
	//private By loginbuton=By.xpath("//button[contains(text(),'Login')]");
	
	public PageFactoryLoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public void enterusername(String Username) {
		
		username.sendKeys(Username);
		//driver.findElement(username).sendKeys(Username);
		Log.info("Username passed");
	}

	public void enterpassword(String Password) {
		
		//driver.findElement(password).sendKeys(Password);
		password.sendKeys(Password);
		Log.info("Password entered");
	}
	public void Login() {
		
		//driver.findElement(loginbuton).click();
		loginbuton.click();
		Log.info("Login button clicked");
	}
}
