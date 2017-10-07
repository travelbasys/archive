package de.travelbasys.archive.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.travelbasys.archive.servlet.LoginServlet;


public class Templates {

	private static Log log = LogFactory.getFactory().getInstance(Templates.class.getName());
	
	public static String ReadFile(String fileName) throws IOException {

		String content = "";
	
			String templatePath = ArchiveInitialContext.templatePath;
			
			log.debug("templatePath: " + templatePath);
			FileReader fileReader = new FileReader(templatePath + fileName);
			BufferedReader bufferReader = new BufferedReader(fileReader);
			String line = "";
			while ((line = bufferReader.readLine()) != null) {
				content += line;
		}
		return content;
	}
}
