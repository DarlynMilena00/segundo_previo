package com.procesos.segundo_previo.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class VehicleUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name ="vehicleId", unique = true)
    private Vehicle vehicleId;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "userId")
    private User userId;
}
