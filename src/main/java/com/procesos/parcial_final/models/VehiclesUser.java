package com.procesos.parcial_final.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class VehiclesUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "vehiclesId", unique = true)
    private Vehicles vehiclesId;

    @ManyToOne()
    @JoinColumn(name="userId")
    private User userId;
}
