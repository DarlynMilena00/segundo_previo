package com.procesos.parcial_final.services;

import com.procesos.parcial_final.models.Vehicles;

import java.util.List;

public interface VehiclesService {

    Boolean saveCars();

    Boolean updateCars (Long id, Vehicles vehicles);

    Vehicles getCars(Long id);

    List <Vehicles> allCars();

}
