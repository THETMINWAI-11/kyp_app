package com.khayayphyu.service.search;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.khayayphyu.dto.purchase.PurchasePriceDto;
import com.khayayphyu.entity.purchase.PurchasePrice;
import com.khayayphyu.utils.date.DateUtil;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PurchasePriceSearchRequest implements SearchRequest<PurchasePrice, PurchasePriceDto> {
	
	@DateTimeFormat(pattern = DateUtil.uiDateFormatStr)
	private Date startDate;
	
	@DateTimeFormat(pattern = DateUtil.uiDateFormatStr)
	private Date endDate;
	private StringBuffer buffer;
	
	@Override
	public String generateQuery() {
		buffer.append(generateBaseQuery());
		addDate();
		return buffer.toString();
	}

	@Override
	public String generateBaseQuery() {
		return "select ps from PurchasePrice ps where 1 = 1";
	}
	
	private void addDate() {
		if (startDate == null && endDate == null)
			return;
		buffer.append(" and ");
		if (endDate == null) {
			buffer.append(generateAfterDateQuery(startDate, "ps", "purchaseDate"));
		} else if (startDate == null) {
			buffer.append(generateBeforeDateQuery(endDate, "ps", "purchaseDate"));
		} else {
			buffer.append(generateBetweenDateQuery(startDate, endDate, "ps", "purchaseDate"));
		}
	}

}
