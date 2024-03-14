package com.accenture.controller;

import com.accenture.service.dto.AdministrateurResponseDto;
import com.accenture.service.dto.ClientResponseDto;
import com.accenture.service.AdministrateurService;
import com.accenture.service.ClientService;
import com.accenture.service.dto.AdministrateurDto;
import com.accenture.service.dto.ClientDto;
import com.accenture.service.mapper.AdministrateurMapper;
import com.accenture.service.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/connexion")
public class ConnexionController {

    private final AdministrateurService administrateurService;
    private final ClientService clientService;
    private final ClientMapper clientMapper;
    private final AdministrateurMapper administrateurMapper;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private ConnexionController(AdministrateurService administrateurService, ClientService clientService, ClientMapper clientMapper, AdministrateurMapper administrateurMapper, PasswordEncoder passwordEncoder) {
        this.administrateurService = administrateurService;
        this.clientService = clientService;
        this.clientMapper = clientMapper;
        this.administrateurMapper = administrateurMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/client")
    public ResponseEntity<ClientResponseDto> getUtilisateur(@RequestBody ClientDto loginClient) {
        ClientDto client = clientService.getClientByEmailIgnoreCaseAndMotDePasse(loginClient.email(), loginClient.motDePasse());
        ResponseEntity<ClientResponseDto> re;
        if (client != null) {
            ClientResponseDto clientResponse = clientMapper.clientDtoToClientResponseDto(client);
            re = ResponseEntity.status(HttpStatus.OK).body(clientResponse);
        } else {
            re = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return re;
    }

    @PostMapping("/admin")
    public ResponseEntity<AdministrateurResponseDto> getUtilisateur(@RequestBody AdministrateurDto loginAdministrateur) {
        String email = loginAdministrateur.email();
        String motDePasse = passwordEncoder.encode(loginAdministrateur.motDePasse());

        AdministrateurDto administrateur = administrateurService.getAdministrateurByEmailIgnoreCaseAndMotDePasse(email, motDePasse);
        ResponseEntity<AdministrateurResponseDto> re;
        if (administrateur != null) {
            AdministrateurResponseDto administrateurResponse = administrateurMapper.administrateurDtoToAdministrateurResponseDto(administrateur);
            re = ResponseEntity.status(HttpStatus.OK).body(administrateurResponse);
        } else {
            re = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return re;
    }

}
