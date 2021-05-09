package com.demirev.service;

import com.demirev.model.EndUser;
import com.demirev.model.criteria.CriteriaBuilder;
import com.demirev.model.criteria.EndUserCriteria;
import com.demirev.model.dto.EndUserDto;
import com.demirev.model.mapper.EndUserMapper;
import com.demirev.repository.EndUserRepository;
import com.demirev.rest.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EndUserService {

    private final Logger log = LoggerFactory.getLogger(EndUserService.class);

    private final EndUserRepository endUserRepository;

    private final EndUserMapper endUserMapper;

    public EndUserService(EndUserRepository endUserRepository, EndUserMapper endUserMapper) {
        this.endUserRepository = endUserRepository;
        this.endUserMapper = endUserMapper;
    }

    public EndUserDto save(EndUserDto endUserDto) {
        log.debug("Request to save EndUser : {}", endUserDto);
        EndUser endUser = endUserMapper.toEntity(endUserDto);
        return endUserMapper.toDto(endUserRepository.save(endUser));
    }

    public Optional<EndUserDto> partialUpdate(EndUserDto endUserDto) {
        log.debug("Request to partially update EndUser : {}", endUserDto);

        return endUserRepository
                .findById(endUserDto.getId())
                .map(
                        endUser -> {
                            if (endUserDto.getEmail() != null) {
                                endUser.setEmail(endUserDto.getEmail());
                            }

                            if (endUserDto.getFirstName() != null) {
                                endUser.setFirstName(endUserDto.getFirstName());
                            }

                            if (endUserDto.getPassword() != null) {
                                endUser.setPassword(endUserDto.getPassword());
                            }

                            if (endUserDto.getLastName() != null) {
                                endUser.setLastName(endUserDto.getLastName());
                            }

                            if (endUserDto.getPhoneNumber() != null) {
                                endUser.setPhoneNumber(endUserDto.getPhoneNumber());
                            }

                            if (endUserDto.getRegistrationDate() != null) {
                                endUser.setRegistrationDate(endUserDto.getRegistrationDate());
                            }

                            if (endUserDto.getRole() != null) {
                                endUser.setRole(endUserDto.getRole());
                            }

                            return endUser;
                        })
                .map(endUserRepository::save)
                .map(endUserMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<EndUserDto> findAll(Pageable pageable) {
        log.debug("Request to get all EndUser");
        return endUserRepository.findAll(pageable).map(endUserMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<EndUserDto> findByCriteria(EndUserCriteria criteria, Pageable pageable) {
        log.debug("Request to find all EndUsers by criteria : {}", criteria);
        return endUserMapper.toDtoPage(endUserRepository.findAll(CriteriaBuilder.filterEndUsers(criteria), pageable));
    }

    @Transactional(readOnly = true)
    public long countByCriteria(EndUserCriteria criteria) {
        log.debug("Request to count EndUsers by criteria : {}", criteria);
        return endUserRepository.count(CriteriaBuilder.filterEndUsers(criteria));
    }

    @Transactional(readOnly = true)
    public EndUserDto findOne(Long id) throws NotFoundException {
        log.debug("Request to get EndUser : {}", id);
        return endUserMapper.toDto(endUserRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("EndUser with id: " + id + " was not found")));
    }

    public void delete(Long id) {
        log.debug("Request to delete EndUser : {}", id);
        endUserRepository.deleteById(id);
    }

}
