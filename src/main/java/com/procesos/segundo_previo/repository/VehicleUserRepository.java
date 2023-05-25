package com.procesos.segundo_previo.repository;

import com.procesos.segundo_previo.models.User;
import com.procesos.segundo_previo.models.VehicleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleUserRepository extends JpaRepository<VehicleUser, Long> {
    List<VehicleUser> findUserById (User userId);
}
