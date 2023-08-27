package com.khayayphyu.service.search;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.khayayphyu.dto.sale.SaleOrderDto;
import com.khayayphyu.entity.sale.SaleOrder;
import com.khayayphyu.utils.date.DateUtil;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SaleOrderSearchRequest implements SearchRequest<SaleOrder, SaleOrderDto> {
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
		return "select so from Sale so where 1 = 1";
	}
	
	private void addDate() {
		if (startDate == null && endDate == null)
			return;
		buffer.append(" and ");
		if (endDate == null) {
			buffer.append(generateAfterDateQuery(startDate, "so", "purchaseDate"));
		} else if (startDate == null) {
			buffer.append(generateBeforeDateQuery(endDate, "so", "purchaseDate"));
		} else {
			buffer.append(generateBetweenDateQuery(startDate, endDate, "so", "purchaseDate"));
		}
	}
}
