package com.example.escalable.Models;

public class courses {
    Integer id;
    String name, information, src;
    Double price;

    public courses(Integer id, String name, String information, String src, Double price) {
        this.id = id;
        this.name = name;
        this.information = information;
        this.src = src;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

