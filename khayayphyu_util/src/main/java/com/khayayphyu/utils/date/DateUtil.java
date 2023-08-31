package com.khayayphyu.utils.date;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.khayayphyu.utils.CommonUtils;

public class DateUtil {
	public static final String uiDateFormatStr = "dd/MM/yyyy";

	public static final String uiTimeFormatStr = "hh:mm aa";
	
	public static final String apiTimeFormatStr = "HH:mm";
	
	public static final String sqlDateFormatStr = "yyyy-MM-dd";

	public static final SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public static final SimpleDateFormat uiTimeFormat = new SimpleDateFormat(uiTimeFormatStr);

	public static final SimpleDateFormat uiDateFormat = new SimpleDateFormat(uiDateFormatStr);

	public static final SimpleDateFormat monthYearFormat = new SimpleDateFormat("MMM-yyyy");

	public static final SimpleDateFormat fullMonthYearFormat = new SimpleDateFormat("MMMM-yyyy");

	public static final SimpleDateFormat fullDateFormat = new SimpleDateFormat(uiDateFormatStr + " HH:mm:ss");

	public static boolean isSameDate(Date d1, Date d2) {
		if (d1 == null || d2 == null)
			return false;
		String d1Str = DateUtil.formatUiDate(d1);
		String d2Str = DateUtil.formatUiDate(d2);
		return d1Str.equals(d2Str);
	}

	public static String formatDate(Date date) {
		if (date == null)
			return "-";
		return sqlDateFormat.format(date);
	}

	public static String formatUiDate(Date date) {
		if (date == null)
			return "-";
		return uiDateFormat.format(date);
	}

	public static Date parseDate(String str) {
		return parseDate(str, uiDateFormat);
	}

	public static Date parseUiDate(String str) {
		if (str == null)
			return null;
		return parseDate(str, uiDateFormat);
	}

	private static Date parseDate(String str, SimpleDateFormat sampleDateFormate) {
		if (StringUtils.isEmpty(str))
			return null;

		try {
			return sampleDateFormate.parse(str);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String formatFullMonthString(Date date) {
		return date == null ? "" : fullMonthYearFormat.format(date);
	}

	// this method is sql query serch
	public static Date toStartOfMonth(Date date) {
		if (date == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		return calendar.getTime();
	}

	public static Date toFirstDateOfMonth(Date date) {
		if (date == null)
			return null;
		/*
		 * Calendar calendar = Calendar.getInstance(); calendar.setTime(date);
		 * calendar.set(Calendar.DAY_OF_MONTH, 1); return calendar.getTime();
		 */
		LocalDate localDate = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		localDate = LocalDate.of(localDate.getYear(), localDate.getMonth(), 1);
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date toEndOfDay(Date date) {
		if (date == null)
			return null;
		String dateStr = DateUtil.formatUiDate(date) + " 23:59:59";
		try {
			return fullDateFormat.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date toEndOfMonth(Date date) {
		if (date == null)
			return null;
		LocalDate localDate = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		int month = localDate.getMonthValue();
		int year = localDate.getYear();
		if (month >= 12) {
			year++;
			month = 1;
		} else {
			month++;
		}
		localDate = LocalDate.of(year, month, 1);
		localDate = localDate.minusDays(1);
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

	}

	public static boolean isBetween(Date date, Date startDate, Date endDate) {
		if (date == null || startDate == null || endDate == null)
			return false;

		return startDate.before(date) && endDate.after(date);
	}

	public static Date addDay(Date date, int count) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, count);
		return calendar.getTime();
	}

	public static long dayCount(Date startDate, Date endDate) {
		LocalDate localStartDate = toLocalDate(startDate);
		LocalDate localEndDate = toLocalDate(endDate);
		return ChronoUnit.DAYS.between(localStartDate, localEndDate);
	}

	public static LocalDate toLocalDate(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static LocalDateTime toLocalDateTime(Date date) {
		if (date == null)
			return LocalDateTime.now();
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}

	public static Date mergeDateAndTime(Date date, LocalTime time) {
		time = time == null ? LocalTime.now() : time;
		LocalDateTime l = date != null ? LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault())
				: LocalDateTime.now();
		l = LocalDateTime.of(l.getYear(), l.getMonthValue(), l.getDayOfMonth(), time.getHour(), time.getMinute(),
				time.getSecond());
		return Timestamp.valueOf(l);
	}

	public static Date mergeDateAndTime(Date date, String timeStr) {
		if (StringUtils.isBlank(timeStr))
			return mergeDateAndTime(date, LocalTime.now());
		LocalTime time = LocalTime.parse(timeStr, DateTimeFormatter.ofPattern("h:mm a"));
		return mergeDateAndTime(date, time);
	}
	
	public static boolean isSameMonth(Date date1, Date date2) {
		String str1 = formatFullMonthString(date1);
		String str2 = formatFullMonthString(date2);
		return str1.equals(str2);
	}
	
	public static String dateToString(String format, Date date) {
		if (date == null) {
			return "";
		}
		if (format == null || format.trim().isEmpty()) {
			format = uiDateFormatStr;
		}
		return new SimpleDateFormat(format).format(date);
	}
	
	public static Date stringToDate(String format, String dateString) {
		if (dateString == null || dateString.trim().isEmpty()) {
			return null;
		}
		if (format == null || format.trim().isEmpty()) {
			format = sqlDateFormatStr;
		}
		try {
			return new SimpleDateFormat(format).parse(dateString);
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}


	
	public static String changeDateStringFormat(String fromFormat, String toFormat, String dateString) {
		if (!CommonUtils.validString(dateString)) {
			return null;
		}
		if (!CommonUtils.validString(fromFormat) && !CommonUtils.validString(toFormat)) {
			return dateString;
		}
		if (!CommonUtils.validString(fromFormat)) {
			fromFormat = sqlDateFormatStr;
		}
		if (!CommonUtils.validString(toFormat)) {
			toFormat = uiDateFormatStr;
		}
		return dateToString(toFormat, stringToDate(fromFormat, dateString));
	}

}
