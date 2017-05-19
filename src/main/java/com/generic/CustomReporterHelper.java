package com.generic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
/**
 * @Description :	helper class for custom reporter 
 * @author	    : Automation Tester (SQS) 
 */
public class CustomReporterHelper
{
	// Local Variables
	private String runningScriptName = "";
	private Properties objCustomConfig;

	// Excel
	private String strExcelFilePath = "";
	private XSSFWorkbook workbook;
	private XSSFSheet currentReportSheet;
	private XSSFSheet summarySheet;
	private XSSFRow summaryRow;
	private XSSFRow sheetRow;
	private int intRowCounter = 1;
	private int intSummaryRowCounter = 1;
	private int excelReportStepNumber = 1;
	private int intExcelReportNoOfFails = 0;

	// PDF
	private String strPDFFilePath = "";
	private String strTempPDFFilePath = "";
	private Document document;
	private com.itextpdf.text.Font fs12;
	private com.itextpdf.text.Font fs25Bold;
	private com.itextpdf.text.Font fs12Bold;
	private com.itextpdf.text.Font fs12Red;
	private PdfPTable tableSteps;
	private PdfPTable tableHead;
	private Phrase phrase;
	private PdfPCell cell;
	private PdfStamper stamper;
	private PdfReader reader;
	boolean exixtingPdf = false;
	private int numberOfPages;
	private int pdfReportStepNumber = 1;
	
	//HTML
	private String strHTMLFilePath = "";
	private String strTempHTMLFilePath = "";
	private String runningScriptDivName = "";
	boolean exixtingHTML = false;
	private FileWriter fileWriter;
	private BufferedWriter bufferedWriter;
	private String htmlPageOutput = "";
	private int htmlReportStepNumber = 1;
	private int intHTMLReportNoOfFails = 0;
	private int intTotalPass = 0;
	private int intTotalFail = 0;
	private int intTotalFailGraph = 0;
	private int intTotalPassGraph = 0;
	 
