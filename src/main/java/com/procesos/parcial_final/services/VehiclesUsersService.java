package com.procesos.parcial_final.services;

import com.procesos.parcial_final.models.VehiclesUser;
import com.procesos.parcial_final.models.User;

import java.util.List;

public interface VehiclesUsersService {
    Boolean saveVehiclesUsers(VehiclesUser vehiclesUser);

    Boolean updateVehiclesUsers (Long id, VehiclesUser vehiclesUser);

    List<VehiclesUser> getVehiclesUsers(User user);

    List<VehiclesUser> allVehiclesUsers();
}
