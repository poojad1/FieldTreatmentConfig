package responses;

public class DegreeTreatment {
	
	
	private String degree;	
	
	private String deid;	
	
	private String user;	
	
	private String action;
	
	 public DegreeTreatment( String degree,String deid, String user,String action) {
	        super();
	        this.degree = degree;
	        this.deid = deid;
	        
	        this.user = user;
	        
	        this.action = action;
	    }
	
	public DegreeTreatment() {
		super();		
	}

	public String getDegree() {
		return degree;
	}


	public void setDegree(String degree) {
		this.degree = degree;
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
