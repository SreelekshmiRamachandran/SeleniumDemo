package com.demo.webdriver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.CapabilityType;

import lombok.Getter;

public class LocalDriverFactory {
	 @Getter
	    private WebDriver driver;
	   	    
	    public LocalDriverFactory(BrowserType browserType) {
	        driver = createInstance(browserType);
	    }

	    /**
	     * Creates a new LocalDriver object.
	     *
	     * @param browserType the webdriver type
	     * @return the web driver
	     */
	    public WebDriver createInstance(final BrowserType browserType) {	
	    	switch(browserType)
	    	{
	    	case CHROME:	    		
	    	default: 
	    		return getChromeDriver(BrowserType.CHROME.getDriverPath());
	    	case FIREFOX:
	    		return getFirefoxDriver(BrowserType.FIREFOX.getDriverPath());
	    	}
	           
	        }
	    
	    
	    /**
	     * Creates a new ChromeDriver object.
	     *
	     * @param driverPath the driver path
	     * @return the web driver
	     */
	    private ChromeDriver getChromeDriver(final String driverPath) {
	        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, driverPath);
	        final ChromeOptions options = new ChromeOptions();
	        options.addArguments("start-maximized");

	        Map <String, Object> prefs = new HashMap <>();
	        prefs.put("credentials_enable_service", false);
	        prefs.put("profile.password_manager_enabled", false);
	        prefs.put("profile.default_content_settings.popups", 0);
	      //  prefs.put("profile.default_content_setting_values.notifications", 2);  //added by sandhya on 23/4/2019 for Salesforce application
	        options.setExperimentalOption("prefs", prefs);

	        options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
	        options.setCapability("chrome_binary", driverPath);
	        options.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
	        options.setCapability("chrome.switches", Arrays.asList("--incognito"));
	        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
	        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	        options.addArguments("--allow-running-insecure-content");
	        options.addArguments("--disable-gpu");
	        WebDriver driver = new ChromeDriver(options);
	        driver.manage().deleteAllCookies();
	        driver.manage().window().maximize();

	        return (ChromeDriver) driver;
	    }

	    /**
	     * Creates a new FirefoxDriver object.
	     *
	     * @param driverPath the driver path
	     * @return the web driver
	     */
	    private FirefoxDriver getFirefoxDriver(final String driverPath) {
	        System.setProperty(GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY, driverPath);
	        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
	        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
	        FirefoxProfile profile = new FirefoxProfile();
	        profile.setPreference("security.mixed_content.block_active_content", false);
	        profile.setPreference("security.mixed_content.block_display_content", true);
	        profile.setPreference("devtools.jsonview.enabled", false);
	        FirefoxOptions options = new FirefoxOptions();
	        options.setProfile(profile);
	        WebDriver driver = new FirefoxDriver(options);
	        driver.manage().deleteAllCookies();
	        driver.manage().window().maximize();
	        return (FirefoxDriver) driver;
	    }
	    
}
