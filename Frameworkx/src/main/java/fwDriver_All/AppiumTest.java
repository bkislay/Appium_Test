package fwDriver_All;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AppiumTest {

WebDriver driver;

@BeforeClass
public void setUp()	throws MalformedURLException{

	File appDir = new File("F:\\Automation_Repos\\");
	File app = new File(appDir, "com.linkedin.android.apk");
	
	DesiredCapabilities capabilities = new DesiredCapabilities();
	capabilities.setCapability(CapabilityType.BROWSER_NAME,"");
	capabilities.setCapability(CapabilityType.VERSION,"4.4");
	capabilities.setCapability(CapabilityType.PLATFORM,"WINDOWS");
	capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "KislayB");
	capabilities.setCapability(MobileCapabilityType.APP_PACKAGE, "com.android.calculator2");
	capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, "com.android.calculator2.Calculator");
	driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
}
	
@Test
public void addition(){
	
WebElement two = driver.findElement(By.name("4"));
two.click();
WebElement plus = driver.findElement(By.name("+"));
plus.click();
WebElement four = driver.findElement(By.name("4"));
four.click();
WebElement equals = driver.findElement(By.name("="));
equals.click();
WebElement results = driver.findElement(By.tagName("EditText"));
assert results.getText().equals("8"):"Actual value is : "+results.getText()+"did not match";
}
@AfterClass
public void closures(){
	driver.quit();
	}
}
