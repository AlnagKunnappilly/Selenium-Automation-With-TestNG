package com.bankingproject.utility;

import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Excel {
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;

	public Excel(String path) {
		this.path = path;
	}

	public int getRowCount(String sheetName) throws IOException {
		fi = new FileInputStream(System.getProperty("user.dir") + path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowCount;
	}

	public int getCellCount(String sheetName, int rowNum) throws IOException {
		fi = new FileInputStream(System.getProperty("user.dir") + path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellCount;
	}

	public Object[][] getCellData(String sheetName, int rowNum, int colNum) throws IOException {
		fi = new FileInputStream(System.getProperty("user.dir") + path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);
		DataFormatter formatter = new DataFormatter();
		row = sheet.getRow(0);
		String data[][] = new String[rowNum - 1][colNum];
		for (int i = 0; i < rowNum - 1; i++) {

			row = sheet.getRow(i + 1);
			for (int j = 0; j < colNum; j++) {
				data[i][j] = formatter.formatCellValue(row.getCell(j));

			}
		}
		workbook.close();
		fi.close();
		return data;
	}

	public String[] concatenatedCols(String sheetName, int rowNum, int colNum) throws IOException {
		fi = new FileInputStream(System.getProperty("user.dir") + path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);
		DataFormatter formatter = new DataFormatter();
		row = sheet.getRow(0);
		String col1;
		String col2;
		String combined[] = new String[rowNum - 1];
		for (int i = 0; i < rowNum - 1; i++) {
			row = sheet.getRow(i + 1);
			for (int j = 0; j < colNum - 2; j++) {
				col1 = formatter.formatCellValue(row.getCell(j));
				col2 = formatter.formatCellValue(row.getCell(j + 1));
				combined[i] = col1 + " " + col2;
			}
		}
		workbook.close();
		fi.close();
		return combined;
	}

	public String[] fetchFirstCol(String sheetName, int rowNum, int colNum) throws IOException {
		fi = new FileInputStream(System.getProperty("user.dir") + path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);
		DataFormatter formatter = new DataFormatter();
		row = sheet.getRow(0);
		String colFirst[] = new String[rowNum - 1];
		for (int i = 0; i < rowNum - 1; i++) {
			row = sheet.getRow(i + 1);
			for (int j = 0; j < colNum - 2; j++) {
				colFirst[i] = formatter.formatCellValue(row.getCell(j));
			}
		}
		workbook.close();
		fi.close();
		return colFirst;
	}

	public void setCellData(String sheetName, Map<Integer, Object[]> hp) throws IOException {
		File xlFile = new File(System.getProperty("user.dir") + path);
		if (!xlFile.exists()) {
			workbook = new XSSFWorkbook();
			fo = new FileOutputStream(System.getProperty("user.dir") + path);
			workbook.write(fo);
		}
		fi = new FileInputStream(System.getProperty("user.dir") + path);
		workbook = new XSSFWorkbook(fi);

		if (workbook.getSheetIndex(sheetName) == -1) {
			workbook.createSheet(sheetName);
		}
		sheet = workbook.getSheet(sheetName);

		Set<Integer> keyid = hp.keySet(); 
		  
        int rowid = 0;   
        for (Integer key : keyid) { 
  
            row = sheet.createRow(rowid++); 
            Object[] objectArr = hp.get(key); 
            int cellid = 0; 
  
            for (Object obj : objectArr) { 
                Cell cell = row.createCell(cellid++); 
                cell.setCellValue((String)obj); 
            } 
        } 
		
	fo=new FileOutputStream(System.getProperty("user.dir")+path);
	workbook.write(fo);
	workbook.close();
	fi.close();
	fo.close();
}}
