package com.khayayphyu.utils.common;

import java.io.File;

public class CommonConstant {
	public static final String REPORT_PATH = File.separator + "WEB-INF" + File.separator + "jasper_reports"
			+ File.separator;

	// folder name
	public static final String FUEL_ADVANCE_HISTORY_REPORT = "fuel-advance-history";

	// jasper name
	public static final String FUEL_ADVANCE_HISTORY_REPORT_EXCEL = File.separator + "FuelAdvanceHistory.jasper"
			+ File.separator;

	// file name
	public static final String FUEL_ADVANCE_HISTORY_FILE = "fuel_advance_history";

	public static final String DRIVER_SALARY_REPORT_DIR = "driver-salary";
	public static final String DRIVER_SALARY_REPORT_FILE = File.separator + "driver-salary.jasper";

	public static final String STD_DATE_FORMAT = "dd/MM/yyyy";
	public static final String STD_DATE_TIME_FORMAT = "dd/MM/yyyy hh:mm a";
	public static final String STD_DATE_DAY_FORMAT = "dd/MM/yyyy(E)";
	public static final String STD_MONTH_YEAR_FORMAT = "MMM-yyyy";

	public static final int ADMIN_PAGE_SIZE = 10;

	public static String getDeleryOrderReportPath() {
		return String.format("%s/delivery-order/delivery-order.jasper", REPORT_PATH);
	}

}
