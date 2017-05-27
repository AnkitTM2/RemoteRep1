package com.library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelLibrary extends ListenerClass
{
	public static String getdata(String FieldName)
	{
		//abcd
		String StrValue = "";
		try
		{
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			StackTraceElement stackTraceElement = stackTraceElements[2];
			String ClassName = stackTraceElement.getMethodName();
			
			
			File fi=new File("RunManager.xlsm");
			FileInputStream fin=new FileInputStream(fi);
			
			XSSFWorkbook wrk=new XSSFWorkbook(fin);
			XSSFSheet	shet=wrk.getSheet("TestData");
			
			Row rw=shet.getRow(1);
			
			for(int i=0;i<=shet.getLastRowNum();i++)
			{
				rw=shet.getRow(i+1);
				if(rw==null || rw.getCell(1)==null)
				continue;
	
				String TestCaseName =rw.getCell(1).getStringCellValue();
				if(TestCaseName.equals(ClassName))
				{
					for(int j=1;j<rw.getLastCellNum();j++)
					{
						Cell cl=rw.getCell(j);
						if(cl==null)
						continue;
						String val=cl.getStringCellValue();
						String arrval[] = val.split(":=");
						
						String StrFieldName = arrval[0];
						if(StrFieldName.equalsIgnoreCase(FieldName))
						{
							StrValue = arrval[1];
							break;
						}
					}
				}
			}
			
			wrk.close();
			fin.close();
		}
		catch(Exception e)
		{
			
		}
		return StrValue;
	}
	public static String putdata(String FieldName,String Value)
	{
		String StrValue = "";
		try
		{
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			StackTraceElement stackTraceElement = stackTraceElements[2];
			String ClassName = stackTraceElement.getMethodName();
			
			
			File fi=new File("RunManager.xlsm");
			FileInputStream fin=new FileInputStream(fi);
			
			XSSFWorkbook wrk=new XSSFWorkbook(fin);
			XSSFSheet	shet=wrk.getSheet("TestData");
			
			Row rw=shet.getRow(1);
			
			for(int i=0;i<=shet.getLastRowNum();i++)
			{
				rw=shet.getRow(i+1);
				if(rw==null || rw.getCell(1)==null)
				continue;
	
				String TestCaseName =rw.getCell(1).getStringCellValue();
				if(TestCaseName.equals(ClassName))
				{
					for(int j=1;j<rw.getLastCellNum();j++)
					{
						Cell cl=rw.getCell(j);
						if(cl==null)
						continue;
						String val=cl.getStringCellValue();
						String arrval[] = val.split(":=");
						
						String StrFieldName = arrval[0];
						if(StrFieldName.equalsIgnoreCase(FieldName))
						{
							cl.setCellValue(Value);
							break;
						}
					}
				}
			}
			fin.close();
			FileOutputStream fos = new FileOutputStream(fi);
			wrk.write(fos);
			wrk.close();
		}
		catch(Exception e)
		{
			
		}
		return StrValue;
	}
	public static boolean getExecuteStatus(String mthName)
	{
		boolean flag=false;
		
		try
		{
			XSSFWorkbook wrk;
			XSSFSheet shet;
			XSSFRow rww;
			XSSFCell cll;
			
			File fi=new File("RunManager.xlsm");
			FileInputStream fin=new FileInputStream(fi);
			
			wrk=new XSSFWorkbook(fin);
			shet=wrk.getSheet("ExecutionController");
			
			rww=shet.getRow(1);
			for(int i=0;i<=shet.getLastRowNum();i++)
			{
				rww=shet.getRow(i+1);
				if(rww==null || rww.getCell(0)==null )
				continue;
				cll=rww.getCell(1);
				if(cll.getStringCellValue().equals(mthName))
				{
					cll=rww.getCell(3);
					String result=cll.getStringCellValue();
					flag=Boolean.parseBoolean(result);
				}
				
			}
			wrk.close();
			fin.close();
		}
		catch(Exception e)
		{
			
		}
		return flag;
	}
	
	public static int getPriority(String mthName)
	{
		int priority=0;
		
		try
		{
			XSSFWorkbook wrk;
			XSSFSheet shet;
			XSSFRow rww;
			XSSFCell cll;
			
			File fi=new File("RunManager.xlsm");
			FileInputStream fin=new FileInputStream(fi);
			
			wrk=new XSSFWorkbook(fin);
			shet=wrk.getSheet("ExecutionController");
			rww=shet.getRow(1);
			for(int i=0;i<=shet.getLastRowNum();i++)
				
			{
				rww=shet.getRow(i+1);
				if(rww==null || rww.getCell(4)==null)
				continue;
				cll=rww.getCell(1);
				if(cll.getStringCellValue().equals(mthName))
				{
					cll=rww.getCell(4);
					String result=cll.getStringCellValue();
					priority=Integer.parseInt(result);
				}
			}
			
			wrk.close();
			fin.close();
		}
		catch(Exception e)
		{
			
		}
		return priority;
		
	}
	
	public static String getDescription(String mthName)
	{
		String tcDdescription=null;
		
		try
		{
			XSSFWorkbook wrk;
			XSSFSheet shet;
			XSSFRow rww;
			XSSFCell cll;
			
			File fi=new File("RunManager.xlsm");
			FileInputStream fin=new FileInputStream(fi);
			
			wrk=new XSSFWorkbook(fin);
			shet=wrk.getSheet("ExecutionController");
			
			rww=shet.getRow(1);
			
			for(int i=0;i<=shet.getLastRowNum();i++)
				
			{
				rww=shet.getRow(i+1);
				if(rww==null || rww.getCell(2)==null)
				continue;
				cll=rww.getCell(1);
				if(cll.getStringCellValue().equals(mthName))
				{
					cll=rww.getCell(2);
					 tcDdescription=cll.getStringCellValue();
					
				}
				
			}
			
			wrk.close();
			fin.close();
		}
		catch(Exception e)
		{
			
		}
		
		return tcDdescription;
		
	}
}
