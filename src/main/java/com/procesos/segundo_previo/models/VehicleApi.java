package com.procesos.segundo_previo.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class VehicleApi {
    private List<Vehicle> vehicles;
    public List<Vehicle> getVehicles(){
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
