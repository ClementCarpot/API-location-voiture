package com.accenture.controller;

import com.accenture.exception.VehiculeException;
import com.accenture.service.VeloService;
import com.accenture.service.dto.VeloDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/velos")
public class VeloController {

    private final VeloService veloService;

    @Autowired
    public VeloController(VeloService veloService) {
        this.veloService = veloService;
    }

    @GetMapping({"/", ""})
    List<VeloDto> velos() {
        return veloService.getAllVelos();
    }

    @GetMapping("/{id}")
    ResponseEntity<VeloDto> velo(@PathVariable("id") int id) {
        ResponseEntity<VeloDto> re;
        VeloDto veloDto = veloService.getVeloById(id);
        if (veloDto == null)
            re = ResponseEntity.notFound().build();
        else
            re = ResponseEntity.ok(veloDto);
        return re;
    }

    @PostMapping
    ResponseEntity<String> ajout(@RequestBody VeloDto dto, BindingResult br) {
        ResponseEntity<String> re;
        if (br.hasErrors()) {
            StringBuilder message = new StringBuilder();
            for (Object error : br.getAllErrors()) {
                message.append(error).append(" ");
            }
            re = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(message.toString());
        } else {
            try {
                veloService.ajouter(dto);
                re = ResponseEntity.status(HttpStatus.CREATED).build();
            } catch (Exception e) {
                re = ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
        }
        return re;
    }

    @PutMapping("/{id}")
    ResponseEntity<String> ajout(@RequestBody VeloDto dto, BindingResult br, @PathVariable("id") int id) {
        ResponseEntity<String> re;
        if (br.hasErrors()) {
            StringBuilder message = new StringBuilder();
            for (Object error : br.getAllErrors()) {
                message.append(error).append(" ");
            }
            re = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(message.toString());
        } else {
            try {
                veloService.modifier(dto, id);
                re = ResponseEntity.status(HttpStatus.CREATED).build();
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
            veloService.supprimer(id);
            re = ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            veloService.desactiver(id);
            re = ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return re;
    }
}
