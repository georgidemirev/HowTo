package com.demirev.model.mapper;

import com.demirev.model.dto.CountryDto;
import com.demirev.model.Country;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface CountryMapper extends EntityMapper<CountryDto, Country> {

    CountryDto toDto(Country country);

    Country toEntity(CountryDto countryDto);

    default Page<CountryDto> toDtoPage(Page<Country> countries) {
        return countries.map(this::toDto);
    }

}
