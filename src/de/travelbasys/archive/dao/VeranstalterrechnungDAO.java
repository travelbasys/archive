package de.travelbasys.archive.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.travelbasys.archive.model.Veranstalterrechnung;

public class VeranstalterrechnungDAO extends ArchiveDAO{

	public VeranstalterrechnungDAO() throws SQLException {
		super();
	}

	private static Connection connection = null;
	private static Statement statement = null;

	protected Object fillObject(ResultSet result)
			throws SQLException {
		String file_Name = result.getString("file_name");
		String dokumentart = result.getString("Dokumentart");
		
		String veranstaltercode = result.getString("Veranstaltercode");
		String veranstaltername = result.getString("Veranstaltername");
		String buero = result.getString("Buero");
		String reisender = result.getString("Reisender");
		String reisedatum = result.getString("Reisedatum");
		String vorgangsnummer = result.getString("Vorgangsnummer");
		String reisepreis = result.getString("Reisepreis");
		String r_G = result.getString("R_G");
		String waehrung = result.getString("Waehrung");
		String agenturnummer = result.getString("Agenturnummer");
		String prov_betrag1 = result.getString("Prov_betrag1");
		String prov_betrag2 = result.getString("Prov_betrag2");
		String prov_betrag3 = result.getString("Prov_betrag3");
		String prov_betrag4 = result.getString("Prov_betrag4");
		String rechnungsbetrag = result.getString("Rechnungsbetrag");
		String inkassoart = result.getString("Inkassoart");
		String abbucherkennz = result.getString("file_name");
		String rechnungsdatum = result.getString("Abbucherkennz");
		String rechnungsnummer = result.getString("Rechnungsnummer");
		String barcodenummer = result.getString("Barcodenummer");
		String loeschkennzeichen = result.getString("Loeschkennzeichen");
		String abgleichdatum = result.getString("Abgleichdatum");
		String rBS_Firma = result.getString("RBS_Firma");

		Veranstalterrechnung fileConfiguration = new Veranstalterrechnung();
		fileConfiguration.setFile_name(file_Name);
		fileConfiguration.setDokumentart(dokumentart);
		fileConfiguration.setBarcodenummer(barcodenummer);
		fileConfiguration.setVeranstaltercode(veranstaltercode);

		fileConfiguration.setVeranstaltername(veranstaltername);
		fileConfiguration.setBuero(buero);
		fileConfiguration.setReisender(reisender);
		fileConfiguration.setReisedatum(reisedatum);
		fileConfiguration.setVorgangsnummer(vorgangsnummer);
		fileConfiguration.setReisepreis(reisepreis);
		fileConfiguration.setR_G(r_G);
		fileConfiguration.setWaehrung(waehrung);
		fileConfiguration.setAgenturnummer(agenturnummer);
		fileConfiguration.setProv_betrag1(prov_betrag1);
		fileConfiguration.setProv_betrag2(prov_betrag2);
		fileConfiguration.setProv_betrag3(prov_betrag3);
		fileConfiguration.setProv_betrag4(prov_betrag4);
		fileConfiguration.setRechnungsbetrag(rechnungsbetrag);
		fileConfiguration.setInkassoart(inkassoart);
		fileConfiguration.setAbbucherkennz(abbucherkennz);
		fileConfiguration.setRechnungsdatum(rechnungsdatum);
		fileConfiguration.setRechnungsnummer(rechnungsnummer);
		fileConfiguration.setLoeschkennzeichen(loeschkennzeichen);
		fileConfiguration.setAbgleichdatum(abgleichdatum);

		fileConfiguration.setrBS_Firma(rBS_Firma);

		return fileConfiguration;
	}
}
