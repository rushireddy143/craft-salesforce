package com.automation.yahoo;

import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cts.objectrepository.ObjectRepository;
import com.cts.panels.vbr.Panel;

public class YahooLogin extends Panel{
	
	@Test
	@Parameters({"strDatalocation","strDatasheet","Browser"})
	public void TestSetup(String strDatalocation,String strDatasheet,String Browser){
		
		YahooLogin gl=new YahooLogin(); 
		try{
			gl.setup(strDatalocation, strDatasheet, Browser);
			gl.test();
			driver.quit();
			Reporter.log("Test Script pass");
		}catch(Exception e){
			e.printStackTrace();
			Reporter.log(" Test Script fail");
		}
}
	
	public void test()  throws Exception {
		
		textfield.findByXpath(ObjectRepository.yahooun);
		
		
		data.setActiveSheet("Yahoo");
		
		Reporter.log(" Yahoo user name entered in the text box");
		textfield.setValue(data.getIterationData("UserName", 1));
		
		button.findByXpath(ObjectRepository.next);
		
		Reporter.log("Next button i sclicked");
		
		button.click();
		
		Thread.sleep(5000);
		
		
		textfield.findByXpath(ObjectRepository.yahoopwd);
		
		
		
		textfield.setValue(data.getIterationData("Password", 1));
		
		
		Reporter.log("Password entered in the text box");
		
		
		Thread.sleep(3000);
		
		button.findByXpath(ObjectRepository.signin);
		
		
		
		
		button.click();
		Reporter.log("Clicked on sign in Button");
		Thread.sleep(4000);
		
	    button.findByXpath(ObjectRepository.forgotPwd);
	    
	   
	    button.click();
	    
	    Reporter.log("forgot pwd link is clicked");
		
		
		}
}
