package com.scripts.iHeart;

import java.awt.AWTException; 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.framework.BaseReport;
import com.framework.BaseTest;
import com.framework.Excel_Reader;
import com.framework.Generic;
import com.framework.PDFValidation;
import com.netsuite.common.NS_Billing_AdjustmentAndSpecialBilling_iHeart;
import com.netsuite.common.NS_Login_iHeart;


public class TS_RemoveAgencyCommission_iHeart extends BaseReport {

	private BaseTest basetest;
	public static Excel_Reader excelReader;
	public static int i=2222;
	int HistoryRowNumber=0;
	int passCount=0, FailCount=0;
	public static String TestDataPath="";
	public static HashMap<String,String> XLTestData;
	public static WebDriver driver;

	//========================>
	NS_Login_iHeart oLoginPage=new NS_Login_iHeart();
	Generic oGenericUtils=new Generic();
	NS_Billing_AdjustmentAndSpecialBilling_iHeart oSalesOrderNetsuite = new NS_Billing_AdjustmentAndSpecialBilling_iHeart();
	Generic gen =new Generic();
	String filePathToDownload =null;
	//========================>
	@BeforeTest(alwaysRun=true)
	public void getTest() throws IOException {
		basetest=new BaseTest();
		basetest.getTest(this.getClass().getSimpleName(),"Remove Agency Commission");
		/*Runtime rt = Runtime.getRuntime();
		Process proc = rt.exec("taskkill /im chromedriver.exe /f /t");*/

	}

	//==============>
	@BeforeClass
	public void test() throws FileNotFoundException, IOException {
		TestDataPath = System.getProperty("user.dir") + "\\Data\\iHeart_NetSuiteTestData_RemoveAgencyCommission.xlsx";
		System.out.println("Test Data Path: "+TestDataPath);
		excelReader=new Excel_Reader(TestDataPath);
		excelReader.cFileNameWithPath = TestDataPath;
		excelReader.cSheetName = "TestData";
		excelReader.cTcID = "TestCaseID";
		excelReader.cTcValue = "1";
		XLTestData = new HashMap<String, String>();
		XLTestData = excelReader.readExcel("TC_NST_" + Integer.toString(i));
		
		
		
		//Folder creation
		File folder = new File(System.getProperty("user.dir") + "\\RemoveAgencyCommissionPDFFile");
		if(!folder.exists()){
			folder.mkdir();
		}
		for(File file :folder.listFiles()){
			String fileNameDeleted = "";
			fileNameDeleted = file.getName();
			if(file.delete()){
				System.out.println("File : "+fileNameDeleted+" deleted sucessfully");
			}
		}
		filePathToDownload = folder.getAbsolutePath();
	}

	//=============>
	@Test
	public void removeAgencyCommission() throws InterruptedException, IOException, AWTException {

		/*PDFValidation PDFValidation = new PDFValidation();
        String[] pdfData = PDFValidation.getPDFData();  
        
        System.out.println(pdfData[10]);*/
        
		System.out.println(XLTestData.get("NetSuite_URL").toString());	
		System.out.println(XLTestData.get("NetEmail").toString());
		System.out.println(XLTestData.get("NetPassword").toString());
		basetest.test = basetest.extent.createTest("CA_"+XLTestData.get("Scenario").toString(),"CA_"+XLTestData.get("Scenario").toString());

		//Launch URL
		//driver=oLoginPage.LaunchNetSuiteApp(XLTestData.get("NetSuite_URL").toString(),XLTestData,basetest);
		driver = oLoginPage.LaunchNetSuiteApp(XLTestData.get("NetSuite_URL").toString(), XLTestData, filePathToDownload, basetest);
		//Login Application
		oLoginPage.NetSuiteLogin(driver, XLTestData,basetest);

		//choosing role
		oSalesOrderNetsuite.SelectRoleFOrNetSuiteAsAdmin(driver, XLTestData, basetest);

		//selecting new sales order through list
		oSalesOrderNetsuite.selectAdjSplBillingInBilling(driver, XLTestData, basetest);

		//adjustments
		String amountValidationDetails =oSalesOrderNetsuite.clickAdjustments(driver, XLTestData, basetest);
        
		String grossAmountInUI, agencyCommissionInUI, netDueInUI; 
        String details[] =amountValidationDetails.split(";");
        
        grossAmountInUI = details[0].toString();
        agencyCommissionInUI = details[1].toString();
        netDueInUI = details[2].toString();
        
        
		oSalesOrderNetsuite.clickPDFDownloadLink(driver,basetest);
        
		
		PDFValidation PDFValidation = new PDFValidation();
        String[] pdfData = PDFValidation.getPDFData(filePathToDownload);  
        System.out.println(pdfData[9]);
        if(pdfData[9].contains(grossAmountInUI)){
        	basetest.test.log(Status.PASS, "Gross Amount("+grossAmountInUI+") in UI and in PDF document are equal");	
        }else{
        	basetest.test.log(Status.FAIL, "Gross Amount("+grossAmountInUI+") in UI and in PDF document are equal");
        }
        if(pdfData[9].contains(agencyCommissionInUI)){
        	basetest.test.log(Status.PASS, "Agency Commission("+agencyCommissionInUI+") in UI and in PDF document are equal");
        }else{
        	basetest.test.log(Status.FAIL, "Agency Commission("+agencyCommissionInUI+") in UI and in PDF document are equal");
        }
        if(pdfData[9].contains(netDueInUI)){
        	basetest.test.log(Status.PASS, "Net Due("+netDueInUI+") in UI and in PDF document are equal");
        }else{
        	basetest.test.log(Status.FAIL, "Net Due("+netDueInUI+") in UI and in PDF document are equal");
        }

		
		//logout from Netsuite
		oLoginPage.NetSuiteLogout(driver, basetest);
	}

	//=========================>
	@AfterMethod(alwaysRun = true)
	public void ExtentReport() {
		basetest.extent.flush();
		if(driver != null)
		{driver.close();
		// driver.quit();
		}
	}

	//=========================>	  
	@AfterClass(alwaysRun = true)
	public void LogsOut() throws InterruptedException, IOException {
		String ClassName = this.getClass().getSimpleName();
		LogScenario(ClassName, passCount, FailCount);
		if(driver != null) {
			{
				if(gen.isAlertPresents(driver))
				{
					Alert alert = driver.switchTo().alert();
					alert.accept();
				}
				driver.close();
				   driver.quit();
			}

		}
	}
}