	Pojo objPojo=new Pojo();
	/**
	 * Load configuration properties
	 * @param scriptName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Properties loadCustomConfigFile(String scriptName) throws FileNotFoundException, IOException
	{
		runningScriptName = scriptName;
		objCustomConfig = new Properties();
		objCustomConfig.load(new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/customReporter.properties"));
		return objCustomConfig;
	}

	/**
	 * Start excel report with mentioned configuration properties
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public void startExcelReport() throws IOException, InvalidFormatException
	{
		String reportSheet = "";

		// Excel allow sheet name long upto 31 characters. 
		// In excel report sheets are created by using running script name. 
		if(runningScriptName.length() > 30)
			reportSheet = reportSheet.substring(0, 30);
		else
			reportSheet = runningScriptName;

				
		// Excel report file path
	 	String timeStamp = new SimpleDateFormat("dd_MM_YYYY")
			.format(Calendar.getInstance().getTime());
		strExcelFilePath = System.getProperty("user.dir") + "/target/custom-reports/ExecutionReport_"+timeStamp+".xlsx";
		File reportFile = new File(strExcelFilePath);

		// If file not exist create new with summary sheet and sheet for running script details
		if(!reportFile.exists())
		{
			new File(System.getProperty("user.dir") + "/target/custom-reports").mkdir();
			reportFile.createNewFile();
			workbook = new XSSFWorkbook();
			summarySheet = workbook.createSheet("TestSummary");
			summaryRow = summarySheet.createRow(0);
			this.createSummaryHeaderCell(0, "Test Case");
			this.createSummaryHeaderCell(1, "Status");
			summaryRow = summarySheet.createRow(intSummaryRowCounter);
			this.createSummaryLinkCell(0, runningScriptName, reportSheet);
			currentReportSheet = workbook.createSheet(runningScriptName);
			sheetRow = currentReportSheet.createRow(0);
		}
		else // If file exist update summary sheet and create sheet for running script details
		{
			workbook = (XSSFWorkbook)WorkbookFactory.create(new FileInputStream(strExcelFilePath));
			summarySheet = workbook.getSheet("TestSummary");
			intSummaryRowCounter = summarySheet.getLastRowNum() + 1;
			summaryRow = summarySheet.createRow(intSummaryRowCounter);
			this.createSummaryLinkCell(0, runningScriptName, reportSheet);
			String sheetName=	runningScriptName.toString();
			System.out.println("sheetName "+sheetName);
			System.out.println("intSummaryRowCounter  "+intSummaryRowCounter);
			currentReportSheet = workbook.createSheet(runningScriptName);
			sheetRow = currentReportSheet.createRow(0);
		}

		// Add column header in sheet of running script details
		this.createSheetHeaderCell(0, "S.No");
		this.createSheetHeaderCell(1, "Step Description");
		this.createSheetHeaderCell(2, "Input Value");
		this.createSheetHeaderCell(3, "Expected Value");
		this.createSheetHeaderCell(4, "Actual Value");
		this.createSheetHeaderCell(5, "Status");
		
	}

	
	
	/**
	 *  Start pdf report with mentioned configuration properties
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void startPDFReport() throws DocumentException, IOException
	{
		// PDF report file path
		String timeStamp = new SimpleDateFormat("dd_MM HH_mm_ss")
		.format(Calendar.getInstance().getTime());
		strPDFFilePath = System.getProperty("user.dir") + "/target/custom-reports/Execution_Report_"+timeStamp+".pdf";

		// Create various fonts
		fs12 = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 12, com.lowagie.text.Font.NORMAL, BaseColor.BLACK);
		fs12Bold = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 12, com.lowagie.text.Font.BOLD, BaseColor.BLACK);;
		fs25Bold = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 25, com.lowagie.text.Font.BOLD|com.lowagie.text.Font.UNDERLINE, BaseColor.BLACK);;
		fs12Red = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 12, com.lowagie.text.Font.NORMAL, BaseColor.RED);;

		// create table 
		float[] colsWidthHead = {1};
		tableHead = new PdfPTable(colsWidthHead);
		tableHead.setWidthPercentage(100);

		float[] colsWidthSteps = {3, 30, 10, 10, 10, 7};
		tableSteps = new PdfPTable(colsWidthSteps);
		tableSteps.setWidthPercentage(100);

		File reportFile = new File(strPDFFilePath);

		// If file not exist create new  
		if(!reportFile.exists())
		{
			// Create a Document
			// page size in the Document constructor along with 
			// (left margin--user units,right margin--user units,top margin--user units,bottom margin--user units)
			document = new Document(PageSize.A4.rotate(), 10, 10, 10, 70);
		
			// document objects not mandatory-----
			document.addTitle("Execution Report");
			document.addCreator("SQS India");
			document.addKeywords("Metadata, iText, PDF");
			document.addAuthor("SQS India");

			exixtingPdf = false;
			// PdfWriter is the class responsible for writing the PDF file
			PdfWriter.getInstance(document, new FileOutputStream(strPDFFilePath));

			// Open the Document.
			document.open();

			// Add running script name in header table
			tableHead.addCell(NewCellHeader(phrase, cell, fs25Bold, runningScriptName, 1, Element.ALIGN_LEFT));
			tableHead.addCell(NewCellHeader(phrase, cell, fs25Bold, "", 1, Element.ALIGN_LEFT));
			document.add(tableHead);

			// Add header column name in step details table
			tableSteps.addCell(NewCellHeaderFirstColumn(phrase, cell, fs12Bold, "S.No", 1));
			tableSteps.addCell(NewCellHeaderColumn(phrase, cell, fs12Bold, "Step Description", 1));
			tableSteps.addCell(NewCellHeaderColumn(phrase, cell, fs12Bold, "Input Value", 1));
			tableSteps.addCell(NewCellHeaderColumn(phrase, cell, fs12Bold, "Expected Value", 1));
			tableSteps.addCell(NewCellHeaderColumn(phrase, cell, fs12Bold, "Actual Value", 1));
			tableSteps.addCell(NewCellHeaderLastColumn(phrase, cell, fs12Bold, "Status", 1));
		}
		else // If file exist update existing   
		{
			exixtingPdf = true;
			// get existing file in pdf reder object and calculate number of pages in existing file 
			reader = new PdfReader(strPDFFilePath);
			numberOfPages = reader.getNumberOfPages();

			// Create temporary pdf file of adding new result and get PDF stamper object
			strTempPDFFilePath = System.getProperty("user.dir") + "/target/custom-reports/Execution_Report_Temp.pdf";
			stamper = new PdfStamper(reader, new FileOutputStream(strTempPDFFilePath));

			// get owner and user password from mentioned configuration
			String ownerPassword = objCustomConfig.getProperty("custom.reports.pdf.masterPassword").trim();
			String userPassword = objCustomConfig.getProperty("custom.reports.pdf.userPassword").trim();

			// opening document with user password printing and copy is disabled
			if(!ownerPassword.equals(""))
				stamper.setEncryption(userPassword.getBytes(), ownerPassword.getBytes(), 0, PdfWriter.STANDARD_ENCRYPTION_128);

			// to make allow copy and printing for user password 
			// stamper.setEncryption(userPassword.getBytes(), ownerPassword.getBytes(), PdfWriter.ALLOW_COPY | PdfWriter.ALLOW_PRINTING,  PdfWriter.STANDARD_ENCRYPTION_128 );

			// add existing pages to new document
			for(int pageNo = 1; pageNo <= numberOfPages; pageNo++)
			{
				ColumnText column = new ColumnText(stamper.getOverContent(pageNo));
				Rectangle rectPage1 = reader.getPageSize(pageNo);
				column.setSimpleColumn(rectPage1);
				column.go();
			}

			// Add running script name in header table
			tableHead.addCell(NewCellHeader(phrase, cell, fs25Bold, runningScriptName, 1, Element.ALIGN_LEFT));
			tableHead.addCell(NewCellHeader(phrase, cell, fs25Bold, "", 1, Element.ALIGN_LEFT));

			// Add header column name in step details table
			tableSteps.addCell(NewCellHeaderFirstColumn(phrase, cell, fs12Bold, "S.No", 1));
			tableSteps.addCell(NewCellHeaderColumn(phrase, cell, fs12Bold, "Step Description", 1));
			tableSteps.addCell(NewCellHeaderColumn(phrase, cell, fs12Bold, "Input Value", 1));
			tableSteps.addCell(NewCellHeaderColumn(phrase, cell, fs12Bold, "Expected Value", 1));
			tableSteps.addCell(NewCellHeaderColumn(phrase, cell, fs12Bold, "Actual Value", 1));
			tableSteps.addCell(NewCellHeaderLastColumn(phrase, cell, fs12Bold, "Status", 1));
		}
	}

	/**
	 * Start HTML report with mentioned configuration properties
	 * @throws IOException
	 */
	public void startHTMLReport() throws IOException
	{
		// HTML report file path
		String timeStamp = new SimpleDateFormat("dd_MM HH_mm_ss")
		.format(Calendar.getInstance().getTime());
		strHTMLFilePath = System.getProperty("user.dir") + "/target/custom-reports/Html_Report/Execution_Report_"+timeStamp+".html";

		// Add div for running script details
		runningScriptDivName = runningScriptName + "_"+ this.getRequiredDate(0, "yyyy_MM_dd_HHmmss", null);

		// If file not exist create new
		File reportFile = new File(strHTMLFilePath);
		if(!reportFile.exists())
		{
			exixtingHTML = false;
			// Create new directory and add required .css, .js and images files
			new File(System.getProperty("user.dir") + "/target/custom-reports/Html_Report").mkdirs();
			FileUtils.copyDirectory(new File(System.getProperty("user.dir") + "/src/main/resources/externalResources/Custom_Design_Files"), new File(System.getProperty("user.dir") + "/target/custom-reports/Html_Report"));
			reportFile.createNewFile();

			// Assume default encoding.
			fileWriter = new FileWriter(strHTMLFilePath);

			// Wrap FileWriter in BufferedWriter.
			bufferedWriter = new BufferedWriter(fileWriter);

			// If header logo is available copy the same in images folder and add in report
			String hederLogoPath = objCustomConfig.getProperty("custom.proj.header.logo").trim();
			String hederLogoExtension = "";
			if(!hederLogoPath.equals("") && hederLogoPath.contains("."))
			{
				hederLogoExtension = hederLogoPath.substring(hederLogoPath.lastIndexOf(".") +1);
				FileUtils.copyFile(new File(hederLogoPath),  new File(System.getProperty("user.dir") + "/target/custom-reports/Html_Report/IMG/headerlogo." + hederLogoExtension));
			}

			// Add html tags to page
			htmlPageOutput = " <!DOCTYPE html> \n" +
					" <head> \n"+ 
					" 	<link rel='stylesheet' type='text/css' href='./CSS/design.css'> \n"+ 
					"	<script type='text/javascript' src='./jquery/jquery-1.11.3.min.js'></script>"+
					"	<script src='./js/highcharts.js'></script>"+
					"	<script src='./js/highcharts-3d.js'></script>"+
					"	<script src='./js/modules/exporting.js'></script>"+
					" </head> \n"+ 
					" <body> \n"+ 
					" 	<table id='mainTable'> \n"+ 
					" 		<tbody> \n"+ 
					" 			<tr id='header'> \n"+ 
					" 				<td id='headertext'> \n"+ 
					" 					<b><u> <font color='#004080'>" + objCustomConfig.getProperty("custom.proj.description") + " </font></u></b> \n"; 

			if(!hederLogoPath.equals(""))
				htmlPageOutput = htmlPageOutput + " <div style='padding-right:20px;float:right'> <img src='./IMG/headerLogo." + hederLogoExtension + "' height='70' width='140' /> "+ " </i> "+" </div> \n"; 

			// Add summary table with list of test case with status 
			htmlPageOutput = htmlPageOutput + " 				</td> \n"+ 			
					" 			</tr> \n"+ 
					" 			<tr id='container'> \n"+ 
					" 				<td id='content'> \n"+ 
					" 					<div id='summary' class='info'> \n"+ 
					"				    	<table style='width: 100%;' align='center' > \n"+
					"							<tr > \n"+
					"								<td style='width: 70%;' align = 'left' valign='top'> \n"+
					" 									<table style='width: 100%;' class='expandCollpaseTable' align='center'> \n"+ 
					" 										<tr> \n"+ 
					" 											<th width='70%' align='center' class='expandCollpase'>Test Case</th> \n"+ 
					" 											<th width='30%' align='center' class='expandCollpase'>Status</th> \n"+ 
					" 										</tr> \n"+ 
					" 									</table> \n"+ 
					" 									<table style='width: 100%;' class='_customReportTestCaseTable' align='center'> \n"+ 
					" 										<tr> \n"+
					" 											<td width='70%' align='left' class='expandCollpase'> "+ " <a href='javascript:void(0)' onclick=\"document.getElementById('" + runningScriptDivName + "').style.display='block';document.getElementById('fade').style.display='block'\"> " + runningScriptName + "</a></td> \n"+ 
					" 											<td width='30%' align ='center' class='expandCollpase' id = '"+ runningScriptDivName +" _Status'>  </td> \n"+
					" 										</tr> \n"+ 
					" 									</table> \n"+ 
					"								</td > \n"+ 
					"								<td style='width: 30%;' align = 'left' valign='top'> \n"+
					"									<table width = '100%'><tr><td  align='center'><Select id='GraphType'>"+
					"										<option selected>3D Column</option> \n"+
					"										<option>Basic Column</option> \n"+
					"										<option>Pie</option> \n"+
					"										<option>Basic Bar</option> \n"+
					"									</Select> </td></tr><tr><td> \n"+
					"									<div id='_customReportGraph' name='_customReportGraph' style='width: 100%; margin: 0 auto'></div>"+
					"									</td></tr></table> \n"+
					"								</td > \n"+ 
					"							</tr> \n"+
					"						</table> \n"+
					" 					</div> \n"+ 
					" 				</td> \n"+ 
					" 			</tr> \n"+ 
					" 		</tbody> \n"+ 
					" 	</table> \n"+ 
					" 	<div style='display: none;' id='fade' class='_customReport_black_overlay'> </div> \n";

			// Add step details div with header to page 
			htmlPageOutput = htmlPageOutput + this.addScriptDetailsTable(runningScriptName, runningScriptDivName);
		}
		else // If file exist update existing  
		{
			exixtingHTML = true;
			// Add step details div with header to page
			htmlPageOutput = htmlPageOutput + this.addScriptDetailsTable(runningScriptName, runningScriptDivName);
		}
	}

