package de.travelbasys.archive.servlet;

import javax.servlet.annotation.WebServlet;

@WebServlet({ "/KostenrechnungenArchive" })
public class KostenrechnungServlet extends Ausgangsrechnung_KostenrechnungServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String getDokumentart() {
		return "KR";
	}

}
