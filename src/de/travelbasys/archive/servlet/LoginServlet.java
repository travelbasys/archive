package de.travelbasys.archive.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.travelbasys.archive.dao.SessionDAO;
import de.travelbasys.archive.dao.UserDAO;
import de.travelbasys.archive.model.User;
import de.travelbasys.archive.utils.Templates;

@WebServlet({ "/" })
public class LoginServlet extends HttpServlet {

	private static Log log = LogFactory.getFactory().getInstance(LoginServlet.class.getName());

	/**
	 * 
	 * @author Nils, Paul and Amjad
	 * 
	 */

	private static final long serialVersionUID = 1569485124701548457L;

	private HttpServletRequest request = null;

	private HttpServletResponse response = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		request = req;
		response = resp;
		doRequest("Get Protocol for LoginServlet..");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		request = req;
		response = resp;

		doRequest("Post Protocol for LoginServlet..");
	}

	private void doRequest(String string) {

		log.debug(string);
		String text = "";
		try {

			String userID = request.getParameter("userID");
			String password = request.getParameter("password");

			log.info("user: " + userID);
			log.info("password: " + password);

			String selectStatement = "select * from user where UserID = '" + userID + " ' and Password=MD5('" + password
					+ "');";

			UserDAO userDAO = new UserDAO();
			User user = (User) userDAO.doSelectElement(selectStatement);

			if (userID == null && password == null) {
				text = Templates.ReadFile("Login.html");
			} else if (user != null) {

				// Generate SessionID
				SessionDAO sessionDAO = new SessionDAO();

				int timeout = 12;
				String timeoutString = String.valueOf(timeout);
				log.debug("timeoutString: " + timeoutString);

				Date now = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yy.MM.dd HH:mm:ss");
				String nowString = format.format(now);
				log.debug("now: " + nowString);

				String sessionID = getSessionID();
				log.debug("sessionID: " + sessionID);

				String insertStatement = "INSERT INTO session ( SessionID , Lastaccess , Timeout) VALUES ( '"
						+ sessionID + "' , '" + nowString + "' , '" + timeoutString + "' )";
				log.debug(" insertStatement: " + insertStatement);
				sessionDAO.doInsertIntoSession(insertStatement);

				text = Templates.ReadFile("Archive.html");

				text = text.replaceAll("\\{\\*SessionID\\*\\}", sessionID);

				log.debug("Text: " + text);

			} else {

				String selectUserID = "select * from user where UserID = '" + userID + "' ;";
				log.error(selectUserID);
				User user1 = (User) userDAO.doSelectElement(selectUserID);
				log.error("Aus der Datenbank:" + user1);
				if (user1 != null) {
					text = Templates.ReadFile("Error.html");
					text = text.replaceAll("\\{\\*ExceptionTitle\\*\\}", "Passwort ist falsch!");
					text = text.replaceAll("\\{\\*ExceptionMessage\\*\\}", "Bitte geben Sie ein gültiges Passwort ein!");

				} else {
					String selectPassword = "select * from user where Password=MD5('" + password + "');";
					log.error(selectPassword);
					User user2 = (User) userDAO.doSelectElement(selectPassword);
					log.error(user2);

					if (user2!=null) {
						text = Templates.ReadFile("Error.html");
						text = text.replaceAll("\\{\\*ExceptionTitle\\*\\}", "Benutzername ist falsch!");
						text = text.replaceAll("\\{\\*ExceptionMessage\\*\\}", "Bitte geben Sie einen gültigen Benutzername ein!");

					} else {
						 text = Templates.ReadFile("Error.html");
						 text = text.replaceAll("\\{\\*ExceptionTitle\\*\\}",
						 "Benutzername oder Passwort falsch.");
						 text = text.replaceAll("\\{\\*ExceptionMessage\\*\\}", "Bitte geben Sie einen gültigen Benutzername und/oder ein gültiges Passwort ein!");
					}
				}

			}

		} catch (Exception e) {
			try {
				text = Templates.ReadFile("Error.html");
				text = text.replaceAll("\\{\\*ExceptionTitle\\*\\}", "Fehler ist aufgetreten");
				text = text.replaceAll("\\{\\*ExceptionMessage\\*\\}", e.toString());
				log.error(e);
			} catch (IOException e1) {
				text = "Fehler ist aufgetreten \n" + e;
				e1.printStackTrace();
			}
		}

		sendPageToClient(text);
	}

	public synchronized String getSessionID() {
		Date now = new Date();
		long sessionID = now.getTime();

		// log.debug("now: " + now);
		// log.debug("sessionID: " + sessionID);

		return String.valueOf(sessionID);
	}

	private void sendPageToClient(String text) {
		try {
			response.setContentType("text/html");
			PrintWriter writer = response.getWriter();
			writer.write(text);
			writer.close();
		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
		}
	}

}
