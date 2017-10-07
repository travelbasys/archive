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

abstract public class Ausgangsrechnung_KostenrechnungServlet extends ArchiveServlet {

	/**
	 * 
	 * @author Nils, Paul and Amjad
	 * 
	 */

	private static final long serialVersionUID = 1569485124701548457L;

	private static Log log = LogFactory.getFactory().getInstance(
			Ausgangsrechnung_KostenrechnungServlet.class.getName());
	
	abstract protected String getDokumentart();

	protected String getBodyLine(Object object, int counter) {

		Ausgangsrechnung_Kostenrechnung fileConfiguration = (Ausgangsrechnung_Kostenrechnung) object;
		
		String action = "/archive/ShowFile?filename=" + fileConfiguration.getRBS_Firma() + "/"
				+ fileConfiguration.getFile_name()+ "&SessionID=" + request.getParameter("SessionID");
		String fileName = "<form id='Show_" + counter + "' action='" + action + "' method='post' target='_blank'> "
				+ "<a href='#' onclick=\"document.getElementById('Show_" + counter + "').submit();\" >  "
				+ fileConfiguration.getFile_name() + " </a> </form>";
		
		String actionDownload = "/archive/DownloadFile?filename=" + fileConfiguration.getRBS_Firma() + "/"
				+ fileConfiguration.getFile_name() + "&SessionID=" + request.getParameter("SessionID");
		String fileDownload = "<form id='Download_" + counter + "' action='" + actionDownload + "' method='post'> "
				+ "<a href='#' onclick=\"document.getElementById('Download_" + counter + "').submit();\" >  "
				+ fileConfiguration.getFile_name() + " </a> </form>";

		String Dokumentart = fileConfiguration.getDokumentart();
		String Barcode = fileConfiguration.getBarcode();

		String RBS_Firma = fileConfiguration.getRBS_Firma();
		String Bukrs = fileConfiguration.getBukrs();
		String BelegNr = fileConfiguration.getBelegNr();
		String GJahr = fileConfiguration.getGJahr();
		String Belegart = fileConfiguration.getBelegart();
		String Belegdatum = fileConfiguration.getBelegdatum();
		String Buchungsdatum = fileConfiguration.getBuchungsdatum();
		String Belegkopftext = fileConfiguration.getBelegkopftext();
		String Referenz_BelegNr = fileConfiguration.getReferenz_BelegNr();
		String Dauer_BelegNr = fileConfiguration.getDauer_BelegNr();
		String Waehrung = fileConfiguration.getWaehrung();
		String Mappe = fileConfiguration.getMappe();
		String Erfasst = fileConfiguration.getErfasst();
		String Erfasser = fileConfiguration.getErfasser();
		String DKonto = fileConfiguration.getDKonto();
		String DOrt = fileConfiguration.getDOrt();
		String DBetrag = fileConfiguration.getDBetrag();
		String DZuordnung = fileConfiguration.getDZuordnung();
		String DSGText = fileConfiguration.getDSGText();
		String DXRef1 = fileConfiguration.getDXRef1();
		String DXRef2 = fileConfiguration.getDXRef2();
		String DXRef3 = fileConfiguration.getDXRef3();
		String Mandant = fileConfiguration.getMandant();
		String System_ID = fileConfiguration.getSystem_ID();
		String User_Name = fileConfiguration.getUser_Name();
		String TagesDatum = fileConfiguration.getTagesDatum();
		String TagesZeit = fileConfiguration.getTagesZeit();

		String line = "<tr> " + " <td> " + fileName + " </td> " + " <td> " + fileDownload + " </td> " + " <td> " + Dokumentart + " </td> " + " <td> "
				+ Barcode + " </td> " + " <td> " + RBS_Firma + " </td> " + " <td> " + Bukrs + " </td> " + " <td> "
				+ BelegNr + " </td> " + " <td> " + GJahr + " </td> " + "<td> " + Belegart + " </td> " + " <td> "
				+ Belegdatum + " </td> " + " <td> " + Buchungsdatum + " </td> " + " <td> " + Belegkopftext + " </td> "
				+ " <td> " + Referenz_BelegNr + " </td> " + " <td> " + Dauer_BelegNr + " </td> " + " <td> " + Waehrung
				+ " </td> " + "<td> " + Mappe + " </td> " + " <td> " + Erfasst + " </td> " + " <td> " + Erfasser
				+ " </td> " + " <td> " + DKonto + " </td> " + " <td> " + DOrt + " </td> " + " <td> " + DBetrag
				+ " </td> " + " <td> " + DZuordnung + " </td> " + " <td> " + DSGText + " </td> " + " <td> " + DXRef1
				+ " </td> " + " <td> " + DXRef2 + " </td> " + " <td> " + DXRef3 + " </td> " + " <td> " + Mandant
				+ " </td> " + " <td> " + System_ID + " </td> " + " <td> " + User_Name + " </td> " + " <td> "
				+ TagesDatum + " </td> " + " <td> " + TagesZeit + " </td> " + "</tr>";
		return line;
	}

