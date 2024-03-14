package com.accenture.service.mapper;

import com.accenture.dal.entity.utilisateurs.Administrateur;
import com.accenture.service.dto.AdministrateurDto;
import com.accenture.service.dto.AdministrateurResponseDto;

public interface AdministrateurMapper {
    Administrateur administrateurDtoToAdministrateur(AdministrateurDto dto);

    AdministrateurDto administrateurToAdministrateurDto(Administrateur entity);

    AdministrateurResponseDto administrateurDtoToAdministrateurResponseDto(AdministrateurDto administrateurDto);

    AdministrateurResponseDto administrateurToAdministrateurResponseDto(Administrateur administrateur);
}
