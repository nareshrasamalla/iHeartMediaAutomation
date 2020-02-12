package com.netsuite.common;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.framework.BaseTest;
import com.framework.Generic;
import com.framework.ScreenShot;

import junit.framework.Assert;

public class NS_Billing_AdjustmentAndSpecialBilling_iHeart  {




	//Locators for SalesOrder
	String adminElement 			=	"spn_cRR_d1";
	String WSISB2Servicerepo 		= 	"//span[contains(text(),'WSI SB2 - DEV  -  WSI- Support Rep')]";
	String WSISB4Servicerepo		=	"//span[contains(text(),'WSI SB4 - QA  -  WSI- Support Rep')]";
	String WSISB10Servicerepo		=	"//span[contains(text(),'WSI SB10  -  WSI- Support Rep')]";
	String elementSB2 				= 	"//div[@id='ns-dashboard-heading-panel']/h1";
	String custmer 					= 	"//li[@data-title='Customers']";
	String transcation 				= 	"//li[@data-title='Transactions']";
	String sorder 					= 	"//li[@data-title='Sales Orders']";
	String newbutton 				= 	"//li[@data-title='Sales Orders']//li[@data-title='New']";
	String SalesOrderpage 			= 	"//h1[@class='uir-record-type']";
	String customername 			= 	"//input[@id='entity_display']";
	String branddropdown 			= 	"//img[@id='inpt_class3_arrow']";
	String brandslection 			= 	"//div[@id='nl4']";
	String itemadd 					= 	"//input[@id='item_item_display']";
	String amount	 				= 	"//tr[contains(@id,'item_row')]//td[9]";
	String amountinput				=	"(//tr[contains(@id,'item_row')]//td[9]//input)[1]";
	String addbtn 					= 	"//input[@id='item_addedit']";
	String savebtn 					= 	"//input[@id='btn_secondarymultibutton_submitter']";
	String brand 					=	"//input[@id='inpt_class3']";
	String confirmationpage 		= 	"//div[@class='title']";
	String approving 				= 	"//input[@id='approve']";
	String home 					= 	"(//li[contains(@id,'ns-header-menu-home-item')])[1]";


	//Searching salesorder And Admin Locators
	String SB2Admin 				= 	"//span[contains(text(),'SB1 - iHeartMedia  -  Administrator')]";
	String WSISB4Admin				=	"//span[contains(text(),'WSI SB4 - QA  -  Administrator')]";
	String WSISB10Admin				=	"//span[contains(text(),'WSI SB10  -  Administrator')]";
	String Searchingclick 			= 	"//a[contains(text(),'Sales Order:')] ";
	String sales					=	"(//li[@data-title='Sales'])[1]";
	String enterSalesOrder 			= 	"//li[@data-title='Enter Sales Orders']";
	String removeAgencyCommision   =   "//div[text()='Remove Agency Commission']";

