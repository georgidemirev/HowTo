package com.demirev.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ItemCategory {

    @Id
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private ItemCategory parentCategory;

    public ItemCategory() {
    }

    public ItemCategory(Long id, String name, ItemCategory parentCategory) {
        this.id = id;
        this.name = name;
        this.parentCategory = parentCategory;
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

    public ItemCategory getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(ItemCategory parentCategory) {
        this.parentCategory = parentCategory;
    }

    @Override
    public String toString() {
        return "ItemCategory [id=" + id + ", name=" + name + ", parentCategory=" + parentCategory + "]";
    }

}
