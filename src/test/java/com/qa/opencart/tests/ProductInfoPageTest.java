package com.qa.opencart.tests;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void productInfoSetup() {
		accntPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] getProductInfo() {
		Object[][] data = new Object[][] {
				{ "macbook", "MacBook Air", "Apple", "Out Of Stock", "$1,202.00", "$1,000.00", "Product 14" },
				{ "macbook", "MacBook", "Apple", "In Stock", "$602.00", "$500.00", "Product 16" } };
		
		return data;
	}

	@Test(dataProvider = "getProductInfo")
	public void searchProductInfoTest(String searchProduct, String resultProduct, String brand, String availablity,
			String price, String exTax, String productCode) {

		prodInfoPage = accntPage.searchProduct(searchProduct).selectProduct(resultProduct);
		Map<String, String> actProductDetails = prodInfoPage.getProductInfo();
		softAssert.assertEquals(actProductDetails.get("Brand"), brand);
		softAssert.assertEquals(actProductDetails.get("Availability"), availablity);
		softAssert.assertEquals(actProductDetails.get("Price"), price);
		softAssert.assertEquals(actProductDetails.get("Ex Tax"), exTax);
		softAssert.assertEquals(actProductDetails.get("Product Code"), productCode);
		softAssert.assertAll();
	}
}
