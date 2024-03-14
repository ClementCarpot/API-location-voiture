package com.accenture.dal.repository;

import com.accenture.dal.entity.vehicules.CampingCar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampingCarDao extends JpaRepository<CampingCar, Integer> {
}
