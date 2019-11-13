package com.cts.panels.vbr;

import java.util.Calendar;

import com.cts.components.Button;
import com.cts.components.CheckBox;
import com.cts.components.Component;
import com.cts.components.DateAndTime;
import com.cts.components.FileComponent;
import com.cts.components.Link;
import com.cts.components.RadioButton;
import com.cts.components.SelectDropdown;
import com.cts.components.Tab;
import com.cts.components.Table;
import com.cts.components.TextField;

public class Panel  extends Component{
	
	protected Link link=(Link)getComponents("Link");
	protected TextField textfield=(TextField)getComponents("TextField");
	protected Button button=(Button)getComponents("Button");
	protected Table table=(Table)getComponents("Table");
	protected Tab tab=(Tab)getComponents("Tab");
	//protected Calendar calendar=(Calendar)getComponents("Calendar");
	protected SelectDropdown select=(SelectDropdown)getComponents("SelectDropdown");
	protected DateAndTime dateandtime=(DateAndTime)getComponents("DateAndTime");
	protected FileComponent fileComponent=(FileComponent)getComponents("FileComponent");
	protected RadioButton radioButton=(RadioButton)getComponents("RadioButton");
	protected CheckBox checkbox=(CheckBox)getComponents("CheckBox");
	
	protected Object getPanel(String panelName){
		Object object=null;
		try{
			Class<?> c=null;
			if(panelName.contains(".")){
				c=Class.forName(panelName);
			}else{
				c=Class.forName("com.cts.panel.vbr."+panelName);
			}
			object=c.newInstance();
		}catch(Exception e){
			System.out.println("Class not found");
			e.printStackTrace();
		}
		return panelName;
		
	}
}