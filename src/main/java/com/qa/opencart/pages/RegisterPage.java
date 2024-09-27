package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil elmntUtil;
	
	private By firstName = By.cssSelector("#input-firstname");
	private By lastName = By.cssSelector("#input-lastname");
	private By email = By.cssSelector("#input-email");
	private By telephone = By.cssSelector("#input-telephone");
	private By password = By.cssSelector("#input-password");
	private By confirmPassword = By.cssSelector("#input-confirm");
	private By subYes = By.xpath("//label[normalize-space()='Yes']/input");
	private By subNo = By.xpath("//label[normalize-space()='No']/input");
	private By agree = By.cssSelector("input[name='agree']");
	private By continueBtn = By.xpath("//input[@value='Continue']");
	private By regSuccess = By.xpath("//h1[text()='"+AppConstants.REG_SUCCESS_MSG+"']");
	private By logOut = By.xpath("//a[text()='Logout' and @class]");
	private By register = By.xpath("//a[text()='Register' and @class]");

	public RegisterPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		elmntUtil = new ElementUtil(driver);
	}

	public boolean registerUser(String firstName, String lastName, String email, String telephone, String password, String confirmPassword, boolean subscription) {
		elmntUtil.waitForVisibilityOfElement(this.firstName, AppConstants.MED_WAIT).sendKeys(firstName);
		elmntUtil.doSendKeys(this.lastName, lastName);
		elmntUtil.doSendKeys(this.email, email);
		elmntUtil.doSendKeys(this.telephone, telephone);
		elmntUtil.doSendKeys(this.password, password);
		elmntUtil.doSendKeys(this.confirmPassword, confirmPassword);
		
		if(subscription) elmntUtil.doClick(subYes);
		else elmntUtil.doClick(subNo);
		
		elmntUtil.doClick(agree);
		elmntUtil.doClick(continueBtn);
	
		
		if(elmntUtil.waitForVisibilityOfElement(regSuccess, AppConstants.MED_WAIT).isDisplayed()) {
			elmntUtil.doClick(logOut);
			elmntUtil.doClick(register);
			return true;
		}
		
		return false;
	}
}
