package com.accenture.service.mapper;

import com.accenture.dal.entity.utilisateurs.Adresse;
import com.accenture.dal.entity.utilisateurs.Client;
import com.accenture.dal.entity.Permis;
import com.accenture.dal.entity.utilisateurs.Role;
import com.accenture.service.dto.ClientResponseDto;
import com.accenture.service.dto.ClientDto;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;
import java.time.LocalDate;
import java.util.List;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-03-01T11:02:15+0100",
        comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class ClientMapperImpl implements ClientMapper {
    @Override
    public Client clientDtoToClient(ClientDto dto) {
        if (dto == null) {
            return null;
        }

        Client client = new Client();

        client.setId(dto.id());
        client.setNom(dto.nom());
        client.setPrenom(dto.prenom());
        client.setAdresse(dto.adresse());
        client.setEmail(dto.email());
        client.setMotDePasse(dto.motDePasse());
        client.setDateNaissance(dto.dateNaissance());
        client.setDateInscription(dto.dateInscription());
        client.setPermis(dto.permis());
        client.setDesactive(dto.desactive());
        client.setRole(dto.role());

        return client;
    }

    @Override
    public ClientDto clientToClientDto(Client entity) {
        if (entity == null) {
            return null;
        }

        int id = 0;
        String nom = null;
        String prenom = null;
        Adresse adresse = null;
        String email = null;
        String motDePasse = null;
        LocalDate dateNaissance = null;
        LocalDate dateInscription = null;
        List<Permis> permis = null;
        boolean desactive = false;
        Role role = null;

        id = entity.getId();
        nom = entity.getNom();
        prenom = entity.getPrenom();
        adresse = entity.getAdresse();
        email = entity.getEmail();
        motDePasse = entity.getMotDePasse();
        dateNaissance = entity.getDateNaissance();
        dateInscription = entity.getDateInscription();
        permis = entity.getPermis();
        desactive = entity.isDesactive();
        role = entity.getRole();

        ClientDto clientDto = new ClientDto(id, nom, prenom, adresse, email, motDePasse, dateNaissance, dateInscription, permis, desactive, role);

        return clientDto;
    }

    @Override
    public ClientResponseDto clientDtoToClientResponseDto(ClientDto clientDto) {
        if (clientDto == null) {
            return null;
        }

        String nom = null;
        String prenom = null;
        Adresse adresse = null;
        String email = null;
        LocalDate dateNaissance = null;
        LocalDate dateInscription = null;
        List<Permis> permis = null;
        Role role = null;

        nom = clientDto.nom();
        prenom = clientDto.prenom();
        adresse = clientDto.adresse();
        email = clientDto.email();
        dateNaissance = clientDto.dateNaissance();
        dateInscription = clientDto.dateInscription();
        permis = clientDto.permis();
        role = clientDto.role();

        ClientResponseDto clientResponseDto = new ClientResponseDto(email, nom, prenom, adresse, dateNaissance, dateInscription, permis, role);

        return clientResponseDto;
    }
}
