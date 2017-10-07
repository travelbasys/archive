package de.travelbasys.archive.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.travelbasys.archive.dao.Ausgangsrechnung_KostenrechnungDAO;
import de.travelbasys.archive.dao.MidocoBelegeDAO;
import de.travelbasys.archive.dao.SessionDAO;
import de.travelbasys.archive.dao.VeranstalterrechnungDAO;
import de.travelbasys.archive.model.Ausgangsrechnung_Kostenrechnung;
import de.travelbasys.archive.model.MidocoBelege;
import de.travelbasys.archive.model.Session;
import de.travelbasys.archive.model.Veranstalterrechnung;
import de.travelbasys.archive.utils.Templates;

@WebServlet({ "/VeranstalterrechnungArchive" })
public class VeranstalterrechnungServlet extends ArchiveServlet {

	/**
	 * 
	 * @author Nils, Paul and Amjad
	 * 
	 */

	private static Log log = LogFactory.getFactory().getInstance(
			VeranstalterrechnungServlet.class.getName());
	
	private static final long serialVersionUID = 1569485124701548457L;

	private static final String DOKUMENT_ART = "VR";

	
	protected String getBodyLine(Object veranstalterrechnung , int counter) {

		Veranstalterrechnung fileConfiguration = (Veranstalterrechnung) veranstalterrechnung; 
		
		String action = "/archive/ShowFile?filename=" + fileConfiguration.getrBS_Firma() + "/"
				+ fileConfiguration.getFile_name()+ "&SessionID=" + request.getParameter("SessionID");
		String fileName = "<form id='Show_" + counter + "' action='" + action + "' method='post' target='_blank'> "
				+ "<a href='#' onclick=\"document.getElementById('Show_" + counter + "').submit();\" >  "
				+ fileConfiguration.getFile_name() + " </a> </form>";
		
		String actionDownload = "/archive/DownloadFile?filename=" + fileConfiguration.getrBS_Firma() + "/"
				+ fileConfiguration.getFile_name() + "&SessionID=" + request.getParameter("SessionID");
		String fileDownload = "<form id='Download_" + counter + "' action='" + actionDownload + "' method='post'> "
				+ "<a href='#' onclick=\"document.getElementById('Download_" + counter + "').submit();\" >  "
				+ fileConfiguration.getFile_name() + " </a> </form>";

		String rbs_Firma = fileConfiguration.getrBS_Firma();
		String barcodenummer = fileConfiguration.getBarcodenummer();

		String veranstaltercode = fileConfiguration.getVeranstaltercode();
		String veranstaltername = fileConfiguration.getVeranstaltername();
		String buero = fileConfiguration.getBuero();
		String reisender = fileConfiguration.getReisender();
		String reisedatum = fileConfiguration.getReisedatum();
		String vorgangsnummer = fileConfiguration.getVorgangsnummer();
		String reisepreis = fileConfiguration.getReisepreis();
		String r_G = fileConfiguration.getR_G();
		String waehrung = fileConfiguration.getWaehrung();
		String agenturnummer = fileConfiguration.getAgenturnummer();
		String prov_betrag1 = fileConfiguration.getProv_betrag1();
		String prov_betrag2 = fileConfiguration.getProv_betrag2();
		String prov_betrag3 = fileConfiguration.getProv_betrag3();
		String prov_betrag4 = fileConfiguration.getProv_betrag4();
		String rechnungsbetrag = fileConfiguration.getRechnungsbetrag();
		String inkassoart = fileConfiguration.getInkassoart();
		String abbucherkennz = fileConfiguration.getAbbucherkennz();
		String rechnungsdatum = fileConfiguration.getRechnungsdatum();
		String rechnungsnummer = fileConfiguration.getRechnungsnummer();
		String loeschkennzeichen = fileConfiguration.getLoeschkennzeichen();
		String abgleichdatum = fileConfiguration.getAbgleichdatum();

		String line = " <tr> " + "<td> " + fileName + "</td> " + "<td> " + fileDownload + "</td> " + "<td>" + rbs_Firma + "</td> " + "<td>" + barcodenummer
				+ "</td> " + "<td>" + veranstaltercode + "</td> " + "<td>" + veranstaltername + "</td> " + "<td>"
				+ buero + "</td> " + "<td>" + reisender + "</td> " + "<td>" + reisedatum + "</td> " + "<td>"
				+ vorgangsnummer + "</td> " + "<td>" + reisepreis + "</td> " + "<td>" + r_G + "</td> " + "<td>"
				+ waehrung + "</td> " + "<td>" + agenturnummer + "</td> " + "<td>" + prov_betrag1 + "</td> " + "<td>"
				+ prov_betrag2 + "</td> " + "<td>" + prov_betrag3 + "</td> " + "<td>" + prov_betrag4 + "</td> " + "<td>"
				+ rechnungsbetrag + "</td> " + "<td>" + inkassoart + "</td> " + "<td>" + abbucherkennz + "</td> "
				+ "<td>" + rechnungsdatum + "</td> " + "<td>" + rechnungsnummer + "</td> " + "<td>" + loeschkennzeichen
				+ "</td> " + "<td>" + abgleichdatum + "</td> " + "</tr>";
		return line;
	}

