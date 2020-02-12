package com.netsuite.common;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.framework.BaseTest;
import com.framework.Generic;

public class NS_Login_iHeart {
	BaseTest basetest;

	//Locators for Login Page
	String img_NetSuite					=	"//img[@class='uir-logo']";

	//Locators for Login
	String username 					= 	"//input[@id='userName']";
	String pass 						= 	"//input[@id='password']";

	//logout NetSuite application Locators
	String move							=	"//*[@id='spn_cRR_d1']/a";
	String logout 						=	"//span[contains(text(),'Log Out')]";
	String elementhomepage 				= 	"//div[@id='ns-dashboard-heading-panel']/h1";

	//choosing role Locators
	public	String adminElement 		= 	"spn_cRR_d1";
	public	String WSISB2Servicerepo  	= 	"//span[contains(text(),'WSI SB2 - DEV  -  WSI- Support Rep')]";
	public	String WSISB2Purchaser 		= 	"//span[contains(text(),'WSI SB2 - DEV  -  WSI Purchaser')]";
	public	String WSISB2FullAccess 	= 	"//span[contains(text(),'WSI SB2 - DEV  -  WSI Full System Access')]";
	public	String Admin 				= 	"//span[contains(text(),'WSI SB1 - DEV1  -  Administrator')]";
	public	String elementSB2 			= 	"//*[@id='ns-header-menu-main-item2']";

	//Authentication Locators
	String SQ1					 		= 	"What is your maternal grandmother's maiden name?";
	String SQ2 							= 	"What was your childhood nickname?";
	String SQ3 							= 	"In what city did you meet your spouse/significant other?";
	String securityAnswerX 				= 	"//input[@type='password']";
	String secAnsSubmitX 				= 	"//input[@type='submit']";
	String UserRole 					=	"//span[@class='ns-role-menuitem-text'][contains(text(),'WSI Full System Access')]";

	//Locators for OTP
	String newmessage 					= 	"//ul[@id='navBarTabs']/li[1]/a";
	String otp 							= 	"//span[contains(text(),'Your NetSuite verification code is')]";
	String otptextbox 					= 	"//input[@placeholder= '6-digit code']";
	String submitbtn 					= 	"//div[@n-login-id='button-login-next']";
	String gmailusername 				= 	"//input[@name='identifier']";
	String gmailnext 					= 	"//div[@id='identifierNext']";
	String passwordinput 				= 	"//input[@name='password']";
	String passwordnext 				= 	"//div[@id='passwordNext']";
	String popupcancel 					= 	"//button[@id='custom-alert-and-confirm-modal-cancel-button']";

	//Instance creation
	Generic oGenericUtils=new Generic();

	//===========================Launch NetSuite===========================================================>
	public WebDriver LaunchNetSuiteApp(String sURL,HashMap<String, String> XLTestData,String filePathInString,BaseTest basetest) {
		WebDriver driver = null;
		try {

			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\resources\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();   
			
			Map<String,Object> prefs = new HashMap<String,Object>();
			if(!filePathInString.isEmpty()){
				prefs.put("download.default_directory", filePathInString);
			}
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("user-data-dir="+System.getProperty("user.home")+"\\AppData\\Local\\Google\\Chrome\\User Data\\Default");
			options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
			options.addArguments("--disable-features=InfiniteSessionRestore");
			options.addArguments("--disable-notifications");
			options.addArguments("--start-maximized");
			options.addArguments("--disable-infobars"); // disabling infobars
			options.addArguments("--disable-extensions"); // disabling extensions
			options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
			
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
			driver = new ChromeDriver(options);
			driver.get(sURL);

			//Page Maximize
			//driver.manage().window().maximize();				  
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			basetest.test.log(Status.PASS, XLTestData.get("Environment").toString() + " Environment URL "+XLTestData.get("NetSuite_URL").toString() + "  is Launched Successfully");

		}catch(Exception e) {
			oGenericUtils.Verify("Object not found:="+e.getMessage(), "FAILED",basetest);
			basetest.test.log(Status.FAIL,"Netsuite Application is not Launched");
		}
		return driver;
	}

