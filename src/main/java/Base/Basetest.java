package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import utils.EmailSender;
import utils.ExtentReport;
//import org.testng.annotations.BeforeTest;
import utils.Log;

//import jdk.internal.org.jline.utils.Log;

public class Basetest {
	
	protected WebDriver driver;
	protected static ExtentReports extent;
	protected static ExtentTest test;
	
	@BeforeSuite
	public void setupReport() {
		extent=ExtentReport.getReportInstance();
	}
	
	@AfterSuite
	public void teardownReport() {
		extent.flush();
		String reportPath=ExtentReport.reportPath;
		System.out.println("Report path is"+ reportPath);
		EmailSender.sendReport(reportPath);
	}
	
	@BeforeMethod
	public void setup() {
		Log.info("Starting webDriver");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		Log.info("Navigating to test url");
		driver.get("file:///C:/Users/syad0922/Desktop/Yadagiri_Workspace/Selinium_practice/login.html");
	}
	
	@AfterMethod
	public void teardown(ITestResult result) {
		
		if(result.getStatus()==ITestResult.FAILURE) {
			String screenshotPath=ExtentReport.captureScreenshot(driver, "LoginFailure");
			System.out.println("ScreenshotCaptured, PATH"+screenshotPath);
			test.fail("Test failed. Screenshot attached",MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}
		
		
		
		
		if(driver!=null) {
			Log.info("Closing the browser");
		    driver.quit();
			System.out.println("Wait");
		}
	}

}
