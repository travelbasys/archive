package de.travelbasys.archive.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.travelbasys.archive.model.Ausgangsrechnung_Kostenrechnung;

public class Ausgangsrechnung_KostenrechnungDAO extends ArchiveDAO{

	public Ausgangsrechnung_KostenrechnungDAO() throws
			SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	private static Connection connection = null;
	private static Statement statement = null;

	protected Object fillObject(ResultSet result)
			throws SQLException {
		String file_Name = result.getString("file_name");
		String dokumentart = result.getString("Dokumentart");
		//TODO
		
		String Barcode = result.getString("Barcode");
		String RBS_Firma = result.getString("RBS_Firma");
		String Bukrs = result.getString("Bukrs");
		String BelegNr = result.getString("BelegNr");
		String GJahr = result.getString("GJahr");
		String Belegart = result.getString("Belegart");
		String Belegdatum = result.getString("Belegdatum");
		String Buchungsdatum = result.getString("Buchungsdatum");
		String Belegkopftext = result.getString("Belegkopftext");
		String Referenz_BelegNr = result.getString("Referenz_BelegNr");
		String Dauer_BelegNr = result.getString("Dauer_BelegNr");
		String Waehrung = result.getString("Waehrung");
		String Mappe = result.getString("Mappe");
		String Erfasst = result.getString("Erfasst");
		String Erfasser = result.getString("Erfasser");
		String DKonto = result.getString("DKonto");
		String DOrt = result.getString("DOrt");
		String DBetrag = result.getString("DBetrag");
		String DZuordnung = result.getString("DZuordnung");
		String DSGText = result.getString("DSGText");
		String DXRef1 = result.getString("DXRef1");
		String DXRef2 = result.getString("DXRef2");
		String DXRef3 = result.getString("DXRef3");
		String Mandant = result.getString("Mandant");
		String System_ID = result.getString("System_ID");
		String User_Name = result.getString("User_Name");
		String TagesDatum = result.getString("TagesDatum");
		String TagesZeit = result.getString("TagesZeit");
		
		Ausgangsrechnung_Kostenrechnung ausgangsrechnungKostenrechnung = new Ausgangsrechnung_Kostenrechnung();
		ausgangsrechnungKostenrechnung.setFile_name(file_Name);
		ausgangsrechnungKostenrechnung.setDokumentart(dokumentart);
		//TODO
		
		ausgangsrechnungKostenrechnung.setBarcode(Barcode);
		ausgangsrechnungKostenrechnung.setRBS_Firma(RBS_Firma);
		ausgangsrechnungKostenrechnung.setBukrs(Bukrs);
		ausgangsrechnungKostenrechnung.setBelegNr(BelegNr);
		ausgangsrechnungKostenrechnung.setGJahr(GJahr);
		ausgangsrechnungKostenrechnung.setBelegart(Belegart);
		ausgangsrechnungKostenrechnung.setBelegdatum(Belegdatum);
		ausgangsrechnungKostenrechnung.setBuchungsdatum(Buchungsdatum);
		ausgangsrechnungKostenrechnung.setBelegkopftext(Belegkopftext);
		ausgangsrechnungKostenrechnung.setReferenz_BelegNr(Referenz_BelegNr);
		ausgangsrechnungKostenrechnung.setDauer_BelegNr(Dauer_BelegNr);
		ausgangsrechnungKostenrechnung.setWaehrung(Waehrung);
		ausgangsrechnungKostenrechnung.setMappe(Mappe);
		ausgangsrechnungKostenrechnung.setErfasst(Erfasst);
		ausgangsrechnungKostenrechnung.setErfasser(Erfasser);
		ausgangsrechnungKostenrechnung.setDKonto(DKonto);
		ausgangsrechnungKostenrechnung.setDOrt(DOrt);
		ausgangsrechnungKostenrechnung.setDBetrag(DBetrag);
		ausgangsrechnungKostenrechnung.setDZuordnung(DZuordnung);
		ausgangsrechnungKostenrechnung.setDSGText(DSGText);
		ausgangsrechnungKostenrechnung.setDXRef1(DXRef1);
		ausgangsrechnungKostenrechnung.setDXRef2(DXRef2);
		ausgangsrechnungKostenrechnung.setDXRef3(DXRef3);
		ausgangsrechnungKostenrechnung.setMandant(Mandant);
		ausgangsrechnungKostenrechnung.setSystem_ID(System_ID);
		ausgangsrechnungKostenrechnung.setUser_Name(User_Name);
		ausgangsrechnungKostenrechnung.setTagesDatum(TagesDatum);
		ausgangsrechnungKostenrechnung.setTagesZeit(TagesZeit);
		
		return ausgangsrechnungKostenrechnung;
	}
}
