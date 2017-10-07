package de.travelbasys.archive.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.travelbasys.archive.dao.ArchiveDAO;
import de.travelbasys.archive.dao.MidocoBelegeDAO;
import de.travelbasys.archive.dao.SessionDAO;
import de.travelbasys.archive.mongodb.SimpleMongoDB;
import de.travelbasys.archive.utils.Templates;

public abstract class ArchiveServlet extends HttpServlet {

	/**
	 * 
	 * @author Nils, Paul and Amjad
	 * 
	 */

	private static final long serialVersionUID = 1569485124701548457L;

	private static Log log = LogFactory.getFactory().getInstance(ArchiveServlet.class.getName());

	protected HttpServletRequest request = null;

	private HttpServletResponse response = null;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		log.debug("*** Begin doGet: " + req.getRequestURL());

		request = req;
		response = resp;

		doRequest("End doGet");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		log.debug("*** Begin doPost: " + req.getRequestURL());

		request = req;
		response = resp;

		doRequest("End doGet");
	}

	private void doRequest(String string) {
		String text = "";
		try {
			boolean isValidRequest = validateRequestParameter();
			if (isValidRequest != true) {
				return;
			}

			// Update Lastaccess in Session Table
			SessionDAO sessionDao = new SessionDAO();
			sessionDao.doUpdateSession(request.getParameter("SessionID"));

			String firma = request.getParameter("Firma");
			String freeText = request.getParameter("FreeText");
			List fileConfigurationList = new ArrayList<>();
			if (freeText == null || freeText.isEmpty()) {
				log.debug("Using only MYSQL");
				// Select elements from database using DAO class
				fileConfigurationList = doSelectSQLList(); // MY SQL WITH
															// HTML-PARAMETER
			} else {
				log.debug("Using MongoDB and MYSQL");
				String belegnummer = SimpleMongoDB.GetFileNameUsingTextAndCompany(firma, freeText);
				log.debug("belegnummer after MongoDB: " + belegnummer);
				if (belegnummer != null && !belegnummer.isEmpty()) {
					fileConfigurationList = doSelectSQLListUsingBelegnummer(belegnummer);
				}

			}

			// MONGODB + MYSQL WITH BELEGNUMMER/BARCODE und Firma

			// No element found in database
			if (fileConfigurationList.isEmpty()) {
				text = "<h3> Error 404: </h3> " + " Es wurden keine Dokumente gefunden!";
			} else { // at least one element found

				// First line
				text = " <table border=\"3\"> ";

				// Add header
				text += getHeaderLine();

				// List of links
				for (int i = 0; i < fileConfigurationList.size(); i++) {
					Object fileConfiguration = fileConfigurationList.get(i);
					text += getBodyLine(fileConfiguration, i);
				}
				// Last line
				text += " </table>";
			}

		} catch (Exception e) {
			try {
				text = Templates.ReadFile("Error.html");
				text = text.replaceAll("\\{\\*ExceptionTitle\\*\\}", "Fehler ist aufgetreten");
				text = text.replaceAll("\\{\\*ExceptionMessage\\*\\}", e.toString());
				log.error(e);
			} catch (IOException e1) {
				text = "Fehler ist aufgetreten \n" + e;
				log.error(e1);
			}
		}

		try {
			doResponse(text);
		} catch (IOException e1) {
			text = "Fehler ist aufgetreten \n" + e1;
			log.error(e1);
		}
	}

	protected String getStringSQL(String parameter) {
		String parameterValue = request.getParameter(parameter); // Parameter
		String sql = "";
		if ((parameterValue != null) && (!parameterValue.isEmpty())) {
			sql = " and " + parameter + " like \"%" + parameterValue + "\" ";
		}
		return sql;
	}

	protected String getOperationSQL(String parameter, String paramterOperation) {
		String parameterValue = request.getParameter(parameter); // ParameterValue
		String operation = request.getParameter(paramterOperation); // barcode_operation(=,<,<=,>,>=)
		String sql = "";
		if ((parameterValue != null) && (!parameterValue.isEmpty())) {
			sql = " and " + parameter + " " + operation + " " + parameterValue;
		}
		return sql;
	}

	protected String getOperationDateSQL(String parameter, String fieldDatabase , String paramterOperation) {
		String parameterValue = request.getParameter(parameter); // Parameter
		String operation = request.getParameter(paramterOperation); // barcode_operation(=,<,<=,>,>=)
		String sql = "";
		if ((parameterValue != null) && (!parameterValue.isEmpty())) {
			sql = " and " + fieldDatabase + " " + operation + " '" + parameterValue + " '";
		}
		return sql;
	}

	protected void doResponse(String text) throws IOException {
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write(text);
		writer.close();
	}

	protected abstract String getHeaderLine();

	protected abstract String getBodyLine(Object fileConfiguration, int counter);

	protected abstract List doSelectSQLList() throws SQLException;

	protected abstract List doSelectSQLListUsingBelegnummer(String belegnummer) throws SQLException;

	protected abstract boolean validateRequestParameter() throws IOException, SQLException;
}
