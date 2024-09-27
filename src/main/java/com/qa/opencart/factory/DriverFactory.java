package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.google.common.io.Files;
import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {
	private WebDriver driver;
	private Properties prop;
	private OptionsManager optManager;
	public static ThreadLocal<WebDriver> tLocal = new ThreadLocal<WebDriver>();
	public static String isHighLight;

	public WebDriver initDriver(Properties property) {
		String browserName = property.getProperty("browser");
		isHighLight = property.getProperty("highlight");
		// mvn clean install -Dbrowser="chrome" -Denv="Stage"
		// String browserName = System.getProperty("browser");
		optManager = new OptionsManager(property);

		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			tLocal.set(new ChromeDriver(optManager.getChromeOptions()));
			break;

		case "firefox":
			tLocal.set(new FirefoxDriver(optManager.getFirefoxOptions()));
			break;

		case "edge":
			tLocal.set(new EdgeDriver(optManager.getEdgeOptions()));
			break;

		case "safari":
			tLocal.set(new SafariDriver());
			break;

		default:
			throw new FrameworkException("Please pass the right browser " + browserName);
		}
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(property.getProperty("url"));

		return getDriver();
	}

	public static WebDriver getDriver() {
		return tLocal.get();
	}

	// To load property file based on the parameter passing from maven command.
	public Properties initConfigFile() {
		prop = new Properties();
		FileInputStream fis = null;
		String path = System.getProperty("user.dir") + "\\src\\test\\resource\\configuration\\";

		// mvn clean install -Denv="stage"
		// mvn clean install
		String env = System.getProperty("env");

		try {
			if (env == null) {
				System.out.println("We are running in default environment stage.");
				fis = new FileInputStream("./src/test/resource/configuration/stage.properties");
				prop.load(fis);
			}

			else {

				switch (env.toLowerCase().trim()) {
				case "stage":
					fis = new FileInputStream(path + "stage.properties");
					prop.load(fis);
					break;
				case "uat":
					fis = new FileInputStream(path + "uat.properties");
					prop.load(fis);
					break;
				default:
					System.out.println("Please pass right environment.... " + env);
					throw new FrameworkException("Please pass right environment..." + env);
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}

		return prop;
	}

	public static String getScreenshot(String methodName) {
		File source = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		
		String destPath = System.getProperty("user.dir")+"/screenshot/"+methodName+"_"+System.currentTimeMillis()+".png";
		File destination = new File(destPath); 
		try {
			Files.copy(source, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return destPath;
	}
}
