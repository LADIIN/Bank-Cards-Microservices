package com.example.bank.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "accounts")
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue
    Long id;

    @Column(name = "acc_number")
    String accountNumber;

    String currency;

    @Enumerated(EnumType.STRING)
    Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    Employee employee;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "account")
    List<Card> cards;
}
