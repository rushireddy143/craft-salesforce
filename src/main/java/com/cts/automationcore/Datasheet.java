package com.cts.automationcore;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

public class Datasheet {
	
	private int intCurrentRowNumber=1;
	private HSSFSheet readsheet = null;
	private FormulaEvaluator evaluator = null;
	private HSSFWorkbook workbook = null;
	//public static String environment,platform,sendMail,mailsubject,mailinglist,mainUrl,fromemail,smtpserver,updateQC;
	
	private static Datasheet datasheet = null;
	
	public Datasheet() {
		
	}
	
	public static Datasheet getInstance() {
		if(datasheet == null) {
			Datasheet data = new Datasheet();
			return data;
		} else {
			return datasheet;
		}
	}
	
	/**
	 * Loads the test configurtion file
	 */
	
	public void getDataSheet() {
		try
		{
			FileInputStream fis = new FileInputStream("C:\\Users\\Rishi\\Desktop\\shop\\src\\test\\resources\\TestData\\TestData1.xls");
			POIFSFileSystem poifs = new POIFSFileSystem(fis);
			workbook = new HSSFWorkbook(poifs);
		} catch (Exception e) {
			System.out.println("File path not exist for:");
		}
	}
	
	/**
	 * set Active sheet
	 * @param sheetName
	 * @return 
	 */
	public void setActiveSheet(String SheetName) {
		try{
			readsheet = workbook.getSheet(SheetName);
			if(readsheet == null){
				throw new Exception();
			}
			evaluator = workbook.getCreationHelper().createFormulaEvaluator();			
		}catch(Exception e){
			System.out.println("Sheet Name: "+ SheetName + "does not exist");
			System.exit(0);
		}
	}
	
	/**
	 * Gets the column number for the special column Name
	 * @param columnName
	 * @return
	 */
	private int getColumnNumber(String columnName){
		int intColumnPosition=0;
		try{
			Cell cell;
			CellValue cellValue;
			do{
				Row row = readsheet.getRow(0);
				cell = row.getCell(intColumnPosition);
				
				 cellValue = evaluator.evaluate(cell);
				if((cellValue.getStringValue().toString()).equalsIgnoreCase(columnName)){
					break;
				}
				intColumnPosition++;
			}while((cell!=null)&& !((cellValue.getStringValue().toString()).equalsIgnoreCase("")));			
		}catch(Exception e){
			System.out.println("Column Name" +columnName+ " does not exist");
			System.exit(0);
		}
		return intColumnPosition;
	}
	
	/**
	 * Method getUserData: Gets the value from excelsheet's cell from a given column name and returns it in string
	 * @param columnName
	 * @return String - cellValue
	 * @throws IOException
	 */
	public String getUserData(String columnName) throws IOException {
		String cellFlag = null;
		try{
			Row row = readsheet.getRow(intCurrentRowNumber);
			Cell cell = row.getCell(getColumnNumber(columnName));
			CellValue cellValue = evaluator.evaluate(cell);
			if(cell == null)
				cellFlag="";
			else
			{
				try{
					switch (cellValue.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					cellFlag = cellValue.getStringValue().trim().toString();
					break;
				case Cell.CELL_TYPE_FORMULA:
					
					break;
				case Cell.CELL_TYPE_NUMERIC:
					
					String[] tempFlag;
					tempFlag = Double.toString(cellValue.getNumberValue()).split("\\.");
					cellFlag = tempFlag[0].trim().toString();
					break;
				case Cell.CELL_TYPE_BLANK:
					cellFlag = "";
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					cellFlag = Boolean.toString(cellValue.getBooleanValue());
					break;
				}}catch (NullPointerException k){
					cellFlag = "";
					evaluator = null;
				}
			}
		}catch(Exception e){
			//e.printStringTrace();
			System.out.println("Reached end of the row in test data sheet");
			System.exit(0);
		}
		return cellFlag;
	}

	/**
	 * Gets next row of data
	 */
	public boolean getNextRow(){
		boolean blnRecord = false;
		if(intCurrentRowNumber<readsheet.getPhysicalNumberOfRows()){
			intCurrentRowNumber++;
			blnRecord = true;
		}
		return blnRecord;
	}
	
	/**
	 * set first row of the datasheet
	 */
	public void setFristRow(){
		intCurrentRowNumber=1;
	}
	
	/**
	 * set last row of the datasheet
	 */
	public void setLastRow(){
		intCurrentRowNumber=readsheet.getLastRowNum();
	}
	
