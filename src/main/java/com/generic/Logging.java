package com.generic;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.adobe.genie.executor.GenieHelper;

@SuppressWarnings("unused")
/**
 * Logging: Sets up log file in which logs are to be printed. 
 * @author Automation Tester (SQS)
 *
 */
public class Logging
{
	
	
	/**
	 * Inputs the text in log file
	 * 
	 * @param logText : text to be printed in log file
	 * @throws Exception : Exception
	 */
	public static void logEntry(String slogText) throws Exception
	{
		String strLogFilePath;
		String timeStamp = new SimpleDateFormat("dd_MM_YYYY")
		.format(Calendar.getInstance().getTime());
		strLogFilePath = System.getProperty("user.dir") + "/Logs"+timeStamp+".log";
		String slogFile = strLogFilePath;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		String formattedDate = sdf.format(date);
		File file = new File(slogFile);
		FileWriter fileWritter = new FileWriter(slogFile, true);
		BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		
		bufferWritter.write("Test Environment Summary");		
		bufferWritter.write(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");		
		bufferWritter.write("");		
		bufferWritter.write("Environment         : " + XMLInterface.getEnvKeyValue("TESTENVIRONMENT"));		
		bufferWritter.write("Browser         	   : " + XMLInterface.getEnvKeyValue("BROWSER"));	
		bufferWritter.write("URL        	   : " + XMLInterface.getEnvKeyValue("URL"));	
		bufferWritter.write("");		
		bufferWritter.write("");		
		bufferWritter.write(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");		
		bufferWritter.write("");		
		bufferWritter.write("Execution started....");		
		bufferWritter.write(formattedDate + " => " + slogText);
		bufferWritter.newLine();
		bufferWritter.close();
	}

	/**
	 * Capture screenshot files and store it in defined path.
	 * 
	 * @author Automation Tester (SQS)
	 * @param no parameters
	 * @exception Exception : Exception
	 * @return No return value.
	 *//*
	public static void captureScreenShot() throws Exception
	{
		try
		{
			String sdateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss-a").format(Calendar.getInstance().getTime());
			String sbrowserType = XMLInterface.getEnvKeyValue("BROWSER");
			if (sbrowserType.equals("Firefox"))
			{
				String imagePath = PropertiesAndConstants.screenShotPath + "\\"
						+ XMLInterface.getEnvKeyValue("SCREENSHOTNAME") + "_" + sdateFormat;
				PropertiesAndConstants.screenShotName = imagePath;
				new com.adobe.genie.executor.GenieHelper();
				GenieHelper.captureScreenShot(PropertiesAndConstants.screenShotPath + "\\",
						XMLInterface.getEnvKeyValue("SCREENSHOTNAME") + "_" + sdateFormat);
			} else
			{
				Logging.logEntry("Capture screen shot will not support for the browser : " + sbrowserType);
			}
		} catch (Exception e)
		{
			Logging.logEntry(e.getMessage());
		}
	}*/
}