	// Billing and Remove Agency Commission flow
	String billing						=	"(//li[@data-title='Billing'])[1]";
	String adjustmentSpecialBilling 	= 	"//li[@data-title='Adjustment / Special Billing']";
	String adjustmentSpecialBillingPage = 	"//h4[text()='Adjustment']";
	String adjustment 					= 	"//h4[text()='Adjustment']";
	String selectAdjustmentType 		= 	"//input[@name='inpt_adjtype_display']";
	String adjustmentPage				=	"//h1[contains(text(),'Adjustments')]";
	String addOrRemoveAgencyCommision 	= 	"(//input[@title='Add or Remove Agency Commission'])[1]";
	String adjustmentReasonGeneral 		= 	"//input[@name='inpt_adjcode12']";
	String agencyCommision 				= 	"//div[@class='dropdownDiv']/div[text()='Agency Commission']";
	String next    						= 	"//input[@name='next']";
	String agencyCommisNextBtn 			= 	"//input[@name='secondarynext']";
	String invoiceNumber 				= 	"//input[@id='invnum_text']";
	String invoiceNumberText 			=	"//input[@name='invnum_display']";
	String searchInvoice 				=	"//td[text()='Search Invoice']";
	String agencyCommisionOptions 		= 	"//input[@name='inpt_custpage_aggcomm_select']";
	String Comments  					= 	"//textarea[@id='custpage_memo']";
	String finish  						=	"//input[@value='Finish']";
	String caseNumber 					= 	"//table[@id='tbl__innerscroll']//a";
	String goToButton 					= 	"//button[contains(text(),'Go To')]";
	String invoiceCMS 					= 	"//span[@class='inputreadonly']/a[contains(text(),'Invoice')]";
	String adjustAmount = "//input[@id='custpage_creditamount_formattedValue']";
	String lineAdjustTable = "(//div[contains(text(),'Line Adjustment')]/following::tbody[2])/tr[contains(@id,'invoicelines_row')]";
	String downloadpdflink   = "//input[@id='custpage_showpdf']";
	String selectAdjArrow = "//img[@id='inpt_adjtype_display1_arrow']";
	String adjReasonGeneralDowArrow = "//img[@id='inpt_adjcode514_arrow']";
	String orderEntryError = "//div[text()='Order Entry Error']";

	//Locators for sales order multipleItems
	String saveing 					= 	"//input[@id='btn_secondarymultibutton_submitter']";
	String billing2 					= 	"//a[@id='billingtabtxt']";
	String billingcheckbox 			= 	"//*[@id='getauth_fs']/img";
	String csc 						= 	"//input[@id='ccsecuritycode']";
	String costcentreEnter 			= 	"//input[@id='department_display']";


	//Instance creation
	Generic oGenericUtils=new Generic();
	ScreenShot oScreenShot = new ScreenShot();
	


	/*##############################################################
		 @Descriptions 		---	Choosing Role As Admin
		 @param driver 		---	WebDriver parameter
		 @param XLTestData 	---	Test Data Parameter
		 @param basetest 	---	Report Parameter
		 ##############################################################*/
	public String SelectRoleFOrNetSuiteAsAdmin(WebDriver driver, HashMap<String, String> XLTestData, BaseTest basetest)
			throws InterruptedException {
		String homepageurl = null;
		try {

			//Move to Admin Role
			oGenericUtils.navigateMouseToElement(driver, By.id(adminElement),"Admin Button in Netsuite Home page",basetest);

			//Click on Administartor 
			oGenericUtils.clickButton(driver, By.xpath(SB2Admin),"Adminstartor", basetest);

			if(driver.getTitle().contains("Administrator Message - NetSuite"))
			{
				basetest.test.log(Status.INFO,"Administrator Message - NetSuite/Netsuite Under Maintance");
				driver.findElement(By.xpath("//*[@id='agree_fs_fs']/img")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@id='submitter']")).click();
			}

			//Checking for Home Page	
			if(driver.findElements(By.xpath(elementSB2)).size()>0) {

				homepageurl = driver.getCurrentUrl();
				basetest.test.log(Status.PASS,"User Role is successfully selected as "+XLTestData.get("UserRole").toString());
			}
			else 	
			{
				basetest.test.log(Status.FAIL,"User Role is Not selected as "+XLTestData.get("UserRole").toString()+"/May be NewPopup Occurs In Netsuite HomePage");
			}

			
		} catch (Exception e) {
			oGenericUtils.Verify("Object not found:="+e.getMessage(),"FAILED",basetest);
			basetest.test.log(Status.FAIL, "test failed");
		}
		return homepageurl;
	}



