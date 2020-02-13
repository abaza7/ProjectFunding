package interfaces;

import java.util.ArrayList;

import datatypes.SupporterData;
import dbadapter.ProjectsDatabase;

public interface SCmds {
	
	public ArrayList<ProjectsDatabase> getProjects(String ProjectStatus);
	
	public void createDonation(String pid, SupporterData s,String amount);

}
