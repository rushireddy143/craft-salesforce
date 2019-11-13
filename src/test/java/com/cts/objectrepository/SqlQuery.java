package com.cts.objectrepository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.cts.automationcore.FilePath;
import com.cts.components.Component;

/**
 * 
 * @author 
 *@chnageLog mm/dd/yy Description of change
 */

public class SqlQuery extends Component {
	
	//this method finds the SQL query needed by a test according to key-value pairs from properties file
	
	public static String FindQuery(String strQueryName) {
		
		Map<String,String> map=new HashMap<String,String>();
		FileInputStream inputStream=null;
		Properties prop=new Properties();
		String propFileName=FilePath.getFilepath("queries.properties");
		
		try{
			inputStream =new FileInputStream(propFileName);
		}catch(FileNotFoundException e2){
			System.out.println(" Console Message: The Queries Property File not Found");
		}
		try{
			prop.load(inputStream);
		}catch(IOException e1){
			System.out.println("Console Message: The Queries Property File load error");
		}
		for(String s:prop.stringPropertyNames()){
			String val=prop.getProperty(s);
			map.put(s,val);
		}
		try{
			for(Map.Entry<String, String>entry:map.entrySet()){
				if(entry.getKey().equals(strQueryName)){
					return entry.getValue();
				}
			}
		}
		catch(Exception e){
			System.out.println("Console message: Query not found as error at query identification level");
		}
		return null;
	}

}
