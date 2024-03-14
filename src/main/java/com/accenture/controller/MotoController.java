package com.accenture.controller;

import com.accenture.exception.VehiculeException;
import com.accenture.service.MotoService;
import com.accenture.service.dto.MotoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motos")
public class MotoController {

    private final MotoService motoService;

    @Autowired
    public MotoController(MotoService motoService) {
        this.motoService = motoService;
    }

    @GetMapping({"/", ""})
    List<MotoDto> motos() {
        return motoService.getAllMotos();
    }

    @GetMapping("/{id}")
    ResponseEntity<MotoDto> moto(@PathVariable("id") int id) {
        ResponseEntity<MotoDto> re;
        MotoDto motoDto = motoService.getMotoById(id);
        if (motoDto == null)
            re = ResponseEntity.notFound().build();
        else
            re = ResponseEntity.ok(motoDto);
        return re;
    }

    @PostMapping
    ResponseEntity<String> ajout(@RequestBody MotoDto dto, BindingResult br) {
        ResponseEntity<String> re;
        if (br.hasErrors()) {
            StringBuilder message = new StringBuilder();
            for (Object error : br.getAllErrors()) {
                message.append(error).append(" ");
            }
            re = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(message.toString());
        } else {
            try {
                motoService.ajouter(dto);
                re = ResponseEntity.status(HttpStatus.CREATED).build();
            } catch (Exception e) {
                re = ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
        }
        return re;
    }

    @PutMapping("/{id}")
    ResponseEntity<String> ajout(@RequestBody MotoDto dto, BindingResult br, @PathVariable("id") int id) {
        ResponseEntity<String> re;
        if (br.hasErrors()) {
            StringBuilder message = new StringBuilder();
            for (Object error : br.getAllErrors()) {
                message.append(error).append(" ");
            }
            re = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(message.toString());
        } else {
            try {
                motoService.modifier(dto, id);
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
            motoService.supprimer(id);
            re = ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            motoService.desactiver(id);
            re = ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return re;
    }
}
