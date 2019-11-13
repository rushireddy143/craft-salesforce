package com.cts.components;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.cts.automationcore.FilePath;

/**
 * 
 * @author 
 * @changeLog mnt/date/year modified to have more validation for smoke test
 */
public class Table extends Component {

	String parentId ="";
	int colNum=0;
	
	//this method finds the table xpaths according to key-value pairs from properties file
	
	public boolean findTable(String strTableName){
		Map<String, String> map = new HashMap<String, String>();
		FileInputStream inputStream=null;
		Properties prop = new Properties();
		String propFileName=FilePath.getFilepath("tables.properties");
		
		try {
			inputStream  =new FileInputStream(propFileName);
		}catch(FileNotFoundException e2) {
		//	reportEvent.Status("Console Message","The Tables Property File not Found","FAIL");
		}
		
		try{
			prop.load(inputStream);
		}
		catch (IOException e1) {
		//	reportEvent.Status("Console Message","The Tables Property File Load Error","FAIL");
		}
		
		for (String s: prop.stringPropertyNames()){
			String val=prop.getProperty(s);
			map.put(s,val);
		}
		try {
			for(Map.Entry<String, String> entry:map.entrySet()){
				if(entry.getKey().equals(strTableName)){
					parentId=entry.getValue();
					break;
				}
			}}
		catch(Exception e){
		//	reportEvent.Status("Console Message", "Table NOT Found as Error at XPath Identification Level","FAIL");
		}
		 return true;
		 
	}
	
	  /**
	   * Checks if the given column exists
	   * @param colNam
	   * @return true if found, else false
	   */
	
	public boolean isColumnPresent(String strTableName,String colName){
		boolean blnflag=false;
		findTable(strTableName);
		try {
			List<WebElement> element1=driver.findElements(By.xpath(parentId+"/thead//th"));
			Thread.sleep(3000);
			for(int i=1; i<element1.size()+1;i++){
				if(driver.findElement(By.xpath(parentId + "/thead//th["+i+"]/span[2]")).getText().trim().equalsIgnoreCase(colName)){
					StackTraceElement el= new Exception("DEBUG").getStackTrace()[1];
			//		reportEvent.Status(el.getClassName().toString().replace("com.uhg.vbr.panels.","")+" Page","Table Column:"+colName+"is Found","Pass");
					colNum=i;
					blnflag=true;
					break;
				}
			}
			
			List<WebElement> element2=driver.findElements(By.xpath(parentId+"/tr/th"));
			for(int i=1;i<element1.size()+1;i++){
				if(driver.findElement(By.xpath(parentId + "/thead//th["+i+"]/span[2]")).getText().trim().equalsIgnoreCase(colName)){
				StackTraceElement el= new Exception("DEBUG").getStackTrace()[1];
				//reportEvent.Status(el.getClassName().toString().replace("com.uhg.vbr.panels.","")+" Page","Table Column:"+colName+"is Found","Pass");
				colNum=i;
				blnflag=true;
				break;
			}
			}}
	catch(Exception e){}
	if(!blnflag)
	{
		//reportEvent.Status("Console message", "Table Column:" +colName+" is not found","FAIL");
	}
	return blnflag;
	}
	
	/**
	 * Checks if the given column exists in dual header table
	 * @param colName
	 * @return true if found,else false
	 */
	public boolean isColumnPresentDualHeaderTable(String strTableName,String colName){
		boolean blnflag=false;
		findTable(strTableName);
		try{
			List<WebElement> elements=driver.findElements(By.xpath(parentId+"//thead/tr[1]/th"));
			for(int i=1; i<elements.size();i++){
				if((driver.findElement(By.xpath(parentId + "//thead//th["+i+"]/span[2]")).getText().trim()).equalsIgnoreCase(colName)){
					colNum=i;
				//	reportEvent.Status("Column Validation","Table Column: '"+colName+"' is Found","PASS");
					blnflag=true;
					break;
				}
			}
			
			List<WebElement> element1=driver.findElements(By.xpath(parentId+"//thread/tr[2]/th"));
			for(int i=1;i<element1.size()+1;i++){
				if((driver.findElement(By.xpath(parentId + "//thead/tr[2]/th["+i+"]/span[2]")).getText().trim()).equalsIgnoreCase(colName)){
					colNum=i;
				//	reportEvent.Status("COlumn Validation","Table Column:"+colName+"is Found","Pass");
				
				blnflag=true;
				break;
			}
			}}
	catch(Exception e){}
	if(!blnflag)
	{
	//	reportEvent.Status("Console message", "Table Column:" +colName+" is not found","FAIL");
	}
	return blnflag;
	}
				