	/**
	 *  Add pass step in excel report
	 */
	public void addExcelPassStep(String stepDesc, String inputValue, String exceptedValue, String actualValue)
	{
		sheetRow = currentReportSheet.createRow(intRowCounter);
		this.createSheetCell(0, String.valueOf(excelReportStepNumber));
		this.createSheetCell(1, stepDesc);
		this.createSheetCell(2, inputValue);
		this.createSheetCell(3, exceptedValue);
		this.createSheetCell(4, actualValue);
		this.createSheetPassStepCell(5);
		intRowCounter++;
		excelReportStepNumber++;
	}

	/**
	 *  Add pass step in PDF report
	 */
	public void addPDFPassStep(String stepDesc, String inputValue, String exceptedValue, String actualValue)
	{
		tableSteps.addCell(NewCellFirstColumn(phrase, cell, fs12, String.valueOf(pdfReportStepNumber), 1, Element.ALIGN_LEFT));
		tableSteps.addCell(NewCellColumn(phrase, cell, fs12, stepDesc, 1, Element.ALIGN_LEFT));
		tableSteps.addCell(NewCellColumn(phrase, cell, fs12, inputValue, 1, Element.ALIGN_LEFT));
		tableSteps.addCell(NewCellColumn(phrase, cell, fs12, exceptedValue, 1, Element.ALIGN_LEFT));
		tableSteps.addCell(NewCellColumn(phrase, cell, fs12, actualValue, 1, Element.ALIGN_LEFT));
		tableSteps.addCell(NewCellLastColumn(phrase, cell, fs12, "Pass", 1, Element.ALIGN_LEFT));
		pdfReportStepNumber++;
	}

	/**
	 *  Add pass step in HTML report
	 */
	public void addHTMLPassStep(String stepDesc, String inputValue, String exceptedValue, String actualValue)
	{
		String htmlOutput = " <tr> "+ 
				" <td align ='Center'>" + String.valueOf(htmlReportStepNumber) + "</td> \n"+
				" <td align ='left' >" + stepDesc + "</td> \n"+ 
				" <td align ='left'>" + inputValue + "</td> \n"+
				" <td align ='left'>" + exceptedValue + "</td> \n"+
				" <td align ='left'>" + actualValue + "</td> \n"+
				" <td align ='Center' ><img src='./IMG/logpass.png' alt='PASS' style='height:20px;width:20px;border:none'></img></td> \n"+ 
				" </tr> \n ";

		htmlPageOutput = htmlPageOutput + htmlOutput;
		htmlReportStepNumber++;
	}

