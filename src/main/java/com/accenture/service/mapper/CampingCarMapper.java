package com.accenture.service.mapper;

import com.accenture.dal.entity.vehicules.CampingCar;
import com.accenture.service.dto.CampingCarDto;

public interface CampingCarMapper {
    CampingCar campingCarDtoToCampingCar(CampingCarDto dto);

    CampingCarDto campingCarToCampingCarDto(CampingCar entity);
}
