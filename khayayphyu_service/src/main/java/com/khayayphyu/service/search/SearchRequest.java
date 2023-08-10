package com.khayayphyu.service.search;

import java.util.Date;

import com.khayayphyu.dto.AbstractDto;
import com.khayayphyu.utils.date.DateUtil;

public interface SearchRequest<T, D extends AbstractDto<?>> {
	public String generateQuery();

	public String generateBaseQuery();

	default String generateBeforeDateQuery(Date date, String prefix, String name) {
		return String.format("Date(%s.%s) <= Date('%s') ", prefix, name, DateUtil.formatDate(date));
	}

	default String generateAfterDateQuery(Date date, String prefix, String name) {
		return String.format("Date(%s.%s) >= Date('%s') ", prefix, name, DateUtil.formatDate(date));
	}

	default String generateBetweenDateQuery(Date startDate, Date endDate, String prefix, String name) {
		return String.format("Date(%s.%s) between Date('%s') and Date('%s')", prefix, name,
				DateUtil.formatDate(startDate), DateUtil.formatDate(endDate));
	}

	default StringBuffer generateDateQuery(Date startDate, Date endDate, String prefix, String name) {
		StringBuffer buffer = new StringBuffer();
		if (startDate == null && endDate == null)
			return buffer;
		buffer.append(" and ");
		if (endDate == null) {
			buffer.append(generateAfterDateQuery(startDate, prefix, name));
		} else if (startDate == null) {
			buffer.append(generateBeforeDateQuery(endDate, prefix, name));
		} else {
			buffer.append(generateBetweenDateQuery(startDate, endDate, prefix, name));
		}
		return buffer;
	}

	default StringBuffer generateDateQueryWithoutAnd(Date startDate, Date endDate, String prefix, String name) {
		StringBuffer buffer = new StringBuffer();
		if (startDate == null && endDate == null)
			return buffer;

		if (endDate == null) {
			buffer.append(generateAfterDateQuery(startDate, prefix, name));
		} else if (startDate == null) {
			buffer.append(generateBeforeDateQuery(endDate, prefix, name));
		} else {
			buffer.append(generateBetweenDateQuery(startDate, endDate, prefix, name));
		}
		return buffer;
	}

	default boolean filter(T t) {
		return true;
	}

	default boolean isValid() {
		return true;
	}

	default boolean isRequireReport() {
		return false;
	}
}
