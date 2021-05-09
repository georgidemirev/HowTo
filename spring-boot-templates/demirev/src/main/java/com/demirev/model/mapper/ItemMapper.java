package com.demirev.model.mapper;

import com.demirev.model.Item;
import com.demirev.model.dto.ItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface ItemMapper extends EntityMapper<ItemDto, Item> {

    @Mapping(target = "parentCategoryId", source = "parentCategory.id")
    @Mapping(target = "subCategoryId", source = "subCategory.id")
    @Mapping(target = "facilityId", source = "facility.id")
    ItemDto toDto(Item item);

    @Mapping(source = "parentCategoryId", target = "parentCategory.id")
    @Mapping(source = "subCategoryId", target = "subCategory.id")
    @Mapping(source = "facilityId", target = "facility.id")
    Item toEntity(ItemDto itemDto);

    default Page<ItemDto> toDtoPage(Page<Item> itemList) {
        return itemList.map(this::toDto);
    }

}
