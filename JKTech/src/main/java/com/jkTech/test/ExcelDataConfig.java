package com.jkTech.test;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelDataConfig {

	File file;
	HSSFWorkbook wb;
	HSSFSheet sheet = null;
	String data;

	public ExcelDataConfig(String excelPath) {

		try {
			// Specify path of file
			file = new File(excelPath);

			// Load File
			FileInputStream fileIn = new FileInputStream(file);

			// Load WorkBook
			wb = new HSSFWorkbook(fileIn);
		}

		catch (Exception e) {

			System.out.println("File not found error");
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}

	// Method to get data from particular row and column
	public String getData(int sheetNo, int row, int column) {

		// Load sheet
		sheet = wb.getSheetAt(sheetNo);

		// Extract data
		try {
		 data = sheet.getRow(row).getCell(column).getStringCellValue();

		}
		catch(NullPointerException NPE) {
			
		}
		catch(IllegalStateException ISE) {
			data = Integer.toString((int) sheet.getRow(row).getCell(column).getNumericCellValue());
			
		}
		return data;
	}

	// Method to get last row
	public int lastRow(int sheetNo) {

		sheet = wb.getSheetAt(sheetNo);
		return sheet.getPhysicalNumberOfRows();

	}

}
