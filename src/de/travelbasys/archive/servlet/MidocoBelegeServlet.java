package de.travelbasys.archive.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.travelbasys.archive.dao.ArchiveDAO;
import de.travelbasys.archive.dao.MidocoBelegeDAO;
import de.travelbasys.archive.dao.SessionDAO;
import de.travelbasys.archive.model.MidocoBelege;
import de.travelbasys.archive.utils.Templates;

@WebServlet({ "/MidocoBelegeArchive" })
public class MidocoBelegeServlet extends ArchiveServlet {

	/**
	 * 
	 * @author Nils, Paul and Amjad
	 * 
	 */

	private static final long serialVersionUID = 1569485124701548457L;
	
	private static Log log = LogFactory.getFactory().getInstance(
			MidocoBelegeServlet.class.getName());

	private static final String DOKUMENT_ART = "MB";

	protected String getHeaderLine() {
		String header = " <tr> " + " <th> Datei anzeigen </th>"
				+ " <th> Datei runterladen </th>" + "<th> rbs_Firma </th> "
				+ "<th> Belegnummer</th> " + "<th> BelegDatum </th> "
				+ "<th>  Belegart </th> " + "<th> Bezugsnummer </th> "
				+ "<th> Vorgangsnummer </th> " + "<th> Kundennummer </th> "
				+ "<th> Name1 </th> " + "<th> Name2 </th> "
				+ "<th> Name3 </th> " + "<th> Name4 </th> "
				+ "<th> Strasse </th> " + "<th> PLZ </th> " + "<th> Ort </th> "
				+ "<th> Reisender </th> " + "<th> Reisedatum </th> "
				+ "<th> Expedientennummer </th> " + "<th> Expedient </th> "
				+ "<th> Kostenstelle </th> " + "<th> Auftragsnummer </th> "
				+ "<th> Auftragsdatum </th> " + "<th> Besteller </th> "
				+ "<th> Netto1 </th> " + "<th> Netto2 </th> "
				+ "<th> StKz2 </th> " + "<th> MwSt2 </th> "
				+ "<th> Netto3 </th> " + "<th> StKz3 </th> "
				+ "<th> MwSt3 </th> " + "<th> Brutto </th> "
				+ "<th> Waehrung </th> " + "</tr>";

		return header;
	}