	/**
	 *  Add fail step in excel report
	 */
	public void addExcelFailStep(String stepDesc, String inputValue, String exceptedValue, String actualValue)
	{
		sheetRow = currentReportSheet.createRow(intRowCounter);
		this.createSheetFailCell(0, String.valueOf(excelReportStepNumber));
		this.createSheetFailCell(1, stepDesc);
		this.createSheetFailCell(2, inputValue);
		this.createSheetFailCell(3, exceptedValue);
		this.createSheetFailCell(4, actualValue);
		this.createSheetFailStepCell(5);
		intRowCounter++;
		excelReportStepNumber++;
		intExcelReportNoOfFails++;
	}

	/**
	 *  Add fail step in PDF report
	 */
	public void addPDFFailStep(String stepDesc, String inputValue, String exceptedValue, String actualValue)
	{
		tableSteps.addCell(NewCellFirstColumn(phrase, cell, fs12Red, String.valueOf(pdfReportStepNumber), 1, Element.ALIGN_LEFT));
		tableSteps.addCell(NewCellColumn(phrase, cell, fs12Red, stepDesc, 1, Element.ALIGN_LEFT));
		tableSteps.addCell(NewCellColumn(phrase, cell, fs12Red, inputValue, 1, Element.ALIGN_LEFT));
		tableSteps.addCell(NewCellColumn(phrase, cell, fs12Red, exceptedValue, 1, Element.ALIGN_LEFT));
		tableSteps.addCell(NewCellColumn(phrase, cell, fs12Red, actualValue, 1, Element.ALIGN_LEFT));
		tableSteps.addCell(NewCellLastColumn(phrase, cell, fs12Red, "Fail", 1, Element.ALIGN_LEFT));
		pdfReportStepNumber++;
	}

	/**
	 *  Add fail step in HTML report
	 */
	public void addHTMLFailStep(String stepDesc, String inputValue, String exceptedValue, String actualValue)
	{
		String htmlOutput = " <tr> "+ 
				" <td align ='Center'>" + String.valueOf(htmlReportStepNumber) + "</td> \n"+
				" <td align ='left' >" + stepDesc + "</td> \n"+ 
				" <td align ='left'>" + inputValue + "</td>\n "+
				" <td align ='left'>" + exceptedValue + "</td> \n"+
				" <td align ='left'>" + actualValue + "</td> \n"+
				" <td align ='Center' ><img src='./IMG/logfail.png' alt='FAIL' style='height:20px;width:20px;border:none'></img></td> \n"+ 
				" </tr>  \n";
		htmlPageOutput = htmlPageOutput + htmlOutput;
		htmlReportStepNumber++;
		intHTMLReportNoOfFails++;
	}

	/**
	 *  Add info step in excel report
	 */
	public void addExcelInfoStep(String stepDesc, String infoMessage)
	{

		sheetRow = currentReportSheet.createRow(intRowCounter);
		this.createSheetCell(0, String.valueOf(excelReportStepNumber));
		this.createSheetCell(1, stepDesc);
		this.createSheetCell(2, infoMessage);
		this.createSheetInfoStepCell(5);
		intRowCounter++;
		excelReportStepNumber++;
	}

	/**
	 *  Add info step in PDF report
	 */
	public void addPDFInfoStep(String stepDesc, String infoMessage)
	{
		tableSteps.addCell(NewCellFirstColumn(phrase, cell, fs12, String.valueOf(pdfReportStepNumber), 1, Element.ALIGN_LEFT));
		tableSteps.addCell(NewCellColumn(phrase, cell, fs12, stepDesc, 1, Element.ALIGN_LEFT));
		tableSteps.addCell(NewCellColumn(phrase, cell, fs12, infoMessage, 1, Element.ALIGN_LEFT));
		tableSteps.addCell(NewCellColumn(phrase, cell, fs12, "", 1, Element.ALIGN_LEFT));
		tableSteps.addCell(NewCellColumn(phrase, cell, fs12, "", 1, Element.ALIGN_LEFT));
		tableSteps.addCell(NewCellLastColumn(phrase, cell, fs12, "Info", 1, Element.ALIGN_LEFT));
		pdfReportStepNumber++;
	}

	/**
	 *  Add info step in HTML report
	 */
	public void addHTMLInfoStep(String stepDesc, String infoMessage)
	{

		String htmlOutput = " <tr> "+ 
				" <td align ='Center'>" + String.valueOf(htmlReportStepNumber) + "</td> \n"+
				" <td align ='left' >" + stepDesc + "</td> \n"+ 
				" <td align ='left' colspan='3'>" + infoMessage + "</td> \n"+
				" <td align ='Center' ><img src='./IMG/loginfo.png' alt='INFO' style='height:20px;width:20px;border:none'></img></td> \n"+ 
				" </tr>  \n";
		htmlPageOutput = htmlPageOutput + htmlOutput;
		htmlReportStepNumber++;
	}

	/**
	 * End excel report
	 * @throws IOException
	 */

	public void endExcelReport() throws IOException  
	{
		if (intExcelReportNoOfFails > 0)
		{
			this.createSummaryFailStepCell(1);
		} else
		{
			this.createSummaryPassStepCell(1);
		}
		
		this.addSummarySheetLink();
		this.autoSetColumnWidth();
		// writing the workbook object into the file created in startReport()
		
		FileOutputStream fileOutputStream = new FileOutputStream(strExcelFilePath);
		workbook.write(fileOutputStream);
		fileOutputStream.flush();
		fileOutputStream.close();
		
		
	}

