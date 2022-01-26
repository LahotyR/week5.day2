package week5.day2.assignments;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData {
	
	public static String[][] excelData(String filename) throws IOException {
		
	XSSFWorkbook workbook = new XSSFWorkbook("./data/" + filename + ".xlsx");
	
	XSSFSheet worksheet = workbook.getSheetAt(0);
	
	int lastRowNum = worksheet.getLastRowNum();
	
	short lastCellNum = worksheet.getRow(0).getLastCellNum();
	
	String data[][] = new String[lastRowNum][lastCellNum];
	
	for (int i = 1; i <= lastRowNum; i++) {
		
			XSSFRow row = worksheet.getRow(i);
			
			for (int j = 0; j < lastCellNum; j++) {
				
				XSSFCell cell = row.getCell(j);
				String stringCellValue = cell.getStringCellValue();
				//System.out.println(stringCellValue);
				
				data[i-1][j] = stringCellValue;
			}
		
	}
	
	workbook.close();
	return data;
	
	}

}
