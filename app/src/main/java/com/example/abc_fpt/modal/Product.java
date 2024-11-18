package com.example.abc_fpt.modal;

public class Product {
    private int id;
    private String name;
    private int price;
    private String image;

    private int soluongdamua;

    public Product(int id, String name, int price, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public Product(String name, int price, String image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }


    public Product( String name, int soluongdamua) {
        this.name = name;
        this.soluongdamua = soluongdamua;
    }


    public int getSoluongdamua() {
        return soluongdamua;
    }

    public void setSoluongdamua(int soluongdamua) {
        this.soluongdamua = soluongdamua;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
