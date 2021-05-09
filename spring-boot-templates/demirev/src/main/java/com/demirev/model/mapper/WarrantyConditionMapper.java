package com.demirev.model.mapper;

import com.demirev.model.dto.WarrantyConditionDto;
import com.demirev.model.WarrantyCondition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface WarrantyConditionMapper extends EntityMapper<WarrantyConditionDto, WarrantyCondition> {

    @Mapping(target = "facilityId", source = "facility.id")
    WarrantyConditionDto toDto(WarrantyCondition warrantyCondition);

    @Mapping(source = "facilityId", target = "facility.id")
    WarrantyCondition toEntity(WarrantyConditionDto warrantyConditionDto);

    default Page<WarrantyConditionDto> toDtoPage(Page<WarrantyCondition> warrantyConditions) {
        return warrantyConditions.map(this::toDto);
    }

}
