package com.demirev.service;

import com.demirev.model.criteria.CriteriaBuilder;
import com.demirev.model.criteria.WarrantyCriteria;
import com.demirev.model.dto.WarrantyDto;
import com.demirev.model.mapper.WarrantyMapper;
import com.demirev.repository.WarrantyRepository;
import com.demirev.rest.exceptions.NotFoundException;
import com.demirev.model.Warranty;
import com.demirev.util.WarrantyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class WarrantyService {

    private final Logger log = LoggerFactory.getLogger(WarrantyService.class);

    private final WarrantyRepository warrantyRepository;

    private final WarrantyMapper warrantyMapper;

    public WarrantyService(WarrantyRepository warrantyRepository, WarrantyMapper warrantyMapper) {
        this.warrantyRepository = warrantyRepository;
        this.warrantyMapper = warrantyMapper;
    }

    public WarrantyDto save(WarrantyDto warrantyDto) {
        log.debug("Request to save Warranty : {}", warrantyDto);
        Warranty warranty = warrantyMapper.toEntity(warrantyDto);

        //internal logic
        String id = WarrantyUtils.generateWarrantyId();
        warranty.setId(id);
        Date now = new Date();
        warranty.setIssueDate(now);
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.add(Calendar.MONTH, warranty.getDurationMonths());
        Date expirationDate = c.getTime();
        warranty.setExpirationDate(expirationDate);

        return warrantyMapper.toDto(warrantyRepository.save(warranty));
    }

    public Optional<WarrantyDto> partialUpdate(WarrantyDto warrantyDto) {
        log.debug("Request to partially update Warranty : {}", warrantyDto);

        return warrantyRepository
                .findById(warrantyDto.getId())
                .map(
                        warranty -> {
                            if (warrantyDto.getDurationMonths() != null) {
                                warranty.setDurationMonths(warrantyDto.getDurationMonths());
                            }

                            if (warrantyDto.getExpirationDate() != null) {
                                warranty.setExpirationDate(warrantyDto.getExpirationDate());
                            }

                            if (warrantyDto.getIssueDate() != null) {
                                warranty.setIssueDate(warrantyDto.getIssueDate());
                            }

                            if (warrantyDto.getEndUserId() != null) {
                                warranty.setEndUserId(warrantyDto.getEndUserId());
                            }

                            if (warrantyDto.getItemSerialNumber() != null) {
                                warranty.setItemSerialNumber(warrantyDto.getItemSerialNumber());
                            }

//                            Does not support partial update for objects.
//                            Must be implemented if needed.
//
//                            if (warrantyDto.getFacilityId() != null) {
//                                warranty.setFacility(warrantyDto.getFacilityId());
//                            }
//
//                            if (warrantyDto.getWarrantyConditionId() != null) {
//                                warranty.setWarrantyCondition(warrantyDto.getWarrantyConditionId());
//                            }
//
//                            if (warrantyDto.getItemId() != null) {
//                                warranty.setItem(warrantyDto.getItemId());
//                            }

                            return warranty;
                        })
                .map(warrantyRepository::save)
                .map(warrantyMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<WarrantyDto> findAll(Pageable pageable) {
        log.debug("Request to get all Warranty");
        return warrantyRepository.findAll(pageable).map(warrantyMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<WarrantyDto> findByCriteria(WarrantyCriteria criteria, Pageable pageable) {
        log.debug("Request to find all Warranties by criteria : {}", criteria);
        return warrantyMapper.toDtoPage(warrantyRepository.findAll(CriteriaBuilder.filterWarranties(criteria), pageable));
    }

    @Transactional(readOnly = true)
    public long countByCriteria(WarrantyCriteria criteria) {
        log.debug("Request to count Warranties by criteria : {}", criteria);
        return warrantyRepository.count(CriteriaBuilder.filterWarranties(criteria));
    }

    @Transactional(readOnly = true)
    public WarrantyDto findOne(String id) throws NotFoundException {
        log.debug("Request to get Warranty : {}", id);
        return warrantyMapper.toDto(warrantyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Warranty with id: " + id + " was not found")));
    }

    public void delete(String id) {
        log.debug("Request to delete Warranty : {}", id);
        warrantyRepository.deleteById(id);
    }

}
