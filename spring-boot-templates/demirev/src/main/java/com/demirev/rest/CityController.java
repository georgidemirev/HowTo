package com.demirev.rest;

import com.demirev.model.criteria.CityCriteria;
import com.demirev.model.dto.CityDto;
import com.demirev.rest.exceptions.BadRequestException;
import com.demirev.rest.exceptions.NotFoundException;
import com.demirev.service.CityService;
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
@RequestMapping("/api/v1/city")
@RolesAllowed({"ROLE_ADMIN", "ROLE_BUSINESS_USER_ADMIN"})
public class CityController {

    private final Logger log = LoggerFactory.getLogger(CityController.class);

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping
    public ResponseEntity<CityDto> createCity(
            @RequestBody @Valid CityDto cityDto)
            throws URISyntaxException, BadRequestException {
        log.debug("REST request to save City : {}", cityDto);
        if (cityDto.getId() != null) {
            throw new BadRequestException("A new city cannot already have an ID");
        }
        CityDto result = cityService.save(cityDto);
        return ResponseEntity
                .created(new URI("/api/v1/city/" + result.getId()))
                .body(result);
    }

    @PutMapping
    public ResponseEntity<CityDto> updateCity(@RequestBody CityDto cityDto)
            throws BadRequestException {
        log.debug("REST request to update City : {}", cityDto);
        if (cityDto.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        CityDto result = cityService.save(cityDto);
        return ResponseEntity
                .ok()
                .body(result);
    }

    @PatchMapping(consumes = "application/merge-patch+json")
    public ResponseEntity<CityDto> partialUpdateCity(@RequestBody CityDto cityDto)
            throws BadRequestException, NotFoundException {
        log.debug("REST request to update City partially : {}", cityDto);
        if (cityDto.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        CityDto result = cityService.partialUpdate(cityDto)
                .orElseThrow(() -> new NotFoundException("City with id: "
                        + cityDto.getId() + " was not found"));
        return ResponseEntity.ok().body(result);
    }

    @GetMapping
    public ResponseEntity<List<CityDto>> getAllCities(CityCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Cities by criteria: {}", criteria);
        Page<CityDto> page = cityService.findByCriteria(criteria, pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countCities(CityCriteria criteria) {
        log.debug("REST request to count Cities by criteria: {}", criteria);
        return ResponseEntity.ok().body(cityService.countByCriteria(criteria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDto> getCity(@PathVariable Long id) throws NotFoundException {
        log.debug("REST request to get City : {}", id);
        CityDto cityDto = cityService.findOne(id);
        return ResponseEntity.ok().body(cityDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        log.debug("REST request to delete City : {}", id);
        cityService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
