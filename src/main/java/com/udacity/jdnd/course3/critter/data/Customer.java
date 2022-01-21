package com.udacity.jdnd.course3.critter.data;

import javax.persistence.*;
import java.util.List;

/*
    Entity class for Customer
 */

@Entity
public class Customer {

    @Id
    @GeneratedValue
    private long id;

    private String name;
    @Column(name = "number")
    private String phoneNumber;
    private String notes;

    @OneToMany(mappedBy = "owner")
    private List<Pet> pets;

    /*
        Getters and setters
     */

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
