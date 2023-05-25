package com.procesos.segundo_previo.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "vehicle")
    private String vehicle;
    @Column(name = "model")
    private String model;
    @Column(name = "color")
    private String color;
    @Column(name = "model_year")
    private String model_year;
    @Column(name = "price")
    private String price;

    public String getVehicle() {
        return vehicle;
    }

    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getModel_year() {
        return model_year;
    }

    public String getPrice() {
        return price;
    }


    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setModel_year(String model_year) {
        this.model_year = model_year;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
