package gr.aueb.cf.schoolpro.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	private static ThreadLocal<DateFormat> dateFormat = new ThreadLocal<DateFormat>() {
		@Override
		public DateFormat get() {
			return super.get();
		}

		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("dd-MM-yyyy");
		}
	};

	/**
	 * No instances of this Utility class should be available.
	 */
	private DateUtil() {

	}

	/**
	 * Transforms a String of {@link DateUtil#dateFormat} to a Date object
	 * 
	 * @param dateStr
	 *            the formatted date String
	 * @return the Date object that corresponds to the dateStr parameter
	 * @throws ParseException
	 */
	public static Date toDate(String dateStr) throws ParseException {
		return getDateFormat().parse(dateStr);
	}
	
	/**
	 * Transforms a Date object to a SQL date object.
	 * 
	 * @param date
	 * 			the date object
	 * @return	the SQL date object that corresponds to the date parameterMain.java
	 *
	 */
	public static java.sql.Date toSQLDate(Date date) {
		return new java.sql.Date(date.getTime());
	}

	/**
	 * Transforms a Date object to formatted String based on
	 * {@link DateUtil#dateFormat}
	 * 
	 * @param date
	 *            the Date object
	 * @return the formatted String
	 */
	public static String toString(Date date) {
		return getDateFormat().format(date);
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String toSQLDateString(java.sql.Date date) {
		return getDateFormat().format(date);
	}	

	/**
	 * Gets the default {@link DateFormatter}
	 * 
	 * @return {@link DateUtil#dateFormat}
	 */
	public static DateFormat getDateFormat() {
		return dateFormat.get();
	}

}
