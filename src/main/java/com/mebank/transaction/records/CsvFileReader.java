package com.mebank.transaction.records;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * This class will be used to read the CSV file
 * and map the data points from CSV to the
 * Transaction POJO
 */
public class CsvFileReader {

	ObjectMapper objMapper = new ObjectMapper();

	CsvFileReader() {
		objMapper = new CsvMapper().registerModule(new JavaTimeModule());
	}
	
	public List<TransactionModel> read(String fileName) throws IOException, URISyntaxException {

		URL fileURL = getClass().getResource("/"+fileName);
        File csvFile = new File(fileURL.toURI());
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        return objMapper.readerFor(TransactionModel.class)
        				.with(schema) 
        				.with(CsvParser.Feature.TRIM_SPACES).readValues(csvFile) 
        				.readAll().stream()
        				.map(item -> (TransactionModel) item).collect(Collectors.toList());
    }
} 
