package com.demirev.service;

import com.demirev.model.criteria.CriteriaBuilder;
import com.demirev.model.dto.ItemDto;
import com.demirev.model.mapper.ItemMapper;
import com.demirev.repository.ItemRepository;
import com.demirev.rest.exceptions.NotFoundException;
import com.demirev.model.Item;
import com.demirev.model.criteria.ItemCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ItemService {

    private final Logger log = LoggerFactory.getLogger(ItemService.class);

    private final ItemRepository itemRepository;

    private final ItemMapper itemMapper;

    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public ItemDto save(ItemDto itemDto) {
        log.debug("Request to save Item : {}", itemDto);
        Item item = itemMapper.toEntity(itemDto);
        return itemMapper.toDto(itemRepository.save(item));
    }

    public Optional<ItemDto> partialUpdate(ItemDto itemDto) {
        log.debug("Request to partially update Item : {}", itemDto);

        return itemRepository
                .findById(itemDto.getId())
                .map(
                        item -> {
                            if (itemDto.getBrand() != null) {
                                item.setBrand(itemDto.getBrand());
                            }

                            if (itemDto.getDescription() != null) {
                                item.setDescription(itemDto.getDescription());
                            }

                            if (itemDto.getName() != null) {
                                item.setName(itemDto.getName());
                            }

                            if (itemDto.getInternalIdentifier() != null) {
                                item.setInternalIdentifier(itemDto.getInternalIdentifier());
                            }

                            if (itemDto.getPicture() != null) {
                                item.setPicture(itemDto.getPicture());
                            }

                            return item;
                        })
                .map(itemRepository::save)
                .map(itemMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<ItemDto> findAll(Pageable pageable) {
        log.debug("Request to get all Item");
        return itemRepository.findAll(pageable).map(itemMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<ItemDto> findByCriteria(ItemCriteria criteria, Pageable pageable) {
        log.debug("Request to find all Items by criteria : {}", criteria);
        return itemMapper.toDtoPage(itemRepository.findAll(CriteriaBuilder.filterItems(criteria), pageable));
    }

    @Transactional(readOnly = true)
    public long countByCriteria(ItemCriteria criteria) {
        log.debug("Request to count Items by criteria : {}", criteria);
        return itemRepository.count(CriteriaBuilder.filterItems(criteria));
    }

    @Transactional(readOnly = true)
    public ItemDto findOne(Long id) throws NotFoundException {
        log.debug("Request to get Item : {}", id);
        return itemMapper.toDto(itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item with id: " + id + " was not found")));
    }

    public void delete(Long id) {
        log.debug("Request to delete Item : {}", id);
        itemRepository.deleteById(id);
    }

}
