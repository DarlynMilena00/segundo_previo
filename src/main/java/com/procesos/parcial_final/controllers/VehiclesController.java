package com.procesos.parcial_final.controllers;

import com.procesos.parcial_final.models.Vehicles;
import com.procesos.parcial_final.services.VehiclesServiceImp;
import com.procesos.parcial_final.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController

public class VehiclesController {
    private final RestTemplate restTemplate;
    @Autowired
    public VehiclesController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Autowired
    VehiclesServiceImp  vehiclesServiceImp;

    @Autowired
    UserService userService;


    @GetMapping("/saveVehicles")
    public ResponseEntity saveVehicles(@RequestHeader(value="Authorization") String token) {
        Map response = new HashMap();
        if(!userService.Auth(token.substring(7))){
            response.put("status", "401");
            response.put("message", "Token invalido");
            return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
        }
        boolean res= vehiclesServiceImp.saveVehicles();

        if(res==true){
            response.put("status", "201");
            response.put("message", "Se registraron todos los carros");
            return new ResponseEntity(response,HttpStatus.CREATED);
        }else{
            response.put("status", "500");
            response.put("message", "No se registraron los carro");
            return new ResponseEntity(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/updateVehicle/{id}")
    public ResponseEntity updateVehicles(@RequestHeader(value="Authorization") String token,@PathVariable Long id,@RequestBody Vehicles vehicles){
        Map response = new HashMap();
        if(!userService.Auth(token.substring(7))){
            response.put("status", "401");
            response.put("message", "Token invalido");
            return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
        }
        Boolean res = vehiclesServiceImp.updateVehicles(id, vehicles);

        if(res==true){
            response.put("status", "200");
            response.put("message", "Se actualizó el carro");
            return new ResponseEntity(response, HttpStatus.OK) ;
        }else{
            response.put("status", "400");
            response.put("message", "No se actualizó el carro");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping(value = "vehicles/{id}")
    public ResponseEntity findVehicleById(@RequestHeader(value="Authorization") String token,@PathVariable Long id){

        Map response = new HashMap();
        try{
            if(!userService.Auth(token.substring(7))){
                response.put("status", "401");
                response.put("message", "Token invalido");
                return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity(vehiclesServiceImp.getVehicles(id), HttpStatus.OK) ;
        }catch(Exception e) {
            response.put("status", "404");
            response.put("message", "No se encontro el carro");
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping(value = "/vehicles" )
    public ResponseEntity Vehicles(@RequestHeader(value="Authorization") String token){

        Map response = new HashMap();
        try{
            System.out.println(token);
            if(!userService.Auth(token.substring(7))){
                response.put("status", "401");
                response.put("message", "Token invalido");
                return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
            }
            if(vehiclesServiceImp.allVehicles().isEmpty()==false){
                response.put("data", vehiclesServiceImp.allVehicles());
            }
            else{
                response.put("data", null);
            }
            return new ResponseEntity(response, HttpStatus.OK) ;
        }catch(Exception e) {
            response.put("status", "404");
            response.put("message", "No hay carros");
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
    }

}
