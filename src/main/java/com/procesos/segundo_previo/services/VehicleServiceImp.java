package com.procesos.segundo_previo.services;

import com.procesos.segundo_previo.models.Vehicle;
import com.procesos.segundo_previo.models.VehicleApi;
import com.procesos.segundo_previo.repository.VehicleRepository;
import com.procesos.segundo_previo.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VehicleServiceImp implements VehicleService {
    private final RestTemplate restTemplate;
    @Autowired
    public VehicleServiceImp(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private JWTUtil jwtUtil;

    public Vehicle getVehicle(Long id){
        return vehicleRepository.findById(id).get();
    }

    @Override
    public Boolean createVehicle(){
        try {
            String url="https://myfakeapi.com/api/cars/";
            VehicleApi vehicleApi = restTemplate.getForObject(url, VehicleApi.class);
            Map response = new HashMap();
            int con=0;
            int regis=0;
            for(Vehicle vehicle : vehicleApi.getVehicles()){
                try{
                    vehicleRepository.save(vehicle);
                con++;
                    regis++;
                }catch(Exception e){}
            }
            if(con == regis){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            return false;
        }
    }

    public List<Vehicle> allVehicle(){
        return vehicleRepository.findAll();
    }

    @Override
    public Boolean updateVehicle(Long id, Vehicle vehicle) {
        try{
            Vehicle vehicleBD = vehicleRepository.findById(id).get();
            vehicleBD.setVehicle(vehicle.getVehicle());
            vehicleBD.setColor(vehicle.getColor());
            vehicleBD.setModel(vehicle.getModel());
            vehicleBD.setModel_year(vehicle.getModel_year());
            vehicleBD.setPrice(vehicle.getPrice());
            vehicleRepository.save(vehicleBD);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