	protected String getHeaderLine() {
		String header = "<tr> " + " <th> Datei anzeigen </th>" + " <th> Datei runterladen </th>" + " <th> RBS-Firma </th>" + " <th> Barcodenummer </th>"
				+ " <th> Veranstaltercode </th>" + " <th>  veranstaltername </th>" + " <th>  buero  </th>"
				+ " <th>  reisender  </th>" + " <th>  reisedatum  </th>" + " <th>  vorgangsnummer  </th>"
				+ " <th>  reisepreis  </th>" + " <th>  r_G  </th>" + " <th>  waehrung  </th>"
				+ " <th>  agenturnummer </th>" + " <th>  prov_betrag1  </th>" + " <th>  prov_betrag2 </th>"
				+ " <th>  prov_betrag3 </th>" + " <th>  prov_betrag4  </th>" + " <th>  rechnungsbetrag </th>"
				+ " <th>  inkassoart  </th>" + " <th>  abbucherkennz  </th>" + " <th>  rechnungsdatum  </th>"
				+ " <th>  rechnungsnummer  </th>" + " <th>  loeschkennzeichen  </th>" + " <th>  abgleichdatum  </th>"
				+ "</tr>";

		return header;
	}

	protected List<MidocoBelege> doSelectSQLList() throws SQLException {

		String firma = request.getParameter("Firma"); // Firma
		// String dokumentart = request.getParameter("Dokumentart"); //
		// barcode_operation("AR","BP,"KR","VR")
		String dokumentart = DOKUMENT_ART;

		String sqlStatement = "select * from Veranstalterrechnung where RBS_Firma like \"%" + firma + "\""
				+ " and Dokumentart like \"%" + dokumentart + "\" ";

		// Build SQL Statement depending on the value of parameter
		sqlStatement += getStringSQL("Veranstaltercode");
		sqlStatement += getStringSQL("Veranstaltername");
		sqlStatement += getStringSQL("Buero");
		sqlStatement += getStringSQL("Reisender");
		sqlStatement += getOperationSQL("Barcodenummer", "BarcodenummerOperation");
		
		if(request.getParameter("Reisedatum")!=null && !request.getParameter("Reisedatum").isEmpty()) //Simple
			{
		sqlStatement += getOperationDateSQL("Reisedatum", "Reisedatum","ReisedatumOperation");
			}else{ //BelegdatumAUSEXT extended
				sqlStatement += getOperationDateSQL("ReisedatumEXT", "Reisedatum", "ReisedatumOperation"); //2017-02-14 13:27:14  02/21/2017
			}
		
//		if(request.getParameter("Belegdatum")!=null && !request.getParameter("Belegdatum").isEmpty()) //Simple
//		{
//			sqlStatement += getOperationDateSQL("Belegdatum", "Belegdatum", "BelegdatumOperation"); //2017-02-14 13:27:14  02/21/2017
//		}else{ //BelegdatumAUSEXT extended
//			sqlStatement += getOperationDateSQL("BelegdatumAUSEXT", "Belegdatum", "BelegdatumOperation"); //2017-02-14 13:27:14  02/21/2017
//		}
// TODO		
		
		
		// Extended SQL Parameter
		sqlStatement += getOperationSQL("Vorgangsnummer", "VorgangsnummerOperation");
		sqlStatement += getOperationSQL("Reisepreis", "ReisepreisOperation");
		sqlStatement += getStringSQL("RG");
		sqlStatement += getStringSQL("Waehrung");
		sqlStatement += getOperationSQL("Agenturnumme", "AgenturnummeOperation");
		sqlStatement += getOperationSQL("Prov_betrag1", "Prov_betrag1Operation");
		sqlStatement += getOperationSQL("Prov_betrag2", "Prov_betrag2Operation");
		sqlStatement += getOperationSQL("Prov_betrag3", "Prov_betrag3Operation");
		sqlStatement += getOperationSQL("Prov_betrag4", "Prov_betrag4Operation");
		sqlStatement += getOperationSQL("Rechnungsbetrag", "RechnungsbetragOperation");
		sqlStatement += getStringSQL("Inkassoart");
		sqlStatement += getStringSQL("Abbucherkennz");
		sqlStatement += getOperationDateSQL("Rechnungsdatum", "Rechnungsdatum","RechnungsdatumOperation");
		sqlStatement += getOperationSQL("Rechnungsnummer", "RechnungsnummerOperation");
		sqlStatement += getOperationSQL("Loeschkennzeichen", "LoeschkennzeichenOperation");
		sqlStatement += getOperationSQL("Abgleichdatum", "AbgleichdatumOperation");

		log.debug("sqlStatement: " + sqlStatement);

		VeranstalterrechnungDAO mysqldao = new VeranstalterrechnungDAO();
		List fileConfigurationList = mysqldao.doSelectList(sqlStatement);
		
		
		return fileConfigurationList;
	}

