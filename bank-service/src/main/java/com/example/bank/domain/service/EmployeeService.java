package com.example.bank.domain.service;

import com.example.bank.controller.dto.SpecificationDto;
import com.example.bank.domain.entity.Employee;
import com.example.bank.domain.entity.Status;
import com.example.bank.domain.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> findAll(Pageable pageable, SpecificationDto specificationDto) {
        Specification<Employee> specification = createSpecification(specificationDto);
        return employeeRepository.findAll(specification, pageable).toList();
    }

    private Specification<Employee> createSpecification(SpecificationDto specificationDto) {
        return Specification.where(createBirthDateAfterSpecification(specificationDto.getBirthDateAfter()))
                .and(createBirthDateBeforeSpecification(specificationDto.getBirthDateBefore()))
                .and(createIdNumberLikeSpecification(specificationDto.getIdNumber()))
                .and(createStatusInSpecification(specificationDto.getStatuses()));
    }

    private Specification<Employee> createBirthDateAfterSpecification(LocalDate birthDateAfter) {
        return birthDateAfter != null ? ((root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("birthDate"), birthDateAfter)) : null;
    }

    private Specification<Employee> createBirthDateBeforeSpecification(LocalDate birthDateBefore) {
        return birthDateBefore != null ? ((root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("birthDate"), birthDateBefore)) : null;
    }

    private Specification<Employee> createIdNumberLikeSpecification(String idNumber) {
        return idNumber != null ? (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("idNumber"), "%" + idNumber + "%") : null;
    }

    private Specification<Employee> createStatusInSpecification(List<Status> statuses) {
        return statuses != null ? (root, query, criteriaBuilder) ->
                criteriaBuilder.in(root.get("status")).value(statuses) : null;
    }
}
