package Test;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Base.Basetest;
import Pages.Loginpage;
import utils.Excelutil;
import utils.ExtentReport;
import utils.Log;
//import jdk.internal.org.jline.utils.Log;

public class LoginTest extends Basetest{
	
	@DataProvider(name="LoginData")
	public Object[][] getLoginData() throws IOException{
		
		String filepath=System.getProperty("user.dir")+"/Testdata Folder/TestData.xlsx";
		Excelutil.loadExcel(filepath, "sheet1");
		
		int rowCount=Excelutil.getRowCount();
		Object[][] data=new Object[rowCount-1][2];
		for(int i=1;i<rowCount;i++) {
			data[i-1][0]=Excelutil.getCellData(i, 0); //Username
			data[i-1][1]=Excelutil.getCellData(i, 1); //Password
		}
		Excelutil.closeExcel();
		return data;
	}
	
	public Object[][] getData(){
		return new Object[][]{
				{"admin","admin123"},
				{"admin","admin"}
		};
	}
	
	
	
	
	@Test(dataProvider="LoginData")
	public void validLoginTest(String username,String Password) {
		Log.info("Starting login test");
		test=ExtentReport.createTest("Login test");
		test.info("Started the test");
		Loginpage loginpage=new Loginpage(driver);
		Log.info("Entering the credentials");
		test.info("Entering the credentials");
		loginpage.enterusername(username);
		loginpage.enterpassword(Password);
		loginpage.Login();
		Log.info("login successful");
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		System.out.println(alert.getText());
		
		alert.accept();		
		System.out.println("The title of the page is "+driver.getTitle());
		test.info("Testing the page");
		Assert.assertEquals("Products", driver.getTitle());
		test.pass("Test is sucessful");
		System.out.println("Test is updating");
	}
	
	@Test
	@Parameters({"username","password"})
	public void InvalidLoginTest(String username,String password) {
		Log.info("Starting Invalid login test");
		test=ExtentReport.createTest("Invalid Login test");
		test.info("Started the test with invalid credentials");
		Loginpage loginpage=new Loginpage(driver);
		Log.info("Entering the credentials");
		test.info("Entering the credentials");
		loginpage.enterusername(username);
		loginpage.enterpassword(password);
		loginpage.Login();
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
