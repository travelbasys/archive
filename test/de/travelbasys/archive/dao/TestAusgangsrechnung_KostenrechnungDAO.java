package de.travelbasys.archive.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.travelbasys.archive.model.Ausgangsrechnung_Kostenrechnung;

public class TestAusgangsrechnung_KostenrechnungDAO{

	private static Connection connection = null;
	private static Statement statement = null;

	public static void main(String[] args) throws SQLException {

		Ausgangsrechnung_KostenrechnungDAO mySQLDAO = new Ausgangsrechnung_KostenrechnungDAO();
		String barcode1 = "441";
		String firma1 = "6055";
		String sql1 = "select * from Ausgangsrechnung_Kostenrechnung where Barcodenummer = "
				+ barcode1 + " and RBS_Firma = " + firma1;

		List<Ausgangsrechnung_Kostenrechnung> fileConfiguration = mySQLDAO
				.doSelectList(sql1);

		String result = "http://localhost:8080/PDFDaten/"
				+ fileConfiguration.get(0).getRBS_Firma() + "/"
				+ fileConfiguration.get(0).getFile_name() + ".pdf";

		System.out.println(result);

	}
}