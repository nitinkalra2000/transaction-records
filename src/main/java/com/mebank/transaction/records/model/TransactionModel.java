package com.mebank.transaction.records.model;

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
	private String 			transactionId;
	@JsonProperty
	private String 			fromAccountId;
	@JsonProperty
	private String 			toAccountId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:m:ss", timezone = "UTC")
	private Instant 		createAt;
	@JsonProperty
	private BigDecimal 		amount;
	@JsonProperty
	private TransactionType transactionType;
	@JsonProperty
	private String 			relatedTransaction;
	
	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getFromAccountId() {
		return fromAccountId;
	}

	public void setFromAccountId(String fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	public String getToAccountId() {
		return toAccountId;
	}

	public void setToAccountId(String toAccountId) {
		this.toAccountId = toAccountId;
	}

	public Instant getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Instant createAt) {
		this.createAt = createAt;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public String getRelatedTransaction() {
		return relatedTransaction;
	}

	public void setRelatedTransaction(String relatedTransaction) {
		this.relatedTransaction = relatedTransaction;
	}

	public BigDecimal getRelativeAmount(String accountId) {
		if (accountId.equals(fromAccountId)) {
		    return amount.negate();
		}
		if(accountId.equals(toAccountId)) {
		    return amount;
		} 
		return BigDecimal.ZERO;
	}
}


