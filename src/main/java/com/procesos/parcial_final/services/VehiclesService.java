package com.procesos.parcial_final.services;

import com.procesos.parcial_final.models.Vehicles;

import java.util.List;

public interface VehiclesService {

    Boolean saveVehicles();

    Boolean updateVehicles (Long id, Vehicles vehicles);

    Vehicles getVehicles(Long id);

    List <Vehicles> allVehicles();

}
