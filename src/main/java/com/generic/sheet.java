package com.generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class sheet {

	public static void main(String[] args) throws IOException, InvalidFormatException 
	{
		//String reportSheet = "";
		String strExcelFilePath = "";
		 XSSFWorkbook workbook;
		 XSSFSheet currentReportSheet;
		 XSSFSheet summarySheet;
		 XSSFRow summaryRow;
		 XSSFRow sheetRow;
		 int intRowCounter = 1;
		 int intSummaryRowCounter = 1;
		 int excelReportStepNumber = 1;
		 int intExcelReportNoOfFails = 0;
		
			String reportSheet = "";

		

					
			// Excel report file path
		 	String timeStamp = new SimpleDateFormat("dd_MM_YYYY")
				.format(Calendar.getInstance().getTime());
			strExcelFilePath = System.getProperty("user.dir") + "/target/custom-reports/CustomerDetails_"+timeStamp+".xlsx";
			File reportFile = new File(strExcelFilePath);

			// If file not exist create new with summary sheet and sheet for running script details
			if(!reportFile.exists())
			{
				new File(System.getProperty("user.dir") + "/target/custom-reports").mkdir();
				reportFile.createNewFile();
				workbook = new XSSFWorkbook();
				summarySheet = workbook.createSheet("TestSummary");
				summaryRow = summarySheet.createRow(0);
				summaryRow.createCell(0).setCellValue("customerNumber");
				summaryRow.createCell(1).setCellValue("date of birth");
				summaryRow.createCell(2).setCellValue("Card Number");
				
				
			/*	summaryRow = summarySheet.createRow(intSummaryRowCounter);
				this.createSummaryLinkCell(0, runningScriptName, reportSheet);
				currentReportSheet = workbook.createSheet(runningScriptName);
				sheetRow = currentReportSheet.createRow(0);*/
			}
			else // If file exist update summary sheet and create sheet for running script details
			{
				workbook = (XSSFWorkbook)WorkbookFactory.create(new FileInputStream(strExcelFilePath));
				summarySheet = workbook.getSheet("TestSummary");
				intSummaryRowCounter = summarySheet.getLastRowNum() + 1;
				summaryRow = summarySheet.createRow(intSummaryRowCounter);
			//	this.createSummaryLinkCell(0, runningScriptName, reportSheet);
			//	String sheetName=	runningScriptName.toString();
				//System.out.println("sheetName "+sheetName);
				System.out.println("intSummaryRowCounter  "+intSummaryRowCounter);
			///	currentReportSheet = workbook.createSheet(runningScriptName);
				//sheetRow = currentReportSheet.createRow(0);
			}

			// Add column header in sheet of running script details
		/*	this.createSheetHeaderCell(0, "S.No");
			this.createSheetHeaderCell(1, "Step Description");
			this.createSheetHeaderCell(2, "Input Value");
			this.createSheetHeaderCell(3, "Expected Value");
			this.createSheetHeaderCell(4, "Actual Value");
			this.createSheetHeaderCell(5, "Status");
			*/
		}


}
