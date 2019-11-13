package com.cts.automationcore;

import java.io.File;

public class FilePath {
	
	public static String getFilepath(String fileName)
	{
		String filepath = null;
		String frameworkpath = System.getProperty("user.dir")+System.getProperty("file.separator")
				+"src" + System.getProperty("file.separator")
				+"main" + System.getProperty("file.separator")
				+"resources";
		
		String testpath = System.getProperty("user.dir")+System.getProperty("file.separator")
				+"src" + System.getProperty("file.separator")
				+"test" + System.getProperty("file.separator")
				+"resources";
		
		File fileContent;
		
		filepath = frameworkpath + System.getProperty("file.separator") + "Configuration" +System.getProperty("file.separator") +fileName;
		
		  fileContent = new File(filepath);
		  
		  if(fileContent.exists()){
			  //System.out.println(filepath);
			  return filepath;
		  }
		filepath = frameworkpath + System.getProperty("file.separator") + "Drivers" +System.getProperty("file.separator") + fileName;
		fileContent = new File(filepath);
		
		if(fileContent.exists()){
			//System.out.println(filepath);
			return filepath;
		}
		//
		
		
		filepath = testpath + System.getProperty("file.separator") + "ObjectRepository" +System.getProperty("file.separator") +fileName;
		
		  fileContent = new File(filepath);
		  
		  if(fileContent.exists()){
			  //System.out.println(filepath);
			  return filepath;
		  }
		filepath = testpath + System.getProperty("file.separator") + "TestData" +System.getProperty("file.separator") + fileName;
		fileContent = new File(filepath);
		
		if(fileContent.exists()){
			//System.out.println(filepath);
			return filepath;
		}
		
		
		
		
		
		
		
		
		System.out.println("file not found :" + filepath);
		
		return null;
	
	}
	
	public static String getResultFolderpath()
	{
		return System.getProperty("user.dir")+System.getProperty("file.separator") + "Reports";
	}
	
	public static String setZipFile()
	{
		return System.getProperty("user.dir")+System.getProperty("file.separator") + "JPMReports.zip";
	}

}
