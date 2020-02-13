package application;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import datatypes.ProjectStarterData;
import datatypes.SupporterData;
import dbadapter.DBFacade;
import dbadapter.ProjectsDatabase;
import interfaces.PSCmds;
import interfaces.SCmds;

import java.util.ArrayList;


public class PFApplication implements PSCmds, SCmds{

	@Override
	public void createFR(String name, String description, String endDateTime, String fundinglimit,
			ProjectStarterData p, String listOfRewardsLess,String listOfRewardsMore) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date date = dateFormat.parse(endDateTime);
			long time = date.getTime();
			Timestamp endDateSQL = new Timestamp(time);
			double fundingSQl = Double.parseDouble(fundinglimit);
			
			 DBFacade.getInstance().makingFundingRequest(name, description, endDateSQL, fundingSQl, p,listOfRewardsLess,listOfRewardsMore);
			 
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Override
	public ArrayList<ProjectsDatabase> getProjects(String ProjectStatus)
	{
		ArrayList<ProjectsDatabase> result = new ArrayList<ProjectsDatabase>();
		result = DBFacade.getInstance().getProjectDetails(ProjectStatus);
	return result;
	}

	@Override
	public void createDonation(String pid, SupporterData s, String amount) {
		try {
			double amountSQl = Double.parseDouble(amount);
			int pidSQL = Integer.parseInt(pid);
			DBFacade.getInstance().createDonation( pidSQL, s, amountSQl);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}