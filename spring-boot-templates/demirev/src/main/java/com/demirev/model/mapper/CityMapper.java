package com.demirev.model.mapper;

import com.demirev.model.dto.CityDto;
import com.demirev.model.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface CityMapper extends EntityMapper<CityDto, City> {

    @Mapping(target = "countryId", source = "country.id")
    CityDto toDto(City city);

    @Mapping(source = "countryId", target = "country.id")
    City toEntity(CityDto cityDto);

    default Page<CityDto> toDtoPage(Page<City> cities) {
        return cities.map(this::toDto);
    }

}
