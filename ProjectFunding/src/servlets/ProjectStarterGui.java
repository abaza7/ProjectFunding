package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.PFApplication;
import datatypes.ProjectStarterData;

/**
 * Servlet implementation class createProjectGui
 */
@WebServlet("/ProjectStarterGui")
public class ProjectStarterGui extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Dispatch request to template engine
				try {
					request.getRequestDispatcher("/templates/defaultWebpagePS.ftl").forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("action").equals("createProject")) {

			// Append parameter of request
			String name = (String) request.getParameter("name");
			String description = (String) request.getParameter("description");
			String endDateTime = (String) request.getParameter("endDateTime");
			String fundinglimit = (String) request.getParameter("fundingLimit");
			String email = (String) request.getParameter("email");
			String payment = (String) request.getParameter("paymentService");
			String listOfRewardsLess = (String) request.getParameter("listOfRewardsLess");
			String listOfRewardsMore = (String) request.getParameter("listOfRewardsMore");

			// Call application to createFR
			new PFApplication().createFR(name, description,endDateTime, fundinglimit, new ProjectStarterData(email,payment),listOfRewardsLess,listOfRewardsMore);

			// Dispatch message to template engine
			try {
				
				request.getRequestDispatcher("/templates/showConfirmMake.ftl").forward(request, response);

			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			// Call doGet if request is not equal to createFR
		} else
			doGet(request, response);

	}
	}

