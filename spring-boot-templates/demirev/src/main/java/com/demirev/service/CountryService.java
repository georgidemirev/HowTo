package com.demirev.service;

import com.demirev.model.criteria.CountryCriteria;
import com.demirev.model.criteria.CriteriaBuilder;
import com.demirev.model.dto.CountryDto;
import com.demirev.model.mapper.CountryMapper;
import com.demirev.repository.CountryRepository;
import com.demirev.rest.exceptions.NotFoundException;
import com.demirev.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CountryService {

    private final Logger log = LoggerFactory.getLogger(CountryService.class);

    private final CountryRepository countryRepository;

    private final CountryMapper countryMapper;

    public CountryService(CountryRepository countryRepository, CountryMapper countryMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }

    public CountryDto save(CountryDto countryDto) {
        log.debug("Request to save Country : {}", countryDto);
        Country country = countryMapper.toEntity(countryDto);
        return countryMapper.toDto(countryRepository.save(country));
    }

    public Optional<CountryDto> partialUpdate(CountryDto countryDto) {
        log.debug("Request to partially update Country : {}", countryDto);

        return countryRepository
                .findById(countryDto.getId())
                .map(
                        country -> {
                            if (countryDto.getName() != null) {
                                country.setName(countryDto.getName());
                            }

                            if (countryDto.getCodeIso() != null) {
                                country.setCodeIso(countryDto.getCodeIso());
                            }

                            if (countryDto.getCodeIso3() != null) {
                                country.setCodeIso3(countryDto.getCodeIso3());
                            }

                            return country;
                        })
                .map(countryRepository::save)
                .map(countryMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<CountryDto> findAll(Pageable pageable) {
        log.debug("Request to get all Country");
        return countryRepository.findAll(pageable).map(countryMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<CountryDto> findByCriteria(CountryCriteria criteria, Pageable pageable) {
        log.debug("Request to find all Countries by criteria : {}", criteria);
        return countryMapper.toDtoPage(countryRepository.findAll(CriteriaBuilder.filterCountries(criteria), pageable));
    }

    @Transactional(readOnly = true)
    public long countByCriteria(CountryCriteria criteria) {
        log.debug("Request to count Countries by criteria : {}", criteria);
        return countryRepository.count(CriteriaBuilder.filterCountries(criteria));
    }

    @Transactional(readOnly = true)
    public CountryDto findOne(Long id) throws NotFoundException {
        log.debug("Request to get Country : {}", id);
        return countryMapper.toDto(countryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Country with id: " + id + " was not found")));
    }

    public void delete(Long id) {
        log.debug("Request to delete Country : {}", id);
        countryRepository.deleteById(id);
    }

}
