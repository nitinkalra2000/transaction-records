package com.mebank.transaction.records;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;

/**
 * Unit test cases.
 */
public class TransactionRecordsAppTest {
	TransactionResult result = null;

	@Test
    public void testSuccess1() throws IOException, URISyntaxException {
		String args [] = {"ACC334455", "transaction-records.csv", "20/10/2018T12:00:00", "20/10/2018T19:00:00"};
		result = TransactionRecordsApp.start(args);
		
		assertEquals("-$25.00", result.getFinalTotalStr());
		assertEquals(Integer.valueOf(1), result.getTxnCnt());
    }
}
