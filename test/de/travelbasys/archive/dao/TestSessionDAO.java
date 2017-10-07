package de.travelbasys.archive.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import de.travelbasys.archive.model.Session;

public class TestSessionDAO{

	public static void main(String[] args) throws SQLException {

		SessionDAO sessionDAO = new SessionDAO();
		int inserResult = sessionDAO.doInsertIntoSession("INSERT INTO session ( SessionID , Lastaccess , Timeout) VALUES ( '123456789' , '16.10.14 00:00:00' , '15' )");
		System.out.println("insertResult: " + inserResult);
		Session session = (Session) sessionDAO.doSelectElement("select * from session where SessionID = '123456789';");

		System.out.println(session.getSessionID());
	}

}