	/*##############################################################
	 @Descriptions 		---	Select Adjustment and Special Billing in Billing via Admin role
	 @param driver 		---	WebDriver parameter
	 @param XLTestData 	---	Test Data Parameter
	 @param basetest 	---	Report Parameter
	 ##############################################################*/
	public void selectAdjSplBillingInBilling(WebDriver driver,HashMap<String, String> XLTestData,BaseTest basetest) {

		try {	

			//webdriver waiting
			Thread.sleep(5000);

			//Move to Transactions
			oGenericUtils.navigateMouseToElement(driver, By.xpath(transcation),"Transcations",basetest);

			//Move to Billing
			oGenericUtils.navigateMouseToElement(driver, By.xpath(billing),"Billing",basetest);

			//Move to Adjustment / Special Billing
			oGenericUtils.navigateMouseToElement(driver, By.xpath(adjustmentSpecialBilling),"Adjustment / Special Billing",basetest);

			//click on Adjustment / Special Billing
			oGenericUtils.clickButton(driver, By.xpath(adjustmentSpecialBilling),"Adjustment / Special Billing",basetest);
			
			//Page validation
			if(driver.findElements(By.xpath(adjustment)).size()>0)
			{	
				basetest.test.log(Status.PASS,"Differnt Billing Request Page Loded Succesfully");
			}
			else
			{
				basetest.test.log(Status.FAIL,"Differnt Billing Request Page Loded Succesfully");
			}
				
		}catch(Exception e) {
			oGenericUtils.Verify("Object not found:="+e.getMessage(),"FAILED",basetest);
		}	
	}	



