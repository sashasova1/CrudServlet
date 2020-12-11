package com.sashasova.javaee;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/EditServlet2")
public class EditServlet2 extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String sid=request.getParameter("id");
		int id=Integer.parseInt(sid);
		String ownerName=request.getParameter("ownerName");
		String insurance=request.getParameter("insuranceValue");
		double insuranceValue = Double.parseDouble(insurance);
		
		Vehicle v=new Vehicle();
		v.setId(id);
		v.setOwnerName(ownerName);
		v.setInsuranceValue(insuranceValue);
		
		int status=VehicleDAO.update(v);
		if(status>0){
			response.sendRedirect("ViewServlet");
		} else {
			out.println("Sorry! unable to update record");
		}
		
		out.close();
	}

}
