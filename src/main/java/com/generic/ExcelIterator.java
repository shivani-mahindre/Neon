package com.generic; 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * @ScriptName    : ExcelIterator
 * @Description   : This class is used to iterate excel file data
 * @Author	    : Automation Tester (SQS)
 */
public class ExcelIterator 
{
	private String flFile;
	private int intSheetNumber;
	private int intIndex = 0;
	private int intNoOfRow;
	private int intNoOfColumn;
	private XSSFSheet sheet;
	private XSSFWorkbook workbook;
	private FileInputStream excelFileIS;
	String strExcelFilePath = "";
	private Row row;

	 int intRowCounter = 1;
	 int intSummaryRowCounter = 1;

	public ExcelIterator(String strExcelFile, int intSheetno, boolean ignoreHeaderRow)
	{
		flFile = strExcelFile;
		intSheetNumber = intSheetno;
		try
		{
			excelFileIS = new FileInputStream(flFile);
			//workbook = new XSSFWorkbook(new File(flFile));
			workbook = new XSSFWorkbook(excelFileIS);
			excelFileIS.close();
			sheet = workbook.getSheetAt(intSheetNumber);
			intNoOfRow = sheet.getPhysicalNumberOfRows();
			if(ignoreHeaderRow)
				intIndex++;
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		} 
	}

	public ExcelIterator() {
		// TODO Auto-generated constructor stub
	}

	public boolean isDone() 
	{
		if(intIndex < intNoOfRow) 
		{
			return true;
		}
		else 
		{
			return false;
		}
	}

	public String getColumn(int clmNo)
	{
		String strCellvalue = "";
		org.apache.poi.ss.usermodel.Row row = null;
		org.apache.poi.ss.usermodel.Cell cell = null;
		row = sheet.getRow(intIndex);
		intNoOfColumn = row.getPhysicalNumberOfCells();
		try
		{
			cell = row.getCell(clmNo, Row.RETURN_BLANK_AS_NULL);
			if (cell == null)
				strCellvalue = "";
			else 
				strCellvalue = cell.toString().trim();
			strCellvalue = cell.toString();
			return strCellvalue;
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		return strCellvalue;
	}


	public String getColumn(String columnHeader)
	{
		org.apache.poi.ss.usermodel.Row headerRow = sheet.getRow(0);
		org.apache.poi.ss.usermodel.Row testDataRow = sheet.getRow(this.getCurrentRowNumber());
		int clmNo = 0;
		String cellValue = "";
		try
		{
			do
			{
				String currentHeader = "";
				org.apache.poi.ss.usermodel.Cell headerCell = headerRow.getCell(clmNo, Row.RETURN_BLANK_AS_NULL);
				if (headerCell == null)
					currentHeader = "";
				else 
					currentHeader = headerCell.toString().trim();
				if(currentHeader.equalsIgnoreCase(columnHeader))
				{
					org.apache.poi.ss.usermodel.Cell testDataCell = testDataRow.getCell(clmNo, Row.RETURN_BLANK_AS_NULL);

					if (testDataCell == null)
						cellValue = "";
					else 
						cellValue = testDataCell.toString().trim();
				}
				clmNo++;
			}
			while(clmNo < headerRow.getLastCellNum());
		}
		catch(Exception exception)
		{
		}
		return cellValue;
	}

	public void next()
	{
		intIndex++;
	}

	public int getCurrentRowNumber()
	{
		return intIndex;
	}

	public int getNumberOfRows()
	{
		return intNoOfRow; 
	}

	public int getNumberOfColumnForCurrentRow()
	{
		return intNoOfColumn;
	}
	
	public void createCustomerExcel(String custNumber,String cardNumber,String DOB)
	{
		String timeStamp = new SimpleDateFormat("dd_MM_YYYY")
		.format(Calendar.getInstance().getTime());
	strExcelFilePath = System.getProperty("user.dir") + "/src/test/java/testResult/CustomerDetails_"+timeStamp+".xlsx";
	File reportFile = new File(strExcelFilePath);
	
	if(!reportFile.exists())
	{
		try {
			new File(System.getProperty("user.dir") + "/src/test/java/testResult").mkdir();
			reportFile.createNewFile();		
			workbook = new XSSFWorkbook();
			sheet = (XSSFSheet)workbook.createSheet("TestSummary");
			row = sheet.createRow(0);
			row.createCell(0).setCellValue("CustomerNumber");	
			row.createCell(1).setCellValue("CardNumber");
			row.createCell(2).setCellValue("DateOfBirth");	
			intSummaryRowCounter = sheet.getLastRowNum() + 1;
			row = sheet.createRow(intSummaryRowCounter);		
			row.createCell(0).setCellValue(custNumber);
			row.createCell(1).setCellValue(cardNumber);
			row.createCell(2).setCellValue(DOB);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
	}
	else // If file exist update summary sheet and create sheet for running script details
	{
	  try {
		workbook = (XSSFWorkbook)WorkbookFactory.create(new FileInputStream(strExcelFilePath));	
		sheet = (XSSFSheet) workbook.getSheet("TestSummary");
		intSummaryRowCounter = sheet.getLastRowNum() + 1;
		row = sheet.createRow(intSummaryRowCounter);		
		row.createCell(0).setCellValue(custNumber);
		row.createCell(1).setCellValue(cardNumber);
		row.createCell(2).setCellValue(DOB);
		} catch (InvalidFormatException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	  }
		FileOutputStream fileOut;
		try {
		fileOut = new FileOutputStream(reportFile);
		workbook.write(fileOut);
		fileOut.close();		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}

