package com.procesos.parcial_final.repository;

import com.procesos.parcial_final.models.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiclesRepository extends JpaRepository <Vehicles, Long> {
}
