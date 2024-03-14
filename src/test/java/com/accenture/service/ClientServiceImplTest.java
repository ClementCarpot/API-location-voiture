package com.accenture.service;

import com.accenture.dal.entity.Permis;
import com.accenture.dal.entity.utilisateurs.Adresse;
import com.accenture.dal.entity.utilisateurs.Client;
import com.accenture.dal.entity.utilisateurs.Role;
import com.accenture.dal.repository.ClientDao;
import com.accenture.exception.UtilisateurException;
import com.accenture.service.dto.ClientDto;
import com.accenture.service.mapper.ClientMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ClientServiceImplTest {

    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private ClientDao clientDao;

    @Mock
    private ClientMapper clientMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAjouterClient() {
        ClientDto clientDto = new ClientDto(1, "nom", "prenom", new Adresse("rue de la paix", "Paris", "75000"), "email@test.com", "Password1234@!", LocalDate.of(1993, 3, 24), LocalDate.of(2024, 3, 14), List.of(Permis.A, Permis.B), false, Role.CLIENT);

        when(clientMapper.clientDtoToClient(clientDto)).thenReturn(new Client());
        when(clientService.existe(anyString())).thenReturn(false);

        assertDoesNotThrow(() -> clientService.ajouter(clientDto));

        verify(clientDao, times(1)).save(any(Client.class));
    }

    @Test
    void testAjouterClientNull() {
        ClientDto clientDto = null;

        UtilisateurException exception = assertThrows(UtilisateurException.class, () -> {
            clientService.ajouter(clientDto);
        });

        assertEquals("Le client est nul", exception.getMessage());

        verify(clientDao, never()).save(any());
    }

    @Test
    void testAjouterClientEmailExist() {
        ClientDto clientDto = new ClientDto(1, "nom", "prenom", new Adresse("rue de la paix", "Paris", "75000"), "email@test.com", "Password1234@!", LocalDate.of(1993, 3, 24), LocalDate.of(2024, 3, 14), List.of(Permis.A, Permis.B), false, Role.CLIENT);

        when(clientService.existe(clientDto.email())).thenReturn(true);

        UtilisateurException exception = assertThrows(UtilisateurException.class, () -> {
            clientService.ajouter(clientDto);
        });

        assertEquals("Cet email est déjà utilisé", exception.getMessage());

        verify(clientDao, never()).save(any());
    }

    @Test
    void testModifierClient() throws UtilisateurException {
        int id = 1;
        ClientDto clientDto = new ClientDto(1, "nom", "prenom", new Adresse("rue de la paix", "Paris", "75000"), "email@test.com", "Password1234@!", LocalDate.of(1993, 3, 24), LocalDate.of(2024, 3, 14), List.of(Permis.A, Permis.B), false, Role.CLIENT);

        Optional<Client> optionalClient = Optional.of(new Client());

        when(clientDao.findById(id)).thenReturn(optionalClient);
        when(clientService.existe(anyString())).thenReturn(false);

        assertDoesNotThrow(() -> clientService.modifier(clientDto, id));

        verify(clientDao, times(1)).save(any(Client.class));
    }

    @Test
    void testModifierClientNull() {
        int id = 1;

        ClientDto clientDto = null;

        UtilisateurException exception = assertThrows(UtilisateurException.class, () -> {
            clientService.modifier(clientDto, id);
        });

        assertEquals("Le client est nul", exception.getMessage());

        verify(clientDao, never()).save(any());
    }

    @Test
    void testSupprimerUtilisateurParId() {
        int id = 1;

        clientService.supprimer(id);

        verify(clientDao, times(1)).deleteById(id);
    }

    @Test
    void testGetClientById() {
        int id = 1;

        clientService.getClientById(id);

        verify(clientDao, times(1)).findById(id);
    }

    @Test
    void testGetAllClients() {
        clientService.getAllClients();

        verify(clientDao, times(1)).findAll();
    }

    @Test
    void testExisteEmail() {
        String email = "test@email.com";

        clientService.existe(email);

        verify(clientDao, times(1)).existsByEmailIgnoreCase(email);
    }

    @Test
    void testGetClientByEmailIgnoreCaseAndMotDePasse() {
        String email = "test@email.com";
        String motDePasse = "Password1234@!";

        clientService.getClientByEmailIgnoreCaseAndMotDePasse(email, motDePasse);

        verify(clientDao, times(1)).findByEmailIgnoreCaseAndMotDePasse(email, motDePasse);
    }

    @Test
    void testDesactiver() {
        ClientDto clientDto = new ClientDto(1, "nom", "prenom", new Adresse("rue de la paix", "Paris", "75000"), "email@test.com", "Password1234@!", LocalDate.of(1993, 3, 24), LocalDate.of(2024, 3, 14), List.of(Permis.A, Permis.B), false, Role.CLIENT);
        Client client = new Client();

        when(clientDao.findById(clientDto.id())).thenReturn(Optional.of(client));

        assertDoesNotThrow(() -> clientService.desactiver(clientDto));

        verify(clientDao, times(1)).save(client);
    }

    @Test
    void testGetAllClientsActifs() {
        clientService.getAllClientsActifs();

        verify(clientDao, times(1)).findAllByDesactiveFalse();
    }
}