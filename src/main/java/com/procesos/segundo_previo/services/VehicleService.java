package com.procesos.segundo_previo.services;

import com.procesos.segundo_previo.models.Vehicle;

import java.util.List;

public interface VehicleService {
    Vehicle getVehicle(Long id);
    Boolean createVehicle();
    List<Vehicle> allVehicle();
    Boolean updateVehicle(Long id, Vehicle vehicle);

}