	/**
	 * Gets the cell data based on row num and colName
	 * @param rowNum
	 * @param colName 
	 * @return cell value if found,else empty string
	 */
	public String getCellData(String strTableName,int rowNum,String colName){
		StackTraceElement el= new Exception("DEBUG").getStackTrace()[1];
		try{
			if(isColumnPresent(strTableName,colName)){
				String uniqueCellID=parentId +"//tr["+rowNum+"]";
				return (driver.findElement(By.xpath(uniqueCellID)).getText());
			}else{
				return"";
			}
			
		}catch(Exception e){
		//reportEvent.Status(el.getClassName().toString().replace("com.uhg.picts.panels.","")+ "Page"  ,"Invalid Row Number","FAIL");
	return "";
		}
	}
	
	/**
	 * Gets the cell data based on rowNum and colName
	 * @param rowNum
	 * @param colName 
	 * @return cell value if found,else empty string
	 */
	public String getData(int rowNum,int colNum){
		StackTraceElement el= new Exception("DEBUG").getStackTrace()[1];
		try{
				String uniqueCellID=parentId +"//tr["+rowNum+"]//td["+colNum+"]";
				return (driver.findElement(By.xpath(uniqueCellID)).getText());
			} catch(Exception e){
	//	reportEvent.Status(el.getClassName().toString().replace("com.uhg.picts.panels.","")+ "Page"  ,"Invalid Row Number","FAIL");
	return "";
		}
	}
	
	/**
	 * Checks if the given column exists in dual header table
	 * @param colName
	 * @return true if found,else false
	 */
	public boolean isRecordPresent(String strTableName,String colName,String cellValue){
		boolean blnflag=false;
		StackTraceElement el= new Exception("DEBUG").getStackTrace()[1];
		if(isColumnPresent(strTableName,colName)){
		String uniqueCellID=parentId +"//tr["+colNum+"]";
			List<WebElement> items=driver.findElements(By.xpath(uniqueCellID));
			for(WebElement e1:items){
				if(e1.getText().contains(cellValue)){
					blnflag=true;
				//	reportEvent.Status(el.getClassName().toString().replace("com.uhg.picts.panels.","")+ "Page","Data'"+cellValue+"' found in contact table","PASS");
					break;
				}
			}
		}
	return blnflag;
	}
	
	/**
	 * get the num of rows in the grid
	 * @return in
	 */
	public int getRowCount(String strTableName){
		findTable(strTableName);
		try{
			List<WebElement> elements=driver.findElements(By.xpath(parentId+"/tbody/tr"));
			return elements.size();
	}catch(Exception e){
		return -1;
		}
	}
	/**
	 * get the num of rows in the grid
	 * @return in
	 */
	public int getCaseSearchRowCount(){
		try{
			parentId="//*[@id='caseSearchResultForm:caseSearchListDataTableId data']";
			List<WebElement> elements=driver.findElements(By.xpath(parentId+"/tbody/tr"));
			return elements.size();
	}catch(Exception e){
		return -1;
		}
	}
	
	/**
	 * Click the link based on the link name
	 * @param linkName
	 */
	public void ClickLink(String lnkName){
		StackTraceElement el= new Exception("DEBUG").getStackTrace()[1];
		try{
			driver.findElement(By.xpath(parentId+"/tbody//tr//a[text()='"+lnkName+"']")).click();
			//reportEvent.Status(el.getClassName().toString().replace("com.uhg.picts.panels.","")+ "Page","Link"+lnkName+"found and clicked","Pass");
			
			} catch(Exception e){
		//reportEvent.Status(el.getClassName().toString().replace("com.uhg.picts.panels.","")+ "Page"  ,"Link"+lnkName+"Not found","FAIL");
	
		}
	}
	