	protected List<Veranstalterrechnung> doSelectSQLListUsingBelegnummer(String belegnummer) throws SQLException {

		String firma = request.getParameter("Firma"); // Firma

		String sqlStatement = "select * from Veranstalterrechnung where RBS_Firma like \"%" + firma + "\""
				+ " and Barcode like \"%" + belegnummer + "\" ";

		log.debug("sqlStatement: " + sqlStatement);

		VeranstalterrechnungDAO mysqldao = new VeranstalterrechnungDAO();
		List fileConfigurationList = mysqldao.doSelectList(sqlStatement);
		
		
		return fileConfigurationList;
	
		
	}
	protected boolean validateRequestParameter() throws IOException, SQLException {
		boolean isValid = true;
		//Validate SessionID
		SessionDAO sessionDao = new SessionDAO();
		try{
		String notValidMessage = sessionDao.isSessionValid(request);
		if (!"".equals(notValidMessage)) {
			isValid = false;
			doResponse(notValidMessage);
		}
		}catch (SQLException e) {
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
		// Validate Reisedatum
		String reisedatum = request.getParameter("Reisedatum");
		if (reisedatum != null && !reisedatum.isEmpty()) {

			SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
			Date datum = null;
			try {
				datum = format.parse(reisedatum);
				log.debug("Datum: " + datum);

			} catch (ParseException e) {
				isValid = false;
				doResponse("Bitte ein valides Reisedatum (YYYY.MM.DD) eingeben");
			}
		}

		// Validate barcode
		String barcodeString = request.getParameter("Barcode");
		if (barcodeString != null && !barcodeString.isEmpty()) {

			Integer barcodeInteger = null;
			try {
				barcodeInteger = Integer.parseInt(barcodeString);
				log.debug("Datum: " + barcodeInteger);

			} catch (NumberFormatException e) {
				isValid = false;
				doResponse("Bitte ein nummerischen wert im Feld Barcode eingeben.");
			}
		}
		return isValid;
	}

}
