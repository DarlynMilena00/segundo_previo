package com.procesos.parcial_final.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Vehicles {
    @Id
    private Long id;
    private String car;
    private String car_model;
    private String car_color;
    private Long car_model_year;
    private String car_vin;
    private String price;
    private Boolean availability;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVehicle() {
        return car;
    }

    public void setVehicle(String car) {
        this.car = car;
    }

    public String getVehicle_model() {
        return car_model;
    }

    public void setVehicle_model(String car_model) {
        this.car_model = car_model;
    }

    public String getVehicle_color() {
        return car_color;
    }

    public void setVehicle_color(String car_color) {
        this.car_color = car_color;
    }

    public Long getVehicle_model_year() {
        return car_model_year;
    }

    public void setVehicle_model_year(Long car_model_year) {
        this.car_model_year = car_model_year;
    }

    public String getVehicle_vin() {
        return car_vin;
    }

    public void setVehicle_vin(String car_vin) {
        this.car_vin = car_vin;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }
}