	protected String getBodyLine(Object fileConfigurationParameter, int counter) {

		MidocoBelege fileConfiguration = (MidocoBelege) fileConfigurationParameter;

		String action = "/archive/ShowFile?filename="
				+ fileConfiguration.getrBS_Firma() + "/"
				+ fileConfiguration.getFile_name() + "&SessionID="
				+ request.getParameter("SessionID");
		String fileName = "<form id='Show_" + counter + "' action='" + action
				+ "' method='post' target='_blank'> "
				+ "<a href='#' onclick=\"document.getElementById('Show_"
				+ counter + "').submit();\" >  "
				+ fileConfiguration.getFile_name() + " </a> </form>";

		String actionDownload = "/archive/DownloadFile?filename="
				+ fileConfiguration.getrBS_Firma() + "/"
				+ fileConfiguration.getFile_name() + "&SessionID="
				+ request.getParameter("SessionID");
		String fileDownload = "<form id='Download_" + counter + "' action='"
				+ actionDownload + "' method='post'> "
				+ "<a href='#' onclick=\"document.getElementById('Download_"
				+ counter + "').submit();\" >  "
				+ fileConfiguration.getFile_name() + " </a> </form>";

		String rbs_Firma = fileConfiguration.getrBS_Firma();
		String Belegnummer = fileConfiguration.getBelegnummer();
		String BelegDatum = fileConfiguration.getBelegdatum();

		String Belegart = fileConfiguration.getBelegart();
		String Bezugsnummer = fileConfiguration.getBezugsnummer();
		String Vorgangsnummer = fileConfiguration.getVorgangsnummer();
		String Kundennummer = fileConfiguration.getKundennummer();
		String Name1 = fileConfiguration.getName1();
		String Name2 = fileConfiguration.getName2();
		String Name3 = fileConfiguration.getName3();
		String Name4 = fileConfiguration.getName4();
		String Strasse = fileConfiguration.getStrasse();
		String PLZ = fileConfiguration.getPLZ();
		String Ort = fileConfiguration.getOrt();
		String Reisender = fileConfiguration.getReisender();
		String Reisedatum = fileConfiguration.getReisedatum();
		String Expedientennummer = fileConfiguration.getExpedientennummer();
		String Expedient = fileConfiguration.getExpedient();
		String Kostenstelle = fileConfiguration.getKostenstelle();
		String Auftragsnummer = fileConfiguration.getAuftragsnummer();
		String Auftragsdatum = fileConfiguration.getAuftragsdatum();
		String Besteller = fileConfiguration.getBesteller();
		String Netto1 = fileConfiguration.getNetto1();
		String Netto2 = fileConfiguration.getNetto2();
		String StKz2 = fileConfiguration.getStKz2();
		String MwSt2 = fileConfiguration.getMwSt2();
		String Netto3 = fileConfiguration.getNetto3();
		String StKz3 = fileConfiguration.getStKz3();
		String MwSt3 = fileConfiguration.getMwSt3();
		String Brutto = fileConfiguration.getBrutto();
		String Waehrung = fileConfiguration.getWaehrung();

		String line = " <tr> " + "<td> " + fileName + "</td> " + "<td> "
				+ fileDownload + "</td> " + "<td>" + rbs_Firma + "</td> "
				+ "<td>" + Belegnummer + "</td> " + "<td> " + BelegDatum
				+ "</td> " + "<td> " + Belegart + "</td> " + "<td>"
				+ Bezugsnummer + "</td> " + "<td>" + Vorgangsnummer + "</td> "
				+ "<td>" + Kundennummer + "</td> " + "<td>" + Name1 + "</td> "
				+ "<td>" + Name2 + "</td> " + "<td>" + Name3 + "</td> "
				+ "<td>" + Name4 + "</td> " + "<td>" + Strasse + "</td> "
				+ "<td>" + PLZ + "</td> " + "<td>" + Ort + "</td> " + "<td>"
				+ Reisender + "</td> " + "<td>" + Reisedatum + "</td> "
				+ "<td>" + Expedientennummer + "</td> " + "<td>" + Expedient
				+ "</td> " + "<td>" + Kostenstelle + "</td> " + "<td>"
				+ Auftragsnummer + "</td> " + "<td>" + Auftragsdatum + "</td> "
				+ "<td>" + Besteller + "</td> " + "<td>" + Netto1 + "</td> "
				+ "<td>" + Netto2 + "</td> " + "<td>" + StKz2 + "</td> "
				+ "<td>" + MwSt2 + "</td> " + "<td>" + Netto3 + "</td> "
				+ "<td>" + StKz3 + "</td> " + "<td>" + MwSt3 + "</td> "
				+ "<td>" + Brutto + "</td> " + "<td>" + Waehrung + "</td> "
				+ "</tr>";
		return line;
	}

