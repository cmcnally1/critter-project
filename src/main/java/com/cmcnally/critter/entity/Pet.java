package com.cmcnally.critter.entity;

import com.cmcnally.critter.pet.PetType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/*
    Entity class for Pet
 */
@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // id of the pet

    // Type of animal, stored as an enum string
    @Enumerated(EnumType.STRING)
    private PetType type;

    // Pet name
    private String name;

    // Customer who owns this pet
    // A bidirectional relationship is specified between pet and customer
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer owner;

    // Pet's birthday
    private LocalDate birthDate;

    // Notes about the pet
    private String notes;

    // List of appointments this pet is booked in for
    // A join table is specified for this relationship
    @ManyToMany(mappedBy = "pets")
    @Column(name = "appointments")
    private List<Schedule> scheduledAppointments;

    /*
        Getters and Setters
     */

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Schedule> getScheduledAppointments() {
        return scheduledAppointments;
    }

    public void setScheduledAppointments(List<Schedule> scheduledAppointments) {
        this.scheduledAppointments = scheduledAppointments;
    }
}