	/**
	 * Click the link based on the link name
	 * @param linkName
	 */
	public void setFilter(String label,String value){
		StackTraceElement el= new Exception("DEBUG").getStackTrace()[1];
		try{
			String uniqueId="//span[text()='"+label+"']/..//input";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(uniqueId)));
			driver.findElement(By.xpath(uniqueId)).sendKeys(value);
			
			} catch(Exception e){
				e.printStackTrace();
	//	reportEvent.Status(el.getClassName().toString().replace("com.uhg.picts.panels.","")+ "Page"  ,"text field with label"+label+"Not found","FAIL");
	
		}
	}
	
	/**
	 * Get the filter value in the grid based on the label
	 * @param label
	 * @return String
	 */
	public String getFilter(String label){
		StackTraceElement el= new Exception("DEBUG").getStackTrace()[1];
		String value="";
		try{
			String uniqueId="//span[text()='"+label+"']/..//input";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(uniqueId)));
			driver.findElement(By.xpath(uniqueId)).sendKeys(value);
			
			} catch(Exception e){
				e.printStackTrace();
		//reportEvent.Status(el.getClassName().toString().replace("com.uhg.picts.panels.","")+ "Page"  ,"text field with label"+label+"Not found","FAIL");
	
		}
		return value;
	}
	/**
	 * Checks if the given column exists in dual header table
	 * @param colName
	 * @return true if found,else false
	 */
	public void columnPresentDualHeaderTable(String strTableHeader,String colName){
		try{
			List<WebElement> elements=driver.findElements(By.xpath("thead[@id='"+strTableHeader+"'/tr[1]/th"));
			for(int i=1; i<=elements.size();i++){
				String str=driver.findElement(By.xpath("thead[@id='"+strTableHeader+"'/tr[1]/th["+i+"]/span[2]")).getText();
				if(str.equalsIgnoreCase(colName)){
				//	reportEvent.Status("Column Validation","Table Column: '"+colName+"' is Found","PASS");
					break;
				}
			}
			}
	catch(Exception e)
		{
	
	//	reportEvent.Status("Console message", "Table Column:" +colName+" is not found","FAIL");
		}
	}
	
	/**
	 * verify tables Column Name when store at span[1]***/
	
	public void columnPresentDualHeaderTable2(String strTableHeader,String colName){
		try{
			List<WebElement> elements=driver.findElements(By.xpath("thead[@id='"+strTableHeader+"'/tr[1]/th"));
			for(int i=1; i<=elements.size();i++){
				String str=driver.findElement(By.xpath("//thead[@id='"+strTableHeader+"'/tr[1]/th["+i+"]/span[1]")).getText();
				if(str.equalsIgnoreCase(colName)){
				//	reportEvent.Status("Column Validation","Table Column: '"+colName+"' is Found","PASS");
					break;
				}
			}
			}
	catch(Exception e)
		{
	
	//	reportEvent.Status("Console message", "Table Column:" +colName+" is not found","FAIL");
		}
	}
	
	/**
	 * verify sorting function of  column when name store at span[1]***/
	
	public void sortingFunctionColumnHeaderTable(int spanNum,String strTableHeader,String colName){
		try{
			List<WebElement> elements=driver.findElements(By.xpath("//thead[@id='"+strTableHeader+"'/tr[1]/th"));
			for(int i=1; i<=elements.size();i++){
				String str=driver.findElement(By.xpath("//thead[@id='"+strTableHeader+"'/tr[1]/th["+i+"]/span[1]")).getText();
				if(str.equalsIgnoreCase(colName)){
					driver.findElement(By.xpath("//thead[@id='"+strTableHeader+"'/tr[1]/th["+i+"]/span["+spanNum+"]")).click();
					break;
				}
			}
			}
	catch(Exception e)
		{
	
	//	reportEvent.Status("Console message", "Table Column:" +colName+" is clicked","FAIL");
		}
	}
	
	/**
	 * verify sorting function of  column when name store at span[2]***/
	
	public void sortingFunctionColumnHeaderTable2(int spanNum,String strTableHeader,String colName){
		try{
			List<WebElement> elements=driver.findElements(By.xpath("//thead[@id='"+strTableHeader+"'/tr[1]/th"));
			for(int i=1; i<=elements.size();i++){
				String str=driver.findElement(By.xpath("//thead[@id='"+strTableHeader+"'/tr[1]/th["+i+"]/span[2]")).getText();
				if(str.equalsIgnoreCase(colName)){
					driver.findElement(By.xpath("//thead[@id='"+strTableHeader+"'/tr[1]/th["+i+"]/span["+spanNum+"]")).click();
					Thread.sleep(3000);
					break;
				}
			}
			}
	catch(Exception e)
		{
	
		//reportEvent.Status("Console message", "Table Column:" +colName+" is not clicked","FAIL");
		}
	}
}
 