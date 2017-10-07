package de.travelbasys.archive.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.travelbasys.archive.model.Session;
import de.travelbasys.archive.servlet.LoginServlet;

public class SessionDAO extends ArchiveDAO{
	
	private static Log log = LogFactory.getFactory().getInstance(
			SessionDAO.class.getName());

	public SessionDAO() throws SQLException {
		super();
	}

	public int doInsertIntoSession(String sql) throws SQLException {
		int updateResult = 0;
			updateResult = statement.executeUpdate(sql);

			log.debug("updateResult: " + updateResult);
		return updateResult;
	}

	public int doUpdateSession(String sessionID) throws SQLException {

		// Update Session Table

		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yy.MM.dd HH:mm:ss");
		String nowString = format.format(now);
		log.debug("now: " + nowString);

		String sqlStatementUpdate = "UPDATE Session Set Lastaccess = '" + nowString + "' where SessionID = '"
				+ sessionID + "';";

		
		int updateResult = 0;
			updateResult = statement.executeUpdate(sqlStatementUpdate);

			log.debug("doUpdateSession: " + updateResult);
		
		return updateResult;
	}
	
	protected Session fillObject(ResultSet result) throws SQLException {
		String sessionID = result.getString("SessionID");
		String lastaccess = result.getString("Lastaccess");
		String timeout = result.getString("Timeout");

		Session session = new Session();
		session.setSessionID(sessionID);
		session.setLastaccess(lastaccess);
		session.setTimeout(timeout);

		return session;
	}

	public String isSessionValid(HttpServletRequest request) throws IOException, SQLException {
		String notValidMessage = "";

		// Validate Session ID
		String sessionID = request.getParameter("SessionID");
		log.debug("SessionID:" + sessionID);

		SessionDAO sessionDAO = new SessionDAO();
		Session session = (Session) sessionDAO
				.doSelectElement("select * from session where SessionID = '" + sessionID + "';");

		if (session == null) {
			notValidMessage = "Ungültige SessionID. Bitte melden Sie sich neu.";
		} else { // is Timeout?
			try {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				String lastAccess = session.getLastaccess(); // Datum
				Date lastAccessDate = format.parse(lastAccess);

				log.debug("lastAccessDate: " + lastAccessDate);

				String timeout = session.getTimeout(); // 12Min
				int timeoutInt = Integer.parseInt(timeout);
				log.debug("timeoutInt: " + timeoutInt);

				Date now = new Date();
				log.debug("now: " + now);

				Date lastAccessDateAfterTimeout = lastAccessDate;
				log.debug("lastAccessDateAfterTimeout: " + lastAccessDateAfterTimeout);

				lastAccessDateAfterTimeout.setMinutes(lastAccessDate.getMinutes() + timeoutInt);
				log.debug("lastAccessDateAfterTimeout: " + lastAccessDateAfterTimeout);

				log.debug("Is after : " + now.after(lastAccessDateAfterTimeout));
				if (now.after(lastAccessDateAfterTimeout)) {
					notValidMessage = "Session Timeout. Bitte melden Sie sich neu.";
				}

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				log.error(e);
			}

		}
		return notValidMessage;
	}
}
