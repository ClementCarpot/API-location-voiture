package com.accenture.controller;

import com.accenture.dal.entity.vehicules.Vehicule;
import com.accenture.service.LocationService;
import com.accenture.service.dto.LocationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping({"/", ""})
    List<LocationDto> locations() {
        return locationService.getAllLocations();
    }

    @GetMapping("/{id}")
    ResponseEntity<LocationDto> location(@PathVariable("id") int id) {
        ResponseEntity<LocationDto> re;
        LocationDto locationDto = locationService.getLocationById(id);
        if (locationDto == null)
            re = ResponseEntity.notFound().build();
        else
            re = ResponseEntity.ok(locationDto);
        return re;
    }

    @GetMapping("/date/{dateDebut}/{dateFin}")
    List<Vehicule> locations(@PathVariable("dateDebut") LocalDate dateDebut, @PathVariable("dateFin") LocalDate dateFin) {
        List<LocationDto> locationsDto = locationService.getLocationsBetweenDateDebutAndDateFin(dateDebut, dateFin);
        return locationsDto.stream()
                .map(LocationDto::vehicule)
                .toList();
    }

    @GetMapping("/{type}/date/{dateDebut}/{dateFin}")
    List<Vehicule> locations(@PathVariable("type") String type, @PathVariable("dateDebut") LocalDate dateDebut, @PathVariable("dateFin") LocalDate dateFin){
        List<LocationDto> locationsDto = locationService.getLocationsByDateDebutNotAndDateFinNot(dateDebut, dateFin);
        return locationsDto.stream()
                .map(LocationDto::vehicule)
                .filter(v -> v.getClass().getSimpleName().equalsIgnoreCase(type))
                .toList();
    }

    @GetMapping("/{type}/{typeVehicule}/date/{dateDebut}/{dateFin}")
    List<Vehicule> locations(@PathVariable("type") String type, @PathVariable("typeVehicule") String typeVehicule, @PathVariable("dateDebut") LocalDate dateDebut, @PathVariable("dateFin") LocalDate dateFin){
        List<LocationDto> locationsDto = locationService.getLocationsByDateDebutNotAndDateFinNot(dateDebut, dateFin);
        return locationsDto.stream()
                .map(LocationDto::vehicule)
                .filter(v -> v.getClass().getSimpleName().equalsIgnoreCase(type))
                .filter(v -> v.getClass().getSimpleName().equalsIgnoreCase(typeVehicule))
                .toList();
    }



    @PostMapping
    ResponseEntity<String> ajout(@RequestBody LocationDto dto, BindingResult br) {
        ResponseEntity<String> re;
        if (br.hasErrors()) {
            StringBuilder message = new StringBuilder();
            for (Object error : br.getAllErrors()) {
                message.append(error).append(" ");
            }
            re = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(message.toString());
        } else {
            try {
                locationService.ajouter(dto);
                re = ResponseEntity.status(HttpStatus.CREATED).build();
            } catch (Exception e) {
                re = ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
        }
        return re;
    }

    @PutMapping("/{id}")
    ResponseEntity<String> modifier(@RequestBody LocationDto dto, BindingResult br, @PathVariable("id") int id) {
        ResponseEntity<String> re;
        if (br.hasErrors()) {
            StringBuilder message = new StringBuilder();
            for (Object error : br.getAllErrors()) {
                message.append(error).append(" ");
            }
            re = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(message.toString());
        } else {
            try {
                locationService.modifier(dto, id);
                re = ResponseEntity.status(HttpStatus.OK).build();
            } catch (Exception e) {
                re = ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
        }
        return re;
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> supprimer(@PathVariable("id") int id) {
        ResponseEntity<String> re;
        try {
            locationService.supprimer(id);
            re = ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            re = ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return re;
    }

}
