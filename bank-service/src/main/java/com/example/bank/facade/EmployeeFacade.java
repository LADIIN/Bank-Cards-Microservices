package com.example.bank.facade;

import com.example.bank.controller.dto.EmployeeDto;
import com.example.bank.controller.dto.SpecificationDto;
import com.example.bank.facade.mapper.EmployeeMapper;
import com.example.bank.domain.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeeFacade {

    private final EmployeeMapper employeeMapper;
    private final EmployeeService employeeService;

    public List<EmployeeDto> findAll(Pageable pageable, SpecificationDto specificationDto) {
        return employeeMapper.convertListToDto(employeeService.findAll(pageable, specificationDto));
    }
}
