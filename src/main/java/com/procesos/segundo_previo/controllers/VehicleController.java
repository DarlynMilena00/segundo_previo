package com.procesos.segundo_previo.controllers;

import com.procesos.segundo_previo.models.Vehicle;
import com.procesos.segundo_previo.services.UserService;
import com.procesos.segundo_previo.services.VehicleService;
import com.procesos.segundo_previo.utils.ApiResponse;
import com.procesos.segundo_previo.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private UserService userService;
    private ApiResponse apiResponse;
    Map data = new HashMap<>();

    /*Metodo encontrar vehiculos por ID*/
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@RequestHeader(value = "Authorization") String token, @PathVariable Long id){
        try {
            if(!userService.Auth(token.substring(7))) {
                apiResponse = new ApiResponse(Constants.INVALID_TOKEN, vehicleService.getVehicle(id));
                return new ResponseEntity(apiResponse, HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity(vehicleService.getVehicle(id), HttpStatus.OK);
        }catch(Exception e){
            apiResponse = new ApiResponse(Constants.VEHICLE_NOT_FOUND, "");
            return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
        }
    }

    /*Metodo crear vehiculos*/
    @PostMapping(value = "")
    public ResponseEntity saveVehicles(@RequestHeader(value = "Authorization") String token){
        if(!userService.Auth(token.substring(7))){
            apiResponse = new ApiResponse(Constants.INVALID_TOKEN, "");
            return new ResponseEntity(apiResponse, HttpStatus.UNAUTHORIZED);
        }
        Boolean vehicleResp = vehicleService.createVehicle();
        if(vehicleResp == true){
            apiResponse = new ApiResponse(Constants.REGISTER_CREATED, "");
            return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
        }
        apiResponse = new ApiResponse(Constants.REGISTER_BAD, "");
        return new ResponseEntity(apiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*Metodo consultar todos los vehiculos*/
    @GetMapping(value = "")
    public ResponseEntity findAll(@RequestHeader(value = "Authorization")String token){
        try {
            if(!userService.Auth(token.substring(7))) {
                apiResponse = new ApiResponse(Constants.INVALID_TOKEN, "");
                return new ResponseEntity(apiResponse, HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity(vehicleService.allVehicle(), HttpStatus.OK);
        }catch(Exception e){
            apiResponse = new ApiResponse(Constants.REGISTER_NOT_FOUND, "");
            return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
        }
    }

    /*Metodo actualizar vehiculo*/
    @PutMapping(value = "/{id}")
    public ResponseEntity updateVehicle(@RequestHeader(value = "Authorization")String token,@PathVariable Long id, @RequestBody Vehicle vehicle){
        if (!userService.Auth(token.substring(7))){
            apiResponse = new ApiResponse(Constants.INVALID_TOKEN, "");
            return new ResponseEntity(apiResponse, HttpStatus.UNAUTHORIZED);
        }
        Boolean vehicleResp = vehicleService.updateVehicle(id,vehicle);
        if(vehicleResp == true){
            apiResponse = new ApiResponse(Constants.REGISTER_UPDATED, "");
            return new ResponseEntity(apiResponse, HttpStatus.OK);
        }else {
            apiResponse = new ApiResponse(Constants.UPDATE_ERROR, "");
            return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
