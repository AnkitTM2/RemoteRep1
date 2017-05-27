package com.tm.hrm.set1.TestScriptDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.library.WebLibrary;

public class M1_FirstSet extends WebLibrary 
{
  String plog;
  String flog;
  Boolean stepstatus;
  @Test
  @Parameters("browser")
  public void TC101_AddEmergencyContactsAndVerify(String BrowserName) 
  {
	    WebDriver driver= launchBrowser(BrowserName);
	    SetImplicitWait(15, driver);
		
	    stepstatus = OpenUrl(getdata("Url"), driver);
		plog = "Application launched successfully";
		flog = "Unable to luach the application";
		logEvent(stepstatus, plog,flog, driver, true);
		
		stepstatus = setText("//input[@name='txtUsername']", getdata("UserName"), driver);
		plog = "Able to enter username";
		flog = "Unable to to enter username";
		logEvent(stepstatus, plog,flog, driver, false);
		
		stepstatus = setText("//input[@name='txtPassword']", getdata("Password"), driver);
		plog = "Able to enter password";
		flog = "Unable to enter password";
		logEvent(stepstatus, plog,flog, driver, false);
		
		
		stepstatus = clickElement("//input[@value='LOGIN']", driver);
		plog = "Able to click on login";
		flog = "Unable to click on login";
		logEvent(stepstatus, plog,flog, driver, false);
	    
		
		stepstatus = clickElement("//b[text()='My Info']", driver);
		plog = "Able to click on My Info";
		flog = "Unable to click on My Info";
		logEvent(stepstatus, plog,flog, driver, false);

		stepstatus = clickElement("//a[text()='Emergency Contacts']", driver);
		plog = "Able to click on Emergency Contacts";
		flog = "Unable to click on Emergency Contacts";
		logEvent(stepstatus, plog,flog, driver, false);
		
		
		stepstatus = clickElement("//input[@id='btnAddContact']", driver);
		plog = "Able to click on Add Contact";
		flog = "Unable to click on Add Contact";
		logEvent(stepstatus, plog,flog, driver, false);
		
		stepstatus = setText("//input[@id='emgcontacts_name']", getdata("Name"), driver);
		plog = "Able to enter contact name";
		flog = "Unable to enter contact name";
		logEvent(stepstatus, plog,flog, driver, false);
		
		stepstatus = setText("//input[@id='emgcontacts_relationship']", getdata("RelationShip"), driver);
		plog = "Able to enter relationship";
		flog = "Unable to enter relationship";
		logEvent(stepstatus, plog,flog, driver, false);
		
		stepstatus = setText("//input[@id='emgcontacts_homePhone']", getdata("HomePhone"), driver);
		plog = "Able to enter Homephone";
		flog = "Unable to enter Homephone";
		logEvent(stepstatus, plog,flog, driver, false);
		
		stepstatus = setText("//input[@id='emgcontacts_mobilePhone']", getdata("MobilePhone"), driver);
		plog = "Able to enter Mobilephone";
		flog = "Unable to enter Mobilephone";
		logEvent(stepstatus, plog,flog, driver, false);
		
		stepstatus = setText("//input[@id='emgcontacts_workPhone']", getdata("WorkPhone"), driver);
		plog = "Able to enter WorkPhone";
		flog = "Unable to enter WorkPhone";
		logEvent(stepstatus, plog,flog, driver, false);
		

		stepstatus = clickElement("//input[@id='btnSaveEContact']", driver);
		plog = "Able to click save";
		flog = "Unable to click save";
		logEvent(stepstatus, plog,flog, driver, true);
		
		stepstatus=exists("//div[contains(text(),'Successfully Saved')]", driver);
		plog = "Saved Successfully Message is displsyed";
		flog = "Saved Successfully Message is Not displsyed";
		logEvent(stepstatus, plog,flog, driver, true);
		
		
		stepstatus = clickElement("//a[@id='welcome']", driver);
		plog = "Able to click Welcome";
		flog = "Unable to click Welcome";
		logEvent(stepstatus, plog,flog, driver, false);
		
		stepstatus = clickElement("//a[text()='Logout']", driver);
		plog = "Able to click Logout";
		flog = "Unable to click Logout";
		logEvent(stepstatus, plog,flog, driver, false);
		
		stepstatus=exists("//input[@name='txtUsername']", driver);
		plog = "Logout of the Application is Successful";
		flog = "Unable to Logout of the application";
		logEvent(stepstatus, plog,flog, driver, true);
		
		quitDriver(driver);
  } 
 
  @Test
  @Parameters("browser")
  public void TC102_AddDependenciesAndVerify(String BrowserName) 
  {
	   
  } 
  
  @Test
  @Parameters("browser")
  public void TC103_AddImmigrationDetailsAndVerify(String BrowserName) 
  {
	   
  } 
  
  @Test
  @Parameters("browser")
  public void TC104_Immigration_AddAttachment(String BrowserName) 
  {
	   
  } 
  
  @Test
  @Parameters("browser")
  public void TC105_Immigration_VerifyErrorMessage(String BrowserName) 
  {
	   
  } 
}
