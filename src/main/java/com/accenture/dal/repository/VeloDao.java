package com.accenture.dal.repository;

import com.accenture.dal.entity.vehicules.Velo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeloDao extends JpaRepository<Velo, Integer> {
}
