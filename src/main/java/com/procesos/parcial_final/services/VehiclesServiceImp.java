package com.procesos.parcial_final.services;

import com.procesos.parcial_final.models.Vehicles;
import com.procesos.parcial_final.models.VehiclesApi;
import com.procesos.parcial_final.repository.VehiclesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VehiclesServiceImp implements VehiclesService {
    private final RestTemplate restTemplate;

    @Autowired
    public VehiclesServiceImp(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Autowired
    private VehiclesRepository vehiclesRepository;
    @Override
    public Boolean saveVehicles() {
        try{
            String url="https://myfakeapi.com/api/cars/";
            VehiclesApi vehiclesApi = restTemplate.getForObject(url, VehiclesApi.class);
            Map response = new HashMap();
            int contador=0;
            int registrado=0;
            for (Vehicles vehicles : vehiclesApi.getVehicles()) {
                contador++;
                try{
                    vehiclesRepository.save(vehicles);
                    registrado++;
                }catch (Exception e){}
            }
            if(contador==registrado){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            return false;
        }
    }
    public Vehicles getVehicles(Long id){

        return vehiclesRepository.findById(id).get();
    }

    @Override
    public List<Vehicles> allVehicles() {
        return vehiclesRepository.findAll();
    }

    @Override
    public Boolean updateVehicles(Long id, Vehicles vehicles) {
        try{
            Vehicles vehiclesBD = vehiclesRepository.findById(id).get();
            vehiclesBD.setVehicle(vehicles.getVehicle());
            vehiclesBD.setVehicle_color(vehicles.getVehicle_color());
            vehiclesBD.setPrice(vehicles.getPrice());
            vehiclesBD.setVehicle_vin(vehicles.getVehicle_vin());
            vehiclesBD.setAvailability(vehicles.getAvailability());
            vehiclesBD.setVehicle_model(vehicles.getVehicle_model());
            vehiclesBD.setVehicle_model_year(vehicles.getVehicle_model_year());
            vehiclesRepository.save(vehiclesBD);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
