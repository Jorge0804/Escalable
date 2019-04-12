package com.example.escalable.Models;

import java.util.List;

public class modules {
    Integer id;
    String nombre, information;

    public modules(Integer id, String nombre, String information) {
        this.id = id;
        this.nombre = nombre;
        this.information = information;
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
}
