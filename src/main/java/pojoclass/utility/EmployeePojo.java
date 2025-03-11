package pojoclass.utility;

public class EmployeePojo {	  
	  public String designation;	   
	  public String dob;	  
	  public String email;	 
	  public String empName;	  
	  public  double experience;
	  public String mobileNo;
	  public String project;
	  public String role;
	  public String username;
	  
	  private EmployeePojo() {
		  
	  }

	    public EmployeePojo(String designation, String dob, String email, String empName, double experience,
			String mobileNo, String project, String role, String username) {
		super();
		this.designation = designation;
		this.dob = dob;
		this.email = email;
		this.empName = empName;
		this.experience = experience;
		this.mobileNo = mobileNo;
		this.project = project;
		this.role = role;
		this.username = username;
	}

		public void setDesignation(String designation) {
	        this.designation = designation;
	    }
	    public String getDesignation() {
	        return designation;
	    }
	    
	    public void setDob(String dob) {
	        this.dob = dob;
	    }
	    public String getDob() {
	        return dob;
	    }
	    
	    public void setEmail(String email) {
	        this.email = email;
	    }
	    public String getEmail() {
	        return email;
	    }
	    
	    public void setEmpName(String empName) {
	        this.empName = empName;
	    }
	    public String getEmpName() {
	        return empName;
	    }
	    
	    public void setExperience(int experience) {
	        this.experience = experience;
	    }
	    public double getExperience() {
	        return experience;
	    }
	    
	    public void setMobileNo(String mobileNo) {
	        this.mobileNo = mobileNo;
	    }
	    public String getMobileNo() {
	        return mobileNo;
	    }
	    
	    public void setProject(String project) {
	        this.project = project;
	    }
	    public String getProject() {
	        return project;
	    }
	    
	    public void setRole(String role) {
	        this.role = role;
	    }
	    public String getRole() {
	        return role;
	    }
	    
	    public void setUsername(String username) {
	        this.username = username;
	    }
	    public String getUsername() {
	        return username;
	    }
	    
	}