	protected String getHeaderLine() {

		String header = "<tr> " + " <th> Datei Anzeigen </th>" + " <th> Datei Herunterladen </th>" + " <th>  Dokumentart </th>" + " <th>  Barcode  </th>"
				+ " <th>  RBS_Firma  </th>" + " <th>  Bukrs </th>" + " <th>  BelegNr </th>" + " <th>  GJahr </th>"
				+ "<th>  Belegart </th>" + " <th>  Belegdatum </th>" + " <th>  Buchungsdatum </th>"
				+ " <th>  Belegkopftext </th>" + " <th>  Referenz_BelegNr </th>" + " <th>  Dauer_BelegNr </th>"
				+ " <th>  Waehrung </th>" + "<th>  Mappe </th>" + " <th>  Erfasst </th>" + " <th>  Erfasser </th>"
				+ " <th>  DKonto </th>" + " <th>  DOrt </th>" + " <th>  DBetrag </th>" + " <th>  DZuordnung </th>"
				+ " <th>	DSGText </th>" + " <th>  DXRef1 </th>" + " <th>  DXRef2 </th>" + " <th>  DXRef3 </th>"
				+ " <th>  Mandant </th>" + " <th>  System_ID </th>" + " <th>  User_Name </th>"
				+ " <th>  TagesDatum </th>" + " <th>  TagesZeit </th>" + "</tr>";
		return header;
	}

