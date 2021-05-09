package com.demirev.model;

import javax.persistence.*;


@Entity
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    private Float latitude;

    private Float longitude;

    @ManyToOne
    @JoinColumn(name = "parent_facility_id")
    private Facility parentFacility;

    public Facility() {
    }

    public Facility(Long id, String name, Country country, City city, Float latitude, Float longitude,
                    Facility parentFacility) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.parentFacility = parentFacility;
    }

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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
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

    public Facility getParentFacility() {
        return parentFacility;
    }

    public void setParentFacility(Facility parentFacility) {
        this.parentFacility = parentFacility;
    }

    @Override
    public String toString() {
        return "Facility [id=" + id + ", name=" + name + ", country=" + country + ", city=" + city + ", latitude="
                + latitude + ", longitude=" + longitude + ", parentFacility=" + parentFacility + "]";
    }

}
