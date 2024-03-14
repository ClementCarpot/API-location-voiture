package com.accenture.service.mapper;

import com.accenture.dal.entity.Accessoire;
import com.accenture.service.dto.AccessoireDto;

public interface AccessoireMapper {
    Accessoire accessoireDtoToAccessoire(AccessoireDto dto);

    AccessoireDto accessoireToAccessoireDto(Accessoire entity);
}
