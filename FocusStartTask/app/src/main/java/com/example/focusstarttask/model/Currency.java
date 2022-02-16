package com.example.focusstarttask.model;

public class Currency {
    private String charCode;
    private int nominal;
    private String name;
    private double value;

    public Currency(String charCode, int nominal, String name, double value) {
        this.charCode=charCode;
        this.name=name;
        this.nominal=nominal;
        this.value=value;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
