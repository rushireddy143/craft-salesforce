package com.automation.chase;

import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cts.objectrepository.ObjectRepository;
import com.cts.panels.vbr.Panel;

public class ChaseCustomRateRental extends Panel{
	
	@Test
	@Parameters({"strDatalocation","strDatasheet","Browser"})
	public void TestSetup(String strDatalocation,String strDatasheet,String Browser){
		
		ChaseCustomRateRental gl=new ChaseCustomRateRental(); 
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
		
		driver.get("https://apply.chase.com/mortgage/CRQ/CustomRateQuote.aspx");
		
		
		}
}
