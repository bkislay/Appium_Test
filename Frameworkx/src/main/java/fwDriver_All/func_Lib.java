package fwDriver_All;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

///import com.relevantcodes.extentreports.LogStatus;


public class func_Lib extends fwDriver {

	public void closeAll(){
		//driver.close();
	}
	public Boolean callFunction(String func_name,String id_type,String id_value,String testdata){
		Boolean func_status;
		try{
			switch (func_name) {
			case "clicks":
				func_status = this.clicks(id_type,id_value);
				return func_status;
			case "enterText":
				func_status = this.enterText(id_type,id_value,testdata);
				return func_status;
			case "selectDate":
				func_status = this.selectDate(id_type,id_value);
				return func_status;
			case "checkText":
				func_status = this.checkText(id_type,id_value,testdata);
				return func_status;
			case "selectCheckBox" :
				func_status = this.selectCheckBox(id_type,id_value);
				return func_status;
			case "SelectRadio" :
				func_status = this.selectRadio(id_type,id_value);
				return func_status;
			case "selectDropDown" :
				func_status = this.selectDropDown(id_type,id_value,testdata);
				return func_status;
			default:
				System.out.println("No Function Found");
				return false;
			}
		}
		catch (Exception e){
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			//test.log(LogStatus.FATAL, errors.toString());
			return false;
		}

	}
	
	public func_Lib(String browser_name, String url){


		switch (browser_name) {


		case "Mozilla":
	/*		DesiredCapabilities dr=null;
			dr=DesiredCapabilities.firefox();
			dr.setBrowserName("firefox");
			dr.setPlatform(Platform.WINDOWS);
			try {
				this.driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dr);
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	*/		this.driver = new FirefoxDriver();
			this.driver.get(url);
			this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			break;
		case "IE":
			System.setProperty("webdriver.ie.driver","C:\\Users\\Kay Bee\\Desktop\\Selenium\\IEDriverServer.exe");
	/*		DesiredCapabilities dr1=null;
			dr1=DesiredCapabilities.internetExplorer();
			dr1.setBrowserName("IE");
			dr1.setPlatform(Platform.WINDOWS);
			try {
				this.driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dr1);
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	*/		this.driver = new InternetExplorerDriver(); //Create a IE driver object
			this.driver.get(url); //get method to open the url under test
			this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			try {
				Thread.sleep(40000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			System.out.println("No Browser type found");
			break;
		}
	}

	public Boolean mouseClick(String id_type, String id_value)
	{
		WebElement test_obj = null;
		test_obj = this.getObject(id_type, id_value);
		if (test_obj.equals(null))	{ return false; }
		else 
		{
			Actions builder = new Actions(driver);
			Action mouseclick = builder.click(test_obj).build();
			mouseclick.perform();
			return true; }
	}

	public Boolean clicks(String id_type, String id_value)
	{
		WebElement test_obj = null;
		test_obj = this.getObject(id_type, id_value);

		if (test_obj.equals(null))	{ return false; }
		else 
		{
			test_obj.click();
			return true;

		}
	};

	public Boolean checkText(String id_type, String id_value, String test_data)
	{
		WebElement test_obj = null;
		test_obj = this.getObject(id_type, id_value);

		if (test_obj.equals(null))	{ return false; }
		else 
		{
			String actual_text= test_obj.getText();
			if (test_data.contains(actual_text)){return true;}
			else {return false;}

		}
	};

	public File screencapture(){
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		return scrFile;
	}

	public Boolean enterText(String id_type, String id_value, String test_data)
	{

		WebElement test_obj = null;
		test_obj = this.getObject(id_type, id_value);

		if (test_obj.equals(null))	{ return false; }
		else {
			test_obj.sendKeys(test_data); 
			return true;
		}
	};

	public Boolean selectDate(String id_type, String id_value){
		DateFormat dateFormat2 = new SimpleDateFormat("dd"); 
		Date date2 = new Date();

		String today = dateFormat2.format(date2); 

		WebElement dateWidget = this.getObject(id_type, id_value);

		List<WebElement> columns=dateWidget.findElements(By.tagName("td"));  

		//comparing the text of cell with today's date and clicking it.
		for (WebElement cell : columns)
		{
			if (cell.getText().equals(today))
			{
				cell.click();
				return true;
			}
			else {return false;}
		}
		return false;
	}

	
	public Boolean selectDropDown(String id_type, String id_value,String testdata){

		WebElement test_obj = null;
		test_obj = this.getObject(id_type, id_value);

		if (test_obj.equals(null))	{ return false; }
		else 
		{
			Select s = new Select(test_obj); 
			test_obj.sendKeys(testdata);
			List<WebElement> allOptions = s.getOptions();
			System.out.println("Total options in list -> "+ allOptions.size());
			return true;

		}


	};

	public Boolean selectRadio(String id_type,String id_value)
	{

		WebElement test_obj = null;
		test_obj = this.getObject(id_type, id_value);

		if (test_obj.equals(null))	{ return false; }
		else 
		{
			test_obj.click();
			return true;

		}

	};

	public Boolean selectCheckBox(String id_type,String id_value)
	{
		WebElement test_obj = null;
		test_obj = this.getObject(id_type, id_value);

		if (test_obj.equals(null))	{ return false; }
		else 
		{
			if ( !test_obj.isSelected() )
			{
				test_obj.click();
			}

			return true;

		}

	};

	public WebElement getObject(String id_type,String id_value){

		WebElement test_obj = null;
		try {
			switch (id_type) {
			case "id":
				test_obj = driver.findElement(By.id(id_value));
				return test_obj;
			case "name":
				test_obj = driver.findElement(By.name(id_value));
				return test_obj;
			case "cssSelector":
				test_obj = driver.findElement(By.cssSelector(id_value));
				return test_obj;
			case "xpath":
				test_obj = driver.findElement(By.xpath(id_value));
				return test_obj;
			case "linkText":
				test_obj = driver.findElement(By.linkText(id_value));
				return test_obj;
			case "partialLinkText":
				test_obj = driver.findElement(By.partialLinkText(id_value));
				return test_obj;
			case "tagName":
				test_obj = driver.findElement(By.tagName(id_value));
				return test_obj;
			default: return null;
			}}
		catch (Exception e){
			e.printStackTrace();
		}
	
		 return null;
	}
}

