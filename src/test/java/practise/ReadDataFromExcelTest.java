package practise;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcelTest {

	public static void main(String[] args) throws IOException {
		
		//Create a java representation object of Physical File
		FileInputStream fis = new FileInputStream("C:\\Users\\Nihal\\Desktop\\Ammie\\Temp\\Advance Selenium\\TestScriptDataNinzaCRM.xls");
		
		//Open excel file in Read Mode
		//use WorkbookFactory class from Apache POI and call create method
		Workbook wb = WorkbookFactory.create(fis);
		
		//get control of the Sheet
		//getSheet() will return Sheet <Interface>
		Sheet sh = wb.getSheet("Campaign");
		
		//get control of the Row
		//getRow() will return Row<Interface>
		Row r = sh.getRow(1);
		
		//get control of the cell
		//getCell() will return Cell <Interface>
		Cell c = r.getCell(2);
		
		//read the cell data and store it in a String
		//getStringValue() will return String
		String CampaignName = c.getStringCellValue();
		
		System.out.println(CampaignName);
		
		
		//Using Method Chaining
		String TargetSize = wb.getSheet("Campaign").getRow(1).getCell(3).getStringCellValue();
		System.out.println(TargetSize);
		
		wb.close();
	}
	
	
}
