package com.example.berq.e_commerce.helper;

public class Product {
    private int id;
    private String tittle;
    private String shortdesc;
    private double price;
    private String image;
    private int cat;


    public Product() {
    }

    public Product(int id, String tittle, String shortdesc, double price, String image, int cat) {
        this.id = id;
        this.tittle = tittle;
        this.shortdesc = shortdesc;
        this.price = price;
        this.image = image;
        this.cat = cat;
    }

    public Product(int id, String tittle, String shortdesc, double price, String image) {
        this.id = id;
        this.tittle = tittle;
        this.shortdesc = shortdesc;
        this.price = price;
        this.image = image;

    }

    public int getId() {
        return id;
    }

    public String getTittle() {
        return tittle;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public int getCat() {
        return cat;
    }

    public void setCat(int cat) {
        this.cat = cat;
    }


}
