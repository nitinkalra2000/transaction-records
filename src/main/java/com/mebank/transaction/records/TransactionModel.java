package com.mebank.transaction.records;

import java.math.BigDecimal;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class will be mapped to CSV data points as well
 * @author Lenovo
 *
 */
public class TransactionModel {

	@JsonProperty
	String 			transactionId;
	@JsonProperty
	String 			fromAccountId;
	@JsonProperty
	String 			toAccountId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:m:ss", timezone = "UTC")
    Instant 		createAt;
	@JsonProperty
	BigDecimal 		amount;
	@JsonProperty
	TransactionType transactionType;
	@JsonProperty
	String 			relatedTransaction;

	BigDecimal getRelativeAmount(String accountId) {
		if (accountId.equals(fromAccountId)) {
		    return amount.negate();
		}
		if(accountId.equals(toAccountId)) {
		    return amount;
		} 
		return BigDecimal.ZERO;
	}
}
enum TransactionType { PAYMENT, REVERSAL }

