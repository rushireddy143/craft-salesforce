package com.cts.components;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateAndTime extends Component {
	
	/* To Increase/Decrease the system date by no of days
	 * @param NoOfDays
	 * @param Operation : Add or subtract
	 */
	public void setSystemDate(int NoOfdays,String operation) throws Exception
	{
		try
		{
			DateFormat dateFormat = new SimpleDateFormat("MM/DD/YYYY");
			Calendar cal=Calendar.getInstance();
			if(operation.equalsIgnoreCase("Add"))
			{
				cal.add(Calendar.DATE, +NoOfdays);
			}
			else if (operation.equalsIgnoreCase("Substract"))
			{
				cal.add(Calendar.DATE, -NoOfdays);
			}
			String strDateToSet=dateFormat.format(cal.getTime());
			System.out.println("Date :"+strDateToSet);
			Runtime.getRuntime().exec("cmd /c date" + strDateToSet);
			
			narrate(NoOfdays +"Days has been " +operation+ "to current date","Pass");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			narrate("Could not set the Date","Fail");
		}
	}
	
	/* To set the system time to given value
	 * @param time
	 */
	public void setSystemTime()
	{
		
	}

}
