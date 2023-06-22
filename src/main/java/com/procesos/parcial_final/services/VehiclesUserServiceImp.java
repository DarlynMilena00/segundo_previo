package com.procesos.parcial_final.services;

import com.procesos.parcial_final.models.VehiclesUser;
import com.procesos.parcial_final.models.User;
import com.procesos.parcial_final.repository.VehiclesUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiclesUserServiceImp implements VehiclesUsersService {

    @Autowired
    VehiclesUserRepository vehiclesUserRepository;

    @Override
    public Boolean saveCarsUsers(VehiclesUser vehiclesUser) {
        try{
            vehiclesUserRepository.save(vehiclesUser);
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Boolean updateCarsUsers(Long id, VehiclesUser vehiclesUser) {
        try{
            VehiclesUser vehiclesUserBD = vehiclesUserRepository.findById(id).get();
            vehiclesUserBD.setVehiclesId(vehiclesUser.getVehiclesId());
            vehiclesUserBD.setUserId(vehiclesUser.getUserId());
            vehiclesUserRepository.save(vehiclesUserBD);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public List<VehiclesUser> getCarsUsers(User user){
        try {
            return vehiclesUserRepository.findByUserId(user);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<VehiclesUser> allCarsUsers() {
        return vehiclesUserRepository.findAll();
    }
}