	/** 
	 * End PDF report
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void endPDFReport() throws DocumentException, IOException  
	{
		if(exixtingPdf)
		{
			Rectangle pagesize = reader.getPageSize(1).rotate();
			pagesize.setBorder(Rectangle.BOX);
			Rectangle rectPage = reader.getPageSize(1).rotate();
			rectPage.setBorder(Rectangle.BOX);
			stamper.insertPage(++numberOfPages, rectPage);
			ColumnText column = new ColumnText(stamper.getOverContent(numberOfPages));
			Rectangle rectPage1 = reader.getPageSize(1).rotate();
			column.setSimpleColumn(rectPage1);
			column.addElement(tableHead);
			column.addElement(tableSteps);
			int pagecount = numberOfPages;
			Rectangle rectPage2 = reader.getPageSize(1).rotate();
			int status = column.go();
			while (ColumnText.hasMoreText(status)) {
				status = triggerNewPage(stamper, pagesize, column, rectPage2, ++pagecount);
			}
			stamper.setFormFlattening(true);
			stamper.close();
			reader.close();

			File existingReportFile = new File(strPDFFilePath);
			existingReportFile.delete();
			new File(strTempPDFFilePath).renameTo(new File(strPDFFilePath));
		}
		else
		{
			document.add(tableSteps);
			document.close();
		}
	}

	/**
	 * End HTML report
	 * @throws IOException
	 */
	public void endHTMLReport() throws IOException  
	{
		if(exixtingHTML)
		{
			FileReader fileReader = new FileReader(strHTMLFilePath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			strTempHTMLFilePath = System.getProperty("user.dir") + "/target/custom-reports/Html_Report/Execution_Report_New.html";
			// Assume default encoding.
			fileWriter = new FileWriter(strTempHTMLFilePath);

			// Wrap FileWriter in BufferedWriter.
			bufferedWriter = new BufferedWriter(fileWriter);

			String currentLine = "";
			String putData = "";
			String pageContent = "";
			String tempContent = "";
			boolean blntestCaseTable = false;
			boolean blnFadeDiv = false;
			boolean blnCustomReportPassCount = false;
			boolean blnCustomReportFailCount = false;
			boolean blnHighChartDiv = false;
			boolean blnHighChartDivPass = false;
			boolean blnHighChartDivFail = false;
			currentLine = bufferedReader.readLine();

			while(currentLine != null)
			{ 
				 putData = currentLine;
				if(currentLine.contains("_customReportTestCaseTable"))
					blntestCaseTable = true;

				if(blntestCaseTable && currentLine.contains("</table>"))
				{ 
					putData =  	"<tr> \n"+
							"		<td width='70%' align='left' class='expandCollpase'> "+ " <a href='javascript:void(0)' onclick=\"document.getElementById('" + runningScriptDivName + "').style.display='block';document.getElementById('fade').style.display='block'\"> " + runningScriptName + "</a></td> \n"+ 
							"		<td width='30%' align ='center' class='expandCollpase' id = '"+ runningScriptDivName +" _Status'>  </td> \n"+
							"	</tr> \n" +
							"</table> \n";
					blntestCaseTable = false;
				}

				if(currentLine.contains("_customReport_black_overlay"))
					blnFadeDiv = true;

				if(blnFadeDiv)
				{
					htmlPageOutput = htmlPageOutput + "<script> document.getElementById('"+ runningScriptDivName +" _Status').innerHTML =";
					if(intHTMLReportNoOfFails > 0)
					{
						intTotalFail = 1;
						htmlPageOutput = htmlPageOutput + "'<span> <font color=#E03C31><b>Fail</b> </font></span>'";
					}
					else
					{
						intTotalPass = 1;
						htmlPageOutput = htmlPageOutput + "'<span> <font color=#7BB661><b>Pass</b> </font></span>'";
					}

					htmlPageOutput = htmlPageOutput + "</script></table>\n</div>\n</div>\n";

					putData = putData + "\n" + htmlPageOutput;
					blnFadeDiv = false;
				}

				if(currentLine.contains("_customReportPassCount"))
					blnCustomReportPassCount = true;

				if(blnCustomReportPassCount && currentLine.contains("value='"))
				{
					tempContent = currentLine.substring(currentLine.indexOf("value='"), currentLine.indexOf("'>"));
					tempContent = tempContent.substring(tempContent.indexOf("'") + 1);
					intTotalPassGraph = (Integer.parseInt(tempContent) + intTotalPass);
					if(intTotalPass > 0)
						putData = "<input type='hidden' name ='_customReportPassCount' id='_customReportPassCount' value='" + intTotalPassGraph + "'>\n";
					blnCustomReportPassCount = false;
				}

				if(currentLine.contains("_customReportFailCount"))
					blnCustomReportFailCount = true;

				if(blnCustomReportFailCount)
				{
					tempContent = currentLine.substring(currentLine.indexOf("value='"), currentLine.indexOf("'>"));
					tempContent = tempContent.substring(tempContent.indexOf("'") + 1);
					intTotalFailGraph = (Integer.parseInt(tempContent) + intTotalFail);
					if(intTotalFail > 0)
						putData = "<input type='hidden' name ='_customReportFailCount' id='_customReportFailCount' value='" + intTotalFailGraph + "'>\n";
					blnCustomReportFailCount = false;
				}

				if(currentLine.contains("jQuery(document).ready"))
					blnHighChartDiv = true;

				if(blnHighChartDiv && currentLine.contains("var passCount"))
					blnHighChartDivPass =  true;

				if(blnHighChartDiv && blnHighChartDivPass)
				{
			 	 	putData = " 	  var passCount = " + intTotalPassGraph + "; "+ "\n";
					blnHighChartDivPass = false;
				}

				if(blnHighChartDiv && currentLine.contains("var failCount"))
					blnHighChartDivFail =  true;

				if(blnHighChartDiv && blnHighChartDivFail)
				{
		 			putData =  " 	  var failCount = " + intTotalFailGraph +"; "+ "\n";
					blnHighChartDivFail = false;
					blnHighChartDiv = false;
				}

				pageContent = pageContent + (putData + "\n");
				currentLine = bufferedReader.readLine();
			}
			bufferedWriter.write(pageContent);
			bufferedReader.close();
			fileReader.close();
			bufferedWriter.close();
			fileWriter.close();

			File existingReportFile = new File(strHTMLFilePath);
			existingReportFile.delete();
			new File(strTempHTMLFilePath).renameTo(new File(strHTMLFilePath));
		}
		else
		{
			htmlPageOutput = htmlPageOutput + "<script> document.getElementById('"+ runningScriptDivName +" _Status').innerHTML =";
			if(intHTMLReportNoOfFails > 0)
			{
				intTotalFail = 1;
				intTotalFailGraph = 1;
				htmlPageOutput = htmlPageOutput + "'<span> <font color=#E03C31><b>Fail</b> </font></span>'";
			}
			else
			{
				intTotalPass = 1;
		 		intTotalPassGraph = 1;
				htmlPageOutput = htmlPageOutput + "'<span> <font color=#7BB661><b>Pass</b> </font></span>'";
			}

		 	htmlPageOutput = htmlPageOutput + "</script>\n</table>\n</div>\n</div>\n"+
					"<input type='hidden' name ='_customReportPassCount' id='_customReportPassCount' value='" + intTotalPass + "'>\n"+
					"<input type='hidden' name ='_customReportFailCount' id='_customReportFailCount' value='" + intTotalFail + "'>\n"+
					" <script type='text/javascript'> "+ "\n"+
					" $(function () { "+ "\n"+
					" jQuery(document).ready(function(){ "+ "\n"+
					" 	  var passCount = " + intTotalPassGraph + "; "+ "\n"+
					" 	  var failCount = " + intTotalFailGraph +"; "+ "\n"+
					" 	  var chart = new Highcharts.Chart({ "+ "\n"+
					" 	 chart: {renderTo: '_customReportGraph', margin: 75, options3d: {enabled: true,alpha: 20,beta: 33,depth: 50,viewDistance:25}}, "+ "\n"+  	
					" 		title: {text: 'Execution Status'}, "+ "\n"+
					" 		subtitle: {text: ''}, "+ "\n"+	
					" 		credits: {enabled: false}, "+ "\n"+
					" 		xAxis: {categories: ['']}, "+ "\n"+
					" 		yAxis: {title: {text: ''}}, "+ "\n"+
					" 		plotOptions: {column: {depth: 25}}, "+ "\n"+
					" 		series: [{type: 'column', name: 'Pass', color: '#33CC33', data: [['Pass', passCount ]]}, "+ "\n"+
					" 					{type: 'column', name: 'Fail', color: '#CC0000', data: [['Fail', failCount ]] }] "+ "\n"+
					" 		}); "+ "\n"+
					"   $('#GraphType').change(function() { "+ "\n"+
					" 	 var graphType = $('#GraphType option:selected').text(); "+ "\n"+
					"     if(graphType == '3D Column') "+ "\n"+
					" 	{ "+ "\n"+
					" 	  var chart = new Highcharts.Chart({ "+ "\n"+
					" 	 chart: {renderTo: '_customReportGraph', margin: 75, options3d: {enabled: true,alpha: 20,beta: 33,depth: 50,viewDistance:25}}, "+ "\n"+
					" 		title: {text: 'Execution Status'}, "+ "\n"+
					" 		subtitle: {text: ''}, "+ "\n"+
					" 		credits: {enabled: false}, "+ "\n"+
					" 		xAxis: {categories: ['']}, "+ "\n"+
					" 		yAxis: {title: {text: ''}}, "+ "\n"+
					" 		plotOptions: {column: {depth: 25}}, "+ "\n"+
					" 		series: [{type: 'column', name: 'Pass', color: '#33CC33', data: [['Pass', passCount ]]}, "+ "\n"+ 	
					" 					{type: 'column', name: 'Fail', color: '#CC0000', data: [['Fail', failCount ]] }] "+ "\n"+  	
					" 		}); "+ "\n"+ 
					"  	} "+ "\n"+
					" 	if(graphType == 'Pie') "+ "\n"+
					" 	{ "+ "\n"+
					" 	     $('#_customReportGraph').highcharts({ "+ "\n"+
					"             chart: {plotBackgroundColor: null,plotBorderWidth: null,plotShadow: false}, "+ "\n"+
					"             title: {    text: 'Execution Status'}, "+ "\n"+
					" 			credits: {enabled: false}, "+ "\n"+
					"             tooltip: {pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'}, "+ "\n"+
					"             plotOptions: {pie: {allowPointSelect: true,cursor: 'pointer',dataLabels: {enabled: true},showInLegend: true}}, "+ "\n"+
					"             series: [{type: 'pie',data: [['Pass', passCount ],['Fail', failCount ],]}] "+ "\n"+
					"         }); "+ "\n"+
					" 	}  "+ "\n"+
					" 	if(graphType == 'Basic Column') "+ "\n"+
					" 	{ "+ "\n"+
					" 	$('#_customReportGraph').highcharts({ "+ "\n"+
					"         chart: {type: 'column'}, "+ "\n"+
					"         title: {text: 'Execution Status'}, "+ "\n"+
					" 		credits: {enabled: false}, "+ "\n"+
					"         xAxis: {categories: ['']}, "+ "\n"+
					"         yAxis: {title: {text: ''}}, "+ "\n"+
					" 		series: [{type: 'column', name: 'Pass', color: '#33CC33', data: [['Pass', passCount ]]}, "+ "\n"+ 	
					" 					{type: 'column', name: 'Fail', color: '#CC0000', data: [['Fail', failCount ]] }]  "+ "\n"+ 
					"     }); "+ "\n"+
					" 	} "+ "\n"+
					" 	if(graphType == 'Basic Bar') "+ "\n"+
					" 	{ "+ "\n"+
					" 	$('#_customReportGraph').highcharts({ "+ "\n"+
					"         chart: { type: 'bar'}, "+ "\n"+
					"         title: {text: 'Execution Status'}, "+ "\n"+
					" 		credits: {enabled: false}, "+ "\n"+
					"         xAxis: {categories: ['']}, "+ "\n"+
					"         yAxis: {title: {text: ''}}, "+ "\n"+
					"        series: [{type: 'column', name: 'Pass', color: '#33CC33', data: [['Pass', passCount ]]}, "+ "\n"+ 	
					" 					{type: 'column', name: 'Fail', color: '#CC0000', data: [['Fail', failCount ]] }] "+ "\n"+  
					"     }); "+ "\n"+
					" 	} "+ "\n"+
					"   }); "+ "\n"+
					" }); "+ "\n"+
					" }); "+ "\n"+		 
					" </script> "+ "\n"+
					" </body>\n</html>\n"; 

			bufferedWriter.write(htmlPageOutput);
			bufferedWriter.close();
			fileWriter.close();
		}
	}

	/**
	 * 
	 * @param cellNumber
	 * @param value
	 * @param nevigationSheet
	 */
	public void createSummaryLinkCell(int cellNumber, String value, String nevigationSheet)
	{
		XSSFCell cell = summaryRow.createCell(cellNumber);
		cell.setCellValue(new XSSFRichTextString(value));
		CreationHelper createHelper = workbook.getCreationHelper();
		Hyperlink link = createHelper.createHyperlink(Hyperlink.LINK_DOCUMENT);
		link.setAddress("'" + nevigationSheet + "'!A1");
		cell.setHyperlink(link);
		cell.setCellStyle(getHlinkCellStyle());
	}

	private XSSFCellStyle getHlinkCellStyle()
	{
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		Font hlink_font = workbook.createFont();
		hlink_font.setUnderline(Font.U_SINGLE);
		hlink_font.setColor(IndexedColors.BLUE.getIndex());
		cellStyle.setFont(hlink_font);
		return cellStyle;
	}

	private XSSFCellStyle getHeaderCellStyle()
	{
		XSSFFont headerFont = workbook.createFont(); 
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerFont.setFontName("Arial");
		XSSFCellStyle cellStyle = workbook.createCellStyle(); 
		cellStyle.setFont(headerFont);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		return cellStyle;
	}

	private XSSFCellStyle getPassCellStyle()
	{
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		return cellStyle;
	}

	private XSSFCellStyle getInfoCellStyle()
	{
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		return cellStyle;
	}

	private XSSFCellStyle getFailCellStyle()
	{
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setFillForegroundColor(HSSFColor.RED.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		return cellStyle;
	}

	public void createSummaryPassStepCell(int cellNumber)
	{
		XSSFCell cell = summaryRow.createCell(cellNumber);
		cell.setCellValue(new XSSFRichTextString("PASS"));
		cell.setCellStyle(getPassCellStyle());
	}

	public void createSummaryFailStepCell(int cellNumber)
	{
		XSSFCell cell = summaryRow.createCell(cellNumber);
		cell.setCellValue(new XSSFRichTextString("FAIL"));
		cell.setCellStyle(getFailCellStyle());
	}

	private XSSFCellStyle getFailCellFontStyle()
	{
		XSSFFont failFont = workbook.createFont();
		failFont.setColor(HSSFColor.RED.index);
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(failFont);
		return cellStyle;
	}

	private XSSFCellStyle getcategoryCellStyle()
	{
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		return cellStyle;
	}

	public void createSheetHeaderCell(int cellNumber, String value)
	{
		XSSFCell cell = sheetRow.createCell(cellNumber);
		cell.setCellValue(new XSSFRichTextString(value));
		cell.setCellStyle(this.getHeaderCellStyle());
	}

	public void createSheetCell(int cellNumber, String value)
	{
		XSSFCell cell = sheetRow.createCell(cellNumber);
		cell.setCellValue(new XSSFRichTextString(value));
	}

	public void createSheetFailCell(int cellNumber, String value)
	{
		XSSFCell cell = sheetRow.createCell(cellNumber);
		cell.setCellValue(new XSSFRichTextString(value));
		cell.setCellStyle(getFailCellFontStyle());
	}

	public void createSheetPassStepCell(int cellNumber)
	{
		XSSFCell cell = sheetRow.createCell(cellNumber);
		cell.setCellValue(new XSSFRichTextString("PASS"));
		cell.setCellStyle(getPassCellStyle());
	}

	public void createSheetFailStepCell(int cellNumber)
	{
		XSSFCell cell = sheetRow.createCell(cellNumber);
		cell.setCellValue(new XSSFRichTextString("FAIL"));
		cell.setCellStyle(getFailCellStyle());
	}

	public void createSheetInfoStepCell(int cellNumber)
	{
		XSSFCell cell = sheetRow.createCell(cellNumber);
		cell.setCellValue(new XSSFRichTextString("INFO"));
		cell.setCellStyle(getInfoCellStyle());
	}

	public void createSheetCategoryCell(int cellNumber, String value)
	{
		XSSFCell cell = sheetRow.createCell(cellNumber);
		cell.setCellValue(new XSSFRichTextString(value));
		cell.setCellStyle(getcategoryCellStyle());
	}


	public void createSummaryHeaderCell(int cellNumber, String value)
	{
		XSSFCell cell = summaryRow.createCell(cellNumber);
		cell.setCellValue(new XSSFRichTextString(value));
		cell.setCellStyle(this.getHeaderCellStyle());
	}

	public void addSummarySheetLink()
	{
		XSSFRow navigationBar = currentReportSheet.createRow(intRowCounter);
		CreationHelper createHelper = workbook.getCreationHelper();

		XSSFCell cell = navigationBar.createCell(0);
		cell.setCellValue(new XSSFRichTextString(""));
		Hyperlink link = createHelper.createHyperlink(Hyperlink.LINK_DOCUMENT);
		link.setAddress("'TestSummary'!A1");
		cell.setHyperlink(link);
		cell.setCellStyle(getNavigationCellStyle());

		XSSFCell cell1 = navigationBar.createCell(1);
		cell1.setCellValue(new XSSFRichTextString("Go To Test Summary Sheet"));
		Hyperlink link1 = createHelper.createHyperlink(Hyperlink.LINK_DOCUMENT);
		link1.setAddress("'TestSummary'!A1");
		cell1.setHyperlink(link1);
		cell1.setCellStyle(getNavigationCellStyle());
		
	}

	private XSSFCellStyle getNavigationCellStyle()
	{
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		Font hlink_font = workbook.createFont();
		hlink_font.setUnderline(Font.U_SINGLE);
		hlink_font.setColor(IndexedColors.WHITE.getIndex());
		cellStyle.setFont(hlink_font);
		cellStyle.setFillForegroundColor(HSSFColor.INDIGO.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		return cellStyle;
	}

	public void autoSetColumnWidth()
	{
		summarySheet.autoSizeColumn(0);
		summarySheet.autoSizeColumn(1);
		currentReportSheet.autoSizeColumn(0);
		currentReportSheet.autoSizeColumn(1);
		currentReportSheet.autoSizeColumn(2);
		currentReportSheet.autoSizeColumn(3);
		currentReportSheet.autoSizeColumn(4);
		currentReportSheet.autoSizeColumn(5);
		currentReportSheet.autoSizeColumn(6);
		currentReportSheet.autoSizeColumn(7);
	}  

	public PdfPCell NewCellHeader(Phrase ph, PdfPCell cell, com.itextpdf.text.Font fs1, String strValue, Integer colSpan, Integer colAlign ) 
	{
		ph = new Phrase(strValue, fs1); 
		cell = new PdfPCell (new Paragraph (ph));
		cell.setHorizontalAlignment (colAlign);
		//cell.setMinimumHeight(35f);
		cell.setColspan(colSpan );
		cell.setBorder(Rectangle.NO_BORDER);
		return cell;
	}

	public PdfPCell NewCellHeaderFirstColumn(Phrase ph, PdfPCell cell, com.itextpdf.text.Font fs1, String strValue, Integer colSpan)
	{
		ph = new Phrase(strValue, fs1);
		cell = new PdfPCell (new Paragraph (ph));
		cell.setHorizontalAlignment (Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		//cell.setMinimumHeight(35f);
		cell.setColspan(colSpan );
		cell.setPadding (2.0f);
		cell.setGrayFill(0.9f);
		cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.TOP);
		return cell;
	}

	public PdfPCell NewCellHeaderColumn(Phrase ph, PdfPCell cell, com.itextpdf.text.Font fs1, String strValue, Integer colSpan) 
	{
		ph = new Phrase(strValue, fs1);
		cell = new PdfPCell (new Paragraph (ph));
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		cell.setHorizontalAlignment (Element.ALIGN_CENTER);
		cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.TOP);
		//cell.setMinimumHeight(35f);
		cell.setColspan(colSpan );
		cell.setPadding (2.0f);
		cell.setGrayFill(0.9f);
		return cell;
	}

	public PdfPCell NewCellHeaderLastColumn(Phrase ph, PdfPCell cell, com.itextpdf.text.Font fs1, String strValue, Integer colSpan) 
	{
		ph = new Phrase(strValue, fs1);
		cell = new PdfPCell (new Paragraph (ph));
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		cell.setHorizontalAlignment (Element.ALIGN_CENTER);
		cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM | Rectangle.TOP);
		//cell.setMinimumHeight(35f);
		cell.setColspan(colSpan );
		cell.setPadding (2.0f);
		cell.setGrayFill(0.9f);
		return cell;
	}

	public PdfPCell NewCellFirstColumn(Phrase ph, PdfPCell cell, com.itextpdf.text.Font fs1, String strValue, Integer colSpan, Integer colAlign)
	{
		ph = new Phrase(strValue, fs1);
		cell = new PdfPCell (new Paragraph (ph));
		cell.setHorizontalAlignment (Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		//cell.setMinimumHeight(35f);
		cell.setColspan(colSpan );
		cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.TOP);
		return cell;
	}

	public PdfPCell NewCellColumn(Phrase ph, PdfPCell cell, com.itextpdf.text.Font fs1, String strValue, Integer colSpan, Integer colAlign) 
	{
		ph = new Phrase(strValue, fs1);
		cell = new PdfPCell (new Paragraph (ph));
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		cell.setHorizontalAlignment (Element.ALIGN_CENTER);
		cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.TOP);
		//cell.setMinimumHeight(35f);
		cell.setColspan(colSpan );
		return cell;
	}

