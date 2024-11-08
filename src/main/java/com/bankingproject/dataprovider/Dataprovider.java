package com.bankingproject.dataprovider;

import org.testng.annotations.DataProvider;

import com.bankingproject.utility.Excel;

public class Dataprovider {
	public static int row;
	public static int col;

	@DataProvider(name = "exceldata")
	public Object[][] fetchCustomerDetails() throws Exception {

		Excel Excel = new Excel("\\src\\test\\resources\\TestData\\Customers.xlsx");
		row = Excel.getRowCount("ValidData");
		col = Excel.getCellCount("ValidData", row);
		Object[][] customerDetails = Excel.getCellData("ValidData", row, col);
		return (customerDetails);

	}

	@DataProvider(name = "fetchFirstName")
	public String[] FetchFirstNames() throws Exception {
		Excel Excel = new Excel("\\src\\test\\resources\\TestData\\Customers.xlsx");
		row = Excel.getRowCount("ValidData");
		col = Excel.getCellCount("ValidData", row);
		String[] firstName = Excel.fetchFirstCol("ValidData", row, col);
		return (firstName);

	}

	@DataProvider(name = "fetchCustomerName")
	public String[] FetchCustomerNames() throws Exception {
		Excel Excel = new Excel("\\src\\test\\resources\\TestData\\Customers.xlsx");
		String[] customerName = Excel.concatenatedCols("ValidData", row, col);
		return (customerName);

	}

}
