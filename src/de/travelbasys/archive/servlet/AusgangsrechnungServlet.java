package de.travelbasys.archive.servlet;

import javax.servlet.annotation.WebServlet;

@WebServlet({ "/AusgangsrechnungenArchive" })
public class AusgangsrechnungServlet extends Ausgangsrechnung_KostenrechnungServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getDokumentart() {
		return "AR";
	}

}
