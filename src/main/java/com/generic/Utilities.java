package com.generic;

import io.appium.java_client.AppiumDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * @ScriptName : Utilities
 * @Description : This class contains utlities function
 * @Author : Automation Tester (SQS)
 */
public class Utilities
{
	private WebDriver webDriver;
	private AppiumDriver<?> appiumDriver;
	private Properties objConfig;
	private CustomReporter objReporter;
	private Hashtable<String, String> testData;

	public Utilities(Pojo objPojo)
	{
		webDriver = objPojo.getDriver();
		appiumDriver = objPojo.getAppiumDriver();
		objConfig = objPojo.getObjConfig();
		objReporter = objPojo.getCustomeReporter();
		testData = objPojo.getDataPoolHashTable();
	}

	/**
	 * @Method : waitForElementWeb
	 * @Description : Waits for the specified amount of [timeInMilliseconds].
	 * @param : webelement - web element locator
	 * @param : timeUnitSeconds - wait time seconds
	 * @Author : Automation Tester (SQS)
	 */
	public void waitForElementWeb(By locator)
	{
		try
		{
			int maxWait = Integer.parseInt(objConfig.getProperty("elementPresenceWait").trim());
			WebDriverWait wait = new WebDriverWait(webDriver, maxWait);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			System.out.println("Wait complete for" + locator);
		} catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}

	/**
	 * @Method : waitForElementMobile
	 * @Description : Waits for the specified amount of [timeInMilliseconds].
	 * @param : webelement - web element locator
	 * @param : timeUnitSeconds - wait time seconds
	 * @Author : Automation Tester (SQS)
	 */
	public void waitForElementMobile(By locator)
	{
		try
		{
			int maxWait = Integer.parseInt(objConfig.getProperty("elementPresenceWait").trim());
			WebDriverWait wait = new WebDriverWait(appiumDriver, maxWait);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}

	/**
	 * @Method : waitFor
	 * @Description : Waits for the specified amount of [timeInMilliseconds].
	 * @param : timeUnitSeconds - wait time seconds
	 * @Author : Automation Tester (SQS)
	 */
	public void waitFor(int timeUnitSeconds)
	{
		try
		{
			Thread.sleep(TimeUnit.MILLISECONDS.convert(timeUnitSeconds, TimeUnit.SECONDS));
		} catch (Exception exception)
		{
			throw new RuntimeException(exception);
		}
	}

	/**
	 * @Method : getRequiredDate
	 * @Description : This method will give require date
	 * @param : incrfementDateByDays Number by which user want increase date
	 * @param : sExpectedDateFormat - User expected date format eg. 9 april 2014
	 *        --- dd/MM/yyyy -> 09/04/2015, dd-MM-yyyy -> 09-04-2015
	 * @param : timeZoneId - Time Zone
	 * @Author : Automation Tester (SQS)
	 */
	public String getRequiredDay(int incrementDays, String expectedDateFormat, String timeZoneId)
	{
		try
		{
			DateFormat dateFormat;
			Calendar calendar = Calendar.getInstance();
			dateFormat = new SimpleDateFormat(expectedDateFormat);
			if (timeZoneId != null && !timeZoneId.equals(""))
				dateFormat.setTimeZone(TimeZone.getTimeZone(timeZoneId));
			calendar.add(Calendar.DAY_OF_MONTH, incrementDays);
			Date tomorrow = calendar.getTime();
			String formattedDate = dateFormat.format(tomorrow);
			return formattedDate;
		} catch (Exception exception)
		{
			exception.printStackTrace();
			return null;
		}
	}

	/**
	 * @Method : getRequiredDate
	 * @Description : This method will give require date
	 * @param : incrfementDateByDays Number by which user want increase date
	 * @param : sExpectedDateFormat - User expected date format eg. 9 april 2014
	 *        --- dd/MM/yyyy -> 09/04/2015, dd-MM-yyyy -> 09-04-2015
	 * @param : timeZoneId - Time Zone
	 * @Author : Automation Tester (SQS)
	 */
	public String getRequiredMonth(int incrementMonth, String expectedDateFormat, String timeZoneId)
	{
		try
		{
			DateFormat dateFormat;
			Calendar calendar = Calendar.getInstance();
			dateFormat = new SimpleDateFormat(expectedDateFormat);
			if (timeZoneId != null && !timeZoneId.equals(""))
				dateFormat.setTimeZone(TimeZone.getTimeZone(timeZoneId));
			calendar.add(Calendar.MONTH, incrementMonth);
			Date tomorrow = calendar.getTime();
			String formattedDate = dateFormat.format(tomorrow);
			return formattedDate;
		} catch (Exception exception)
		{
			exception.printStackTrace();
			return null;
		}
	}

	/**
	 * @Method : getFormatedDate
	 * @Description : This method will converted date into excepted date format
	 * @Author : Automation Tester (SQS)
	 */
	public String getFormatedDate(String date, String originalDateFormat, String expectedDateFormat)
	{
		try
		{
			DateFormat inputFormatter = new SimpleDateFormat(originalDateFormat);
			Date originalDate = inputFormatter.parse(date);
			DateFormat outputFormatter = new SimpleDateFormat(expectedDateFormat);
			String expectedDate = outputFormatter.format(originalDate);
			return expectedDate;
		} catch (Exception exception)
		{
			exception.printStackTrace();
			return null;
		}
	}

	/**
	 * @Method : getRequiredDate
	 * @Description : This method will give require Minute
	 * @param : incrfementDateByDays Number by which user want increase date
	 * @param : sExpectedDateFormat - User expected date format eg. 9 april 2014
	 *        --- dd/MM/yyyy -> 09/04/2015, dd-MM-yyyy -> 09-04-2015
	 * @param : timeZoneId - Time Zone
	 * @Author : Automation Tester (SQS)
	 */
	public String getRequiredMinute(int incrementMinute, String expectedDateFormat, String timeZoneId)
	{
		try
		{
			DateFormat dateFormat;
			Calendar calendar = Calendar.getInstance();
			dateFormat = new SimpleDateFormat(expectedDateFormat);
			if (timeZoneId != null && !timeZoneId.equals(""))
				dateFormat.setTimeZone(TimeZone.getTimeZone(timeZoneId));
			calendar.add(Calendar.MINUTE, 1);
			Date tomorrow = calendar.getTime();
			String formattedDate = dateFormat.format(tomorrow);
			return formattedDate;
		} catch (Exception exception)
		{
			exception.printStackTrace();
			return null;
		}
	}

	/**
	 * @Method : modifyDaysFromDate
	 * @Description : Modifying(Add/Subtract) days from a date
	 * @Author : Vaishali bhad
	 */
	public String modifyDaysFromDate(String date, int daysToSub, String expectedDateFormat)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat(expectedDateFormat);
		Calendar c = Calendar.getInstance();
		try
		{
			c.setTime(dateFormat.parse(date));
		} catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.add(Calendar.DATE, daysToSub);

		String convertedDate = dateFormat.format(c.getTime());
		return convertedDate;
	}

