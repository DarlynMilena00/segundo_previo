package com.procesos.segundo_previo.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class VehiclesApi {
    private List<Vehicles> vehicles;
    public List<Vehicles> getVehicles(){
        return vehicles;
    }

    public void setVehicles(List<Vehicles> vehicles) {
        this.vehicles = vehicles;
    }
}
