package com.demirev.rest;

import com.demirev.model.criteria.WarrantyConditionCriteria;
import com.demirev.model.dto.WarrantyConditionDto;
import com.demirev.rest.exceptions.BadRequestException;
import com.demirev.rest.exceptions.NotFoundException;
import com.demirev.service.WarrantyConditionService;
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
@RequestMapping("/api/v1/warranty-condition")
@RolesAllowed({"ROLE_ADMIN", "ROLE_BUSINESS_USER_ADMIN"})
public class WarrantyConditionController {

    private final Logger log = LoggerFactory.getLogger(WarrantyConditionController.class);

    private final WarrantyConditionService warrantyConditionService;

    public WarrantyConditionController(WarrantyConditionService warrantyConditionService) {
        this.warrantyConditionService = warrantyConditionService;
    }

    @PostMapping
    @RolesAllowed("ROLE_BUSINESS_USER_WRITE")
    public ResponseEntity<WarrantyConditionDto> createWarrantyCondition(
            @RequestBody @Valid WarrantyConditionDto warrantyConditionDto)
            throws URISyntaxException, BadRequestException {
        log.debug("REST request to save WarrantyCondition : {}", warrantyConditionDto);
        if (warrantyConditionDto.getId() != null) {
            throw new BadRequestException("A new warrantyCondition cannot already have an ID");
        }
        WarrantyConditionDto result = warrantyConditionService.save(warrantyConditionDto);
        return ResponseEntity
                .created(new URI("/api/v1/warranty-condition/" + result.getId()))
                .body(result);
    }

    @PutMapping
    @RolesAllowed("ROLE_BUSINESS_USER_WRITE")
    public ResponseEntity<WarrantyConditionDto> updateWarrantyCondition(
            @RequestBody WarrantyConditionDto warrantyConditionDto) throws BadRequestException {
        log.debug("REST request to update WarrantyCondition : {}", warrantyConditionDto);
        if (warrantyConditionDto.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        WarrantyConditionDto result = warrantyConditionService.save(warrantyConditionDto);
        return ResponseEntity
                .ok()
                .body(result);
    }

    @RolesAllowed("ROLE_BUSINESS_USER_WRITE")
    @PatchMapping(consumes = "application/merge-patch+json")
    public ResponseEntity<WarrantyConditionDto> partialUpdateWarrantyCondition(
            @RequestBody WarrantyConditionDto warrantyConditionDto) throws BadRequestException, NotFoundException {
        log.debug("REST request to update WarrantyCondition partially : {}", warrantyConditionDto);
        if (warrantyConditionDto.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        WarrantyConditionDto result = warrantyConditionService.partialUpdate(warrantyConditionDto)
                .orElseThrow(() -> new NotFoundException("WarrantyCondition with id: "
                        + warrantyConditionDto.getId() + " was not found"));
        return ResponseEntity.ok().body(result);
    }

    @GetMapping
    @RolesAllowed("ROLE_BUSINESS_USER_READ")
    public ResponseEntity<List<WarrantyConditionDto>> getAllWarrantyConditions(WarrantyConditionCriteria criteria,
                                                                               Pageable pageable) {
        log.debug("REST request to get WarrantyConditions by criteria: {}", criteria);
        Page<WarrantyConditionDto> page = warrantyConditionService.findByCriteria(criteria, pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    @GetMapping("/count")
    @RolesAllowed("ROLE_BUSINESS_USER_READ")
    public ResponseEntity<Long> countWarrantyConditions(WarrantyConditionCriteria criteria) {
        log.debug("REST request to count WarrantyConditions by criteria: {}", criteria);
        return ResponseEntity.ok().body(warrantyConditionService.countByCriteria(criteria));
    }

    @GetMapping("/{id}")
    @RolesAllowed("ROLE_BUSINESS_USER_READ")
    public ResponseEntity<WarrantyConditionDto> getWarrantyCondition(@PathVariable Long id) throws NotFoundException {
        log.debug("REST request to get WarrantyCondition : {}", id);
        WarrantyConditionDto warrantyConditionDto = warrantyConditionService.findOne(id);
        return ResponseEntity.ok().body(warrantyConditionDto);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed("ROLE_BUSINESS_USER_WRITE")
    public ResponseEntity<Void> deleteWarrantyCondition(@PathVariable Long id) {
        log.debug("REST request to delete WarrantyCondition : {}", id);
        warrantyConditionService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }

}
