package com.demirev.service;

import com.demirev.model.criteria.BusinessUserCriteria;
import com.demirev.model.criteria.CriteriaBuilder;
import com.demirev.model.dto.BusinessUserDto;
import com.demirev.model.mapper.BusinessUserMapper;
import com.demirev.repository.BusinessUserRepository;
import com.demirev.rest.exceptions.NotFoundException;
import com.demirev.model.BusinessUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BusinessUserService {

    private final Logger log = LoggerFactory.getLogger(BusinessUserService.class);

    private final BusinessUserRepository businessUserRepository;

    private final BusinessUserMapper businessUserMapper;

    public BusinessUserService(BusinessUserRepository businessUserRepository, BusinessUserMapper businessUserMapper) {
        this.businessUserRepository = businessUserRepository;
        this.businessUserMapper = businessUserMapper;
    }

    public BusinessUserDto save(BusinessUserDto businessUserDto) {
        log.debug("Request to save BusinessUser : {}", businessUserDto);
        BusinessUser businessUser = businessUserMapper.toEntity(businessUserDto);
        return businessUserMapper.toDto(businessUserRepository.save(businessUser));
    }

    public Optional<BusinessUserDto> partialUpdate(BusinessUserDto businessUserDto) {
        log.debug("Request to partially update BusinessUser : {}", businessUserDto);

        return businessUserRepository
                .findById(businessUserDto.getId())
                .map(
                        businessUser -> {
                            if (businessUserDto.getEmail() != null) {
                                businessUser.setEmail(businessUserDto.getEmail());
                            }

                            if (businessUserDto.getFirstName() != null) {
                                businessUser.setFirstName(businessUserDto.getFirstName());
                            }

                            if (businessUserDto.getPassword() != null) {
                                businessUser.setPassword(businessUserDto.getPassword());
                            }

                            if (businessUserDto.getLastName() != null) {
                                businessUser.setLastName(businessUserDto.getLastName());
                            }

                            if (businessUserDto.getPhoneNumber() != null) {
                                businessUser.setPhoneNumber(businessUserDto.getPhoneNumber());
                            }

                            if (businessUserDto.getRegistrationDate() != null) {
                                businessUser.setRegistrationDate(businessUserDto.getRegistrationDate());
                            }

                            if (businessUserDto.getRole() != null) {
                                businessUser.setRole(businessUserDto.getRole());
                            }

                            return businessUser;
                        })
                .map(businessUserRepository::save)
                .map(businessUserMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<BusinessUserDto> findAll(Pageable pageable) {
        log.debug("Request to get all BusinessUser");
        return businessUserRepository.findAll(pageable).map(businessUserMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<BusinessUserDto> findByCriteria(BusinessUserCriteria criteria, Pageable pageable) {
        log.debug("Request to find all BusinessUsers by criteria : {}", criteria);
        return businessUserMapper.toDtoPage(businessUserRepository.findAll(CriteriaBuilder.filterBusinessUsers(criteria), pageable));
    }

    @Transactional(readOnly = true)
    public long countByCriteria(BusinessUserCriteria criteria) {
        log.debug("Request to count BusinessUsers by criteria : {}", criteria);
        return businessUserRepository.count(CriteriaBuilder.filterBusinessUsers(criteria));
    }

    @Transactional(readOnly = true)
    public BusinessUserDto findOne(Long id) throws NotFoundException {
        log.debug("Request to get BusinessUser : {}", id);
        return businessUserMapper.toDto(businessUserRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("BusinessUser with id: " + id + " was not found")));
    }

    public void delete(Long id) {
        log.debug("Request to delete BusinessUser : {}", id);
        businessUserRepository.deleteById(id);
    }

}
