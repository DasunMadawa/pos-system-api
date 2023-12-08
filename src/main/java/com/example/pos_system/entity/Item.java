package com.example.pos_system.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Item {
    @Id
    private String iCode;
    private String iName;
    private double iPrice;
    private int iQty;

    @ManyToMany(mappedBy = "items" ,cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    List<Order_t> orders;

    public Item() {
    }

    public Item(String iCode, String iName, double iPrice, int iQty) {
        this.iCode = iCode;
        this.iName = iName;
        this.iPrice = iPrice;
        this.iQty = iQty;
    }

    public String getiCode() {
        return iCode;
    }

    public void setiCode(String iCode) {
        this.iCode = iCode;
    }

    public String getiName() {
        return iName;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }

    public double getiPrice() {
        return iPrice;
    }

    public void setiPrice(double iPrice) {
        this.iPrice = iPrice;
    }

    public int getiQty() {
        return iQty;
    }

    public void setiQty(int iQty) {
        this.iQty = iQty;
    }

}
