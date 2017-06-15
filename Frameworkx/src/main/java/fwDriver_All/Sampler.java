package fwDriver_All;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;

public class Sampler {
	
	public static void main(String[] args) {
		
		
		String chromepath = "/Users/kislaybarve/Downloads/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", "/Users/kislaybarve/Downloads/chromedriver");
		WebDriver driver = new ChromeDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.get("http://www.chartjs.org/samples/latest/charts/bar/vertical.html");
		String sgVal = (String) js.executeScript("alert(barChartData.datasets[0].data[0])");
	    Assert.assertEquals("new value for seleniumGlobal", sgVal);
	    
	}

}
