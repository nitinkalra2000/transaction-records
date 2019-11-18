package com.mebank.transaction.records;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.mebank.transaction.records.calculator.AccountBalanceCalculator;
import com.mebank.transaction.records.exception.TransactionRecordsException;
import com.mebank.transaction.records.model.TransactionModel;
import com.mebank.transaction.records.model.TransactionResultModel;
import com.mebank.transaction.records.util.CsvFileReader;

/**
 * Entry point for the Problem solution
 *
 */
public class TransactionRecordsApp {
	
	/**
	 * This method will accept the arguments in the following form
	 * Running from Eclipse, set these in Program arguments
	 * ACC334455 transaction-records.csv 20/10/2018T12:00:00 20/10/2018T19:00:00
	 * @throws Exception 
	 */
	public static void main(String args[]) throws TransactionRecordsException {
		TransactionRecordsApp.start(args);
	}
	
	public static TransactionResultModel start(String args[]) throws TransactionRecordsException {
		if(args.length != 4) {
			System.out.println(" ** Enter all the arguments **");
			System.out.println("accId fileName fromDate endDate");
			return null;
		}
		TransactionResultModel result = TransactionRecordsApp.executeCalculation(args[0], args[1], args[2], args[3]);
	    String 			  totalString = null;
	    
		if(result.getTotal().compareTo(BigDecimal.ZERO) == 1 || result.getTotal()
				                                               .compareTo(BigDecimal.ZERO) == 0) {
			totalString = "$" + result.getTotal();
		} else {
			totalString = "-" + "$" + result.getTotal().abs();
		}
	
		//sysouts for Demo only
	    System.out.println("Relative balance for the period is: " + totalString);
	    System.out.println("Number of transactions included is: " + result.getTxnCnt());
	    
	    result.setFinalTotalStr(totalString);
	    return result;
	}
	
	private static TransactionResultModel executeCalculation(String accountId, String filePath, 
			                                                 String startDate, String endDate) 
			                                                 throws TransactionRecordsException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm:ss");

        Instant start = LocalDateTime.from(formatter.parse(startDate)).toInstant(ZoneOffset.UTC);

        Instant end = LocalDateTime.from(formatter.parse(endDate)).toInstant(ZoneOffset.UTC);

        List<TransactionModel> transactions = new CsvFileReader().read(filePath);

        if (transactions == null || transactions.size() == 0) {
            return new TransactionResultModel(BigDecimal.ZERO, 0);
        }

        return new AccountBalanceCalculator().calculate(accountId, start, end, transactions);
    }
}