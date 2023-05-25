package com.procesos.segundo_previo.controllers;

import com.procesos.segundo_previo.models.User;
import com.procesos.segundo_previo.models.VehicleUser;
import com.procesos.segundo_previo.services.UserService;
import com.procesos.segundo_previo.services.VehicleUserService;
import com.procesos.segundo_previo.utils.ApiResponse;
import com.procesos.segundo_previo.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/vehicleUser")
public class VehicleUserController {
    @Autowired
    private VehicleUserService vehicleUserService;
    @Autowired
    private UserService userService;
    private ApiResponse apiResponse;
    Map data = new HashMap<>();

    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@RequestHeader(value = "Authorization") String token, User user){
        try {
            if(!userService.Auth(token.substring(7))) {
                apiResponse = new ApiResponse(Constants.INVALID_TOKEN, vehicleUserService.getVehicleUser(user));
                return new ResponseEntity(apiResponse, HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity(vehicleUserService.getVehicleUser(user), HttpStatus.OK);
        }catch(Exception e){
            apiResponse = new ApiResponse(Constants.VEHICLE_NOT_FOUND, "");
            return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity saveVehicleUser(@RequestHeader(value = "Authorization") String token, @RequestBody VehicleUser vehicleUser){
        if(!userService.Auth(token.substring(7))){
            apiResponse = new ApiResponse(Constants.INVALID_TOKEN, "");
            return new ResponseEntity(apiResponse, HttpStatus.UNAUTHORIZED);
        }
        Boolean vehicleResp = vehicleUserService.createVehicleUser(vehicleUser);
        if(vehicleResp == true){
            apiResponse = new ApiResponse(Constants.REGISTER_CREATED, "");
            return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
        }
        apiResponse = new ApiResponse(Constants.REGISTER_BAD, "");
        return new ResponseEntity(apiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "")
    public ResponseEntity findAll(@RequestHeader(value = "Authorization")String token){
        try {
            if(!userService.Auth(token.substring(7))) {
                apiResponse = new ApiResponse(Constants.INVALID_TOKEN, "");
                return new ResponseEntity(apiResponse, HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity(vehicleUserService.allVehicleUser(), HttpStatus.OK);
        }catch(Exception e){
            apiResponse = new ApiResponse(Constants.REGISTER_NOT_FOUND, "");
            return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity updateVehicleUser(@RequestHeader(value = "Authorization")String token,@PathVariable Long id, @RequestBody VehicleUser vehicleUser){
        if (!userService.Auth(token.substring(7))){
            apiResponse = new ApiResponse(Constants.INVALID_TOKEN, "");
            return new ResponseEntity(apiResponse, HttpStatus.UNAUTHORIZED);
        }
        Boolean vehicleResp = vehicleUserService.updateVehicleUser(id,vehicleUser);
        if(vehicleResp == true){
            apiResponse = new ApiResponse(Constants.REGISTER_UPDATED, "");
            return new ResponseEntity(apiResponse, HttpStatus.OK);
        }else {
            apiResponse = new ApiResponse(Constants.UPDATE_ERROR, "");
            return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
