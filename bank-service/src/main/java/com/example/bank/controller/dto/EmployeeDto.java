package com.example.bank.controller.dto;

import com.example.bank.domain.entity.Status;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    Long id;
    String name;
    String surname;
    String patronymic;
    String idNumber;
    LocalDate birthDate;
    Status status;
}


