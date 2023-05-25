package com.procesos.segundo_previo.services;

import com.procesos.segundo_previo.models.User;
import com.procesos.segundo_previo.models.VehicleUser;

import java.util.List;

public interface VehicleUserService {
    Boolean createVehicleUser(VehicleUser vehicleUser);
    Boolean updateVehicleUser(Long id, VehicleUser vehicleUser);
    List<VehicleUser> getVehicleUser(User user);
    List <VehicleUser> allVehicleUser();
}
