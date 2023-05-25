package com.procesos.segundo_previo.services;

import com.procesos.segundo_previo.models.VehicleUser;
import com.procesos.segundo_previo.models.User;
import com.procesos.segundo_previo.repository.VehicleUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VehicleUserServiceImp implements VehicleUserService{

    @Autowired
    VehicleUserRepository vehicleUserRepository;

    @Override
    public Boolean createVehicleUser(VehicleUser vehicleUser) {
        try{
            vehicleUserRepository.save(vehicleUser);
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Boolean updateVehicleUser(Long id, VehicleUser vehicleUser) {
        try{
            VehicleUser vehicleUserBD = vehicleUserRepository.findById(id).get();
            vehicleUserBD.setVehicleId(vehicleUser.getVehicleId());
            vehicleUserBD.setUserId(vehicleUser.getUserId());
            vehicleUserRepository.save(vehicleUserBD);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public List<VehicleUser> getVehicleUser(User user) {
        try {
            return vehicleUserRepository.findUserById(user);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<VehicleUser> allVehicleUser() {
        return vehicleUserRepository.findAll();
    }


}
