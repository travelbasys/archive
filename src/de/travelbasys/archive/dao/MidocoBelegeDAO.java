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

import de.travelbasys.archive.model.MidocoBelege;
import de.travelbasys.archive.servlet.LoginServlet;

public class MidocoBelegeDAO extends ArchiveDAO{

	public MidocoBelegeDAO() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	private static Log log = LogFactory.getFactory().getInstance(MidocoBelegeDAO.class.getName());
	
	private static Connection connection = null;
	private static Statement statement = null;

	@Override
	protected Object fillObject(ResultSet result)
			throws SQLException {
		
		log.debug("Belegnummer" + result.getString("Belegnummer"));
		
		String file_Name = result.getString("file_name");
		String dokumentart = result.getString("Dokumentart");
		String rBS_Firma = result.getString("RBS_Firma");
		String belegnummer= result.getString("Belegnummer");
		String belegdatum = result.getString("Belegdatum");
		String Belegart = result.getString("Belegart");
		String Bezugsnummer = result.getString("Bezugsnummer");
		String Vorgangsnummer = result.getString("Vorgangsnummer");
		String Kundennummer = result.getString("Kundennummer");
		String Name1 = result.getString("Name1");
		String Name2 = result.getString("Name2");
		String Name3 = result.getString("Name3");
		String Name4 = result.getString("Name4");
		String Strasse = result.getString("Strasse");
		String PLZ = result.getString("PLZ");
		String Ort = result.getString("Ort");
		String Reisender = result.getString("Reisender");
		String Reisedatum = result.getString("Reisedatum");
		String Expedientennummer = result.getString("Expedientennummer");
		String Expedient = result.getString("Expedient");
		String Kostenstelle = result.getString("Kostenstelle");
		String Auftragsnummer = result.getString("Auftragsnummer");
		String Auftragsdatum = result.getString("Auftragsdatum");
		String Besteller = result.getString("Besteller");
		String Netto1 = result.getString("Netto1");
		String Netto2 = result.getString("Netto2");
		String StKz2 = result.getString("StKz2");
		String MwSt2 = result.getString("MwSt2");
		String Netto3 = result.getString("Netto3");
		String StKz3 = result.getString("StKz3");
		String MwSt3 = result.getString("MwSt3");
		String Brutto = result.getString("Brutto");
		String Waehrung = result.getString("Waehrung");

		MidocoBelege fileConfiguration = new MidocoBelege();
		fileConfiguration.setFile_name(file_Name);
		fileConfiguration.setDokumentart(dokumentart);
		fileConfiguration.setrBS_Firma(rBS_Firma);
		fileConfiguration.setBelegnummer(belegnummer);
		fileConfiguration.setBelegdatum(belegdatum);
		fileConfiguration.setBelegart(Belegart);
		fileConfiguration.setBezugsnummer(Bezugsnummer);
		fileConfiguration.setVorgangsnummer(Vorgangsnummer);
		fileConfiguration.setKundennummer(Kundennummer);
		fileConfiguration.setName1(Name1);
		fileConfiguration.setName2(Name2);
		fileConfiguration.setName3(Name3);
		fileConfiguration.setName4(Name4);
		fileConfiguration.setStrasse(Strasse);
		fileConfiguration.setPLZ(PLZ);
		fileConfiguration.setOrt(Ort);
		fileConfiguration.setReisender(Reisender);
		fileConfiguration.setExpedientennummer(Expedientennummer);
		fileConfiguration.setExpedient(Expedient);
		fileConfiguration.setKostenstelle(Kostenstelle);
		fileConfiguration.setAuftragsnummer(Auftragsnummer);
		fileConfiguration.setAuftragsdatum(Auftragsdatum);
		fileConfiguration.setBesteller(Besteller);
		fileConfiguration.setNetto1(Netto1);
		fileConfiguration.setNetto2(Netto2);
		fileConfiguration.setStKz2(StKz2);
		fileConfiguration.setMwSt2(MwSt2);
		fileConfiguration.setNetto3(Netto3);
		fileConfiguration.setStKz3(StKz3);
		fileConfiguration.setMwSt3(MwSt3);
		fileConfiguration.setBrutto(Brutto);
		fileConfiguration.setWaehrung(Waehrung);
		
		log.debug("fileConfiguration.Belegnummer" + fileConfiguration.getBelegnummer());
		
		return fileConfiguration;
	}
}
