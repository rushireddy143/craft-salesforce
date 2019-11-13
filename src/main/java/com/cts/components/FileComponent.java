package com.cts.components;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

public class FileComponent extends Component {

	
	public Boolean isFileExists(String filePath)
	{
	     File file= new File(filePath);
		try
		{
		if(file.exists())
		{
		narrate("File Exists","Pass");
		return true;
		}
		else{
			narrate ("File Does not exists at the given Path","Pass");
			return false;
		}
		}catch(Exception e)
		{
			e.printStackTrace();
			narrate("Exception thrown","Fail");
			return false;	
		}
	}
	
	public void DeleteFile(String filePath) throws Exception
	{
		try
		{
			File file= new File(filePath);
			if(file.delete())
			{
				narrate("File Deleted Successfully","pass");
			}
			else
			{
				narrate("File Could not be deleted","Fail");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			narrate("File could not be deleted","Fail");

		}
	}
	
	
	public void copyFileToDir(String srcfilePath, String destDirPath)
	{
		try
		{
		File srcFile=new File(srcfilePath);
		File destDir= new File(destDirPath);
		
		FileUtils.copyFileToDirectory(srcFile, destDir, false);

		narrate("File is copied from source File "+srcfilePath+" to destination Directory "+destDirPath,"Pass");
		}catch(Exception e){
			e.printStackTrace();
			narrate("File is not copied from source File "+srcfilePath+" to destination Directory+"+destDirPath,"Fail");
		}
	}


	public void copyFiletoFile(String srcfilePath, String desfilePath)
	{
		try
		{
		File srcFile= new File(srcfilePath);
		File destFile= new File(desfilePath);

		FileUtils.copyFile(srcFile, destFile, false);
		
		narrate("File is copied from source File"+srcfilePath+" to destination File +"+desfilePath,"pass"); 
		}catch(Exception e)
		{
			e.printStackTrace();
			narrate("Fiel is not copied from source File " +srcfilePath+" to destination File +"+desfilePath, "Fail");
		}
	}


	public String readProperty(String filename, String PropertyName)
	{
		String temp="";
		try
		{
		Properties prop= new Properties();
		FileInputStream fis= new FileInputStream(filename);
		prop.load(fis);
		temp=prop.getProperty(PropertyName);
		//narrate("Property "+PropertyName+" has been read form file "+filename +" successfully," +"Pass");
		}catch(Exception e){
			e.printStackTrace(); 
			narrate("Property "+PropertyName+"has NOT been read form file "+filename +"successfully  ", "Fail");
				}
		return temp;
		}
}



