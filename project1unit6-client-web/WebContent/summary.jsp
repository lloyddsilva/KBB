<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="edu.cmu.lloyddsilva.model.Automobile" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Car Configurator - Configured Car Summary</title>
</head>
<body>
	<% Automobile auto = (Automobile) request.getSession().getAttribute("auto"); %>
	<h3>Your Configured Car is:</h3>
    <table border="1" style="width:100%">
      <tr>
        <td><%= auto.getName() %></td>
        <td>Base Price</td> 
        <td><%= auto.getBasePrice() %></td> 
      </tr>
      <% ArrayList<String> opSets = auto.getOptionSetsAsString();
         for(String opSet : opSets) {
        	 String selectedOption = request.getParameter(opSet);
        	 auto.setOptionChoice(opSet, selectedOption);
       %>
       
       <tr>
        	<td><%= opSet %></td>
        	<td><%= selectedOption %></td>
        	<td><%= auto.getOptionChoicePrice(opSet) %></td> 
      	</tr>

       <% 	 
         }
       %>
      <tr>
        <td>Total Cost</td>
        <td></td>
        <td><%= auto.getTotalPrice() %></td>  
      </tr>
    </table>
</body>
</html>