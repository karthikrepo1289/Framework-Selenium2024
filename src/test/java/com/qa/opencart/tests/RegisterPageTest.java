package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void registerSetup() {
		registerPage = loginPage.navigateToRegistration();
	}

	private String getEmailAddress() {
		return "test" + System.currentTimeMillis() + "@opencart.com";
	}

	public String getPhoneNumber() {
		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		return String.valueOf(number);
	}

	@DataProvider
	public Object[][] newRegistrationData() {
		Object[][] data = new Object[][] {
				{ "QA" + System.currentTimeMillis(), "QA" + System.currentTimeMillis(), "test", "test", "yes" } };
		return data;
	}

	@Test(dataProvider = "newRegistrationData")
	public void registrationTest(String firstName, String lastName, String passWord, String confirmPassword, String isSubRequired) {
	boolean isRegSuccessful = registerPage.registerUser(firstName, lastName, getEmailAddress(), getPhoneNumber(), passWord, confirmPassword, Boolean.valueOf(isSubRequired));
	Assert.assertTrue(isRegSuccessful);
	}
}
