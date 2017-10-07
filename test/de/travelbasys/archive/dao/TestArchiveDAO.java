package de.travelbasys.archive.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.travelbasys.archive.utils.ArchiveInitialContext;

public class TestArchiveDAO {

	protected static Connection connection = null;
	protected static Statement statement = null;
	
	public TestArchiveDAO() {

		try {
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager
					.getConnection(ArchiveInitialContext.sqlConnection);

			statement = connection.createStatement();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}