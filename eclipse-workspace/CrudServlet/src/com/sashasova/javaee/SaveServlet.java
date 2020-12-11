package com.sashasova.javaee;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		
		
		String ownerName=request.getParameter("ownerName");
		String insurance=request.getParameter("insuranceValue");
		Double insuranceValue = Double.parseDouble(insurance);
		Double insurancePremiums = Double.parseDouble(insurance);
		
		
		Vehicle v=new Vehicle();
		v.setOwnerName(ownerName);
		v.setInsuranceValue(Double.valueOf(insuranceValue));
		v.setInsurancePremiums(Double.valueOf(insuranceValue));
		
		int status=VehicleDAO.save(v);
		if(status>0){
			out.print("<p>Record saved successfully!</p>");
			request.getRequestDispatcher("index.html").include(request, response);
		}else{
			out.println("Sorry! unable to save record");
		}
		
		out.close();
	}

}
