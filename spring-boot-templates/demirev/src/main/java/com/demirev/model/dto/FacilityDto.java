package com.demirev.model.dto;

public class FacilityDto {

    private Long id;

    private String name;

    private Long countryId;

    private Long cityId;

    private Float latitude;

    private Float longitude;

    private Long parentFacilityId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Long getParentFacilityId() {
        return parentFacilityId;
    }

    public void setParentFacilityId(Long parentFacilityId) {
        this.parentFacilityId = parentFacilityId;
    }
}
