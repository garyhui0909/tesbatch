package com.gary.testbatch.test;

import java.time.LocalDate;
import java.util.List;

class Employee {

    private Integer id;
    private String name;
    private String cDistrict;
    private LocalDate dateOfBirth;
    private List<String> locations;
    private List<Role> roles;

    public Employee(Integer id, String name, String cDistrict, LocalDate dateOfBirth, List<String> locations, List<Role> roles) {
        this.id = id;
        this.name = name;
        this.cDistrict = cDistrict;
        this.dateOfBirth = dateOfBirth;
        this.locations = locations;
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getcDistrict() {
        return cDistrict;
    }

    public void setcDistrict(String cDistrict) {
        this.cDistrict = cDistrict;
    }
}