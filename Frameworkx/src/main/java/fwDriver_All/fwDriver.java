package fwDriver_All;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

//import java.io.PrintWriter;
//import java.io.StringWriter;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class fwDriver {
	WebDriver driver = null;
	Properties prop = null;
	String filepath,browser_name,homeurl, filepath_new;
	
@BeforeClass
public void init_set(){
	prop = new Properties();
	try {
		FileInputStream fis = new FileInputStream("C:\\Users\\Kay Bee\\Desktop\\Selenium\\Frameworkx\\src\\main\\java\\config.properties");
		prop.load(fis);

	} 

	catch (IOException e) {
		e.printStackTrace();
	}

	filepath = prop.getProperty("filepath");
	filepath_new = prop.getProperty("filepath_new");
	browser_name = prop.getProperty("browser_name");
	homeurl = prop.getProperty("homeurl");

}


@Test
public void start_fwDriver() {	

		String test_case_name,test_step_desc;

		
		func_Lib fl_object = new func_Lib("IE",homeurl);

		Excel_works excel_obj = new Excel_works (filepath);
		ExtentReports e_report;
		e_report = new ExtentReports(filepath_new+"testreport2.html", true);
		e_report.config()
		.documentTitle("Hotel Automation Report")
		.reportName("Functional")
		.reportHeadline("Sample Automation Report for Hotel WebApp V1");

		e_report
		.addSystemInfo("Selenium Version", "2.46")
		.addSystemInfo("Environment", "QA");

		int rowcount=0,nwrowcount = 0;
		String exec_Flag;exec_Flag="Y";
		Boolean func_status = false;
		String nwsheetname,id_type,id_value,testdata;

		rowcount = excel_obj.getRowCount("Test_Cases");
		System.out.println("Row count outloop"+rowcount);

		ExtentTest test = null;

		for (int i=2;i<=rowcount;i++)
		{
			if(excel_obj.getCellData("Test_Cases", 2, i).equals(exec_Flag))
			{

				nwsheetname = excel_obj.getCellData("Test_Cases", "TCID", i);
				test_case_name = excel_obj.getCellData("Test_Cases", "TC_Name", i);
				nwrowcount = excel_obj.getRowCount(nwsheetname);
				test = e_report.startTest(nwsheetname,test_case_name);

				int test_count=0;
				System.out.println("Row count of I "+i);		

				for(int j=2;j<=nwrowcount;j++)

				{


					String func_name=excel_obj.getCellData(nwsheetname, "func_name", j);
					System.out.println(func_name);		

					id_type=excel_obj.getCellData(nwsheetname, "id_type", j);
					id_value=excel_obj.getCellData(nwsheetname, "id_value", j);
					testdata=excel_obj.getCellData(nwsheetname, "test_data", j);
					test_step_desc=excel_obj.getCellData(nwsheetname, "test_step_desc", j);
					func_status = fl_object.callFunction(func_name,id_type,id_value,testdata);
					
					if (func_status==true){
						try {
							File scrfile = fl_object.screencapture();
							FileUtils.copyFile(scrfile, new File("C:\\Users\\Kay Bee\\Desktop\\Selenium\\Frameworkx\\src\\main\\java\\"+nwsheetname+"_"+test_step_desc+"_.png"));

							System.out.println("Row count of J "+j+test_step_desc);		
						} catch (IOException e) {

							e.printStackTrace();
						}

					}
					else {
						try {
							File scrfile = fl_object.screencapture();
							FileUtils.copyFile(scrfile, new File("C:\\Users\\Kay Bee\\Desktop\\Selenium\\Frameworkx\\src\\main\\java\\"+nwsheetname+"_"+test_case_name+"_.png"));
							test_count=test_count + 1;
							System.out.println("Row count of J "+j+test_step_desc);
						}		    	 
						catch (Exception e)
						{

							e.printStackTrace();

						}


					} 

				}
				if(test_count==0){
					test.log(LogStatus.PASS,test_case_name,"Details");
				}
				else{
					test.log(LogStatus.FAIL,test_case_name+ "No. of Steps that failed :- "+test_count,test.addScreenCapture("C:\\Users\\Kay Bee\\Desktop\\Selenium\\Frameworkx\\src\\main\\java\\"+nwsheetname+"_"+test_case_name+"_.png"));
				}
				e_report.endTest(test);
				e_report.flush();

			};
		}

		fl_object.closeAll();
	}

