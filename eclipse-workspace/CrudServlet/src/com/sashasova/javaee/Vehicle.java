package com.sashasova.javaee;

public class Vehicle {
	private int id;

    private String ownerName;
    private double insuranceValue;
    private double insurancePremiums = insuranceValue/10;
       
    public int getId() {
    	return id;
    }
   	
    public String getOwnerName() {
        return ownerName;
    }

    public double getInsuranceValue() {
        return insuranceValue;
    }

    public double getInsurancePremiums() {
        return insurancePremiums;
    }
    
    public void setId(int id) {
    	this.id = id;
    }   

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setInsuranceValue(double insuranceValue) {
        this.insuranceValue = insuranceValue;
        this.insurancePremiums = insuranceValue/10;
    }
    
    public void setInsurancePremiums(double insurancePremiums) {
        this.insurancePremiums = insurancePremiums;
    }
}
