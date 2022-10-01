import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import java.io.PrintWriter;
import java.io.IOException;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class BoardServlet extends HttpServlet {

	private ArrayList<Advertisement> advertisementList = new ArrayList<Advertisement>();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrintWriter printWriter = response.getWriter();
		RequestDispatcher requestDispatcher = null;

		
		
		printWriter.println("<!DOCTYPE html>");
		printWriter.println("<html>");
		request.getRequestDispatcher("header.html").include(request, response);
		if(session != null) {
			requestDispatcher = request.getRequestDispatcher("header_for_users.html");
		} else {
			requestDispatcher = request.getRequestDispatcher("header_for_guests.html");
		}

		requestDispatcher.include(request, response);

		printWriter.println("<body>");
		printWriter.println("<div class='board'>");

		for(int i = 0; i < advertisementList.size(); ++i) {
				printWriter.println(advertisementList.get(i));
		}
		
		printWriter.println("</div>");
		printWriter.println("</body>");
		printWriter.println("</html>");
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		PrintWriter printWriter = response.getWriter();
		HttpSession session = request.getSession(false);

		String header = (String) request.getParameter("header");
		String text = (String) request.getParameter("text");
		String rating = (String) request.getParameter("sort");


		if(session != null) {
			if(rating.equals("like")) {
				advertisementList.
			}
			advertisementList.add(new Advertisement(header, text, (String) session.getAttribute("username")));
			response.sendRedirect("board");
		} else {
			printWriter.println(reportAboutBug("Please sign in"));
		}
	}
	
	
	public String reportAboutBug(String cause) {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("<!DOCTYPE html>");
		stringBuilder.append("<html>");

		stringBuilder.append("<head>");
		stringBuilder.append("<meta charset='utf-8'/>");
		stringBuilder.append("<link rel=\'stylesheet\' href=\'styles/error_page.css\'/>");
		stringBuilder.append("<div class=\"error-header\">");
		stringBuilder.append("<title>");
		stringBuilder.append("Error!");
		stringBuilder.append("</title>");
		stringBuilder.append("</div>");
		stringBuilder.append("</head>");
		
		stringBuilder.append("<body>");
		stringBuilder.append("<div class=\"error-text\">");
		stringBuilder.append("<p>ERROR</p>");
		stringBuilder.append("<p>");
		stringBuilder.append(cause);
		stringBuilder.append("</p>");
		stringBuilder.append("</div>");
		stringBuilder.append("</body>");

		stringBuilder.append("</html>");

		return stringBuilder.toString();
	}


}
