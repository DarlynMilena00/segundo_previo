package com.procesos.parcial_final.models;

import lombok.Data;

import java.util.List;

@Data
public class VehiclesApi {
    private List<Vehicles> cars;

    public List<Vehicles> getCars() {
        return cars;
    }

    public void setCars(List<Vehicles> cars) {
        this.cars = cars;
    }
}
