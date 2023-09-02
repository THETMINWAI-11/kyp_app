package com.khayayphyu.service.search;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.khayayphyu.entity.sale.Sale;
import com.khayayphyu.utils.date.DateUtil;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SaleSearchRequest implements SearchRequest<Sale> {
	
	@DateTimeFormat(pattern = DateUtil.uiDateFormatStr)
	private Date startDate;
	
	@DateTimeFormat(pattern = DateUtil.uiDateFormatStr)
	private Date endDate;
	private StringBuffer buffer;
	
	@Override
	public String generateQuery() {
		buffer.append(generateBaseQuery());
		addDate();
		return null;
	}

	@Override
	public String generateBaseQuery() {
		return "select s from Sale s where 1 = 1";
	}
	
	private void addDate() {
		if (startDate == null && endDate == null)
			return;
		buffer.append(" and ");
		if (endDate == null) {
			buffer.append(generateAfterDateQuery(startDate, "s", "purchaseDate"));
		} else if (startDate == null) {
			buffer.append(generateBeforeDateQuery(endDate, "s", "purchaseDate"));
		} else {
			buffer.append(generateBetweenDateQuery(startDate, endDate, "s", "purchaseDate"));
		}
	}

}
