package Test;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Base.Basetest;
//import Pages.Loginpage;
import Pages.PageFactoryLoginPage;
import utils.ExtentReport;
import utils.Log;
//import jdk.internal.org.jline.utils.Log;

public class PageFactoryLogintest extends Basetest{
	
	@Test
	public void validLoginTest() {
		Log.info("Starting login test");
		test=ExtentReport.createTest("Login test");
		test.info("Started the test");
		PageFactoryLoginPage factoryloginpage=new PageFactoryLoginPage(driver);
		Log.info("Entering the credentials");
		test.info("Entering the credentials");
		factoryloginpage.enterusername("admin");
		factoryloginpage.enterpassword("admin123");
		factoryloginpage.Login();
		Log.info("login successful");
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		System.out.println(alert.getText());
		
		alert.accept();		
		System.out.println("The title of the page is "+driver.getTitle());
		test.info("Testing the page");
		Assert.assertEquals("Products", driver.getTitle());
		test.pass("Test is sucessful");
	}
	
	@Test
	public void InvalidLoginTest() {
		Log.info("Starting Invalid login test");
		test=ExtentReport.createTest("Invalid Login test");
		test.info("Started the test with invalid credentials");
		PageFactoryLoginPage factoryloginpage=new PageFactoryLoginPage(driver);
		Log.info("Entering the credentials");
		test.info("Entering the credentials");
		factoryloginpage.enterusername("admin123");
		factoryloginpage.enterpassword("admin123");
		factoryloginpage.Login();
		Log.info("login is failed");
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		System.out.println(alert.getText());
		
		alert.accept();		
		System.out.println("The title of the page is "+driver.getTitle());
		test.info("Testing the page");
		Assert.assertEquals("Products", driver.getTitle());
		test.pass("Test is sucessful");
	}


}
