package com.accenture.service.mapper;

import com.accenture.dal.entity.vehicules.Utilitaire;
import com.accenture.service.dto.UtilitaireDto;

public interface UtilitaireMapper {
    Utilitaire utilitaireDtoToUtilitaire(UtilitaireDto dto);

    UtilitaireDto utilitaireToUtilitaireDto(Utilitaire entity);
}
