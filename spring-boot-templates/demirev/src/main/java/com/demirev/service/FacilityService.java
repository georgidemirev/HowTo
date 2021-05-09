package com.demirev.service;

import com.demirev.model.criteria.CriteriaBuilder;
import com.demirev.model.criteria.FacilityCriteria;
import com.demirev.model.dto.FacilityDto;
import com.demirev.model.mapper.FacilityMapper;
import com.demirev.repository.FacilityRepository;
import com.demirev.rest.exceptions.NotFoundException;
import com.demirev.model.Facility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FacilityService {

    private final Logger log = LoggerFactory.getLogger(FacilityService.class);

    private final FacilityRepository facilityRepository;

    private final FacilityMapper facilityMapper;

    public FacilityService(FacilityRepository facilityRepository, FacilityMapper facilityMapper) {
        this.facilityRepository = facilityRepository;
        this.facilityMapper = facilityMapper;
    }

    public FacilityDto save(FacilityDto facilityDto) {
        log.debug("Request to save Facility : {}", facilityDto);
        Facility facility = facilityMapper.toEntity(facilityDto);
        return facilityMapper.toDto(facilityRepository.save(facility));
    }

    public Optional<FacilityDto> partialUpdate(FacilityDto facilityDto) {
        log.debug("Request to partially update Facility : {}", facilityDto);

        return facilityRepository
                .findById(facilityDto.getId())
                .map(
                        facility -> {
                            if (facilityDto.getLatitude() != null) {
                                facility.setLatitude(facilityDto.getLatitude());
                            }

                            if (facilityDto.getLongitude() != null) {
                                facility.setLongitude(facilityDto.getLongitude());
                            }

                            if (facilityDto.getName() != null) {
                                facility.setName(facilityDto.getName());
                            }

                            return facility;
                        })
                .map(facilityRepository::save)
                .map(facilityMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<FacilityDto> findAll(Pageable pageable) {
        log.debug("Request to get all Facility");
        return facilityRepository.findAll(pageable).map(facilityMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<FacilityDto> findByCriteria(FacilityCriteria criteria, Pageable pageable) {
        log.debug("Request to find all Facilities by criteria : {}", criteria);
        return facilityMapper.toDtoPage(facilityRepository.findAll(CriteriaBuilder.filterFacilities(criteria), pageable));
    }

    @Transactional(readOnly = true)
    public long countByCriteria(FacilityCriteria criteria) {
        log.debug("Request to count Facilities by criteria : {}", criteria);
        return facilityRepository.count(CriteriaBuilder.filterFacilities(criteria));
    }

    @Transactional(readOnly = true)
    public FacilityDto findOne(Long id) throws NotFoundException {
        log.debug("Request to get Facility : {}", id);
        return facilityMapper.toDto(facilityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Facility with id: " + id + " was not found")));
    }

    public void delete(Long id) {
        log.debug("Request to delete Facility : {}", id);
        facilityRepository.deleteById(id);
    }

}
