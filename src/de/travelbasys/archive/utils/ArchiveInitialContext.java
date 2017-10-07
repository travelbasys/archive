package de.travelbasys.archive.utils;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.travelbasys.archive.servlet.DownloadFileServlet;

public class ArchiveInitialContext {
	public static String templatePath;
	public static String sqlConnection;
	public static String pdfOrginalFolder;
	public static String pdfShowFolder;
	
	private static Log log = LogFactory.getFactory().getInstance(
			DownloadFileServlet.class.getName());

	static {
		log.debug("Start reading properties file");

		Properties props = new Properties();
		try {
			props.load(Templates.class.getClassLoader().getResourceAsStream("runtime.properties"));
		} catch (IOException e1) {
			log.error(e1);
		}

		String env = props.getProperty("environment");

		templatePath = props.getProperty(env + ".de.travelbasys.templatePath");
		sqlConnection = props.getProperty(env + ".de.travelbasys.sqlConnection");
		pdfOrginalFolder = props.getProperty(env + ".de.travelbasys.pdfOrginalFolder");
		pdfShowFolder = props.getProperty(env + ".de.travelbasys.pdfShowFolder");

		log.debug("End reading properties file");
	}

}
