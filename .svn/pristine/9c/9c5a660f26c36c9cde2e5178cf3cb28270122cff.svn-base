package com.generic;

import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;

/**
 * Appium Manager - this class contains method to start and stops appium server  
 * @author		  : Automation Tester (SQS)  
 */
public class AppiumManager 
{
 	/**
	 * start appium server with  : appium ip, appium port 
	 */
	public void startAppium(String appiumIP, String appiumPort) 
	{
		CommandLine command = new CommandLine(
				"/Applications/Appium.app/Contents/Resources/node/bin/node");
		command.addArgument(
				"/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js",
				false);
		command.addArgument("--address", false);
		command.addArgument("127.0.0.1");
		command.addArgument("--port", false);
		command.addArgument("4723");
		command.addArgument("--full-reset", false);
		//command.addArgument("--log");
		//command.addArgument("/src/test/resources/log.txt");
		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		DefaultExecutor executor = new DefaultExecutor();
		executor.setExitValue(1);
		try {
			executor.execute(command, resultHandler);
			Thread.sleep(5000);
			System.out.println("Appium server started.");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * stop appium server 
	 */
	public void stopServer() 
	{
		String[] command = { "/usr/bin/killall", "-KILL", "node" };
		try 
		{
			Runtime.getRuntime().exec(command);
			System.out.println("Appium server stopped.");
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