@Test
public void start_fwDriverNew() {	

		String test_case_name,test_step_desc;

		
		func_Lib fl_object = new func_Lib("IE",homeurl);

		Excel_works excel_obj = new Excel_works (filepath);
		ExtentReports e_report;
		e_report = new ExtentReports(filepath_new+"testreport1.html", true);
		e_report.config()
		.documentTitle("Hotel Automation Report")
		.reportName("Functional")
		.reportHeadline("Sample Automation Report for Hotel WebApp V1");

		e_report
		.addSystemInfo("Selenium Version", "2.46")
		.addSystemInfo("Environment", "QA");

		int rowcount=0,nwrowcount = 0;
		String exec_Flag;exec_Flag="N";
		Boolean func_status = false;
		String nwsheetname,id_type,id_value,testdata;

		rowcount = excel_obj.getRowCount("Test_Cases");
		System.out.println("Row count outloop"+rowcount);

		ExtentTest test = null;

		for (int i=2;i<=rowcount;i++)
		{
			if(excel_obj.getCellData("Test_Cases", 2, i).equals(exec_Flag))
			{

				nwsheetname = excel_obj.getCellData("Test_Cases", "TCID", i);
				test_case_name = excel_obj.getCellData("Test_Cases", "TC_Name", i);
				nwrowcount = excel_obj.getRowCount(nwsheetname);
				test = e_report.startTest(nwsheetname,test_case_name);

				int test_count=0;
				System.out.println("Row count of I "+i);		

				for(int j=2;j<=nwrowcount;j++)

				{


					String func_name=excel_obj.getCellData(nwsheetname, "func_name", j);
					System.out.println(func_name);		

					id_type=excel_obj.getCellData(nwsheetname, "id_type", j);
					id_value=excel_obj.getCellData(nwsheetname, "id_value", j);
					testdata=excel_obj.getCellData(nwsheetname, "test_data", j);
					test_step_desc=excel_obj.getCellData(nwsheetname, "test_step_desc", j);
					func_status = fl_object.callFunction(func_name,id_type,id_value,testdata);
					
					if (func_status==true){
						try {
							File scrfile = fl_object.screencapture();
							FileUtils.copyFile(scrfile, new File("C:\\Users\\Kay Bee\\Desktop\\Selenium\\Frameworkx\\src\\main\\java\\"+nwsheetname+"_"+test_step_desc+"_.png"));

							System.out.println("Row count of J "+j+test_step_desc);		
						} catch (IOException e) {

							e.printStackTrace();
						}

					}
					else {
						try {
							File scrfile = fl_object.screencapture();
							FileUtils.copyFile(scrfile, new File("C:\\Users\\Kay Bee\\Desktop\\Selenium\\Frameworkx\\src\\main\\java\\"+nwsheetname+"_"+test_case_name+"_.png"));
							test_count=test_count + 1;
							System.out.println("Row count of J "+j+test_step_desc);
						}		    	 
						catch (Exception e)
						{

							e.printStackTrace();

						}


					} 

				}
				if(test_count==0){
					test.log(LogStatus.PASS,test_case_name,"Details");
				}
				else{
					test.log(LogStatus.FAIL,test_case_name+ "No. of Steps that failed :- "+test_count,test.addScreenCapture("C:\\Users\\Kay Bee\\Desktop\\Selenium\\Frameworkx\\src\\main\\java\\"+nwsheetname+"_"+test_case_name+"_.png"));
				}
				e_report.endTest(test);
				e_report.flush();

			};
		}

		fl_object.closeAll();
	}

@AfterClass
public void closeAll(){
	System.out.println("Closing All");
	
	}

}