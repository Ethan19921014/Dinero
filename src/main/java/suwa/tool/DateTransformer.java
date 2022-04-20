package suwa.tool;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.naming.java.javaURLContextFactory;

public class DateTransformer {
	
	String pattern = "yyyy-MM-dd HH:mm:ss";
	
	String greenPattern ="yyyy/MM/dd HH:mm:ss";
	
	String timeStampPattern = "yyyy-MM-dd hh:mm:ss.SSS";
	
	
	
	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	SimpleDateFormat sdfGreen =new SimpleDateFormat(greenPattern);
	SimpleDateFormat sdfTs = new SimpleDateFormat(timeStampPattern);
	
	Date date = new Date();
	
	public String newStringDate () {
		
		String time = sdf.format(date);
		
		//System.out.println(time);
		return time;
	}
	
	public java.sql.Date newSqlDate () {
		
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		//System.out.println(sqlDate);
		
		return sqlDate;
		
	}
	
	public String transSqlToString(java.sql.Date sqlDate) {
		String dateStr = sdf.format(sqlDate);
		
		return dateStr;
	}
	
	public String toGreenDate() {
		String time = sdfGreen.format(date);
		return time;
	}
	
	public Timestamp sqlDateStrToCalendar(String sqlDateString) throws ParseException {
		 Date parsedDate = sdfTs.parse(sqlDateString);
		  Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		  return timestamp;
	}
	
	public Date strToJavaDate(String str) throws ParseException {
		
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(str);
		
		return date;
	}
	
	public static void main(String[] args) {
		DateTransformer dtf = new DateTransformer();
		
		
		System.out.println(dtf.transSqlToString(dtf.newSqlDate()));
		
	}
}
