package com.procesos.segundo_previo.services;

import com.procesos.segundo_previo.models.Vehicles;

import java.util.List;

public interface VehiclesService {
    Vehicles getVehicles(Long id);
    Boolean createVehicles(Vehicles vehicles);
    List<Vehicles> allVehicles();
    Boolean updateVehicles(Long id, Vehicles vehicles);

}