	public PdfPCell NewCellLastColumn(Phrase ph, PdfPCell cell, com.itextpdf.text.Font fs1, String strValue, Integer colSpan, Integer colAlign) 
	{
		ph = new Phrase(strValue, fs1);
		cell = new PdfPCell (new Paragraph (ph));
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		cell.setHorizontalAlignment (Element.ALIGN_CENTER);
		cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM | Rectangle.TOP);
		//cell.setMinimumHeight(35f);
		cell.setColspan(colSpan );
		return cell;
	}

	public int triggerNewPage(PdfStamper stamper, Rectangle pagesize, ColumnText column, Rectangle rect, int pagecount)   
	{
		try 
		{
			stamper.insertPage(pagecount, pagesize);
			PdfContentByte canvas = stamper.getOverContent(pagecount);
			column.setCanvas(canvas);
			column.setSimpleColumn(rect);
			return column.go();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	public String addScriptDetailsTable(String runningScriptName, String runningScriptDivName)
	{
		String strScriptDetails = ""+
				" 	<div style='display: none;' id='" + runningScriptDivName + "' class='white_content'> \n"+ 
				" 		<table id='testNameTable'> \n"+   
				" 			<tr> \n"+ 
				" 				<td align ='center' width ='80%'> " + runningScriptName + " </td> \n"+ 
				" 				<td align ='right' width ='20%'> <button onclick=\"document.getElementById('fade').style.display='none';document.getElementById('" + runningScriptDivName + "').style.display='none'\">Close</button></td> \n"+
				" 			</tr> \n"+
				" 		</table> \n"+
				" 		<div style='width:100%; overflow:auto;height:90%;'> \n"+
				" 		<table id='tableStyle' width='100%' class='chartStyle' > \n"+ 
				" 			<thead> \n"+  
				" 				<tr> \n"+
				" 					<th width='5%' align ='center'  class='expandCollpase'>S.No</th> \n"+
				" 					<th width='60%' align='Center' class='expandCollpase'>Step Description</th> \n"+
				" 					<th width='10%' align ='center'  class='expandCollpase'>Input Value</th> \n"+
				" 					<th width='10%' align ='center'  class='expandCollpase'>Expected Value</th> \n"+
				" 					<th width='10%' align ='center'  class='expandCollpase'>Actual Value</th> \n"+
				" 					<th width='5%' align ='center'  class='expandCollpase'>Status</th> \n"+
				" 				</tr> \n"+
				" 			</thead> \n";
		return strScriptDetails;
	}

	/**
	 * @Method		: getRequiredDate
	 * @Description	: This method will give require date
	 * @param		: incrfementDateByDays Number by which user want increase date 
	 * @param		: sExpectedDateFormat - User expected date format
	 * 					eg. 9 april 2014 --- dd/MM/yyyy -> 09/04/2015, dd-MM-yyyy -> 09-04-2015
	 */
	public String getRequiredDate(int incrementDays, String expectedDateFormat, String timeZoneId)
	{
		try 
		{
			DateFormat dateFormat;
			Calendar calendar = Calendar.getInstance();
			dateFormat = new SimpleDateFormat(expectedDateFormat);
			if(timeZoneId != null && ! timeZoneId.equals(""))
				dateFormat.setTimeZone(TimeZone.getTimeZone(timeZoneId));
			calendar.add(Calendar.DAY_OF_MONTH, incrementDays);
			Date tomorrow = calendar.getTime();
			String formattedDate = dateFormat.format(tomorrow);
			return formattedDate;
		}
		catch (Exception exception) 
		{
			exception.printStackTrace();
			return null;
		}
	}
}
