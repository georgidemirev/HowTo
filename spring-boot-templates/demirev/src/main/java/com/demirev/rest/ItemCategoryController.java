package com.demirev.rest;

import com.demirev.model.criteria.ItemCategoryCriteria;
import com.demirev.model.dto.ItemCategoryDto;
import com.demirev.rest.exceptions.BadRequestException;
import com.demirev.rest.exceptions.NotFoundException;
import com.demirev.service.ItemCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/item-category")
@RolesAllowed({"ROLE_ADMIN", "ROLE_BUSINESS_USER_ADMIN"})
public class ItemCategoryController {

    private final Logger log = LoggerFactory.getLogger(ItemCategoryController.class);

    private final ItemCategoryService itemCategoryService;

    public ItemCategoryController(ItemCategoryService itemCategoryService) {
        this.itemCategoryService = itemCategoryService;
    }

    @PostMapping
    @RolesAllowed("ROLE_BUSINESS_USER_WRITE")
    public ResponseEntity<ItemCategoryDto> createItemCategory(
            @RequestBody @Valid ItemCategoryDto itemCategoryDto)
            throws URISyntaxException, BadRequestException {
        log.debug("REST request to save ItemCategory : {}", itemCategoryDto);
        if (itemCategoryDto.getId() != null) {
            throw new BadRequestException("A new itemCategory cannot already have an ID");
        }
        ItemCategoryDto result = itemCategoryService.save(itemCategoryDto);
        return ResponseEntity
                .created(new URI("/api/v1/itemCategory/" + result.getId()))
                .body(result);
    }

    @PutMapping
    @RolesAllowed("ROLE_BUSINESS_USER_WRITE")
    public ResponseEntity<ItemCategoryDto> updateItemCategory(@RequestBody ItemCategoryDto itemCategoryDto)
            throws BadRequestException {
        log.debug("REST request to update ItemCategory : {}", itemCategoryDto);
        if (itemCategoryDto.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        ItemCategoryDto result = itemCategoryService.save(itemCategoryDto);
        return ResponseEntity
                .ok()
                .body(result);
    }

    @RolesAllowed("ROLE_BUSINESS_USER_WRITE")
    @PatchMapping(consumes = "application/merge-patch+json")
    public ResponseEntity<ItemCategoryDto> partialUpdateItemCategory(@RequestBody ItemCategoryDto itemCategoryDto)
            throws BadRequestException, NotFoundException {
        log.debug("REST request to update ItemCategory partially : {}", itemCategoryDto);
        if (itemCategoryDto.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        ItemCategoryDto result = itemCategoryService.partialUpdate(itemCategoryDto)
                .orElseThrow(() -> new NotFoundException("ItemCategory with id: "
                        + itemCategoryDto.getId() + " was not found"));
        return ResponseEntity.ok().body(result);
    }

    @GetMapping
    @RolesAllowed("ROLE_BUSINESS_USER_READ")
    public ResponseEntity<List<ItemCategoryDto>> getAllItemCategories(ItemCategoryCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ItemCategories by criteria: {}", criteria);
        Page<ItemCategoryDto> page = itemCategoryService.findByCriteria(criteria, pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    @GetMapping("/count")
    @RolesAllowed("ROLE_BUSINESS_USER_READ")
    public ResponseEntity<Long> countItemCategories(ItemCategoryCriteria criteria) {
        log.debug("REST request to count ItemCategories by criteria: {}", criteria);
        return ResponseEntity.ok().body(itemCategoryService.countByCriteria(criteria));
    }

    @GetMapping("/{id}")
    @RolesAllowed("ROLE_BUSINESS_USER_READ")
    public ResponseEntity<ItemCategoryDto> getItemCategory(@PathVariable Long id) throws NotFoundException {
        log.debug("REST request to get ItemCategory : {}", id);
        ItemCategoryDto itemCategoryDto = itemCategoryService.findOne(id);
        return ResponseEntity.ok().body(itemCategoryDto);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed("ROLE_BUSINESS_USER_WRITE")
    public ResponseEntity<Void> deleteItemCategory(@PathVariable Long id) {
        log.debug("REST request to delete ItemCategory : {}", id);
        itemCategoryService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }

}
