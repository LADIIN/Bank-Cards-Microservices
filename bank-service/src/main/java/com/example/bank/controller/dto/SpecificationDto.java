package com.example.bank.controller.dto;

import com.example.bank.entity.Status;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class SpecificationDto {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate birthDateAfter;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate birthDateBefore;
    String idNumber;
    List<Status> statuses;
}
