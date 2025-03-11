package com.ninza.hrm.api.genericutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility 
{
	public int getRandomNumber()
	{
		Random ranDom=new Random();
		int ranDomNumber=ranDom.nextInt(5000);
		return ranDomNumber;
	}
	
	public String getSystemDateYYYYMMDD()
	{
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(d);
		return date;
	}
	
	public String getRequiredDateYYYYMMDD(int days)
	{
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		sdf.format(d);
		Calendar cal=sdf.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String enddate=sdf.format(cal.getTime());
		return enddate;
	}
}
