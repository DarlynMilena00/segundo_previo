package com.procesos.parcial_final.models;

import lombok.Data;

import java.util.List;

@Data
public class VehiclesApi {
    private List<Vehicles> vehicles;

    public List<Vehicles> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicles> vehicles) {
        this.vehicles =vehicles;
    }
}
