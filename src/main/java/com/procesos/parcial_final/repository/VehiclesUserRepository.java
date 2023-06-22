package com.procesos.parcial_final.repository;

import com.procesos.parcial_final.models.VehiclesUser;
import com.procesos.parcial_final.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiclesUserRepository extends JpaRepository<VehiclesUser, Long> {
    List<VehiclesUser> findByUserId (User userId);
}
