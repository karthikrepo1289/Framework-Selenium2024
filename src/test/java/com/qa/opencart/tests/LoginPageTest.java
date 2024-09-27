package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
	public void testTitle() {
		Assert.assertEquals(loginPage.getTitle(), AppConstants.LOGINPAGE_TITLE);
	}

	@Test(priority = 2)
	public void testUrl() {
		Assert.assertEquals(loginPage.getPageURL(), "");
	}
	
	@Test(priority = 3)
	public void testLoginHeader() {
		Assert.assertEquals(loginPage.getLoginTitle(), AppConstants.LOGIN_HEADER);
	}
	
	@Test(priority = 4)
	public void testLogin() {
		String actTitle = loginPage.login(prop.getProperty("username"), prop.getProperty("password")).accntPagetitle();
		Assert.assertEquals(actTitle, AppConstants.accntPagetitle);
	}
}
