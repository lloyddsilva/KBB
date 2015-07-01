package edu.cmu.lloyddsilva.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Models
 */
@WebServlet("/Models")
public class Models extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletClient client;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Models() {
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
		ArrayList<String> models = client.getAvailableModels();
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.println("<!DOCTYPE html>");
		pw.println("<html lang=\"en\">");
		pw.println("<head>");
		pw.println("<title>Car Configurator - Select a Model</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<h3>Select a car model:</h3>");
		pw.println("<form action=\"Options\">");
		pw.println("<select name=\"modelname\">");
		for(String model: models) {
			pw.println("<option value=\"" + model +"\">" + model + "</option>");
		}
		pw.println("</select>");
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
