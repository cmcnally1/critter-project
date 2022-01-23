package com.udacity.jdnd.course3.critter.data;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

/*
    Entity class for Employee
 */
@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Long id; // id for the employee

    // Name of the employee
    private String name;

    // Set of skills the employee can perform
    // Element collection specified for enum class EmployeeSkill
    // Stored as an enum string
    @ElementCollection(targetClass = EmployeeSkill.class)
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> skills;

    // Set of days the employee can work
    // Element collection specified for enum class DayOfWeek
    // Stored as an enum string
    // Database column name changed to availability
    @ElementCollection(targetClass = DayOfWeek.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "availability")
    private Set<DayOfWeek> daysAvailable;

    // List of appointments this employee is booked to work
    // A join table is specified for this relationship
    @ManyToMany(mappedBy = "employees")
    @Column(name = "appointments")
    private List<Schedule> scheduledAppointments;

    /*
        Getters and setters
     */

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

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    public List<Schedule> getScheduledAppointments() {
        return scheduledAppointments;
    }

    public void setScheduledAppointments(List<Schedule> scheduledAppointments) {
        this.scheduledAppointments = scheduledAppointments;
    }
}
