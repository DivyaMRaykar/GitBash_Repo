package pojoclass.utility;

public class ProjectPojo {

	String projectName;
	String createdBy;
	String status;
	int teamSize;
	
	private ProjectPojo() {}

	public ProjectPojo(String projectName, String createdBy, String status, int teamSize) {
		super();
		this.projectName = projectName;
		this.createdBy = createdBy;
		this.status = status;
		this.teamSize = teamSize;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTeamSize() {
		return teamSize;
	}

	public void setTeamSize(int teamSize) {
		this.teamSize = teamSize;
	}

}
