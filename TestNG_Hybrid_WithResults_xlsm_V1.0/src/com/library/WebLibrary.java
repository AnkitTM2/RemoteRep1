package com.library;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

public class WebLibrary extends ExcelLibrary
{
/*
	 * Description       : To enter text into a text field
	 * Method Name       : setText
	 * Input parameters  : 1)Webelement Xpath
	 *                     2)Value
	 *                     3)driver reference
	 * Created By        :Ankit Reddy
	 * Created On        :8-May-2017     
	 * LastUpdated On    :9-May-2017
	 * Last Updated By   : Ankit Reddy
	 * Email id          :tmasters.ankit@gmail.com 
	 * Comments          : 
*/
	protected static boolean setText(String strXpath,String strValue,WebDriver driver)
	{
		boolean stepstatus;
		try
		{
			driver.findElement(By.xpath(strXpath)).click();
			driver.findElement(By.xpath(strXpath)).clear();
			driver.findElement(By.xpath(strXpath)).sendKeys(strValue);
			stepstatus = true;
		}
		catch(Exception e)
		{
			stepstatus = false;
		}
		return stepstatus;
	}
	
/*
	 * Description       : To click on a particular field
	 * Method Name       : clickElement
	 * Input parameters  : 1)Webelement Xpath
	 *                     2)driver reference
	 * Created By        :Ankit Reddy
	 * Created On        :8-May-2017     
	 * LastUpdated On    :9-May-2017
	 * Last Updated By   : Ankit Reddy
	 * Email id          :tmasters.ankit@gmail.com 
	 * Comments          : 
*/
	protected static boolean clickElement(String strXpath,WebDriver driver)
	{
		boolean stepstatus;
		try
		{
			driver.findElement(By.xpath(strXpath)).click();
			stepstatus = true;
		}
		catch(Exception e)
		{
			stepstatus = false;
		}
		return stepstatus;
	}
/*
	 * Description       : To Launch a browser based on the name provided
	 * Method Name       : launchBrowser
	 * Input parameters  : 1)BrowserName
	 * Created By        :Ankit Reddy
	 * Created On        :8-May-2017     
	 * LastUpdated On    :9-May-2017
	 * Last Updated By   : Ankit Reddy
	 * Email id          :tmasters.ankit@gmail.com 
	 * Comments          : 
*/
	protected static WebDriver launchBrowser(String BrowserName)
	{
		WebDriver Tempdriver = null;
		switch(BrowserName.toLowerCase())
		{
			case "firefox":
			{
				Tempdriver = new FirefoxDriver();
				break;
			}
			case "ie":
			case "internetexplorer":
			{
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
				System.setProperty("webdriver.ie.driver", "BrowserServers\\IEDriverServer.exe");
				Tempdriver = new InternetExplorerDriver(capabilities);
				break;
			}
			case "chrome":
			{
				System.setProperty("webdriver.chrome.driver","BrowserServers\\chromedriver.exe");
				Tempdriver = new ChromeDriver();
				break;
			}
			default:
			{
				System.out.println("please Select the correct browser");
			}
		}
		return Tempdriver;
	}
	
	
/*
	 * Description       : To open a URL in the driver instance
	 * Method Name       : OpenUrl
	 * Input parameters  : 1)URL
	 * 					   2)driver instance
	 * Created By        :Ankit Reddy
	 * Created On        :8-May-2017     
	 * LastUpdated On    :9-May-2017
	 * Last Updated By   : Ankit Reddy
	 * Email id          :tmasters.ankit@gmail.com 
	 * Comments          : 
*/
	protected static Boolean OpenUrl(String URL,WebDriver driver)
    {
    	Boolean stepStatus = true;
    	try
    	{
    		driver.get(URL);
    		driver.manage().window().maximize();
    	}
    	catch(Exception e)
    	{
    		stepStatus = false;
    	}
    	String CurrentUrl = driver.getCurrentUrl();
    	if (!CurrentUrl.contains(URL))
    	{
    		stepStatus = false;
    	}
    	return stepStatus;
    }
	
	
/*
	 * Description       : To close all the window instances
	 * Method Name       : quitDriver
	 * Input parameters  : 1)driver instance
	 * Created By        :Ankit Reddy
	 * Created On        :8-May-2017     
	 * LastUpdated On    :9-May-2017
	 * Last Updated By   : Ankit Reddy
	 * Email id          :tmasters.ankit@gmail.com 
	 * Comments          : 
*/
	protected static Boolean quitDriver(WebDriver driver)
    {
    	Boolean stepStatus;
    	try
    	{
    		driver.quit();
    		stepStatus = true;
    	}
    	catch(Exception e)
    	{
    		stepStatus = false;
    	}
    	return stepStatus;
    }

/*
	 * Description       : To verify the existance of a webelement
	 * Method Name       : exists
	 * Input parameters  : 1)driver instance
	 * Created By        :Ankit Reddy
	 * Created On        :11-May-2017     
	 * LastUpdated On    :11-May-2017
	 * Last Updated By   : Ankit Reddy
	 * Email id          :tmasters.ankit@gmail.com 
	 * Comments          : 
*/
	protected static boolean exists(String strXpath,WebDriver driver)
	{
		List<WebElement> allitems = driver.findElements(By.xpath(strXpath));
		boolean found = false;
		if (allitems.size()>0)
		{
			found = true;
		}
		else
		{
			found = false;
		}
		return found;
	}
/*
	 * Description       : To highlight a webelement
	 * Method Name       : highlight
	 * Input parameters  : 1)driver instance
	 * Created By        :Ankit Reddy
	 * Created On        :11-May-2017     
	 * LastUpdated On    :11-May-2017
	 * Last Updated By   : Ankit Reddy
	 * Email id          :tmasters.ankit@gmail.com 
	 * Comments          : 
*/
	protected static void highlight(String StrXpath,WebDriver driver)
	{
        JavascriptExecutor js = (JavascriptExecutor)driver;
        try
        {
	        WebElement ele = driver.findElement(By.xpath(StrXpath));
	        for(int i = 1;i<=2;i++)
	        {
	        	Thread.sleep(400);
		        js.executeScript("arguments[0].style.border='solid 4px black'", ele);
				Thread.sleep(400);
		        js.executeScript("arguments[0].style.border=''", ele);
	        }
        }
        catch(Exception e)
        {
        	
        }
	}
	
/*
	 * Description       : Set Text And click on escape mainly used for calender entries
	 * Method Name       : setTextAndEscape
	 * Input parameters  : 1)driver instance
	 * Created By        :Ankit Reddy
	 * Created On        :11-May-2017     
	 * LastUpdated On    :11-May-2017
	 * Last Updated By   : Ankit Reddy
	 * Email id          :tmasters.ankit@gmail.com 
	 * Comments          : 
*/	
	protected static boolean setTextAndEscape(String StrXpath,String Value,WebDriver driver)
	{
		boolean status = false;
		try
		{
			highlight(StrXpath, driver);
			driver.findElement(By.xpath(StrXpath)).click();
			driver.findElement(By.xpath(StrXpath)).clear();
			driver.findElement(By.xpath(StrXpath)).sendKeys(Value);
			String webvalue = driver.findElement(By.xpath(StrXpath)).getAttribute("value");
			driver.findElement(By.xpath(StrXpath)).sendKeys(Keys.ESCAPE);
			Thread.sleep(500);
			if (webvalue.equalsIgnoreCase(Value))
			{
				status = true;
			}
		}
		catch(Exception e)
		{
			status = false;
		}		
		return status;
	}
/*
	 * Description       : To perform mouseHover to a webelement
	 * Method Name       : mouseHover
	 * Input parameters  : 1)WebElemwnt Xpath
	 *                     2)driver instance
	 * Created By        :Ankit Reddy
	 * Created On        :8-May-2017     
	 * LastUpdated On    :9-May-2017
	 * Last Updated By   : Ankit Reddy
	 * Email id          :tmasters.ankit@gmail.com 
	 * Comments          : 
*/	
	protected static boolean mouseHover(String strXpath,WebDriver driver)
	{
		boolean stepStatus;
		try
		{
			Actions a1 = new Actions(driver);
			a1.moveToElement(driver.findElement(By.xpath(strXpath)));
			a1.build().perform();
			stepStatus = true;
		}
		catch(Exception e)
		{
			stepStatus= false;
		}
		return stepStatus;
	}
	
	
/*
	 * Description       : To perform rightclick on a webelement
	 * Method Name       : rightClick
	 * Input parameters  : 1)WebElemwnt Xpath
	 *                     2)driver instance
	 * Created By        :Ankit Reddy
	 * Created On        :8-May-2017     
	 * LastUpdated On    :9-May-2017
	 * Last Updated By   : Ankit Reddy
	 * Email id          :tmasters.ankit@gmail.com 
	 * Comments          : 
*/	
	protected static boolean rightClick(String StrXpath,WebDriver driver)
	{
		boolean stepStatus;
		try
		{
			WebElement ele = driver.findElement(By.xpath(StrXpath));
			Actions action= new Actions(driver);
			action.contextClick(ele).build().perform();
			stepStatus = true;
		}
		catch(Exception e)
		{
			stepStatus = false;
		}		
		return stepStatus;
	}
	
	
/*
	 * Description       : To perform drag and drop operation
	 * Method Name       : dragAndDrop
	 * Input parameters  : 1)WebElemwnt Xpath
	 *                     2)driver instance
	 * Created By        :Ankit Reddy
	 * Created On        :8-May-2017     
	 * LastUpdated On    :9-May-2017
	 * Last Updated By   : Ankit Reddy
	 * Email id          :tmasters.ankit@gmail.com 
	 * Comments          : 
*/	
	protected static boolean dragAndDrop(String StrDragXpath,String StrDropXpath,WebDriver driver)
	{
		boolean stepStatus;
		try
		{
			WebElement drag = driver.findElement(By.xpath(StrDragXpath));
			WebElement drop = driver.findElement(By.xpath(StrDropXpath));
			Actions action = new Actions(driver);
			action.moveToElement(drag).dragAndDrop(drag, drop).build().perform();
			stepStatus = true;
		}
		catch(Exception e)
		{
			stepStatus = false;
		}		
		return stepStatus;
	}
	
	
/*
	 * Description       : To switch to a frame based on xpath
	 * Method Name       : switchToframe
	 * Input parameters  : 1)frame Xpath
	 *                     2)driver instance
	 * Created By        :Ankit Reddy
	 * Created On        :8-May-2017     
	 * LastUpdated On    :9-May-2017
	 * Last Updated By   : Ankit Reddy
	 * Email id          :tmasters.ankit@gmail.com 
	 * Comments          : 
*/	
	protected static WebDriver switchToframe(String StrXpath,WebDriver driver)
	{
		try
		{
			WebElement frameref = driver.findElement(By.xpath(StrXpath));
			driver.switchTo().frame(frameref);
		}
		catch(Exception e)
		{
		}	
		return driver;
	}
/*
	 * Description       : To switch to a window based on title
	 * Method Name       : switchToWindowByTitle
	 * Input parameters  : 1)window title
	 *                     2)driver instance
	 * Created By        :Ankit Reddy
	 * Created On        :8-May-2017     
	 * LastUpdated On    :9-May-2017
	 * Last Updated By   : Ankit Reddy
	 * Email id          :tmasters.ankit@gmail.com 
	 * Comments          : 
*/	
	protected static WebDriver switchToWindowByTitle(String StrTitle,WebDriver driver)
	{
		try
		{
			Set<String> allhandles = driver.getWindowHandles();
			for(String h1:allhandles)
			{
				driver.switchTo().window(h1);
				String CurrentURL = driver.getTitle();
				if (CurrentURL.contains(StrTitle))
				{
					break;
				}
			}
		}
		catch(Exception e)
		{
		}	
		return driver;
	}
/*
	 * Description       : To switch to a window based on URL
	 * Method Name       : switchToWindowByURL
	 * Input parameters  : 1)window URL
	 *                     2)driver instance
	 * Created By        :Ankit Reddy
	 * Created On        :8-May-2017     
	 * LastUpdated On    :9-May-2017
	 * Last Updated By   : Ankit Reddy
	 * Email id          :tmasters.ankit@gmail.com 
	 * Comments          : 
*/	
	protected static WebDriver switchToWindowByURL(String StrURL,WebDriver driver)
	{

		try
		{
			Set<String> allhandles = driver.getWindowHandles();
			for(String h1:allhandles)
			{
				driver.switchTo().window(h1);
				String CurrentURL = driver.getCurrentUrl();
				if (CurrentURL.contains(StrURL))
				{
					break;
				}
			}
		}
		catch(Exception e)
		{

		}	
		return driver;
	}
/*
	 * Description       : To close a window based on title
	 * Method Name       : closeWindowByTitle
	 * Input parameters  : 1)window title
	 *                     2)driver instance
	 * Created By        :Ankit Reddy
	 * Created On        :8-May-2017     
	 * LastUpdated On    :9-May-2017
	 * Last Updated By   : Ankit Reddy
	 * Email id          :tmasters.ankit@gmail.com 
	 * Comments          : 
*/	
	protected static boolean closeWindowByTitle(String StrTitle,WebDriver driver)
	{
		boolean stepStatus = false;
		try
		{
			Set<String> allhandles = driver.getWindowHandles();
			for(String h1:allhandles)
			{
				driver.switchTo().window(h1);
				String CurrentURL = driver.getTitle();
				if (CurrentURL.contains(StrTitle))
				{
					driver.close();
					stepStatus = true;
					break;
				}
			}
		}
		catch(Exception e)
		{
			stepStatus = false;
		}	
		return stepStatus;
	}
/*
	 * Description       : To close a window based on url
	 * Method Name       : closeWindowByURL
	 * Input parameters  : 1)window URL
	 *                     2)driver instance
	 * Created By        :Ankit Reddy
	 * Created On        :8-May-2017     
	 * LastUpdated On    :9-May-2017
	 * Last Updated By   : Ankit Reddy
	 * Email id          :tmasters.ankit@gmail.com 
	 * Comments          : 
*/	
	protected static boolean closeWindowByURL(String StrURL,WebDriver driver)
	{
		boolean stepStatus = false;
		try
		{
			Set<String> allhandles = driver.getWindowHandles();
			for(String h1:allhandles)
			{
				driver.switchTo().window(h1);
				String CurrentURL = driver.getCurrentUrl();
				if (CurrentURL.contains(StrURL))
				{
					driver.close();
					stepStatus = true;
					break;
				}
			}
		}
		catch(Exception e)
		{
			stepStatus = false;
		}	
		return stepStatus;
	}
	
/*	
	 * Description       : To set max time out time for the driver instance
	 * Method Name       : SetImplicitWait
	 * Input parameters  : 1)Time duration in seconds
	 *                     2)driver instance
	 * Created By        :Ankit Reddy
	 * Created On        :8-May-2017     
	 * LastUpdated On    :9-May-2017
	 * Last Updated By   : Ankit Reddy
	 * Email id          :tmasters.ankit@gmail.com 
	 * Comments          : 
*/
	protected static Boolean SetImplicitWait(int TimeInSec,WebDriver driver)
    {
        Boolean stepStatus = true;
        try
        {
        	driver.manage().timeouts().implicitlyWait(TimeInSec, TimeUnit.SECONDS);
        }
        catch (Exception e)
        {
            stepStatus = false;
        }
        return stepStatus;
    }
	
	
/*
	 * Description       : To wait for specified time duration{Static Wait}
	 * Method Name       : wait
	 * Input parameters  : 1)Time In Seconds
	 * Created By        :Ankit Reddy
	 * Created On        :8-May-2017     
	 * LastUpdated On    :9-May-2017
	 * Last Updated By   : Ankit Reddy
	 * Email id          :tmasters.ankit@gmail.com 
	 * Comments          : 
*/	
	protected static boolean wait(int timeInSec)
	{
		boolean stepStatus;
		try
		{
			Thread.sleep(timeInSec*1000);
			stepStatus = true;
		}
		catch(Exception e)
		{
			stepStatus = false;
		}		
		return stepStatus;
	}
	
	
/*
	 * Description       : To wait for specified time duration{Static Wait}
	 * Method Name       : dynamicWait
	 * Input parameters  : 1)WebElemwnt Xpath
	 *                     2)driver Instance
	 *                     3)Wait Time In Seconds
	 * Created By        :Ankit Reddy
	 * Created On        :8-May-2017     
	 * LastUpdated On    :9-May-2017
	 * Last Updated By   : Ankit Reddy
	 * Email id          :tmasters.ankit@gmail.com 
	 * Comments          : 
*/	
	protected static boolean dynamicWait(String strxpath,WebDriver driver,int waittimeinsec)
	{
			boolean status = false;
			int counter = 0;
			while(counter<=waittimeinsec)
			{
				try
				{
					driver.findElement(By.xpath(strxpath));
					status = true;
					break;
				}
				catch(Exception e)
				{
					try 
					{
						Thread.sleep(1000);
					} catch (Exception e1) 
					{
						
					}
					counter = counter+ 1;
				}
			 }
			 if(counter>waittimeinsec)
			 {
				 status = false;
			 }
			 return status;
			
	}


/*
	 * Description       : To upload file based on .exe file
	 * Method Name       : AutoITFileUpload
	 * Input parameters  : 1).exe file path
	 *                     2)Destination path & file name
	 * Created By        :Ankit Reddy
	 * Created On        :8-May-2017     
	 * LastUpdated On    :9-May-2017
	 * Last Updated By   : Ankit Reddy
	 * Email id          :tmasters.ankit@gmail.com 
	 * Comments          : 
*/
	protected static boolean AutoITFileUpload(String exeFileName,String Filepath)
    {
        Boolean stepStatus = true;
        try
        {
        	Runtime.getRuntime().exec("AuotITFiles\\" + exeFileName + ".exe" + " " + Filepath);
        }
        catch (Exception e)
        {
            stepStatus = false;
        }
        return stepStatus;
    }
/*
	 * Description       : To upload file based on string path
	 * Method Name       : RobotKeysFileUpload
	 * Input parameters  : 1)Destination path & file name
	 * Created By        :Ankit Reddy
	 * Created On        :8-May-2017     
	 * LastUpdated On    :9-May-2017
	 * Last Updated By   : Ankit Reddy
	 * Email id          :tmasters.ankit@gmail.com 
	 * Comments          : 
*/
	protected static boolean RobotKeysFileUpload(String Filepath)
	{
		boolean stepstatus;
		try
		{
		 	wait(2);
		   StringSelection selection = new StringSelection(Filepath);
	       Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	       clipboard.setContents(selection,selection);

			
			Robot robo = new Robot();   
			robo.keyPress(KeyEvent.VK_CONTROL);
	     	robo.keyPress(KeyEvent.VK_V);
	      	robo.keyRelease(KeyEvent.VK_V);
	      	robo.keyRelease(KeyEvent.VK_CONTROL);

	  		wait(2);
	      	robo.keyPress(KeyEvent.VK_ENTER);
	      	robo.keyRelease(KeyEvent.VK_ENTER);
			wait(1);
	    	robo.keyPress(KeyEvent.VK_ENTER);
	      	robo.keyRelease(KeyEvent.VK_ENTER);
	      	stepstatus = true;
		}
		catch(Exception e)
		{
			stepstatus = false;
		}
		return stepstatus;
	}
/*
	 * Description       : To verify if alert exist
	 * Method Name       : AlertExist
	 * Input parameters  : 1)driver instance
	 * Created By        :Ankit Reddy
	 * Created On        :8-May-2017     
	 * LastUpdated On    :9-May-2017
	 * Last Updated By   : Ankit Reddy
	 * Email id          :tmasters.ankit@gmail.com 
	 * Comments          : 
*/	
	protected static boolean AlertExist(WebDriver driver)
	{
		boolean stepstatus;
		try
		{
		 	driver.switchTo().alert();
	      	stepstatus = true;
		}
		catch(Exception e)
		{
			stepstatus = false;
		}
		return stepstatus;
	}
	
/*
	 * Description       : To get text of the alert
	 * Method Name       : alertGetText
	 * Input parameters  : 1)driver instance
	 * Created By        :Ankit Reddy
	 * Created On        :8-May-2017     
	 * LastUpdated On    :9-May-2017
	 * Last Updated By   : Ankit Reddy
	 * Email id          :tmasters.ankit@gmail.com 
	 * Comments          : 
*/		
	protected static String alertGetText(WebDriver driver)
	{
		String StrAlertText = "";
		try
		{
		 	Alert a1 = driver.switchTo().alert();
		 	StrAlertText = a1.getText();
		}
		catch(Exception e)
		{
			
		}
		return StrAlertText;
	}
/*
	 * Description       : To accept the alert
	 * Method Name       : acceptAlert
	 * Input parameters  : 1)driver instance
	 * Created By        :Ankit Reddy
	 * Created On        :8-May-2017     
	 * LastUpdated On    :9-May-2017
	 * Last Updated By   : Ankit Reddy
	 * Email id          :tmasters.ankit@gmail.com 
	 * Comments          : 
*/	
	protected static boolean acceptAlert(WebDriver driver)
	{
		boolean stepstatus;
		try
		{
		 	Alert a1 = driver.switchTo().alert();
		 	a1.accept();
	      	stepstatus = true;
		}
		catch(Exception e)
		{
			stepstatus = false;
		}
		return stepstatus;
	}
/*
	 * Description       : To dismiss the alert
	 * Method Name       : dismissAlert
	 * Input parameters  : 1)driver instance
	 * Created By        :Ankit Reddy
	 * Created On        :8-May-2017     
	 * LastUpdated On    :9-May-2017
	 * Last Updated By   : Ankit Reddy
	 * Email id          :tmasters.ankit@gmail.com 
	 * Comments          : 
*/	
	protected static boolean dismissAlert(WebDriver driver)
	{
		boolean stepstatus;
		try
		{
		 	Alert a1 = driver.switchTo().alert();
		 	a1.dismiss();
	      	stepstatus = true;
		}
		catch(Exception e)
		{
			stepstatus = false;
		}
		return stepstatus;
	}
	
/*
	 * Description       : To log events to Extent Reports and Console
	 * Method Name       : logEvent
	 * Input parameters  : 1)StepStatus
	 *                     2)Pass Log Message
	 *                     3)Fail Log Message
	 *                     4)driver instance
	 *                     5)takeScrrenshot flag
	 * Created By        :Ankit Reddy
	 * Created On        :8-May-2017     
	 * LastUpdated On    :9-May-2017
	 * Last Updated By   : Ankit Reddy
	 * Email id          :tmasters.ankit@gmail.com 
	 * Comments          : 
*/	
	@SuppressWarnings("static-access")
	public static void logEvent(boolean stepstatus,String plog,String flog,WebDriver driver,boolean falgTakeScreenshot)
    {
		if (falgTakeScreenshot == false)
		{
			if(stepstatus)
			{
				TestReport.log(LogStatus.INFO.PASS, plog);
				System.out.println("	<<Pass>> " + plog);
			}
			else
			{
				TestReport.log(LogStatus.INFO.FAIL, flog);
				System.out.println("	<<Fail>> " + flog);
			}
		}
		else
		{
			if(stepstatus)
			{
				String ScreenShotPath = getscreenshot(driver);
				String LoggerScreenShotpath = TestReport.addScreenCapture(ScreenShotPath);
				TestReport.log(LogStatus.INFO.PASS, plog + LoggerScreenShotpath);
				System.out.println("	<<Pass>> " + plog);
			}
			else
			{
				String ScreenShotPath = getscreenshot(driver);
				String LoggerScreenShotpath = TestReport.addScreenCapture(ScreenShotPath);
				TestReport.log(LogStatus.INFO.FAIL, flog + LoggerScreenShotpath);
				System.out.println("	<<Fail>> " + flog);
			}
		}
		Assert.assertTrue(stepstatus);
    }
/*
	 * Description       : get Current Date and time stamp in the form of string
	 * Method Name       : getTimeStamp
	 * Input parameters  : 1)StepStatus
	 *                     2)Pass Log Message
	 *                     3)Fail Log Message
	 *                     4)driver instance
	 *                     5)takeScrrenshot flag
	 * Created By        :Ankit Reddy
	 * Created On        :8-May-2017     
	 * LastUpdated On    :9-May-2017
	 * Last Updated By   : Ankit Reddy
	 * Email id          :tmasters.ankit@gmail.com 
	 * Comments          : 
*/	
	public static String getTimeStamp() 
	{
		DateFormat dateTimeInstance = SimpleDateFormat.getDateTimeInstance();
	    String DateTimeStamp = dateTimeInstance.format(Calendar.getInstance().getTime());
	    DateTimeStamp = DateTimeStamp.replace(",", "");
	    DateTimeStamp = DateTimeStamp.replace(" ", "_");
	    DateTimeStamp = DateTimeStamp.replace(":", "-");
		return  DateTimeStamp;
	}
/*
	 * Description       : take scrrenshot of the current driver istance.
	 * Method Name       : getscreenshot
	 * Input parameters  : 1)driver instance
	 * Created By        :Ankit Reddy
	 * Created On        :8-May-2017     
	 * LastUpdated On    :9-May-2017
	 * Last Updated By   : Ankit Reddy
	 * Email id          :tmasters.ankit@gmail.com 
	 * Comments          : 
*/	
	public static String getscreenshot(WebDriver driver)
	{
		  try
		  {
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			StackTraceElement stackTraceElement = stackTraceElements[3];
			String CurrentTestCase = stackTraceElement.getMethodName();
			CurrentTestCase = CurrentTestCase.replace(".java", "");
				
			String ScreenshotName;
			DateFormat dateTimeInstance = SimpleDateFormat.getDateTimeInstance();
			String DateTimeStamp = dateTimeInstance.format(Calendar.getInstance().getTime());
			DateTimeStamp = DateTimeStamp.replace(",", "");
			DateTimeStamp = DateTimeStamp.replace(" ", "_");
			DateTimeStamp = DateTimeStamp.replace(":", "_");
			ScreenshotName =  CurrentTestCase + "_"+ DateTimeStamp;
			
			TakesScreenshot ts =(TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String Dest = ScreenshotsFolderPath + "//" + ScreenshotName + ".png";
			File destination = new File(Dest);
			FileUtils.copyFile(source, destination);
			return Dest;
		  }
		  catch(Exception e)
		  {
			  return e.getMessage();
		  }
	}
/*
	 * Description       : Create a folder in results with date and time stamp
	 * Method Name       : createresultsfolder
	 * Input parameters  : NA
	 * Created By        :Ankit Reddy
	 * Created On        :8-May-2017     
	 * LastUpdated On    :9-May-2017
	 * Last Updated By   : Ankit Reddy
	 * Email id          :tmasters.ankit@gmail.com 
	 * Comments          : 
*/	
	public static String createresultsfolder() 
	{
		DateFormat dateTimeInstance = SimpleDateFormat.getDateTimeInstance();
	    String DateTimeStamp = dateTimeInstance.format(Calendar.getInstance().getTime());
	    DateTimeStamp = DateTimeStamp.replace(",", "");
	    DateTimeStamp = DateTimeStamp.replace(" ", "_");
	    DateTimeStamp = DateTimeStamp.replace(":", "-");
	    ResultsFolderPath = System.getProperty("user.dir") + "//" + ResultsFolderPath;
		File dir = new File(ResultsFolderPath + "//" +  DateTimeStamp);
		dir.mkdir();
		File dir2 = new File(ResultsFolderPath + "//" +  DateTimeStamp + "//Screenshots");
		dir2.mkdir();
		return ResultsFolderPath + "//" + DateTimeStamp;
	}
}
