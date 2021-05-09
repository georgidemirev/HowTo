package com.demirev.service;

import com.demirev.model.criteria.CriteriaBuilder;
import com.demirev.model.criteria.WarrantyConditionCriteria;
import com.demirev.model.dto.WarrantyConditionDto;
import com.demirev.model.mapper.WarrantyConditionMapper;
import com.demirev.repository.WarrantyConditionRepository;
import com.demirev.rest.exceptions.NotFoundException;
import com.demirev.model.WarrantyCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class WarrantyConditionService {

    private final Logger log = LoggerFactory.getLogger(WarrantyConditionService.class);

    private final WarrantyConditionMapper warrantyConditionMapper;

    private final WarrantyConditionRepository warrantyConditionRepository;

    public WarrantyConditionService(WarrantyConditionRepository warrantyConditionRepository,
                                    WarrantyConditionMapper warrantyConditionMapper) {
        this.warrantyConditionRepository = warrantyConditionRepository;
        this.warrantyConditionMapper = warrantyConditionMapper;
    }

    public WarrantyConditionDto save(WarrantyConditionDto warrantyConditionDto) {
        log.debug("Request to save WarrantyCondition : {}", warrantyConditionDto);
        WarrantyCondition warrantyCondition = warrantyConditionMapper.toEntity(warrantyConditionDto);
        return warrantyConditionMapper.toDto(warrantyConditionRepository.save(warrantyCondition));
    }

    public Optional<WarrantyConditionDto> partialUpdate(WarrantyConditionDto warrantyConditionDto) {
        log.debug("Request to partially update WarrantyCondition : {}", warrantyConditionDto);

        return warrantyConditionRepository
                .findById(warrantyConditionDto.getId())
                .map(
                        warrantyCondition -> {
                            if (warrantyConditionDto.getDescription() != null) {
                                warrantyCondition.setDescription(warrantyConditionDto.getDescription());
                            }

                            if (warrantyConditionDto.getName() != null) {
                                warrantyCondition.setName(warrantyConditionDto.getName());
                            }

                            if (warrantyConditionDto.getText() != null) {
                                warrantyCondition.setText(warrantyConditionDto.getText());
                            }

                            return warrantyCondition;
                        })
                .map(warrantyConditionRepository::save)
                .map(warrantyConditionMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<WarrantyConditionDto> findAll(Pageable pageable) {
        log.debug("Request to get all WarrantyCondition");
        return warrantyConditionRepository.findAll(pageable).map(warrantyConditionMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<WarrantyConditionDto> findByCriteria(WarrantyConditionCriteria criteria, Pageable pageable) {
        log.debug("Request to find all WarrantyConditions by criteria : {}", criteria);
        return warrantyConditionMapper.toDtoPage(warrantyConditionRepository
                .findAll(CriteriaBuilder.filterWarrantyConditions(criteria), pageable));
    }

    @Transactional(readOnly = true)
    public long countByCriteria(WarrantyConditionCriteria criteria) {
        log.debug("Request to count WarrantyConditions by criteria : {}", criteria);
        return warrantyConditionRepository.count(CriteriaBuilder.filterWarrantyConditions(criteria));
    }

    @Transactional(readOnly = true)
    public WarrantyConditionDto findOne(Long id) throws NotFoundException {
        log.debug("Request to get WarrantyCondition : {}", id);
        return warrantyConditionMapper.toDto(warrantyConditionRepository
                .findById(id).orElseThrow(() -> new NotFoundException("")));
    }

    public void delete(Long id) {
        log.debug("Request to delete WarrantyCondition : {}", id);
        warrantyConditionRepository.deleteById(id);
    }
}
