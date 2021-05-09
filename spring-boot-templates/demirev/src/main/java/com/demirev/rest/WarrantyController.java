package com.demirev.rest;

import com.demirev.model.criteria.WarrantyCriteria;
import com.demirev.model.dto.WarrantyDto;
import com.demirev.rest.exceptions.BadRequestException;
import com.demirev.rest.exceptions.NotFoundException;
import com.demirev.service.WarrantyService;
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
@RequestMapping("/api/v1/warranty")
@RolesAllowed({"ROLE_ADMIN", "ROLE_BUSINESS_USER_ADMIN"})
public class WarrantyController {

    private final Logger log = LoggerFactory.getLogger(WarrantyController.class);

    private final WarrantyService warrantyService;

    public WarrantyController(WarrantyService warrantyService) {
        this.warrantyService = warrantyService;
    }

    @PostMapping
    @RolesAllowed("ROLE_BUSINESS_USER_WRITE")
    public ResponseEntity<WarrantyDto> createWarranty(
            @RequestBody @Valid WarrantyDto warrantyDto)
            throws URISyntaxException, BadRequestException {
        log.debug("REST request to save Warranty : {}", warrantyDto);
        if (warrantyDto.getId() != null) {
            throw new BadRequestException("A new warranty cannot already have an ID");
        }
        WarrantyDto result = warrantyService.save(warrantyDto);
        return ResponseEntity
                .created(new URI("/api/v1/warranty/" + result.getId()))
                .body(result);
    }

    @PutMapping
    @RolesAllowed("ROLE_BUSINESS_USER_WRITE")
    public ResponseEntity<WarrantyDto> updateWarranty(@RequestBody WarrantyDto warrantyDto)
            throws BadRequestException {
        log.debug("REST request to update Warranty : {}", warrantyDto);
        if (warrantyDto.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        WarrantyDto result = warrantyService.save(warrantyDto);
        return ResponseEntity
                .ok()
                .body(result);
    }

    @RolesAllowed("ROLE_BUSINESS_USER_WRITE")
    @PatchMapping(consumes = "application/merge-patch+json")
    public ResponseEntity<WarrantyDto> partialUpdateWarranty(@RequestBody WarrantyDto warrantyDto)
            throws BadRequestException, NotFoundException {
        log.debug("REST request to update Warranty partially : {}", warrantyDto);
        if (warrantyDto.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        WarrantyDto result = warrantyService.partialUpdate(warrantyDto)
                .orElseThrow(() -> new NotFoundException("Warranty with id: "
                        + warrantyDto.getId() + " was not found"));
        return ResponseEntity.ok().body(result);
    }

    @GetMapping
    @RolesAllowed("ROLE_BUSINESS_USER_READ")
    public ResponseEntity<List<WarrantyDto>> getAllWarranties(WarrantyCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Warranties by criteria: {}", criteria);
        Page<WarrantyDto> page = warrantyService.findByCriteria(criteria, pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    @GetMapping("/count")
    @RolesAllowed("ROLE_BUSINESS_USER_READ")
    public ResponseEntity<Long> countWarranties(WarrantyCriteria criteria) {
        log.debug("REST request to count Warranties by criteria: {}", criteria);
        return ResponseEntity.ok().body(warrantyService.countByCriteria(criteria));
    }

    @GetMapping("/{id}")
    @RolesAllowed("ROLE_BUSINESS_USER_READ")
    public ResponseEntity<WarrantyDto> getWarranty(@PathVariable String id) throws NotFoundException {
        log.debug("REST request to get Warranty : {}", id);
        WarrantyDto warrantyDto = warrantyService.findOne(id);
        return ResponseEntity.ok().body(warrantyDto);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed("ROLE_BUSINESS_USER_WRITE")
    public ResponseEntity<Void> deleteWarranty(@PathVariable String id) {
        log.debug("REST request to delete Warranty : {}", id);
        warrantyService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
