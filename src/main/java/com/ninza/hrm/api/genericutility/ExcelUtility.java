package com.ninza.hrm.api.genericutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility
{
	public String getDataFromExcel(String sheetname, int rowNum, int celNum) throws Throwable
	{
		FileInputStream fis=new FileInputStream("./testdata/testScriptdata.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		String data=wb.getSheet(sheetname).getRow(rowNum).getCell(celNum).getStringCellValue();
		wb.close();
		return data;
	}
	
	public int getRowCount(String sheetName) throws Throwable
	{
		FileInputStream fis=new FileInputStream("./testdata/testScriptdata.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		int lastRowCount=wb.getSheet(sheetName).getLastRowNum();
		wb.close();
		return lastRowCount;
	}

	public void setDataIntoExcel(String sheetName, int rowNum, int celNum, String data) throws Throwable
	{
		FileInputStream fis=new FileInputStream("./testdata/testScriptdata.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rowNum).createCell(celNum).setCellValue(data);
		
		FileOutputStream fos=new FileOutputStream("./testdata/testScriptdata.xlsx");
		wb.write(fos);
		wb.close();
	}
}
