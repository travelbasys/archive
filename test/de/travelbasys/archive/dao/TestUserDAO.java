package de.travelbasys.archive.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.travelbasys.archive.model.User;

public class TestUserDAO {

	public static void main(String[] args) throws SQLException {

		UserDAO userdao = new UserDAO();
		User user = (User) userdao.doSelectElement("select * from user where UserID = 'mru' and Password=MD5('mru');");

		System.out.println(user.getUserID());
	}

}