	/**
	 * @Descriptions 		---	Select Adjustment and Special Billing in Billing via Admin role
	 * @param driver 		---	WebDriver parameter
	 * @param XLTestData 	---	Test Data Parameter
	 * @param basetest 	---	Report Parameter
	 */
	 
	
	public String clickAdjustments(WebDriver driver,HashMap<String, String> XLTestData,BaseTest basetest) 
	{
		String invoiceNumberTextValue="";
		float grossAmount =0;
		float netDue =0;
		String adjmentAmtValue =  "";
		String grossValueFormated="";
		String netValueFormated="";
		
		try {
			float totalRevsiedAmt;
			//click on Adjustment
			oGenericUtils.clickButton(driver, By.xpath(adjustment),"Click on Adjustment",basetest);

			//wait untaile page load
			oGenericUtils.WaitUntilElement(driver, By.xpath(adjustmentPage), "Adjustment Page", basetest);
			
			Thread.sleep(5000);
			//click on select Adjustment Type
			oGenericUtils.clickButton(driver, By.xpath(selectAdjArrow),"Click on Select Adjustment Type",basetest);

			//click on Add or remove agency commision
			Thread.sleep(5000);
			String typeofAdjustment = "//div[contains(text(),'"+XLTestData.get("Scenario")+"')]";
			oGenericUtils.clickButton(driver, By.xpath(typeofAdjustment),"Type of Invoice Adjustment Slection",basetest);
			
			//tabing 
			Thread.sleep(6000);
			//clicking on OK Alert
			oGenericUtils.isAlertPresent(driver);
			
			//Agencyselection
			oGenericUtils.clickButton(driver, By.xpath(adjustmentReasonGeneral),"Click on Adjustment Reason General",basetest);
			String typeofagency = "//div[contains(text(),'"+XLTestData.get("Agencyselection")+"')]";
			oGenericUtils.clickButton(driver, By.xpath(typeofagency),"Adjustment Reason General",basetest);
			
			//click on next
			oGenericUtils.clickButton(driver, By.xpath(next),"Click on next",basetest);

			Thread.sleep(10000);
			String invoicePage = "//span[@id='invnum_text_fs_lbl']/a[contains(text(),'Invoice Number')]";
					
			if(driver.findElements(By.xpath(invoicePage)).size()>0){
				basetest.test.log(Status.PASS,"<span style='font-weight:bold;color:blue'> '"+"Invoice  Page verifeid successfully"+"'</span>");
			}else {
				basetest.test.log(Status.FAIL,"<span style='font-weight:bold;color:blue'> '"+"Invoice  Page Not verifeid"+"'</span>");
			}

			
			oGenericUtils.SetVal(driver, By.xpath(invoiceNumber), XLTestData.get("invoiceNumber").toString(),"Invoice Text Box",basetest);

			//click on Search Invoice
			oGenericUtils.clickButton(driver, By.xpath(searchInvoice),"Click on Invoice Search",basetest);

			Thread.sleep(18000);

			WebElement invoiceElement = driver.findElement(By.xpath(invoiceNumberText));

			invoiceNumberTextValue = invoiceElement.getAttribute("previousvalue");

			if(invoiceNumberTextValue.contains("#"))

				Assert.assertTrue("Invoice Verified Successfully", true);
			else
				Assert.assertFalse("InvoiceNotVerified", false);


			//click on next
			oGenericUtils.clickButton(driver, By.xpath(next),"Click on next",basetest);

			
			Thread.sleep(9000);
			if(driver.findElements(By.xpath(agencyCommisionOptions)).size()>0){
				basetest.test.log(Status.PASS,"<span style='font-weight:bold;color:blue'> '"+"Invoice  Page verifeid successfully"+"'</span>");
			}else {
				basetest.test.log(Status.FAIL,"<span style='font-weight:bold;color:blue'> '"+"Invoice  Page verifeid successfully"+"'</span>");
			}
			List<WebElement> lineAdjustTableRowCount=driver.findElements(By.xpath(lineAdjustTable));

			int size =lineAdjustTableRowCount.size();
			ArrayList<Float> beforeRevisedNetAmt =  new ArrayList<Float>();
			ArrayList<Float> aftereRevisedNetAmt =new ArrayList<Float>();
			for(int i=1; i<=size ; i++){
				List<WebElement> tableHead = driver.findElements(By.xpath("//table[@id='invoicelines_splits']/tbody/tr/td/div"));
				int headSize = tableHead.size();
				for(int j=1;j<=headSize;j++){
					String headText = driver.findElement(By.xpath("(//table[@id='invoicelines_splits']/tbody/tr/td/div)["+j+"]")).getText().trim();
					if(headText.contains("REVISED NET AMOUNT")){
						String text = oGenericUtils.getTextOfElement(driver, "//table[@id='invoicelines_splits']/tbody/tr[contains(@id,'invoicelines_row_"+i+"')]/td["+j+"]");						
						beforeRevisedNetAmt.add(Float.parseFloat(text));
						break;
					}
				}
				
			}
			
			//click on Adjustment Reason General   agencyCommision
			oGenericUtils.clickButton(driver, By.xpath(agencyCommisionOptions),"Click on Agency Commision",basetest);

			//click on Remove agency commision
			oGenericUtils.clickButton(driver, By.xpath(removeAgencyCommision),"Click on Remove Agency Commision",basetest);

			Thread.sleep(18000);
			 lineAdjustTableRowCount=driver.findElements(By.xpath(lineAdjustTable));

			 size =lineAdjustTableRowCount.size();
			 String revisedcommisionRate="";
			 for(int i=1; i<=size ; i++){
					List<WebElement> tableHead = driver.findElements(By.xpath("//table[@id='invoicelines_splits']/tbody/tr/td/div"));
					int headSize = tableHead.size();
					for(int j=1;j<=headSize;j++){
						String headText = driver.findElement(By.xpath("(//table[@id='invoicelines_splits']/tbody/tr/td/div)["+j+"]")).getText().trim();
						if(headText.contains("REVISED AG COMM AMOUNT")){
							 revisedcommisionRate = oGenericUtils.getTextOfElement(driver, "//table[@id='invoicelines_splits']/tbody/tr[contains(@id,'invoicelines_row_"+i+"')]/td["+j+"]");						
							break;
						}
					}
					if(revisedcommisionRate.contains("0.0")){
						basetest.test.log(Status.PASS,"<span style='font-weight:bold;color:blue'>Revised commision rate is zero"+"'</span>");

					}else{
						basetest.test.log(Status.FAIL,"<span style='font-weight:bold;color:blue'>Revised commision rate not changed"+"'</span>");

					}	
				}
	
			
			WebElement adjAmtElement = driver.findElement(By.xpath(adjustAmount));
			adjmentAmtValue = adjAmtElement.getAttribute("value").trim();
			if(adjmentAmtValue.contains("-")){
				adjmentAmtValue = adjmentAmtValue.replace("-", "");
			}else if(adjmentAmtValue.contains("+")){
				adjmentAmtValue = adjmentAmtValue.replace("+", "");
			}
			float adjmentAmtValueInDecimal = Float.parseFloat(adjmentAmtValue);
			
			ArrayList<Float> netCreditAmtDecimal = new ArrayList<Float>();
			float totalNetCreditAmount=0;
			for(int i=1; i<=size ; i++){
				String netCreditAmtText ="";
				List<WebElement> tableHead = driver.findElements(By.xpath("//table[@id='invoicelines_splits']/tbody/tr/td/div"));
				int headSize = tableHead.size();
				for(int j=1;j<=headSize;j++){
					String headText = driver.findElement(By.xpath("(//table[@id='invoicelines_splits']/tbody/tr/td/div)["+j+"]")).getText().trim();
					if(headText.contains("NET CREDIT AMOUNT")){
						netCreditAmtText = oGenericUtils.getTextOfElement(driver, "//table[@id='invoicelines_splits']/tbody/tr[contains(@id,'invoicelines_row_"+i+"')]/td["+j+"]"); 
						break;
					}
				}				
				if(netCreditAmtText.contains("-")){
					netCreditAmtText = netCreditAmtText.replace("-", "");
				}else if(netCreditAmtText.contains("+")){
					netCreditAmtText = netCreditAmtText.replace("+", "");
				}
				float netCreditAmount=Float.parseFloat(netCreditAmtText);
				netCreditAmtDecimal.add(netCreditAmount);
				totalNetCreditAmount=totalNetCreditAmount+netCreditAmount;
			}
			
			if(adjmentAmtValueInDecimal==totalNetCreditAmount){
				basetest.test.log(Status.PASS, "Adjustment Amount("+adjmentAmtValueInDecimal+")= Total Net Credit Amount("+totalNetCreditAmount+") are equal");
			}else{
				basetest.test.log(Status.FAIL, "Adjustment Amount("+adjmentAmtValueInDecimal+")= Total Net Credit Amount("+totalNetCreditAmount+") are equal not equal");
			}
			
			 lineAdjustTableRowCount=driver.findElements(By.xpath(lineAdjustTable));

			 size =lineAdjustTableRowCount.size();
			
			
			for(int i=1; i<=size ; i++){
				List<WebElement> tableHead = driver.findElements(By.xpath("//table[@id='invoicelines_splits']/tbody/tr/td/div"));
				int headSize = tableHead.size();
				for(int j=1;j<=headSize;j++){
					String headText = driver.findElement(By.xpath("(//table[@id='invoicelines_splits']/tbody/tr/td/div)["+j+"]")).getText().trim();
					if(headText.contains("REVISED NET AMOUNT")){
						String text = oGenericUtils.getTextOfElement(driver, "//table[@id='invoicelines_splits']/tbody/tr[contains(@id,'invoicelines_row_"+i+"')]/td["+j+"]");
						aftereRevisedNetAmt.add(Float.parseFloat(text));
						break;
					}
				}
				totalRevsiedAmt	=netCreditAmtDecimal.get(i-1)+beforeRevisedNetAmt.get(i-1);
				if(aftereRevisedNetAmt.get((i-1))==(totalRevsiedAmt)){
					basetest.test.log(Status.PASS, "After Revised Net Amount : "+aftereRevisedNetAmt.get((i-1))+" Before Revised Net Amount("+beforeRevisedNetAmt.get(i-1)+")+ Net Credit Amount("+netCreditAmtDecimal.get(i-1)+") are equal");
				}else{
					basetest.test.log(Status.FAIL, "After Revised Net Amount : "+aftereRevisedNetAmt.get((i-1))+" Before Revised Net Amount("+beforeRevisedNetAmt.get(i-1)+")+ Net Credit Amount("+netCreditAmtDecimal.get(i-1)+") are not equal");
				}
				
			}
			
			
			//click on next
			oGenericUtils.clickButton(driver, By.xpath(agencyCommisNextBtn),"Click on next",basetest);


			Thread.sleep(6000);
			//Enter Comments

			oGenericUtils.setText(driver, Comments, "QA");

			//click on finish
			oGenericUtils.clickButton(driver, By.xpath(finish),"Click on finish",basetest);


			//click on Casenumber

			String caseNumberText =  oGenericUtils.getTextOfElement(driver, caseNumber);
			if(caseNumberText.length()>0){
				basetest.test.log(Status.INFO, "Case Number :"+caseNumberText);
			}
			Thread.sleep(3000);
			oGenericUtils.clickButton(driver, By.xpath(goToButton), "Go To Case", basetest);


			
			Thread.sleep(8000);
			//page scroll
			/*JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,600)");
			*/
			oGenericUtils.clickButton(driver, By.xpath(invoiceCMS), "invoice CMS", basetest);
			Thread.sleep(8000);
			
			
			for(float tempGrossAmt: aftereRevisedNetAmt){
				grossAmount = grossAmount+tempGrossAmt;
			}
			
			for(float tempNetDue :beforeRevisedNetAmt){
				netDue = netDue+tempNetDue;
			}
			
			 grossValueFormated = String.valueOf(grossAmount);
			grossValueFormated = "$"+grossValueFormated.substring(0,1)+","+grossValueFormated.substring(1,grossValueFormated.length());
			
			 netValueFormated = String.valueOf(netDue);
			netValueFormated = "$"+netValueFormated.substring(0,1)+","+netValueFormated.substring(1,netValueFormated.length());
			
			
			//Return [1]Gross Amount[2]Net Due[3]invoicenumber
			
		}catch(Exception e) {
			oGenericUtils.Verify("Object not found:="+e.getMessage(),"FAILED",basetest);
		}
		return grossValueFormated+";"+adjmentAmtValue+";"+netValueFormated;	
	}
	
