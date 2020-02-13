package dbadapter;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import datatypes.ProjectStarterData;
import datatypes.SupporterData;
import dbadapter.Configuration;
import dbadapter.ProjectsDatabase;


/**
 * Class which acts as the connector between application and database. Creates
 * Java objects from SQL returns.  
 *
 */
public class DBFacade{
	private static DBFacade instance;

	/**
	 * Constructor which loads the corresponding driver for the chosen database
	 * type
	 */
	private DBFacade() {
		try {
			Class.forName("com." + Configuration.getType() + ".jdbc.Driver")
					.newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void setInstance(DBFacade instance) {
		DBFacade.instance = instance;
	}

	/**
	 * Implementation of the Singleton pattern.
	 * 
	 * @return
	 */
	public static DBFacade getInstance() {
		if (instance == null) {
			instance = new DBFacade();
		}

		return instance;
	}
	
	
	
	public void makingFundingRequest(String name, String description, Timestamp endDateTime, double fundinglimit,  ProjectStarterData p,String listOfRewardsLess, String listOfRewardsMore) 
	{

		// Declare SQL query to create page.
		String sqlInsert = "INSERT INTO project (name,description,fundingLimit,endDate,email,paymentService,listOfRewardsLess,listOfRewardsMore) VALUES (?,?,?,?,?,?,?,?)";

		// Insert project into database.
		try (Connection connection = DriverManager.getConnection(
				"jdbc:" + Configuration.getType() + "://"
						+ Configuration.getServer() + ":"
						+ Configuration.getPort() + "/"
						+ Configuration.getDatabase(), Configuration.getUser(),
				Configuration.getPassword())) {

			try (PreparedStatement ps = connection.prepareStatement(sqlInsert)) {
				ps.setString(1, name);
				ps.setString(2, description);
				ps.setDouble(3, fundinglimit);				
				ps.setTimestamp(4,endDateTime);
				ps.setString(5, p.getEmail());
				ps.setString(6, p.getPaymentService());
				if(listOfRewardsLess.isEmpty())
					ps.setNull(7, java.sql.Types.VARCHAR);
				else
				ps.setString(7, listOfRewardsLess);
				if(listOfRewardsMore.isEmpty())
					ps.setNull(8, java.sql.Types.VARCHAR);
				else
				ps.setString(8, listOfRewardsMore); 
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	public ArrayList<ProjectsDatabase> getProjectDetails(String projectStauts) {

		ArrayList<ProjectsDatabase> result = new ArrayList<ProjectsDatabase>();

		// Declare the necessary SQL queries.
		String sqlSelect = "SELECT * FROM project WHERE projectStatus = ?";

		// Query all offers that fits to the given criteria.
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {

			try (PreparedStatement ps = connection.prepareStatement(sqlSelect)) {
				ps.setString(1, projectStauts);

				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						ProjectsDatabase temp = new ProjectsDatabase(rs.getInt(1), rs.getString(2), rs.getString(3),
								rs.getDouble(4), rs.getTimestamp(5),
								new ProjectStarterData(rs.getString(6), rs.getString(7)), rs.getString(8),rs.getDouble(9));	
						result.add(temp);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	
	
	
	public void createDonation(int id,SupporterData s, double amount) 
	{

		// Declare SQL query to create Donation
		String sqlInsert = "INSERT INTO donation (projectId,email,paymentService,amount) VALUES (?,?,?,?)";
		String sqlUpdate = "UPDATE project SET totalDonation=? WHERE id=? ";
		String sqlSelect= "SELECT * FROM project WHERE id = ?";

		// Insert offer into database.
		try (Connection connection = DriverManager.getConnection(
				"jdbc:" + Configuration.getType() + "://"
						+ Configuration.getServer() + ":"
						+ Configuration.getPort() + "/"
						+ Configuration.getDatabase(), Configuration.getUser(),
				Configuration.getPassword())) {

			try (PreparedStatement ps = connection.prepareStatement(sqlInsert);
					PreparedStatement psB = connection.prepareStatement(sqlUpdate);
					PreparedStatement psC = connection.prepareStatement(sqlSelect)) {
				

			psC.setInt(1, id);
				
				try (ResultSet rs = psC.executeQuery()) {
					if (rs.next()) {
						
						double sum = rs.getDouble(9);
						psB.setDouble(1, sum+amount);
						psB.setInt(2, id);
						psB.executeUpdate();
						
					}	
				}
				
				
				ps.setInt(1, id);
				ps.setString(2, s.getEmail());				
				ps.setString(3, s.getPaymentService());
				ps.setDouble(4, amount);
				ps.executeUpdate();
				
				
					
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	
}