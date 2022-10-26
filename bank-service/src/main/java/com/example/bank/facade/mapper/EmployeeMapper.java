package com.example.bank.facade.mapper;

import com.example.bank.controller.dto.EmployeeDto;
import com.example.bank.domain.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {

    public EmployeeDto convertToDto(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .surname(employee.getSurname())
                .patronymic(employee.getPatronymic())
                .idNumber(employee.getIdNumber())
                .birthDate(employee.getBirthDate())
                .status(employee.getStatus())
                .build();
    }

    public List<EmployeeDto> convertListToDto(List<Employee> employees) {
        return employees.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
