package com.accenture.controller;

import com.accenture.exception.UtilisateurException;
import com.accenture.service.ClientService;
import com.accenture.service.dto.ClientDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;


    @Autowired
    private ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping({"/", ""})
    List<ClientDto> clients() {
        return clientService.getAllClients();
    }

    @GetMapping("/actifs")
    List<ClientDto> clientsActifs() {
        return clientService.getAllClientsActifs();
    }

    @GetMapping("/{id}")
    ResponseEntity<ClientDto> client(@PathVariable("id") int id) {
        ResponseEntity<ClientDto> re;
        ClientDto clientDto = clientService.getClientById(id);
        if (clientDto == null)
            re = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else
            re = ResponseEntity.status(HttpStatus.OK).body(clientDto);
        return re;
    }

    @PostMapping
    ResponseEntity<String> ajout(@Valid @RequestBody ClientDto dto, BindingResult br) {
        ResponseEntity<String> re;

        LocalDate dateNaissance = dto.dateNaissance();
        int age = LocalDate.now().getYear() - dateNaissance.getYear();

        if (age < 18) {
            re = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Le client doit être majeur");
            return re;
        }

        if (br.hasErrors()) {
            StringBuilder message = new StringBuilder();
            for (ObjectError error : br.getAllErrors()) {
                message.append(error.getDefaultMessage()).append(" ");
            }
            re = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(message.toString());
        } else {
            try {
                clientService.ajouter(dto);
                re = ResponseEntity.status(HttpStatus.CREATED).build();
            } catch (Exception e) {
                re = ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
        }
        return re;
    }

    @PutMapping("/{id}")
    ResponseEntity<String> modifier(@RequestBody ClientDto dto, @PathVariable("id") int id, BindingResult br) {
        ResponseEntity<String> re;

        if (br.hasErrors()) {
            StringBuilder message = new StringBuilder();
            for (Object error : br.getAllErrors()) {
                message.append(error).append(" ");
            }
            re = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(message.toString());
        } else {
            try {
                clientService.modifier(dto, id);
                re = ResponseEntity.status(HttpStatus.CREATED).build();
            } catch (Exception e) {
                re = ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
        }
        return re;
    }

    @DeleteMapping
    ResponseEntity<String> delete(@RequestBody ClientDto loginClient) throws UtilisateurException {
        ClientDto client = clientService.getClientByEmailIgnoreCaseAndMotDePasse(loginClient.email(), loginClient.motDePasse());
        ResponseEntity<String> re;
        if (client != null) {
            try {
                clientService.supprimer(client);
                re = ResponseEntity.status(HttpStatus.OK).build();
            } catch (Exception e) {
                clientService.desactiver(client);
                re = ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
        } else {
            re = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return re;
    }
}
