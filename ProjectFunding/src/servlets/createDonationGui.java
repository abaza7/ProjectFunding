package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.PFApplication;
import datatypes.ProjectStarterData;
import datatypes.SupporterData;

/**
 * Servlet implementation class createDonationGui
 */
@WebServlet("/createDonationGui")
public class createDonationGui extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createDonationGui() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Dispatch request to template engine
		
		String action = (request.getParameter("action") == null) ? "" : request.getParameter("action");

		
		if (action.equals("selectProject")) {
			// Set request attributes
			request.setAttribute("pid", request.getParameter("pid"));

			// Dispatch request to template engine
			try {
				request.getRequestDispatcher("/templates/defaultWebpageDonation.ftl").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// Otherwise show search form
		}/* else {

			// Dispatch request to template engine
			try {
				request.getRequestDispatcher("/templates/defaultWebpageDonation.ftl").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}*/
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("action").equals("createDonation")) {

			// Append parameter of request
			String email = (String) request.getParameter("email");
			String amount = (String) request.getParameter("amount");
			String payment = (String) request.getParameter("paymentService");
			String pid = (String) request.getParameter("pid");

			// Call application to create project
			new PFApplication().createDonation(pid,new SupporterData(email,payment),amount);

			// Dispatch message to template engine
			try {
				//request.setAttribute("message", "New offer successful stored in the database.");
				request.getRequestDispatcher("/templates/showConfirmMake.ftl").forward(request, response);

			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			// Call doGet if request is not equal to insertOffer
		} else
			doGet(request, response);

	}
	}
