package com.accenture.service;

import com.accenture.dal.entity.Location;
import com.accenture.dal.entity.vehicules.Vehicule;
import com.accenture.dal.repository.LocationDao;
import com.accenture.exception.LocationException;
import com.accenture.service.dto.LocationDto;
import com.accenture.service.mapper.LocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationDao locationDao;

    private final LocationMapper locationMapper;

    private final VehiculeService vehiculeService;

    @Autowired
    public LocationServiceImpl(LocationDao locationDao, LocationMapper locationMapper, VehiculeService vehiculeService) {
        this.locationDao = locationDao;
        this.locationMapper = locationMapper;
        this.vehiculeService = vehiculeService;
    }

    @Override
    public void ajouter(LocationDto locationDto) throws LocationException {

        if (locationDto == null)
            throw new LocationException("La location est nulle");
        if (locationDto.dateDebut() == null)
            throw new LocationException("La date de début est obligatoire");
        if (locationDto.dateFin() == null)
            throw new LocationException("La date de fin est obligatoire");
        if (locationDto.dateDebut().isAfter(locationDto.dateFin()))
            throw new LocationException("La date de début doit être avant la date de fin");
        if (locationDto.client() == null)
            throw new LocationException("Le client est obligatoire");
        if (locationDto.vehicule() == null)
            throw new LocationException("Le véhicule est obligatoire");
        if (locationDto.etatLocation() == null)
            throw new LocationException("L'état de la location est obligatoire");

        Vehicule vehicule = vehiculeService.getVehiculeById(locationDto.vehicule().getId());

        if (vehicule == null)
            throw new LocationException("Le véhicule n'existe pas");

        int nombreJoursLocation = locationDto.dateDebut().until(locationDto.dateFin()).getDays() + 1;
        double tarifJournalierVehicule = vehicule.getTarifJournalier();

        double montantLocation = nombreJoursLocation * tarifJournalierVehicule;

        Location location = locationMapper.locationDtoToLocation(locationDto);

        location.setMontantLocation(montantLocation);

        locationDao.save(location);


    }

    @Override
    public void modifier(LocationDto locationDto, int id) throws LocationException {
        if (locationDto == null)
            throw new LocationException("La location est nulle");

        Optional<Location> opt = locationDao.findById(id);
        if (opt.isEmpty())
            throw new LocationException("La location n'existe pas");

        Location location = opt.get();

        if (locationDto.dateDebut() != null)
            location.setDateDebut(locationDto.dateDebut());
        if (locationDto.dateFin() != null)
            location.setDateFin(locationDto.dateFin());
        if (locationDto.client() != null)
            location.setClient(locationDto.client());
        if (locationDto.vehicule() != null) {
            location.setVehicule(locationDto.vehicule());
            Vehicule vehicule = vehiculeService.getVehiculeById(locationDto.vehicule().getId());

            double montantLocation = getMontantLocation(locationDto, vehicule);

            location.setMontantLocation(montantLocation);
        }
        if (locationDto.etatLocation() != null)
            location.setEtatLocation(locationDto.etatLocation());
        if (locationDto.dateValidation() != null)
            location.setDateValidation(locationDto.dateValidation());



        locationDao.save(location);
    }

    @Override
    public void supprimer(int id) {
        locationDao.deleteById(id);
    }

    @Override
    public void supprimer(LocationDto locationDto) {
        locationDao.delete(locationMapper.locationDtoToLocation(locationDto));
    }

    @Override
    public LocationDto getLocationById(int id) {
        Optional<Location> opt = locationDao.findById(id);
        return opt.map(locationMapper::locationToLocationDto).orElse(null);
    }

    @Override
    public List<LocationDto> getAllLocations() {
        return locationDao.findAll()
                .stream()
                .map(locationMapper::locationToLocationDto)
                .toList();
    }

    @Override
    public List<LocationDto> getLocationsByDateDebutAndDateFin(LocalDate dateDebut, LocalDate dateFin) {
        return locationDao.findByDateDebutAndDateFin(dateDebut, dateFin)
                .stream()
                .map(locationMapper::locationToLocationDto)
                .toList();
    }

    @Override
    public List<LocationDto> getLocationsByDateDebutNotAndDateFinNot(LocalDate dateDebut, LocalDate dateFin) {
        return locationDao.findByDateDebutNotAndDateFinNot(dateDebut, dateFin)
                .stream()
                .map(locationMapper::locationToLocationDto)
                .toList();
    }

    @Override
    public List<LocationDto> getLocationsBetweenDateDebutAndDateFin(LocalDate dateDebut, LocalDate dateFin) {
        return locationDao.findLocationsBetweenDateDebutAndDateFin(dateDebut, dateFin)
                .stream()
                .map(locationMapper::locationToLocationDto)
                .toList();
    }

    private static double getMontantLocation(LocationDto locationDto, Vehicule vehicule) throws LocationException {
        if (locationDto.dateDebut() == null)
            throw new LocationException("La date de début est obligatoire");
        if (locationDto.dateFin() == null)
            throw new LocationException("La date de fin est obligatoire");
        int nombreJoursLocation = locationDto.dateDebut().until(locationDto.dateFin()).getDays() + 1;
        double tarifJournalierVehicule = vehicule.getTarifJournalier();
        return nombreJoursLocation * tarifJournalierVehicule;
    }
}
