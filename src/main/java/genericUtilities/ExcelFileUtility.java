package genericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {
	
	public String readDataFromExcelFile(String SheetName, int RowNum, int CellNum) throws IOException
	{
		FileInputStream fis = new FileInputStream("./src/main/resources/TestScriptData_NINZA_CRM.xls");

		Workbook wb = WorkbookFactory.create(fis);

		String data = wb.getSheet(SheetName).getRow(RowNum).getCell(CellNum).getStringCellValue();
		
		wb.close();
		
		return data;
		
	}
	
	
	public int getRowCount(String SheetName) throws Exception
	{
		FileInputStream fis = new FileInputStream("./src/main/resources/TestScriptData_NINZA_CRM.xls");

		Workbook wb = WorkbookFactory.create(fis);
		
		int RowCount = wb.getSheet(SheetName).getLastRowNum();
		
		wb.close();
		
		return RowCount;
		
		
	}

}
