package com.example.bank.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "users")
@Getter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String username;

    String password;

    String name;

    String surname;

    String patronymic;

    @Column(name = "id_number")
    String idNumber;

    @Column(name = "birth_date")
    LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    Status status;

    @OneToMany(targetEntity = Account.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee")
    List<Account> accounts;

    @ManyToMany(targetEntity = Role.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    List<Role> roles;
}
