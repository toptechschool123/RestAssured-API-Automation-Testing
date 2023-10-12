package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import core.BaseClass;

public class ExcelUtility extends BaseClass{

	public static String pathToExcelFile ="C:\\Users\\T540p\\Desktop\\dataprovider.xlsx";
										  
	public static Workbook book;
	public static Sheet sheet;

	public static Object[][] getExcelData(String sheetName){
		
		try {
			FileInputStream file = new FileInputStream(pathToExcelFile);
			
			
			book = WorkbookFactory.create(file);
			sheet = book.getSheet(sheetName);
			
			Object data [][] = new Object [sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			for(int i=0; i<sheet.getLastRowNum(); i++) {
			
				for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
				data[i][j]=sheet.getRow(i +1).getCell(j).toString();
			}
			
		}
			return data;
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		
	    }catch(IOException e) {
	    e.printStackTrace();
	    }
	    return null;
}}