	public void enableAutomaticPDFDownload(WebDriver driver) throws AWTException, InterruptedException{

        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabList = new ArrayList<String>(driver.getWindowHandles());
        int numberOfWindows = tabList.size();
        System.out.println(numberOfWindows);
        driver.switchTo().window(tabList.get(numberOfWindows-1));
        driver.get("chrome://settings/content/pdfDocuments");
        WebElement settingsUIRoot = driver.findElement(By.xpath("//settings-ui"));
        WebElement shadowRoot1 = (WebElement)((JavascriptExecutor)driver).executeScript("return arguments[0].shadowRoot",settingsUIRoot);
        WebElement settingsMainRoot = shadowRoot1.findElement(By.cssSelector("settings-main"));
        WebElement shadowRoot2 = (WebElement)((JavascriptExecutor)driver).executeScript("return arguments[0].shadowRoot",settingsMainRoot);
        WebElement settingsBasicPageRoot = shadowRoot2.findElement(By.cssSelector("settings-basic-page"));
        WebElement shadowRoot3 = (WebElement)((JavascriptExecutor)driver).executeScript("return arguments[0].shadowRoot",settingsBasicPageRoot);
        WebElement settingsPrivacyPageRoot = shadowRoot3.findElement(By.cssSelector("settings-privacy-page"));
        WebElement shadowRoot4 = (WebElement)((JavascriptExecutor)driver).executeScript("return arguments[0].shadowRoot",settingsPrivacyPageRoot);
        WebElement settingsPDFDocumentsRoot = shadowRoot4.findElement(By.cssSelector("settings-pdf-documents"));
        WebElement shadowRoot5 = (WebElement)((JavascriptExecutor)driver).executeScript("return arguments[0].shadowRoot",settingsPDFDocumentsRoot);
        WebElement settingsToggleButtonRoot = shadowRoot5.findElement(By.cssSelector("settings-toggle-button"));
        WebElement shadowRoot6 = (WebElement)((JavascriptExecutor)driver).executeScript("return arguments[0].shadowRoot",settingsToggleButtonRoot);    
        WebElement crToggleRoot = shadowRoot6.findElement(By.cssSelector("cr-toggle"));
        WebElement shadowRoot7 = (WebElement)((JavascriptExecutor)driver).executeScript("return arguments[0].shadowRoot",crToggleRoot);
        WebElement enableDownloadButton = shadowRoot7.findElement(By.cssSelector("#knob"));
        boolean button= enableDownloadButton.isSelected();
        if(button==false){
            enableDownloadButton.click();
        }
        //driver.findElement(By.id("knob")).click();

        //keyboard operation is working fine for selecting automatic download option
        /*Robot robot = new Robot();  // Robot class throws AWT Exception    
        Thread.sleep(2000); 
        robot.keyPress(KeyEvent.VK_TAB);  
        Thread.sleep(2000);  
        robot.keyPress(KeyEvent.VK_TAB);  
        Thread.sleep(2000);  
        robot.keyPress(KeyEvent.VK_ENTER);*/
       Thread.sleep(18000);

        driver.switchTo().window(tabList.get(numberOfWindows-2));
 }




