package com.accenture.service.mapper;

import com.accenture.dal.entity.utilisateurs.Client;
import com.accenture.service.dto.ClientResponseDto;
import com.accenture.service.dto.ClientDto;

public interface ClientMapper {
    Client clientDtoToClient(ClientDto dto);

    ClientDto clientToClientDto(Client entity);

    ClientResponseDto clientDtoToClientResponseDto(ClientDto clientDto);
}
