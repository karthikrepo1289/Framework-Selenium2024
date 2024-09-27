package com.qa.opencart.tests;

import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Sales")
@Story("Accounts Page")
public class AccountPageTest extends BaseTest {

	@BeforeClass
	public void accntSetup() {
		accntPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Description("Account Menus Testing")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void accountMenuTest() {
		Assert.assertTrue(accntPage.myAccountOption());
	}

	@Description("Accounts Header Testing")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void accountHeadersTest() {
		List<String> actualMenu = accntPage.getAccountHeadersInfo();
		List<String> expMenu = AppConstants.ACCOUNT_HEADERS;
		Collections.sort(expMenu);
		Collections.sort(actualMenu);
		Assert.assertEquals(actualMenu, expMenu);
	}
}
