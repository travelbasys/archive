package de.travelbasys.archive.utils;

import java.sql.SQLException;
import java.util.List;

import de.travelbasys.archive.dao.UserDAO;
import de.travelbasys.archive.dao.VeranstalterrechnungDAO;
import de.travelbasys.archive.model.User;
import de.travelbasys.archive.model.Veranstalterrechnung;
import de.travelbasys.archive.servlet.AusgangsrechnungServlet;
import de.travelbasys.archive.servlet.LoginServlet;
import junit.framework.TestCase;

public class JUnitTest extends TestCase {

	public JUnitTest(String name) {
		super(name);
	}

	public void testAmount() {
		assertTrue(2.00 == 2.00);
	}

	public void testSession() {
		LoginServlet loginServlet = new LoginServlet();
		String sessionID = loginServlet.getSessionID();
		assertNotNull(sessionID);
		assertTrue(13 == sessionID.length());
		assertFalse(Long.parseLong(sessionID) < 0);
	}

	public void testMath() {
		String nullObject = "";
		assertEquals(nullObject, "");
		assertNotSame("0", "1");
		assertSame("1", "1");
	}
	
	public void testDocumentType(){
		AusgangsrechnungServlet ausgangsrechnungServlet = new AusgangsrechnungServlet();
		String dokumetnArt = ausgangsrechnungServlet.getDokumentart();
		assertEquals(dokumetnArt, "AR");
	}

	public void testUser() throws SQLException{
		UserDAO userdao = new UserDAO();
		User user = (User) userdao.doSelectElement("select * from user where UserID = 'mru' and Password=MD5('mru');");

		assertEquals(user.getUserID(), "mru");
	}
	
	public void testVaranstalterRechnung() throws SQLException{
		VeranstalterrechnungDAO mySQLDAO = new VeranstalterrechnungDAO();
		String firma1 = "6055";
		String sql1 = "select * from Veranstalterrechnung where RBS_Firma = " + firma1;

		List<Veranstalterrechnung> fileConfiguration = mySQLDAO
				.doSelectList(sql1);	
		assertNotNull(fileConfiguration);
		assertNotSame(fileConfiguration.size() , 0);
		
	}
}
