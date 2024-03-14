package com.accenture.controller;

import com.accenture.exception.UtilisateurException;
import com.accenture.service.AdministrateurService;
import com.accenture.service.dto.AdministrateurDto;
import com.accenture.service.dto.AdministrateurResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdministrateurController {


    private final AdministrateurService administrateurService;

    @Autowired
    private AdministrateurController(AdministrateurService administrateurService) {
        this.administrateurService = administrateurService;
    }

    @GetMapping({"/", ""})
    List<AdministrateurResponseDto> administrateurs() {
        return administrateurService.getAllAdministrateursWithoutPassword();
    }

    @GetMapping("/{id}")
    ResponseEntity<AdministrateurResponseDto> administrateur(@PathVariable("id") int id) {
        ResponseEntity<AdministrateurResponseDto> re;
        AdministrateurResponseDto administrateurDto = administrateurService.getAdministrateurByIdWithoutPassword(id);
        if (administrateurDto == null)
            re = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else
            re = ResponseEntity.status(HttpStatus.OK).body(administrateurDto);
        return re;
    }

    @PostMapping
    ResponseEntity<String> ajout(@Valid @RequestBody AdministrateurDto dto, BindingResult br) {
        ResponseEntity<String> re;
        if (br.hasErrors()) {
            StringBuilder message = new StringBuilder();
            for (Object error : br.getAllErrors()) {
                message.append(error).append(" ");
            }
            re = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(message.toString());
        } else {
            try {
                administrateurService.ajouter(dto);
                re = ResponseEntity.status(HttpStatus.CREATED).build();
            } catch (Exception e) {
                re = ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
        }
        return re;
    }

    @PutMapping("/{id}")
    ResponseEntity<String> modifier(@Valid @RequestBody AdministrateurDto loginAdministrateur, BindingResult br, @PathVariable("id") int id){
        ResponseEntity<String> re;
        if (br.hasErrors()) {
            StringBuilder message = new StringBuilder();
            for (ObjectError error : br.getAllErrors()) {
                message.append(error.getDefaultMessage()).append(" ");
            }
            re = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(message.toString());
        } else {
            try {
                    administrateurService.modifier(loginAdministrateur, id);
                    re = ResponseEntity.status(HttpStatus.CREATED).build();
            } catch (Exception e) {
                re = ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
        }
        return re;
    }

    @DeleteMapping
    ResponseEntity<String> delete(@RequestBody AdministrateurDto loginAdministrateur) throws UtilisateurException {
        AdministrateurDto administrateur = administrateurService.getAdministrateurByEmailIgnoreCaseAndMotDePasse(loginAdministrateur.email(), loginAdministrateur.motDePasse());
        ResponseEntity<String> re;
        if (administrateur != null) {
            try {
                administrateurService.supprimer(administrateur);
                re = ResponseEntity.status(HttpStatus.OK).build();
            } catch (Exception e) {
                administrateurService.desactiver(administrateur);
                re = ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
        } else {
            re = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return re;
    }
}
