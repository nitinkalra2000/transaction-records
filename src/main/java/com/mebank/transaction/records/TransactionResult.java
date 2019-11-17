package com.mebank.transaction.records;

import java.math.BigDecimal;

/**
 * Object containing the final result to be printed
 * @author Lenovo
 *
 */
public class TransactionResult {

	BigDecimal total;
	Integer    txnCnt;
	String     finalTotalStr;
	
	TransactionResult(BigDecimal total, Integer txnCnt) {
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
	
}
