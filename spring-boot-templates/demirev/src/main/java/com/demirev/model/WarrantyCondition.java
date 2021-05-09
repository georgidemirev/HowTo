package com.demirev.model;

import javax.persistence.*;

@Entity
public class WarrantyCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "facility_id")
    private Facility facility;

    private String name;

    private String description;

    @Column(columnDefinition = "LONGTEXT")
    private String text;

    public WarrantyCondition() {
    }

    public WarrantyCondition(Facility facility, String name, String description, String text) {
        this.facility = facility;
        this.name = name;
        this.description = description;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "WarrantyCondition{" +
                "id=" + id +
                ", facility=" + facility +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
