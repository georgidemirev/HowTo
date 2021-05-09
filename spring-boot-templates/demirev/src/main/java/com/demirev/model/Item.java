package com.demirev.model;

import javax.persistence.*;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String picture;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private ItemCategory parentCategory;

    @ManyToOne
    @JoinColumn(name = "sub_category_id")
    private ItemCategory subCategory;

    private String brand;

    @ManyToOne
    @JoinColumn(name = "facility_id")
    private Facility facility;

    private String internalIdentifier;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    public Item() {
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public ItemCategory getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(ItemCategory parentCategory) {
        this.parentCategory = parentCategory;
    }

    public ItemCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(ItemCategory subCategory) {
        this.subCategory = subCategory;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public String getInternalIdentifier() {
        return internalIdentifier;
    }

    public void setInternalIdentifier(String internalIdentifier) {
        this.internalIdentifier = internalIdentifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", parentCategory=" + parentCategory +
                ", subCategory=" + subCategory +
                ", brand='" + brand + '\'' +
                ", facility=" + facility +
                ", internalIdentifier='" + internalIdentifier + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
