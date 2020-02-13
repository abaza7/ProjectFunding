package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import net.sourceforge.jwebunit.junit.WebTester;

public class TestProjectStarterGui {

	private WebTester tester;

	/**
	 * Create a new WebTester object that performs the test.
	 */
	@Before
	public void prepare() {
		tester = new WebTester();
		tester.setBaseUrl("http://localhost:8080/Swtpro/");
	}

	@Test
	public void testcreateFR() {
		// Start testing for ProjectStarter->create
		tester.beginAt("create");

		// Check all components of the createProject form
		
		tester.assertFormPresent();
		tester.assertTextPresent("Required Information");
		tester.assertTextPresent("Name");
		tester.assertFormElementPresent("name");
		tester.assertTextPresent("Description");
		tester.assertFormElementPresent("description");
		tester.assertTextPresent("End Date Time");
		tester.assertFormElementPresent("endDateTime");
		tester.assertTextPresent("Fundinglimit");
		tester.assertFormElementPresent("fundingLimit");
		tester.assertTextPresent("Email");
		tester.assertFormElementPresent("email");
		tester.assertTextPresent("Payment service");
		tester.assertFormElementPresent("paymentService");
		tester.assertButtonPresent("GoToFeedback");

		// Submit the form with given parameters
		tester.setTextField("name", "Group20");
		tester.setTextField("description", "we attend at 10:30");
		tester.setTextField("endDateTime", "01/31/2020");
		tester.setTextField("fundingLimit", "400");
		tester.setTextField("email", "group20@swt.due.de");
		tester.setTextField("paymentService", "paypal");
		
		tester.clickButton("GoToFeedback");

		// Check the representation of the text on the feedback page
		tester.assertTextPresent("Project Added");
		tester.assertTextPresent("Successfully");
		 
	
	}



}
