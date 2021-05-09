package com.demirev.rest;

import com.demirev.model.criteria.BusinessUserCriteria;
import com.demirev.model.dto.BusinessUserDto;
import com.demirev.rest.exceptions.BadRequestException;
import com.demirev.rest.exceptions.NotFoundException;
import com.demirev.service.BusinessUserService;
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
@RequestMapping("/api/v1/business-user")
@RolesAllowed({"ROLE_ADMIN", "ROLE_BUSINESS_USER_ADMIN"})
public class BusinessUserController {

    private final Logger log = LoggerFactory.getLogger(BusinessUserController.class);

    private final BusinessUserService businessUserService;

    public BusinessUserController(BusinessUserService businessUserService) {
        this.businessUserService = businessUserService;
    }

    @PostMapping
    public ResponseEntity<BusinessUserDto> createBusinessUser(
            @RequestBody @Valid BusinessUserDto businessUserDto)
            throws URISyntaxException, BadRequestException {
        log.debug("REST request to save BusinessUser : {}", businessUserDto);
        if (businessUserDto.getId() != null) {
            throw new BadRequestException("A new businessUser cannot already have an ID");
        }
        BusinessUserDto result = businessUserService.save(businessUserDto);
        return ResponseEntity
                .created(new URI("/api/v1/businessUser/" + result.getId()))
                .body(result);
    }

    @PutMapping
    public ResponseEntity<BusinessUserDto> updateBusinessUser(@RequestBody BusinessUserDto businessUserDto)
            throws BadRequestException {
        log.debug("REST request to update BusinessUser : {}", businessUserDto);
        if (businessUserDto.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        BusinessUserDto result = businessUserService.save(businessUserDto);
        return ResponseEntity
                .ok()
                .body(result);
    }

    @PatchMapping(consumes = "application/merge-patch+json")
    public ResponseEntity<BusinessUserDto> partialBusinessUser(@RequestBody BusinessUserDto businessUserDto)
            throws BadRequestException, NotFoundException {
        log.debug("REST request to update BusinessUser partially : {}", businessUserDto);
        if (businessUserDto.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        BusinessUserDto result = businessUserService.partialUpdate(businessUserDto)
                .orElseThrow(() -> new NotFoundException("BusinessUser with id: "
                        + businessUserDto.getId() + " was not found"));
        return ResponseEntity.ok().body(result);
    }

    @GetMapping
    public ResponseEntity<List<BusinessUserDto>> getAllBusinessUsers(BusinessUserCriteria criteria, Pageable pageable) {
        log.debug("REST request to get BusinessUsers by criteria: {}", criteria);
        Page<BusinessUserDto> page = businessUserService.findByCriteria(criteria, pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countBusinessUsers(BusinessUserCriteria criteria) {
        log.debug("REST request to count BusinessUsers by criteria: {}", criteria);
        return ResponseEntity.ok().body(businessUserService.countByCriteria(criteria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusinessUserDto> getBusinessUser(@PathVariable Long id) throws NotFoundException {
        log.debug("REST request to get BusinessUser : {}", id);
        BusinessUserDto businessUserDto = businessUserService.findOne(id);
        return ResponseEntity.ok().body(businessUserDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusinessUser(@PathVariable Long id) {
        log.debug("REST request to delete BusinessUser : {}", id);
        businessUserService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
