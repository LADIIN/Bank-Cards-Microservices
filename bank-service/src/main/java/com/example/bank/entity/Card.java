package com.example.bank.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "cards")
@Getter
@Setter
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String number;

    String status;

    String name;

    String surname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    Account account;
}
