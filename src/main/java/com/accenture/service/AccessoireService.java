package com.accenture.service;

import com.accenture.exception.AccessoireException;
import com.accenture.service.dto.AccessoireDto;

import java.util.List;

public interface AccessoireService {
    void ajouter(AccessoireDto accessoireDto) throws AccessoireException;

    void modifier(AccessoireDto accessoireDto, int id) throws AccessoireException;

    void supprimer(int id);

    void supprimer(AccessoireDto accessoireDto);

    AccessoireDto getAccessoireById(int id);

    List<AccessoireDto> getAllAccessoires();
}
