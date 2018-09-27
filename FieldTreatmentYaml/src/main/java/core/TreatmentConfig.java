package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import com.entity.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import responses.DegreeTreatment;
import responses.FieldTreatment;


public class TreatmentConfig {

	ListDataset ListDataset;
	ListDegree ListDegree;

	public ListDataset getDatasetYaml() throws IOException {
		Properties prop = new Properties();
		InputStream input = null;
		input = new FileInputStream("config.properties");
		prop.load(input);
		String datasetYamlPath = prop.getProperty("pathDataSet");
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		ListDataset = mapper.readValue(new File(datasetYamlPath), ListDataset.class);
		return ListDataset;
	}

	public ListDegree getDegreeYaml() throws IOException {
		Properties prop = new Properties();
		InputStream input = null;
		input = new FileInputStream("config.properties");
		prop.load(input);
		String degreeYamlPath = prop.getProperty("pathDegree");
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		ListDegree = mapper.readValue(new File(degreeYamlPath), ListDegree.class);
		return ListDegree;
	}

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
		}else {
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

}
