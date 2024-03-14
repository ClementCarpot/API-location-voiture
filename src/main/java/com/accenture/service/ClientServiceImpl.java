package com.accenture.service;

import com.accenture.dal.entity.utilisateurs.Client;
import com.accenture.dal.repository.ClientDao;
import com.accenture.exception.UtilisateurException;
import com.accenture.service.dto.ClientDto;
import com.accenture.service.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {


    private final ClientDao clientDao;

    private final ClientMapper clientMapper;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    ClientServiceImpl(ClientDao clientDao, ClientMapper clientMapper, PasswordEncoder passwordEncoder) {
        this.clientDao = clientDao;
        this.clientMapper = clientMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void ajouter(ClientDto clientDto) throws UtilisateurException {
        if (clientDto == null)
            throw new UtilisateurException("Le client est nul");
        if (existe(clientDto.email()))
            throw new UtilisateurException("Cet email est déjà utilisé");

        Client client = clientMapper.clientDtoToClient(clientDto);

        LocalDate dateInscription = LocalDate.now();
        client.setDateInscription(dateInscription);
        client.setEmail(clientDto.email().toLowerCase());
        setPassword(clientDto, client);

        clientDao.save(client);
    }

    @Override
    public void modifier(ClientDto clientDto, int id) throws UtilisateurException {
        if (clientDto == null)
            throw new UtilisateurException("Le client est nul");
        Optional<Client> opt = clientDao.findById(id);
        if(opt.isEmpty())
            throw new UtilisateurException("Le client à modifier n'existe pas");

        Client client = opt.get();

        if (clientDto.nom() != null && !clientDto.nom().isBlank())
            client.setNom(clientDto.nom());
        if (clientDto.prenom() != null && !clientDto.prenom().isBlank())
            client.setPrenom(clientDto.prenom());
        if (clientDto.adresse() != null)
            client.setAdresse(clientDto.adresse());
        if (clientDto.email() != null && !clientDto.email().isBlank()) {
            if (existe(clientDto.email())) {
                throw new UtilisateurException("Cet email est déjà utilisé");
            } else {
                client.setEmail(clientDto.email().toLowerCase());
            }
        }
        if (clientDto.motDePasse() != null && !clientDto.motDePasse().isBlank())
            setPassword(clientDto, client);
        if (clientDto.dateNaissance() != null)
            client.setDateNaissance(clientDto.dateNaissance());
        if (clientDto.permis() != null)
            client.setPermis(clientDto.permis());
        if (clientDto.role() != null)
            client.setRole(clientDto.role());

        clientDao.save(client);
    }

    @Override
    public void supprimer(int id) {
        clientDao.deleteById(id);
    }

    @Override
    public void supprimer(ClientDto clientDto) {
        clientDao.delete(clientMapper.clientDtoToClient(clientDto));
    }

    @Override
    public ClientDto getClientById(int id) {
        Optional<Client> opt = clientDao.findById(id);
        return opt.map(clientMapper::clientToClientDto).orElse(null);
    }

    @Override
    public List<ClientDto> getAllClients() {
        return clientDao.findAll()
                .stream()
                .map(clientMapper::clientToClientDto)
                .toList();
    }

    @Override
    public boolean existe(String email) {
        return clientDao.existsByEmailIgnoreCase(email);
    }

    @Override
    public ClientDto getClientByEmailIgnoreCaseAndMotDePasse(String email, String motDePasse) {
        return clientMapper.clientToClientDto(clientDao.findByEmailIgnoreCaseAndMotDePasse(email, motDePasse));
    }

    @Override
    public void desactiver(ClientDto clientDto) throws UtilisateurException {
        Client client = clientDao.findById(clientDto.id()).orElseThrow(() -> new UtilisateurException("Le client n'existe pas"));
        client.setDesactive(true);
        clientDao.save(client);
    }

    @Override
    public List<ClientDto> getAllClientsActifs() {
        return clientDao.findAllByDesactiveFalse()
                .stream()
                .map(clientMapper::clientToClientDto)
                .toList();
    }

    private void setPassword(ClientDto clientDto, Client client) throws UtilisateurException {
        // 8 à 16 caractères, au moins une majuscule, une lettre minuscule, un chiffre et un caractère spécial
        if (!clientDto.motDePasse().matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[&#@\\-_§]).{8,16}$")) {
            throw new UtilisateurException("Le mot de passe doit contenir 8 à 16 caractères, " +
                    "au moins une majuscule, une lettre minuscule, un chiffre et un caractère spécial");
        } else {
            client.setMotDePasse(passwordEncoder.encode(clientDto.motDePasse()));
        }
    }
}
