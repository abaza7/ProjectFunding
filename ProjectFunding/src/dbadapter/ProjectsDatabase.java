package dbadapter;

import java.sql.Timestamp;

import datatypes.ProjectStarterData;
import datatypes.TimeData;

public class ProjectsDatabase {
	
	private int id;
	private String name;
	private String description;
	private double fundingLimit;
	private Timestamp endDateTime;
	private ProjectStarterData projectStarterData;
	private String projectStatus;
	private double totalDonation;
	public ProjectsDatabase(int id, String name, String description, double fundingLimit, Timestamp endDateTime,
			ProjectStarterData projectStarterData, String projectStatus, double totalDonation) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.fundingLimit = fundingLimit;
		this.endDateTime = endDateTime;
		this.projectStarterData = projectStarterData;
		this.projectStatus = projectStatus;
		this.totalDonation=totalDonation;
	}
	public double getTotalDonation() {
		return totalDonation;
	}
	public void setTotalDonation(double totalDonation) {
		this.totalDonation = totalDonation;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getFundingLimit() {
		return fundingLimit;
	}
	public void setFundingLimit(double fundingLimit) {
		this.fundingLimit = fundingLimit;
	}
	public Timestamp getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(Timestamp endDateTime) {
		this.endDateTime = endDateTime;
	}
	public ProjectStarterData getProjectStarterData() {
		return projectStarterData;
	}
	public void setProjectStarterData(ProjectStarterData projectStarterData) {
		this.projectStarterData = projectStarterData;
	}
	public String getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}
	
	

}
