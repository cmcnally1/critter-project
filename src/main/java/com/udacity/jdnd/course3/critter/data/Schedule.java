package com.udacity.jdnd.course3.critter.data;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/*
    Entity class for Schedule (scheduled appointments)
 */

@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private long id; // id for the scheduled appointment

    // Scheduled appointments can have multiple employees working them,
    // as well as employees can have multiple scheduled appointments.
    // Many to many relationship with a join table to hold relationship
    @ManyToMany
    @JoinTable(
            name = "schedule_employee",
            joinColumns = { @JoinColumn(name = "schedule_id")},
            inverseJoinColumns = { @JoinColumn(name = "employee_id")}
    )
    private List<Employee> employees;

    // Scheduled appointments can have multiple pets attending them,
    // as well as pets can have multiple scheduled appointments.
    // Many to many relationship with a join table to hold relationship
    @ManyToMany
    @JoinTable(
            name = "schedule_pet",
            joinColumns = { @JoinColumn(name = "schedule_id")},
            inverseJoinColumns = { @JoinColumn(name = "pet_id")}
    )
    private List<Pet> pets;

    // Date of the appointment
    private LocalDate date;

    // Set of activities being performed during appointment
    @ElementCollection(targetClass = EmployeeSkill.class)
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> activities;

    /*
        Getters and setters
     */

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }
}
