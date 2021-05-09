package com.demirev.rest;

import com.demirev.model.criteria.CountryCriteria;
import com.demirev.model.dto.CountryDto;
import com.demirev.rest.exceptions.BadRequestException;
import com.demirev.rest.exceptions.NotFoundException;
import com.demirev.service.CountryService;
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
@RequestMapping("/api/v1/country")
@RolesAllowed("ROLE_ADMIN")
public class CountryController {

    private final Logger log = LoggerFactory.getLogger(CountryController.class);

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping
    public ResponseEntity<CountryDto> createCountry(
            @RequestBody @Valid CountryDto countryDto)
            throws URISyntaxException, BadRequestException {
        log.debug("REST request to save Country : {}", countryDto);
        if (countryDto.getId() != null) {
            throw new BadRequestException("A new country cannot already have an ID");
        }
        CountryDto result = countryService.save(countryDto);
        return ResponseEntity
                .created(new URI("/api/v1/country/" + result.getId()))
                .body(result);
    }

    @PutMapping
    public ResponseEntity<CountryDto> updateCountry(@RequestBody CountryDto countryDto)
            throws BadRequestException {
        log.debug("REST request to update Country : {}", countryDto);
        if (countryDto.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        CountryDto result = countryService.save(countryDto);
        return ResponseEntity
                .ok()
                .body(result);
    }

    @PatchMapping(consumes = "application/merge-patch+json")
    public ResponseEntity<CountryDto> partialUpdateCountry(@RequestBody CountryDto countryDto)
            throws BadRequestException, NotFoundException {
        log.debug("REST request to update Country partially : {}", countryDto);
        if (countryDto.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        CountryDto result = countryService.partialUpdate(countryDto)
                .orElseThrow(() -> new NotFoundException("Country with id: "
                        + countryDto.getId() + " was not found"));
        return ResponseEntity.ok().body(result);
    }

    @GetMapping
    public ResponseEntity<List<CountryDto>> getAllCountries(CountryCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Countries by criteria: {}", criteria);
        Page<CountryDto> page = countryService.findByCriteria(criteria, pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countCountries(CountryCriteria criteria) {
        log.debug("REST request to count Countries by criteria: {}", criteria);
        return ResponseEntity.ok().body(countryService.countByCriteria(criteria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDto> getCountry(@PathVariable Long id) throws NotFoundException {
        log.debug("REST request to get Country : {}", id);
        CountryDto countryDto = countryService.findOne(id);
        return ResponseEntity.ok().body(countryDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        log.debug("REST request to delete Country : {}", id);
        countryService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
