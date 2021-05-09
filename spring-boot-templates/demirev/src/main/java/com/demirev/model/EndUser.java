package com.demirev.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "end_user")
public class EndUser {

    @Id
    private Long id;

    private String firstName;

    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    private String role;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    private String phoneNumber;

    private Date registrationDate;

    public EndUser() {
    }

    public EndUser(Long id, String firstName, String lastName, String email, String password,
                   String role, Country country, String phoneNumber, Date registrationDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.registrationDate = registrationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
