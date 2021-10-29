package com.demo.webdriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;

public class LocalDriverManager {
	/**
	 * Drivers pool
	 */
	private static List<LocalDriverFactory> webDriverThreadPool = Collections.synchronizedList(new ArrayList<>());

	/**
	 * Driver thread
	 */
	private static InheritableThreadLocal<LocalDriverFactory> driverThread;
	
	/**
	 * Get driver from thread
	 *
	 * @return
	 */
	public static WebDriver getDriver(final BrowserType browserType) {
		 switch (browserType) {
         case CHROME:        	 
         default:
        	 LocalDriverManager.setDriverThread(BrowserType.CHROME);
        	 return driverThread.get().getDriver();
         case FIREFOX:
        	 LocalDriverManager.setDriverThread(BrowserType.FIREFOX);
        	 return driverThread.get().getDriver();
		 }		
		
	}
	/**
	 * Set driver thread
	 *
	 * @param browserType
	 */
	public static void setDriverThread(final BrowserType browserType) {
		driverThread = new InheritableThreadLocal<LocalDriverFactory>() {
			@Override
			protected LocalDriverFactory initialValue() {
				LocalDriverFactory localDriverFactory = new LocalDriverFactory(browserType);
				webDriverThreadPool.add(localDriverFactory);
				System.out.println("New Driver instance has been created!");
				return localDriverFactory;
			}
		};
	}
}
