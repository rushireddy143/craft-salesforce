package com.cts.components;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.cts.automationcore.FilePath;

public class Pagintion extends Component {

	String parentId=null;
	String totalRecords=null;
	
	/**
	 * Find the pagination control based on the xpath
	 * @param xpath
	 * @return true if found,else false
	 */
	public boolean findPaginationControl(){
	
	Map<String,String> map = new HashMap<String,String>();
	FileInputStream inputStream = null;
	Properties prop= new Properties();
	String propFileName = FilePath.getFilepath("tables.properties");
	
	try{
		inputStream = new FileInputStream(propFileName);
	}catch (FileNotFoundException e2) {
	//	reportEvent.Status("Console Message","The Tables property file not found","FAIL");
	}
	try{
		prop.load(inputStream);
	}catch (IOException e1) {
	//	reportEvent.Status("Console Message","The Tables property file not found","FAIL");
	}
	for (String s :prop.stringPropertyNames()){
		String val =prop.getProperty(s);
		map.put(s,val);
	}
	try{
		for(Map.Entry<String, String> entry : map.entrySet()){
			 if(entry.getKey().equals("PaginationControl")){
				 parentId=entry.getValue();
				 break;
			 }
		}
	}
	catch(Exception e){
	//	reportEvent.Status("Console Message","Table NOT Found as Error at xpath identification Level","FAIL");
	}
	return true;
	}
	public String totalRecords(){
		findPaginationControl();
		try{
			StackTraceElement el=new Exception("DEBUG").getStackTrace()[1];
			WebElement element = (WebElement) driver.findElement(By.xpath(parentId +"//tr[1]/td/spam[2]"));
		//	reportEvent.Status(el.getClassName().toString().replace("com.uhg.vbr.panels.", "")+"Page","Records are available in table","Pass");
			return element.getText();
		}catch(NoSuchElementException e)
		{
			return null;
		}
	}
	public int totalPages(){
		StackTraceElement el=new Exception("DEBUG").getStackTrace()[1];
		if(totalRecords().trim().equals(null)){
		//	reportEvent.Status(el.getClassName().toString().replace("com.uhg.vbr.panels.", "")+"Page","No data page available","Pass");
			return 0;
		}else if(Integer.parseInt(totalRecords().trim())<=10){
		//	reportEvent.Status(el.getClassName().toString().replace("com.uhg.vbr.panels.", "")+"Page","Could not navigate page as only one page available","Pass");
			return 1;
		}
		else {
			int totalRecorsPerPage=10;
		//	reportEvent.Status(el.getClassName().toString().replace("com.uhg.vbr.panels.", "")+"Page","Pagination enabled","Pass");
			return Integer.parseInt(totalRecords().trim())+totalRecorsPerPage-1/totalRecorsPerPage;
	}
}
}