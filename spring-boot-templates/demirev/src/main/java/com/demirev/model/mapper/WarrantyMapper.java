package com.demirev.model.mapper;

import com.demirev.model.Warranty;
import com.demirev.model.dto.WarrantyDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface WarrantyMapper extends EntityMapper<WarrantyDto, Warranty> {

    @Mapping(target = "warrantyConditionId", source = "warrantyCondition.id")
    @Mapping(target = "itemId", source = "item.id")
    @Mapping(target = "facilityId", source = "facility.id")
    WarrantyDto toDto(Warranty warranty);

    @Mapping(source = "warrantyConditionId", target = "warrantyCondition.id")
    @Mapping(source = "itemId", target = "item.id")
    @Mapping(source = "facilityId", target = "facility.id")
    Warranty toEntity(WarrantyDto warrantyDto);

    default Page<WarrantyDto> toDtoPage(Page<Warranty> warranties) {
        return warranties.map(this::toDto);
    }

}
