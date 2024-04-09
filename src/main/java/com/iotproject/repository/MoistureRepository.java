package com.iotproject.repository;

import com.iotproject.entity.Moisture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoistureRepository extends JpaRepository<Moisture, Long> {
}
