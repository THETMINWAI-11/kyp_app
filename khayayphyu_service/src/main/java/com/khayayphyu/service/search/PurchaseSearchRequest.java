package com.khayayphyu.service.search;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.khayayphyu.entity.purchase.Purchase;
import com.khayayphyu.utils.date.DateUtil;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PurchaseSearchRequest implements SearchRequest<Purchase> {
	
	private String productName;
	private String customerName;
	
	@DateTimeFormat(pattern = DateUtil.uiDateFormatStr)
	private Date startDate;
	
	@DateTimeFormat(pattern = DateUtil.uiDateFormatStr)
	private Date endDate;
	private StringBuffer buffer;

	@Override
	public String generateQuery() {
		buffer.append(generateBaseQuery());
		addDate();
		addProduct();
		addCustomer();
		return buffer.toString();
	}

	@Override
	public String generateBaseQuery() {
		
		return "select pc from Purchase pc where 1 = 1";
	}
	
	private void addDate() {
		if (startDate == null && endDate == null)
			return;
		buffer.append(" and ");
		if (endDate == null) {
			buffer.append(generateAfterDateQuery(startDate, "pc", "purchaseDate"));
		} else if (startDate == null) {
			buffer.append(generateBeforeDateQuery(endDate, "pc", "purchaseDate"));
		} else {
			buffer.append(generateBetweenDateQuery(startDate, endDate, "pc", "purchaseDate"));
		}
	}
	
	private void addProduct() {
			if (StringUtils.isEmpty(productName))
				return;
			buffer.append(" and upper(ao.product.name) like upper('%" + productName + "%')");
	}
	
	private void addCustomer() {
		if (StringUtils.isEmpty(customerName))
			return;
		buffer.append(" and upper(pc.customer.name) like upper('%" + customerName + "%')");
	}

}
