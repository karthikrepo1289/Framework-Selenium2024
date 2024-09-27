package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	ElementUtil elmntUtil;
	
	private By prodMetaInfo = By.xpath("//div[@id='content']//ul[@class='list-unstyled'][1]//li");
	private By prodPrice = By.xpath("(//div[@id='product']/preceding-sibling::ul)[2]/li");
	
	public ProductInfoPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		elmntUtil = new ElementUtil(this.driver);
	}

	public Map<String, String> getProductInfo() {
		Map<String, String> productInfo = new HashMap<String, String>();
		List<String> elementsTextList = elmntUtil.getElementsTextList(prodMetaInfo);
		for(String product : elementsTextList)
		{
			System.out.println(product);
			String[] arr = product.split(":");
			productInfo.put(arr[0].trim(), arr[1].trim());
		}
		elementsTextList.clear();
		elementsTextList = elmntUtil.getElementsTextList(prodPrice);
		System.out.println(elementsTextList);
		productInfo.put("Price", elementsTextList.get(0));
		String[] tax = elementsTextList.get(1).split(":");
		System.out.println(elementsTextList.get(1));
		productInfo.put(tax[0].trim(), tax[1].trim());
		
		return productInfo;
	}
}