	/*##############################################################
	 @Descriptions 		---	Login to NetSuite Page
	 @param driver 		---	WebDriver parameter
	 @param XLTestData 	---	Test Data Parameter
	 @param basetest 	---	Report Parameter
	 ##############################################################*/
	public void NetSuiteLogin(WebDriver driver,HashMap<String, String> XLTestData,BaseTest basetest) {
		try {

			//Enter Username
			oGenericUtils.SetVal(driver, By.xpath(username), XLTestData.get("NetEmail").toString(),"Username textbox",basetest);

			//Enter Password
			driver.findElement(By.xpath(pass)).sendKeys(XLTestData.get("NetPassword").toString());
			basetest.test.log(Status.INFO,"******* is Entered Successfully in Password Text Box");

			//Click Login Button
			oGenericUtils.clickButton(driver, By.id("submitButton"),"Submit Button",basetest);


			if(driver.findElements(By.xpath(elementhomepage)).size() > 0) 
			{
				basetest.test.log(Status.INFO, "User is successfully logged in to NetSuite");

			}
			else {

				if(driver.getTitle().contains("Administrator Message - NetSuite"))
				{
					basetest.test.log(Status.INFO, "Administrator Message - NetSuite/Under Maintainace ");
					driver.findElement(By.xpath("//*[@id='agree_fs_fs']/img")).click();
					Thread.sleep(1000);
					driver.findElement(By.xpath("//*[@id='submitter']")).click();
				}
				else 
				{
					if(driver.getTitle().contains("Two-factor login challenge")) {
						
						basetest.test.log(Status.FAIL, "User is not successfully logged in to NetSuite/Pass Code is Expired");
						driver.close();
					}
				
					basetest.test.log(Status.FAIL, "User is not successfully logged in to NetSuite/May NewPop Occurs in Netsuite HomePage");
					Assert.assertTrue(false);
				}
			}

		} catch (Exception e) {
			oGenericUtils.Verify("Object not found:="+e.getMessage(),"FAILED",basetest);
			basetest.test.log(Status.FAIL, "test failed");
		}
	}


	/*##############################################################
	 @Descriptions 		---	Logout from NetSuite Page
	 @param driver 		---	WebDriver parameter
	 @param basetest 	---	Report Parameter
	 ##############################################################*/
	public void NetSuiteLogout(WebDriver driver,BaseTest basetest) {
		try {

			//Click on Logout Page
			oGenericUtils.navigateMouseToElement(driver, By.xpath(move),"Logout button",basetest);
			oGenericUtils.clickButton(driver, By.xpath(logout),"Logout button",basetest);
			basetest.test.log(Status.PASS,"Logout from Netsuite Application Successfully");

		}catch(Exception e) {
			oGenericUtils.Verify("Object not found:="+e.getMessage(), "FAILED",basetest);
			basetest.test.log(Status.FAIL,"Logout from netsuite application not happens");
		}
	}

	/*##############################################################
	 @Descriptions 		---	Choosing Role from DropDown
	 @param driver 		---	WebDriver parameter
	 ##############################################################*/
	public void chosingRole(WebDriver driver) throws Exception {		

		//Move to Admin Role
		oGenericUtils.navigateMouseToElement(driver, By.id(adminElement),"Admin Role",basetest);

		//Click onn Service Repo Role
		oGenericUtils.clickButton(driver, By.xpath(WSISB2Servicerepo),"service Repo Role", basetest);
		oGenericUtils.isElementExist(driver, By.xpath(elementSB2),"home Page",basetest);
	}

