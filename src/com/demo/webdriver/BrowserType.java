package com.demo.webdriver;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BrowserType {
	CHROME("../SeleniumDemo/src/com/demo/resources/chromedriver.exe"),
	FIREFOX("../SeleniumDemo/src/com/demo/resources/geckodriver.exe");	
	private String driverPath;
}
