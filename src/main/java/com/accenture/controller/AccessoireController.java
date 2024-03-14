package com.accenture.controller;

import com.accenture.service.AccessoireService;
import com.accenture.service.dto.AccessoireDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accessoires")
public class AccessoireController {

    private final AccessoireService accessoireService;

    @Autowired
    public AccessoireController(AccessoireService accessoireService) {
        this.accessoireService = accessoireService;
    }

    @GetMapping({"/", ""})
    List<AccessoireDto> accessoires() {
        return accessoireService.getAllAccessoires();
    }

    @GetMapping("/{id}")
    ResponseEntity<AccessoireDto> accessoire(@PathVariable("id") int id) {
        ResponseEntity<AccessoireDto> re;
        AccessoireDto accessoireDto = accessoireService.getAccessoireById(id);
        if (accessoireDto == null)
            re = ResponseEntity.notFound().build();
        else
            re = ResponseEntity.ok(accessoireDto);
        return re;
    }

    @PostMapping
    ResponseEntity<String> ajout(@RequestBody AccessoireDto dto, BindingResult br) {
        ResponseEntity<String> re;
        if (br.hasErrors()) {
            StringBuilder message = new StringBuilder();
            for (Object error : br.getAllErrors()) {
                message.append(error).append(" ");
            }
            re = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(message.toString());
        } else {
            try {
                accessoireService.ajouter(dto);
                re = ResponseEntity.status(HttpStatus.CREATED).build();
            } catch (Exception e) {
                re = ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
        }
        return re;
    }

    @PutMapping("/{id}")
    ResponseEntity<String> modifier(@RequestBody AccessoireDto dto, BindingResult br, @PathVariable("id") int id) {
        ResponseEntity<String> re;
        if (br.hasErrors()) {
            StringBuilder message = new StringBuilder();
            for (Object error : br.getAllErrors()) {
                message.append(error).append(" ");
            }
            re = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(message.toString());
        } else {
            try {
                accessoireService.modifier(dto, id);
                re = ResponseEntity.status(HttpStatus.CREATED).build();
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
            accessoireService.supprimer(id);
            re = ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            re = ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return re;
    }
}
