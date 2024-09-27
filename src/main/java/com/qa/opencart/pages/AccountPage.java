package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class AccountPage {
	private WebDriver driver;
	private ElementUtil elmntUtil;

	private By accntHeaders = By.xpath("//div[@id='content']/h2");
	private By myAccntOption = By.xpath("//a[@title='" + AppConstants.ACCNT_MENU + "']");
	private By options = By.xpath("//*[@id='column-right']//a");
	private By srchInputBox = By.name("search");
	private By srchBtn = By.cssSelector("span.input-group-btn button");

	public AccountPage(WebDriver driver) {
		this.driver = driver;
		elmntUtil = new ElementUtil(this.driver);
	}

	@Step("Account Headers Information")
	public List<String> getAccountHeadersInfo() {
		return elmntUtil.getElementsTextList(accntHeaders);
	}

	public List<String> getMyAccountOptions() {
		return elmntUtil.getElementsTextList(options);
	}

	@Step("Checks My Account Option visibility")
	public boolean myAccountOption() {
		return elmntUtil.getElement(myAccntOption).isDisplayed();
	}

	public String accntPagetitle() {
		return driver.getTitle();
	}

	public SearchResultPage searchProduct(String productName) {
		elmntUtil.getElement(srchInputBox).clear();
		elmntUtil.doSendKeys(srchInputBox, productName);
		elmntUtil.doClick(srchBtn);

		return new SearchResultPage(driver);
	}
}
