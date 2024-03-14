package com.accenture.service.mapper;

import com.accenture.dal.entity.Accessoire;
import com.accenture.dal.entity.EtatLocation;
import com.accenture.dal.entity.Location;
import com.accenture.dal.entity.utilisateurs.Client;
import com.accenture.dal.entity.vehicules.Vehicule;
import com.accenture.service.dto.LocationDto;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;
import java.time.LocalDate;
import java.util.List;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-03-01T11:02:15+0100",
        comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class LocationMapperImpl implements LocationMapper {
    @Override
    public Location locationDtoToLocation(LocationDto dto) {
        if (dto == null) {
            return null;
        }

        Location location = new Location();

        location.setId(dto.id());
        location.setClient(dto.client());
        location.setVehicule(dto.vehicule());
        location.setAccessoires(dto.accessoires());
        location.setDateDebut(dto.dateDebut());
        location.setDateFin(dto.dateFin());
        location.setNombreKmParcourus(dto.nombreKmParcourus());
        location.setMontantLocation(dto.montantLocation());
        location.setDateValidation(dto.dateValidation());
        location.setEtatLocation(dto.etatLocation());

        return location;
    }

    @Override
    public LocationDto locationToLocationDto(Location entity) {
        if (entity == null) {
            return null;
        }

        int id = 0;
        Client client = null;
        Vehicule vehicule = null;
        List<Accessoire> accessoires = null;
        LocalDate dateDebut = null;
        LocalDate dateFin = null;
        int nombreKmParcourus = 0;
        double montantLocation = 0.0d;
        LocalDate dateValidation = null;
        EtatLocation etatLocation = null;

        id = entity.getId();
        client = entity.getClient();
        vehicule = entity.getVehicule();
        accessoires = entity.getAccessoires();
        dateDebut = entity.getDateDebut();
        dateFin = entity.getDateFin();
        nombreKmParcourus = entity.getNombreKmParcourus();
        montantLocation = entity.getMontantLocation();
        dateValidation = entity.getDateValidation();
        etatLocation = entity.getEtatLocation();

        LocationDto locationDto = new LocationDto(id, client, vehicule, accessoires, dateDebut, dateFin, nombreKmParcourus, montantLocation, dateValidation, etatLocation);

        return locationDto;
    }
}
