package yaml.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Constants.Constants;
import Response.DegreeResponse;
import Response.FinalResponseDataSet;
import Response.ResponseDataset;

public class YamlTreatment {

	public static void main(String[] args) throws IOException {
		TreatmentConfig config = new TreatmentConfig();
		String tableName = "use_transaction";
		try {
			List<DegreeResponse> degreeDefaultTreatment = config.getDegree();
			System.out.println("=============================");
			System.out.println(Constants.DEGREE + " " + Constants.DEID + " " + Constants.USER + " " + Constants.ACTION);
			for (DegreeResponse degreeResponse : degreeDefaultTreatment) {
				System.out.println(degreeResponse.getDegree() + " " + degreeResponse.getDeid() + " "
						+ degreeResponse.getUser() + " " + degreeResponse.getAction());
			}

			List<ResponseDataset> dataSetTreatment = new ArrayList<ResponseDataset>();
			dataSetTreatment = config.getDataset(tableName);
			
			System.out.println("=============================");
			System.out.println(Constants.DATASET + " " + Constants.FIELD + " " + Constants.DEID + " " + Constants.USER
					+ "  " + Constants.ACTION);
			for (ResponseDataset dataSetResponse : dataSetTreatment) {
				System.out.println(dataSetResponse.getDataset() + " " + dataSetResponse.getField() + " "
						+ dataSetResponse.getDeid() + " " + dataSetResponse.getUser() + " "
						+ dataSetResponse.getAction());
			}

			System.out.println("=============================");
			System.out.println(Constants.DATASET + " " + Constants.FIELD + "  " + Constants.DEID + " " + Constants.USER
					+ " " + Constants.ACTION);
			List<FinalResponseDataSet> finalDatasetTreatment = config.getFinalDatasetResponse(degreeDefaultTreatment,
					dataSetTreatment);
			for (FinalResponseDataSet finalDataSet : finalDatasetTreatment) {
				System.out.println(finalDataSet.getDataset() + " " + finalDataSet.getField() + " "
						+ finalDataSet.getDeid() + " " + finalDataSet.getUser() + " " + finalDataSet.getAction());
			}

		} catch (Exception e) {
			System.out.println("Please provide the  correct path for Yaml in config file "+e.getMessage());
		}

	}

}
