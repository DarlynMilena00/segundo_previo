package com.procesos.segundo_previo.controllers;

import com.procesos.segundo_previo.models.Vehicles;
import com.procesos.segundo_previo.models.VehiclesApi;
import com.procesos.segundo_previo.services.VehiclesService;
import com.procesos.segundo_previo.services.VehiclesServiceImp;
import com.procesos.segundo_previo.utils.ApiResponse;
import com.procesos.segundo_previo.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class VehiclesController {
    private final RestTemplate restTemplate;
    @Autowired
    public VehiclesController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Autowired
    private VehiclesService vehiclesService;
    private ApiResponse apiResponse;
    Map data = new HashMap<>();

    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        try {
            apiResponse = new ApiResponse(Constants.REGISTER_FOUND, vehiclesService.getVehicles(id));
            return new ResponseEntity(apiResponse, HttpStatus.OK);
        }catch(Exception e){
            apiResponse = new ApiResponse(Constants.REGISTER_NOT_FOUND, "");
            return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity createVehicles(@RequestBody Vehicles vehicles){
        Boolean userResp = vehiclesService.createVehicles(vehicles);

        if(userResp == true){
            apiResponse = new ApiResponse(Constants.REGISTER_CREATED, "");
            return new ResponseEntity(apiResponse, HttpStatus.CREATED);
        }
        apiResponse = new ApiResponse(Constants.REGISTER_BAD, vehicles);
        return new ResponseEntity(apiResponse,HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "")
    public ResponseEntity findAll(){
        try {
            apiResponse = new ApiResponse(Constants.REGISTER_LIST, vehiclesService.allVehicles());
            return new ResponseEntity(apiResponse, HttpStatus.OK);
        }catch(Exception e){
            apiResponse = new ApiResponse(Constants.REGISTER_NOT_FOUND, "");
            return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateVehicles(@PathVariable Long id, @RequestBody Vehicles vehicles){
        Boolean userResp = vehiclesService.updateVehicles(id, vehicles);
        if (userResp){
            apiResponse = new ApiResponse(Constants.REGISTER_UPDATED, vehiclesService.updateVehicles(id, vehicles));
            return new ResponseEntity(apiResponse, HttpStatus.CREATED);
        }
        apiResponse = new ApiResponse(Constants.REGISTER_NOT_FOUND, "");
        return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
