package de.travelbasys.archive.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.travelbasys.archive.servlet.ArchiveServlet;
import de.travelbasys.archive.utils.ArchiveInitialContext;

public abstract class ArchiveDAO {
	
	private static Log log = LogFactory.getFactory().getInstance(
			ArchiveDAO.class.getName());

	protected static Connection connection = null;
	protected static Statement statement = null;
	
	public ArchiveDAO() throws SQLException {

		try {
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager
					.getConnection(ArchiveInitialContext.sqlConnection);

			statement = connection.createStatement();

		} catch (ClassNotFoundException e) {
			log.error(e);
		} 
	
	}
	public List doSelectList(String sql) throws SQLException {

		List objectList = new ArrayList<>();

			ResultSet result = statement.executeQuery(sql);

			while (result != null && result.next()) {

				Object fileConfiguration = fillObject(result);

				objectList.add(fileConfiguration);

			}
		
		return objectList;
	}

	public Object doSelectElement(String sql) throws SQLException {

		Object fileConfiguration = null;
			ResultSet result = statement.executeQuery(sql);

			while (result != null && result.next()) {

				fileConfiguration = fillObject(result);

				break;

			}

		return fileConfiguration;
	}
	
	
	protected abstract Object fillObject (ResultSet result) throws SQLException;
}
