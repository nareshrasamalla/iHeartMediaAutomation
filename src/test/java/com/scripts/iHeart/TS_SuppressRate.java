package com.scripts.iHeart;

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

import com.framework.BaseReport;
import com.framework.BaseTest;
import com.framework.Excel_Reader;
import com.framework.Generic;
import com.netsuite.common.NS_Billing_AdjustmentAndSpecialBilling_iHeart;
import com.netsuite.common.NS_Login_iHeart;

public class TS_SplitInvoiceSplitStation extends BaseReport{
	private BaseTest basetest;
	public static Excel_Reader excelReader;
	public static int i=6700;
	int HistoryRowNumber=0;
	int passCount=0, FailCount=0;
	public static String TestDataPath="";
	public static HashMap<String,String> XLTestData;
	public static WebDriver driver;
	
	NS_Login_iHeart oLoginPage=new NS_Login_iHeart();
	Generic oGenericUtils=new Generic();
	NS_Billing_AdjustmentAndSpecialBilling_iHeart oSalesOrderNetsuite = new NS_Billing_AdjustmentAndSpecialBilling_iHeart();
	Generic gen =new Generic();
	@BeforeTest(alwaysRun=true)
	public void getTest() throws IOException {
		basetest=new BaseTest();
		basetest.getTest(this.getClass().getSimpleName(),"Split Invoice-Split Station");
	}
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
	}
	
	@Test()
	public void splitInvoiceSplitStation() throws InterruptedException{
		basetest.test = basetest.extent.createTest("Split Invoice - Split Station","Split Invoice - Split Station");
		driver = oLoginPage.LaunchNetSuiteApp(XLTestData.get("NetSuite_URL").toString(), XLTestData, "", basetest);
		//Login Application
		oLoginPage.NetSuiteLogin(driver, XLTestData,basetest);
		//choosing role
		oSalesOrderNetsuite.SelectRoleFOrNetSuiteAsAdmin(driver, XLTestData, basetest);
		
		//selecting new sales order through list
		 oSalesOrderNetsuite.selectAdjSplBillingInBilling(driver, XLTestData, basetest);		
		 oSalesOrderNetsuite.splitInvoiceSplitStation(driver, XLTestData, basetest);
	}
	
	@AfterMethod(alwaysRun = true)
	public void ExtentReport() {
		basetest.extent.flush();
		if(driver != null)
		{driver.close();
		// driver.quit();
		}
	}
  
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
