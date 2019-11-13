package com.cts.components;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Component extends com.cts.automationcore.Driver {
	
	/**
	 * Load the class and creat instance at runtime
	 * @param componentname
	 * @return Object
	 */
	protected Object getComponents(String componentName){
		Object object = null;
		try{
			Class<?> c = null;
			if(componentName.contains(".")){
				c=Class.forName(componentName);
			}else{
				c=Class.forName("com.uhg.components." +componentName);
			}
			object =c.newInstance();
		}catch(Exception e){
			System.out.println("Class not found");
			e.printStackTrace();
		}
		return object;
	}
	
	/**
	 * Check the given condition is true and logs the result
	 * @param strDescription
	 * @param blnCondition
	 */
	public void verifyTrue(String strDescription,boolean blnCondition){
		if(blnCondition==true){
			//reportEvent.Status("Verification",strDescription,"PASS");
		}else{
		///	reportEvent.Status("Verification",strDescription,"FAIL");
		}
	}
	
	/**
	 * Check the given condition is false and logs the result
	 * @param strDescription
	 * @param blnCondition
	 */
	public void verifyFalse(String strDescription, boolean blnCondition){
		verifyTrue(strDescription,!blnCondition);
	}
	
	/**
	 * Checks the given 2 string matched or not
	 * @param str1
	 * @param str2
	 * @return true if equals else false
	 */
	public void verifyEquals(String strDescription,String str1,String str2){
		if(str1.trim().equalsIgnoreCase(str2.trim())){
			//reportEvent.Status("Verification",strDescription+"actual value :"+ str1+ "is EQUAL to expected value:"+str2,"PASS");
		}else{
			//reportEvent.Status("Verification",strDescription+"actual value :"+ str1+ "is EQUAL to expected value:"+str2,"FAIL");
		}
	}
	
	/**
	 * Checks the given 2 integers matched or not
	 * @param value1
	 * @param value2
	 * @return true if equals else false
	 */
	public void verifyEquals(String strDescription,int value1,int value2){
		if(value1==value2){
			//reportEvent.Status("Verification",strDescription+ "actual value:" + value1 +"is EQUAL to expected value:"+value2,"PASS");
		}else{
			//reportEvent.Status("Verification",strDescription+ "actual value:" + value1 +"is EQUAL to expected value:"+value2,"FAIL");
		}
	}
	
	/**
	 * Checks the given 2 strings matched or not
	 * @param str1
	 * @param str2
	 * @return true if equals else false
	 */
	public void verifyNotEquals(String strDescription,String str1,String str2){
		if(!(str1.trim().equalsIgnoreCase(str2.trim()))){
			//reportEvent.Status("Verification",strDescription+ "actual value:" + str1 +"is NOT EQUAL to expected value:"+str2,"PASS");
		}else{
			//reportEvent.Status("Verification",strDescription+ "actual value:" + str1 +"should NOT EQUAL to expected value:"+str2,"FAIL");
		}
	}
	
	/**
	 * verify text on the web page
	 */
	public void verifyText(String strText){
		try{
			wait.until(ExpectedConditions.textToBePresentInElement(By.xpath("//*"), strText));
			//reportEvent.Status("Verification","Text "+strText+"is present on screen","PASS");
		}catch(Exception e){
			//reportEvent.Status("Verification","Text "+strText+"is Not present on screen","FAIL");
		}
	}
	
	/**
	 * verify text on the web page
	 */
	public void verifyContains(String strDescription,String str1,String str2){
		if(str1.trim().contains(str2.trim())){
			//reportEvent.Status("Verification",strDescription+ "actual value:" + str1 +"is EQUAL to expected value:"+str2,"PASS");
		}else{
			//reportEvent.Status("Verification",strDescription+ "actual value:" + str1 +"is NOT EQUAL to expected value:"+str2,"FAIL");
		}
	}
	
	/**
	 * verify partial text
	 */
	public void verifyPartialtext(String strText){
		try{
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*"))));
		//	reportEvent.Status("Verification","Text "+strText+"is present on screen","PASS");
		}catch(Exception e){
		//	reportEvent.Status("Verification","Text "+strText+"is Not present on screen","FAIL");
		}
	}
	
	/**
	 * verify text on the web page
	 */
	public void verifyNotText(String strText){
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'"+strText+"')]")));
		//	reportEvent.Status("Verification","Text "+strText+"is present on screen","FAIL");
		}catch(Exception e){
		//	reportEvent.Status("Verification","Text "+strText+"is Not present on screen","PASS");
		}
	}
	
	/**
	 * Check the given page is displayed
	 * @param strPageName
	 * @return true if displayed, else false
	 */
	public boolean isPageDispalyed(String strPageName){
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@class,'ux-pnav-selected')]/a[text()='"+strPageName+"']")));
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	/**
	 * Narrate the status in the console
	 * @param strDesc
	 * @param strStatus
	 */
	public void narrate(String strDesc,String strStatus){
		//reportEvent.Status("Console Message", strDesc,strStatus);
		}
	/**
	 * Narrate the status in the console
	 * @param strDesc
	 * @param strStatus
	 */
	public void narrate(String strMsg,String strDesc,String strStatus){
	//	reportEvent.Status(strMsg, strDesc,strStatus);
		}
	/**
	 * Get CSS value
	 * @param Xpath Expression
	 * @param Attribute
	 * @return String
	 */
	public String getCssValue(String xpath,String attribute){
		String strValue="";
		try{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*")));
			strValue=driver.findElement(By.xpath(xpath)).getCssValue(attribute).toLowerCase();
			if(strValue.contains("rgba")){
				String[] numbers = strValue.replace("rgba(", "").replace(")","").split(",");
				int r = Integer.parseInt(numbers[0].trim());
				int g = Integer.parseInt(numbers[1].trim());
				int b = Integer.parseInt(numbers[2].trim());
				String hex ="#" +Integer.toHexString(r)+Integer.toHexString(g) + Integer.toHexString(b);
				strValue=hex.toUpperCase();
			}
		
		
		//reportEvent.Status("attribute: ",attribute,strValue,"PASS");
		}catch(Exception e){
			e.printStackTrace();
		//	reportEvent.Status("Attribute: "+ strValue,"Not Found","FAIL");
			}
		return strValue;
	}
	
	/**
	 * Get Attribute value
	 * @param Xpath Expression
	 * @param Attribute
	 * @return String
	 */
	public String getAttributeValue(String xpath,String attribute){
		String strValue="";
		try{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			strValue=driver.findElement(By.xpath(xpath)).getAttribute(attribute);
			
		//reportEvent.Status("attribute: ",attribute,strValue,"PASS");
		}catch(Exception e){
		//	reportEvent.Status("Attribute: "+ attribute,"Not Found","FAIL");
			}
		return strValue;
	}
	
	/**
	 * Get Text for given location
	 * @param Xpath Expression
	 * @return String
	 */
	public String getText(String xpath){
		try{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			return driver.findElement(By.xpath(xpath)).getText();
		}catch(Exception e){
			//reportEvent.Status("Not a valid Identity","No text Found","FAIL");
			return "";
	}
	}

}