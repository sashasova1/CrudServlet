package com.sashasova.javaee;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<a href='index.html'>Add New Vehicle</a>");
		out.println("<h1>Vehicles List</h1>");
		
		List<Vehicle> list=VehicleDAO.getAllVehicles();
		
		out.print("<table border='1' width='100%'");
		out.print("<tr><th>Id</th><th>Name</th><th>Insurance value</th><th>Insurance premiums</th><th>Edit</th><th>Delete</th></tr>");
		for(Vehicle v:list){
			out.print("<tr><td>"+v.getId()+"</td><td>"+v.getOwnerName()+"</td><td>"+v.getInsuranceValue()+"</td><td>"+v.getInsurancePremiums()+"</td><td><a href='EditServlet?id="+v.getId()+"'>edit</a></td><td><a href='DeleteServlet?id="+v.getId()+"'>delete</a></td></tr>");
		}
		out.print("</table>");
		
		out.close();
	}
}
