package com.generic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * @ScriptName    : DataPool
 * @Description   : 
 * @author	    : Automation Tester (SQS)
 */
public class DataPool 
{
	private XSSFSheet sheet;
	private XSSFWorkbook workbook;
	private FileInputStream excelFileIS;
	private Row headerRow;
	private Row testDataRow;

	/**
	 * @Method      : loadTestData(String testCaseID, String testDataFile)
	 * @Description : Load Data from Excel for the running testCase and return as Object array    
	 * @author	    : Automation Tester (SQS) 
	 */
	public Object[][] loadTestData(String testCaseID, String testDataFilePath)
	{
		ArrayList<Hashtable<String, String>> hashTableList = new ArrayList<Hashtable<String, String>>();
		Object[][] objDataProvider = null;

		int headerRowCount = 0, lastRowNumber = 1;
		String bufferCell = "";
		// Logic to read the Excel workBook
		try 
		{
			excelFileIS = new FileInputStream(testDataFilePath);
			workbook = new XSSFWorkbook(excelFileIS);
			sheet = workbook.getSheetAt(0);
			headerRow = sheet.getRow(0);
			testDataRow = sheet.getRow(1);
			lastRowNumber = sheet.getLastRowNum();
			int rowIndex = 0;

			while( rowIndex <= lastRowNumber)
			{
				String cellData = this.getCellValue(sheet.getRow(rowIndex), 0);
				if(cellData.equalsIgnoreCase(testCaseID))
				{
					headerRowCount = rowIndex - 1;
					bufferCell = this.getCellValue(sheet.getRow(rowIndex), 0);
					while(rowIndex <= lastRowNumber && !bufferCell.equalsIgnoreCase("TestCaseId"))
					{
						if(bufferCell.equalsIgnoreCase(testCaseID))
						{
							headerRow = sheet.getRow(headerRowCount);
							testDataRow = sheet.getRow(rowIndex);
							Hashtable<String , String> dataValueSet = new Hashtable<String, String>();
							int clmNo = 0;
							//iterating over cells
							do
							{
								String header = "", testData = "";
								// Key Data
								header = this.getCellValue(headerRow, clmNo);
								// Value
								testData = this.getCellValue(testDataRow, clmNo);

								if(!header.equals(""))
									dataValueSet.put(header, testData);
								clmNo++;

							}while(clmNo < headerRow.getLastCellNum());
							//put the hash-table in list
							hashTableList.add(dataValueSet);
							dataValueSet = null;
							clmNo = 0;
						}
						rowIndex++;
						if(rowIndex > lastRowNumber)
							bufferCell = "";
						else
							bufferCell = this.getCellValue(sheet.getRow(rowIndex), 0);
					};
					break;
				}
				rowIndex++;     
			}

			objDataProvider = new Object[hashTableList.size()][2];
			int rowCount = 0;
			for( Hashtable<String, String>hashTable : hashTableList) 
			{
				objDataProvider[rowCount][0] = "Run" + (rowCount + 1);
				objDataProvider[rowCount][1] = hashTable;
				rowCount++;
			}
			excelFileIS.close();
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		finally
		{
			hashTableList = null;
		}
		return objDataProvider;
	}

	/**
	 * @Method      : updateTestDataSheet(String testDataFile, String testCaseID, String runID,Hashtable<String, String> testDataToUpdate)
	 * @Description : Load Data from Excel for the running testCase and return as Object array    
	 * @author	    : Automation Tester (SQS)
	 */
	public boolean updateTestDataSheet(String testDataFilePath, String testCaseID, String runID,Hashtable<String, String> testDataToUpdate)
	{
		int headerRowCount = 0,lastRowNumber = 1;
		String bufferCell = "";
		// Logic to Write in the Excel workBook
		try 
		{
			excelFileIS = new FileInputStream(testDataFilePath);
			workbook = new XSSFWorkbook(excelFileIS);
			sheet = workbook.getSheetAt(0);
			headerRow = sheet.getRow(0);
			testDataRow = sheet.getRow(1);
			lastRowNumber = sheet.getLastRowNum();
			int rowIndex = 0;
			int runCounter = 1;

			while( rowIndex <= lastRowNumber)
			{
				String cellData = this.getCellValue(sheet.getRow(rowIndex), 0);
				if(cellData.equalsIgnoreCase(testCaseID))
				{
					headerRowCount = rowIndex - 1;
					bufferCell = this.getCellValue(sheet.getRow(rowIndex), 0);
					while(rowIndex <= lastRowNumber && !bufferCell.equalsIgnoreCase("TestCaseId"))
					{
						if(bufferCell.equalsIgnoreCase(testCaseID) && runID.equalsIgnoreCase("Run" + runCounter))
						{
							headerRow = sheet.getRow(headerRowCount);
							testDataRow = sheet.getRow(rowIndex);
							int clmNo = 0;
							//iterating over cells
							do
							{
								String header = "";
								// Key Data
								header = this.getCellValue(headerRow, clmNo);
								if(testDataToUpdate.containsKey(header))
									this.putCellValue(testDataRow, clmNo, testDataToUpdate.get(header));
								clmNo++;
							}while(clmNo < headerRow.getLastCellNum());
							// Save and close file
							clmNo = 0;
							excelFileIS.close();
							FileOutputStream fileOut = new FileOutputStream(testDataFilePath);
							workbook.write(fileOut);
							fileOut.close();
							return true; 
						}
						rowIndex++;
						runCounter++;
						if(rowIndex > lastRowNumber)
							bufferCell = "";
						else
							bufferCell = this.getCellValue(sheet.getRow(rowIndex), 0);
					};
					break;
				}
				rowIndex++;     
			}
			excelFileIS.close();
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
		return false;
	}

	/**
	 * @Method      : putCellValue(Row testDataRow, int columnNumber, String testData)
	 * @Description : Put Cell value for given cell (Used in loadDataProvider)    
	 * @author	    : Automation Tester (SQS) 
	 */
	private boolean putCellValue(Row testDataRow, int columnNumber, String testData)
	{
		if (testDataRow != null)
		{
			Cell testDataCell = testDataRow.getCell(columnNumber, Row.CREATE_NULL_AS_BLANK);
			if(testDataCell != null)
			{
				testDataCell.setCellValue(testData);
				return true;
			}
		}
		return false;
	}

	/**
	 * @Method      : getCellValue(Cell testDataCell, int columnNumber)
	 * @Description : Get Cell value for given cell (Used in loadDataProvider)    
	 * @author	    : Automation Tester (SQS) 
	 */
	private String getCellValue(Row testDataRow, int columnNumber)
	{
		if (testDataRow == null)
			return "";
		else 
		{
			Cell testDataCell = testDataRow.getCell(columnNumber, Row.RETURN_BLANK_AS_NULL);
			if(testDataCell == null)
				return "";
			else
				return testDataCell.toString().trim();
		}
	}
}
