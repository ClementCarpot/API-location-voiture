package com.accenture.dal.repository;

import com.accenture.dal.entity.vehicules.Moto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotoDao extends JpaRepository<Moto, Integer> {
}
