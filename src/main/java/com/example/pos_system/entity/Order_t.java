package com.example.pos_system.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Order_t {
    @Id
    private String oId;
    private String oDate;
    private double oTotal;
    private double oSubTotal;
    private int oDiscount;
    private double oBalance;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;

    @ManyToMany
    private List<Item> items;

    public Order_t() {
    }

    public Order_t(String oId, String oDate, double oTotal, double oSubTotal, int oDiscount, double oBalance, Customer customer, List<Item> items) {
        this.oId = oId;
        this.oDate = oDate;
        this.oTotal = oTotal;
        this.oSubTotal = oSubTotal;
        this.oDiscount = oDiscount;
        this.oBalance = oBalance;
        this.customer = customer;
        this.items = items;
    }

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public String getoDate() {
        return oDate;
    }

    public void setoDate(String oDate) {
        this.oDate = oDate;
    }

    public double getoTotal() {
        return oTotal;
    }

    public void setoTotal(double oTotal) {
        this.oTotal = oTotal;
    }

    public double getoSubTotal() {
        return oSubTotal;
    }

    public void setoSubTotal(double oSubTotal) {
        this.oSubTotal = oSubTotal;
    }

    public int getoDiscount() {
        return oDiscount;
    }

    public void setoDiscount(int oDiscount) {
        this.oDiscount = oDiscount;
    }

    public double getoBalance() {
        return oBalance;
    }

    public void setoBalance(double oBalance) {
        this.oBalance = oBalance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
