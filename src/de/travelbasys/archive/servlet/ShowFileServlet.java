package de.travelbasys.archive.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.travelbasys.archive.dao.SessionDAO;
import de.travelbasys.archive.utils.ArchiveFileConverter;
import de.travelbasys.archive.utils.ArchiveInitialContext;
import de.travelbasys.archive.utils.Templates;

/**
 * Read file and send it to client
 * 
 * @author Nils, Paul and Amjad
 * @version 1.0
 * @since 21.12.2016
 * @see ArchiveFileConverter, SessionDAO
 * 
 */

@WebServlet({ "/ShowFile", "/archive/ShowFile" })
public class ShowFileServlet extends HttpServlet {
	
	private static Log log = LogFactory.getFactory().getInstance(
			DownloadFileServlet.class.getName());

	// SerialVersionUID is a unique identifier for each class,
	// JVM uses it to compare the versions of the class ensuring that the same
	// class was used during Serialization is loaded during Deserialization.
	private static final long serialVersionUID = 1569485124701548457L;

	// HttpServletRequest used to provide request information for HTTP servlets.
	private HttpServletRequest request = null;

	// HttpServletResponse used to provide HTTP-specific functionality in
	// sending a response.
	// For example, it has methods to access HTTP headers and cookies.
	private HttpServletResponse response = null;

	/**
	 * This method will be called using get protocol
	 * 
	 * @param {@link HttpServletRequest}
	 * @param {@link HttpServletResponse}
	 * @return no value
	 * @throws ServletException
	 *             , IOException
	 *
	 */
	/*
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		request = req;
		response = resp;

		sendErrorMessage("Datei nicht vorhanden");
	}

	/**
	 * This method will be called using post protocol
	 * 
	 * @param {@link HttpServletRequest}
	 * @param {@link HttpServletResponse}
	 * @return no return values
	 * @throws ServletException
	 *             , IOException
	 *
	 */

	/*
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		request = req;
		response = resp;
		SessionDAO sessionDao = null;
		try {
		sessionDao = new SessionDAO();
		
			String notValidMessage = sessionDao.isSessionValid(request);

			if (!notValidMessage.isEmpty()) {
				sendErrorMessage(notValidMessage);
			}
			
			// Update Lastaccess in Session Table
			sessionDao.doUpdateSession(request.getParameter("SessionID"));
			
		} catch (SQLException e) {
			String text = "";
			try {

				text = Templates.ReadFile("Error.html");
				text = text.replaceAll("\\{\\*ExceptionTitle\\*\\}",
						"Fehler ist aufgetreten");
				text = text.replaceAll("\\{\\*ExceptionMessage\\*\\}",
						e.toString());
			} catch (IOException e1) {
				text = "Fehler ist aufgetreten \n" + e;
				log.error(e1);
			}
			sendErrorMessage(text);
		}


		doRequest("Post Protocol for ShowFileServlet..");
	}

	/**
	 * Send error message to client
	 * 
	 * @param notValidMessage
	 *            : message which should be sent
	 * @throws IOException
	 *             : Input Output Exception
	 */
	private void sendErrorMessage(String notValidMessage) throws IOException {
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write(notValidMessage);
		writer.close();
	}

	/**
	 * Read file, convet it to pdf and send it to client This will be called in
	 * {@link ShowFileServlet#doPost(HttpServletRequest, HttpServletResponse)}
	 * 
	 * @param string
	 */
	private void doRequest(String string) {

		log.debug(string);
		try {

			String filenameValue = request.getParameter("filename");
			String orginalPath = ArchiveInitialContext.pdfOrginalFolder
					+ filenameValue;

			String extension = filenameValue.split("\\.")[1];

			String fileName = "";

			if ("pdf".equalsIgnoreCase(extension)) {
				fileName = orginalPath;
			} else if ("tif".equalsIgnoreCase(extension)) {
				String tifFile = orginalPath;
				log.debug("TIF: " + orginalPath);
				String pdfFile = orginalPath.replaceAll(".tif", ".pdf");
				log.debug("PDF: " + pdfFile);

				ArchiveFileConverter.convertTifToPDF(tifFile, pdfFile);

				fileName = pdfFile;
			}

			File file = new File(fileName);

			response.setHeader("Content-Disposition", "inline;filename="
					+ filenameValue);
			response.setContentType("application/pdf");
			response.setContentLength((int) file.length());

			FileInputStream fileInputStream = new FileInputStream(file);
			ServletOutputStream outputStream = response.getOutputStream();

			int bytes;
			while ((bytes = fileInputStream.read()) != -1) {
				outputStream.write(bytes);
			}

			fileInputStream.close();
			outputStream.close();

		} catch (Exception e) {
			log.error("IOException: " + e);
		}
	}
}
