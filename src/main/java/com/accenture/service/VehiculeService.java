package com.accenture.service;

import com.accenture.dal.entity.vehicules.Vehicule;

import java.util.List;

public interface VehiculeService {
    Vehicule getVehiculeById(int id);
    List<Object> getVehicules();
}
