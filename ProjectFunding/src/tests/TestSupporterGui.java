package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import net.sourceforge.jwebunit.junit.WebTester;



public class TestSupporterGui {

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
	public void testSearch() {
		// Start testing for Supporter->search
		tester.beginAt("search");

		// Check all components of the search form
		
		tester.assertFormPresent();
		tester.assertTextPresent("Required Information");
		tester.assertFormElementPresent("status");
		tester.assertButtonPresent("SelectPDWebpage");

		// Submit the form with given parameters
		tester.assertRadioOptionPresent("status", "open");
		tester.assertRadioOptionPresent("status", "successful");
		tester.assertRadioOptionPresent("status", "fail");
		
		tester.clickButton("SelectPDWebpage");

		// Check the representation of the table for an empty result
		tester.assertTablePresent("PDs");
		String[][] tableHeadings = { { "#", "Name", "Description", "Funding Limit","Total Donation","Project Status" } };
		tester.assertTableEquals("PDs", tableHeadings);
	
	}


}
