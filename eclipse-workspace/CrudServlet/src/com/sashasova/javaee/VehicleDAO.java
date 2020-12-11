package com.sashasova.javaee;
import java.util.*;
import java.sql.*;

public class VehicleDAO {

	public static Connection getConnection(){
		Connection con=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/accounting","root","root");
		}catch(Exception e){System.out.println(e);}
		return con;
	}
	public static int save(Vehicle v){
		int status=0;
		try{
			Connection con=VehicleDAO.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into vehicles(ownerName,insuranceValue,insurancePremiums) values (?,?,?)");
			ps.setString(1,v.getOwnerName());
			ps.setDouble(2,v.getInsuranceValue());
			ps.setDouble(3, v.getInsurancePremiums());
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return status;
	}
	public static int update(Vehicle v){
		int status=0;
		try{
			Connection con=VehicleDAO.getConnection();
			PreparedStatement ps=con.prepareStatement("update vehicles set ownerName=?,insuranceValue=?,insurancePremiums=? where idVehicles=?");
			ps.setString(1,v.getOwnerName());
			ps.setDouble(2,v.getInsuranceValue());
			ps.setInt(3,v.getId());		
			ps.setDouble(4,v.getInsurancePremiums());
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return status;
	}
	public static int delete(int id){
		int status=0;
		try{
			Connection con=VehicleDAO.getConnection();
			PreparedStatement ps=con.prepareStatement("delete from vehicles where idVehicles=?");
			ps.setInt(1,id);
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception e){e.printStackTrace();}
		
		return status;
	}
	public static Vehicle getVehicleById(int id){
		Vehicle v=new Vehicle();
		try{
			Connection con=VehicleDAO.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from vehicles where idVehicles=?");
			ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				v.setId(rs.getInt(1));
				v.setOwnerName(rs.getString(2));
				v.setInsuranceValue(rs.getDouble(3));
				v.setInsurancePremiums(rs.getDouble(4));
			}
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return v;
	}
	public static List<Vehicle> getAllVehicles(){
		List<Vehicle> list=new ArrayList<Vehicle>();
		
		try{
			Connection con=VehicleDAO.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from vehicles");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vehicle v=new Vehicle();
				v.setId(rs.getInt(1));
				v.setOwnerName(rs.getString(2));
				v.setInsuranceValue(rs.getDouble(3));
				v.setInsurancePremiums(rs.getDouble(4));
				list.add(v);
			}
			con.close();
		}catch(Exception e){e.printStackTrace();}
		
		return list;
	}
}
