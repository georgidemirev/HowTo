package com.demirev.service;

import com.demirev.model.criteria.CriteriaBuilder;
import com.demirev.model.criteria.ItemCategoryCriteria;
import com.demirev.model.dto.ItemCategoryDto;
import com.demirev.model.mapper.ItemCategoryMapper;
import com.demirev.repository.ItemCategoryRepository;
import com.demirev.rest.exceptions.NotFoundException;
import com.demirev.model.ItemCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ItemCategoryService {

    private final Logger log = LoggerFactory.getLogger(ItemCategoryService.class);

    private final ItemCategoryRepository itemCategoryRepository;

    private final ItemCategoryMapper itemCategoryMapper;

    public ItemCategoryService(ItemCategoryRepository itemCategoryRepository, ItemCategoryMapper itemCategoryMapper) {
        this.itemCategoryRepository = itemCategoryRepository;
        this.itemCategoryMapper = itemCategoryMapper;
    }

    public ItemCategoryDto save(ItemCategoryDto itemCategoryDto) {
        log.debug("Request to save ItemCategory : {}", itemCategoryDto);
        ItemCategory itemCategory = itemCategoryMapper.toEntity(itemCategoryDto);
        return itemCategoryMapper.toDto(itemCategoryRepository.save(itemCategory));
    }

    public Optional<ItemCategoryDto> partialUpdate(ItemCategoryDto itemCategoryDto) {
        log.debug("Request to partially update ItemCategory : {}", itemCategoryDto);

        return itemCategoryRepository
                .findById(itemCategoryDto.getId())
                .map(
                        itemCategory -> {
                            if (itemCategoryDto.getName() != null) {
                                itemCategory.setName(itemCategoryDto.getName());
                            }

                            return itemCategory;
                        })
                .map(itemCategoryRepository::save)
                .map(itemCategoryMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<ItemCategoryDto> findAll(Pageable pageable) {
        log.debug("Request to get all ItemCategory");
        return itemCategoryRepository.findAll(pageable).map(itemCategoryMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<ItemCategoryDto> findByCriteria(ItemCategoryCriteria criteria, Pageable pageable) {
        log.debug("Request to find all ItemCategories by criteria : {}", criteria);
        return itemCategoryMapper.toDtoPage(itemCategoryRepository.findAll(CriteriaBuilder.filterItemCategories(criteria), pageable));
    }

    @Transactional(readOnly = true)
    public long countByCriteria(ItemCategoryCriteria criteria) {
        log.debug("Request to count ItemCategories by criteria : {}", criteria);
        return itemCategoryRepository.count(CriteriaBuilder.filterItemCategories(criteria));
    }

    @Transactional(readOnly = true)
    public ItemCategoryDto findOne(Long id) throws NotFoundException {
        log.debug("Request to get ItemCategory : {}", id);
        return itemCategoryMapper.toDto(itemCategoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ItemCategory with id: " + id + " was not found")));
    }

    public void delete(Long id) {
        log.debug("Request to delete ItemCategory : {}", id);
        itemCategoryRepository.deleteById(id);
    }

}
