package com.example.escalable.Models;

public class videos {
    Integer id;
    String nombre, information, src;

    public videos(Integer id, String nombre, String information, String src) {
        this.id = id;
        this.nombre = nombre;
        this.information = information;
        this.src = src;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
}
