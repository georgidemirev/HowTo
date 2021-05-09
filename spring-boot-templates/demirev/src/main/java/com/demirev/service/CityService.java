package com.demirev.service;

import com.demirev.model.criteria.CityCriteria;
import com.demirev.model.criteria.CriteriaBuilder;
import com.demirev.model.dto.CityDto;
import com.demirev.model.mapper.CityMapper;
import com.demirev.repository.CityRepository;
import com.demirev.rest.exceptions.NotFoundException;
import com.demirev.model.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CityService {

    private final Logger log = LoggerFactory.getLogger(CityService.class);

    private final CityRepository cityRepository;

    private final CityMapper cityMapper;

    public CityService(CityRepository cityRepository, CityMapper cityMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    public CityDto save(CityDto cityDto) {
        log.debug("Request to save City : {}", cityDto);
        City city = cityMapper.toEntity(cityDto);
        return cityMapper.toDto(cityRepository.save(city));
    }

    public Optional<CityDto> partialUpdate(CityDto cityDto) {
        log.debug("Request to partially update City : {}", cityDto);

        return cityRepository
                .findById(cityDto.getId())
                .map(
                        city -> {
                            if (cityDto.getName() != null) {
                                city.setName(cityDto.getName());
                            }

                            return city;
                        })
                .map(cityRepository::save)
                .map(cityMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<CityDto> findAll(Pageable pageable) {
        log.debug("Request to get all City");
        return cityRepository.findAll(pageable).map(cityMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<CityDto> findByCriteria(CityCriteria criteria, Pageable pageable) {
        log.debug("Request to find all Cities by criteria : {}", criteria);
        return cityMapper.toDtoPage(cityRepository.findAll(CriteriaBuilder.filterCities(criteria), pageable));
    }

    @Transactional(readOnly = true)
    public long countByCriteria(CityCriteria criteria) {
        log.debug("Request to count Cities by criteria : {}", criteria);
        return cityRepository.count(CriteriaBuilder.filterCities(criteria));
    }

    @Transactional(readOnly = true)
    public CityDto findOne(Long id) throws NotFoundException {
        log.debug("Request to get City : {}", id);
        return cityMapper.toDto(cityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("City with id: " + id + " was not found")));
    }

    public void delete(Long id) {
        log.debug("Request to delete City : {}", id);
        cityRepository.deleteById(id);
    }

}
