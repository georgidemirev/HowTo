package com.demirev.rest;

import com.demirev.model.criteria.FacilityCriteria;
import com.demirev.model.dto.FacilityDto;
import com.demirev.rest.exceptions.BadRequestException;
import com.demirev.rest.exceptions.NotFoundException;
import com.demirev.service.FacilityService;
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
@RequestMapping("/api/v1/facility")
@RolesAllowed({"ROLE_ADMIN", "ROLE_BUSINESS_USER_ADMIN"})
public class FacilityController {

    private final Logger log = LoggerFactory.getLogger(FacilityController.class);

    private final FacilityService facilityService;

    public FacilityController(FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    @PostMapping
    public ResponseEntity<FacilityDto> createFacility(
            @RequestBody @Valid FacilityDto facilityDto)
            throws URISyntaxException, BadRequestException {
        log.debug("REST request to save Facility : {}", facilityDto);
        if (facilityDto.getId() != null) {
            throw new BadRequestException("A new facility cannot already have an ID");
        }
        FacilityDto result = facilityService.save(facilityDto);
        return ResponseEntity
                .created(new URI("/api/v1/facility/" + result.getId()))
                .body(result);
    }

    @PutMapping
    public ResponseEntity<FacilityDto> updateFacility(@RequestBody FacilityDto facilityDto)
            throws BadRequestException {
        log.debug("REST request to update Facility : {}", facilityDto);
        if (facilityDto.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        FacilityDto result = facilityService.save(facilityDto);
        return ResponseEntity
                .ok()
                .body(result);
    }

    @PatchMapping(consumes = "application/merge-patch+json")
    public ResponseEntity<FacilityDto> partialUpdateFacility(@RequestBody FacilityDto facilityDto)
            throws BadRequestException, NotFoundException {
        log.debug("REST request to update Facility partially : {}", facilityDto);
        if (facilityDto.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        FacilityDto result = facilityService.partialUpdate(facilityDto)
                .orElseThrow(() -> new NotFoundException("Facility with id: "
                        + facilityDto.getId() + " was not found"));
        return ResponseEntity.ok().body(result);
    }

    @GetMapping
    public ResponseEntity<List<FacilityDto>> getAllFacilities(FacilityCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Facilities by criteria: {}", criteria);
        Page<FacilityDto> page = facilityService.findByCriteria(criteria, pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countFacilities(FacilityCriteria criteria) {
        log.debug("REST request to count Facilities by criteria: {}", criteria);
        return ResponseEntity.ok().body(facilityService.countByCriteria(criteria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacilityDto> getFacility(@PathVariable Long id) throws NotFoundException {
        log.debug("REST request to get Facility : {}", id);
        FacilityDto facilityDto = facilityService.findOne(id);
        return ResponseEntity.ok().body(facilityDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacility(@PathVariable Long id) {
        log.debug("REST request to delete Facility : {}", id);
        facilityService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
