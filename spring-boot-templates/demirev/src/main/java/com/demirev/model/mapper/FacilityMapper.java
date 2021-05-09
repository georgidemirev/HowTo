package com.demirev.model.mapper;

import com.demirev.model.Facility;
import com.demirev.model.dto.FacilityDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface FacilityMapper extends EntityMapper<FacilityDto, Facility> {

    @Mapping(target = "parentFacilityId", source = "parentFacility.id")
    @Mapping(target = "cityId", source = "city.id")
    @Mapping(target = "countryId", source = "country.id")
    FacilityDto toDto(Facility facility);

    @Mapping(source = "parentFacilityId", target = "parentFacility.id")
    @Mapping(source = "cityId", target = "city.id")
    @Mapping(source = "countryId", target = "country.id")
    Facility toEntity(FacilityDto facilityDto);

    default Page<FacilityDto> toDtoPage(Page<Facility> facilities) {
        return facilities.map(this::toDto);
    }

}
