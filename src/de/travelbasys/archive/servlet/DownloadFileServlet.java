package de.travelbasys.archive.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.travelbasys.archive.dao.SessionDAO;
import de.travelbasys.archive.dao.VeranstalterrechnungDAO;
import de.travelbasys.archive.model.Veranstalterrechnung;
import de.travelbasys.archive.utils.ArchiveFileConverter;
import de.travelbasys.archive.utils.ArchiveInitialContext;
import de.travelbasys.archive.utils.Templates;

@WebServlet({ "/DownloadFile", "/archive/DownloadFile" })
public class DownloadFileServlet extends HttpServlet {

	/**
	 * 
	 * @author Nils, Paul and Amjad
	 * 
	 */

	private static final long serialVersionUID = 1569485124701548457L;

	private static Log log = LogFactory.getFactory().getInstance(
			DownloadFileServlet.class.getName());
	
	private HttpServletRequest request = null;

	private HttpServletResponse response = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		request = req;
		response = resp;
		log.error("Datei nicht vorhanden");

	}

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
				e1.printStackTrace();
			}
			sendErrorMessage(text);
		}

		// Update Lastaccess in Session Table
		try{
		sessionDao.doUpdateSession(request.getParameter("SessionID"));
		}catch(SQLException e){
			String text;
			try {
				text = Templates.ReadFile("Error.html");
				text = text.replaceAll("\\{\\*ExceptionTitle\\*\\}",
						"Fehler ist aufgetreten");
				text = text.replaceAll("\\{\\*ExceptionMessage\\*\\}",
						e.toString());
				
			} catch (IOException e1) {
				text = "Fehler ist aufgetreten \n" + e;
				e1.printStackTrace();
			}
			
			sendErrorMessage(text);
		}
		doRequest("Post Protocol for ShowFileServlet..");
	}

	private void sendErrorMessage(String notValidMessage) throws IOException {
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write(notValidMessage);
		writer.close();
	}

	private void doRequest(String string) throws IOException {

		try {
			log.debug(string);
			String text = Templates.ReadFile("ShowFile.html");

			log.debug("text " + text);

			String filenameValue = request.getParameter("filename");
			String orginalFilePath = ArchiveInitialContext.pdfOrginalFolder
					+ filenameValue;

			log.debug("File: " + orginalFilePath);

			File file = new File(orginalFilePath);

			response.setHeader("Content-Disposition", "attachment;filename="
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
			response.setContentType("text/html");
			PrintWriter writer = response.getWriter();
			writer.write("Fehler ist aufgetreten: \n" + e);
			writer.close();

			log.error("IOException: " + e);
		}
	}
}
