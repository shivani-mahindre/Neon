package com.generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class testexcel {

	public static void main(String[] args) throws IOException, InvalidFormatException {
		// TODO Auto-generated method stub
		String strExcelFilePath = "";
		Workbook wb;
		XSSFSheet sheet;Row row;
		 int intRowCounter = 1;
		 int intSummaryRowCounter = 1;
		String str="dfs";
		String timeStamp = new SimpleDateFormat("dd_MM_YYYY")
		.format(Calendar.getInstance().getTime());
	strExcelFilePath = System.getProperty("user.dir") + "/target/custom-reports/CustomerDetails_"+timeStamp+".xlsx";
	File reportFile = new File(strExcelFilePath);
	
	if(!reportFile.exists())
	{
		new File(System.getProperty("user.dir") + "/target/custom-reports").mkdir();
		reportFile.createNewFile();
		 wb = new XSSFWorkbook();
		 sheet = (XSSFSheet) wb.createSheet("TestSummary");
		 row = sheet.createRow(0);
		row.createCell(0).setCellValue("CardNumber");
		row.createCell(1).setCellValue("DateOfBirth");
		row.createCell(2).setCellValue("CustomerNumber");
	}
	else // If file exist update summary sheet and create sheet for running script details
	{
		wb = (XSSFWorkbook)WorkbookFactory.create(new FileInputStream(strExcelFilePath));
		sheet = (XSSFSheet) wb.getSheet("TestSummary");
		intSummaryRowCounter = sheet.getLastRowNum() + 1;
		row = sheet.createRow(intSummaryRowCounter);
		
		row.createCell(0).setCellValue(str);
		row.createCell(1).setCellValue(str);
		row.createCell(2).setCellValue(str);
	}
		FileOutputStream fileOut = new FileOutputStream(reportFile);
		wb.write(fileOut);
		fileOut.close();
	}
	
}
