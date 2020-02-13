package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.PFApplication;
import datatypes.ProjectStarterData;
import dbadapter.ProjectsDatabase;

/**
 * Servlet implementation class SupporterGui
 */
@WebServlet("/SupporterGui")
public class SupporterGui extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			request.getRequestDispatcher("/templates/searchForm.ftl").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("action").equals("search")) {

			// Append parameter of request
			String status = (String) request.getParameter("status");
			ArrayList<ProjectsDatabase> result = new ArrayList<ProjectsDatabase>();
			result = new PFApplication().getProjects(status);

			// Dispatch message to template engine
			try {
			    request.setAttribute("projects",result);
				request.getRequestDispatcher("/templates/SearchResults.ftl").forward(request, response);

			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}else {
		doGet(request, response);
	}
	}
	}


