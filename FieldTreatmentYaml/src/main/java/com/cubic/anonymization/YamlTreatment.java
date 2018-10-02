
package com.cubic.anonymization;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import com.cubic.anonymization.constant.Constant;
import com.cubic.anonymization.response.FieldTreatment;
import com.cubic.anonymization.response.DegreeTreatment;

public class YamlTreatment {

	public static void main(String[] args) {
/*		TreatmentConfig config = new TreatmentConfig();
		String tableName = "use_transaction";
		try {
			List<DegreeTreatment> degreeTreatments = config.getDegree();
			System.out.println("=============================");
			System.out.println(Constant.DEGREE + " " + Constant.DEID + " " + Constant.USER + " " + Constant.ACTION);
			for (DegreeTreatment degreeTreatment : degreeTreatments) {
				System.out.println(degreeTreatment.getDegree() + " " + degreeTreatment.getDeid() + " "
					+ degreeTreatment.getUser() + " " + degreeTreatment.getAction());
			}
			List<FieldTreatment> fieldTreatments = config.getDataset(tableName);
			System.out.println("=============================");
			System.out.println(Constant.DATASET + " " + Constant.FIELD + " " + Constant.DEID + " " + Constant.USER
					+ "  " + Constant.ACTION);
			for (FieldTreatment fieldTreatment : fieldTreatments) {
				System.out.println(fieldTreatment.getDataset() + " " + fieldTreatment.getField() + " "
						+ fieldTreatment.getDeid() + " " + fieldTreatment.getUser() + " " + fieldTreatment.getAction());
			}
			System.out.println("=============================");
			System.out.println(Constant.DATASET + " " + Constant.FIELD + "  " + Constant.DEID + " " + Constant.USER
					+ " " + Constant.ACTION);
			List<FieldTreatment> fieldDegreeTreatments = config.getFieldTreatment(degreeTreatments, fieldTreatments);
			for (FieldTreatment fieldDegreeTreatment : fieldDegreeTreatments) {
				System.out.println(fieldDegreeTreatment.getDataset() + " " + fieldDegreeTreatment.getField() + " "
						+ fieldDegreeTreatment.getDeid() + " " + fieldDegreeTreatment.getUser() + " "
						+ fieldDegreeTreatment.getAction());
			}
		} catch (Exception e) {
			System.out.println("Please provide the  correct path for Yaml in config file " + e.getMessage());
		}*/
	}
}

