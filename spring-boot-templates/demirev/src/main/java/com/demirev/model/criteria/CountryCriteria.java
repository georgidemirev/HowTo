package com.demirev.model.criteria;

public class CountryCriteria {

    private Long id;

    private String name;

    private String codeIso;

    private String codeIso3;

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
}
