package com.example.listview;

public class Spot {
    private int pho;
    private String name;
    private String dsc;

    public Spot(int pho, String name, String dsc) {
        this.pho = pho;
        this.name = name;
        this.dsc = dsc;
    }

    public int getPho() {
        return pho;
    }

    public void setPho(int pho) {
        this.pho = pho;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDsc() {
        return dsc;
    }

    public void setDsc(String dsc) {
        this.dsc = dsc;
    }
}
