package practise;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import genericUtilities.ExcelFileUtility;

public class ReadMultipleDataFromExcel {

	public static void main(String[] args) throws Exception {

//		FileInputStream fis = new FileInputStream("C:\\Users\\Nihal\\Desktop\\Ammie\\Temp\\Advance Selenium\\ReadingMultipleDataFromExcel.xls");
//
//		Workbook wb = WorkbookFactory.create(fis);
//		
//		Sheet sh = wb.getSheet("Mobiles");
//		
//		int RowCount = sh.getLastRowNum();
		
		ExcelFileUtility eLib = new ExcelFileUtility();
		
		int RowCount = eLib.getRowCount("Mobiles");
		
		System.out.println("Total Rows Available: " +RowCount);
		
		for(int row=1; row<=RowCount; row++)
		{
			String data = eLib.readDataFromExcelFile("Mobiles", row, 0);
			System.out.println(data);
		}
		
	}

}
