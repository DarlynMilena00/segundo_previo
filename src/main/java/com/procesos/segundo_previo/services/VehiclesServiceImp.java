package com.procesos.segundo_previo.services;

import com.procesos.segundo_previo.models.Vehicles;
import com.procesos.segundo_previo.repository.VehiclesRepository;
import com.procesos.segundo_previo.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiclesServiceImp implements VehiclesService {
    @Autowired
    private VehiclesRepository vehiclesRepository;
    @Autowired
    private JWTUtil jwtUtil;

    public Vehicles getVehicles(Long id){
        return vehiclesRepository.findById(id).get();
    }

    @Override
    public Boolean createVehicles(Vehicles vehicles){
        try{
            vehiclesRepository.save(vehicles);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public List<Vehicles> allVehicles(){
        return vehiclesRepository.findAll();
    }

    @Override
    public Boolean updateVehicles(Long id, Vehicles vehicles) {
        try {
            Vehicles vehiclesBD = vehiclesRepository.findById(id).get();
            vehiclesBD.setType(vehicles.getType());
            Vehicles vehiclesUp = vehiclesRepository.save(vehiclesBD);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
