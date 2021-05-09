package com.demirev.model.mapper;

import com.demirev.model.dto.BusinessUserDto;
import com.demirev.model.BusinessUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface BusinessUserMapper extends EntityMapper<BusinessUserDto, BusinessUser> {

    @Mapping(target = "facilityId", source = "facility.id")
    @Mapping(target = "countryId", source = "country.id")
    BusinessUserDto toDto(BusinessUser businessUser);

    @Mapping(source = "facilityId", target = "facility.id")
    @Mapping(source = "countryId", target = "country.id")
    BusinessUser toEntity(BusinessUserDto businessUserDto);

    default Page<BusinessUserDto> toDtoPage(Page<BusinessUser> businessUsers) {
        return businessUsers.map(this::toDto);
    }

}
