package com.accenture.service.mapper;

import com.accenture.dal.entity.vehicules.Voiture;
import com.accenture.service.dto.VoitureDto;

public interface VoitureMapper {
    Voiture voitureDtoToVoiture(VoitureDto dto);

    VoitureDto voitureToVoitureDto(Voiture entity);
}
