package com.example.bank.controller;

import com.example.bank.controller.dto.EmployeeDto;
import com.example.bank.controller.dto.SpecificationDto;
import com.example.bank.controller.facade.EmployeeFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeFacade employeeFacade;

    @GetMapping
    public List<EmployeeDto> findAll(Pageable pageable, SpecificationDto specificationDto) {
        return employeeFacade.findAll(pageable, specificationDto);
    }
}
