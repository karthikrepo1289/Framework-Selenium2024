package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {
	private WebDriver driver;
	private ElementUtil elmntUtil;
	
	private By srchResults = By.xpath("//div[contains(@class,'product-layout')]//div[@class='image']/a");
	
	public SearchResultPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		elmntUtil = new ElementUtil(this.driver);
	}

	public List<String> getSearchResultsOfProduct() {
		return elmntUtil.getElementsTextList(srchResults);
	}
	
	public ProductInfoPage selectProduct(String productName) {
		By productAddress = By.xpath("//div[@class='caption']//a[text()='"+productName+"']");
		elmntUtil.doClick(productAddress);
		
		return new ProductInfoPage(driver);
	}
}
