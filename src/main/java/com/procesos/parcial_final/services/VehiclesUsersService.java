package com.procesos.parcial_final.services;

import com.procesos.parcial_final.models.VehiclesUser;
import com.procesos.parcial_final.models.User;

import java.util.List;

public interface VehiclesUsersService {
    Boolean saveCarsUsers(VehiclesUser vehiclesUser);

    Boolean updateCarsUsers (Long id, VehiclesUser vehiclesUser);

    List<VehiclesUser> getCarsUsers(User user);

    List<VehiclesUser> allCarsUsers();
}
