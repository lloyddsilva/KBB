package edu.cmu.lloyddsilva.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.cmu.lloyddsilva.model.Automobile;

/**
 * Servlet implementation class Options
 */
@WebServlet("/Options")
public class Options extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletClient client;
	private HttpSession session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Options() {
        super();
    }
    
    public void init()
    {
    	client = ServletClient.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession(true);
		String modelname = request.getParameter("modelname");
		Automobile auto = client.getModel(modelname);
		request.getSession().setAttribute("auto", auto);
		ArrayList<String> opSets = auto.getOptionSetsAsString();
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.println("<!DOCTYPE html>");
		pw.println("<html lang=\"en\">");
		pw.println("<head>");
		pw.println("<title>Car Configurator - Select Options</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<h3>Select a car options:</h3>");
		pw.println("<form action=\"summary.jsp\">");
		pw.println("<table border=\"1\" style=\"width:100%\">");
		pw.println("<tr>");
		pw.println("<td>Make/Model:</td>");
		pw.println("<td>" + modelname + "</td>");
		pw.println("</tr>");
		
		for(String opSet : opSets) {
			pw.println("<tr>");
			pw.println("<td>" + opSet + "</td>");
			pw.println("<td>");
			
			pw.println("<select name=\""+ opSet + "\">");
			for(String option: auto.getOptionsAsString(opSet)) {
				pw.println("<option value=\"" + option +"\">" + option + "</option>");
			}
			pw.println("</select>");

			pw.println("</td>");
			pw.println("</tr>");
		}
		pw.println("</table>");
		pw.println("<input type=\"submit\" value=\"Submit\">");
		pw.println("</form>");
		pw.println("</body>");
		pw.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
