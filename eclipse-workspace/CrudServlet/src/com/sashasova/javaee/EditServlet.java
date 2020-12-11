package com.sashasova.javaee;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.print("<style>");
		out.print("input::-webkit-outer-spin-button,");
		out.print("input::-webkit-inner-spin-button {");
		out.print("-webkit-appearance: none;");
		out.print(" margin: 0;");
		out.print("}");
		out.print("input[type=number] {");
		out.print("-moz-appearance: textfield;");
		out.print("}");
		out.print("</style>");
		
		out.println("<h1>Update Vehicle</h1>");
		String sid=request.getParameter("id");
		int id=Integer.parseInt(sid);
		
		Vehicle v = VehicleDAO.getVehicleById(id);
		
		out.print("<form action='EditServlet2' method='post'>");
		out.print("<table>");
		out.print("<tr><td></td><td><input type='hidden' name='id' value='"+v.getId()+"'/></td></tr>");
		out.print("<tr><td>Name:</td><td><input type='text' name='ownerName' value='"+v.getOwnerName()+"'/></td></tr>");
		
		out.print("<tr><td>Insurance value:</td><td><input type='number' step='0.01' onkeypress='return (event.charCode !=8 && event.charCode ==0 || ( event.charCode == 46 || (event.charCode >= 48 && event.charCode <= 57)))' name='insuranceValue' value='"+v.getInsuranceValue()+"'/></td></tr>");
		
		out.print("<tr><td colspan='2'><input type='submit' value='Edit &amp; Save '/></td></tr>");
		out.print("</table>");
		out.print("</form>");
		
		out.close();
	}
}