	protected List<MidocoBelege> doSelectSQLList() throws SQLException {

		String firma = request.getParameter("Firma"); // Firma
		// String dokumentart = request.getParameter("Dokumentart"); //
		// barcode_operation("AR","BP,"KR","VR")
		String dokumentart = getDokumentart();

		String sqlStatement = "select * from Ausgangsrechnung_Kostenrechnung where RBS_Firma like \"%" + firma + "\""
				+ " and Dokumentart like \"%" + dokumentart + "\" ";

		// Build SQL Statement depending on the value of parameter
		sqlStatement += getStringSQL("RBS_Firma");
		sqlStatement += getOperationSQL("Barcodenummer", "BarcodenummerOperation");
		sqlStatement += getOperationSQL("Buchungskreis", "BuchungskreisOperation");
		sqlStatement += getOperationSQL("Belegnummer", "BelegnummerOperation");
		sqlStatement += getOperationSQL("Belegart", "BelegartOperation");
		
		if(request.getParameter("Belegdatum")!=null && !request.getParameter("Belegdatum").isEmpty()) //Simple
		{
			sqlStatement += getOperationDateSQL("Belegdatum", "Belegdatum", "BelegdatumOperation"); //2017-02-14 13:27:14  02/21/2017
		}else{ //BelegdatumAUSEXT extended
			sqlStatement += getOperationDateSQL("BelegdatumAUSEXT", "Belegdatum", "BelegdatumOperation"); //2017-02-14 13:27:14  02/21/2017
		}
		
		
		sqlStatement += getOperationSQL("ReferenzBelegNr", "ReferenzBelegNrOperation");
//		sqlStatement += getOperationSQL("Kontonummer", "KontonummerOperation");
		sqlStatement += getOperationSQL("DBetrag", "BetragOperation");
		sqlStatement += getOperationSQL("DZuordnung", "ZuordnungOperation");

		sqlStatement += getOperationSQL("GJahr", "GJahrOperation");
		sqlStatement += getOperationDateSQL("Buchungsdatum", "Buchungsdatum", "BuchungsdatumOperation");
		sqlStatement += getStringSQL("Belegkopftext");
		sqlStatement += getOperationSQL("Dauer_BelegNr", "Dauer_BelegNrOperation");
		sqlStatement += getStringSQL("Waehrung");
		sqlStatement += getStringSQL("Mappe");
		sqlStatement += getOperationDateSQL("Erfasst" ,"Erfasst" , "ErfasstOperation");
		sqlStatement += getStringSQL("Erfasser");
		
		
		sqlStatement += getOperationDateSQL("DOrt","DOrt", "DOrt");
		sqlStatement += getOperationDateSQL("DSGText", "DSGText","DSGText");
		sqlStatement += getStringSQL("DXRef1");
		sqlStatement += getStringSQL("DXRef2");
		sqlStatement += getStringSQL("DXRef3");
		
		sqlStatement += getOperationSQL("Mandant", "MandantOperation");
		sqlStatement += getStringSQL("System_ID");
		sqlStatement += getOperationDateSQL("User_Name", "User_Name","User_Name");
		sqlStatement += getOperationDateSQL("TagesDatum", "TagesDatum","TagesDatumOperation");
		sqlStatement += getStringSQL("TagesZeit");

		
		sqlStatement += getStringSQL("DKonto");
		
		
		log.debug("sqlStatement: " + sqlStatement);
		Ausgangsrechnung_KostenrechnungDAO mysqldao = new Ausgangsrechnung_KostenrechnungDAO();
		List fileConfigurationList = mysqldao.doSelectList(sqlStatement);

		return fileConfigurationList;
	}

	protected List<Ausgangsrechnung_Kostenrechnung> doSelectSQLListUsingBelegnummer(String belegnummer) throws SQLException {

		String firma = request.getParameter("Firma"); // Firma

		String sqlStatement = "select * from Ausgangsrechnung_Kostenrechnung where RBS_Firma like \"%" + firma + "\""
				+ " and barcode like \"%" + belegnummer + "\" ";

		log.debug("sqlStatement: " + sqlStatement);

		Ausgangsrechnung_KostenrechnungDAO mysqldao = new Ausgangsrechnung_KostenrechnungDAO();
		List fileConfigurationList = mysqldao.doSelectList(sqlStatement);
		
		
		return fileConfigurationList;
	
		
	}
	protected boolean validateRequestParameter() throws IOException, SQLException {
		boolean isValid = true;

		// Validate SessionID
		SessionDAO sessionDao = new SessionDAO();
		String notValidMessage = sessionDao.isSessionValid(request);
		if (!"".equals(notValidMessage)) {
			isValid = false;
			doResponse(notValidMessage);
		}

		// Validate Reisedatum
		String reisedatum = request.getParameter("Reisedatum");
		if (reisedatum != null && !reisedatum.isEmpty()) {

			SimpleDateFormat format = new SimpleDateFormat("yy.MM.dd HH:mm:ss");
			Date datum = null;
			try {
				datum = format.parse(reisedatum);
				log.debug("Datum: " + datum);

			} catch (ParseException e) {
				isValid = false;
				doResponse("Bitte ein valides Reisedatum (YY.MM.DD HH:MM:SS) eingeben");
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
