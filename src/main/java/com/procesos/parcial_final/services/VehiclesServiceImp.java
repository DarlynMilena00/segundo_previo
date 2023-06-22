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
    public Boolean saveCars() {
        try{
            String url="https://myfakeapi.com/api/cars/";
            VehiclesApi vehiclesApi = restTemplate.getForObject(url, VehiclesApi.class);
            Map response = new HashMap();
            int contador=0;
            int registrado=0;
            for (Vehicles vehicles : vehiclesApi.getCars()) {
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
    public Vehicles getCars(Long id){

        return vehiclesRepository.findById(id).get();
    }

    @Override
    public List<Vehicles> allCars() {
        return vehiclesRepository.findAll();
    }

    @Override
    public Boolean updateCars(Long id, Vehicles vehicles) {
        try{
            Vehicles vehiclesBD = vehiclesRepository.findById(id).get();
            vehiclesBD.setCar(vehicles.getCar());
            vehiclesBD.setCar_color(vehicles.getCar_color());
            vehiclesBD.setCar_price(vehicles.getCar_price());
            vehiclesBD.setCar_vin(vehicles.getCar_vin());
            vehiclesBD.setAvailability(vehicles.getAvailability());
            vehiclesBD.setCar_model(vehicles.getCar_model());
            vehiclesBD.setCar_model_year(vehicles.getCar_model_year());
            vehiclesRepository.save(vehiclesBD);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
