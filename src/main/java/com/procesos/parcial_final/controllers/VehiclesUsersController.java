package com.procesos.parcial_final.controllers;

import com.procesos.parcial_final.models.VehiclesUser;
import com.procesos.parcial_final.models.User;
import com.procesos.parcial_final.services.VehiclesUsersService;
import com.procesos.parcial_final.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class VehiclesUsersController {
    @Autowired
    VehiclesUsersService vehiclesUsersService;
    @Autowired
    UserService userService;

    @PostMapping("/saveCarsUser")
    public ResponseEntity saveCarsUsers(@RequestHeader(value="Authorization") String token, @RequestBody VehiclesUser vehiclesUser) {
        Map response = new HashMap();
        if(!userService.Auth(token.substring(7))){
            response.put("status", "401");
            response.put("message", "Token invalido");
            return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
        }
        boolean res= vehiclesUsersService.saveCarsUsers(vehiclesUser);

        if(res==true){
            response.put("status", "201");
            response.put("message", "Se registraron todos los carros");
            return new ResponseEntity(response,HttpStatus.CREATED);
        }else {
            response.put("status", "500");
            response.put("message", "No se registraron los carro");
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping(value = "/updateCarsUser/{id}")
    public ResponseEntity updateCarsUser(@RequestHeader(value="Authorization") String token,@PathVariable Long id,@RequestBody VehiclesUser vehiclesUser){
        Map response = new HashMap();
        if(!userService.Auth(token.substring(7))){
            response.put("status", "401");
            response.put("message", "Token invalido");
            return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
        }
        Boolean res = vehiclesUsersService.updateCarsUsers(id, vehiclesUser);

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



    @PostMapping(value = "/carsUser")
    public ResponseEntity findCarsUserById(@RequestHeader(value="Authorization") String token,@RequestBody User user){
        System.out.println(user.getId());
        Map response = new HashMap();
        try{
            if(!userService.Auth(token.substring(7))) {
                response.put("status", "401");
                response.put("message", "Token invalido");
                return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
            }
            response.put("status", "201");
            if(vehiclesUsersService.getCarsUsers(user).isEmpty()==false) {
                response.put("data", vehiclesUsersService.getCarsUsers(user));
            }else {
                response.put("data", null);
            }
            return new ResponseEntity(response, HttpStatus.OK);
        }catch(Exception e) {
            response.put("status", "404");
            response.put("message", "No se encontro el carro");
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping(value = "/carsUsersAll" )
    public ResponseEntity CarsUser(@RequestHeader(value="Authorization") String token){

        Map response = new HashMap();
        try{
            if(!userService.Auth(token.substring(7))){
                response.put("status", "401");
                response.put("message", "Token invalido");
                return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity(vehiclesUsersService.allCarsUsers(), HttpStatus.OK) ;
        }catch(Exception e) {
            response.put("status", "404");
            response.put("message", "No hay carros");
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
    }

}
