package de.travelbasys.archive.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.travelbasys.archive.model.User;

public class UserDAO extends ArchiveDAO {

	public UserDAO() throws SQLException {
		super();
	}

	protected User fillObject(ResultSet result) throws SQLException {
		String userID = result.getString("UserID");

		User user = new User();
		user.setUserID(userID);

		return user;
	}
}
