package com.example.bank.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @Column(name = "creation_date")
    LocalDateTime creationDateTime;

    @ManyToMany(targetEntity = Employee.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "roles")
    List<Employee> employees;
}
