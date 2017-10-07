package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/piza" , "/helloworld/piza"})
public class HelloServlet extends HttpServlet {

	/**
	 * 
	 */
	private HttpServletRequest request = null;
	
	private HttpServletResponse response = null;
	
	private static final long serialVersionUID = 1569485124701548457L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		request = req;
		response  = resp;
		doRequest("Get Protocol..");
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		request = req;
		response  = resp;
		
		doRequest("Post Protocol..");
	}

	private void doRequest(String string) {
		
		try {
			String greeting = request.getParameter("Greeting"); 
			String name = request.getParameter("Name1"); 
			
			PrintWriter writer = response.getWriter();
			writer.write(greeting + " " + name);
			
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
}


