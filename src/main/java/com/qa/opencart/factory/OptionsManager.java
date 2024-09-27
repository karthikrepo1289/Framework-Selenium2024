package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	private Properties prop;
	private ChromeOptions chromeOpt;
	private FirefoxOptions firefoxOpt;
	private EdgeOptions edgeOpt;

	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {
		chromeOpt = new ChromeOptions();
		//mvn clean install -Dheadless="true"
		String headLess = System.getProperty("headless");
		if(headLess !=null) {
			if (Boolean.parseBoolean(headLess))
				chromeOpt.addArguments("--headless");
		}
		else {
		if (Boolean.parseBoolean(prop.getProperty("headless")))
			chromeOpt.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito")))
			chromeOpt.addArguments("incognito");
		}

		return chromeOpt;
	}

	public FirefoxOptions getFirefoxOptions() {
		firefoxOpt = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless")))
			firefoxOpt.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito")))
			firefoxOpt.addArguments("incognito");
		
		return firefoxOpt;

	}

	public EdgeOptions getEdgeOptions() {
		edgeOpt = new EdgeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless")))
			edgeOpt.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito")))
			edgeOpt.addArguments("incognito");

		return edgeOpt;
	}
}
