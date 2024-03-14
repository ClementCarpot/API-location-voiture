package com.accenture.service.mapper;

import com.accenture.dal.entity.vehicules.Velo;
import com.accenture.service.dto.VeloDto;

public interface VeloMapper {
    Velo veloDtoToVelo(VeloDto dto);

    VeloDto veloToVeloDto(Velo entity);
}
