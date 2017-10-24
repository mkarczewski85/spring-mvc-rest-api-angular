package com.karczewski.manager.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private CompanyPositions position;
    private Double salary;
    @OneToOne(mappedBy = "employees", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "adress_id")
    private Address address;
    private String email;
    private ZonedDateTime employmentDate;
    private ZonedDateTime birthDate;
    private byte[] photo;

    public Employee() {
    }

    public Employee(String firstName, String lastName, CompanyPositions position, Double salary, Address address,
                    String email, ZonedDateTime employmentDate, ZonedDateTime birthDate, byte[] photo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.salary = salary;
        this.address = address;
        this.employmentDate = employmentDate;
        this.birthDate = birthDate;
        this.photo = photo;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public CompanyPositions getPosition() {
        return position;
    }

    public void setPosition(CompanyPositions position) {
        this.position = position;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ZonedDateTime getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(ZonedDateTime employmentDate) {
        this.employmentDate = employmentDate;
    }

    public ZonedDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(ZonedDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
