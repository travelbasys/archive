package de.travelbasys.archive.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.travelbasys.archive.model.Veranstalterrechnung;

public class TestVeranstalterrechnungDAO{

	private static Connection connection = null;
	private static Statement statement = null;

	public static void main(String[] args) throws SQLException {

		VeranstalterrechnungDAO mySQLDAO = new VeranstalterrechnungDAO();
		String barcode1 = "441";
		String firma1 = "6055";
		String sql1 = "select * from Veranstalterrechnung where Barcodenummer = "
				+ barcode1 + " and RBS_Firma = " + firma1;

		List<Veranstalterrechnung> fileConfiguration = mySQLDAO
				.doSelectList(sql1);

		String result = "http://localhost:8080/PDFDaten/"
				+ fileConfiguration.get(0).getrBS_Firma() + "/"
				+ fileConfiguration.get(0).getFile_name() + ".pdf";

		System.out.println(result);

	}
}