package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;

public class BaseTest {
	protected WebDriver driver;
	protected Properties prop;
	protected DriverFactory driverFactory;
	protected LoginPage loginPage;
	protected AccountPage accntPage;
	protected ProductInfoPage prodInfoPage;
	protected RegisterPage registerPage;
	protected SoftAssert softAssert;

	@Parameters({"browser"})
	@BeforeTest
	public void setUp(String browser) {
		driverFactory = new DriverFactory();
		prop = driverFactory.initConfigFile();
		
		if(browser !=null) {
		prop.setProperty("browser", browser);
		}
		
		driver = driverFactory.initDriver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
