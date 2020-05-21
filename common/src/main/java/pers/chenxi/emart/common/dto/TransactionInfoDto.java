package pers.chenxi.emart.common.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class TransactionInfoDto {

	private String userId;

	private Integer itemId;

	private Integer number;

	private String transactionStatus;

	private Date transactionDate;

	private String remarks;

	private Integer discountId;

	private String discountCode;

	private BigDecimal percentage;

}