	protected List<MidocoBelege> doSelectSQLList() throws SQLException {

		
		
		String firma = request.getParameter("Firma_id"); // Firma
		log.debug("firma_id:" +firma);
		
		String firma1 = request.getParameter("Firma_name"); // Firma
		log.debug("firma_name:" +firma1);
		
		String dokumentart = DOKUMENT_ART;
		String sqlStatement = "select * from MidocoBelege where RBS_Firma like \"%"
				+ firma
				+ "\""
				+ " and Dokumentart like \"%"
				+ dokumentart
				+ "\" ";
		log.debug("sqlstatement" +sqlStatement);
		
		// Build SQL Statement depending on the value of parameter
		sqlStatement += getOperationSQL("Belegnummer", "BelegnummerOperation");
		
		if(request.getParameter("BelegdatumSIM")!=null && !request.getParameter("BelegdatumSIM").isEmpty()) //Simple
		{
		sqlStatement += getOperationDateSQL("BelegdatumSIM", "Belegdatum", "BelegdatumOperation"); 
		}else{ //BelegdatumAUSEXT extended
			sqlStatement += getOperationDateSQL("BelegdatumMIDEXT", "Belegdatum", "BelegdatumOperation");//BelegdatumMIDEXT
		}
		
		
		sqlStatement += getOperationSQL("Agenturnummer",
				"AgenturnummerOperation");
		sqlStatement += getOperationSQL("Bezugsnummer", "BezugsnummerOperation");
		sqlStatement += getOperationSQL("Vorgangsnummer",
				"VorgangsnummerOperation");
		sqlStatement += getOperationSQL("Kundennummer", "KundennummerOperation");
		sqlStatement += getOperationSQL("Reisedatum", "ReisedatumOperation");
		sqlStatement += getOperationSQL("Expedientennummer",
				"ExpedientennummerOperation");
		sqlStatement += getOperationSQL("Kostenstelle", "KostenstelleOperation");
		sqlStatement += getOperationSQL("Auftragsnummer",
				"AuftragsnummerOperation");
		sqlStatement += getOperationSQL("Netto1", "Netto1Operation");
		sqlStatement += getOperationSQL("Netto2", "Netto2Operation");
		sqlStatement += getOperationSQL("StKz2", "StKz2Operation");
		sqlStatement += getOperationSQL("MwSt2", "MwSt2Operation");
		sqlStatement += getOperationSQL("Netto3", "Netto3Operation");
		sqlStatement += getOperationSQL("StKz3", "StKz3Operation");
		sqlStatement += getOperationSQL("MwSt3", "MwSt3Operation");
		sqlStatement += getOperationSQL("Brutto", "BruttoOperation");

		sqlStatement += getStringSQL("Mandant");
		sqlStatement += getStringSQL("Buchungskreis");
		sqlStatement += getStringSQL("Belegart");
		sqlStatement += getStringSQL("Name1");
		sqlStatement += getStringSQL("Name2");
		sqlStatement += getStringSQL("Name3");
		sqlStatement += getStringSQL("Name4");
		sqlStatement += getStringSQL("Strasse");
		sqlStatement += getStringSQL("PLZ");
		sqlStatement += getStringSQL("Ort");
		sqlStatement += getStringSQL("Reisender");
		sqlStatement += getStringSQL("Expedient");
		sqlStatement += getStringSQL("Besteller");
		sqlStatement += getStringSQL("Waehrung");

		log.debug("sqlStatement: " + sqlStatement);

		MidocoBelegeDAO mysqldao = new MidocoBelegeDAO();
		List fileConfigurationList = mysqldao.doSelectList(sqlStatement);

		return fileConfigurationList;
	}

	protected List<MidocoBelege> doSelectSQLListUsingBelegnummer(
			String belegnummer) throws SQLException {

		log.debug("belegnummer in doSelectSQLListUsingBelegnummer : "
				+ belegnummer);
		String firma = request.getParameter("Firma"); // Firma

		String sqlStatement = "select * from MidocoBelege where RBS_Firma like \"%"
				+ firma
				+ "\""
				+ " and Belegnummer like \"%"
				+ belegnummer
				+ "\" ";

		log.debug("sqlStatement: " + sqlStatement);

		MidocoBelegeDAO mysqldao = new MidocoBelegeDAO();
		List fileConfigurationList = mysqldao.doSelectList(sqlStatement);

		return fileConfigurationList;

	}

	protected boolean validateRequestParameter() throws IOException, SQLException {
		boolean isValid = true;

		// Validate SessionID
		SessionDAO sessionDao = new SessionDAO();
		String notValidMessage;

		try {
			notValidMessage = sessionDao.isSessionValid(request);
			if (!"".equals(notValidMessage)) {
				isValid = false;
				doResponse(notValidMessage);
			}
		} catch (SQLException e) {
			String text = "";
			try {

				text = Templates.ReadFile("Error.html");
				text = text.replaceAll("\\{\\*ExceptionTitle\\*\\}",
						"Fehler ist aufgetreten");
				text = text.replaceAll("\\{\\*ExceptionMessage\\*\\}",
						e.toString());
			} catch (IOException e1) {
				text = "Fehler ist aufgetreten \n" + e;
				log.error(e1);
			}
			doResponse(text);
		}

		// Validate Belegdatum
		String reisedatum = request.getParameter("Belegdatum");
		if (reisedatum != null && !reisedatum.isEmpty()) {

			SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
			Date datum = null;
			try {
				datum = format.parse(reisedatum);
				log.debug("Datum: " + datum);

			} catch (ParseException e) {
				isValid = false;
				doResponse("Bitte ein valides Belegdatum (YYYY.MM.DD) eingeben");
			}
		}

		// Validate Belegnummer
		String barcodeString = request.getParameter("Belegnummer");
		if (barcodeString != null && !barcodeString.isEmpty()) {

			Integer barcodeInteger = null;
			try {
				barcodeInteger = Integer.parseInt(barcodeString);
				log.debug("Datum: " + barcodeInteger);

			} catch (NumberFormatException e) {
				isValid = false;
				doResponse("Bitte ein nummerischen wert im Feld Belegnummer eingeben.");
			}
		}
		return isValid;
	}

}
