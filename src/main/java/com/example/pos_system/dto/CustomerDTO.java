package com.example.pos_system.dto;

public class CustomerDTO {
    private String cId;
    private String cName;
    private String cAddress;
    private double cSalary;

    public CustomerDTO() {
    }

    public CustomerDTO(String cId, String cName, String cAddress, double cSalary) {
        this.cId = cId;
        this.cName = cName;
        this.cAddress = cAddress;
        this.cSalary = cSalary;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcAddress() {
        return cAddress;
    }

    public void setcAddress(String cAddress) {
        this.cAddress = cAddress;
    }

    public double getcSalary() {
        return cSalary;
    }

    public void setcSalary(double cSalary) {
        this.cSalary = cSalary;
    }


}
