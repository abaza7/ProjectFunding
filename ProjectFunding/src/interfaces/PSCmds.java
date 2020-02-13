package interfaces;

import datatypes.ProjectStarterData;

public interface PSCmds {

	public void createFR(String name, String description, String endDateTime, String fundinglimit,  ProjectStarterData p ,String listOfRewardsLess, String listOfRewardsMore);
}