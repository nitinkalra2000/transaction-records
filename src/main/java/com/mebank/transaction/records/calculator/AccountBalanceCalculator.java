package com.mebank.transaction.records.calculator;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.mebank.transaction.records.model.TransactionModel;
import com.mebank.transaction.records.model.TransactionResultModel;
import com.mebank.transaction.records.model.TransactionType;

public class AccountBalanceCalculator {

	 /**
     * It will calculate the balance of series of transactions
     *
     * And if a transaction has a reversing transaction, the transaction will be omitted from the calculation,
     * even transaction is outside the given time frame.
     *
     * @param accountId The id of the account to calculate.
     * @param start Start time to being calculating from.
     * @param end End time to stop calculating at.
     * @param transactions List of transactions to process.
	 * @return 
     * @return The total of all the transactions within the provided time frame in the TransactionResult object.
     */
    public TransactionResultModel calculate(String accountId, Instant start, Instant end, List<TransactionModel> transactions) {

        //filter the transactions from & to this account
        List<TransactionModel> accTransactions = transactions.stream().filter(item -> 
        						  (accountId.equals(item.getFromAccountId()) || accountId.equals(item.getToAccountId())))
        						  .collect(Collectors.toList());

        //get the reversed transaction 
        List<String> reversals = accTransactions.stream().filter(item -> (item.getTransactionType() == TransactionType.REVERSAL))
        		                 .map(item -> item.getRelatedTransaction()).collect(Collectors.toList());

        //filter reverse trxs between the start and end date
        List<TransactionModel> requiredTransactions = accTransactions.stream().filter(item -> 
                start.isBefore(item.getCreateAt())
                        && end.isAfter(item.getCreateAt())
                        && !reversals.contains(item.getTransactionId())
                        && item.getTransactionType() != TransactionType.REVERSAL
            ).collect(Collectors.toList());

        // if no transactions were found, return zero
        if (requiredTransactions.isEmpty()) {
            return new TransactionResultModel(BigDecimal.ZERO, 0);
        }

        //calculate the sum of remaining trxs
        Optional<BigDecimal> totalOpt = requiredTransactions.stream().map(item -> item.getRelativeAmount(accountId))
        		           .reduce((acc,curr) -> acc.add(curr));

        BigDecimal total = totalOpt.get();
        return new TransactionResultModel(total, requiredTransactions.size());
    }
}
