package com.demirev.rest;

import com.demirev.model.criteria.EndUserCriteria;
import com.demirev.model.dto.EndUserDto;
import com.demirev.rest.exceptions.BadRequestException;
import com.demirev.rest.exceptions.NotFoundException;
import com.demirev.service.EndUserService;
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
@RequestMapping("/api/v1/end-user")
@RolesAllowed("ROLE_ADMIN")
public class EndUserController {

    private final Logger log = LoggerFactory.getLogger(EndUserController.class);

    private final EndUserService endUserService;

    public EndUserController(EndUserService endUserService) {
        this.endUserService = endUserService;
    }

    @PostMapping
    public ResponseEntity<EndUserDto> createEndUser(
            @RequestBody @Valid EndUserDto endUserDto)
            throws URISyntaxException, BadRequestException {
        log.debug("REST request to save EndUser : {}", endUserDto);
        if (endUserDto.getId() != null) {
            throw new BadRequestException("A new endUser cannot already have an ID");
        }
        EndUserDto result = endUserService.save(endUserDto);
        return ResponseEntity
                .created(new URI("/api/v1/endUser/" + result.getId()))
                .body(result);
    }

    @PutMapping
    public ResponseEntity<EndUserDto> updateEndUser(@RequestBody EndUserDto endUserDto)
            throws BadRequestException {
        log.debug("REST request to update EndUser : {}", endUserDto);
        if (endUserDto.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        EndUserDto result = endUserService.save(endUserDto);
        return ResponseEntity
                .ok()
                .body(result);
    }

    @PatchMapping(consumes = "application/merge-patch+json")
    public ResponseEntity<EndUserDto> partialUpdateEndUser(@RequestBody EndUserDto endUserDto)
            throws BadRequestException, NotFoundException {
        log.debug("REST request to update EndUser partially : {}", endUserDto);
        if (endUserDto.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        EndUserDto result = endUserService.partialUpdate(endUserDto)
                .orElseThrow(() -> new NotFoundException("EndUser with id: "
                        + endUserDto.getId() + " was not found"));
        return ResponseEntity.ok().body(result);
    }

    @GetMapping
    public ResponseEntity<List<EndUserDto>> getAllEndUsers(EndUserCriteria criteria, Pageable pageable) {
        log.debug("REST request to get EndUsers by criteria: {}", criteria);
        Page<EndUserDto> page = endUserService.findByCriteria(criteria, pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countEndUsers(EndUserCriteria criteria) {
        log.debug("REST request to count EndUsers by criteria: {}", criteria);
        return ResponseEntity.ok().body(endUserService.countByCriteria(criteria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EndUserDto> getEndUser(@PathVariable Long id) throws NotFoundException {
        log.debug("REST request to get EndUser : {}", id);
        EndUserDto endUserDto = endUserService.findOne(id);
        return ResponseEntity.ok().body(endUserDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndUser(@PathVariable Long id) {
        log.debug("REST request to delete EndUser : {}", id);
        endUserService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
