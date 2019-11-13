package com.cts.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Calender extends Component{
	WebElement uniqId=null;
	String ParentId=null;
	String label="";
	/**
	 * Find the calendar based on the label
	 * 
	 * @param label
	 * @return true if found,else false
	 */
	public boolean findByLabel1(String label){
		this.label=label;
		StackTraceElement e1=new Exception("DEBUG").getStackTrace()[1];
		try{
			ParentId="//*[@id='extDocpopupFormId:Dateof"+label+"DateCalpopupButton']";
			uniqId=wait.until(ExpectedConditions.presenceOfElementLocated(org.openqa.selenium.By.xpath(ParentId)));
			return true;
		}catch(Exception e){
		//	reportEvent.Status(
					//e1.getClassName().toString()
					//.replace("com.ung.vbr.panels.","")
				//	+"page","button"+label+"is NOT Found",
				//	"FAIL");
			  return false;
	       }
		}
	/*
	  *fin the Calendar based on the label
	  *
	  *@param label
	  *@return true if found,else false
	  */
	public boolean findByLabel(String label){
		this.label=label;
		StackTraceElement e1= new Exception("DEBUG").getStackTrace()[1];
		try{
			ParentId = "//img[contains9@id,'"+label+"')and contains(@class,'rf-cal-btn')]";
			uniqId = wait.until(ExpectedConditions.presenceOfElementLocated(org.openqa.selenium.By.xpath(ParentId)));
			return true;
		}catch (Exception e){
		//	reportEvent.Status(
			//		e1.getClassName().toString().replace("com.uhg.vbr.panels.","")+"page","button"+label+"is Not Found","FAIL");
			          return false;
	   }
	}
     /**
      * click Today
      */
   public void clickToday(){
	   try{
		   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Tody']"))).click();
	   } catch(Exception e){
		   e.printStackTrace();
		   narrate("calendar","Today Link not found","FAIL");
	   }
	 }
   /**
    * Click a Day
    */
   public void clickDay(String day){
	   try{
		   wait.until(ExpectedConditions.invisibilityOfElementLocated(org.openqa.selenium.By.xpath("//[contains=(@id,'dayCell"+day+"')and contains(@class,'rf-cal-btn')]")));
		      }catch(Exception e){
		    	  e.printStackTrace();
		    	  narrate("Clendar","Day"+day+"not clicked","FAIL");
		      }
      }
     /**
      * Click clear   
      */
    public void clickedClear(){
    	try{
    		wait.until(ExpectedConditions.invisibilityOfElementLocated(org.openqa.selenium.By.xpath("//div[text()='Clear']")));
    	}catch(Exception e){
    		e.printStackTrace();
    		narrate("Clendar","Clear link not found","FAIL");
    		
    	}
      }
    }
