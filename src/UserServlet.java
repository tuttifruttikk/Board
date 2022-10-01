import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;


public class UserServlet extends HttpServlet {
	private HashMap<String, String> users = null;
	private UserIO io = new UserIO("../webapps/servlet-15/users.txt");

	public void init(){
		users = io.loadFromFile();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter printWriter = response.getWriter();
			String action = (String) request.getAttribute("action");
			String username = (String) request.getParameter("username");
			String password = (String) request.getParameter("password");

			if (username == null || username.length() < 1 || password == null || password.length() < 1) {
				printWriter.println(reportAboutBug("Incorrect username or password, please try again."));
				return;
			}

			if ("registration".equals(action)) {
				if (!users.containsKey(username)) {
					users.put(username, password);
					io.saveToFile(users);
					createSession(request, response, username);
				} else {
					printWriter.println(reportAboutBug("Username already exists."));
				}
			} else {
				if ("login".equals(action)) {
					if (password.equals(users.get(username))) {
						createSession(request, response, username);
					} else {
						printWriter.println(reportAboutBug("Incorrect username or password, please try again."));
					}
				}
			}
		} catch (IOException exception) {
			exception.printStackTrace();
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

	
	private void createSession(HttpServletRequest request, HttpServletResponse response, String username) throws IOException {
		HttpSession session = request.getSession();
		session.setAttribute("username", username);
		response.sendRedirect("board");
	}

}
