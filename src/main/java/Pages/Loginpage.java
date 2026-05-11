package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Log;
//import jdk.internal.org.jline.utils.Log;

public class Loginpage {
	
	private WebDriver driver;
	
	private By username=By.id("user");
	private By password=By.id("pass");
	private By loginbuton=By.xpath("//button[contains(text(),'Login')]");
	
	public Loginpage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void enterusername(String Username) {
		driver.findElement(username).sendKeys(Username);
		Log.info("Username passed");
	}

	public void enterpassword(String Password) {
		driver.findElement(password).sendKeys(Password);
		Log.info("Password entered");
	}
	public void Login() {
		driver.findElement(loginbuton).click();
		Log.info("Login button clicked");
		Log.info("Login button ");
		Log.info("add");
	}
}
