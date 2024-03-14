package com.accenture.service.mapper;

import com.accenture.dal.entity.vehicules.Moto;
import com.accenture.service.dto.MotoDto;

public interface MotoMapper {
    Moto motoDtoToMoto(MotoDto dto);

    MotoDto motoToMotoDto(Moto entity);
}