	/**
	 * @Method : copyFileUsingStream
	 * @Description : copy files
	 * @param : Soure file path
	 * @param : destination file path
	 * @Author : Automation Tester (SQS)
	 */
	public void copyFileUsingStream(String sourceFilePath, String destinationFilePath)
	{
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try
		{
			inputStream = new FileInputStream(new File(sourceFilePath));
			outputStream = new FileOutputStream(new File(destinationFilePath));
			byte[] buffer = new byte[1024];
			int length;
			while ((length = inputStream.read(buffer)) > 0)
			{
				outputStream.write(buffer, 0, length);
			}
		} catch (Exception exception)
		{
			exception.printStackTrace();

		} finally
		{
			try
			{
				inputStream.close();
				outputStream.close();
			} catch (IOException iOException)
			{
				iOException.printStackTrace();
			}
		}
	}

	/**
	 * @Method : logReporter
	 * @Description : Reporter method
	 * @param : Step - Step description, resultLog - result log pass/fail
	 *        (true/false), includeMobile - result for mobile(true/false)
	 * @Author : Automation Tester (SQS)
	 */
	@Step("{0}")
	public void logReporter(String step, boolean resultLog, boolean includeMobile)
	{
		try
		{
			String strLog = step;
			this.addAssertTakeScreenShot(step, strLog, "", "", "", resultLog, includeMobile);

		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @Method : logReporter
	 * @Description : Reporter method
	 * @param : Step - Step description, inputValue - Input value, resultLog -
	 *        result log pass/fail (true/false), includeMobile - result for
	 *        mobile(true/false)
	 * @Author : Automation Tester (SQS)
	 */
	@Step("{0} - {1}")
	public void logReporter(String step, String inputValue, boolean resultLog, boolean includeMobile)
	{
		String strLog = step + "|| Input Value : " + inputValue;
		this.addAssertTakeScreenShot(step, strLog, inputValue, "", "", resultLog, includeMobile);
	}

	/**
	 * @Method : logReporter
	 * @Description : Reporter method
	 * @param : Step - Step description, expectedValue - verification point
	 *        expected value, actualValue - verification point actual value,
	 *        resultLog - result log pass/fail (true/false), includeMobile -
	 *        result for mobile(true/false)
	 * @Author : Automation Tester (SQS)
	 */
	@Step("{0} - {2} - {3}")
	public void logReporter(String step, String expectedValue, String actualValue, boolean resultLog, boolean includeMobile)
	{
		String strLog = step + " || Expected Result : " + expectedValue + " || Actual Result : " + actualValue;
		this.addAssertTakeScreenShot(step, strLog, "", expectedValue, actualValue, resultLog, includeMobile);
	}

	/**
	 * @Method : addAssertTakeScreenShot
	 * @Description :
	 * @param :
	 * @Author : Automation Tester (SQS)
	 */
	public void addAssertTakeScreenShot(String step, String strLog, String inputValue, String expectedValue, String actualValue, boolean resultLog, boolean includeMobile)
	{
		try
		{
			if (objConfig.getProperty("test.custom.reporter").equalsIgnoreCase("true"))
			{
				if (resultLog)
					objReporter.pass(step, inputValue, expectedValue, actualValue);
				else
					objReporter.fail(step, inputValue, expectedValue, actualValue);
			}

			String fileName = Long.toString(System.currentTimeMillis()) + ".png";
			String fileWithPath = System.getProperty("user.dir") + "\\" + objConfig.getProperty("screenshot.dir") + "\\ScreenShot\\" + fileName;
			if (resultLog)
			{
				Reporter.log("Step >> " + strLog);

				Logging.logEntry(step + "" + strLog);

				Assert.assertTrue(true);
			} else
			{
				Reporter.log("Step >> " + strLog);
				Logging.logEntry(step + "" + strLog);
				if (includeMobile)
					this.takeScreenShot(this.appiumDriver, fileWithPath);
				else
					this.takeScreenShot(this.webDriver, fileWithPath);
				Assert.assertTrue(false);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @Method : takeScreenShot
	 * @Description : Take Screen shot for given web driver.
	 * @Author : Automation Tester (SQS)
	 */
	public void takeScreenShot(WebDriver webDriver, String fileWithPath)
	{
		TakesScreenshot scrShot = ((TakesScreenshot) webDriver);
		// Call getScreenshotAs method to create image file
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		// Move image file to new destination
		File destFile = new File(fileWithPath);
		// Copy file at destination
		try
		{
			FileUtils.moveFile(srcFile, destFile);
			this.fileToByte(destFile);
		} catch (IOException iOException)
		{
			iOException.printStackTrace();
		}
	}

	/**
	 * @Method : takeScreenShot
	 * @Description : Take Screen shot for given appiumDriver.
	 * @Author : Automation Tester (SQS)
	 */
	public void takeScreenShot(AppiumDriver<?> appiumDriver, String fileWithPath)
	{
		TakesScreenshot scrShot = ((TakesScreenshot) appiumDriver);
		// Call getScreenshotAs method to create image file
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		// Move image file to new destination
		File destFile = new File(fileWithPath);
		// Copy file at destination
		try
		{
			FileUtils.moveFile(srcFile, destFile);
			this.fileToByte(destFile);
		} catch (IOException iOException)
		{
			iOException.printStackTrace();
		}
	}

	/**
	 * @Method : fileToByte
	 * @Description : Converts image file to byte array for allure.
	 * @Author : Automation Tester (SQS)
	 * @throws : IOException
	 */
	@Attachment(value = "Screenshot", type = "image/png")
	private byte[] fileToByte(File file) throws IOException
	{
		if (file != null)
			return Files.readAllBytes(Paths.get(file.getPath()));
		else
			return new byte[0];
	}

	/**
	 * @Method: dpString
	 * @Description: this method returns data from the the previously loaded
	 *               datapool
	 * @param columnHeader
	 *            - excel file header column name
	 * @return - value for corresponding header
	 * @Author: Automation Tester (SQS) @CreationDate: 27 April 2015
	 *          @ModifiedDate:
	 */
	public String dpString(String columnHeader)
	{
		Hashtable<String, String> dataPoolHashTable = testData;
		try
		{
			if (dataPoolHashTable.get(columnHeader) == null)
				return "";
			else
			{
				return dataPoolHashTable.get(columnHeader);
			}
		} catch (Exception exception)
		{
			throw new RuntimeException(exception);
		}
	}

}
