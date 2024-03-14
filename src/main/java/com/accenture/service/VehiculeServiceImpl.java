package com.accenture.service;

import com.accenture.dal.entity.vehicules.Vehicule;
import com.accenture.dal.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehiculeServiceImpl implements VehiculeService {

    private final VoitureDao voitureDao;
    private final MotoDao motoDao;
    private final VeloDao veloDao;
    private final UtilitaireDao utilitaireDao;
    private final CampingCarDao campingCarDao;
    private final CampingCarService campingCarService;
    private final UtilitaireService utilitaireService;
    private final VoitureService voitureService;
    private final MotoService motoService;
    private final VeloService veloService;

    @Autowired
    public VehiculeServiceImpl(VoitureDao voitureDao, MotoDao motoDao, VeloDao veloDao, UtilitaireDao utilitaireDao, CampingCarDao campingCarDao, CampingCarService campingCarService, UtilitaireService utilitaireService, VoitureService voitureService, MotoService motoService, VeloService veloService) {
        this.voitureDao = voitureDao;
        this.motoDao = motoDao;
        this.veloDao = veloDao;
        this.utilitaireDao = utilitaireDao;
        this.campingCarDao = campingCarDao;
        this.campingCarService = campingCarService;
        this.utilitaireService = utilitaireService;
        this.voitureService = voitureService;
        this.motoService = motoService;
        this.veloService = veloService;
    }
    @Override
    public Vehicule getVehiculeById(int id) {
        Vehicule vehicule = voitureDao.findById(id).orElse(null);
        if (vehicule == null) {
            vehicule = motoDao.findById(id).orElse(null);
        }
        if (vehicule == null) {
            vehicule = veloDao.findById(id).orElse(null);
        }
        if (vehicule == null) {
            vehicule = utilitaireDao.findById(id).orElse(null);
        }
        if (vehicule == null) {
            vehicule = campingCarDao.findById(id).orElse(null);
        }
        return vehicule;
    }

    @Override
    public List<Object> getVehicules() {
        List<Object> listVehicules = new ArrayList<>();
        if (!campingCarService.getAllCampingCars().isEmpty()){
            listVehicules.add("==== CampingCars ====");
            listVehicules.add(campingCarService.getAllCampingCars());
        }
        if (!utilitaireService.getAllUtilitaires().isEmpty()){
            listVehicules.add("==== Utilitaires ====");
            listVehicules.add(utilitaireService.getAllUtilitaires());
        }
        if (!voitureService.getAllVoitures().isEmpty()){
            listVehicules.add("==== Voitures ====");
            listVehicules.add(voitureService.getAllVoitures());
        }
        if (!motoService.getAllMotos().isEmpty()){
            listVehicules.add("==== Motos ====");
            listVehicules.add(motoService.getAllMotos());
        }
        if (!veloService.getAllVelos().isEmpty()){
            listVehicules.add("==== Velos ====");
            listVehicules.add(veloService.getAllVelos());
        }

        return listVehicules;
    }
}
