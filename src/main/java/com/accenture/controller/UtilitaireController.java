package com.accenture.controller;

import com.accenture.exception.VehiculeException;
import com.accenture.service.UtilitaireService;
import com.accenture.service.dto.UtilitaireDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilitaires")
public class UtilitaireController {

    private final UtilitaireService utilitaireService;

    @Autowired
    public UtilitaireController(UtilitaireService utilitaireService) {
        this.utilitaireService = utilitaireService;
    }

    @GetMapping({"/", ""})
    public List<UtilitaireDto> utilitaires() {
        return utilitaireService.getAllUtilitaires();
    }

    @GetMapping("/{id}")
    ResponseEntity<UtilitaireDto> utilitaire(@PathVariable("id") int id) {
        ResponseEntity<UtilitaireDto> re;
        UtilitaireDto utilitaireDto = utilitaireService.getUtilitaireById(id);
        if (utilitaireDto == null)
            re = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else
            re = ResponseEntity.status(HttpStatus.OK).body(utilitaireDto);
        return re;
    }

    @PostMapping
    ResponseEntity<String> ajout(@RequestBody UtilitaireDto dto, BindingResult br) {
        ResponseEntity<String> re;

        if (br.hasErrors()) {
            StringBuilder message = new StringBuilder();
            for (Object error : br.getAllErrors()) {
                message.append(error).append(" ");
            }
            re = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(message.toString());
        } else {
            try {
                utilitaireService.ajouter(dto);
                re = ResponseEntity.status(HttpStatus.CREATED).build();
            } catch (Exception e) {
                re = ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
        }
        return re;
    }

    @PutMapping("/{id}")
    ResponseEntity<String> ajout(@RequestBody UtilitaireDto dto, BindingResult br, @PathVariable("id") int id) {
        ResponseEntity<String> re;

        if (br.hasErrors()) {
            StringBuilder message = new StringBuilder();
            for (Object error : br.getAllErrors()) {
                message.append(error).append(" ");
            }
            re = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(message.toString());
        } else {
            try {
                utilitaireService.modifier(dto, id);
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
            utilitaireService.supprimer(id);
            re = ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            utilitaireService.desactiver(id);
            re = ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return re;
    }
}
