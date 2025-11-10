package genericUtilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	public String getRequiredDate(int days) {

		// Generate date after 30 days
		Date date = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
		sim.format(date);
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String requiredDate = sim.format(cal.getTime());

		return requiredDate;
	}
	
	
	public int generateNineDigitNumber()
	{
		Random random = new Random();
		
		//Limit from and to
		//Random 9 digit number will be generated within that range
		int randomNum = random.nextInt(100000000, 999999999);
		return randomNum;
	}
	
	
	public String getCurrentDateAndTime()
	{
		Date d = new Date();
		
		String date = d.toString().replace(" " , "_").replace(":" , "_");
		return date;
	}


}
