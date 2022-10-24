package com.example.bank.security;

import com.example.bank.entity.Employee;
import com.example.bank.exception.NotFoundException;
import com.example.bank.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final EmployeeRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) {
        String username = authentication.getName();
        Optional<Employee> employeeOptional = userRepository.findByUsername(username);
        Employee employee = employeeOptional.orElseThrow(NotFoundException::new);
        User userDetails = new User(employee.getUsername(), employee.getPassword(), true,
                true, true, true, new HashSet<>());
        return new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
