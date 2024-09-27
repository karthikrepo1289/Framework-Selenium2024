package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil elmntUtil;

	// Locators information
	private By txtboxUserName = By.id("input-email");
	private By txtboxPassword = By.id("input-password");
	private By btnLogin = By.xpath("//input[@value='Login']");
	private By loginTitle = By.xpath("//h2[text()='Returning Customer']");
	private By registerLink = By.xpath("//a[text()='Register' and @class]");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elmntUtil = new ElementUtil(driver);
	}

	public String getTitle() {
		return driver.getTitle();
	}
	
	public String getPageURL() {
		return driver.getCurrentUrl();
	}
	
	public String getLoginTitle() {
		return elmntUtil.doElementGetText(loginTitle);
	}
	
	public AccountPage login(String userName, String passWord) {
		elmntUtil.waitForVisibilityOfElement(txtboxUserName, 10).sendKeys(userName);
		elmntUtil.doSendKeys(txtboxPassword, passWord);
		elmntUtil.doClick(btnLogin);
		
		return new AccountPage(driver);
	}
	
	public RegisterPage navigateToRegistration() {
		elmntUtil.waitForVisibilityOfElement(registerLink, AppConstants.MED_WAIT).click();
		
		return new RegisterPage(driver);
	}
}
