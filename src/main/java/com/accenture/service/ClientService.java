package com.accenture.service;

import com.accenture.exception.UtilisateurException;
import com.accenture.service.dto.ClientDto;

import java.util.List;

public interface ClientService {
    void ajouter(ClientDto clientDto) throws UtilisateurException;

    void modifier(ClientDto clientDto, int id) throws UtilisateurException;

    void supprimer(int id);

    void supprimer(ClientDto clientDto);

    ClientDto getClientById(int id);

    List<ClientDto> getAllClients();

    boolean existe(String nom);

    ClientDto getClientByEmailIgnoreCaseAndMotDePasse(String email, String motDePasse);

    void desactiver(ClientDto client) throws UtilisateurException;

    List<ClientDto> getAllClientsActifs();
}
