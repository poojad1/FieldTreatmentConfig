package com.csv.yaml.conversion;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;


/**    */
public class CSVConfig {
	String input,output="";
	Properties properties = new Properties();
	InputStream inputStream = null;
	
	/**    */
	public String getCSV() throws IOException {
		inputStream =new FileInputStream("config.properties");
		properties.load(inputStream);
		String csvDataSet = properties.getProperty("pathDataSetcsv");	
		properties.clear();
		inputStream.close();
		return csvDataSet;
	}
	/**    */
    public String getJson() throws IOException {
    	inputStream = new FileInputStream("config.properties");
		properties.load(inputStream);
		String csvDataSet = properties.getProperty("pathDataSetjson");		
		inputStream.close();
		return csvDataSet;
	}
    /**    */
	public String getYaml() throws IOException {
		inputStream = new FileInputStream("config.properties");
		properties.load(inputStream);
		String csvDataSet = properties.getProperty("pathDataSetyaml");		
		inputStream.close();
		return csvDataSet;
	}
	/**    */
	public void getJsonFromCSV() throws IOException{
		File input = new File(getCSV());
		 File output = new File(getJson()); 
        CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();
        CsvMapper csvMapper = new CsvMapper();
        List<Object> readAll = csvMapper.readerWithSchemaFor(Map.class).with(csvSchema).readValues(input).readAll();        	
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(output, readAll);
	}
	/**    */
	public void getYamlFromJson()throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(getJson()));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(getYaml()));
		String line = null;
		StringBuilder data = new StringBuilder();
		while ((line = bufferedReader.readLine()) != null){
			data = data.append(line);
			data.append("\n");
		}
		// parse JSON
		JsonNode jsonNodeTree = new ObjectMapper().readTree(data.toString());	
		// save it as YAML
		String jsonAsYaml = new YAMLMapper().writeValueAsString(jsonNodeTree);
		System.out.println(jsonAsYaml);		
		bufferedWriter.write(jsonAsYaml);
		bufferedReader.close();
		bufferedWriter.close();
	}
}
