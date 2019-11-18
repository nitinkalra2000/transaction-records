package com.mebank.transaction.records.model;

import java.math.BigDecimal;

/**
 * Object containing the final result to be printed
 * @author Lenovo
 *
 */
public class TransactionResultModel {

	private BigDecimal total;
	private Integer    txnCnt;
	private String     finalTotalStr;
	
	public TransactionResultModel(BigDecimal total, Integer txnCnt) {
		this.total  = total;
		this.txnCnt = txnCnt;
	}

	public String getFinalTotalStr() {
		return finalTotalStr;
	}

	public void setFinalTotalStr(String finalTotalStr) {
		this.finalTotalStr = finalTotalStr;
	}

	public Integer getTxnCnt() {
		return txnCnt;
	}

	public void setTxnCnt(Integer txnCnt) {
		this.txnCnt = txnCnt;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
}
