package responses;

public class FieldTreatment {
	
	private String dataset;	
	private String field;
	private String deid;	
	private String user;
	private String action;
	
	public String getDataset() {
		return dataset;
	}


	public void setDataset(String dataset) {
		this.dataset = dataset;
	}


	public String getField() {
		return field;
	}


	public void setField(String field) {
		this.field = field;
	}


	public String getDeid() {
		return deid;
	}


	public void setDeid(String deid) {
		this.deid = deid;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}

}
