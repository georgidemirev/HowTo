package com.demirev.rest;

import com.demirev.model.dto.ItemDto;
import com.demirev.model.criteria.ItemCriteria;
import com.demirev.rest.exceptions.BadRequestException;
import com.demirev.rest.exceptions.NotFoundException;
import com.demirev.service.ItemService;
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
@RequestMapping("/api/v1/item")
@RolesAllowed({"ROLE_ADMIN", "ROLE_BUSINESS_USER_ADMIN"})
public class ItemController {

    private final Logger log = LoggerFactory.getLogger(ItemController.class);

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    @RolesAllowed("ROLE_BUSINESS_USER_WRITE")
    public ResponseEntity<ItemDto> createItem(
            @RequestBody @Valid ItemDto itemDto)
            throws URISyntaxException, BadRequestException {
        log.debug("REST request to save Item : {}", itemDto);
        if (itemDto.getId() != null) {
            throw new BadRequestException("A new item cannot already have an ID");
        }
        ItemDto result = itemService.save(itemDto);
        return ResponseEntity
                .created(new URI("/api/v1/item/" + result.getId()))
                .body(result);
    }

    @PutMapping
    @RolesAllowed("ROLE_BUSINESS_USER_WRITE")
    public ResponseEntity<ItemDto> updateItem(@RequestBody ItemDto itemDto)
            throws BadRequestException {
        log.debug("REST request to update Item : {}", itemDto);
        if (itemDto.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        ItemDto result = itemService.save(itemDto);
        return ResponseEntity
                .ok()
                .body(result);
    }

    @RolesAllowed("ROLE_BUSINESS_USER_WRITE")
    @PatchMapping(consumes = "application/merge-patch+json")
    public ResponseEntity<ItemDto> partialUpdateItem(@RequestBody ItemDto itemDto)
            throws BadRequestException, NotFoundException {
        log.debug("REST request to update Item partially : {}", itemDto);
        if (itemDto.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        ItemDto result = itemService.partialUpdate(itemDto)
                .orElseThrow(() -> new NotFoundException("Item with id: "
                        + itemDto.getId() + " was not found"));
        return ResponseEntity.ok().body(result);
    }

    @GetMapping
    @RolesAllowed("ROLE_BUSINESS_USER_READ")
    public ResponseEntity<List<ItemDto>> getAllItems(ItemCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Items by criteria: {}", criteria);
        Page<ItemDto> page = itemService.findByCriteria(criteria, pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    @GetMapping("/count")
    @RolesAllowed("ROLE_BUSINESS_USER_READ")
    public ResponseEntity<Long> countPlanIntervals(ItemCriteria criteria) {
        log.debug("REST request to count Items by criteria: {}", criteria);
        return ResponseEntity.ok().body(itemService.countByCriteria(criteria));
    }

    @GetMapping("/{id}")
    @RolesAllowed("ROLE_BUSINESS_USER_READ")
    public ResponseEntity<ItemDto> getItem(@PathVariable Long id) throws NotFoundException {
        log.debug("REST request to get Item : {}", id);
        ItemDto itemDto = itemService.findOne(id);
        return ResponseEntity.ok().body(itemDto);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed("ROLE_BUSINESS_USER_WRITE")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        log.debug("REST request to delete Item : {}", id);
        itemService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