	public void clickPDFDownloadLink(WebDriver driver,BaseTest basetest) throws InterruptedException, AWTException{
        
		Thread.sleep(15000);
		//enableAutomaticPDFDownload(driver);
       
        //click on Adjustment
		oGenericUtils.clickButton(driver, By.xpath(downloadpdflink),"Click on Adjustment",basetest);

        Thread.sleep(30000);
 }

	
	

	public void ChangeinvoiceAddress(WebDriver driver,HashMap<String, String> XLTestData,BaseTest basetest) throws InterruptedException {
		
		//click on Adjustment
		oGenericUtils.clickButton(driver, By.xpath(adjustment),"Click on Adjustment",basetest);

		//wait untaile page load
		oGenericUtils.WaitUntilElement(driver, By.xpath(adjustmentPage), "Adjustment Page", basetest);
		
		Thread.sleep(5000);
		//click on select Adjustment Type
		oGenericUtils.clickButton(driver, By.xpath("//img[@id='inpt_adjtype_display1_arrow']"),"Click on Select Adjustment Type",basetest);
		
		//click on Adrress/Est/NoteChange
		Thread.sleep(5000);
		String typeofAdjustment = "//div[contains(text(),'"+XLTestData.get("Scenario")+"')]";
		oGenericUtils.clickButton(driver, By.xpath(typeofAdjustment),"Type of Invoice Adjustment Slection",basetest);
		
		//tabing 
		Thread.sleep(6000);
		//clicking on OK Alert
		oGenericUtils.isAlertPresent(driver);
		
		//Agencyselection
		oGenericUtils.clickButton(driver, By.xpath(adjustmentReasonGeneral),"Click on Adjustment Reason General",basetest);
		String typeofagency = "//div[contains(text(),'"+XLTestData.get("Agencyselection")+"')]";
		oGenericUtils.clickButton(driver, By.xpath(typeofagency),"Adjustment Reason General",basetest);
		
		//click on next
		oGenericUtils.clickButton(driver, By.xpath(next),"Click on next",basetest);
		
		Thread.sleep(10000);
		String invoicePage = "//span[@id='invnum_text_fs_lbl']/a[contains(text(),'Invoice Number')]";
				
		if(driver.findElements(By.xpath(invoicePage)).size()>0){
			basetest.test.log(Status.PASS,"<span style='font-weight:bold;color:blue'> '"+"Invoice  Page verifeid successfully"+"'</span>");
		}else {
			basetest.test.log(Status.FAIL,"<span style='font-weight:bold;color:blue'> '"+"Invoice  Page Not verifeid"+"'</span>");
		}

		oGenericUtils.SetVal(driver, By.xpath(invoiceNumber), XLTestData.get("invoiceNumber").toString(),"Invoice Text Box",basetest);

		//click on Search Invoice
		oGenericUtils.clickButton(driver, By.xpath(searchInvoice),"Click on Invoice Search",basetest);

		Thread.sleep(18000);
		
		WebElement invoiceElement = driver.findElement(By.xpath(invoiceNumberText));

		String invoiceNumberText = invoiceElement.getAttribute("previousvalue");

		if(invoiceNumberText.contains("#"))

			Assert.assertTrue("Invoice Verified Successfully", true);
		else
			Assert.assertFalse("InvoiceNotVerified", false);


		//click on next
		oGenericUtils.clickButton(driver, By.xpath(next),"Click on next",basetest);

		
		Thread.sleep(9000);
		
		/*if(driver.findElements(By.xpath(agencyCommisionOptions)).size()>0){
			basetest.test.log(Status.PASS,"<span style='font-weight:bold;color:blue'> '"+"Invoice  Page verifeid successfully"+"'</span>");
		}else {
			basetest.test.log(Status.FAIL,"<span style='font-weight:bold;color:blue'> '"+"Invoice  Page verifeid successfully"+"'</span>");
		}*/
		
		
		//click on select Adjustment Type
		oGenericUtils.clickButton(driver, By.xpath("//img[@id='inpt_custpage_billto1_arrow']"),"Click on Select Adjustment Type",basetest);
		
		String typeofBilltoSelect = "//div[contains(text(),'"+XLTestData.get("Bill TO Select")+"')]";
		oGenericUtils.clickButton(driver, By.xpath(typeofBilltoSelect),"Type of Invoice Adjustment Slection",basetest);
			
		String revisedESTIOCPE ="//textarea[@id='custpage_cpe']";
		oGenericUtils.SetVal(driver, By.xpath(revisedESTIOCPE), XLTestData.get("RevisedEST/IO/CPE").toString(),"RevisedEST/CPE",basetest);
		
		String newNote1 = "//textarea[@id='custpage_note1']";
		oGenericUtils.SetVal(driver, By.xpath(newNote1), XLTestData.get("NewNote1").toString(),"NewNote1",basetest);
		
		String newNote2 = "//textarea[@id='custpage_note2']";
		oGenericUtils.SetVal(driver, By.xpath(newNote2), XLTestData.get("NewNote2").toString(),"NewNote2",basetest);		
	}
/**
 * 
 * @param driver
 */
	public void clickOnAdjustment(WebDriver driver,BaseTest basetest) {
		try {
			oGenericUtils.clickButton(driver, By.xpath(adjustment), "Click on Adjustment", basetest);
		} catch (Exception e) {
			System.out.println(e);
		}
	}	
	public void splitInvoiceSplitStation(WebDriver driver,HashMap<String,String> XLTestData,BaseTest basetest){
		try{
			clickOnAdjustment(driver, basetest);
			oGenericUtils.clickButton(driver, By.xpath(selectAdjArrow),"Click on Select Adjustment Type",basetest);		
			String typeofAdjustment = "//div[contains(text(),'"+XLTestData.get("Scenario")+"')]";
			oGenericUtils.clickButton(driver, By.xpath(typeofAdjustment),"Type of Invoice Adjustment Slection",basetest);
			Thread.sleep(3000);			
			//click on select Adjustment Type
			oGenericUtils.clickButton(driver, By.xpath(adjReasonGeneralDowArrow),"Click on Select Adjustment Type",basetest);
			Thread.sleep(2000);
			oGenericUtils.clickButton(driver, By.xpath(orderEntryError),"Click on Select Adjustment Type",basetest);
			oGenericUtils.clickButton(driver, By.xpath(next),"Click on next button",basetest);
			Thread.sleep(5000);
			oGenericUtils.SetVal(driver, By.xpath(invoiceNumber), XLTestData.get("invoiceNumber").toString(),"Invoice Text Box",basetest);
			oGenericUtils.clickButton(driver, By.xpath(searchInvoice),"Click on Invoice Search",basetest);

			Thread.sleep(18000);
			oGenericUtils.clickButton(driver, By.xpath(next),"Click on next button",basetest);
			Thread.sleep(2000);
		}catch(Exception e){
			
		}
	}
}
