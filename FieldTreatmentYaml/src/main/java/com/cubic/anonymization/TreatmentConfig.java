package com.cubic.anonymization;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.cubic.anonymization.Azure.AzureConfig;
import com.cubic.anonymization.entity.Dataset;
import com.cubic.anonymization.entity.Degree;
import com.cubic.anonymization.entity.ListDataset;
import com.cubic.anonymization.entity.ListDegree;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import com.cubic.anonymization.response.DegreeTreatment;
import com.cubic.anonymization.response.FieldTreatment;
import com.microsoft.azure.storage.StorageException;

public class TreatmentConfig {

	private ListDataset ListDataset;
	private ListDegree ListDegree;
	private AzureConfig azureConfig = new AzureConfig();

	/* No need to read from system path as yaml files are resource files*/
	//private String datasetYamlPath,degreeYamlPath="";

	//Updated code to read from resource
	public ListDataset getDatasetYaml()  {

		//ClassLoader classLoader = getClass().getClassLoader();
		//InputStream datasetIn = classLoader.getResourceAsStream("Dataset.yaml");
		InputStreamReader inputStreamReader = null;
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		try {
			 inputStreamReader = azureConfig.getYamlStreamFromAzure(azureConfig.getProperties().getProperty("dataset.filename"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			ListDataset = mapper.readValue(inputStreamReader, ListDataset.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ListDataset;
	}

	//Updated code to read from resource
	public ListDegree getDegreeYaml()   {

		//ClassLoader classLoader = getClass().getClassLoader();
		//InputStream degreeIn = classLoader.getResourceAsStream("Degree.yaml");
		InputStreamReader inputStreamReader = null;
		try {
			inputStreamReader = azureConfig.getYamlStreamFromAzure(azureConfig.getProperties().getProperty("degree.filename"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		try {
			ListDegree = mapper.readValue(inputStreamReader, ListDegree.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ListDegree;
	}

	//try to avoid duplication
	public List<FieldTreatment> getDataset(String tableName) throws IOException {
		ListDataset ListDataset = getDatasetYaml();
		List<Dataset> datasets = ListDataset.getDatasets();
		List<FieldTreatment> dataSetTreatments = new ArrayList<FieldTreatment>();
		if (!"".equals(tableName)) {
			datasets.stream().forEach(dataset -> {
				if (dataset.getName().equals(tableName)) {
					dataset.getFields().stream().forEach(field -> {
						FieldTreatment datasetTreatment = new FieldTreatment();
						datasetTreatment.setDataset(dataset.getName());
						datasetTreatment.setField(field.getName());
						field.getTreatments().stream().forEach(treatment -> {
							datasetTreatment.setDeid(treatment.getDeid());
							datasetTreatment.setAction(treatment.getAction());
							datasetTreatment.setUser(treatment.getUser());
						});
						dataSetTreatments.add(datasetTreatment);
					});
				}

			});
		} else {
			datasets.stream().forEach(dataset -> {
				dataset.getFields().stream().forEach(field -> {
					FieldTreatment datasetTreatment = new FieldTreatment();
					datasetTreatment.setDataset(dataset.getName());
					datasetTreatment.setField(field.getName());
					field.getTreatments().stream().forEach(treatment -> {
						datasetTreatment.setDeid(treatment.getDeid());
						datasetTreatment.setAction(treatment.getAction());
						datasetTreatment.setUser(treatment.getUser());
					});
					dataSetTreatments.add(datasetTreatment);
				});
			});
		}
		return dataSetTreatments;
	}

	public List<DegreeTreatment> getDegree() throws IOException {
		ListDegree ListDegree = getDegreeYaml();
		List<Degree> degrees = ListDegree.getDegrees();

		List<DegreeTreatment> degreeResponses = new ArrayList<DegreeTreatment>();
		degrees.stream().forEach(degree -> {
			degree.getTreatments().stream().forEach(treatment -> {
				DegreeTreatment degreeResponse = new DegreeTreatment();
				degreeResponse.setDegree(degree.getName());
				degreeResponse.setDeid(treatment.getDeid());
				degreeResponse.setUser(treatment.getUser());
				degreeResponse.setAction(treatment.getAction());
				degreeResponses.add(degreeResponse);
			});
		});

		return degreeResponses;
	}

	public List<FieldTreatment> getFieldTreatment(List<DegreeTreatment> degreeResponse,
			List<FieldTreatment> dataSetResponse) {
		List<FieldTreatment> responseList = new ArrayList<FieldTreatment>();
		dataSetResponse.stream().forEach(dataset -> {
			degreeResponse.stream().forEach(degree -> {
				FieldTreatment degreeY = new FieldTreatment();
				degreeY.setDataset(dataset.getDataset());
				degreeY.setField(dataset.getField());
				String deid = dataset.getDeid();
				String user = dataset.getUser();
				if (deid.equals(degree.getDeid()) && user.equals(degree.getUser())) {
					degreeY.setAction(dataset.getAction());
				} else {
					degreeY.setAction(degree.getAction());
				}
				degreeY.setUser(degree.getUser());
				degreeY.setDeid(degree.getDeid());
				responseList.add(degreeY);
			});

		});
		return responseList;
	}

	//Added method to get required treatments as per table
	public  List<FieldTreatment> getTreatments(String tableName){
		List<FieldTreatment> fieldDegreeTreatments = new ArrayList<>();
		try{
			List<DegreeTreatment> degreeTreatments = this.getDegree();
			List<FieldTreatment> fieldTreatments = this.getDataset(tableName);
			fieldDegreeTreatments = this.getFieldTreatment(degreeTreatments, fieldTreatments);

		}catch(Exception e){
			e.printStackTrace();
		}
		return fieldDegreeTreatments;
	}

}
