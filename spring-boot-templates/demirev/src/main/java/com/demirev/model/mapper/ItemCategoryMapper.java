package com.demirev.model.mapper;

import com.demirev.model.dto.ItemCategoryDto;
import com.demirev.model.ItemCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface ItemCategoryMapper extends EntityMapper<ItemCategoryDto, ItemCategory> {

    @Mapping(target = "parentCategoryId", source = "parentCategory.id")
    ItemCategoryDto toDto(ItemCategory itemCategory);

    @Mapping(source = "parentCategoryId", target = "parentCategory.id")
    ItemCategory toEntity(ItemCategoryDto itemCategoryDto);

    default Page<ItemCategoryDto> toDtoPage(Page<ItemCategory> itemCategories) {
        return itemCategories.map(this::toDto);
    }

}