	/*##############################################################
	 @Descriptions 		---	Authentication Page
	 @param driver 		---	WebDriver parameter
	 @param XLTestData 	---	Test Data Parameter
	 @param basetest 	---	Report Parameter
	 ##############################################################*/
	public void answerSecurityQuestion(WebDriver driver, HashMap<String, String> XLTestData,BaseTest basetest) throws InterruptedException {
		try {

			//Verify Authentication screen
			if (oGenericUtils.isElementExist(driver, By.xpath("//h1[contains(text(),'Logging in to WSI SB2 - DEV')]"),"Two factor Authentication", basetest)) {
				oGenericUtils.clickButton(driver, By.xpath("//a[@n-login-id='button-login-change-role-WSI_SB2_-_DEV-SANDBOX-WSI-_Support_Rep']"),"choosing role",basetest);
			} else if(oGenericUtils.isElementExist(driver, By.xpath("//td[contains(text(),'Additional Authentication Required')]"),"Authentication", basetest)) {
				//Question1
				if(driver.findElement(By.xpath("//td[contains(text(),'What was your childhood')]")).isDisplayed()){
					oGenericUtils.SetVal(driver, By.xpath(securityAnswerX), XLTestData.get("securityAnswer2").toString(),"Answer Text Box",basetest);
					oGenericUtils.clickButton(driver, By.xpath(secAnsSubmitX),"submit",basetest);
					//Question2
				} else  if(driver.findElement(By.xpath("//td[contains(text(),'What is your maternal grandmother')]")).isDisplayed()){
					oGenericUtils.SetVal(driver, By.xpath(securityAnswerX), XLTestData.get("securityAnswer1").toString(),"Answer Text Box",basetest);
					oGenericUtils.clickButton(driver, By.xpath(secAnsSubmitX),"submit",basetest);

					//Question3	    	
				}else if(driver.findElement(By.xpath("//td[contains(text(),'In what city did you meet your spouse')]")).isDisplayed()) {
					oGenericUtils.SetVal(driver, By.xpath(securityAnswerX), XLTestData.get("securityAnswer3").toString(),"Answer Text Box",basetest);
					oGenericUtils.clickButton(driver, By.xpath(secAnsSubmitX),"submit",basetest);
				}
			}

			oGenericUtils.isElementExist(driver, By.xpath("//h1[.='Home']"),"home field",basetest);
			basetest.test.log(Status.PASS,"<span style='font-weight:bold;color:blue'> '"+ "login into netsuite application successfully" +  "'</span>");
		}catch(Exception e) {
			oGenericUtils.Verify("Object not found:="+e.getMessage(), "FAILED",basetest);
		}
	}

	/*##############################################################
	 @Descriptions 		---	OTP Authentication Page
	 @param driver 		---	WebDriver parameter
	 @param XLTestData 	---	Test Data Parameter
	 @param basetest 	---	Report Parameter
	 ##############################################################*/
	public void otpAuth(WebDriver driver, HashMap<String, String> XLTestData,BaseTest basetest) throws InterruptedException {
		try {

			//Verifiaction of Two Factor Authentication Page
			if (oGenericUtils.isElementExist(driver, By.xpath("//h1[contains(text(),'Logging in to WSI SB2 - DEV')]"),"Two factor Authentication", basetest)) {
				String a = "window.open('https://mightytext.net/web8','_blank');";
				((JavascriptExecutor)driver).executeScript(a);
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1));

				//Gmail login
				driver.findElement(By.xpath(gmailusername)).sendKeys("vandrapu.navi@gmail.com");
				driver.findElement(By.xpath(gmailnext)).click();
				driver.findElement(By.xpath(passwordinput)).sendKeys("navigmail");
				driver.findElement(By.xpath(passwordnext)).click();
				driver.findElement(By.xpath(popupcancel)).click();    		

				//Click on New Message Page
				oGenericUtils.clickButton(driver, By.xpath(newmessage), "newmessage", basetest);

				WebElement element = driver.findElement(By.xpath(otp));
				String message = element.getText();
				System.out.println(message);
				String newotp =	extractDigits(message);

				//Frame Handling
				driver.switchTo().window(tabs.get(0));

				//Click on Submit test Page
				oGenericUtils.clickButton(driver, By.xpath(submitbtn), "submit", basetest);
			}
		}catch(Exception e) {
			oGenericUtils.Verify("Object not found:="+e.getMessage(), "FAILED",basetest);
		}
	}

	/*##############################################################
	 @Descriptions 		---	extracting digits from url
	 @param src 		---	String value
	 ##############################################################*/
	public String extractDigits(String src) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < src.length(); i++) {
			char c = src.charAt(i);
			if (Character.isDigit(c)) {
				builder.append(c);
			}
		}
		return builder.toString();
	}
}
