package practise;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataBackToExcel {

	public static void main(String[] args) throws Exception {

		FileInputStream fis = new FileInputStream("C:\\Users\\Nihal\\Desktop\\Ammie\\Temp\\Advance Selenium\\ReadingMultipleDataFromExcel.xls");

		Workbook wb = WorkbookFactory.create(fis);
		
		Sheet sh = wb.getSheet("Mobiles");
		
		Row r = sh.getRow(1);
		
		Cell c = r.createCell(1, CellType.STRING);
		
		c.setCellValue("ID0521236Iphone");
		
		FileOutputStream fos = new FileOutputStream("C:\\Users\\Nihal\\Desktop\\Ammie\\Temp\\Advance Selenium\\ReadingMultipleDataFromExcel.xls");
		
		wb.write(fos);
		
	}

}
