package tests;

import dbadapter.DBFacade;
import dbadapter.ProjectsDatabase;
import junit.framework.TestCase;
import application.PFApplication;
import datatypes.ProjectStarterData;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;



import org.junit.Before;
import org.junit.Test;

public class TestPFApplication extends TestCase  {
	
	DBFacade DBFacadeStub = mock(DBFacade.class) ;
	PFApplication pf =new PFApplication();
	ProjectStarterData psd =mock( ProjectStarterData.class);
	ProjectsDatabase ps = mock(ProjectsDatabase.class);
	ArrayList<ProjectsDatabase> mockedProjectsList = mock(ArrayList.class);
	
	
	
	@Before
 	public void prepare ()  {
	DBFacade.setInstance(DBFacadeStub);	
	psd= new ProjectStarterData("group20@gmail","pay");
	when(DBFacadeStub.getProjectDetails("open")).thenReturn(mockedProjectsList);
	
	}
		
	@Test
	public void testSearchAndCreate() {
		try {
			
			//test create
			pf.createFR("hello", "description", "2015-12-04","8500" ,psd, "book", "two books");
			verify(DBFacadeStub).makingFundingRequest("hello", "description", Timestamp.valueOf("2015-12-04"),8500 ,psd,"book", "two books" );
			
			//test search
			when(DBFacadeStub.getProjectDetails("open")).thenReturn(pf.getProjects("open"));
			assertEquals(mockedProjectsList, pf.getProjects("open"));
		} catch (Exception e) {
		e.printStackTrace();
	}
	}

}
