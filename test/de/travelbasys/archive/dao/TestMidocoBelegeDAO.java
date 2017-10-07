package de.travelbasys.archive.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.travelbasys.archive.model.MidocoBelege;

public class TestMidocoBelegeDAO{

	private static Connection connection = null;
	private static Statement statement = null;

	public static void main(String[] args) throws SQLException {

		MidocoBelegeDAO mySQLDAO = new MidocoBelegeDAO();
		String belegnummer = "441";
		String firma1 = "6055";
		String sql1 = "select * from  MidocoBelege where Belegnummer = "
				+ belegnummer + " and RBS_Firma = " + firma1;

		List<MidocoBelege> fileConfiguration = mySQLDAO
				.doSelectList(sql1);

		String result = "http://localhost:8080/PDFDaten/"
				+ fileConfiguration.get(0).getrBS_Firma() + "/"
				+ fileConfiguration.get(0).getFile_name() + ".pdf";

		System.out.println(result);

	}
}