package com.accenture.service.mapper;

import com.accenture.dal.entity.Location;
import com.accenture.service.dto.LocationDto;

public interface LocationMapper {
    Location locationDtoToLocation(LocationDto dto);

    LocationDto locationToLocationDto(Location entity);
}
