package com.khayayphyu.service.search;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.khayayphyu.entity.sale.SalePrice;
import com.khayayphyu.utils.date.DateUtil;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SalePriceSearchRequest implements SearchRequest<SalePrice> {
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
		return "select ss from Sale ss where 1 = 1";
	}
	
	private void addDate() {
		if (startDate == null && endDate == null)
			return;
		buffer.append(" and ");
		if (endDate == null) {
			buffer.append(generateAfterDateQuery(startDate, "ss", "purchaseDate"));
		} else if (startDate == null) {
			buffer.append(generateBeforeDateQuery(endDate, "ss", "purchaseDate"));
		} else {
			buffer.append(generateBetweenDateQuery(startDate, endDate, "ss", "purchaseDate"));
		}
	}
}
