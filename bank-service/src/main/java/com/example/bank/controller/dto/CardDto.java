package com.example.bank.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CardDto {

    Long id;
    String number;
    String status;
    String name;
    String surname;
    Long accountId;
}
