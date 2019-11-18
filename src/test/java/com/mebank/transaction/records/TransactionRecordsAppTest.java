package com.mebank.transaction.records;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.mebank.transaction.records.exception.TransactionRecordsException;
import com.mebank.transaction.records.model.TransactionResultModel;

/**
 * Test cases
 */
public class TransactionRecordsAppTest {
	TransactionResultModel result = null;

	@Test
    public void testSuccess1() throws Exception {
		String args [] = {"ACC334455", "transaction-records.csv", "20/10/2018T12:00:00", "20/10/2018T19:00:00"};
		result = TransactionRecordsApp.start(args);
		
		assertEquals("-$25.00", result.getFinalTotalStr());
		assertEquals(Integer.valueOf(1), result.getTxnCnt());
    }
	
	@Test
    public void testFailure1() throws TransactionRecordsException {
		String args [] = {"ACC334455", "transaction-records.csv", "20/10/2018T12:00:00", "21/10/2018T19:00:00"};
		result = TransactionRecordsApp.start(args);
		
		assertNotEquals("-$25.00", result.getFinalTotalStr());
		assertNotEquals(Integer.valueOf(1), result.getTxnCnt());
    }
	
	@Test(expected=Exception.class)
    public void testException1() throws Exception {
		String args [] = {"ACC334455", "transaction-records-1.csv", "20/10/2018T12:00:00", "21/10/2018T19:00:00"};
		result = TransactionRecordsApp.start(args);
		
		assertEquals("-$25.00", result.getFinalTotalStr());
		assertEquals(Integer.valueOf(1), result.getTxnCnt());
    }
}
