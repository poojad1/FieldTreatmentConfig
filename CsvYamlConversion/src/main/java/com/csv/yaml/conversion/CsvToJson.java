package com.csv.yaml.conversion;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;


/**    */
public class CsvToJson {
	public static void main(String[] args) throws JsonProcessingException, IOException {
		CSVConfig config = new CSVConfig();	
		config.getJsonFromCSV();
		System.out.println("The yaml for the input csv file :");
		config.getYamlFromJson();
	}
}
