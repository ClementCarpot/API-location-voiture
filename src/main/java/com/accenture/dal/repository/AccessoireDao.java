package com.accenture.dal.repository;

import com.accenture.dal.entity.Accessoire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessoireDao extends JpaRepository<Accessoire, Integer> {
}