	public String getDriverData(int rowPosition) throws IOException {
		String cellFlag = null;
		Row row = readsheet.getRow(rowPosition);
		Cell cell = row.getCell(1);
		CellValue cellValue = evaluator.evaluate(cell);
		if(cell == null)
			cellFlag="";
		else
		{
			try{switch (cellValue.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				cellFlag = cellValue.getStringValue().trim().toString();
				//System.out.Println("string"+cellFlag);
				break;
			case Cell.CELL_TYPE_FORMULA:
				
				break;
			case Cell.CELL_TYPE_NUMERIC:
				
				String[] tempFlag;
				tempFlag = Double.toString(cellValue.getNumberValue()).split("\\.");
				cellFlag = tempFlag[0].trim().toString();
				System.out.println("string"+cellFlag);
				break;
			case Cell.CELL_TYPE_BLANK:
				cellFlag = "";
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				cellFlag = Boolean.toString(cellValue.getBooleanValue());
				break;
			}}catch (NullPointerException k){
				cellFlag = "";
				evaluator = null;
			}
		}
		return cellFlag;
	}
	/**
	 * Method getRowNumber: Gets the value from excelsheet's cell returnsuit in string formate
	 * @param TestName
	 * @return String - cellValue
	 * @throws IOException
	 */
	public int getRowNumber(String strTestName) throws IOException {
		int intRowPosition=0;
		try{
			Cell cell;
			CellValue cellValue;
			do{
				Row row = readsheet.getRow(intRowPosition);
				cell = row.getCell(0);
				cellValue = evaluator.evaluate(cell);
				if((cellValue.getStringValue().toString()).equalsIgnoreCase(strTestName)){
					break;
				}
				intRowPosition++;
			}while((cell!=null)&& !((cellValue.getStringValue().toString()).equalsIgnoreCase("")));			
		}catch(Exception e){
			System.out.println("Test Case '" +strTestName+ "' does not exist in SQLData Sheet");
			System.exit(0);
		}
		return intRowPosition;
	}
	/**
	 * Method getUserData: Gets the value from excelsheet's cell from a given column name and returns it in
	 * @param columnName
	 * @return String - cellValue
	 * @throws IOException
	 */
	public String getIterationData(String columnName, int iterationCount) throws IOException {
		String cellFlag=null; 
		
		try{
			Row row = readsheet.getRow(iterationCount);
			Cell cell = row.getCell(getColumnNumber(columnName));
			CellValue cellValue = evaluator.evaluate(cell);
			if(cell == null)
				cellFlag="";
			else
			{
				try{switch (cellValue.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					cellFlag = cellValue.getStringValue().trim().toString();
					break;
				case Cell.CELL_TYPE_FORMULA:
					
					break;
				case Cell.CELL_TYPE_NUMERIC:
					
					String[] tempFlag;
					tempFlag = Double.toString(cellValue.getNumberValue()).split("\\.");
					cellFlag = tempFlag[0].trim().toString();
					System.out.println("Numeric"+cellFlag);
					break;
				case Cell.CELL_TYPE_BLANK:
					cellFlag = "";
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					cellFlag = Boolean.toString(cellValue.getBooleanValue());
					break;
				}}catch (NullPointerException k){
					cellFlag = "";
					evaluator = null;
				}
			}
		}catch(Exception e){
			//e.printStackTrace();
			System.out.println("Reached end of the rows in test data sheet");
			System.exit(0);
		}
			return cellFlag;
	}
	/**
	 * Method updateIterationData: updates the value to excelsheet's cell for a given column name and returns it in
	 * @param columnName
	 * @throws IOException
	 */
	public void updateIterationData(String columnName, int iterationCount,String strCellValue) throws IOException {
		Cell cell=null; 
		
		try{
			Row row = readsheet.getRow(iterationCount);
			cell = row.getCell(getColumnNumber(columnName));
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(strCellValue);
		}catch(Exception e){
			System.out.println("Unable to update the cell value in '"+columnName+", for excel sheet");
			//System.exit(0);
		}
	}
	/**
	 * Update the Test data file		
	 */
	public void updateDataSheet(String path){
		try
		{
			FileOutputStream fis = new FileOutputStream(FilePath.getFilepath(path));
			workbook.write(fis);
			fis.close();
		}catch (Exception e){
			System.out.println("Unable to update file at location,name: " +path);
		}
	}

	
	
}
