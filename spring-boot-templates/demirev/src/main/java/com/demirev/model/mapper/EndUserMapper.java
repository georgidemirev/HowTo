package com.demirev.model.mapper;

import com.demirev.model.EndUser;
import com.demirev.model.dto.EndUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface EndUserMapper extends EntityMapper<EndUserDto, EndUser> {

    @Mapping(target = "countryId", source = "country.id")
    EndUserDto toDto(EndUser businessUser);

    @Mapping(source = "countryId", target = "country.id")
    EndUser toEntity(EndUserDto businessUserDto);

    default Page<EndUserDto> toDtoPage(Page<EndUser> endUsers) {
        return endUsers.map(this::toDto);
    }

}
