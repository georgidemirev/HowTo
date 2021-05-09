package com.demirev.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Country {

    @Id
    private Long id;

    private String name;

    private String codeIso;

    private String codeIso3;

    public Country() {
    }

    public Country(String name, String codeIso, String codeIso3) {
        this.name = name;
        this.codeIso = codeIso;
        this.codeIso3 = codeIso3;
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

    public String getCodeIso() {
        return codeIso;
    }

    public void setCodeIso(String codeIso) {
        this.codeIso = codeIso;
    }

    public String getCodeIso3() {
        return codeIso3;
    }

    public void setCodeIso3(String codeIso3) {
        this.codeIso3 = codeIso3;
    }

    @Override
    public String toString() {
        return "Country [id=" + id + ", name=" + name + ", codeIso=" + codeIso + ", codeIso3=" + codeIso3 + "]";
    }

}
