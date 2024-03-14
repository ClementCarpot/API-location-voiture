package com.accenture.controller;

import com.accenture.exception.VehiculeException;
import com.accenture.service.CampingCarService;
import com.accenture.service.dto.CampingCarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campingCars")
public class CampingCarController {

    private final CampingCarService campingCarService;

    @Autowired
    public CampingCarController(CampingCarService campingCarService) {
        this.campingCarService = campingCarService;
    }

    @GetMapping({"/", ""})
    public List<CampingCarDto> campingCars() {
        return campingCarService.getAllCampingCars();
    }

    @GetMapping("/{id}")
    ResponseEntity<CampingCarDto> campingCar(@PathVariable("id") int id) {
        ResponseEntity<CampingCarDto> re;
        CampingCarDto campingCarDto = campingCarService.getCampingCarById(id);
        if (campingCarDto == null)
            re = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else
            re = ResponseEntity.status(HttpStatus.OK).body(campingCarDto);
        return re;
    }

    @PostMapping
    ResponseEntity<String> ajout(@RequestBody CampingCarDto dto, BindingResult br) {
        ResponseEntity<String> re;

        if (br.hasErrors()) {
            StringBuilder message = new StringBuilder();
            for (Object error : br.getAllErrors()) {
                message.append(error).append(" ");
            }
            re = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(message.toString());
        } else {
            try {
                campingCarService.ajouter(dto);
                re = ResponseEntity.status(HttpStatus.CREATED).build();
            } catch (Exception e) {
                re = ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
        }
        return re;
    }

    @PutMapping("/{id}")
    ResponseEntity<String> modifier(@RequestBody CampingCarDto dto, BindingResult br, @PathVariable("id") int id) {
        ResponseEntity<String> re;

        if (br.hasErrors()) {
            StringBuilder message = new StringBuilder();
            for (Object error : br.getAllErrors()) {
                message.append(error).append(" ");
            }
            re = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(message.toString());
        } else {
            try {
                campingCarService.modifier(dto, id);
                re = ResponseEntity.status(HttpStatus.OK).build();
            } catch (Exception e) {
                re = ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
        }
        return re;
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable("id") int id) throws VehiculeException {
        ResponseEntity<String> re;
        try {
            campingCarService.supprimer(id);
            re = ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            campingCarService.desactiver(id);
            re = ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return re;
    }

}
