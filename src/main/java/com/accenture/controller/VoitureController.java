package com.accenture.controller;

import com.accenture.exception.VehiculeException;
import com.accenture.service.VoitureService;
import com.accenture.service.dto.VoitureDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voitures")
public class VoitureController {

    private final VoitureService voitureService;

    @Autowired
    private VoitureController(VoitureService voitureService) {
        this.voitureService = voitureService;
    }

    @GetMapping({"/", ""})
    List<VoitureDto> voitures() {
        return voitureService.getAllVoitures();
    }

    @GetMapping("/{id}")
    ResponseEntity<VoitureDto> voiture(@PathVariable("id") int id) {
        ResponseEntity<VoitureDto> re;
        VoitureDto voitureDto = voitureService.getVoitureById(id);
        if (voitureDto == null)
            re = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else
            re = ResponseEntity.status(HttpStatus.OK).body(voitureDto);
        return re;
    }

    @PostMapping
    ResponseEntity<String> ajout(@RequestBody VoitureDto dto, BindingResult br) {
        ResponseEntity<String> re;

        if (br.hasErrors()) {
            StringBuilder message = new StringBuilder();
            for (Object error : br.getAllErrors()) {
                message.append(error).append(" ");
            }
            re = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(message.toString());
        } else {
            try {
                voitureService.ajouter(dto);
                re = ResponseEntity.status(HttpStatus.CREATED).build();
            } catch (Exception e) {
                re = ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
        }
        return re;
    }

    @PutMapping("/{id}")
    ResponseEntity<String> modifier(@RequestBody VoitureDto dto, BindingResult br, @PathVariable("id") int id) {
        ResponseEntity<String> re;

        if (br.hasErrors()) {
            StringBuilder message = new StringBuilder();
            for (Object error : br.getAllErrors()) {
                message.append(error).append(" ");
            }
            re = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(message.toString());
        } else {
            try {
                voitureService.modifier(dto, id);
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
            voitureService.supprimer(id);
            re = ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            voitureService.desactiver(id);
            re = ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return re;
    }
}
